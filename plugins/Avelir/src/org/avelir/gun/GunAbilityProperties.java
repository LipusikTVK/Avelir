package org.avelir.gun;

import java.util.List;

import org.avelir.ProgressionCalc;
import org.ikayzo.sdl.Tag;

public class GunAbilityProperties
{
	ProgressionCalc damage_factor;
	ProgressionCalc repair_factor;
	String[] allowable_deal_damage_to;
	
	public ProgressionCalc damage()
	{
		return damage_factor;
	}
	
	public ProgressionCalc repair()
	{
		return repair_factor;
	}
	
	void parseFromSDL(Tag tag) throws Exception
	{
		Tag _damage_factor,
			_repair_factor,
			_allowable_deal_damage_to;
		
		_damage_factor = tag.getChild("damage_factor");
		if (_damage_factor == null)
		{
			throw new Exception("Нужно указать значение урона способности!");
		} else
		{
			if (_damage_factor.getValue().getClass() == Double.class)
			{
				damage_factor = new ProgressionCalc(
					(float) (double) _damage_factor.getValue(),
					"custom",
					"a + b"
				);
			} else
			{
				damage_factor = new ProgressionCalc(
					1f,
					"custom",
					(String) _damage_factor.getValue()
				);
			}
		}
		
		_repair_factor = tag.getChild("damage_factor");
		if (_repair_factor == null)
		{
			throw new Exception("Нужно указать значение починки способности!");
		} else
		{
			if (_repair_factor.getValue().getClass() == Double.class)
			{
				repair_factor = new ProgressionCalc(
					(float) (double) _repair_factor.getValue(),
					"custom",
					"a + b"
				);
			} else
			{
				repair_factor = new ProgressionCalc(
					1f,
					"custom",
					(String) _repair_factor.getValue()
				);
			}
		}
		
		_allowable_deal_damage_to = tag.getChild("allowable_deal_damage_to");
		if (_allowable_deal_damage_to == null)
		{
			throw new Exception("Нужно указать, к кому нужно применять событие!");
		} else
		{
			List<Object> values = _allowable_deal_damage_to.getValues();
			allowable_deal_damage_to = new String[values.size()];
			
			for (int i = 0; i < values.size(); i++)
			{
				allowable_deal_damage_to[i] = (String) values.get(i);
			}
		}
	}
}
