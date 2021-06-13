package mc.server.survival.commands;

import mc.server.survival.files.Configuration;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.managers.DPlayerManager;
import mc.server.survival.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Zaplac
implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if (args.length >= 3)
			{
				ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cCo Ty tworzysz! Wzor komendy to #fc7474/zaplac (gracz) (ilosc)!");
				VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
				VisualUtil.showPlayerParticle(player, Particle.FLAME);
				SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
				return true;
			}
			
			if (args.length == 0)
				ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cSponsor, widze! Sluchaj... podaj nazwe gracza, ktoremu chcesz zaplacic!");
			else
			{
				String potencial_player = args[0];
				
				for (Player dplayer : Bukkit.getOnlinePlayers())
					if (dplayer.getName().equalsIgnoreCase(potencial_player)) 
					{	
						if (args.length == 1)
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cWprowadz sume monet jaka chcesz przelac graczowi " + ChatUtil.returnPlayerColor(player) + dplayer.getName() + "!");
						else
							{
							if (MathUtil.isInteger(args[1]))
							{
								int check = Integer.parseInt(args[1]);
								
								if (check > DPlayerManager.getInstance().getMoney(player))
								{
									ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Nie posiadasz nawet tyle w portfelu!");
									VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
									VisualUtil.showPlayerParticle(player, Particle.FLAME);
									SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
									return true;
								}
								
								DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - check);
								DPlayerManager.getInstance().setMoney(dplayer, DPlayerManager.getInstance().getMoney(dplayer) + check);
								ChatManager.sendMessage(player, "&c&l» &f&oPrzelano kwote &c&o" + check + ",- &f&omonet do " + ChatUtil.returnPlayerColor(dplayer) + "&o" + dplayer.getName() + "!");
								ChatManager.sendMessage(dplayer, "&c&l» &f&oOtrzymano kwote &c&o" + check + ",- &f&omonet od " + ChatUtil.returnPlayerColor(player) + "&o" + player.getName() + "!");
								VisualUtil.showDelayedTitle(dplayer, "&7od: " + player.getName(), "#ffc936⛃", 0, 10, 10);
								ServerUtil.reloadContents(player);
								ServerUtil.reloadContents(dplayer);
								return true;
							}
							
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Ta kwota to liczba?!");
							VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
							VisualUtil.showPlayerParticle(player, Particle.FLAME);
							SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
						}
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