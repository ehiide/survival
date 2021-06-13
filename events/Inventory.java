package mc.server.survival.events;

import mc.server.survival.files.Configuration;
import mc.server.survival.files.Main;
import mc.server.survival.items.*;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.managers.DPlayerManager;
import mc.server.survival.managers.FileManager;
import mc.server.survival.managers.GangManager;
import mc.server.survival.utils.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Inventory implements Listener
{
	@EventHandler
	public void onEvent(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot =  event.getSlot();

		if (checkName(event, "&c&lDOMKI"))
		{
			event.setCancelled(true);

			if (slot == 11)
			{
				if (event.getClick() == ClickType.LEFT)
				{
					String isExist = (String) FileManager.getInstance().configuration().get(DPlayerManager.getDPlayer(player.getName()) + ".data.home1.world");

					if (isExist.equalsIgnoreCase("none"))
					{
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Nie wiem co piles ale wlasnie probujesz przeteleportowac sie do domku, ktorego nawet nie ustawiles!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}

					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cTrwa teleportacja do wybranego domku... #fff203⌛");
					player.teleport(DPlayerManager.getInstance().getHome(player, "1"));

					new BukkitRunnable() { @Override public void run() {
						player.closeInventory();
						VisualUtil.showServerChangeTitle(player);
						ScoreboardUtil.showScoreboard(player);
						TablistUtil.showTablist(player);
					} }.runTaskLater(Main.getInstance(), 20L);
				}

				if (event.getClick() == ClickType.RIGHT)
				{
					DPlayerManager.getInstance().setHome(player, "1", player.getLocation());
				}

				InventoryUtil.createNewInventory(player, 27, ChatColor.translateAlternateColorCodes('&', "&c&lDOMKI"), "domki");
			}

			if (slot == 15)
			{
				if (event.getClick() == ClickType.LEFT)
				{
					String isExist = (String) FileManager.getInstance().configuration().get(DPlayerManager.getDPlayer(player.getName()) + ".data.home2.world");

					if (isExist.equalsIgnoreCase("none"))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Nie wiem co piles ale wlasnie probujesz przeteleportowac sie do domku, ktorego nawet nie ustawiles!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}

					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cTrwa teleportacja do wybranego domku... #fff203⌛");
					player.teleport(DPlayerManager.getInstance().getHome(player, "2"));

					new BukkitRunnable() { @Override public void run() {
						player.closeInventory();
						VisualUtil.showServerChangeTitle(player);
						ScoreboardUtil.showScoreboard(player);
						TablistUtil.showTablist(player);
					} }.runTaskLater(Main.getInstance(), 20L);
				}

				if (event.getClick() == ClickType.RIGHT)
					DPlayerManager.getInstance().setHome(player, "2", player.getLocation());

				InventoryUtil.createNewInventory(player, 27, ChatColor.translateAlternateColorCodes('&', "&c&lDOMKI"), "domki");
			}
		}
		else if (checkName(event, "&c&lSCHOWEK"))
			new BukkitRunnable()
			{
				@Override
				public void run()
				{
					schedule(event);
				}
			}.runTaskLaterAsynchronously(Main.getInstance(), 1);
		else if (checkName(event, "&c&lULEPSZENIE SCHOWKU"))
		{
			event.setCancelled(true);

			if (slot == 15)
			{
				if (event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT)
				{
					if (DPlayerManager.getInstance().getSchowekLevel(player) >= 3)
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Posiadasz juz maksymalny poziom ulepszenia schowku!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}

					boolean isAble = DPlayerManager.getInstance().getMoney(player) >= 200;

					if (!isAble)
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Marzenia marzeniami, ale najpierw uzbieraj sume na zakup ulepszenia, troszke Ci brakuje!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}
					else
					{
						DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 200);
						DPlayerManager.getInstance().setSchowekLevel(player, DPlayerManager.getInstance().getSchowekLevel(player) + 1);

						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cGratulacje! Twoj schowek zostal powiekszony, wpisz komende #fc7474/schowek #8c8c8ci zajrzyj do srodka!");
						ServerUtil.reloadContents(player);
						QuestUtil.manageQuest(player, 7);
					}

					player.closeInventory();
				}
			}
		}
		else if (checkName(event, "&c&lSKLEP"))
		{
			event.setCancelled(true);

			if (event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT)
				if (slot == 13)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI"), "sklep_przedmioty");
				else if (slot == 20)
					InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z EFEKTAMI"), "sklep_efekty");
				else if (slot == 24)
					InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP GANGU"), "sklep_gang");
				else if (slot == 31)
					InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z DODATKAMI"), "sklep_dodatki");
		}
		else if (checkName(event, "&c&lSKLEP Z PRZEDMIOTAMI"))
		{
			event.setCancelled(true);

			if (event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT)
				if (slot == 9)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (I, 1/6)"), "sklep_przedmioty_itemy");
				else if (slot == 18)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 1/2)"), "sklep_przedmioty_ksiazki");
				else if (slot == 27)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (S, 1/1)"), "sklep_przedmioty_specjalne");
				else if (slot == 49)
					InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP"), "sklep");
		}
		else if (checkName(event, "&c&lSKLEP Z PRZEDMIOTAMI (I, 1/6)"))
		{
			event.setCancelled(true);

			if (event.getSlot() == 11 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy1")))
				manageItem(event, Material.COBBLESTONE, 16, 4 ,2);
			else if (event.getSlot() == 12 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy2")))
				manageItem(event, Material.NETHERRACK, 16, 4 ,2);
			else if (event.getSlot() == 13 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy3")))
				manageItem(event, Material.STONE, 16, 4 ,3);
			else if (event.getSlot() == 14 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy4")))
				manageItem(event, Material.DIRT, 16, 8 ,3);
			else if (event.getSlot() == 15 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy5")))
				manageItem(event, Material.GRASS_BLOCK, 16, 16 ,6);
			else if (event.getSlot() == 20 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy6")))
				manageItem(event, Material.CRIMSON_NYLIUM, 16, 16 ,8);
			else if (event.getSlot() == 21 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy7")))
				manageItem(event, Material.WARPED_NYLIUM, 16, 16 ,8);
			else if (event.getSlot() == 22 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy8")))
				manageItem(event, Material.GRANITE, 8, 8 ,4);
			else if (event.getSlot() == 23 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy9")))
				manageItem(event, Material.DIORITE, 8, 8 ,4);
			else if (event.getSlot() == 24 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy10")))
				manageItem(event, Material.ANDESITE, 8, 8 ,4);
			else if (event.getSlot() == 29 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy11")))
				manageItem(event, Material.GRAVEL, 8, 8 ,4);
			else if (event.getSlot() == 30 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy12")))
				manageItem(event, Material.SAND, 8, 4, 2);
			else if (event.getSlot() == 31 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy13")))
				manageItem(event, Material.SANDSTONE, 8, 4, 3);
			else if (event.getSlot() == 32 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy14")))
				manageItem(event, Material.RED_SAND, 8, 8, 3);
			else if (event.getSlot() == 33 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy15")))
				manageItem(event, Material.RED_SANDSTONE, 8, 8, 4);

			if (event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT)
				if (slot == 18)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 1/2)"), "sklep_przedmioty_ksiazki");
				else if (slot == 27)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (S, 1/1)"), "sklep_przedmioty_specjalne");
				else if (slot == 49)
					InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP"), "sklep");
				else if (slot == 53)
                    InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (I, 2/6)"), "sklep_przedmioty_itemy2");
		}
        else if (checkName(event, "&c&lSKLEP Z PRZEDMIOTAMI (I, 2/6)"))
        {
			event.setCancelled(true);

			if (event.getSlot() == 11 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy16")))
				manageItem(event, Material.OAK_LOG, 8, 8 ,4);
			else if (event.getSlot() == 12 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy17")))
				manageItem(event, Material.SPRUCE_LOG, 8, 8 ,4);
			else if (event.getSlot() == 13 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy18")))
				manageItem(event, Material.BIRCH_LOG, 8, 8 ,4);
			else if (event.getSlot() == 14 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy19")))
				manageItem(event, Material.JUNGLE_LOG, 8, 8 ,4);
			else if (event.getSlot() == 15 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy20")))
				manageItem(event, Material.ACACIA_LOG, 8, 8 ,4);
			else if (event.getSlot() == 20 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy21")))
				manageItem(event, Material.DARK_OAK_LOG, 8, 8 ,4);
			else if (event.getSlot() == 21 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy22")))
				manageItem(event, Material.CRIMSON_STEM, 8, 12 ,6);
			else if (event.getSlot() == 22 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy23")))
				manageItem(event, Material.WARPED_STEM, 8, 12 ,6);
			else if (event.getSlot() == 23 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy24")))
				manageItem(event, Material.OAK_LEAVES, 16, 8 ,3);
			else if (event.getSlot() == 24 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy25")))
				manageItem(event, Material.SPRUCE_LEAVES, 16, 8 ,3);
			else if (event.getSlot() == 29 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy26")))
				manageItem(event, Material.BIRCH_LEAVES, 16, 8 ,3);
			else if (event.getSlot() == 30 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy27")))
				manageItem(event, Material.JUNGLE_LEAVES, 16, 8 ,3);
			else if (event.getSlot() == 31 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy28")))
				manageItem(event, Material.ACACIA_LEAVES, 16, 8 ,3);
			else if (event.getSlot() == 32 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy29")))
				manageItem(event, Material.DARK_OAK_LEAVES, 16, 8 ,3);
			else if (event.getSlot() == 33 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy30")))
				manageItem(event, Material.SNOW_BLOCK, 8, 32, 16);

            if (event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT)
                if (slot == 18)
                    InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 1/2)"), "sklep_przedmioty_ksiazki");
                else if (slot == 27)
                    InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (S, 1/1)"), "sklep_przedmioty_specjalne");
                else if (slot == 49)
                    InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP"), "sklep");
                else if (slot == 45)
                    InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (I, 1/6)"), "sklep_przedmioty_itemy");
				else if (slot == 53)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (I, 3/6)"), "sklep_przedmioty_itemy3");
		}
		else if (checkName(event, "&c&lSKLEP Z PRZEDMIOTAMI (I, 3/6)"))
		{
			event.setCancelled(true);

			if (event.getSlot() == 11 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy31")))
				manageItem(event, Material.ICE, 8, 16 ,8);
			else if (event.getSlot() == 12 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy32")))
				manageItem(event, Material.PACKED_ICE, 8, 24 ,12);
			else if (event.getSlot() == 13 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy33")))
				manageItem(event, Material.SEA_LANTERN, 1, 8 ,-1);
			else if (event.getSlot() == 14 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy34")))
				manageItem(event, Material.PRISMARINE, 8, 16 ,-1);
			else if (event.getSlot() == 15 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy35")))
				manageItem(event, Material.DARK_PRISMARINE, 8, 24 ,12);
			else if (event.getSlot() == 20 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy36")))
				manageItem(event, Material.NETHER_BRICKS, 8, 8 ,4);
			else if (event.getSlot() == 21 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy37")))
				manageItem(event, Material.GLOWSTONE, 8, 24 ,12);
			else if (event.getSlot() == 22 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy38")))
				manageItem(event, Material.SOUL_SAND, 8, 24 ,6);
			else if (event.getSlot() == 23 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy39")))
				manageItem(event, Material.BLACKSTONE, 8, 8 ,3);
			else if (event.getSlot() == 24 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy40")))
				manageItem(event, Material.OBSIDIAN, 8, 32 ,16);
			else if (event.getSlot() == 29 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy41")))
				manageItem(event, Material.END_STONE, 16, 12 ,8);
			else if (event.getSlot() == 30 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy42")))
				manageItem(event, Material.END_STONE_BRICKS, 8, 36 ,12);
			else if (event.getSlot() == 31 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy43")))
				manageItem(event, Material.PURPUR_BLOCK, 8, 24 ,-1);
			else if (event.getSlot() == 32 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy44")))
				manageItem(event, Material.CHORUS_FRUIT, 8, 32 ,8);
			else if (event.getSlot() == 33 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy45")))
				manageItem(event, Material.CHORUS_FLOWER, 8, -1, 12);

			if (event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT)
				if (slot == 18)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 1/2)"), "sklep_przedmioty_ksiazki");
				else if (slot == 27)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (S, 1/1)"), "sklep_przedmioty_specjalne");
				else if (slot == 49)
					InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP"), "sklep");
				else if (slot == 45)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (I, 2/6)"), "sklep_przedmioty_itemy2");
				else if (slot == 53)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (I, 4/6)"), "sklep_przedmioty_itemy4");
		}
		else if (checkName(event, "&c&lSKLEP Z PRZEDMIOTAMI (I, 4/6)"))
		{
			event.setCancelled(true);

			if (event.getSlot() == 11 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy46")))
				manageItem(event, Material.NETHERITE_INGOT, 1, 1000 ,400);
			else if (event.getSlot() == 12 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy47")))
				manageItem(event, Material.EMERALD, 1, 40 ,20);
			else if (event.getSlot() == 13 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy48")))
				manageItem(event, Material.DIAMOND, 1, 160 ,60);
			else if (event.getSlot() == 14 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy49")))
				manageItem(event, Material.GOLD_INGOT, 1,  32,6);
			else if (event.getSlot() == 15 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy50")))
				manageItem(event, Material.IRON_INGOT, 1, 16 ,4);
			else if (event.getSlot() == 20 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy51")))
				manageItem(event, Material.QUARTZ, 8, 24 ,16);
			else if (event.getSlot() == 21 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy52")))
				manageItem(event, Material.IRON_NUGGET, 8, -1 ,-1);
			else if (event.getSlot() == 22 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy53")))
				manageItem(event, Material.REDSTONE, 8, 12 ,8);
			else if (event.getSlot() == 23 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy54")))
				manageItem(event, Material.LAPIS_LAZULI, 8, 24 ,6);
			else if (event.getSlot() == 24 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy55")))
				manageItem(event, Material.COAL, 8, 12 ,4);
			else if (event.getSlot() == 29 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy56")))
				manageItem(event, Material.WHEAT, 8, 12 ,4);
			else if (event.getSlot() == 30 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy57")))
				manageItem(event, Material.CARROT, 8, 16 ,6);
			else if (event.getSlot() == 31 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy58")))
				manageItem(event, Material.POTATO, 8, 12 ,4);
			else if (event.getSlot() == 32 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy59")))
				manageItem(event, Material.MELON_SLICE, 8, 32 ,6);
			else if (event.getSlot() == 33 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy60")))
				manageItem(event, Material.BEETROOT, 8, 24, 8);

			if (event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT)
				if (slot == 18)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 1/2)"), "sklep_przedmioty_ksiazki");
				else if (slot == 27)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (S, 1/1)"), "sklep_przedmioty_specjalne");
				else if (slot == 49)
					InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP"), "sklep");
				else if (slot == 45)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (I, 3/6)"), "sklep_przedmioty_itemy3");
				else if (slot == 53)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (I, 5/6)"), "sklep_przedmioty_itemy5");
		}
		else if (checkName(event, "&c&lSKLEP Z PRZEDMIOTAMI (I, 5/6)"))
		{
			event.setCancelled(true);

			if (event.getSlot() == 11 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy61")))
				manageItem(event, Material.BAMBOO, 16, 48 ,4);
			else if (event.getSlot() == 12 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy62")))
				manageItem(event, Material.SUGAR_CANE, 8, 16 ,8);
			else if (event.getSlot() == 13 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy63")))
				manageItem(event, Material.CACTUS, 8, 8 ,6);
			else if (event.getSlot() == 14 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy64")))
				manageItem(event, Material.PUMPKIN, 8,  12,8);
			else if (event.getSlot() == 15 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy65")))
				manageItem(event, Material.EGG, 8, 12 ,4);
			else if (event.getSlot() == 20 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy66")))
				manageItem(event, Material.APPLE, 1, 8 ,2);
			else if (event.getSlot() == 21 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy67")))
				manageItem(event, Material.GOLDEN_APPLE, 1, 200 ,64);
			else if (event.getSlot() == 22 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy68")))
				manageItem(event, Material.PORKCHOP, 16, 8 ,4);
			else if (event.getSlot() == 23 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy69")))
				manageItem(event, Material.RABBIT, 16, 8 ,4);
			else if (event.getSlot() == 24 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy70")))
				manageItem(event, Material.BEEF, 16, 8 ,4);
			else if (event.getSlot() == 29 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy71")))
				manageItem(event, Material.CHICKEN, 16, 8 ,4);
			else if (event.getSlot() == 30 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy72")))
				manageItem(event, Material.MUTTON, 16, 8 ,4);
			else if (event.getSlot() == 31 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy73")))
				manageItem(event, Material.LEATHER, 8, 24 ,12);
			else if (event.getSlot() == 32 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy74")))
				manageItem(event, Material.HONEYCOMB, 4, 16 ,12);
			else if (event.getSlot() == 33 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy75")))
				manageItem(event, Material.NETHER_WART, 8, 48, 16);

			if (event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT)
				if (slot == 18)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 1/2)"), "sklep_przedmioty_ksiazki");
				else if (slot == 27)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (S, 1/1)"), "sklep_przedmioty_specjalne");
				else if (slot == 49)
					InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP"), "sklep");
				else if (slot == 45)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (I, 4/6)"), "sklep_przedmioty_itemy4");
				else if (slot == 53)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (I, 6/6)"), "sklep_przedmioty_itemy6");
		}
		else if (checkName(event, "&c&lSKLEP Z PRZEDMIOTAMI (I, 6/6)"))
		{
			event.setCancelled(true);

			if (event.getSlot() == 11 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy76")))
				manageItem(event, Material.SLIME_BALL, 8, 64 ,-1);
			else if (event.getSlot() == 12 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy77")))
				manageItem(event, Material.ENDER_PEARL, 1, 32 ,4);
			else if (event.getSlot() == 13 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy78")))
				manageItem(event, Material.BLAZE_ROD, 1, 48 ,8);
			else if (event.getSlot() == 14 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy79")))
				manageItem(event, Material.GHAST_TEAR, 1,  128,32);
			else if (event.getSlot() == 15 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy80")))
				manageItem(event, Material.SPIDER_EYE, 1, 24 ,8);
			else if (event.getSlot() == 20 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy81")))
				manageItem(event, Material.GUNPOWDER, 8, 32 ,16);
			else if (event.getSlot() == 21 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy82")))
				manageItem(event, Material.BONE, 8, 24 ,12);
			else if (event.getSlot() == 22 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepitemy83")))
				manageItem(event, Material.STRING, 8, 24 ,18);

			if (event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT)
				if (slot == 18)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 1/2)"), "sklep_przedmioty_ksiazki");
				else if (slot == 27)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (S, 1/1)"), "sklep_przedmioty_specjalne");
				else if (slot == 49)
					InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP"), "sklep");
				else if (slot == 45)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (I, 5/6)"), "sklep_przedmioty_itemy5");
		}
		else if (checkName(event, "&c&lSKLEP Z PRZEDMIOTAMI (K, 1/2)"))
		{
			event.setCancelled(true);

			if (event.getSlot() == 11 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepksiazki1")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 4200)
				{
					player.getInventory().addItem(InventoryUtil.getEnchantmentBook(Enchantment.MENDING, 1));
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 4200);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 1/2)"), "sklep_przedmioty_ksiazki");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles zakleta ksiazke #8c8c8c(4200⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}
			else if (event.getSlot() == 12 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepksiazki2")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 2700)
				{
					player.getInventory().addItem(InventoryUtil.getEnchantmentBook(Enchantment.ARROW_INFINITE, 1));
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 2700);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 1/2)"), "sklep_przedmioty_ksiazki");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles zakleta ksiazke #8c8c8c(2700⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}
			else if (event.getSlot() == 13 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepksiazki3")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 3000)
				{
					player.getInventory().addItem(InventoryUtil.getEnchantmentBook(Enchantment.SILK_TOUCH, 1));
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 3000);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 1/2)"), "sklep_przedmioty_ksiazki");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles zakleta ksiazke #8c8c8c(3000⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}
			else if (event.getSlot() == 14 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepksiazki4")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 3900)
				{
					player.getInventory().addItem(InventoryUtil.getEnchantmentBook(Enchantment.PROTECTION_ENVIRONMENTAL, 4));
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 3900);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 1/2)"), "sklep_przedmioty_ksiazki");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles zakleta ksiazke #8c8c8c(3900⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}
			else if (event.getSlot() == 15 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepksiazki5")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 3700)
				{
					player.getInventory().addItem(InventoryUtil.getEnchantmentBook(Enchantment.DAMAGE_ALL, 5));
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 3700);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 1/2)"), "sklep_przedmioty_ksiazki");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles zakleta ksiazke #8c8c8c(3700⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}
			else if (event.getSlot() == 20 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepksiazki6")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 2200)
				{
					player.getInventory().addItem(InventoryUtil.getEnchantmentBook(Enchantment.FIRE_ASPECT, 2));
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 2200);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 1/2)"), "sklep_przedmioty_ksiazki");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles zakleta ksiazke #8c8c8c(2200⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}
			else if (event.getSlot() == 21 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepksiazki7")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 3900)
				{
					player.getInventory().addItem(InventoryUtil.getEnchantmentBook(Enchantment.ARROW_DAMAGE, 5));
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 3900);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 1/2)"), "sklep_przedmioty_ksiazki");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles zakleta ksiazke #8c8c8c(3900⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}
			else if (event.getSlot() == 22 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepksiazki8")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 1400)
				{
					player.getInventory().addItem(InventoryUtil.getEnchantmentBook(Enchantment.ARROW_FIRE, 2));
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 1400);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 1/2)"), "sklep_przedmioty_ksiazki");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles zakleta ksiazke #8c8c8c(1400⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}
			else if (event.getSlot() == 23 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepksiazki9")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 2900)
				{
					player.getInventory().addItem(InventoryUtil.getEnchantmentBook(Enchantment.ARROW_KNOCKBACK, 2));
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 2900);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 1/2)"), "sklep_przedmioty_ksiazki");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles zakleta ksiazke #8c8c8c(2900⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}
			else if (event.getSlot() == 24 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepksiazki10")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 4000)
				{
					player.getInventory().addItem(InventoryUtil.getEnchantmentBook(Enchantment.DIG_SPEED, 5));
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 4000);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 1/2)"), "sklep_przedmioty_ksiazki");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles zakleta ksiazke #8c8c8c(4000⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}
			else if (event.getSlot() == 29 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepksiazki11")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 2500)
				{
					player.getInventory().addItem(InventoryUtil.getEnchantmentBook(Enchantment.DURABILITY, 3));
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 2500);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 1/2)"), "sklep_przedmioty_ksiazki");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles zakleta ksiazke #8c8c8c(2500⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}
			else if (event.getSlot() == 30 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepksiazki12")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 3400)
				{
					player.getInventory().addItem(InventoryUtil.getEnchantmentBook(Enchantment.LOOT_BONUS_MOBS, 3));
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 3400);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 1/2)"), "sklep_przedmioty_ksiazki");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles zakleta ksiazke #8c8c8c(3400⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}
			else if (event.getSlot() == 31 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepksiazki13")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 3600)
				{
					player.getInventory().addItem(InventoryUtil.getEnchantmentBook(Enchantment.LOOT_BONUS_BLOCKS, 3));
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 3600);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 1/2)"), "sklep_przedmioty_ksiazki");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles zakleta ksiazke #8c8c8c(3600⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}
			else if (event.getSlot() == 32 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepksiazki14")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 3300)
				{
					player.getInventory().addItem(InventoryUtil.getEnchantmentBook(Enchantment.PROTECTION_FALL, 4));
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 3300);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 1/2)"), "sklep_przedmioty_ksiazki");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles zakleta ksiazke #8c8c8c(3300⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}
			else if (event.getSlot() == 33 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepksiazki15")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 3000)
				{
					player.getInventory().addItem(InventoryUtil.getEnchantmentBook(Enchantment.THORNS, 3));
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 3000);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 1/2)"), "sklep_przedmioty_ksiazki");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles zakleta ksiazke #8c8c8c(3000⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}

			if (event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT)
				if (slot == 9)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (I, 1/6)"), "sklep_przedmioty_itemy");
				else if (slot == 27)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (S, 1/1)"), "sklep_przedmioty_specjalne");
				else if (slot == 49)
					InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP"), "sklep");
				else if (slot == 53)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 2/2)"), "sklep_przedmioty_ksiazki2");
				else if (slot == 45)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 1/2)"), "sklep_przedmioty_ksiazki");
		}
		else if (checkName(event, "&c&lSKLEP Z PRZEDMIOTAMI (K, 2/2)"))
		{
			event.setCancelled(true);

			if (event.getSlot() == 11 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepksiazki16")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 3600)
				{
					player.getInventory().addItem(InventoryUtil.getEnchantmentBook(Enchantment.DEPTH_STRIDER, 3));
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 3600);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 2/2)"), "sklep_przedmioty_ksiazki2");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles zakleta ksiazke #8c8c8c(3600⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}
			else if (event.getSlot() == 12 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepksiazki17")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 2700)
				{
					player.getInventory().addItem(InventoryUtil.getEnchantmentBook(Enchantment.LUCK, 3));
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 2700);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 2/2)"), "sklep_przedmioty_ksiazki2");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles zakleta ksiazke #8c8c8c(2700⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}
			else if (event.getSlot() == 13 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepksiazki18")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 2600)
				{
					player.getInventory().addItem(InventoryUtil.getEnchantmentBook(Enchantment.LURE, 3));
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 2600);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 2/2)"), "sklep_przedmioty_ksiazki2");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles zakleta ksiazke #8c8c8c(2600⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}
			else if (event.getSlot() == 14 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepksiazki19")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 2400)
				{
					player.getInventory().addItem(InventoryUtil.getEnchantmentBook(Enchantment.QUICK_CHARGE, 3));
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 2400);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 2/2)"), "sklep_przedmioty_ksiazki2");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles zakleta ksiazke #8c8c8c(2400⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}
			else if (event.getSlot() == 15 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepksiazki20")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 2900)
				{
					player.getInventory().addItem(InventoryUtil.getEnchantmentBook(Enchantment.CHANNELING, 1));
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 2900);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 2/2)"), "sklep_przedmioty_ksiazki2");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles zakleta ksiazke #8c8c8c(2900⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}
			else if (event.getSlot() == 20 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepksiazki21")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 3500)
				{
					player.getInventory().addItem(InventoryUtil.getEnchantmentBook(Enchantment.MULTISHOT, 1));
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 3500);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 2/2)"), "sklep_przedmioty_ksiazki2");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles zakleta ksiazke #8c8c8c(3500⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}
			else if (event.getSlot() == 21 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepksiazki22")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 3700)
				{
					player.getInventory().addItem(InventoryUtil.getEnchantmentBook(Enchantment.IMPALING, 5));
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 3700);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 2/2)"), "sklep_przedmioty_ksiazki2");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles zakleta ksiazke #8c8c8c(3700⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}
			else if (event.getSlot() == 22 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepksiazki23")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 3400)
				{
					player.getInventory().addItem(InventoryUtil.getEnchantmentBook(Enchantment.PIERCING, 4));
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 3400);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 2/2)"), "sklep_przedmioty_ksiazki2");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles zakleta ksiazke #8c8c8c(3400⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}
			else if (event.getSlot() == 23 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepksiazki24")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 1900)
				{
					player.getInventory().addItem(InventoryUtil.getEnchantmentBook(Enchantment.RIPTIDE, 3));
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 1900);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 2/2)"), "sklep_przedmioty_ksiazki2");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles zakleta ksiazke #8c8c8c(1900⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}

			if (event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT)
				if (slot == 9)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (I, 1/6)"), "sklep_przedmioty_itemy");
				else if (slot == 27)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (S, 1/1)"), "sklep_przedmioty_specjalne");
				else if (slot == 45)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 1/2)"), "sklep_przedmioty_ksiazki");
				else if (slot == 49)
					InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP"), "sklep");
		}
		else if (checkName(event, "&c&lSKLEP Z PRZEDMIOTAMI (S, 1/1)"))
		{
			event.setCancelled(true);

			if (event.getSlot() == 11 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepspecjalne1")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 3100)
				{
					player.getInventory().addItem(IKusza.getItem());
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 3100);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (S, 1/1)"), "sklep_przedmioty_specjalne");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles nowy przedmiot specjalny #8c8c8c(3100⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}
			else if (event.getSlot() == 12 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepspecjalne2")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 4600)
				{
					player.getInventory().addItem(IKilof.getItem());
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 4600);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (S, 1/1)"), "sklep_przedmioty_specjalne");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles nowy przedmiot specjalny #8c8c8c(4600⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}
			else if (event.getSlot() == 13 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepspecjalne3")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 2900)
				{
					player.getInventory().addItem(ISiekiera.getItem());
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 2900);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (S, 1/1)"), "sklep_przedmioty_specjalne");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles nowy przedmiot specjalny #8c8c8c(2900⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}
			else if (event.getSlot() == 14 && event.getClick() == ClickType.LEFT && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepspecjalne4")))
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 2600)
				{
					player.getInventory().addItem(ILopata.getItem());
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 2600);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (S, 1/1)"), "sklep_przedmioty_specjalne");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles nowy przedmiot specjalny #8c8c8c(2600⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}
			else if (event.getSlot() == 15 && event.getClick() == ClickType.LEFT)
			{
				if (InventoryUtil.isFullInventory(player))
				{
					castByFullInv(player);
					return;
				}

				if (DPlayerManager.getInstance().getMoney(player) >= 1400 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepspecjalne5")))
				{
					player.getInventory().addItem(IMotyka.getItem());
					DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 1400);
					ServerUtil.reloadContents(player);
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (S, 1/1)"), "sklep_przedmioty_specjalne");
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles nowy przedmiot specjalny #8c8c8c(1400⛃)#80ff1f!");
				}
				else
					castByNoMoney(player);
			}

			if (event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT)
				if (slot == 9)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (I, 1/6" +")"), "sklep_przedmioty_itemy");
				else if (slot == 18)
					InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z PRZEDMIOTAMI (K, 1/2)"), "sklep_przedmioty_ksiazki");
				else if (slot == 49)
					InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP"), "sklep");
		}
		else if (checkName(event, "&c&lSKLEP GANGU"))
		{
			event.setCancelled(true);

			if (event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT)
				if (slot == 10 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepgang1")))
				{
					if (!GangManager.getInstance().getLider(GangManager.getInstance().getGang(player)).equalsIgnoreCase(player.getName()))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Tylko lider gangu moze decydowac o jego designie!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}

					if (GangManager.getInstance().getColor(GangManager.getInstance().getGang(player)).equalsIgnoreCase("red"))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Twoj gang posiada juz ten kolor!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
					}
					else
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 250)
						{
							GangManager.getInstance().setColor(GangManager.getInstance().getGang(player), "red");
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 250);
							ServerUtil.reloadContents(player);
							InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP GANGU"), "sklep_gang");
							ChatUtil.sendGangAllChatMessage(GangManager.getInstance().getGang(player), "#ffc936Gracz " + player.getName() + " zakupil nowy kolor dla gangu [&c█#ffc936] #8c8c8c(200⛃)#ffc936!");
						}
						else
							castByNoMoney(player);
					}
				}
				else if (slot == 11 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepgang2")))
				{
					if (!GangManager.getInstance().getLider(GangManager.getInstance().getGang(player)).equalsIgnoreCase(player.getName()))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Tylko lider gangu moze decydowac o jego designie!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}

					if (GangManager.getInstance().getColor(GangManager.getInstance().getGang(player)).equalsIgnoreCase("blue"))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Twoj gang posiada juz ten kolor!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}
					else
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 250)
						{
							GangManager.getInstance().setColor(GangManager.getInstance().getGang(player), "blue");
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 250);
							ServerUtil.reloadContents(player);
							InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP GANGU"), "sklep_gang");
							ChatUtil.sendGangAllChatMessage(GangManager.getInstance().getGang(player), "#ffc936Gracz " + player.getName() + " zakupil nowy kolor dla gangu [&b█#ffc936] #8c8c8c(200⛃)#ffc936!");
						}
						else
							castByNoMoney(player);
					}
				}
				else if (slot == 12 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepgang3")))
				{
					if (!GangManager.getInstance().getLider(GangManager.getInstance().getGang(player)).equalsIgnoreCase(player.getName()))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Tylko lider gangu moze decydowac o jego designie!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}

					if (GangManager.getInstance().getColor(GangManager.getInstance().getGang(player)).equalsIgnoreCase("green"))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Twoj gang posiada juz ten kolor!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}
					else
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 250)
						{
							GangManager.getInstance().setColor(GangManager.getInstance().getGang(player), "green");
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 250);
							ServerUtil.reloadContents(player);
							InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP GANGU"), "sklep_gang");
							ChatUtil.sendGangAllChatMessage(GangManager.getInstance().getGang(player), "#ffc936Gracz " + player.getName() + " zakupil nowy kolor dla gangu [&a█#ffc936] #8c8c8c(200⛃)#ffc936!");
						}
						else
							castByNoMoney(player);
					}
				}
				else if (slot == 19 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepgang4")))
				{
					if (!GangManager.getInstance().getLider(GangManager.getInstance().getGang(player)).equalsIgnoreCase(player.getName()))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Tylko lider gangu moze decydowac o jego designie!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}

					if (GangManager.getInstance().getColor(GangManager.getInstance().getGang(player)).equalsIgnoreCase("yellow"))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Twoj gang posiada juz ten kolor!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}
					else
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 250)
						{
							GangManager.getInstance().setColor(GangManager.getInstance().getGang(player), "yellow");
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 250);
							ServerUtil.reloadContents(player);
							InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP GANGU"), "sklep_gang");
							ChatUtil.sendGangAllChatMessage(GangManager.getInstance().getGang(player), "#ffc936Gracz " + player.getName() + " zakupil nowy kolor dla gangu [&e█#ffc936] #8c8c8c(200⛃)#ffc936!");
						}
						else
							castByNoMoney(player);
					}
				}
				else if (slot == 20 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepgang5")))
				{
					if (!GangManager.getInstance().getLider(GangManager.getInstance().getGang(player)).equalsIgnoreCase(player.getName()))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Tylko lider gangu moze decydowac o jego designie!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}

					if (GangManager.getInstance().getColor(GangManager.getInstance().getGang(player)).equalsIgnoreCase("white"))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Twoj gang posiada juz ten kolor!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}
					else
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 250)
						{
							GangManager.getInstance().setColor(GangManager.getInstance().getGang(player), "white");
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 250);
							ServerUtil.reloadContents(player);
							InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP GANGU"), "sklep_gang");
							ChatUtil.sendGangAllChatMessage(GangManager.getInstance().getGang(player), "#ffc936Gracz " + player.getName() + " zakupil nowy kolor dla gangu [&f█#ffc936] #8c8c8c(200⛃)#ffc936!");
						}
						else
							castByNoMoney(player);
					}
				}
				else if (slot == 21 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepgang6")))
				{
					if (!GangManager.getInstance().getLider(GangManager.getInstance().getGang(player)).equalsIgnoreCase(player.getName()))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Tylko lider gangu moze decydowac o jego designie!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}

					if (GangManager.getInstance().getColor(GangManager.getInstance().getGang(player)).equalsIgnoreCase("gray"))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Twoj gang posiada juz ten kolor!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}
					else
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 250)
						{
							GangManager.getInstance().setColor(GangManager.getInstance().getGang(player), "gray");
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 250);
							ServerUtil.reloadContents(player);
							InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP GANGU"), "sklep_gang");
							ChatUtil.sendGangAllChatMessage(GangManager.getInstance().getGang(player), "#ffc936Gracz " + player.getName() + " zakupil nowy kolor dla gangu [&7█#ffc936] #8c8c8c(200⛃)#ffc936!");
						}
						else
							castByNoMoney(player);
					}
				}
				else if (slot == 28 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepgang7")))
				{
					if (!GangManager.getInstance().getLider(GangManager.getInstance().getGang(player)).equalsIgnoreCase(player.getName()))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Tylko lider gangu moze decydowac o jego designie!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}

					if (GangManager.getInstance().getColor(GangManager.getInstance().getGang(player)).equalsIgnoreCase("orange"))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Twoj gang posiada juz ten kolor!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}
					else
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 250)
						{
							GangManager.getInstance().setColor(GangManager.getInstance().getGang(player), "orange");
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 250);
							ServerUtil.reloadContents(player);
							InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP GANGU"), "sklep_gang");
							ChatUtil.sendGangAllChatMessage(GangManager.getInstance().getGang(player), "#ffc936Gracz " + player.getName() + " zakupil nowy kolor dla gangu [&6█#ffc936] #8c8c8c(200⛃)#ffc936!");
						}
						else
							castByNoMoney(player);
					}
				}
				else if (slot == 29 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepgang8")))
				{
					if (!GangManager.getInstance().getLider(GangManager.getInstance().getGang(player)).equalsIgnoreCase(player.getName()))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Tylko lider gangu moze decydowac o jego designie!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}

					if (GangManager.getInstance().getColor(GangManager.getInstance().getGang(player)).equalsIgnoreCase("pink"))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Twoj gang posiada juz ten kolor!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}
					else
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 250)
						{
							GangManager.getInstance().setColor(GangManager.getInstance().getGang(player), "pink");
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 250);
							ServerUtil.reloadContents(player);
							InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP GANGU"), "sklep_gang");
							ChatUtil.sendGangAllChatMessage(GangManager.getInstance().getGang(player), "#ffc936Gracz " + player.getName() + " zakupil nowy kolor dla gangu [&d█#ffc936] #8c8c8c(200⛃)#ffc936!");
						}
						else
							castByNoMoney(player);
					}
				}
				else if (slot == 14 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepgangp1")))
				{
					if (!GangManager.getInstance().getLider(GangManager.getInstance().getGang(player)).equalsIgnoreCase(player.getName()))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Tylko lider gangu moze decydowac o jego designie!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}

					if (GangManager.getInstance().getPrefixes(GangManager.getInstance().getGang(player)).equalsIgnoreCase("normal"))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Twoj gang posiada juz te prefixy!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}
					else
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 350)
						{
							GangManager.getInstance().setPrefixes(GangManager.getInstance().getGang(player), "normal");
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 350);
							ServerUtil.reloadContents(player);
							InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP GANGU"), "sklep_gang");
							ChatUtil.sendGangAllChatMessage(GangManager.getInstance().getGang(player), "#ffc936Gracz " + player.getName() + " zakupil nowe prefixy dla gangu [&7Kwadratowe#ffc936] #8c8c8c(350⛃)#ffc936!");
						}
						else
							castByNoMoney(player);
					}
				}
				else if (slot == 23 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepgangp2")))
				{
					if (!GangManager.getInstance().getLider(GangManager.getInstance().getGang(player)).equalsIgnoreCase(player.getName()))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Tylko lider gangu moze decydowac o jego designie!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}

					if (GangManager.getInstance().getPrefixes(GangManager.getInstance().getGang(player)).equalsIgnoreCase("rounded"))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Twoj gang posiada juz te prefixy!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}
					else
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 350)
						{
							GangManager.getInstance().setPrefixes(GangManager.getInstance().getGang(player), "rounded");
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 350);
							ServerUtil.reloadContents(player);
							InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP GANGU"), "sklep_gang");
							ChatUtil.sendGangAllChatMessage(GangManager.getInstance().getGang(player), "#ffc936Gracz " + player.getName() + " zakupil nowe prefixy dla gangu [&7Zaokraglone#ffc936] #8c8c8c(350⛃)#ffc936!");
						}
						else
							castByNoMoney(player);
					}
				}
				else if (slot == 32 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepgangp3")))
				{
					if (!GangManager.getInstance().getLider(GangManager.getInstance().getGang(player)).equalsIgnoreCase(player.getName()))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Tylko lider gangu moze decydowac o jego designie!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}

					if (GangManager.getInstance().getPrefixes(GangManager.getInstance().getGang(player)).equalsIgnoreCase("arrows"))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Twoj gang posiada juz te prefixy!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}
					else
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 350)
						{
							GangManager.getInstance().setPrefixes(GangManager.getInstance().getGang(player), "arrows");
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 350);
							ServerUtil.reloadContents(player);
							InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP GANGU"), "sklep_gang");
							ChatUtil.sendGangAllChatMessage(GangManager.getInstance().getGang(player), "#ffc936Gracz " + player.getName() + " zakupil nowe prefixy dla gangu [&7Strzalkowe#ffc936] #8c8c8c(350⛃)#ffc936!");
						}
						else
							castByNoMoney(player);
					}
				}
				else if (slot == 16 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepgangstar")))
				{
					if (!GangManager.getInstance().getLider(GangManager.getInstance().getGang(player)).equalsIgnoreCase(player.getName()))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Tylko lider gangu moze decydowac o jego designie!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}

					if (GangManager.getInstance().getStar(GangManager.getInstance().getGang(player)))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Twoj gang posiada juz kosmetyk gwiazdy!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
						return;
					}
					else
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 900)
						{
							GangManager.getInstance().setStar(GangManager.getInstance().getGang(player), true);
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 900);
							ServerUtil.reloadContents(player);
							InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP GANGU"), "sklep_gang");
							ChatUtil.sendGangAllChatMessage(GangManager.getInstance().getGang(player), "#ffc936Gracz " + player.getName() + " zakupil ekskluzywna gwiazde dla gangu [★] #8c8c8c(900⛃)#ffc936!");
							QuestUtil.manageQuest(player, 13);
						}
						else
							castByNoMoney(player);
					}
				}
				else if (slot == 25 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepgangchat")))
				{
					if (GangManager.getInstance().getChat(GangManager.getInstance().getGang(player)))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Twoj gang posiada juz odblokowany czat prywatny!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
					}
					else
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 500)
						{
							GangManager.getInstance().setChat(GangManager.getInstance().getGang(player), true);
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 500);
							ServerUtil.reloadContents(player);
							InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP GANGU"), "sklep_gang");
							ChatUtil.sendGangAllChatMessage(GangManager.getInstance().getGang(player), "#ffc936Gracz " + player.getName() + " zakupil prywatny czat dla gangu [#80ff1f!wiadomosc#ffc936] #8c8c8c(500⛃)#ffc936!");
						}
						else
							castByNoMoney(player);
					}
				}
				else if (slot == 40)
					InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP"), "sklep");
		}
		else if (checkName(event, "&c&lSKLEP Z DODATKAMI"))
		{
			event.setCancelled(true);

			if (event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT)
				if (slot == 10 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepkolor1")))
					if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("red"))
					{
						castByNowActive(player);
						return;
					}
					else
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 400)
						{
							DPlayerManager.getInstance().setChatColor(player, "red");
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 400);
							ServerUtil.reloadContents(player);
							InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z DODATKAMI"), "sklep_dodatki");
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles nowy kolor swojego nicku [#fc7474█#80ff1f] #8c8c8c(400⛃)#80ff1f!");
						}
						else
							castByNoMoney(player);
					}
				else if (slot == 11 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepkolor2")))
					if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("blue"))
					{
						castByNowActive(player);
						return;
					}
					else {
						if (DPlayerManager.getInstance().getMoney(player) >= 400) {
							DPlayerManager.getInstance().setChatColor(player, "blue");
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 400);
							ServerUtil.reloadContents(player);
							InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z DODATKAMI"), "sklep_dodatki");
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles nowy kolor swojego nicku [#3075ff█#80ff1f] #8c8c8c(400⛃)#80ff1f!");
						} else
							castByNoMoney(player);
					}
				else if (slot == 12 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepkolor3")))
					if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("green"))
					{
						castByNowActive(player);
						return;
					}
					else
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 400) {
							DPlayerManager.getInstance().setChatColor(player, "green");
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 400);
							ServerUtil.reloadContents(player);
							InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z DODATKAMI"), "sklep_dodatki");
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles nowy kolor swojego nicku [#02d645█#80ff1f] #8c8c8c(400⛃)#80ff1f!");
						} else
							castByNoMoney(player);
					}
				else if (slot == 19 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepkolor4")))
					if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("yellow"))
					{
						castByNowActive(player);
						return;
					}
					else
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 400) {
							DPlayerManager.getInstance().setChatColor(player, "yellow");
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 400);
							ServerUtil.reloadContents(player);
							InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z DODATKAMI"), "sklep_dodatki");
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles nowy kolor swojego nicku [#fcff33█#80ff1f] #8c8c8c(400⛃)#80ff1f!");
						} else
							castByNoMoney(player);
					}
				else if (slot == 20 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepkolor5")))
					if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("white"))
					{
						castByNowActive(player);
						return;
					}
					else
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 400) {
							DPlayerManager.getInstance().setChatColor(player, "white");
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 400);
							ServerUtil.reloadContents(player);
							InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z DODATKAMI"), "sklep_dodatki");
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles nowy kolor swojego nicku [#ffffff█#80ff1f] #8c8c8c(400⛃)#80ff1f!");
						} else
							castByNoMoney(player);
					}
				else if (slot == 21 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepkolor6")))
					if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("gray"))
					{
						castByNowActive(player);
						return;
					}
					else
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 400) {
							DPlayerManager.getInstance().setChatColor(player, "gray");
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 400);
							ServerUtil.reloadContents(player);
							InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z DODATKAMI"), "sklep_dodatki");
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles nowy kolor swojego nicku [#242424█#80ff1f] #8c8c8c(400⛃)#80ff1f!");
						} else
							castByNoMoney(player);
					}
				else if (slot == 28 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepkolor7")))
					if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("orange"))
					{
						castByNowActive(player);
						return;
					}
					else
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 400) {
							DPlayerManager.getInstance().setChatColor(player, "orange");
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 400);
							ServerUtil.reloadContents(player);
							InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z DODATKAMI"), "sklep_dodatki");
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles nowy kolor swojego nicku [#ffb338█#80ff1f] #8c8c8c(400⛃)#80ff1f!");
						} else
							castByNoMoney(player);
					}
				else if (slot == 29 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepkolor8")))
					if (DPlayerManager.getInstance().getChatColor(player).equalsIgnoreCase("pink"))
					{
						castByNowActive(player);
						return;
					}
					else
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 400) {
							DPlayerManager.getInstance().setChatColor(player, "pink");
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 400);
							ServerUtil.reloadContents(player);
							InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z DODATKAMI"), "sklep_dodatki");
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles nowy kolor swojego nicku [#ff9ee7█#80ff1f] #8c8c8c(400⛃)#80ff1f!");
						} else
							castByNoMoney(player);
					}
				else if (slot == 14 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepschowek")))
					InventoryUtil.createNewInventory(player, 27, ChatColor.translateAlternateColorCodes('&', "&c&lULEPSZENIE SCHOWKU"), "schowek");
				else if (slot == 23 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepvoucher")))
				{
					if (!(TimeUtil.getDifferenceInSeconds(DPlayerManager.getInstance().getMute(player.getName())) < DPlayerManager.getInstance().getMuteLength(player.getName())))
					{
						player.closeInventory();
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Nie jestes nawet wyciszony, to tak jakbys kupil szampon do wlosow bedac lysym!");
						VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
					}
					else
						{
						if (DPlayerManager.getInstance().getMoney(player) >= 300)
						{
							DPlayerManager.getInstance().setMuteLength(player, 0);
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 300);
							ServerUtil.reloadContents(player);
							InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP Z DODATKAMI"), "sklep_dodatki");
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles voucher na odciszenie #8c8c8c(300⛃)#80ff1f!");
						}
						else
							castByNoMoney(player);
					}
				}
				else if (slot == 40)
					InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP"), "sklep");
		}
		else if (checkName(event, "&c&lMONOPOLOWY U STASIA"))
		{
			event.setCancelled(true);

			if (event.getSlot() == 20 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "monopolowypiwo")))
			{
				if (event.getClick() == ClickType.LEFT)
				{
					if (InventoryUtil.isFullInventory(player))
					{
						castByFullInv(player);
						return;
					}

					if (DPlayerManager.getInstance().getMoney(player) >= 60)
					{
						DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 60);
						player.getInventory().addItem(Piwo.getItem());
						ServerUtil.reloadContents(player);
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles alkohol #8c8c8c(60⛃)#80ff1f!");
					}
					else
						castByNoMoney(player);
				}
			}
			else if (event.getSlot() == 21 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "monopolowywino")))
			{
				if (event.getClick() == ClickType.LEFT)
				{
					if (InventoryUtil.isFullInventory(player))
					{
						castByFullInv(player);
						return;
					}

					if (DPlayerManager.getInstance().getMoney(player) >= 100)
					{
						DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 100);
						player.getInventory().addItem(Wino.getItem());
						ServerUtil.reloadContents(player);
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles alkohol #8c8c8c(100⛃)#80ff1f!");
					}
					else
						castByNoMoney(player);
				}
			}
			else if (event.getSlot() == 22 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "monopolowyszampan")))
			{
				if (event.getClick() == ClickType.LEFT)
				{
					if (InventoryUtil.isFullInventory(player))
					{
						castByFullInv(player);
						return;
					}

					if (DPlayerManager.getInstance().getMoney(player) >= 150)
					{
						DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 150);
						player.getInventory().addItem(Szampan.getItem());
						ServerUtil.reloadContents(player);
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles alkohol #8c8c8c(150⛃)#80ff1f!");
					}
					else
						castByNoMoney(player);
				}
			}
			else if (event.getSlot() == 23 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "monopolowywhisky")))
			{
				if (event.getClick() == ClickType.LEFT)
				{
					if (InventoryUtil.isFullInventory(player))
					{
						castByFullInv(player);
						return;
					}

					if (DPlayerManager.getInstance().getMoney(player) >= 340)
					{
						DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 340);
						player.getInventory().addItem(Whisky.getItem());
						ServerUtil.reloadContents(player);
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles alkohol #8c8c8c(340⛃)#80ff1f!");
					}
					else
						castByNoMoney(player);
				}
			}
			else if (event.getSlot() == 24 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "monopolowywodka")))
			{
				if (event.getClick() == ClickType.LEFT)
				{
					if (InventoryUtil.isFullInventory(player))
					{
						castByFullInv(player);
						return;
					}

					if (DPlayerManager.getInstance().getMoney(player) >= 400)
					{
						DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 400);
						player.getInventory().addItem(Wodka.getItem());
						ServerUtil.reloadContents(player);
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles alkohol #8c8c8c(400⛃)#80ff1f!");
					}
					else
						castByNoMoney(player);
				}
			}
		}
		else if (checkName(event, "&c&lSKLEP Z EFEKTAMI"))
		{
			event.setCancelled(true);

			if (event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT)
				if (slot == 40)
					InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP"), "sklep");


			if (event.getSlot() == 10 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepspeed"))) {
				if (event.getClick() == ClickType.LEFT) {
					if (DPlayerManager.getInstance().getMoney(player) >= 300) {
						DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 300);
						player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 60 * 3, 1));
						ServerUtil.reloadContents(player);
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury szybkosci 2 na okres 3 minut #8c8c8c(300⛃)#80ff1f!");
					} else
						castByNoMoney(player);
				} else if (event.getClick() == ClickType.SHIFT_LEFT) {
					if (DPlayerManager.getInstance().getMoney(player) >= 600) {
						DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 600);
						player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 60 * 8, 1));
						ServerUtil.reloadContents(player);
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury szybkosci 2 na okres 8 minut #8c8c8c(600⛃)#80ff1f!");
					} else
						castByNoMoney(player);
				} else if (event.getClick() == ClickType.MIDDLE) {
					if (DPlayerManager.getInstance().getMoney(player) >= 1800) {
						DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 1800);
						player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 60 * 30, 1));
						ServerUtil.reloadContents(player);
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury szybkosci 2 na okres 30 minut #8c8c8c(1800⛃)#80ff1f!");
					} else
						castByNoMoney(player);
				}
			} else if (event.getSlot() == 11 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepjumpboost"))) {
				if (event.getClick() == ClickType.LEFT) {
					if (DPlayerManager.getInstance().getMoney(player) >= 300) {
						DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 300);
						player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20 * 60 * 3, 3));
						ServerUtil.reloadContents(player);
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury zwiekszonego skoku 4 na okres 3 minut #8c8c8c(300⛃)#80ff1f!");
					} else
						castByNoMoney(player);
				} else if (event.getClick() == ClickType.SHIFT_LEFT) {
					if (DPlayerManager.getInstance().getMoney(player) >= 600) {
						DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 600);
						player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20 * 60 * 8, 3));
						ServerUtil.reloadContents(player);
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury zwiekszonego skoku 4 na okres 8 minut #8c8c8c(600⛃)#80ff1f!");
					} else
						castByNoMoney(player);
				} else if (event.getClick() == ClickType.MIDDLE) {
					if (DPlayerManager.getInstance().getMoney(player) >= 1800) {
						DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 1800);
						player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20 * 60 * 30, 3));
						ServerUtil.reloadContents(player);
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury zwiekszonego skoku 4 na okres 30 minut #8c8c8c(1800⛃)#80ff1f!");
					} else
						castByNoMoney(player);
				}
			} else if (event.getSlot() == 12 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepresistance"))) {
				if (event.getClick() == ClickType.LEFT) {
					if (DPlayerManager.getInstance().getMoney(player) >= 300) {
						DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 300);
						player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 60 * 3, 0));
						ServerUtil.reloadContents(player);
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury odpornosci na okres 3 minut #8c8c8c(300⛃)#80ff1f!");
					} else
						castByNoMoney(player);
				} else if (event.getClick() == ClickType.SHIFT_LEFT) {
					if (DPlayerManager.getInstance().getMoney(player) >= 600) {
						DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 600);
						player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 60 * 8, 0));
						ServerUtil.reloadContents(player);
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury odpornosci na okres 8 minut #8c8c8c(600⛃)#80ff1f!");
					} else
						castByNoMoney(player);
				} else if (event.getClick() == ClickType.MIDDLE) {
					if (DPlayerManager.getInstance().getMoney(player) >= 1800) {
						DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 1800);
						player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 60 * 30, 0));
						ServerUtil.reloadContents(player);
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury odpornosci na okres 30 minut #8c8c8c(1800⛃)#80ff1f!");
					} else
						castByNoMoney(player);
				}
			} else if (event.getSlot() == 13 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepstrength"))) {
				if (event.getClick() == ClickType.LEFT) {
					if (DPlayerManager.getInstance().getMoney(player) >= 300) {
						DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 300);
						player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 60 * 3, 0));
						ServerUtil.reloadContents(player);
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury sily na okres 3 minut #8c8c8c(300⛃)#80ff1f!");
					} else
						castByNoMoney(player);
				} else if (event.getClick() == ClickType.SHIFT_LEFT) {
					if (DPlayerManager.getInstance().getMoney(player) >= 600) {
						DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 600);
						player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 60 * 8, 0));
						ServerUtil.reloadContents(player);
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury sily na okres 8 minut #8c8c8c(600⛃)#80ff1f!");
					} else
						castByNoMoney(player);
				} else if (event.getClick() == ClickType.MIDDLE) {
					if (DPlayerManager.getInstance().getMoney(player) >= 1800) {
						DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 1800);
						player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 60 * 30, 0));
						ServerUtil.reloadContents(player);
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury sily na okres 30 minut #8c8c8c(1800⛃)#80ff1f!");
					} else
						castByNoMoney(player);
				}
			} else if (event.getSlot() == 14 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklephaste"))) {
				if (event.getClick() == ClickType.LEFT) {
					if (DPlayerManager.getInstance().getMoney(player) >= 300) {
						DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 300);
						player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 60 * 3, 0));
						ServerUtil.reloadContents(player);
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury szybkosci kopania na okres 3 minut #8c8c8c(300⛃)#80ff1f!");
					} else
						castByNoMoney(player);
				} else if (event.getClick() == ClickType.SHIFT_LEFT) {
					if (DPlayerManager.getInstance().getMoney(player) >= 600) {
						DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 600);
						player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 60 * 8, 0));
						ServerUtil.reloadContents(player);
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury szybkosci kopania na okres 8 minut #8c8c8c(600⛃)#80ff1f!");
					} else
						castByNoMoney(player);
				} else if (event.getClick() == ClickType.MIDDLE) {
					if (DPlayerManager.getInstance().getMoney(player) >= 1800) {
						DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 1800);
						player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 60 * 30, 0));
						ServerUtil.reloadContents(player);
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury szybkosci kopania na okres 30 minut #8c8c8c(1800⛃)#80ff1f!");
					} else
						castByNoMoney(player);
				}
			}
			else if (event.getSlot() == 15 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepfireresistance")))
				{
					if (event.getClick() == ClickType.LEFT)
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 300)
						{
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 300);
							player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20*60 * 3, 1));
							ServerUtil.reloadContents(player);
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury odpornosci na ogien 2 na okres 3 minut #8c8c8c(300⛃)#80ff1f!");
						}
						else
							castByNoMoney(player);
					}
					else if (event.getClick() == ClickType.SHIFT_LEFT)
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 600)
						{
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 600);
							player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20*60 * 8, 1));
							ServerUtil.reloadContents(player);
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury odpornosci na ogien 2 na okres 8 minut #8c8c8c(600⛃)#80ff1f!");
						}
						else
							castByNoMoney(player);
					}
					else if (event.getClick() == ClickType.MIDDLE)
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 1800)
						{
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 1800);
							player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20*60 * 30, 1));
							ServerUtil.reloadContents(player);
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury odpornosci na ogien 2 na okres 30 minut #8c8c8c(1800⛃)#80ff1f!");
						}
						else
							castByNoMoney(player);
					}
				}
				else if (event.getSlot() == 16 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepregeneration")))
				{
					if (event.getClick() == ClickType.LEFT)
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 300)
						{
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 300);
							player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20*60 * 3, 0));
							ServerUtil.reloadContents(player);
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury regeneracji na okres 3 minut #8c8c8c(300⛃)#80ff1f!");
						}
						else
							castByNoMoney(player);
					}
					else if (event.getClick() == ClickType.SHIFT_LEFT)
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 600)
						{
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 600);
							player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20*60 * 8, 0));
							ServerUtil.reloadContents(player);
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury regeneracji na okres 8 minut #8c8c8c(600⛃)#80ff1f!");
						}
						else
							castByNoMoney(player);
					}
					else if (event.getClick() == ClickType.MIDDLE)
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 1800)
						{
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 1800);
							player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20*60 * 30, 0));
							ServerUtil.reloadContents(player);
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury regeneracji na okres 30 minut #8c8c8c(1800⛃)#80ff1f!");
						}
						else
							castByNoMoney(player);
					}
				}
				else if (event.getSlot() == 21 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepinvis")))
				{
					if (event.getClick() == ClickType.LEFT)
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 300)
						{
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 300);
							player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20*60 * 3, 1));
							ServerUtil.reloadContents(player);
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury niewidzialnosci 2 na okres 3 minut #8c8c8c(300⛃)#80ff1f!");
						}
						else
							castByNoMoney(player);
					}
					else if (event.getClick() == ClickType.SHIFT_LEFT)
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 600)
						{
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 600);
							player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20*60 * 8, 1));
							ServerUtil.reloadContents(player);
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury niewidzialnosci 2 na okres 8 minut #8c8c8c(600⛃)#80ff1f!");
						}
						else
							castByNoMoney(player);
					}
					else if (event.getClick() == ClickType.MIDDLE)
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 1800)
						{
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 1800);
							player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20*60 * 30, 1));
							ServerUtil.reloadContents(player);
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury niewidzialnosci 2 na okres 30 minut #8c8c8c(1800⛃)#80ff1f!");
						}
						else
							castByNoMoney(player);
					}
				}
				else if (event.getSlot() == 22 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepwaterbreathing")))
				{
					if (event.getClick() == ClickType.LEFT)
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 300)
						{
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 300);
							player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 20*60 * 3, 0));
							ServerUtil.reloadContents(player);
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury oddychania pod woda na okres 3 minut #8c8c8c(300⛃)#80ff1f!");
						}
						else
							castByNoMoney(player);
					}
					else if (event.getClick() == ClickType.SHIFT_LEFT)
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 600)
						{
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 600);
							player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 20*60 * 8, 0));
							ServerUtil.reloadContents(player);
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury oddychania pod woda na okres 8 minut #8c8c8c(600⛃)#80ff1f!");
						}
						else
							castByNoMoney(player);
					}
					else if (event.getClick() == ClickType.MIDDLE)
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 1800)
						{
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 1800);
							player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 20*60 * 30, 0));
							ServerUtil.reloadContents(player);
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury oddychania pod woda na okres 30 minut #8c8c8c(1800⛃)#80ff1f!");
						}
						else
							castByNoMoney(player);
					}
				}
				else if (event.getSlot() == 23 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "sklepnightvision")))
				{
					if (event.getClick() == ClickType.LEFT)
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 300)
						{
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 300);
							player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20*60 * 3, 1));
							ServerUtil.reloadContents(player);
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury noktowizji 2 na okres 3 minut #8c8c8c(300⛃)#80ff1f!");
						}
						else
							castByNoMoney(player);
					}
					else if (event.getClick() == ClickType.SHIFT_LEFT)
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 600)
						{
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 600);
							player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20*60 * 8, 1));
							ServerUtil.reloadContents(player);
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury noktowizji 2 na okres 8 minut #8c8c8c(600⛃)#80ff1f!");
						}
						else
							castByNoMoney(player);
					}
					else if (event.getClick() == ClickType.MIDDLE)
					{
						if (DPlayerManager.getInstance().getMoney(player) >= 1800)
						{
							DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 1800);
							player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20*60 * 30, 1));
							ServerUtil.reloadContents(player);
							ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles efekt mikstury noktowizji 2 na okres 30 minut #8c8c8c(1800⛃)#80ff1f!");
						}
						else
							castByNoMoney(player);
					}
				}
		}
		else if (checkName(event, "&c&lCENTRUM POMOCY SERWERA"))
			event.setCancelled(true);
		else if (checkName(event, "&c&lWYSPY"))
		{
			if (event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT)
			{
				if (slot == 11)
					player.teleport(WorldUtil.WYSPA_DZUNGLA_SPAWN);
				else if (slot == 12)
					player.teleport(WorldUtil.WYSPA_GRZYBOWA_SPAWN);
				else if (slot == 13)
					player.teleport(WorldUtil.WYSPA_LAS_SPAWN);
				else if (slot == 14)
					player.teleport(WorldUtil.WYSPA_LODOWA_SPAWN);
				else if (slot == 15)
					player.teleport(WorldUtil.WYSPA_SAVANNA_SPAWN);
				else if (slot == 20)
					player.teleport(WorldUtil.WYSPA_GORY_SPAWN);
				else if (slot == 21)
					player.teleport(WorldUtil.WYSPA_PUSTYNNA_SPAWN);
				else if (slot == 22)
					player.teleport(WorldUtil.WYSPA_MESSA_SPAWN);
				else if (slot == 40)
					player.teleport(WorldUtil.SURVIVAL_SPAWN);

				if (slot == 11 || slot == 12 || slot == 13 || slot == 14 || slot == 15 || slot == 20 || slot == 21 || slot == 22)
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cTrwa teleportacja na wybrana wyspe... #fff203⌛");

				if (slot == 40)
					ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cTrwa teleportacja na spawn... #fff203⌛");
			}

			event.setCancelled(true);
		}
		else if (checkName(event, "&c&lDOSTEPNE KOLORY"))
		{
			event.setCancelled(true);
		}
		else if (checkName(event, "&c&lCRAFTINGI (1/7)"))
		{
			if (slot == 44)
				InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lCRAFTINGI (2/7)"), "craftingi2");

			event.setCancelled(true);
		}
		else if (checkName(event, "&c&lCRAFTINGI (2/7)"))
		{
			if (slot == 44)
				InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lCRAFTINGI (3/7)"), "craftingi3");
			else if (slot == 36)
				InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lCRAFTINGI (1/7)"), "craftingi1");

			event.setCancelled(true);
		}
		else if (checkName(event, "&c&lCRAFTINGI (3/7)"))
		{
			if (slot == 44)
				InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lCRAFTINGI (4/7)"), "craftingi4");
			else if (slot == 36)
				InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lCRAFTINGI (2/7)"), "craftingi2");

			event.setCancelled(true);
		}
		else if (checkName(event, "&c&lCRAFTINGI (4/7)"))
		{
			if (slot == 44)
				InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lCRAFTINGI (5/7)"), "craftingi5");
			else if (slot == 36)
				InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lCRAFTINGI (3/7)"), "craftingi3");

			event.setCancelled(true);
		}
		else if (checkName(event, "&c&lCRAFTINGI (5/7)"))
		{
			if (slot == 44)
				InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lCRAFTINGI (6/7)"), "craftingi6");
			else if (slot == 36)
				InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lCRAFTINGI (4/7)"), "craftingi4");

			event.setCancelled(true);
		}
		else if (checkName(event, "&c&lCRAFTINGI (6/7)"))
		{
			if (slot == 44)
				InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lCRAFTINGI (7/7)"), "craftingi7");
			else if (slot == 36)
				InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lCRAFTINGI (5/7)"), "craftingi5");

			event.setCancelled(true);
		}
		else if (checkName(event, "&c&lCRAFTINGI (7/7)"))
		{
			if (slot == 36)
				InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lCRAFTINGI (6/7)"), "craftingi6");

			event.setCancelled(true);
		}
		else if (checkName(event, "&c&lQUESTY"))
		{
			event.setCancelled(true);
			player.closeInventory();
			ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Czy tam pisze zeby cos kliknac? Jasny chuj!");
		}
		else if (checkName(event, "&c&lPOSTAC"))
		{
			if (slot == 38 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "postacupgrade1")))
			{
				if (event.getClick() == ClickType.LEFT)
				{
					if (DPlayerManager.getInstance().getUpgradeLevel(player.getName(), "vitality") >= 5)
					{
						castByMaxUpgrade(player);
						return;
					}

					if (DPlayerManager.getInstance().getSP(player) >= 1)
					{
						DPlayerManager.getInstance().addUpgradeLevel(player.getName(), "vitality");
						DPlayerManager.getInstance().setSP(player, DPlayerManager.getInstance().getSP(player) - 1);
						ServerUtil.reloadContents(player);
						player.setMaxHealth(20.0 + (4.0D * DPlayerManager.getInstance().getUpgradeLevel(player.getName(), "vitality")));
						player.setHealth(player.getMaxHealth());
						InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lPOSTAC"), "postac");
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles ulepszenie postaci #8c8c8c(1☀)#80ff1f!");
					}
					else
						castByNoSP(player);
				}
			}
			else if (slot == 39 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "postacupgrade2")))
			{
				if (event.getClick() == ClickType.LEFT)
				{
					if (DPlayerManager.getInstance().getUpgradeLevel(player.getName(), "luck") >= 5)
					{
						castByMaxUpgrade(player);
						return;
					}

					if (DPlayerManager.getInstance().getSP(player) >= 1)
					{
						DPlayerManager.getInstance().addUpgradeLevel(player.getName(), "luck");
						DPlayerManager.getInstance().setSP(player, DPlayerManager.getInstance().getSP(player) - 1);
						ServerUtil.reloadContents(player);
						InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lPOSTAC"), "postac");
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles ulepszenie postaci #8c8c8c(1☀)#80ff1f!");
					}
					else
						castByNoSP(player);
				}
			}
			else if (slot == 40 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "postacupgrade3")))
			{
				if (event.getClick() == ClickType.LEFT)
				{
					if (DPlayerManager.getInstance().getUpgradeLevel(player.getName(), "loot") >= 5)
					{
						castByMaxUpgrade(player);
						return;
					}

					if (DPlayerManager.getInstance().getSP(player) >= 1)
					{
						DPlayerManager.getInstance().addUpgradeLevel(player.getName(), "loot");
						DPlayerManager.getInstance().setSP(player, DPlayerManager.getInstance().getSP(player) - 1);
						ServerUtil.reloadContents(player);
						InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lPOSTAC"), "postac");
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles ulepszenie postaci #8c8c8c(1☀)#80ff1f!");
					}
					else
						castByNoSP(player);
				}
			}
			else if (slot == 41 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "postacupgrade4")))
			{
				if (event.getClick() == ClickType.LEFT)
				{
					if (DPlayerManager.getInstance().getUpgradeLevel(player.getName(), "honorable") >= 5)
					{
						castByMaxUpgrade(player);
						return;
					}

					if (DPlayerManager.getInstance().getSP(player) >= 1)
					{
						DPlayerManager.getInstance().addUpgradeLevel(player.getName(), "honorable");
						DPlayerManager.getInstance().setSP(player, DPlayerManager.getInstance().getSP(player) - 1);
						ServerUtil.reloadContents(player);
						InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lPOSTAC"), "postac");
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles ulepszenie postaci #8c8c8c(1☀)#80ff1f!");
					}
					else
						castByNoSP(player);
				}
			}
			else if (slot == 42 && Objects.equals(event.getCurrentItem(), InventoryUtil.getItem(player, "postacupgrade5")))
			{
				if (event.getClick() == ClickType.LEFT)
				{
					if (DPlayerManager.getInstance().getUpgradeLevel(player.getName(), "thiefy") >= 5)
					{
						castByMaxUpgrade(player);
						return;
					}

					if (DPlayerManager.getInstance().getSP(player) >= 1)
					{
						DPlayerManager.getInstance().addUpgradeLevel(player.getName(), "thiefy");
						DPlayerManager.getInstance().setSP(player, DPlayerManager.getInstance().getSP(player) - 1);
						ServerUtil.reloadContents(player);
						InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lPOSTAC"), "postac");
						ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie zakupiles ulepszenie postaci #8c8c8c(1☀)#80ff1f!");
					}
					else
						castByNoSP(player);
				}
			}

			event.setCancelled(true);
		}
	}
	
	public void schedule(InventoryInteractEvent event) 
	{
		Player player = (Player) event.getWhoClicked();
		
		ItemStack[] items = event.getInventory().getContents();
		List<ItemStack> contents = new ArrayList<>();

		Collections.addAll(contents, items);
		
		DPlayerManager.getInstance().setSchowek(player, contents);
	}

	private boolean checkName(InventoryClickEvent event, String name) { return event.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', name)); }
	
	private void castByNoMoney(Player player)
	{
		player.closeInventory();
		ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Nie posiadasz wystarczajaco monet, aby zakupic ten przedmiot!");
		VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
	}

	private void castByNoSP(Player player)
	{
		player.closeInventory();
		ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Nie posiadasz wystarczajaco punktow umiejetnosci, aby zakupic ten przedmiot!");
		VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
	}

	private void castByFullInv(Player player)
	{
		player.closeInventory();
		ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Gdzie ty masz kieszenie! Najpierw zrob wolne miejsce w ekwipunku, aby cos kupic!");
		VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
	}

	private void castByNowActive(Player player)
	{
		player.closeInventory();
		ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Posiadasz juz ustawiony ten kolor jako swoj aktualny!");
		VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
	}

	private void castByMaxUpgrade(Player player)
	{
		player.closeInventory();
		ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Posiadasz juz maksymalny poziom tego ulepszenia!");
		VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
	}

	private void castByNotEnoughItem(Player player)
	{
		player.closeInventory();
		ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474No chyba nie, probujesz sprzedac przedmiot ktorego nawet nie posiadasz?!");
		VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
	}

	private void castByNotEnoughISpace(Player player)
	{
		player.closeInventory();
		ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Powieksz swoje kieszenie bo chyba masz za malo miejsca!");
		VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
	}

	private void castByNoPermission(Player player)
	{
		player.closeInventory();
		ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474A ty slepy czy co! Pisze ze nie ma to nie ma kurwa!");
		VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
	}

	public boolean consumeItem(Player player, int count, Material mat)
	{
		Map<Integer, ? extends ItemStack> ammo = player.getInventory().all(mat);
		int found = 0;

		for (ItemStack stack : ammo.values())
			found += stack.getAmount();

		if (count > found)
			return false;

		for (Integer index : ammo.keySet())
		{
			ItemStack stack = ammo.get(index);

			int removed = Math.min(count, stack.getAmount());
			count -= removed;

			if (stack.getAmount() == removed)
				player.getInventory().setItem(index, null);
			else
				stack.setAmount(stack.getAmount() - removed);

			if (count <= 0)
				break;
		}

		player.updateInventory();
		return true;
	}

	public static void removeItem(Player player, ItemStack itemStack, int amount)
	{
		if (!(player.getInventory().getItemInMainHand().getAmount() < amount))
			player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);

		player.updateInventory();
	}

	private void manageItem(InventoryClickEvent event, Material item, int amount, int buyCost, int sellCost)
	{
		Player player = (Player) event.getWhoClicked();

		if (event.getClick() == ClickType.LEFT)
		{
			if (buyCost == -1)
			{
				castByNoPermission(player);
				return;
			}

			if (DPlayerManager.getInstance().getMoney(player) < buyCost)
			{
				castByNoMoney(player);
				return;
			}

			if (InventoryUtil.isFullInventory(player))
				if (!InventoryUtil.canStackItem(player, item, amount))
				{
					castByNotEnoughISpace(player);
					return;
				}

			DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - buyCost);
			ServerUtil.reloadContents(player);
			player.getInventory().addItem(new ItemStack(item, amount));
			ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie kupiles #ffc936" + item.toString() + "x" + amount + " #8c8c8c(" + buyCost + "⛃)#80ff1f!");
		}
		else if (event.getClick() == ClickType.SHIFT_LEFT)
		{
			if (buyCost == -1)
			{
				castByNoPermission(player);
				return;
			}

			if (DPlayerManager.getInstance().getMoney(player) < (item.getMaxStackSize() / amount) * buyCost)
			{
				castByNoMoney(player);
				return;
			}

			if (InventoryUtil.isFullInventory(player))
			{
				castByNotEnoughISpace(player);
				return;
			}

			DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - (item.getMaxStackSize() / amount) * buyCost);
			ServerUtil.reloadContents(player);
			player.getInventory().addItem(new ItemStack(item, item.getMaxStackSize()));
			ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie kupiles #ffc936" + item.toString() + "x" + item.getMaxStackSize() + " #8c8c8c(" + ((item.getMaxStackSize() / amount) * buyCost) + "⛃)#80ff1f!");
		}
		else if (event.getClick() == ClickType.RIGHT)
		{
			if (sellCost == -1)
			{
				castByNoPermission(player);
				return;
			}

			boolean sell = consumeItem(player, amount, item);

			if (sell)
			{
				ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie sprzedales #ffc936" + item.toString() + "x" + amount + " #8c8c8c(+" + sellCost + "⛃)#80ff1f!");
				DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) + sellCost);
				ServerUtil.reloadContents(player);
			}
			else
				castByNotEnoughItem(player);
		}
		else if (event.getClick() == ClickType.SHIFT_RIGHT)
		{
			if (sellCost == -1)
			{
				castByNoPermission(player);
				return;
			}

			boolean sell = consumeItem(player, item.getMaxStackSize(), item);

			if (sell)
			{
				ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fPomyslnie sprzedales #ffc936" + item.toString() + "x" + item.getMaxStackSize() + " #8c8c8c(+" + ((item.getMaxStackSize() / amount) * sellCost) + "⛃)#80ff1f!");
				DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) + (item.getMaxStackSize() / amount) * sellCost);
				ServerUtil.reloadContents(player);
			}
			else
				castByNotEnoughItem(player);
		}
	}
}