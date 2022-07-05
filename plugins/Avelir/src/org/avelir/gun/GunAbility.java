package org.avelir.gun;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;

import org.avelir.ProgressionCalc;
import org.ikayzo.sdl.Tag;

public class GunAbility
{
	public String name;
	public String[] applying;
	public HashMap<String, GunAbilityProperties> properties;
	
	public GunAbility()
	{
		properties = new HashMap<>();
	}
	
	public HashMap<String, ProgressionCalc> damage_event()
	{
		HashMap<String, ProgressionCalc> result = new HashMap<>();
		
		result.put("damage", properties.get("damage").damage());
		result.put("repair", properties.get("damage").repair());
		
		return result;
	}
	
	public void parseFromSDL(Tag tag) throws Exception
	{
		name = tag.getName();
		
		Tag _applying = tag.getChild("applying");
		
		if (_applying == null)
		{
			throw new Exception("Неизвестно, какие события должен отслеживать способность оружия!");
		} else
		{
			List<Object> values = _applying.getValues();
			applying = new String[values.size()];
			for (int i = 0; i < values.size(); i++)
			{
				applying[i] = (String) values.get(i);
			}
		}
		
		for (String event : applying)
		{
			GunAbilityProperties property = new GunAbilityProperties();
			property.parseFromSDL(tag.getChild(event));
			
			properties.put(event, property);
		}
	}
}
