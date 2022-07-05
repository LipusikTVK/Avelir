package org.avelir;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveSampleImpl implements CommandExecutor
{
	MainPlugin context;
	
	public GiveSampleImpl(MainPlugin context)
	{
		this.context = context;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (!(sender instanceof Player))
		{
			return false;
		}
		
		ItemStack itm = new ItemStack(Material.DIAMOND_SWORD);
		net.minecraft.world.item.ItemStack itm_n = CraftItemStack.asNMSCopy(itm);
		itm_n.getOrCreateTag().setString("name", "knife");
		
		Player player = (Player) sender;
		ItemStack[] itms = {CraftItemStack.asBukkitCopy(itm_n)};
		player.getInventory().addItem(itms);
		
		return false;
	}

}
