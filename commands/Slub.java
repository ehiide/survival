package mc.server.survival.commands;

import mc.server.Broadcaster;
import mc.server.survival.files.Configuration;
import mc.server.survival.files.Main;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.managers.DPlayerCloudManager;
import mc.server.survival.managers.DPlayerManager;
import mc.server.survival.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static mc.server.survival.managers.DPlayerManager.getInstance;

public class Slub
implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if (args.length == 0)
				ChatManager.sendMessages(player, "", "#f83044&m---------------------------------------",
						"#fc7474/slub (gracz) #8c8c8c- wysyla zareczyny do konkretnego gracza",
						"#fc7474/slub akceptuj (gracz) #8c8c8c- akceptuje zareczyny wyslane przez konkretnego gracza",
						"#fc7474/slub odrzuc (gracz) #8c8c8c- odrzuca zareczyny wyslane przez konkretnego gracza",
						"#fc7474/slub status [gracz] #8c8c8c- pozwala sprawdzac z kim jest w zwiazku dany gracz",
						"#fc7474/slub poziom #8c8c8c- pokazuje szczegoly dzialania systemu poziomow slubu",
						"&8&o(argumenty wymagane - (), argumewnty opcjonalne - [])",
						"#f83044&m---------------------------------------", "");
			else
			{
				if (player.getLocation().distance(WorldUtil.KOSCIOL) > 20)
				{
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Formalnosci ponad wszystko, najpierw udaj sie do kosciola!");
					VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
					VisualUtil.showPlayerParticle(player, Particle.FLAME);
					SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
					return true;
				}

				if (args.length >= 3)
				{
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Spokojnie, spokojnie, wpisz komende jeszcze raz, nie mozemy nadazyc nad toba!");
					VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
					VisualUtil.showPlayerParticle(player, Particle.FLAME);
					SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
					return true;
				}

				if (args[0].equalsIgnoreCase("akceptuj"))
				{
					if (args.length == 1)
					{
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cWpisz nazwe gracza, od ktorego otrzymales zareczyny!");
						return true;
					}

					if (DPlayerManager.getInstance().getMarry(player) != null)
					{
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Nie zdradzaj swojego partnera(ki), " + ChatUtil.returnPlayerColor(player) + DPlayerManager.getInstance().getMarry(player) + "!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						VisualUtil.showPlayerParticle(player, Particle.FLAME);
						SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
						return true;
					}

					String potencial_player = args[1];

					for (Player dplayer : Bukkit.getOnlinePlayers())
						if (dplayer.getName().equalsIgnoreCase(potencial_player))
						{
							if (DPlayerCloudManager.getMarry(dplayer) == player)
							{
								DPlayerCloudManager.setMarry(dplayer, null);
								DPlayerCloudManager.setMarry(player, null);
								DPlayerManager.getInstance().setMarryLevel(dplayer, 0);
								DPlayerManager.getInstance().setMarryLevel(player, 0);
								DPlayerManager.getInstance().setMarry(dplayer, player.getName());
								DPlayerManager.getInstance().setMarry(player, dplayer.getName());

								DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
								LocalDateTime now = LocalDateTime.now();
								String date = dateTimeFormatter.format(now);

								DPlayerManager.getInstance().setMarryDate(dplayer, date);
								DPlayerManager.getInstance().setMarryDate(player, date);

								Broadcaster.broadcastMessages("       ", "#ff03e2█&f█#ff03e2█&f█#ff03e2█", "&f█████                &f&l<#ff03e2&l!&f&l> #ff03e2&lSLUB WZIELI &f&l<#ff03e2&l!&f&l>",
										"&f█████" + ChatUtil.centerString("#8c8c8c" + player.getName() + " #ff03e2❤ #8c8c8c" + dplayer.getName(), 3, 0), "#ff03e2█&f███#ff03e2█" + ChatUtil.centerString(" &fCaly serwer zyczy wam wszystkiego dobrego!", 0, 1), "#ff03e2██&f█#ff03e2██", "");
								ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cMozesz pocalowac swoja milosc, zblizajac sie do niej i przytrzymujac prawy przycisk myszy (#fc7474PPM#8c8c8c)");
								ChatManager.sendMessage(dplayer, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cMozesz pocalowac swoja milosc, zblizajac sie do niej i przytrzymujac prawy przycisk myszy (#fc7474PPM#8c8c8c)");
								return true;
							}

							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Nic mi nie wiadomo o zadnym slubie!");
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
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cWpisz nazwe gracza, od ktorego otrzymales zareczyny!");
						return true;
					}

					String potencial_player = args[1];

					for (Player dplayer : Bukkit.getOnlinePlayers())
						if (dplayer.getName().equalsIgnoreCase(potencial_player))
						{
							if (DPlayerCloudManager.getMarry(dplayer) == player)
							{
								ChatManager.sendMessage(player, "&c&l» &fZareczyny od gracza " + ChatUtil.returnPlayerColor(player) + player.getName() + " &fzostaly odrzucone!");
								ChatManager.sendMessage(dplayer, "&c&l» #fc7474Twoje zarczyny do gracza " + ChatUtil.returnPlayerColor(dplayer) + dplayer.getName() + " #fc7474zostaly odrzucone, F!");
								DPlayerCloudManager.setMarry(dplayer, null);
								return true;
							}

							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Nic mi nie wiadomo o zadnym slubie!");
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
				else if (args[0].equalsIgnoreCase("rozwod"))
				{
					if (getInstance().getMarry(player) == null)
					{
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cNawet nie jestes w zwiazku!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						VisualUtil.showPlayerParticle(player, Particle.FLAME);
						SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
						return true;
					}

					if (args.length == 1)
					{
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cWpisz nazwe gracza, od ktorego chcesz sie uwolnic!");
						return true;
					}

					String potencial_player = args[1];

					for (Player dplayer : Bukkit.getOnlinePlayers())
						if (dplayer.getName().equalsIgnoreCase(potencial_player))
						{
							if (DPlayerManager.getInstance().getMarry(player).equalsIgnoreCase(dplayer.getName()))
							{
								DPlayerManager.getInstance().setMarry(dplayer, null);
								DPlayerManager.getInstance().setMarry(player, null);
								DPlayerManager.getInstance().setMarryDate(dplayer, null);
								DPlayerManager.getInstance().setMarryDate(player, null);
								DPlayerManager.getInstance().setMarryLevel(dplayer, 0);
								DPlayerManager.getInstance().setMarryLevel(player, 0);
								Broadcaster.broadcastMessages("   ", "#8c8c8c█&f█#8c8c8c█&f█#8c8c8c█", "&f█████              &f&l<#8c8c8c&l!&f&l> #8c8c8c&lROZWOD WZIELI &f&l<#8c8c8c&l!&f&l>",
										"&f█████           #8c8c8c" + player.getName() + " #8c8c8c</3 #8c8c8c" + dplayer.getName(), "#8c8c8c█&f███#8c8c8c█       &fMamy nadzieje ze do siebie wrocicie!", "#8c8c8c██&f█#8c8c8c██", "");
								return true;
							}
							else
							{
								ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Wy nawet nie jestescie razem!");
								VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
								VisualUtil.showPlayerParticle(player, Particle.FLAME);
								SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
							}
							return true;
						}

					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Twoja milosc musi byc on-line!");
					VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
					VisualUtil.showPlayerParticle(player, Particle.FLAME);
					SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
					return true;
				}
				else if (args[0].equalsIgnoreCase("poziom"))
				{
					if (getInstance().getMarry(player) == null)
					{
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cNawet nie jestes w zwiazku!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						VisualUtil.showPlayerParticle(player, Particle.FLAME);
						SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
						return true;
					}

					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cSystem poziomow pokazuje moc milosci w zwiazku, poziom mozna zwiekszyc calujac sie lub po prostu bedac razem przez dlugi czas! Jezeli chcesz zobaczyc poziom twojego slubu, wpisz komende #fc7474/slub status");
					return true;
				}
				else if (args[0].equalsIgnoreCase("status"))
				{
					if (args.length == 1)
					{
						if (getInstance().getMarry(player) == null)
						{
							ChatManager.sendMessages(player, "         ", "#ff03e2█&f█#ff03e2█&f█#ff03e2█", "&f█████   #ff03e2* #8c8c8cObecnie jestes singlem",
									"&f█████   #ff03e2* #8c8c8cData twojego slubu bedzie w przyszlosci ", "#ff03e2█&f███#ff03e2█   #ff03e2* #8c8c8cPoziom % alkoholu we krwi wynosi...", "#ff03e2██&f█#ff03e2██", "");
							return true;
						}

						int timexp = TimeUtil.getDifferenceInMinutes(DPlayerManager.getInstance().getMarryDate(player)) / 6;
						int xp = DPlayerManager.getInstance().getMarryLevel(player) + DPlayerManager.getInstance().getMarryLevel(DPlayerManager.getInstance().getMarry(player)) + timexp;
						int level = xp / 100;
						String s = Integer.toString(xp);
						String percentage;
						if (xp < 10)
							percentage = "0";
						else
						{
							percentage = Integer.toString(xp).charAt(s.length() - 2) + "0";
							if (percentage.equalsIgnoreCase("00"))
								percentage = "0";
						}

						ChatManager.sendMessages(player, "         ", "#ff03e2█&f█#ff03e2█&f█#ff03e2█", "&f█████   #ff03e2* #8c8c8cJestes w zwiazku z " + ChatUtil.returnPlayerColor(DPlayerManager.getInstance().getMarry(player)) + DPlayerManager.getInstance().getMarry(player),
								"&f█████   #ff03e2* #8c8c8cData waszego slubu to " + ChatUtil.returnPlayerColor(DPlayerManager.getInstance().getMarry(player)) + DPlayerManager.getInstance().getMarryDate(player), "#ff03e2█&f███#ff03e2█   #ff03e2* #8c8c8cPoziom waszego slubu wynosi " + ChatUtil.returnPlayerColor(DPlayerManager.getInstance().getMarry(player)) + level + " #8c8c8c(" + percentage + "%)", "#ff03e2██&f█#ff03e2██", "");
						return true;
					}

					String dplayer = args[1];

					if (!DPlayerManager.getInstance().checkExist(dplayer))
					{
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cTen gracz tu kiedykolwiek gral czy tak z dupy go podales?");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						VisualUtil.showPlayerParticle(player, Particle.FLAME);
						SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
						return true;
					}

					if (DPlayerManager.getInstance().getMarry(dplayer) == null)
					{
						ChatManager.sendMessages(player, "         ", "#ff03e2█&f█#ff03e2█&f█#ff03e2█", "&f█████   #ff03e2* #8c8c8cGracz " + ChatUtil.returnPlayerColor(dplayer) + dplayer.toLowerCase() + " #8c8c8cjest singlem",
								"&f█████   #ff03e2* #8c8c8cData jego slubu bedzie w przyszlosci ", "#ff03e2█&f███#ff03e2█   #ff03e2* #8c8c8cPoziom % alkoholu we krwi wynosi...", "#ff03e2██&f█#ff03e2██", "");
						return true;
					}

					int timexp = TimeUtil.getDifferenceInMinutes(DPlayerManager.getInstance().getMarryDate(dplayer)) / 6;
					int xp = DPlayerManager.getInstance().getMarryLevel(player) + DPlayerManager.getInstance().getMarryLevel(DPlayerManager.getInstance().getMarry(player)) + timexp;
					int level = xp / 100;
					String s = Integer.toString(xp);
					String percentage;
					if (xp < 10)
						percentage = "0";
					else
					{
						percentage = Integer.toString(xp).charAt(s.length() - 2) + "0";
						if (percentage.equalsIgnoreCase("00"))
							percentage = "0";
					}

					ChatManager.sendMessages(player, "         ", "#ff03e2█&f█#ff03e2█&f█#ff03e2█", "&f█████   #ff03e2* #8c8c8cGracz " + ChatUtil.returnPlayerColor(dplayer) + dplayer.toLowerCase() + " #8c8c8cjest z " + ChatUtil.returnPlayerColor(DPlayerManager.getInstance().getMarry(dplayer)) + DPlayerManager.getInstance().getMarry(dplayer),
							"&f█████   #ff03e2* #8c8c8cData ich slubu to " + ChatUtil.returnPlayerColor(DPlayerManager.getInstance().getMarry(dplayer)) + DPlayerManager.getInstance().getMarryDate(dplayer), "#ff03e2█&f███#ff03e2█   #ff03e2* #8c8c8cPoziom ich slubu wynosi " + ChatUtil.returnPlayerColor(DPlayerManager.getInstance().getMarry(player)) + level + " #8c8c8c(" + percentage + "%)", "#ff03e2██&f█#ff03e2██", "");
					return true;
				}

				if (DPlayerManager.getInstance().getMarry(player) != null)
				{
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Nie zdradzaj swojego partnera(ki), " + ChatUtil.returnPlayerColor(player) + DPlayerManager.getInstance().getMarry(player) + "!");
					VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
					VisualUtil.showPlayerParticle(player, Particle.FLAME);
					SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
					return true;
				}

				String potencial_player = args[0];

				for (Player dplayer : Bukkit.getOnlinePlayers())
					if (dplayer.getName().equalsIgnoreCase(potencial_player))
					{
						if (dplayer.getName().equalsIgnoreCase(player.getName()))
						{
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Nie mozesz byc w zwiazku sam ze soba narcyzie!");
							VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
							VisualUtil.showPlayerParticle(player, Particle.FLAME);
							SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
							return true;
						}

						DPlayerCloudManager.setMarry(player, null);
						DPlayerCloudManager.setMarry(player, dplayer);
						ChatManager.sendMessage(player, "&c&l» &f&oWyslano zareczyny do gracza " + ChatUtil.returnPlayerColor(player) + "&o" + dplayer.getName() + "!");
						ChatManager.sendMessage(dplayer, "&c&l» &f&oOtrzymano zareczyny od gracza " + ChatUtil.returnPlayerColor(player) + "&o" + player.getName() + "!\n#fc7474&o/slub akceptuj/odrzuc (gracz)&f&o - zaakceptowanie/odrzucenie\n#fc7474&o" + Configuration.SERVER_MARRY_REQUEST_TIME + " sekund&f&o - czas oczekiwania");
						VisualUtil.showDelayedTitle(dplayer, "&7od: " + player.getName(), "#ff03e2❤", 0, 20, 20);

						new BukkitRunnable() { @Override public void run() {
							DPlayerCloudManager.setMarry(player, null);
						} }.runTaskLaterAsynchronously(Main.getInstance(), 20*Configuration.SERVER_MARRY_REQUEST_TIME);
						return true;
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