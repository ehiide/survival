package mc.server.survival.events;

import mc.server.Broadcaster;
import mc.server.survival.files.Configuration;
import mc.server.survival.files.Main;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.managers.DPlayerCloudManager;
import mc.server.survival.managers.DPlayerManager;
import mc.server.survival.managers.NeuroManager;
import mc.server.survival.utils.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerJoin 
implements Listener
{
	@EventHandler
	public void onEvent(PlayerJoinEvent event)
	{
		event.setJoinMessage(null);
		
		Player player = event.getPlayer();
		player.teleport(WorldUtil.SURVIVAL_SPAWN);
		player.setWalkSpeed(0.2f);

		if (player.hasPlayedBefore())
		{
			new BukkitRunnable() { @Override public void run() {
				ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX +
				"Poniewaz znajdowales sie poza spawnem, teleportowano Cie do niego. " +
			    "Jezeli chcesz powrocic na swoje ostatnie miejsce wpisz komende #fc7474/powrot!");
			} }.runTaskLaterAsynchronously(Main.getInstance(), 60L);
		}

		Broadcaster.broadcastMessage(Configuration.PLUGIN_FULL_PREFIX + "Gracz " + player.getName() + " dolaczyl na serwer!");
		VisualUtil.showServerChangeTitle(player);
		VisualUtil.spawnFirework(player.getLocation());

		DPlayerManager.getInstance().loadDplayer(player);
		ServerUtil.reloadContents(player);
        NeuroManager.apply(player);

		player.setMaxHealth(20.0 + (4.0D * DPlayerManager.getInstance().getUpgradeLevel(player.getName(), "vitality")));
		if (TimeUtil.getDifferenceInMinutes(DPlayerManager.getInstance().getLogged(player)) > 60*24*7)
			QuestUtil.manageQuest(player, 10);
	}
	
	@EventHandler
	public void onEvent(PlayerQuitEvent event)
	{
		event.setQuitMessage(null);
		
		Player player = event.getPlayer();
		removeLocalData(player);
		
		DPlayerManager.getInstance().setLastLocation(player, player.getLocation());
		Broadcaster.broadcastMessage(Configuration.PLUGIN_FULL_PREFIX + "Gracz " + player.getName() + " opuscil serwer!");
		VisualUtil.spawnFirework(player.getLocation());
	}

	private void removeLocalData(Player player)
    {
		PacketUtil.cancelTask(player, PacketUtil.shakeEffect);
		PacketUtil.cancelTask(player, PacketUtil.overdoseEffect);
		PacketUtil.cancelTask(player, PacketUtil.heartEffect);
		PacketUtil.cancelTask(player, DPlayerCloudManager.animated_scoreboard);
    }
}