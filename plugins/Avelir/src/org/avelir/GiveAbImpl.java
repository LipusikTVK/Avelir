package org.avelir;

import org.avelir.gun.GunAbility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveAbImpl implements CommandExecutor
{
	MainPlugin context;
	
	public GiveAbImpl(MainPlugin context)
	{
		this.context = context;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		GunAbility ability = null;
		
		if (!(sender instanceof Player))
		{
			return false;
		}
		
		for (GunAbility ab : context.gunsAbilities)
		{
			if (ab.name.equals(args[0]))
			{
				ability = ab;
				break;
			}
		}
		
		if (ability == null)
		{
			sender.sendMessage("Не найден абилити!");
			return false;
		}
		
		Player player = (Player) sender;
		ItemStack itm = player.getInventory().getItemInMainHand();
		net.minecraft.world.item.ItemStack itm_n = CraftItemStack.asNMSCopy(itm);
		
		if (itm_n.getTag() == null)
		{
			sender.sendMessage("Предмет не является оружием из конфига!");
			return false;
		}
		
		if (itm_n.getTag().hasKey("ability_length"))
		{
			int len = itm_n.getTag().getInt("ability_length") + 1;
			itm_n.getTag().setInt("ability_length", len);
			itm_n.getTag().setString("ability_" + Integer.toString(len), ability.name);
		} else
		{
			int len = 0 + 1;
			itm_n.getTag().setInt("ability_length", len);
			itm_n.getTag().setString("ability_" + Integer.toString(len), ability.name);
		}
		
		itm = CraftItemStack.asBukkitCopy(itm_n);
		player.getInventory().setItemInMainHand(itm);
		
		return true;
	}

}
