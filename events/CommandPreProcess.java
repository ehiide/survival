package mc.server.survival.events;

import mc.server.survival.commands.AllowedCommands;
import mc.server.survival.files.Configuration;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.utils.SoundUtil;
import mc.server.survival.utils.VisualUtil;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;


public class CommandPreProcess 
implements Listener
{
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event) 
	{	
		if (!event.isCancelled())
		{
			String string = event.getMessage().split(" ")[0];
			HelpTopic helptopic = Bukkit.getServer().getHelpMap().getHelpTopic(string);
			Player player = event.getPlayer();

			if (helptopic != null)
			{
				for (String command : AllowedCommands.allowedCommnads)
					if (string.equalsIgnoreCase("/" + command))
						return;


				wrongCommand(event, player);
			}
			else
				wrongCommand(event, event.getPlayer());
		}
	}

	private void wrongCommand(PlayerCommandPreprocessEvent event, Player player)
	{
		ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474O chuj Ci chodzi! Ta komenda nie istnieje w bazie danych serwera!");
		VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
		VisualUtil.showPlayerParticle(player, Particle.FLAME);
		SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
		event.setCancelled(true);
	}
}