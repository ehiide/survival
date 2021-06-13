package mc.server;

import mc.server.survival.files.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Logger 
{
	public static void log(String log) 
	{
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "" + log));
	}
	
	public static void asyncLog(String log) 
	{
		@SuppressWarnings("unused")
		BukkitTask runnable = new BukkitRunnable()
		{
			@Override
			public void run()
			{
				log(log);
			}
		}.runTaskAsynchronously(Main.getInstance());
	}
}