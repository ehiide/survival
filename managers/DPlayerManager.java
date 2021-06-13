package mc.server.survival.managers;

import mc.server.survival.utils.WorldUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DPlayerManager 
{
	private DPlayerManager() {}
	
	static DPlayerManager instance = new DPlayerManager();
	
	FileConfiguration configuration = FileManager.getInstance().configuration();
	
	public static DPlayerManager getInstance()
	{
		return instance;
	}
	
	public static String getDPlayer(String name) { return name.toLowerCase(); }
	
	public boolean checkExist(Player player)
	{
		return configuration.get(getDPlayer(player.getName())) != null;
	}

	public boolean checkExist(String name)
	{
		return configuration.get(getDPlayer(name)) != null;
	}
	
	public void loadDplayer(Player player)
	{
		if (!checkExist(player))
		{
			if (configuration.get(getDPlayer(player.getName()) + ".data.uuid") == null)
				configuration.set(getDPlayer(player.getName()) + ".data.uuid", player.getUniqueId().toString());

			if (configuration.get(getDPlayer(player.getName()) + ".data.logged") == null)
				configuration.set(getDPlayer(player.getName()) + ".data.logged", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now()));
			
			if (configuration.get(getDPlayer(player.getName()) + ".data.rank") == null)
				configuration.set(getDPlayer(player.getName()) + ".data.rank", "Gracz");

			if (configuration.get(getDPlayer(player.getName()) + ".data.gang") == null)
				configuration.set(getDPlayer(player.getName()) + ".data.gang", null);
			
			if (configuration.get(getDPlayer(player.getName()) + ".data.money") == null)
				configuration.set(getDPlayer(player.getName()) + ".data.money", 0);

			if (configuration.get(getDPlayer(player.getName()) + ".data.sp") == null)
				configuration.set(getDPlayer(player.getName()) + ".data.sp", 0);
			
			if (configuration.get(getDPlayer(player.getName()) + ".data.kills") == null)
				configuration.set(getDPlayer(player.getName()) + ".data.kills", 0);
			
			if (configuration.get(getDPlayer(player.getName()) + ".data.deaths") == null)
				configuration.set(getDPlayer(player.getName()) + ".data.deaths", 0);
			
			if (configuration.get(getDPlayer(player.getName()) + ".data.vanished") == null)
				configuration.set(getDPlayer(player.getName()) + ".data.vanished", false);
			
			if (configuration.get(getDPlayer(player.getName()) + ".data.married") == null)
				configuration.set(getDPlayer(player.getName()) + ".data.married", null);

			if (configuration.get(getDPlayer(player.getName()) + ".data.marrieddate") == null)
				configuration.set(getDPlayer(player.getName()) + ".data.marrieddate", null);

			if (configuration.get(getDPlayer(player.getName()) + ".data.marriedlevel") == null)
				configuration.set(getDPlayer(player.getName()) + ".data.marriedlevel", 0);

			if (configuration.get(getDPlayer(player.getName()) + ".data.chatcolor") == null)
				configuration.set(getDPlayer(player.getName()) + ".data.chatcolor", "red");

			if (configuration.get(getDPlayer(player.getName()) + ".data.mute") == null)
				configuration.set(getDPlayer(player.getName()) + ".data.mute", "2000/01/01 12:00:00");

			if (configuration.get(getDPlayer(player.getName()) + ".data.mutelength") == null)
				configuration.set(getDPlayer(player.getName()) + ".data.mutelength", 0);
			
			if (configuration.get(getDPlayer(player.getName()) + ".data.lastlocation.world") == null)
				configuration.set(getDPlayer(player.getName()) + ".data.lastlocation.world", WorldUtil.SURVIVAL_SPAWN.getWorld().getName());
				configuration.set(getDPlayer(player.getName()) + ".data.lastlocation.x", WorldUtil.SURVIVAL_SPAWN.getX());
				configuration.set(getDPlayer(player.getName()) + ".data.lastlocation.y", WorldUtil.SURVIVAL_SPAWN.getY());
				configuration.set(getDPlayer(player.getName()) + ".data.lastlocation.z", WorldUtil.SURVIVAL_SPAWN.getZ());
				configuration.set(getDPlayer(player.getName()) + ".data.lastlocation.yaw", WorldUtil.SURVIVAL_SPAWN.getYaw());
				configuration.set(getDPlayer(player.getName()) + ".data.lastlocation.pitch", WorldUtil.SURVIVAL_SPAWN.getPitch());
				
			if (configuration.get(getDPlayer(player.getName()) + ".data.home1.world") == null)
			{
				configuration.set(getDPlayer(player.getName()) + ".data.home1.world", "none");
				configuration.set(getDPlayer(player.getName()) + ".data.home1.x", 0D);
				configuration.set(getDPlayer(player.getName()) + ".data.home1.y", 0D);
				configuration.set(getDPlayer(player.getName()) + ".data.home1.z", 0D);
				configuration.set(getDPlayer(player.getName()) + ".data.home1.yaw", 0F);
				configuration.set(getDPlayer(player.getName()) + ".data.home1.pitch", 0F);
			}
			
			if (configuration.get(getDPlayer(player.getName()) + ".data.home2.world") == null)
			{
				configuration.set(getDPlayer(player.getName()) + ".data.home2.world", "none");
				configuration.set(getDPlayer(player.getName()) + ".data.home2.x", 0D);
				configuration.set(getDPlayer(player.getName()) + ".data.home2.y", 0D);
				configuration.set(getDPlayer(player.getName()) + ".data.home2.z", 0D);
				configuration.set(getDPlayer(player.getName()) + ".data.home2.yaw", 0F);
				configuration.set(getDPlayer(player.getName()) + ".data.home2.pitch", 0F);
			}
			
			if (configuration.get(getDPlayer(player.getName()) + ".data.schowek.items") == null)
				configuration.set(getDPlayer(player.getName()) + ".data.schowek.items", null);
			
			if (configuration.get(getDPlayer(player.getName()) + ".data.schowek.level") == null)
				configuration.set(getDPlayer(player.getName()) + ".data.schowek.level", 0);

			if (configuration.get(getDPlayer(player.getName()) + ".data.neurotransmitters.serotonine") == null)
				configuration.set(getDPlayer(player.getName()) + ".data.neurotransmitters.serotonine", 0);

			if (configuration.get(getDPlayer(player.getName()) + ".data.neurotransmitters.dopamine") == null)
				configuration.set(getDPlayer(player.getName()) + ".data.neurotransmitters.dopamine", 0);

			if (configuration.get(getDPlayer(player.getName()) + ".data.neurotransmitters.noradrenaline") == null)
				configuration.set(getDPlayer(player.getName()) + ".data.neurotransmitters.noradrenaline", 0);

			if (configuration.get(getDPlayer(player.getName()) + ".data.neurotransmitters.gaba") == null)
				configuration.set(getDPlayer(player.getName()) + ".data.neurotransmitters.gaba", 0);

			if (configuration.get(getDPlayer(player.getName()) + ".data.quests.q1") == null)
			{
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q1.status", false);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q1.current", 0);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q1.requied", 350);
			}

			if (configuration.get(getDPlayer(player.getName()) + ".data.quests.q2") == null)
			{
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q2.status", false);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q2.current", 0);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q2.requied", 500);
			}

			if (configuration.get(getDPlayer(player.getName()) + ".data.quests.q3") == null)
			{
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q3.status", false);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q3.current", 0);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q3.requied", 400);
			}

			if (configuration.get(getDPlayer(player.getName()) + ".data.quests.q4") == null)
			{
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q4.status", false);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q4.current", 0);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q4.requied", 4);
			}

			if (configuration.get(getDPlayer(player.getName()) + ".data.quests.q5") == null)
			{
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q5.status", false);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q5.current", 0);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q5.requied", 150);
			}

			if (configuration.get(getDPlayer(player.getName()) + ".data.quests.q6") == null)
			{
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q6.status", false);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q6.current", 0);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q6.requied", 10);
			}

			if (configuration.get(getDPlayer(player.getName()) + ".data.quests.q7") == null)
			{
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q7.status", false);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q7.current", 1);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q7.requied", 4);
			}

			if (configuration.get(getDPlayer(player.getName()) + ".data.quests.q8") == null)
			{
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q8.status", false);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q8.current", 0);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q8.requied", 1);
			}

			if (configuration.get(getDPlayer(player.getName()) + ".data.quests.q9") == null)
			{
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q9.status", false);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q9.current", 0);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q9.requied", 10);
			}

			if (configuration.get(getDPlayer(player.getName()) + ".data.quests.q10") == null)
			{
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q10.status", false);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q10.current", 0);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q10.requied", 1);
			}

			if (configuration.get(getDPlayer(player.getName()) + ".data.quests.q11") == null)
			{
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q11.status", false);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q11.current", 0);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q11.requied", 400);
			}

			if (configuration.get(getDPlayer(player.getName()) + ".data.quests.q12") == null)
			{
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q12.status", false);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q12.current", 0);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q12.requied", 1);
			}

			if (configuration.get(getDPlayer(player.getName()) + ".data.quests.q13") == null)
			{
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q13.status", false);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q13.current", 0);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q13.requied", 1);
			}

			if (configuration.get(getDPlayer(player.getName()) + ".data.quests.q14") == null)
			{
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q14.status", false);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q14.current", 0);
				configuration.set(getDPlayer(player.getName()) + ".data.quests.q14.requied", 15);
			}

			if (configuration.get(getDPlayer(player.getName()) + ".data.upgrades") == null)
			{
				configuration.set(getDPlayer(player.getName()) + ".data.upgrades.vitality", 0);
				configuration.set(getDPlayer(player.getName()) + ".data.upgrades.luck", 0);
				configuration.set(getDPlayer(player.getName()) + ".data.upgrades.loot", 0);
				configuration.set(getDPlayer(player.getName()) + ".data.upgrades.honorable", 0);
				configuration.set(getDPlayer(player.getName()) + ".data.upgrades.thiefy", 0);
			}

			addToPlayerData(getDPlayer(player.getName()));
		}
		
		FileManager.getInstance().save();
	}

	public void addToPlayerData(String player)
	{
		if (configuration.get("gracze.lista") == null)
			configuration.set("gracze.lista", new ArrayList<String>());

		ArrayList<String> players = (ArrayList<String>) configuration.get("gracze.lista");
		players.add(player);
		configuration.set("gracze.lista", players);
	}

	public ArrayList<String> getPlayerData()
	{
		return (ArrayList<String>) configuration.get("gracze.lista");
	}

	public String getUUID(Player player)
	{
		return (String) configuration.get(getDPlayer(player.getName()) + ".data.uuid");
	}

	public String getLogged(Player player)
	{
		return (String) configuration.get(getDPlayer(player.getName()) + ".data.logged");
	}
	
	public String getRank(Player player)
	{
		return (String) configuration.get(getDPlayer(player.getName()) + ".data.rank");
	}
	
	public void setRank(Player player, String rank)
	{
		configuration.set(getDPlayer(player.getName()) + ".data.rank", rank);
		FileManager.getInstance().save();
	}

	public String getGang(Player player)
	{
		return (String) configuration.get(getDPlayer(player.getName()) + ".data.gang");
	}

	public void setGang(Player player, String name)
	{
		configuration.set(getDPlayer(player.getName()) + ".data.gang", name);
		FileManager.getInstance().save();
	}
	
	public int getMoney(Player player)
	{
		return (int) configuration.get(getDPlayer(player.getName()) + ".data.money");
	}
	
	public void setMoney(Player player, int money)
	{
		configuration.set(getDPlayer(player.getName()) + ".data.money", money);
		FileManager.getInstance().save();
	}

	public int getSP(Player player)
	{
		return (int) configuration.get(getDPlayer(player.getName()) + ".data.sp");
	}

	public void setSP(Player player, int money)
	{
		configuration.set(getDPlayer(player.getName()) + ".data.sp", money);
		FileManager.getInstance().save();
	}
	
	public int getKills(Player player)
	{
		return (int) configuration.get(getDPlayer(player.getName()) + ".data.kills");
	}
	
	public void setKills(Player player, int kills)
	{
		configuration.set(getDPlayer(player.getName()) + ".data.kills", kills);
		FileManager.getInstance().save();
	}
	
	public int getDeaths(Player player)
	{
		return (int) configuration.get(getDPlayer(player.getName()) + ".data.deaths");
	}
	
	public void setDeaths(Player player, int deaths)
	{
		configuration.set(getDPlayer(player.getName()) + ".data.deaths", deaths);
		FileManager.getInstance().save();
	}
	
	public boolean getVanished(Player player)
	{
		return (boolean) configuration.get(getDPlayer(player.getName()) + ".data.vanished");
	}
	
	public void setVanished(Player player, boolean isVanished)
	{
		configuration.set(getDPlayer(player.getName()) + ".data.vanished", isVanished);
		FileManager.getInstance().save();
	}
	
	public String getMarry(Player player)
	{
		return (String) configuration.get(getDPlayer(player.getName()) + ".data.married");
	}

	public String getMarry(String player)
	{
		return (String) configuration.get(getDPlayer(player) + ".data.married");
	}
	
	public void setMarry(Player player, String lover)
	{
		configuration.set(getDPlayer(player.getName()) + ".data.married", lover);
		FileManager.getInstance().save();
	}

	public String getMarryDate(Player player) { return (String) configuration.get(getDPlayer(player.getName()) + ".data.marrieddate"); }

	public String getMarryDate(String player) { return (String) configuration.get(getDPlayer(player) + ".data.marrieddate"); }

	public void setMarryDate(Player player, String date)
	{
		configuration.set(getDPlayer(player.getName()) + ".data.marrieddate", date);
		FileManager.getInstance().save();
	}

	public int getMarryLevel(Player player) { return (int) configuration.get(getDPlayer(player.getName()) + ".data.marriedlevel"); }

	public int getMarryLevel(String player) { return (int) configuration.get(getDPlayer(player) + ".data.marriedlevel"); }

	public void setMarryLevel(Player player, int level)
	{
		configuration.set(getDPlayer(player.getName()) + ".data.marriedlevel", level);
		FileManager.getInstance().save();
	}
	
	public Location getLastLocation(Player player)
	{
		String world = (String) configuration.get(getDPlayer(player.getName()) + ".data.lastlocation.world");
		double x = (double) configuration.get(getDPlayer(player.getName()) + ".data.lastlocation.x");
		double y = (double) configuration.get(getDPlayer(player.getName()) + ".data.lastlocation.y");
		double z = (double) configuration.get(getDPlayer(player.getName()) + ".data.lastlocation.z");
		float yaw = (float) Float.valueOf(String.valueOf(configuration.get(getDPlayer(player.getName()) + ".data.lastlocation.yaw")));
		float pitch = (float) Float.valueOf(String.valueOf(configuration.get(getDPlayer(player.getName()) + ".data.lastlocation.pitch")));
		
		return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
	}
	
	public void setLastLocation(Player player, Location location)
	{
		configuration.set(getDPlayer(player.getName()) + ".data.lastlocation.world", location.getWorld().getName());
		configuration.set(getDPlayer(player.getName()) + ".data.lastlocation.x", location.getX());
		configuration.set(getDPlayer(player.getName()) + ".data.lastlocation.y", location.getY());
		configuration.set(getDPlayer(player.getName()) + ".data.lastlocation.z", location.getZ());
		configuration.set(getDPlayer(player.getName()) + ".data.lastlocation.yaw", ((float) location.getYaw()));
		configuration.set(getDPlayer(player.getName()) + ".data.lastlocation.pitch", ((float) location.getPitch()));
		FileManager.getInstance().save();
	}
	
	public void resetLastLocation(Player player)
	{
		configuration.set(getDPlayer(player.getName()) + ".data.lastlocation.world", WorldUtil.SURVIVAL_SPAWN.getWorld().getName());
		configuration.set(getDPlayer(player.getName()) + ".data.lastlocation.x", WorldUtil.SURVIVAL_SPAWN.getX());
		configuration.set(getDPlayer(player.getName()) + ".data.lastlocation.y", WorldUtil.SURVIVAL_SPAWN.getY());
		configuration.set(getDPlayer(player.getName()) + ".data.lastlocation.z", WorldUtil.SURVIVAL_SPAWN.getZ());
		configuration.set(getDPlayer(player.getName()) + ".data.lastlocation.yaw", WorldUtil.SURVIVAL_SPAWN.getYaw());
		configuration.set(getDPlayer(player.getName()) + ".data.lastlocation.pitch", WorldUtil.SURVIVAL_SPAWN.getPitch());
		FileManager.getInstance().save();
	}
	
	public Location getHome(Player player, String which)
	{
		String world = (String) configuration.get(getDPlayer(player.getName()) + ".data.home" + which + ".world");
		double x = (double) configuration.get(getDPlayer(player.getName()) + ".data.home" + which + ".x");
		double y = (double) configuration.get(getDPlayer(player.getName()) + ".data.home" + which + ".y");
		double z = (double) configuration.get(getDPlayer(player.getName()) + ".data.home" + which + ".z");
		float yaw = (float) Float.valueOf(String.valueOf(configuration.get(getDPlayer(player.getName()) + ".data.home" + which + ".yaw")));
		float pitch = (float) Float.valueOf(String.valueOf(configuration.get(getDPlayer(player.getName()) + ".data.home" + which + ".pitch")));
		
		return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
	}
	
	public void setHome(Player player, String which, Location location)
	{
		configuration.set(getDPlayer(player.getName()) + ".data.home" + which + ".world", location.getWorld().getName());
		configuration.set(getDPlayer(player.getName()) + ".data.home" + which + ".x", location.getX());
		configuration.set(getDPlayer(player.getName()) + ".data.home" + which + ".y", location.getY());
		configuration.set(getDPlayer(player.getName()) + ".data.home" + which + ".z", location.getZ());
		configuration.set(getDPlayer(player.getName()) + ".data.home" + which + ".yaw", ((float) location.getYaw()));
		configuration.set(getDPlayer(player.getName()) + ".data.home" + which + ".pitch", ((float) location.getPitch()));
		FileManager.getInstance().save();
	}
	
	public void setSchowek(Player player, List<ItemStack> contents)
	{
		configuration.set(DPlayerManager.getDPlayer(player.getName()) + ".data.schowek.items", contents);
		FileManager.getInstance().save();
	}
	
	public int getSchowekLevel(Player player)
	{
		return (int) configuration.get(getDPlayer(player.getName()) + ".data.schowek.level");
	}
	
	public void setSchowekLevel(Player player, int level)
	{
		configuration.set(getDPlayer(player.getName()) + ".data.schowek.level", level);
		FileManager.getInstance().save();
	}

	public String getChatColor(Player player)
	{
		return (String) configuration.get(getDPlayer(player.getName()) + ".data.chatcolor");
	}

	public String getChatColor(String player)
	{
		return (String) configuration.get(getDPlayer(player) + ".data.chatcolor");
	}

	public void setChatColor(Player player, String color)
	{
		configuration.set(getDPlayer(player.getName()) + ".data.chatcolor", color);
		FileManager.getInstance().save();
	}

	public String getMute(String player)
	{
		return (String) configuration.get(getDPlayer(player) + ".data.mute");
	}

	public void setMute(Player player, String date)
	{
		configuration.set(getDPlayer(player.getName()) + ".data.mute", date);
		FileManager.getInstance().save();
	}

	public int getMuteLength(String player)
	{
		return (int) configuration.get(getDPlayer(player) + ".data.mutelength");
	}

	public void setMuteLength(Player player, int length)
	{
		configuration.set(getDPlayer(player.getName()) + ".data.mutelength", length);
		FileManager.getInstance().save();
	}

	public int getSerotonine(String player)
	{
		return (int) configuration.get(getDPlayer(player) + ".data.neurotransmitters.serotonine");
	}

	public void setSerotonine(Player player, int amount)
	{
		configuration.set(getDPlayer(player.getName()) + ".data.neurotransmitters.serotonine", amount);
		FileManager.getInstance().save();
	}

	public int getDopamine(String player)
	{
		return (int) configuration.get(getDPlayer(player) + ".data.neurotransmitters.dopamine");
	}

	public void setDopamine(Player player, int amount)
	{
		configuration.set(getDPlayer(player.getName()) + ".data.neurotransmitters.dopamine", amount);
		FileManager.getInstance().save();
	}

	public int getNoradrenaline(String player)
	{
		return (int) configuration.get(getDPlayer(player) + ".data.neurotransmitters.noradrenaline");
	}

	public void setNoradrenaline(Player player, int amount)
	{
		configuration.set(getDPlayer(player.getName()) + ".data.neurotransmitters.noradrenaline", amount);
		FileManager.getInstance().save();
	}

	public int getGABA(String player)
	{
		return (int) configuration.get(getDPlayer(player) + ".data.neurotransmitters.gaba");
	}

	public void setGABA(Player player, int amount)
	{
		configuration.set(getDPlayer(player.getName()) + ".data.neurotransmitters.gaba", amount);
		FileManager.getInstance().save();
	}

	public boolean getQuestStatus(String player, int quest)
	{
		return (boolean) configuration.get(getDPlayer(getDPlayer(player)) + ".data.quests.q" + quest + ".status");
	}

	public void setQuestStatus(String player, int quest, boolean status)
	{
		configuration.set(getDPlayer(player) + ".data.quests.q" + quest + ".status", status);
		FileManager.getInstance().save();
	}

	public int getQuestCompleting(String player, int quest)
	{
		int current = (int) configuration.get(getDPlayer(player) + ".data.quests.q" + quest + ".current");
		int requied = (int) configuration.get(getDPlayer(player) + ".data.quests.q" + quest + ".requied");
		double curr = Double.parseDouble(String.valueOf(current));
		double req = Double.parseDouble(String.valueOf(requied));
		double math = (curr / req) * 100;

		return (int) math;
	}

	public void addQuestomplete(String player, int quest)
	{
		configuration.set(getDPlayer(player) + ".data.quests.q" + quest + ".current", (int) configuration.get(getDPlayer(player) + ".data.quests.q" + quest + ".current") + 1);
		FileManager.getInstance().save();
	}

	public String getQuestBar(String player,int quest)
	{
		int percentage = getQuestCompleting(getDPlayer(player), quest);

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("&a");

		for (int progress = 0; progress < (percentage / 2); progress++)
			stringBuilder.append(":");

		stringBuilder.append("&8");

		for (int left = 0; left <= (53 - (stringBuilder.length() - left) ); left++)
			stringBuilder.append(":");

		return stringBuilder.append(" &7(").append(percentage).append("%)").toString();
	}

	public int getUpgradeLevel(String player, String upgrade)
	{
		return (int) configuration.get(getDPlayer(player) + ".data.upgrades." + upgrade.toLowerCase());
	}

	public void addUpgradeLevel(String player, String upgrade)
	{
		configuration.set(getDPlayer(player) + ".data.upgrades." + upgrade, (int) configuration.get(getDPlayer(player) + ".data.upgrades." + upgrade) + 1);
		FileManager.getInstance().save();
	}

	public String getUpgradeBar(String player, String upgrade, String type)
	{
		StringBuilder stringBuilder = new StringBuilder();
		int tier = getUpgradeLevel(player, upgrade);

		if (tier > 0)
		{
			stringBuilder.append(type);

			for (int color = 0; color < tier; color++)
				for (int fullness = 0; fullness < 10; fullness++)
					stringBuilder.append(":");
		}

		if (tier < 5)
		{
			stringBuilder.append("&7");

			for (int fullness = 0; fullness < 10; fullness++)
				stringBuilder.append(":");

			if (tier < 4)
			{
				stringBuilder.append("&8");

				for (int black = 0; black < 5 - (tier + 1); black++)
					for (int fullness = 0; fullness < 10; fullness++)
						stringBuilder.append(":");
			}
		}

		return stringBuilder.toString();
	}
}