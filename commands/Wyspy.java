package mc.server.survival.commands;

import mc.server.survival.files.Configuration;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.utils.InventoryUtil;
import mc.server.survival.utils.SoundUtil;
import mc.server.survival.utils.VisualUtil;
import mc.server.survival.utils.WorldUtil;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Wyspy
implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if (!player.getWorld().getName().equalsIgnoreCase("survival"))
			{
				ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Ale wyspy sa tylko na mapie survivalowej ziomek ¯\\_(ツ)_/¯");
				VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
				VisualUtil.showPlayerParticle(player, Particle.FLAME);
				SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
				return true;
			}
			
			if (args.length == 0)
			{
				InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lWYSPY"), "wyspy");
				return true;
			}
			else
			{
				if (args.length == 1)
				{
					if (args[0].equalsIgnoreCase("dzungla"))
					{
						player.teleport(WorldUtil.WYSPA_DZUNGLA_SPAWN);
					}
					else if (args[0].equalsIgnoreCase("grzybowa"))
					{
						player.teleport(WorldUtil.WYSPA_GRZYBOWA_SPAWN);
					}
					else if (args[0].equalsIgnoreCase("las"))
					{
						player.teleport(WorldUtil.WYSPA_LAS_SPAWN);
					}
					else if (args[0].equalsIgnoreCase("lodowa"))
					{
						player.teleport(WorldUtil.WYSPA_LODOWA_SPAWN);
					}
					else if (args[0].equalsIgnoreCase("savanna"))
					{
						player.teleport(WorldUtil.WYSPA_SAVANNA_SPAWN);
					}
					else if (args[0].equalsIgnoreCase("gory"))
					{
						player.teleport(WorldUtil.WYSPA_GORY_SPAWN);
					}
					else if (args[0].equalsIgnoreCase("pustynna"))
					{
						player.teleport(WorldUtil.WYSPA_PUSTYNNA_SPAWN);
					}
					else if (args[0].equalsIgnoreCase("messa"))
					{
						player.teleport(WorldUtil.WYSPA_MESSA_SPAWN);
					}
					else
					{
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Co?! Nie ma takiej wyspy! Dostepne to: #fff203dzungla#fc7474, #fff203grzybowa#fc7474, #fff203las#fc7474, #fff203lodowa#fc7474, #fff203savanna#fc7474, #fff203gory#fc7474, #fff203pustynna#fc7474 i #fff203messa#fc7474!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						VisualUtil.showPlayerParticle(player, Particle.FLAME);
						SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
						return true;
					}
					
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cTrwa teleportacja na wyspe: #fff203" + args[0].toUpperCase() + "... #fff203⌛");
					return true;
				}
				
				ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Mozesz wpisac nazwe wyspy za pomoca komendy #ffc936/wyspa (nazwa)#fc7474, ale nie mozesz tepnac sie do dwoch na raz!");
				VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
				VisualUtil.showPlayerParticle(player, Particle.FLAME);
				SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
				return true;
			}
		}
		
		return false;
	}
}