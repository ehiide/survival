package mc.server.survival.events;

import mc.server.Broadcaster;
import mc.server.survival.files.Main;
import mc.server.survival.managers.DPlayerManager;
import mc.server.survival.utils.QuestUtil;
import mc.server.survival.utils.ServerUtil;
import mc.server.survival.utils.WorldUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerDeath implements Listener
{
	@EventHandler
	public void onEvent(PlayerDeathEvent event)
	{
		event.setDeathMessage(null);
		Player death = event.getEntity();
		
		if (event.getEntity().getKiller() instanceof Player)
		{
			Player killer = event.getEntity().getKiller();
			DPlayerManager.getInstance().setKills(killer, DPlayerManager.getInstance().getKills(killer) + 1);
			ServerUtil.reloadContents(killer);

			Broadcaster.broadcastMessage("#f83044&l⚔ #8c8c8cGracz " + death.getName() + " (#80ff1f" + DPlayerManager.getInstance().getKills(death) + "⚔#8c8c8c/#fc7474" + DPlayerManager.getInstance().getDeaths(death) + "☠#8c8c8c) zostal zabity przez " + killer.getName() + " (#80ff1f" + DPlayerManager.getInstance().getKills(killer) + "⚔#8c8c8c/#fc7474" + DPlayerManager.getInstance().getDeaths(killer) + "☠#8c8c8c)!");
			QuestUtil.manageQuest(killer, 6);
		}
		else
			if (DPlayerManager.getInstance().getMoney(death) >= 10)
			{
				DPlayerManager.getInstance().setMoney(death, DPlayerManager.getInstance().getMoney(death) - (int) (DPlayerManager.getInstance().getMoney(death) * getMoneyBack(death)));
				ServerUtil.reloadContents(death);
			}

		new BukkitRunnable() { @Override public void run() {
			death.spigot().respawn();
			death.teleport(WorldUtil.SURVIVAL_SPAWN);
			death.setMaxHealth(20.0 + (4.0D * DPlayerManager.getInstance().getUpgradeLevel(death.getName(), "vitality")));
			death.setHealth(death.getMaxHealth());
		} }.runTaskLater(Main.getInstance(), 1L);

		DPlayerManager.getInstance().setDeaths(death, DPlayerManager.getInstance().getDeaths(death) + 1);
		ServerUtil.reloadContents(death);
	}

	private double getMoneyBack(Player player)
	{
		return 0.1 - (0.02 * DPlayerManager.getInstance().getUpgradeLevel(player.getName(), "luck"));
	}
}