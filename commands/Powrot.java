package mc.server.survival.commands;

import mc.server.survival.files.Configuration;
import mc.server.survival.files.Main;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.managers.DPlayerManager;
import mc.server.survival.utils.*;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Powrot 
implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if (args.length == 0)
				if (DPlayerManager.getInstance().getLastLocation(player).equals(WorldUtil.SURVIVAL_SPAWN))
				{
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Miejsce, na ktore probujesz powrocic zostalo juz przez Ciebie uzyte lub odleglosc do niego jest zbyt mala!");
					VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
					VisualUtil.showPlayerParticle(player, Particle.FLAME);
					SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
				}
				else
				{
					player.teleport(DPlayerManager.getInstance().getLastLocation(player));

					new BukkitRunnable() {
						@Override
						public void run() {
							player.closeInventory();
							VisualUtil.showServerChangeTitle(player);
							ScoreboardUtil.showScoreboard(player);
							TablistUtil.showTablist(player);
						}
					}.runTaskLater(Main.getInstance(), 20L);

					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cTrwa teleportacja do ostatniej lokacji... #fff203⌛");
					DPlayerManager.getInstance().resetLastLocation(player);
				}
			else
			{
				ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Umiesz pisac?! Wystarczy napisac #ffc936/powrot!");
				VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
				VisualUtil.showPlayerParticle(player, Particle.FLAME);
				SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
			}
			return true;
		}
		
		return false;
	}
}