package org.avelir.gun;

import java.util.List;
import java.util.Random;

import org.avelir.ProgressionCalc;
import org.ikayzo.*;
import org.ikayzo.sdl.Tag;

public class Gun
{
	enum Type
	{
		light_melee,
		heavy_melee,
		throwing,
		light_ranged,
		heavy_ranged
	}
	
	public String name;
	
	public Type type;
	public double[] damage;
	public boolean critical;
	public double[] critical_factor;
	public int hit_distance;
	public double hit_distance_factor;
	public String[] allowable_abilities;
	public String[] allowable_deal_damage_to;
	public ProgressionCalc level_damage_factor;
	
	public double uniform(double a, double b)
	{
		final Random random = new Random();
		final double diff = b - a;
		
		double i = random.nextDouble(diff);
		i += a;
		
		return i;
	}
	
	public double calculateDamage(
			double criticalChance,
			int distance,
			int level
	)
	{
		if (distance > hit_distance)
			return 0.0f;
		
		double result = 0.0f;
		result = uniform(damage[0], damage[1]);
		if (uniform(0f, 1f) > (criticalChance / 100f) && critical)
		{
			result = result * uniform(critical_factor[0], critical_factor[1]);
		}
		
		result = result * (1.0f - (hit_distance_factor * distance));
		result = result * level_damage_factor.take(level);
		
		return result;
	}
	
	public void parseFromSDL(Tag tag) throws Exception
	{
		Tag _type,
			_damage,
			_critical,
			_critical_factor,
			_hit_distance,
			_hit_distance_factor,
			_allowable_abilities,
			_allowable_deal_damage_to,
			_level_damage_factor;
		
		name = tag.getName();
		
		_type = tag.getChild("type");
		if (_type == null)
		{
			throw new Exception("Не найден тип оружия!");
		} else
		{
			String str_type = (String) _type.getValue();
			
			switch (str_type)
			{
			case "light_melee":
				type = Type.light_melee;
				break;
				
			case "heavy_melee":
				type = Type.heavy_melee;
				break;
				
			case "throwning":
				type = Type.throwing;
				break;
				
			case "light_ranged":
				type = Type.light_ranged;
				break;
				
			case "heavy_ranged":
				type = Type.heavy_ranged;
				break;
				
			default:
				throw new Exception("Нет такого типа орудия!");
			}
		}
		
		_damage = tag.getChild("damage");
		if (_damage == null)
		{
			throw new Exception("Не найден дипазон урона оружия!");
		} else
		{
			List<Object> values = _damage.getValues();
			damage = new double[2];
			damage[0] = (double) (double) values.get(0);
			damage[1] = (double) (double) values.get(1);
		}
		
		_critical = tag.getChild("critical");
		if (_critical == null)
		{
			critical = true;
			critical_factor = new double[2];
			critical_factor[0] = 1.5f;
			critical_factor[1] = 2.0f;
		} else
		{
			critical = (boolean) _critical.getValue();
			if (critical)
			{
				_critical_factor = tag.getChild("critical_factor");
				critical_factor = new double[2];
				List<Object> values = _critical_factor.getValues();
				
				critical_factor[0] = (double) (double) values.get(0);
				critical_factor[1] = (double) (double) values.get(1);
			}
		}
		
		_hit_distance = tag.getChild("hit_distance");
		if (_hit_distance == null)
		{
			if (type == Type.light_melee)
			{
				hit_distance = 5;
			} else
			if (type == Type.heavy_melee)
			{
				hit_distance = 3;
			} else
			{
				hit_distance = 100;
			}
		} else
		{
			hit_distance = (int) _hit_distance.getValue();
		}
		
		_hit_distance_factor = tag.getChild("hit_distance_factor");
		if (_hit_distance_factor == null)
		{
			hit_distance_factor = 0.005f;
		} else
		{
			hit_distance_factor = (double) (double) _hit_distance_factor.getValue();
		}
		
		_allowable_abilities = tag.getChild("allowable_abilities");
		if (_allowable_abilities == null)
		{
			allowable_abilities = new String[1];
			allowable_abilities[0] = "all";
		} else
		{
			List<Object> values = _allowable_abilities.getValues();
			allowable_abilities = new String[values.size()];
			for (int i = 0; i < values.size(); i++)
			{
				allowable_abilities[i] = (String) values.get(i);
			}
		}
		
		_allowable_deal_damage_to = tag.getChild("allowable_deal_damage_to");
		if (_allowable_deal_damage_to == null)
		{
			allowable_deal_damage_to = new String[1];
			allowable_deal_damage_to[0] = "all";
		} else
		{
			List<Object> values = _allowable_deal_damage_to.getValues();
			allowable_deal_damage_to = new String[values.size()];
			
			for (int i = 0; i < values.size(); i++)
			{
				allowable_deal_damage_to[i] = (String) values.get(i);
			}
		}
		
		_level_damage_factor = tag.getChild("level_damage_factor");
		if (_level_damage_factor == null)
		{
			level_damage_factor = new ProgressionCalc(0.01f, "custom", "a + b");
		} else
		{
			List<Object> values = _level_damage_factor.getValues();
			
			if (values.size() == 0)
			{
				level_damage_factor = new ProgressionCalc(0.01, "custom", "a + b");
			} else
			if (values.size() == 1)
			{
				level_damage_factor = new ProgressionCalc((double) (double) values.get(0), "custom", "a + b");
			} else
			if (values.size() >= 2)
			{
				String name_progression = (String) values.get(1);
				if (name_progression.equals("algebraic"))
				{
					level_damage_factor = new ProgressionCalc(
						(double) values.get(0),
						"custom",
						"a + b"
					);
					
					System.out.println("algebraic!");
				} else
				if (name_progression.equals("geometric"))
				{
					level_damage_factor = new ProgressionCalc(
						(double) values.get(0),
						"custom",
						"a * b"
					);
					
					System.out.println("geometric!");
				} else
				if (name_progression.equals("custom"))
				{
					level_damage_factor = new ProgressionCalc(
						(double) values.get(0),
						"custom",
						(String) values.get(2)
					);
					
					System.out.println("custom!");
				}
			}
		}
	}
}
