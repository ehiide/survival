package mc.server.survival.commands;

import mc.server.survival.files.Configuration;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.utils.InventoryUtil;
import mc.server.survival.utils.SoundUtil;
import mc.server.survival.utils.VisualUtil;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Craftingi
implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if (args.length == 0)
				InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lCRAFTINGI (1/7)"), "craftingi1");
			else
			{
				if (args.length <= 1)
				{
					if (args[0].equalsIgnoreCase("1"))
					{
						InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lCRAFTINGI (1/7)"), "craftingi1");
						return true;
					}
					else if (args[0].equalsIgnoreCase("2"))
					{
						InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lCRAFTINGI (2/7)"), "craftingi2");
						return true;
					}
					else if (args[0].equalsIgnoreCase("3"))
					{
						InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lCRAFTINGI (3/7)"), "craftingi3");
						return true;
					}
					else if (args[0].equalsIgnoreCase("4"))
					{
						InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lCRAFTINGI (4/7)"), "craftingi4");
						return true;
					}
					else if (args[0].equalsIgnoreCase("5"))
					{
						InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lCRAFTINGI (5/7)"), "craftingi5");
						return true;
					}
					else if (args[0].equalsIgnoreCase("6"))
					{
						InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lCRAFTINGI (6/7)"), "craftingi6");
						return true;
					}
					else if (args[0].equalsIgnoreCase("7"))
					{
						InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lCRAFTINGI (7/7)"), "craftingi7");
						return true;
					}
					
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Koledze sie chyba strony pojebaly! Mozesz wybrac strone #ffc9361-7!");
				}
				else
				{
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Umiesz pisac?! Wystarczy napisac #ffc936/craftingi (strona)!");
				}
				VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
				VisualUtil.showPlayerParticle(player, Particle.FLAME);
				SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
			}
			return true;
		}
		
		return false;
	}
}