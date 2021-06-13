package mc.server.survival.commands;

import mc.server.survival.files.Configuration;
import mc.server.survival.files.Main;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.managers.DPlayerCloudManager;
import mc.server.survival.utils.ChatUtil;
import mc.server.survival.utils.SoundUtil;
import mc.server.survival.utils.VisualUtil;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TPA
implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if (sender instanceof Player)
  		{
			Player player = (Player) sender;
			
			if (args.length == 0)
				ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cWpisz nazwe gracza, do ktorego chcesz sie przeteleportowac!");
			else
			{
				if (args.length >= 3)
				{
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Spokojnie, spokojnie, jeden gracz wystarczy!");
					VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
					VisualUtil.showPlayerParticle(player, Particle.FLAME);
					SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
					return true;
				}

				if (args[0].equalsIgnoreCase("akceptuj"))
				{
					if (args.length == 1)
					{
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cWpisz nazwe gracza, od ktorego otrzymales prosbe teleportacji!");
						return true;
					}

					String potencial_player = args[1];

					for (Player dplayer : Bukkit.getOnlinePlayers())
						if (dplayer.getName().equalsIgnoreCase(potencial_player))
						{
							if (DPlayerCloudManager.getTPA(dplayer) == player)
							{
								ChatManager.sendMessage(player, "&c&l» &fProsba teleportacji od gracza " + ChatUtil.returnPlayerColor(dplayer) + dplayer.getName() + " &fzostala zaakceptowana!");
								ChatManager.sendMessage(dplayer, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cTrwa teleportacja do gracza " + ChatUtil.returnPlayerColor(player) + player.getName() + "... #fff203⌛");
								DPlayerCloudManager.setTPA(dplayer, null);
								dplayer.teleport(player);
								return true;
							}

							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Wy mieliscie sie tepnac? Moze czas minal, moze gracz wyslal juz prosbe komus innemu, ja nic nie wiem!");
							VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
							VisualUtil.showPlayerParticle(player, Particle.FLAME);
							SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
							return true;
						}

					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Podany gracz nie jest on-line na serwerze!");
					VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
					VisualUtil.showPlayerParticle(player, Particle.FLAME);
					SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
					return true;
				}
				else if (args[0].equalsIgnoreCase("odrzuc"))
				{
					if (args.length == 1)
					{
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cWpisz nazwe gracza, od ktorego otrzymales prosbe teleportacji!");
						return true;
					}

					String potencial_player = args[1];

					for (Player dplayer : Bukkit.getOnlinePlayers())
						if (dplayer.getName().equalsIgnoreCase(potencial_player))
						{
							if (DPlayerCloudManager.getTPA(dplayer) == player)
							{
								ChatManager.sendMessage(dplayer, "&c&l» #fc7474Prosba teleportacji do gracza " + ChatUtil.returnPlayerColor(player) + player.getName() + " #fc7474zostala odrzucona!");
								ChatManager.sendMessage(player, "&c&l» &fProsba teleportacji od gracza " + ChatUtil.returnPlayerColor(player) + dplayer.getName() + " &fzostala odrzucona!");
								DPlayerCloudManager.setTPA(dplayer, null);
								return true;
							}

							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Wy mieliscie sie tepnac? Moze czas minal, moze gracz wyslal juz prosbe komus innemu, ja nic nie wiem!");
							VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
							VisualUtil.showPlayerParticle(player, Particle.FLAME);
							SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
							return true;
						}

					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Podany gracz nie jest on-line na serwerze!");
					VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
					VisualUtil.showPlayerParticle(player, Particle.FLAME);
					SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
					return true;
				}

				String potencial_player = args[0];

				for (Player dplayer : Bukkit.getOnlinePlayers())
				{
					if (dplayer.getName().equalsIgnoreCase(potencial_player))
					{
						DPlayerCloudManager.setTPA(player, null);
						DPlayerCloudManager.setTPA(player, dplayer);
						ChatManager.sendMessage(player, "&c&l» &f&oWyslano prosbe teleportacji do gracza " + ChatUtil.returnPlayerColor(dplayer) + "&o" + dplayer.getName() + "!");
						ChatManager.sendMessage(dplayer, "&c&l» &f&oOtrzymano prosbe teleportacji od gracza " + ChatUtil.returnPlayerColor(player) +"&o" + player.getName() + "!\n#fc7474&o/tpa akceptuj/odrzuc (gracz)&f&o - zaakceptowanie/odrzucenie\n#fc7474&o" + Configuration.SERVER_TELEPORT_REQUEST_TIME + " sekund&f&o - czas oczekiwania");
						VisualUtil.showDelayedTitle(dplayer, "&7od: " + player.getName(), "#80ff1f⌛", 0, 20, 20);

						new BukkitRunnable() { @Override public void run() {
							DPlayerCloudManager.setTPA(player, null);
						} }.runTaskLaterAsynchronously(Main.getInstance(), 20*Configuration.SERVER_TELEPORT_REQUEST_TIME);
						return true;
					}
				}

				ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Podany gracz nie jest on-line na serwerze!");
				VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
				VisualUtil.showPlayerParticle(player, Particle.FLAME);
				SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
			}
			return true;
		}
		
		return false;
	}
}