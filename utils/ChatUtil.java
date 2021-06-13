package mc.server.survival.utils;

import mc.server.survival.files.Configuration;
import mc.server.survival.files.Main;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.managers.DPlayerManager;
import mc.server.survival.managers.GangManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class ChatUtil 
{
	public static String applyCorrection(String WIADOMOSC) 
	{
		String var1 = WIADOMOSC;
		
		/*
		 * Emotki.
		 */
		
		String[] var2 = {"D:", ":(", ":<", ":{", ":[", ":sad:"};
		
		for (String var3 : var2)
			var1 = var1.replace(var3, ustawEmotke("&6☹"));
		
		String[] var4 = {":D", ":)", ":>", ":}", ":]", ":happy:", ":smile:"};
		
		for (String var5 : var4)
			var1 = var1.replace(var5, ustawLosowaEmotke("smile"));
		
		String[] var6 = {"#>", "<3", "<#", ":heart:"};
		
		for (String var7 : var6)
			var1 = var1.replace(var7, ustawLosowaEmotke("heart"));
		
		var1 = var1.replace(":star:", ustawEmotke("&e★"));
		var1 = var1.replace(":rounded_heart:", ustawEmotke("&d❥"));
		var1 = var1.replace(":time:", ustawEmotke("&e⌛"));
		var1 = var1.replace(":lighting:", ustawEmotke("&b↯"));
		var1 = var1.replace(":flower:", ustawEmotke("&a❀"));
		var1 = var1.replace(":crown:", ustawEmotke("&e♔"));
		var1 = var1.replace(":cloud:", ustawEmotke("&f☁"));
		var1 = var1.replace(":sun:", ustawEmotke("&e☀"));
		var1 = var1.replace(":moon:", ustawEmotke("&7☾"));
		var1 = var1.replace(":money:", ustawEmotke("&e⛃"));
		var1 = var1.replace(":meh:", ustawEmotke("&e¯\\_(ツ)_/¯"));
		var1 = var1.replace(":love:", ustawEmotke("&d(｡♥v♥｡)"));
		var1 = var1.replace(":panda:", ustawEmotke("&7ʕ&f•&0ᴥ&f•&7ʔ"));
		
		/*
		 * Kropka na koncu wiadomosci wraz z duza litera na poczatku zdania.
		 */
		
		var1 = var1.substring(0, 1).toUpperCase() + var1.substring(1);
		
		String var3 = var1.substring(var1.length() - 1);
			
		if (!var3.equalsIgnoreCase(".") && !var3.equalsIgnoreCase("!") && !var3.equalsIgnoreCase("?") && !var3.equalsIgnoreCase(";") && !var3.equalsIgnoreCase(":"))
			var1 = var1 + ".";

		return kompilujWiadomosc(ColorUtil.formatHEX(var1));
	}
	
	private static String kompilujWiadomosc(String WIADOMOSC) 
	{
		String var1 = WIADOMOSC;
		
		/*
		 * Adaptacja wielkiej litery na poczatku zdania.
		 */
		
		var1 = var1.substring(0, 1).toUpperCase() + var1.substring(1);
		
		/*
		 * Anti Capslock.
		 */
		
		var1 = var1.charAt(0) + var1.substring(1).toLowerCase();
		
		/*
		 * Korekta dla wyjatkowych slow.
		 */
		
		var1 = var1.replace("><", "x");
		
		var1 = var1.replace("Gg", "GG");
		var1 = var1.replace("gg", "GG");
		
		var1 = var1.replace("xdddd", "XDDDD");
		var1 = var1.replace("xddd", "XDDD");
		var1 = var1.replace("xdd", "XDD");
		var1 = var1.replace("xd", "XD");
		var1 = var1.replace("Xdddd", "XDDDD");
		var1 = var1.replace("Xddd", "XDDD");
		var1 = var1.replace("Xdd", "XDD");
		var1 = var1.replace("Xd", "XD");
		
		var1 = var1.replace("Lol", "LOL");
		var1 = var1.replace("lol", "LOL");
		
		return var1;
	}

	private static String ustawEmotke(String var1) 
	{
		return ChatColor.translateAlternateColorCodes('&', var1 + "#8c8c8c");
	}
	
	private static String ustawLosowaEmotke(String var1) 
	{
		if (var1.equalsIgnoreCase("heart"))
		{
			Random var2 = new Random();
			int var3 = var2.nextInt(3);
			
			if (var3 == 1)
				return ustawEmotke("#ff03e2❤");
			else if (var3 == 2)
				return ustawEmotke("#ff03e2❣");
			else
				return ustawEmotke("#ff03e2♥");
		}
		else if (var1.equalsIgnoreCase("smile"))
		{
			Random var2 = new Random();
			int var3 = var2.nextInt(3);
			
			if (var3 == 1)
				return ustawEmotke("&6ツ");
			else if (var3 == 2)
				return ustawEmotke("&6☻");
			else
				return ustawEmotke("&6☺");
		}
		else
			return "";
	}

	public static String addDruggedEmotes(Player player, String message)
	{
		if (DPlayerManager.getInstance().getSerotonine(player.getName()) <= -60)
			if (MathUtil.chanceOf(Math.abs(DPlayerManager.getInstance().getSerotonine(player.getName()))))
			{
				if (MathUtil.chanceOf(DPlayerManager.getInstance().getSerotonine(player.getName())))
					message = message + " " + ustawLosowaEmotke("heart");
				if (MathUtil.chanceOf(5))
					message = "Idz sobie";
				else if (MathUtil.chanceOf(5))
					message = "Zycie jest do dupy...";
				else if (MathUtil.chanceOf(5))
					message = "Boze to nie ma sensu";
				else if (MathUtil.chanceOf(5))
					message = "Myslicie ze jak sie zabije to sie zrespie szczesliwsza?";
				else if (MathUtil.chanceOf(5))
					message = "Kys";
				else if (MathUtil.chanceOf(5))
					message = "Spierdalaj wkurwiasz mnie tylko...";
			}

		if (DPlayerManager.getInstance().getSerotonine(player.getName()) >= 60)
		{
			if (MathUtil.chanceOf(DPlayerManager.getInstance().getSerotonine(player.getName())))
				message = message + " " + ustawLosowaEmotke("heart");
			if (MathUtil.chanceOf(5))
				message = "Dziekuje ze jestescie" + " " + ustawLosowaEmotke("heart");
			else if (MathUtil.chanceOf(5))
				message = "Pamietajcie ze was kocham" + " " + ustawLosowaEmotke("heart");
			else if (MathUtil.chanceOf(5))
				message = "Zawsze bedziecie w moim sercu!" + " " + ustawLosowaEmotke("heart");
			else if (MathUtil.chanceOf(5))
				message = "az mam zachcianke kogos wysziftowac";
			else if (MathUtil.chanceOf(5))
				message = "Boze jak ja was uwielbiam";
			else if (MathUtil.chanceOf(5))
				message = "Kc";
			else if (MathUtil.chanceOf(5))
				message = "Dziekuje";
			else if (MathUtil.chanceOf(5))
				message = "Chodz dam ci buzi";
			else if (MathUtil.chanceOf(5))
				message = "Ale super jest";
			else if (MathUtil.chanceOf(5))
				message = "Kocham was mocniej niz skid dzieci";

			if (player.getName().equalsIgnoreCase("ProseczeqPL") && Bukkit.getPlayer("milusia515") != null && Objects.requireNonNull(Bukkit.getPlayer("milusia515")).isOnline())
			{
				if (MathUtil.chanceOf(5))
					message = "milusia kocham cie";
				if (MathUtil.chanceOf(5))
					message = "milusia kc";
				if (MathUtil.chanceOf(5))
					message = "milusia kc kc kc kc";
				if (MathUtil.chanceOf(5))
					message = "schizis bedzie naszym swiadkiem na slubie milena";
				if (MathUtil.chanceOf(5))
					message = "Snapshoooot!";
			}

			if (player.getName().equalsIgnoreCase("milusia515") && Bukkit.getPlayer("ProseczeqPL") != null && Objects.requireNonNull(Bukkit.getPlayer("ProseczeqPL")).isOnline())
			{
				if (MathUtil.chanceOf(5))
					message = "prosek kc";
				if (MathUtil.chanceOf(5))
					message = "boze hsdhshshs jak ja was kocham";
				if (MathUtil.chanceOf(5))
					message = "prosek ja serio to czuje";
				if (MathUtil.chanceOf(5))
					message = "no japieeerdfole prosek wyruchaj mnie";
				if (MathUtil.chanceOf(5))
					message = "kocham was jak skid dzieci!";
			}
		}

		return message;
	}

	public static void scheduleDruggedMessages(Player player, int delay)
	{
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				ArrayList<String> allPlayers = new ArrayList<>();
				Player onlinePlayer = null;

				for (Player onlinePlayers : Bukkit.getOnlinePlayers())
                    if (!onlinePlayers.getName().equalsIgnoreCase(player.getName()))
                    {
                        allPlayers.add(onlinePlayers.getName());
                        int randomSelect = new Random().nextInt(allPlayers.size());
                        onlinePlayer = Bukkit.getPlayer(allPlayers.get(randomSelect));
                    }

				if (onlinePlayer == null)
					return;

				if (MathUtil.chanceOf(50))
				{
					if (MathUtil.chanceOf(10))
						ChatManager.sendMessage(player, ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.getGangInChat(GangManager.getInstance().getGang(onlinePlayer)) + ChatUtil.returnMarryPrefix(onlinePlayer) + "&r" + ChatUtil.returnPlayerColor(onlinePlayer) + Objects.requireNonNull(onlinePlayer.getPlayer()).getName() + "#8c8c8c " +
								applyCorrection("co tam " + player.getName())));
					else if (MathUtil.chanceOf(10))
						ChatManager.sendMessage(player, ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.getGangInChat(GangManager.getInstance().getGang(onlinePlayer)) + ChatUtil.returnMarryPrefix(onlinePlayer) + "&r" + ChatUtil.returnPlayerColor(onlinePlayer) + Objects.requireNonNull(onlinePlayer.getPlayer()).getName() + "#8c8c8c " +
								applyCorrection("ej " + player.getName() + " jestes super <3")));
					else if (MathUtil.chanceOf(10))
						ChatManager.sendMessage(player, ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.getGangInChat(GangManager.getInstance().getGang(onlinePlayer)) + ChatUtil.returnMarryPrefix(onlinePlayer) + "&r" + ChatUtil.returnPlayerColor(onlinePlayer) + Objects.requireNonNull(onlinePlayer.getPlayer()).getName() + "#8c8c8c " +
								applyCorrection("chodz do mnie skarbie")));
					else if (MathUtil.chanceOf(10))
						ChatManager.sendMessage(player, ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.getGangInChat(GangManager.getInstance().getGang(onlinePlayer)) + ChatUtil.returnMarryPrefix(onlinePlayer) + "&r" + ChatUtil.returnPlayerColor(onlinePlayer) + Objects.requireNonNull(onlinePlayer.getPlayer()).getName() + "#8c8c8c " +
								applyCorrection("swiat z toba jest taki piekny")));
					else if (MathUtil.chanceOf(10))
						ChatManager.sendMessage(player, ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.getGangInChat(GangManager.getInstance().getGang(onlinePlayer)) + ChatUtil.returnMarryPrefix(onlinePlayer) + "&r" + ChatUtil.returnPlayerColor(onlinePlayer) + Objects.requireNonNull(onlinePlayer.getPlayer()).getName() + "#8c8c8c " +
								applyCorrection("swiat z toba jest taki piekny jak " + player.getName())));
					else if (MathUtil.chanceOf(10))
						ChatManager.sendMessage(player, ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.getGangInChat(GangManager.getInstance().getGang(onlinePlayer)) + ChatUtil.returnMarryPrefix(onlinePlayer) + "&r" + ChatUtil.returnPlayerColor(onlinePlayer) + Objects.requireNonNull(onlinePlayer.getPlayer()).getName() + "#8c8c8c " +
								applyCorrection(player.getName() + " tpa")));
				}
				else
				{
					if (MathUtil.chanceOf(10))
					{
						ChatManager.sendMessage(player, "&c&l» &f&oWiadomosc od " + ChatUtil.returnPlayerColor(onlinePlayer) + "&o" + onlinePlayer.getName() + ": &r&7" + ChatUtil.applyCorrection("tepnij sie na chwile kotek"));
						VisualUtil.showDelayedTitle(player, "&7od: " + onlinePlayer.getName(), "#faff26✉", 0, 20, 20);
					}
					else if (MathUtil.chanceOf(10))
					{
						ChatManager.sendMessage(player, "&c&l» &f&oWiadomosc od " + ChatUtil.returnPlayerColor(onlinePlayer) + "&o" + onlinePlayer.getName() + ": &r&7" + ChatUtil.applyCorrection("pieknie dzis wygladasz"));
						VisualUtil.showDelayedTitle(player, "&7od: " + onlinePlayer.getName(), "#faff26✉", 0, 20, 20);
					}
					else if (MathUtil.chanceOf(10))
					{
						ChatManager.sendMessage(player, "&c&l» &f&oWiadomosc od " + ChatUtil.returnPlayerColor(onlinePlayer) + "&o" + onlinePlayer.getName() + ": &r&7" + ChatUtil.applyCorrection("ciesze sie ze moj idol to ty XD"));
						VisualUtil.showDelayedTitle(player, "&7od: " + onlinePlayer.getName(), "#faff26✉", 0, 20, 20);
					}
					else if (MathUtil.chanceOf(10))
					{
						ChatManager.sendMessage(player, "&c&l» &f&oWiadomosc od " + ChatUtil.returnPlayerColor(onlinePlayer) + "&o" + onlinePlayer.getName() + ": &r&7" + ChatUtil.applyCorrection("uwielbiam cie"));
						VisualUtil.showDelayedTitle(player, "&7od: " + onlinePlayer.getName(), "#faff26✉", 0, 20, 20);
					}
					else if (MathUtil.chanceOf(10))
					{
						ChatManager.sendMessage(player, "&c&l» &f&oWiadomosc od " + ChatUtil.returnPlayerColor(onlinePlayer) + "&o" + onlinePlayer.getName() + ": &r&7" + ChatUtil.applyCorrection("uwielbiam cie haha"));
						VisualUtil.showDelayedTitle(player, "&7od: " + onlinePlayer.getName(), "#faff26✉", 0, 20, 20);
					}
					else if (MathUtil.chanceOf(10))
					{
						ChatManager.sendMessage(player, "&c&l» &f&oWiadomosc od " + ChatUtil.returnPlayerColor(onlinePlayer) + "&o" + onlinePlayer.getName() + ": &r&7" + ChatUtil.applyCorrection("nastepym razem bierzemy razem <3"));
						VisualUtil.showDelayedTitle(player, "&7od: " + onlinePlayer.getName(), "#faff26✉", 0, 20, 20);
					}
				}
			}
		}.runTaskLater(Main.getInstance(), 20L * new Random().nextInt(delay));
	}

	public static String getPlaceholder(Player player, String placeholder)
	{
		placeholder = placeholder.replace("%ping%", "#8c8c8c" + ServerUtil.getPing(player) + " ms" + " #666666&o(%ping%)#8c8c8c");
		placeholder = placeholder.replace("%monety%", "#8c8c8c" + DPlayerManager.getInstance().getMoney(player) + " monet" + " #666666&o(%monety%)#8c8c8c");
		placeholder = placeholder.replace("%smierci%", "#8c8c8c" + DPlayerManager.getInstance().getDeaths(player) + " smierci" + " #666666&o(%smierci%)#8c8c8c");
		placeholder = placeholder.replace("%zabojstwa%", "#8c8c8c" + DPlayerManager.getInstance().getKills(player) + " zaboojstw" + " #666666&o(%zaboojstwa%)#8c8c8c");
		placeholder = placeholder.replace("%xyz%", "#8c8c8cx: " + player.getLocation().getBlockX() + "#8c8c8c, y: " + player.getLocation().getBlockY() + "#8c8c8c, z: #8c8c8c" + player.getLocation().getBlockZ() + " #666666&o(%xyz%)#8c8c8c");
		placeholder = placeholder.replace("%xy%", "#8c8c8cx: " + player.getLocation().getBlockX() + "#8c8c8c, y: " + player.getLocation().getBlockY() + " #666666&o(%xy%)#8c8c8c");
		placeholder = placeholder.replace("%yz%", "#8c8c8cy: " + player.getLocation().getBlockY() + "#8c8c8c, z: " + player.getLocation().getBlockZ() + " #666666&o(%yz%)#8c8c8c");
		placeholder = placeholder.replace("%xz%", "#8c8c8cx: " + player.getLocation().getBlockX() + "#8c8c8c, z: " + player.getLocation().getBlockZ() + " #666666&o(%xz%)#8c8c8c");
		placeholder = placeholder.replace("%x%", "#8c8c8cx: " + player.getLocation().getBlockX() + " #666666&o(%x%)#8c8c8c");
		placeholder = placeholder.replace("%y%", "#8c8c8cy: " + player.getLocation().getBlockY() + " #666666&o(%y%)#8c8c8c");
		placeholder = placeholder.replace("%z%", "#8c8c8cz: " + player.getLocation().getBlockZ() + " #666666&o(%z%)#8c8c8c");

		if (player.getItemInHand().getType() != Material.AIR)
		{
			placeholder = placeholder.replace("%item%", "#8c8c8c" + player.getItemInHand().getType() + " x" + player.getItemInHand().getAmount() + " #666666&o(%item%)#8c8c8c");
			placeholder = placeholder.replace("%itemmeta%", "#8c8c8c" + player.getItemInHand().getType() + " x" + player.getItemInHand().getAmount() + ", itemEnch: " + Objects.requireNonNull(player.getItemInHand().getItemMeta()).getEnchants() + ", customName: " + player.getItemInHand().getItemMeta().getDisplayName() + " #666666&o(%itemmeta%)#8c8c8c");
		}

		String message = placeholder;

		if (player.getWorld().getName().equalsIgnoreCase("survival"))
			message = placeholder.replace("%swiat%", "#8c8c8cswiat survivalowy" + " #666666&o(%swiat%)#8c8c8c");
		else if (player.getWorld().getName().equalsIgnoreCase("surowce"))
			message = placeholder.replace("%swiat%", "#8c8c8cswiat surowcowy" + " #666666&o(%swiat%)#8c8c8c");
		else if (player.getWorld().getName().equalsIgnoreCase("survival_nether"))
			message = placeholder.replace("%swiat%", "#8c8c8cswiat netheru" + " #666666&o(%swiat%)#8c8c8c");
		else if (player.getWorld().getName().equalsIgnoreCase("survival_the_end"))
			message = placeholder.replace("%swiat%", "#8c8c8cswiat endu" + " #666666&o(%swiat%)#8c8c8c");

		return message;
	}
	
	public static String returnMarryPrefix(Player player)
	{
		if (DPlayerManager.getInstance().getMarry(player) != null)
			return "&c❤ ";
		
		return "";
	}

	public static String returnPlayerColor(Player player)
	{
		if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("red"))
			return "#fc7474";
		else if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("blue"))
			return "#3075ff";
		else if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("green"))
			return "#02d645";
		else if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("yellow"))
			return "#fcff33";
		else if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("white"))
			return "#ffffff";
		else if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("gray"))
			return "#242424";
		else if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("orange"))
			return "#ffb338";
		else if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("pink"))
			return "#ff9ee7";

		return "";
	}

	public static String returnPlayerColor(String player)
	{
		if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("red"))
			return "#fc7474";
		else if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("blue"))
			return "#3075ff";
		else if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("green"))
			return "#02d645";
		else if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("yellow"))
			return "#fcff33";
		else if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("white"))
			return "#ffffff";
		else if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("gray"))
			return "#242424";
		else if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("orange"))
			return "#ffb338";
		else if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("pink"))
			return "#ff9ee7";

		return "";
	}

	public static String returnCustomGangPrefix(Player player, String color, String prefixes, String star)
	{
		if (DPlayerManager.getInstance().getGang(player) == null)
			return "";
		if (prefixes.equalsIgnoreCase("normal"))
			return "&7[" + star + color + DPlayerManager.getInstance().getGang(player) + "&7] ";
		if (prefixes.equalsIgnoreCase("rounded"))
			return "&7(" + star + color + DPlayerManager.getInstance().getGang(player) + "&7) ";
		if (prefixes.equalsIgnoreCase("arrows"))
			return "&7<" + star + color + DPlayerManager.getInstance().getGang(player) + "&7> ";

		return "";
	}

	public static String getValidGangColor(String name)
	{
		if (GangManager.getInstance().getColor(name).equalsIgnoreCase("red"))
			return "&c";
		else if (GangManager.getInstance().getColor(name).equalsIgnoreCase("blue"))
			return "&b";
		else if (GangManager.getInstance().getColor(name).equalsIgnoreCase("green"))
			return "&a";
		else if (GangManager.getInstance().getColor(name).equalsIgnoreCase("yellow"))
			return "&e";
		else if (GangManager.getInstance().getColor(name).equalsIgnoreCase("white"))
			return "&f";
		else if (GangManager.getInstance().getColor(name).equalsIgnoreCase("gray"))
			return "&7";
		else if (GangManager.getInstance().getColor(name).equalsIgnoreCase("orange"))
			return "&6";
		else if (GangManager.getInstance().getColor(name).equalsIgnoreCase("pink"))
			return "&d";

		return "";
	}

	public static String returnGangStar(String name)
	{
		if (GangManager.getInstance().getStar(name))
			return "#ffc936★ ";

		return "";
	}

	public static String getGangInChat(String name)
	{
		if (name == null) return "";

		if (GangManager.getInstance().getPrefixes(name).equalsIgnoreCase("normal"))
			return "&7[" + returnGangStar(name) + getValidGangColor(name) + name.toUpperCase() + "&7] ";
		else if (GangManager.getInstance().getPrefixes(name).equalsIgnoreCase("rounded"))
			return "&7(" + returnGangStar(name) + getValidGangColor(name) + name.toUpperCase() + "&7) ";
		else if (GangManager.getInstance().getPrefixes(name).equalsIgnoreCase("arrows"))
			return "&7<" + returnGangStar(name) +  getValidGangColor(name) + name.toUpperCase() + "&7> ";

		return "";
	}

	public static String returnGangPlayerChatMessage(Player player, String message)
	{
		String isLider = "";

		if (GangManager.getInstance().getLider(GangManager.getInstance().getGang(player)).equalsIgnoreCase(player.getName()))
			isLider = " #ffc936[LIDER]";

		message = kompilujWiadomosc(message.substring(1, message.length()));
		message = ColorUtil.formatHEX("#80ff1f[CZAT GANGU] " + "&d" + ChatUtil.returnMarryPrefix(player) +  "&r" + ChatUtil.returnPlayerColor(player) + Objects.requireNonNull(player.getPlayer()).getName() + isLider + "#8c8c8c " + message);

		return message;
	}

	public static String returnGangGangChatMessage(String message)
	{
		return ColorUtil.formatHEX("#80ff1f[CZAT GANGU] " + "#8c8c8c" + message);
	}

	public static void sendGangChatMessage(Player player, String message)
	{
		if (GangManager.getInstance().getGang(player) == null)
		{
			ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Aby pisac na czacie gangu musisz najpierw do niego dolaczyc!");
			VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
			VisualUtil.showPlayerParticle(player, Particle.FLAME);
			SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
			return;
		}

		if (!GangManager.getInstance().getChat(GangManager.getInstance().getGang(player)))
		{
			ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Twoj gang nie posiada zakupionego czatu, aby go odblokowac odwiedz zakladke ulepszen gangu lub wpisz komende #ffc936/gang ulepsz#fc7474!");
			VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
			VisualUtil.showPlayerParticle(player, Particle.FLAME);
			SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
			return;
		}

		if (message.length() < 2)
		{
			ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cWprowadz swoja wiadomosc, ktora chcesz wyslac na czat gangu!");
			return;
		}

		for (String member : GangManager.getInstance().getPlayerMembers(GangManager.getInstance().getGang(player)))
			if (Bukkit.getPlayer(member) != null && Objects.requireNonNull(Bukkit.getPlayer(member)).isOnline())
			{
				Player dplayer = Bukkit.getPlayer(member);
				assert dplayer != null;
				ChatManager.sendMessage(dplayer, returnGangPlayerChatMessage(player, message));
			}
	}

	public static void sendGangAllChatMessage(String name, String message)
	{
		for (String member : GangManager.getInstance().getPlayerMembers(name))
			if (Bukkit.getPlayer(member) != null && Objects.requireNonNull(Bukkit.getPlayer(member)).isOnline())
			{
				Player dplayer = Bukkit.getPlayer(member);
				assert dplayer != null;
				ChatManager.sendMessage(dplayer, returnGangGangChatMessage(message));
			}
	}

	public static String isGangColor(String name, String color)
	{
		if (getValidGangColor(name).equalsIgnoreCase(color))
			return "#80ff1fTAK";

		return "#fc7474NIE";
	}

	public static String isGangPrefix(String name, String prefix)
	{
		if (GangManager.getInstance().getPrefixes(name).equalsIgnoreCase(prefix))
			return "#80ff1fTAK";

		return "#fc7474NIE";
	}

	public static String isGangStar(String name)
	{
		if (GangManager.getInstance().getStar(name))
			return "#80ff1fTAK";

		return "#fc7474NIE";
	}

	public static String isGangChat(String name)
	{
		if (GangManager.getInstance().getChat(name))
			return "#80ff1fTAK";

		return "#fc7474NIE";
	}

	public static String isGangFriendlyFire(String name)
	{
		if (GangManager.getInstance().getFriendlyFire(name))
			return "#80ff1fTAK";

		return "#fc7474NIE";
	}

	public static String isPlayerColor(Player player, String color)
	{
		if (returnPlayerColor(player).equalsIgnoreCase(color))
			return "#80ff1fTAK";

		return "#fc7474NIE";
	}

	public static String isPlayerPremium(Player player)
	{
		if (ServerUtil.getPremiumState(player.getName()))
			return "#80ff1fTAK";

		return "#fc7474NIE";
	}

	public static String isPlayerSession(Player player)
	{
		if (ServerUtil.getPremiumState(player.getName()))
			return "&7Sesja aktywna";

		return "&7Sesja nieaktywna";
	}

	public static String getColoredRank(Player player)
	{
		if (DPlayerManager.getInstance().getRank(player).equalsIgnoreCase("administrator"))
			return "#fc7474Administrator";

		return "&7Gracz";
	}

	public static String returnColoredPlayerColor(String player)
	{
		if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("red"))
			return "#fc7474Czerwony";
		else if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("blue"))
			return "#3075ffNiebieski";
		else if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("green"))
			return "#02d645Zielony";
		else if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("yellow"))
			return "#fcff33Zolty";
		else if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("white"))
			return "#ffffffBialy";
		else if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("gray"))
			return "#242424Szary";
		else if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("orange"))
			return "#ffb338Pomaranczowy";
		else if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("pink"))
			return "#feb0ffRozowy";

		return "";
	}

	public static String getLastMuteColored(String player)
	{
		if (DPlayerManager.getInstance().getMute(player).equalsIgnoreCase("2000/01/01 12:00:00"))
			return "Brak";

		return DPlayerManager.getInstance().getMute(player);
	}

	public static String getColoredValidGangColor(String name)
	{
		if (GangManager.getInstance().getColor(name).equalsIgnoreCase("red"))
			return "&cCzerwony";
		else if (GangManager.getInstance().getColor(name).equalsIgnoreCase("blue"))
			return "&bNiebieski";
		else if (GangManager.getInstance().getColor(name).equalsIgnoreCase("green"))
			return "&aZielony";
		else if (GangManager.getInstance().getColor(name).equalsIgnoreCase("yellow"))
			return "&eZolty";
		else if (GangManager.getInstance().getColor(name).equalsIgnoreCase("white"))
			return "&fBialy";
		else if (GangManager.getInstance().getColor(name).equalsIgnoreCase("gray"))
			return "&7Szary";
		else if (GangManager.getInstance().getColor(name).equalsIgnoreCase("orange"))
			return "&6Pomaranczowy";
		else if (GangManager.getInstance().getColor(name).equalsIgnoreCase("pink"))
			return "&dRozowy";

		return "";
	}

	public static String getColoredGangPrefixes(String name)
	{
		if (GangManager.getInstance().getPrefixes(name).equalsIgnoreCase("normal"))
			return "Kwadratowe";
		else if (GangManager.getInstance().getPrefixes(name).equalsIgnoreCase("rounded"))
			return "Zaokraglone";
		else if (GangManager.getInstance().getPrefixes(name).equalsIgnoreCase("arrows"))
			return "Strzalkowe";

		return "A chuj wie!";
	}

	public static String randomString(ArrayList<String> strings)
	{
		return strings.get(new Random().nextInt(strings.size()));
	}

	public static String centerString(String string, int hexes, int bukkitcodes)
	{
		int space = (50 - (string.length() - (hexes * 7) - (bukkitcodes * 2))) / 2;
		StringBuilder stringBuilder1 = new StringBuilder(string);

		for (int x = 0; x < space; x++)
			stringBuilder1.insert(0, " ");

		string = stringBuilder1.toString();
		return string;
	}
}