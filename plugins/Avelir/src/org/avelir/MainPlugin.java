/*
 * Основной плагин, отвечающий за поведение в игровом пространстве сервера.
 */
package org.avelir;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.avelir.gun.*;

import org.bukkit.plugin.java.JavaPlugin;
import org.ikayzo.sdl.Parser;
import org.ikayzo.sdl.Tag;

public class MainPlugin extends JavaPlugin
{
	Logger logger = getLogger();
	ArrayList<Gun> guns;
	ArrayList<GunAbility> gunsAbilities;
	String[] all_guns_names;
	String[] all_guns_capabilities_names;
	
	boolean canFind(String[] names, Tag tag)
	{
		for (String name : names)
		{
			if (name.equals(tag.getName()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	void parseConfigurations() throws Exception
	{
		Reader reader = null;
		
		try
		{
			reader = new FileReader("guns.description.sdl");
		} catch (FileNotFoundException e)
		{
			logger.info("Not find file guns.description.sdl!");
			e.printStackTrace();
			this.setEnabled(false);
		}
		
		Parser parser = new Parser(reader);
		List<Tag> tags = null;
		
		try
		{
			tags = parser.parse();
		} catch (Exception e)
		{
			logger.info("Not parse file guns.description.sdl!");
			e.printStackTrace();
		}
		
		Tag _all = null,
			_all_gun_capabilities = null;
		
		for (Tag tag : tags)
		{
			if (_all != null &&
				_all_gun_capabilities != null)
			{
				break;
			}
			
			if (tag.getName().equals("all"))
			{
				_all = tag;
				continue;
			} else
			if (tag.getName().equals("all_gun_capabilities"))
			{
				_all_gun_capabilities = tag;
				continue;
			}
		}
		
		if (_all == null)
		{
			logger.warning("Not find tag \"all\"!");
			this.getServer().shutdown();
		}
		
		all_guns_names = new String[_all.getValues().size()];
		for (int i = 0; i < _all.getValues().size(); i++)
		{
			all_guns_names[i] = (String) _all.getValues().get(i);
		}
		
		all_guns_capabilities_names = new String[_all_gun_capabilities.getValues().size()];
		for (int i = 0; i < _all_gun_capabilities.getValues().size(); i++)
		{
			all_guns_capabilities_names[i] = (String) _all_gun_capabilities.getValues().get(i);
		}
		
		guns = new ArrayList<>();
		gunsAbilities = new ArrayList<>();
		
		for (Tag tag : tags)
		{
			if (canFind(all_guns_names, tag))
			{
				Gun gun = new Gun();
				gun.parseFromSDL(tag);
				
				logger.info("Load gun: " + gun.name);
				
				guns.add(gun);
			} else
			if (canFind(all_guns_capabilities_names, tag))
			{
				GunAbility gunAbility = new GunAbility();
				gunAbility.parseFromSDL(tag);
				
				logger.info("Load ability: " + gunAbility.name);
				
				gunsAbilities.add(gunAbility);
			}
		}
		
		logger.info("Loaded guns: " + Integer.toString(guns.size()));
		logger.info("Loaded abilities: " + Integer.toString(gunsAbilities.size()));
	}
	
	@Override
	public void onEnable()
	{
		try
		{
			this.parseConfigurations();
		} catch (Exception e)
		{
			this.setEnabled(false);
			e.printStackTrace();
		}
		
		getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
		
		getCommand("givegun").setExecutor(new GiveSampleImpl(this));
		getCommand("giveab").setExecutor(new GiveAbImpl(this));
		getCommand("listab").setExecutor(new ListAbImpl());
	}
	
	@Override
	public void onDisable()
	{
		
	}
}
