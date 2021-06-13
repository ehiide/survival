package mc.server.survival.events;

import mc.server.survival.files.Configuration;
import mc.server.survival.files.Main;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.utils.ServerUtil;
import mc.server.survival.utils.VisualUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Portal 
implements Listener
{
	@EventHandler
	public void onEvent(PlayerPortalEvent event)
	{
		Player player = event.getPlayer();
		String cause = event.getCause().toString();
		
		if (event.getFrom().getWorld().getName().equalsIgnoreCase("surowce")) {
			ChatManager.sendMessage(event.getPlayer(), Configuration.PLUGIN_FULL_PREFIX + "#fc7474Do swiata netheru mozesz dostac sie tylko przez swiat survivalowy!");
			VisualUtil.showDelayedTitle(event.getPlayer(), "&c✖", "", 0, 20, 20);
			event.setCancelled(true);
			return;
		}

		if (cause.equalsIgnoreCase("END_PORTAL"))
		{
			if (Configuration.SERVER_BLOCK_THE_END)
			{
				ChatManager.sendMessage(event.getPlayer(), Configuration.PLUGIN_FULL_PREFIX + "#fc7474Swiat endu jest obecnie zamkniety!");
				VisualUtil.showDelayedTitle(event.getPlayer(), "&c✖", "", 0, 20, 20);
				event.setCancelled(true);
				return;
			}
			
			doTeleport(player);
			return;
		}
		
		if (cause.equalsIgnoreCase("NETHER_PORTAL"))
			if (Configuration.SERVER_BLOCK_NETHER)
			{
				ChatManager.sendMessage(event.getPlayer(), Configuration.PLUGIN_FULL_PREFIX + "#fc7474Swiat netheru jest obecnie zamkniety!");
				VisualUtil.showDelayedTitle(event.getPlayer(), "&c✖", "", 0, 20, 20);
				event.setCancelled(true);
			}
			else
				doTeleport(player);
	}
	
	private void doTeleport(Player player)
	{
		new BukkitRunnable() { @Override public void run() {
			VisualUtil.showServerChangeTitle(player);
			ServerUtil.reloadContents(player);
		} }.runTaskLater(Main.getInstance(), 20L);
	}
}