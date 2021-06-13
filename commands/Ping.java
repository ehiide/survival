package mc.server.survival.commands;

import mc.server.survival.files.Configuration;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.utils.ChatUtil;
import mc.server.survival.utils.ServerUtil;
import mc.server.survival.utils.SoundUtil;
import mc.server.survival.utils.VisualUtil;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ping 
implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if (args.length == 0)
				ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cTwoj ping wynosi: #fff203" + ServerUtil.getPing(player) + " #8c8c8cms!");
			else
			{
				String potencial_player = args[0];
				
				for (Player dplayer : Bukkit.getOnlinePlayers())
					if (dplayer.getName().equalsIgnoreCase(potencial_player)) 
					{	
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cPing " + ChatUtil.returnPlayerColor(player) + dplayer.getName() + "#8c8c8c wynosi: #fff203" + ServerUtil.getPing(dplayer) + " #8c8c8cms!");
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