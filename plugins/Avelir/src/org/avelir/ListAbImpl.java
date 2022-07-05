package org.avelir;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ListAbImpl implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (!(sender instanceof Player))
		{
			return false;
		}
		
		Player player = (Player) sender;
		
		ItemStack itm = player.getInventory().getItemInMainHand();
		net.minecraft.world.item.ItemStack itm_n = CraftItemStack.asNMSCopy(itm);
		if (itm_n.getTag() == null)
		{
			sender.sendMessage("Not find NBT tags!");
			return false;
		}
		
		if (!itm_n.getTag().hasKey("ability_length"))
		{
			sender.sendMessage("No abilities.");
			return false;
		}
		
		int len = itm_n.getTag().getInt("ability_length");
		for (int i = 0; i < len; i++)
		{
			sender.sendMessage(itm_n.getTag().getString("ability_" + Integer.toString(i + 1)));
		}
		
		return true;
	}

}
