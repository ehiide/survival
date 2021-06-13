package mc.server.survival.managers;

import mc.server.survival.utils.ColorUtil;
import org.bukkit.entity.Player;

public class ChatManager 
{
	public static void sendMessage(Player player, String message)
	{
		player.sendMessage(ColorUtil.formatHEX(message));
	}

	public static void sendMessages(Player player, String... message)
	{
		for (String line : message)
			player.sendMessage(ColorUtil.formatHEX(line));
	}
}