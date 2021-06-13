package mc.server.survival.events;

import mc.server.survival.files.Main;
import mc.server.survival.managers.NeuroManager;
import mc.server.survival.utils.ServerUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Respawn
implements Listener
{
    @EventHandler
    public void onEvent(PlayerRespawnEvent event)
    {
        new BukkitRunnable() { @Override public void run() {
            ServerUtil.reloadContents(event.getPlayer());
            NeuroManager.apply(event.getPlayer());
        } }.runTaskLater(Main.getInstance(), 20L);
    }
}