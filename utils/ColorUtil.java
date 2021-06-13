package mc.server.survival.utils;

import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtil 
{
	private static final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
	
	public static String formatHEX(String message)
	{
		Matcher matcher = pattern.matcher(message);
		while (matcher.find())
		{
			String color = message.substring(matcher.start(), matcher.end());
			message = message.replace(color, net.md_5.bungee.api.ChatColor.of(color) + "");
			matcher = pattern.matcher(message);
		}

		return ChatColor.translateAlternateColorCodes('&', message);
	}
}