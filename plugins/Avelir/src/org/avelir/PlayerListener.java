package org.avelir;

import java.util.HashMap;

import org.avelir.gun.Gun;
import org.avelir.gun.GunAbility;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener
{
	MainPlugin context;
	
	public PlayerListener(MainPlugin context)
	{
		this.context = context;
	}
	
	@EventHandler
	void onEntityDamage(EntityDamageByEntityEvent event)
	{
		if (!(event.getDamager() instanceof Player))
		{
			return;
		}
		
		int level = 5;
		double damage = event.getDamage();
		
		Player player = (Player) event.getDamager();
		net.minecraft.world.item.ItemStack itm = org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand());
		
		if (itm.getTag() != null)
		{
			String name = itm.getTag().getString("name");
			for (int i = 0; i < context.guns.size(); i++)
			{
				Gun gun = context.guns.get(i);
				if (gun.name.equals(name))
				{
					damage = gun.calculateDamage(gun.uniform(0, 100), 1, level);
				}
			}
			
			if (itm.getTag().hasKey("ability_length"))
			{
				String[] ab_list = new String[itm.getTag().getInt("ability_length")];
				GunAbility[] g_ab = new GunAbility[ab_list.length];
				for (int i = 0; i < ab_list.length; i++)
				{
					ab_list[i] = itm.getTag().getString("ability_" + Integer.toString(i + 1));
					for (int j = 0; j < context.gunsAbilities.size(); j++)
					{
						if (context.gunsAbilities.get(j).name.equals(ab_list[i]))
						{
							g_ab[j] = context.gunsAbilities.get(i);
							break;
						}
					}
				}
				
				for (int i = 0; i < g_ab.length; i++)
				{
					if (g_ab[i] == null)
						continue;
					
					System.out.println("AB_NAME: " + g_ab[i].name);
					
					HashMap<String, ProgressionCalc> d_event = g_ab[i].damage_event();
					
					System.out.println("AB_FACTOR: " + d_event.get("damage").take(level));
					
					damage = damage * d_event.get("damage").take(level);
				}
			}
		}
		
		context.logger.info("damage: " + Double.toString(damage));
		
		event.setDamage(damage);
	}
}
