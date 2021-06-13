package mc.server.survival.commands;

import mc.server.survival.files.Configuration;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.managers.DPlayerManager;
import mc.server.survival.utils.SoundUtil;
import mc.server.survival.utils.VisualUtil;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vanish implements CommandExecutor
{
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;

			if (!DPlayerManager.getInstance().getRank(player).equalsIgnoreCase("administrator"))
			{
				ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Najpierw daj dupy adminowi, a potem rob co tylko zechcesz!");
				VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
				VisualUtil.showPlayerParticle(player, Particle.FLAME);
				SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
				return true;
			}
			
			if (args.length == 0)
			{
				if (DPlayerManager.getInstance().getVanished(player))
				{
					DPlayerManager.getInstance().setVanished(player, false);
					
					for (Player online : Bukkit.getOnlinePlayers())
						online.hidePlayer(player);

					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fFunkcja ukrycia zostala wlaczona!");
				}
				else
				{
					DPlayerManager.getInstance().setVanished(player, true);
					
					for (Player online : Bukkit.getOnlinePlayers())
						online.showPlayer(player);

					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Funkcja ukrycia zostala wylaczona!");
				}
			}
			else
			{
				ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Umiesz pisac?! Wystarczy napisac #ffc936/vanish!");
				VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
				VisualUtil.showPlayerParticle(player, Particle.FLAME);
				SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
			}
			return true;
		}
		
		return false;
	}
}