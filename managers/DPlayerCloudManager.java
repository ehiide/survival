package mc.server.survival.managers;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public class DPlayerCloudManager 
{
	public static HashMap<Player, Player> tpa_queue = new HashMap<Player, Player>();
	public static HashMap<Player, Player> slub_queue = new HashMap<Player, Player>();
	public static HashMap<Player, Player> gang_queue = new HashMap<Player, Player>();

	public static HashMap<Player, Integer> animated_scoreboard_frame = new HashMap<Player, Integer>();
	public static HashMap<Player, BukkitTask> animated_scoreboard = new HashMap<Player, BukkitTask>();
	
	public static Player getTPA(Player player)
	{
		return tpa_queue.get(player);
	}
	
	public static void setTPA(Player player, Player address)
	{
		tpa_queue.put(player, address);
	}
	
	public static Player getMarry(Player player)
	{
		return tpa_queue.get(player);
	}
	
	public static void setMarry(Player player, Player address)
	{
		tpa_queue.put(player, address);
	}

	public static Player getGang(Player player)
	{
		return gang_queue.get(player);
	}

	public static void setGang(Player player, Player address)
	{
		gang_queue.put(player, address);
	}

	public static int getFrame(Player player) { return animated_scoreboard_frame.get(player); }

	public static void setFrame(Player player, int frame)
	{
		animated_scoreboard_frame.put(player, frame);
	}

	public static boolean checkFrame(Player player) { return animated_scoreboard_frame.get(player) == null; }

	public static BukkitTask getScoreboard(Player player) { return animated_scoreboard.get(player); }

	public static void setScoreboard(Player player, BukkitTask task)
	{
		animated_scoreboard.put(player, task);
	}

	public static boolean checkScoreboard(Player player) { return animated_scoreboard.get(player) == null; }
}