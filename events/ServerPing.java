package mc.server.survival.events;

import mc.server.Logger;
import mc.server.survival.files.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.io.File;

public class ServerPing 
implements Listener
{
	@EventHandler
	public void onEvent(ServerListPingEvent event) 
	{
		event.setMaxPlayers(Configuration.SERVER_MAX_PLAYERS);
		
		event.setMotd(ChatColor.translateAlternateColorCodes('&', Configuration.SERVER_MOTD));
		
		try 
		{
			event.setServerIcon(Bukkit.loadServerIcon(new File(Configuration.SERVER_ICON_NAME)));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			Logger.asyncLog("&4&l(!) &cERROR: &7Oh no! That's looks like a problem with apply server's icon \n&cData: &fserver-icon's name: " + Configuration.SERVER_ICON_NAME + ", IP-Address: " + event.getAddress());
		}
		
		event.setServerIcon(Bukkit.getServerIcon());
	}
}