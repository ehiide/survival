package mc.server.survival.commands;

import mc.server.survival.files.Configuration;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.utils.SoundUtil;
import mc.server.survival.utils.VisualUtil;
import mc.server.survival.utils.WorldUtil;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if (args.length == 0)
				if (player.getWorld().getName().equalsIgnoreCase("survival"))
					if (player.getLocation().distance(WorldUtil.SURVIVAL_SPAWN) > 30)
					{
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cTrwa teleportacja na spawn serwera... #fff203⌛");
						
						player.teleport(WorldUtil.SURVIVAL_SPAWN);
					}
					else
					{
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Nie mozesz przeteleportowac sie na spawn, poniewaz juz na nim jestes!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						VisualUtil.showPlayerParticle(player, Particle.FLAME);
						SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
					}
				else
				{
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Nie mozesz uzyc tutaj tej komendy!");
					VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
					VisualUtil.showPlayerParticle(player, Particle.FLAME);
					SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
				}
			else
			{
				ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Umiesz pisac?! Wystarczy napisac #ffc936/spawn!");
				VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
				VisualUtil.showPlayerParticle(player, Particle.FLAME);
				SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
			}
			return true;
		}
		
		return false;
	}
}