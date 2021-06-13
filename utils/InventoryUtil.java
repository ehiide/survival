package mc.server.survival.utils;

import mc.server.survival.files.Configuration;
import mc.server.survival.files.Main;
import mc.server.survival.items.*;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.managers.DPlayerManager;
import mc.server.survival.managers.FileManager;
import mc.server.survival.managers.GangManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class InventoryUtil 
{
	public static void createNewInventory(Player player, int size, String name, String type)
	{
		Inventory inventory = Bukkit.createInventory(player, size, name);
		fillInventory(inventory);
		
		if (type.equalsIgnoreCase("domki"))
		{
			inventory.setItem(11, getItem(player, "home1"));
			inventory.setItem(13, getItem(player, "homeinfo"));
			inventory.setItem(15, getItem(player, "home2"));
		}
		else if (type.equalsIgnoreCase("schowek"))
		{
			inventory.setItem(11, getItem(player, "schowekinfo"));
			inventory.setItem(15, getItem(player, "schowek1"));
		}
		else if (type.equalsIgnoreCase("pomoc"))
		{
			inventory.setItem(13, getItem(player, "pomocinfo"));
			inventory.setItem(24, getItem(player, "pomoc1"));
			inventory.setItem(25, getItem(player, "pomoc2"));
			inventory.setItem(34, getItem(player, "pomoc3"));
			inventory.setItem(19, getItem(player, "pomoc4"));
			inventory.setItem(20, getItem(player, "pomoc5"));
			inventory.setItem(28, getItem(player, "pomoc6"));
			inventory.setItem(31, getItem(player, "pomoc7"));
		}
		else if (type.equalsIgnoreCase("wyspy"))
		{
			inventory.setItem(40, getItem(player, "wyspyspawn"));
			inventory.setItem(11, getItem(player, "wyspy1"));
			inventory.setItem(12, getItem(player, "wyspy2"));
			inventory.setItem(13, getItem(player, "wyspy3"));
			inventory.setItem(14, getItem(player, "wyspy4"));
			inventory.setItem(15, getItem(player, "wyspy5"));
			inventory.setItem(20, getItem(player, "wyspy6"));
			inventory.setItem(21, getItem(player, "wyspy7"));
			inventory.setItem(22, getItem(player, "wyspy8"));
		}
		else if (type.equalsIgnoreCase("craftingi1"))
		{
			inventory.setItem(44, getItem(player, "arrownext"));
			inventory.setItem(24, new ItemStack(Material.SADDLE));
			inventory.setItem(22, new ItemStack(Material.STRING));
			inventory.setItem(21, null);
			inventory.setItem(20, new ItemStack(Material.STRING));
			inventory.setItem(31, new ItemStack(Material.IRON_INGOT));
			inventory.setItem(30, null);
			inventory.setItem(29, new ItemStack(Material.IRON_INGOT));
			inventory.setItem(13, new ItemStack(Material.LEATHER));
			inventory.setItem(12, new ItemStack(Material.LEATHER));
			inventory.setItem(11, new ItemStack(Material.LEATHER));
		}
		else if (type.equalsIgnoreCase("craftingi2"))
		{
			inventory.setItem(36, getItem(player, "arrowback"));
			inventory.setItem(44, getItem(player, "arrownext"));
			inventory.setItem(24, new ItemStack(Material.NAME_TAG));
			inventory.setItem(22, new ItemStack(Material.PAPER));
			inventory.setItem(21, new ItemStack(Material.PAPER));
			inventory.setItem(20, new ItemStack(Material.PAPER));
			inventory.setItem(31, new ItemStack(Material.STRING));
			inventory.setItem(30, new ItemStack(Material.IRON_INGOT));
			inventory.setItem(29, new ItemStack(Material.STRING));
			inventory.setItem(13, new ItemStack(Material.STRING));
			inventory.setItem(12, new ItemStack(Material.IRON_INGOT));
			inventory.setItem(11, new ItemStack(Material.STRING));
		}
		else if (type.equalsIgnoreCase("craftingi3"))
		{
			inventory.setItem(36, getItem(player, "arrowback"));
			inventory.setItem(44, getItem(player, "arrownext"));
			inventory.setItem(24, new ItemStack(Material.ELYTRA));
			inventory.setItem(22, new ItemStack(Material.NETHERITE_INGOT));
			inventory.setItem(21, new ItemStack(Material.ARMOR_STAND));
			inventory.setItem(20, new ItemStack(Material.NETHERITE_INGOT));
			inventory.setItem(31, new ItemStack(Material.NETHERITE_INGOT));
			inventory.setItem(30, null);
			inventory.setItem(29, new ItemStack(Material.NETHERITE_INGOT));
			inventory.setItem(13, new ItemStack(Material.TRIPWIRE_HOOK));
			inventory.setItem(12, new ItemStack(Material.GHAST_TEAR));
			inventory.setItem(11, new ItemStack(Material.TRIPWIRE_HOOK));
		}
		else if (type.equalsIgnoreCase("craftingi4"))
		{
			inventory.setItem(36, getItem(player, "arrowback"));
			inventory.setItem(44, getItem(player, "arrownext"));
			inventory.setItem(24, new ItemStack(Material.DIAMOND_HORSE_ARMOR));
			inventory.setItem(22, new ItemStack(Material.DIAMOND));
			inventory.setItem(21, new ItemStack(Material.DIAMOND));
			inventory.setItem(20, new ItemStack(Material.DIAMOND));
			inventory.setItem(31, new ItemStack(Material.DIAMOND));
			inventory.setItem(30, null);
			inventory.setItem(29, new ItemStack(Material.DIAMOND));
			inventory.setItem(13, new ItemStack(Material.DIAMOND));
			inventory.setItem(12, new ItemStack(Material.DIAMOND));
			inventory.setItem(11, new ItemStack(Material.DIAMOND));
		}
		else if (type.equalsIgnoreCase("craftingi5"))
		{
			inventory.setItem(36, getItem(player, "arrowback"));
			inventory.setItem(44, getItem(player, "arrownext"));
			inventory.setItem(24, new ItemStack(Material.EXPERIENCE_BOTTLE));
			inventory.setItem(22, new ItemStack(Material.IRON_NUGGET));
			inventory.setItem(21, new ItemStack(Material.GLASS_BOTTLE));
			inventory.setItem(20, new ItemStack(Material.IRON_NUGGET));
			inventory.setItem(31, null);
			inventory.setItem(30, new ItemStack(Material.GLOWSTONE_DUST));
			inventory.setItem(29, null);
			inventory.setItem(13, null);
			inventory.setItem(12, new ItemStack(Material.GLOWSTONE_DUST));
			inventory.setItem(11, null);
		}
		else if (type.equalsIgnoreCase("craftingi6"))
		{
			inventory.setItem(36, getItem(player, "arrowback"));
			inventory.setItem(44, getItem(player, "arrownext"));
			inventory.setItem(24, Chmiel.getItem());
			inventory.setItem(22, new ItemStack(Material.WHEAT));
			inventory.setItem(21, new ItemStack(Material.WHEAT));
			inventory.setItem(20, new ItemStack(Material.WHEAT));
			inventory.setItem(31, new ItemStack(Material.WHEAT));
			inventory.setItem(30, new ItemStack(Material.WHEAT));
			inventory.setItem(29, new ItemStack(Material.WHEAT));
			inventory.setItem(13, new ItemStack(Material.WHEAT));
			inventory.setItem(12, new ItemStack(Material.WHEAT));
			inventory.setItem(11, new ItemStack(Material.WHEAT));
		}
		else if (type.equalsIgnoreCase("craftingi7"))
		{
			inventory.setItem(36, getItem(player, "arrowback"));
			inventory.setItem(24, Piwo.getItem());
			inventory.setItem(22, new ItemStack(Material.SUGAR));
			inventory.setItem(21, new ItemStack(Material.GLASS_BOTTLE));
			inventory.setItem(20, new ItemStack(Material.SUGAR));
			inventory.setItem(31, null);
			inventory.setItem(30, Chmiel.getItem());
			inventory.setItem(29, null);
			inventory.setItem(13, null);
			inventory.setItem(12, Chmiel.getItem());
			inventory.setItem(11, null);
		}
		else if (type.equalsIgnoreCase("paleta"))
		{
			inventory.setItem(4, new ItemStack(Material.PINK_STAINED_GLASS_PANE));
			inventory.setItem(9, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
			inventory.setItem(11, new ItemStack(Material.RED_STAINED_GLASS_PANE));
			inventory.setItem(13, getItem(player, "paletainfo"));
			inventory.setItem(15, new ItemStack(Material.YELLOW_STAINED_GLASS_PANE));
			inventory.setItem(17, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
			inventory.setItem(22, new ItemStack(Material.BLUE_STAINED_GLASS_PANE));
		}
		else if (type.equalsIgnoreCase("sklep"))
		{
			inventory.setItem(13, getItem(player, "sklepprzedmioty"));
			inventory.setItem(20, getItem(player, "sklepefekty"));
			inventory.setItem(24, getItem(player, "sklepgangi"));
			inventory.setItem(31, getItem(player, "sklepdodatki"));
		}
		else if (type.equalsIgnoreCase("sklep_efekty"))
		{
			inventory.setItem(10, getItem(player, "sklepspeed"));
			inventory.setItem(11, getItem(player, "sklepjumpboost"));
			inventory.setItem(12, getItem(player, "sklepresistance"));
			inventory.setItem(13, getItem(player, "sklepstrength"));
			inventory.setItem(14, getItem(player, "sklephaste"));
			inventory.setItem(15, getItem(player, "sklepfireresistance"));
			inventory.setItem(16, getItem(player, "sklepregeneration"));
			inventory.setItem(21, getItem(player, "sklepinvis"));
			inventory.setItem(22, getItem(player, "sklepwaterbreathing"));
			inventory.setItem(23, getItem(player, "sklepnightvision"));
			
			inventory.setItem(40, getItem(player, "powrot"));
		}
		else if (type.equalsIgnoreCase("sklep_dodatki"))
		{
			inventory.setItem(10, getItem(player, "sklepkolor1"));
			inventory.setItem(11, getItem(player, "sklepkolor2"));
			inventory.setItem(12, getItem(player, "sklepkolor3"));
			inventory.setItem(19, getItem(player, "sklepkolor4"));
			inventory.setItem(20, getItem(player, "sklepkolor5"));
			inventory.setItem(21, getItem(player, "sklepkolor6"));
			inventory.setItem(28, getItem(player, "sklepkolor7"));
			inventory.setItem(29, getItem(player, "sklepkolor8"));
			inventory.setItem(14, getItem(player, "sklepschowek"));
			inventory.setItem(23, getItem(player, "sklepvoucher"));
			inventory.setItem(16, getItem(player, "sklepslub"));
			inventory.setItem(40, getItem(player, "powrot"));
		}
		else if (type.equalsIgnoreCase("sklep_gang"))
		{
			if (GangManager.getInstance().getGang(player) == null)
			{
				inventory.setItem(22, getItem(player, "sklepnogang"));
			}
			else
			{
				inventory.setItem(10, getItem(player, "sklepgang1"));
				inventory.setItem(11, getItem(player, "sklepgang2"));
				inventory.setItem(12, getItem(player, "sklepgang3"));
				inventory.setItem(19, getItem(player, "sklepgang4"));
				inventory.setItem(20, getItem(player, "sklepgang5"));
				inventory.setItem(21, getItem(player, "sklepgang6"));
				inventory.setItem(28, getItem(player, "sklepgang7"));
				inventory.setItem(29, getItem(player, "sklepgang8"));
				inventory.setItem(14, getItem(player, "sklepgangp1"));
				inventory.setItem(23, getItem(player, "sklepgangp2"));
				inventory.setItem(32, getItem(player, "sklepgangp3"));
				inventory.setItem(16, getItem(player, "sklepgangstar"));
				inventory.setItem(25, getItem(player, "sklepgangchat"));
			}

			inventory.setItem(40, getItem(player, "powrot"));
		}
		else if (type.equalsIgnoreCase("sklep_przedmioty"))
		{
			inventory.setItem(9, getItem(player, "sklepitemy"));
			inventory.setItem(18, getItem(player, "sklepksiazki"));
			inventory.setItem(27, getItem(player, "sklepspecjalne"));

			inventory.setItem(22, getItem(player, "sklepinfo"));

			inventory.setItem(49, getItem(player, "powrot"));
			inventory.setItem(26, getItem(player, "sklepkupno"));
		}
		else if (type.equalsIgnoreCase("sklep_przedmioty_itemy"))
		{
			inventory.setItem(9, getItem(player, "sklepitemyselected"));
			inventory.setItem(18, getItem(player, "sklepksiazki"));
			inventory.setItem(27, getItem(player, "sklepspecjalne"));

			inventory.setItem(11, getItem(player, "sklepitemy1"));
			inventory.setItem(12, getItem(player, "sklepitemy2"));
			inventory.setItem(13, getItem(player, "sklepitemy3"));
			inventory.setItem(14, getItem(player, "sklepitemy4"));
			inventory.setItem(15, getItem(player, "sklepitemy5"));
			inventory.setItem(20, getItem(player, "sklepitemy6"));
			inventory.setItem(21, getItem(player, "sklepitemy7"));
			inventory.setItem(22, getItem(player, "sklepitemy8"));
			inventory.setItem(23, getItem(player, "sklepitemy9"));
			inventory.setItem(24, getItem(player, "sklepitemy10"));
			inventory.setItem(29, getItem(player, "sklepitemy11"));
			inventory.setItem(30, getItem(player, "sklepitemy12"));
			inventory.setItem(31, getItem(player, "sklepitemy13"));
			inventory.setItem(32, getItem(player, "sklepitemy14"));
			inventory.setItem(33, getItem(player, "sklepitemy15"));

			inventory.setItem(45, getItem(player, "arrowback"));
			inventory.setItem(53, getItem(player, "arrownext"));
			inventory.setItem(49, getItem(player, "powrot"));
			inventory.setItem(26, getItem(player, "sklepkupno"));
		}
		else if (type.equalsIgnoreCase("sklep_przedmioty_itemy2"))
		{
			inventory.setItem(9, getItem(player, "sklepitemyselected"));
			inventory.setItem(18, getItem(player, "sklepksiazki"));
			inventory.setItem(27, getItem(player, "sklepspecjalne"));

			inventory.setItem(11, getItem(player, "sklepitemy16"));
			inventory.setItem(12, getItem(player, "sklepitemy17"));
			inventory.setItem(13, getItem(player, "sklepitemy18"));
			inventory.setItem(14, getItem(player, "sklepitemy19"));
			inventory.setItem(15, getItem(player, "sklepitemy20"));
			inventory.setItem(20, getItem(player, "sklepitemy21"));
			inventory.setItem(21, getItem(player, "sklepitemy22"));
			inventory.setItem(22, getItem(player, "sklepitemy23"));
			inventory.setItem(23, getItem(player, "sklepitemy24"));
			inventory.setItem(24, getItem(player, "sklepitemy25"));
			inventory.setItem(29, getItem(player, "sklepitemy26"));
			inventory.setItem(30, getItem(player, "sklepitemy27"));
			inventory.setItem(31, getItem(player, "sklepitemy28"));
			inventory.setItem(32, getItem(player, "sklepitemy29"));
			inventory.setItem(33, getItem(player, "sklepitemy30"));

			inventory.setItem(45, getItem(player, "arrowback"));
			inventory.setItem(53, getItem(player, "arrownext"));
			inventory.setItem(49, getItem(player, "powrot"));
			inventory.setItem(26, getItem(player, "sklepkupno"));
		}
		else if (type.equalsIgnoreCase("sklep_przedmioty_itemy3"))
		{
			inventory.setItem(9, getItem(player, "sklepitemyselected"));
			inventory.setItem(18, getItem(player, "sklepksiazki"));
			inventory.setItem(27, getItem(player, "sklepspecjalne"));

			inventory.setItem(11, getItem(player, "sklepitemy31"));
			inventory.setItem(12, getItem(player, "sklepitemy32"));
			inventory.setItem(13, getItem(player, "sklepitemy33"));
			inventory.setItem(14, getItem(player, "sklepitemy34"));
			inventory.setItem(15, getItem(player, "sklepitemy35"));
			inventory.setItem(20, getItem(player, "sklepitemy36"));
			inventory.setItem(21, getItem(player, "sklepitemy37"));
			inventory.setItem(22, getItem(player, "sklepitemy38"));
			inventory.setItem(23, getItem(player, "sklepitemy39"));
			inventory.setItem(24, getItem(player, "sklepitemy40"));
			inventory.setItem(29, getItem(player, "sklepitemy41"));
			inventory.setItem(30, getItem(player, "sklepitemy42"));
			inventory.setItem(31, getItem(player, "sklepitemy43"));
			inventory.setItem(32, getItem(player, "sklepitemy44"));
			inventory.setItem(33, getItem(player, "sklepitemy45"));

			inventory.setItem(45, getItem(player, "arrowback"));
			inventory.setItem(53, getItem(player, "arrownext"));
			inventory.setItem(49, getItem(player, "powrot"));
			inventory.setItem(26, getItem(player, "sklepkupno"));
		}
		else if (type.equalsIgnoreCase("sklep_przedmioty_itemy4"))
		{
			inventory.setItem(9, getItem(player, "sklepitemyselected"));
			inventory.setItem(18, getItem(player, "sklepksiazki"));
			inventory.setItem(27, getItem(player, "sklepspecjalne"));

			inventory.setItem(11, getItem(player, "sklepitemy46"));
			inventory.setItem(12, getItem(player, "sklepitemy47"));
			inventory.setItem(13, getItem(player, "sklepitemy48"));
			inventory.setItem(14, getItem(player, "sklepitemy49"));
			inventory.setItem(15, getItem(player, "sklepitemy50"));
			inventory.setItem(20, getItem(player, "sklepitemy51"));
			inventory.setItem(21, getItem(player, "sklepitemy52"));
			inventory.setItem(22, getItem(player, "sklepitemy53"));
			inventory.setItem(23, getItem(player, "sklepitemy54"));
			inventory.setItem(24, getItem(player, "sklepitemy55"));
			inventory.setItem(29, getItem(player, "sklepitemy56"));
			inventory.setItem(30, getItem(player, "sklepitemy57"));
			inventory.setItem(31, getItem(player, "sklepitemy58"));
			inventory.setItem(32, getItem(player, "sklepitemy59"));
			inventory.setItem(33, getItem(player, "sklepitemy60"));

			inventory.setItem(45, getItem(player, "arrowback"));
			inventory.setItem(53, getItem(player, "arrownext"));
			inventory.setItem(49, getItem(player, "powrot"));
			inventory.setItem(26, getItem(player, "sklepkupno"));
		}
		else if (type.equalsIgnoreCase("sklep_przedmioty_itemy5"))
		{
			inventory.setItem(9, getItem(player, "sklepitemyselected"));
			inventory.setItem(18, getItem(player, "sklepksiazki"));
			inventory.setItem(27, getItem(player, "sklepspecjalne"));

			inventory.setItem(11, getItem(player, "sklepitemy61"));
			inventory.setItem(12, getItem(player, "sklepitemy62"));
			inventory.setItem(13, getItem(player, "sklepitemy63"));
			inventory.setItem(14, getItem(player, "sklepitemy64"));
			inventory.setItem(15, getItem(player, "sklepitemy65"));
			inventory.setItem(20, getItem(player, "sklepitemy66"));
			inventory.setItem(21, getItem(player, "sklepitemy67"));
			inventory.setItem(22, getItem(player, "sklepitemy68"));
			inventory.setItem(23, getItem(player, "sklepitemy69"));
			inventory.setItem(24, getItem(player, "sklepitemy70"));
			inventory.setItem(29, getItem(player, "sklepitemy71"));
			inventory.setItem(30, getItem(player, "sklepitemy72"));
			inventory.setItem(31, getItem(player, "sklepitemy73"));
			inventory.setItem(32, getItem(player, "sklepitemy74"));
			inventory.setItem(33, getItem(player, "sklepitemy75"));

			inventory.setItem(45, getItem(player, "arrowback"));
			inventory.setItem(53, getItem(player, "arrownext"));
			inventory.setItem(49, getItem(player, "powrot"));
			inventory.setItem(26, getItem(player, "sklepkupno"));
		}
		else if (type.equalsIgnoreCase("sklep_przedmioty_itemy6"))
		{
			inventory.setItem(9, getItem(player, "sklepitemyselected"));
			inventory.setItem(18, getItem(player, "sklepksiazki"));
			inventory.setItem(27, getItem(player, "sklepspecjalne"));

			inventory.setItem(11, getItem(player, "sklepitemy76"));
			inventory.setItem(12, getItem(player, "sklepitemy77"));
			inventory.setItem(13, getItem(player, "sklepitemy78"));
			inventory.setItem(14, getItem(player, "sklepitemy79"));
			inventory.setItem(15, getItem(player, "sklepitemy80"));
			inventory.setItem(20, getItem(player, "sklepitemy81"));
			inventory.setItem(21, getItem(player, "sklepitemy82"));
			inventory.setItem(22, getItem(player, "sklepitemy83"));

			inventory.setItem(45, getItem(player, "arrowback"));
			inventory.setItem(53, getItem(player, "arrownext"));
			inventory.setItem(49, getItem(player, "powrot"));
			inventory.setItem(26, getItem(player, "sklepkupno"));
		}
		else if (type.equalsIgnoreCase("sklep_przedmioty_ksiazki"))
		{
			inventory.setItem(9, getItem(player, "sklepitemy"));
			inventory.setItem(18, getItem(player, "sklepksiazkiselected"));
			inventory.setItem(27, getItem(player, "sklepspecjalne"));

			inventory.setItem(11, getItem(player, "sklepksiazki1"));
			inventory.setItem(12, getItem(player, "sklepksiazki2"));
			inventory.setItem(13, getItem(player, "sklepksiazki3"));
			inventory.setItem(14, getItem(player, "sklepksiazki4"));
			inventory.setItem(15, getItem(player, "sklepksiazki5"));
			inventory.setItem(20, getItem(player, "sklepksiazki6"));
			inventory.setItem(21, getItem(player, "sklepksiazki7"));
			inventory.setItem(22, getItem(player, "sklepksiazki8"));
			inventory.setItem(23, getItem(player, "sklepksiazki9"));
			inventory.setItem(24, getItem(player, "sklepksiazki10"));
			inventory.setItem(29, getItem(player, "sklepksiazki11"));
			inventory.setItem(30, getItem(player, "sklepksiazki12"));
			inventory.setItem(31, getItem(player, "sklepksiazki13"));
			inventory.setItem(32, getItem(player, "sklepksiazki14"));
			inventory.setItem(33, getItem(player, "sklepksiazki15"));

			inventory.setItem(45, getItem(player, "arrowback"));
			inventory.setItem(53, getItem(player, "arrownext"));
			inventory.setItem(49, getItem(player, "powrot"));
			inventory.setItem(26, getItem(player, "sklepkupno"));
		}
		else if (type.equalsIgnoreCase("sklep_przedmioty_ksiazki2"))
		{
			inventory.setItem(9, getItem(player, "sklepitemy"));
			inventory.setItem(18, getItem(player, "sklepksiazkiselected"));
			inventory.setItem(27, getItem(player, "sklepspecjalne"));

			inventory.setItem(11, getItem(player, "sklepksiazki16"));
			inventory.setItem(12, getItem(player, "sklepksiazki17"));
			inventory.setItem(13, getItem(player, "sklepksiazki18"));
			inventory.setItem(14, getItem(player, "sklepksiazki19"));
			inventory.setItem(15, getItem(player, "sklepksiazki20"));
			inventory.setItem(20, getItem(player, "sklepksiazki21"));
			inventory.setItem(21, getItem(player, "sklepksiazki22"));
			inventory.setItem(22, getItem(player, "sklepksiazki23"));
			inventory.setItem(23, getItem(player, "sklepksiazki24"));

			inventory.setItem(45, getItem(player, "arrowback"));
			inventory.setItem(53, getItem(player, "arrownext"));
			inventory.setItem(49, getItem(player, "powrot"));
			inventory.setItem(26, getItem(player, "sklepkupno"));
		}
		else if (type.equalsIgnoreCase("sklep_przedmioty_specjalne"))
		{
			inventory.setItem(9, getItem(player, "sklepitemy"));
			inventory.setItem(18, getItem(player, "sklepksiazki"));
			inventory.setItem(27, getItem(player, "sklepspecjalneselected"));

			inventory.setItem(11, getItem(player, "sklepspecjalne1"));
			inventory.setItem(12, getItem(player, "sklepspecjalne2"));
			inventory.setItem(13, getItem(player, "sklepspecjalne3"));
			inventory.setItem(14, getItem(player, "sklepspecjalne4"));
			inventory.setItem(15, getItem(player, "sklepspecjalne5"));

			inventory.setItem(45, getItem(player, "arrowback"));
			inventory.setItem(53, getItem(player, "arrownext"));
			inventory.setItem(49, getItem(player, "powrot"));
			inventory.setItem(26, getItem(player, "sklepkupno"));
		}
        else if (type.equalsIgnoreCase("questy"))
        {
            inventory.setItem(10, getItem(player, "questy1"));
            inventory.setItem(11, getItem(player, "questy2"));
            inventory.setItem(12, getItem(player, "questy3"));
            inventory.setItem(14, getItem(player, "questy4"));
            inventory.setItem(15, getItem(player, "questy5"));
            inventory.setItem(16, getItem(player, "questy6"));

            inventory.setItem(20, getItem(player, "questy7"));
            inventory.setItem(21, getItem(player, "questy8"));
            inventory.setItem(22, getItem(player, "questy9"));
            inventory.setItem(23, getItem(player, "questy10"));
            inventory.setItem(24, getItem(player, "questy11"));

            inventory.setItem(30, getItem(player, "questy12"));
            inventory.setItem(31, getItem(player, "questy13"));
            inventory.setItem(32, getItem(player, "questy14"));
        }
        else if (type.equalsIgnoreCase("postac"))
        {
			inventory.setItem(13, getItem(player, "postacloading"));
			inventory.setItem(20, getItem(player, "postacslub"));
			inventory.setItem(24, getItem(player, "postacgang"));
			inventory.setItem(31, getItem(player, "postacupgrades"));
			inventory.setItem(38, getItem(player, "postacupgrade1"));
			inventory.setItem(39, getItem(player, "postacupgrade2"));
			inventory.setItem(40, getItem(player, "postacupgrade3"));
			inventory.setItem(41, getItem(player, "postacupgrade4"));
			inventory.setItem(42, getItem(player, "postacupgrade5"));

			new BukkitRunnable()
			{
				@Override
				public void run()
				{
					inventory.setItem(13, getItem(player, "postacstatystyki"));
				}
			}.runTaskAsynchronously(Main.getInstance());
        }
		else if (type.equalsIgnoreCase("monopolowy"))
		{
			inventory.setItem(11, Piwo.getItem());
			inventory.setItem(12, Wino.getItem());
			inventory.setItem(13, Szampan.getItem());
			inventory.setItem(14, Whisky.getItem());
			inventory.setItem(15, Wodka.getItem());
			inventory.setItem(20, getItem(player, "monopolowypiwo"));
			inventory.setItem(21, getItem(player, "monopolowywino"));
			inventory.setItem(22, getItem(player, "monopolowyszampan"));
			inventory.setItem(23, getItem(player, "monopolowywhisky"));
			inventory.setItem(24, getItem(player, "monopolowywodka"));
		}
		
		player.openInventory(inventory);
		player.updateInventory();
	}
	
	public static String getName(Player player)
	{
		return player.getOpenInventory().getTitle();
	}
	
	private static void fillInventory(Inventory inventory) 
	{
		int size = inventory.getSize();

		for (int slot = 0; slot < size; slot++)
		{
			inventory.setItem(slot, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
		}
	}
	
	public static ItemStack getItem(Player player, String name)
	{
		if (name.equalsIgnoreCase("home1"))
		{
			ArrayList<String> lore = new ArrayList<String>();
			String isExist = (String) FileManager.getInstance().configuration().get(DPlayerManager.getDPlayer(player.getName()) + ".data.home1.world");

			if (isExist.equalsIgnoreCase("none"))
			{
				String[] no = {"&c&l» &a&lDOM 1", "", "&c&l» &aWspolrzedne:", " &8> &7Swiat : <brak danych>", " &8> &7X : <brak danych>"
						, " &8> &7Y : <brak danych>", " &8> &7Z : <brak danych>", " &8> &7Yaw : <brak danych>", " &8> &7Pitch : <brak danych>"
						, "", ColorUtil.formatHEX("   #666666(LPM - Teleportacja do domku"), ColorUtil.formatHEX("   #666666PPM - Zmiana miejsca polozenia domku)"), ""};
				
				for (String vers : no)
				{
					lore.add(ChatColor.translateAlternateColorCodes('&', vers));
				}
			}
			else
			{
				String[] yes  = {"&c&l» &a&lDOM 1", "", "&c&l» &aWspolrzedne:", " &8> &7Swiat : " + Objects.requireNonNull(DPlayerManager.getInstance().getHome(player, "1").getWorld()).getName(), " &8> &7X : " + DPlayerManager.getInstance().getHome(player, "1").getX()
						, " &8> &7Y : " + DPlayerManager.getInstance().getHome(player, "1").getY(), " &8> &7Z : " + + DPlayerManager.getInstance().getHome(player, "1").getZ(), " &8> &7Yaw : " + DPlayerManager.getInstance().getHome(player, "1").getYaw(), " &8> &7Pitch : " + DPlayerManager.getInstance().getHome(player, "1").getPitch()
						, "", ColorUtil.formatHEX("   #666666(LPM - Teleportacja do domku"), ColorUtil.formatHEX("   #666666PPM - Zmiana miejsca polozenia domku)"), ""};
				
				for (String vers : yes)
				{
					lore.add(ChatColor.translateAlternateColorCodes('&', vers));
				}
			}
			
			ItemStack item = new ItemStack(Material.OAK_DOOR);
			ItemMeta meta = item.getItemMeta();

            meta.setDisplayName(" ");
			meta.setLore(lore);
			item.setItemMeta(meta);
			
			return item;
		}
		
		if (name.equalsIgnoreCase("home2"))
		{
			ArrayList<String> lore = new ArrayList<String>();
			String isExist = (String) FileManager.getInstance().configuration().get(DPlayerManager.getDPlayer(player.getName()) + ".data.home2.world");
			
			if (isExist.equalsIgnoreCase("none"))
			{
				String[] no = {"&c&l» &a&lDOM 2", "", "&c&l» &aWspolrzedne:", " &8> &7Swiat : <brak danych>", " &8> &7X : <brak danych>"
						, " &8> &7Y : <brak danych>", " &8> &7Z : <brak danych>", " &8> &7Yaw : <brak danych>", " &8> &7Pitch : <brak danych>"
						, "", ColorUtil.formatHEX("   #666666(LPM - Teleportacja do domku"), ColorUtil.formatHEX("   #666666PPM - Zmiana miejsca polozenia domku)"), ""};
				
				for (String vers : no)
				{
					lore.add(ChatColor.translateAlternateColorCodes('&', vers));
				}
			}
			else
			{
				String[] yes  = {"&c&l» &a&lDOM 2", "", "&c&l» &aWspolrzedne:", " &8> &7Swiat : " + DPlayerManager.getInstance().getHome(player, "2").getWorld().getName(), " &8> &7X : " + DPlayerManager.getInstance().getHome(player, "2").getX()
						, " &8> &7Y : " + DPlayerManager.getInstance().getHome(player, "2").getY(), " &8> &7Z : " + + DPlayerManager.getInstance().getHome(player, "2").getZ(), " &8> &7Yaw : " + DPlayerManager.getInstance().getHome(player, "2").getYaw(), " &8> &7Pitch : " + DPlayerManager.getInstance().getHome(player, "2").getPitch()
						, "", ColorUtil.formatHEX("   #666666(LPM - Teleportacja do domku"), ColorUtil.formatHEX("   #666666PPM - Zmiana miejsca polozenia domku)"), ""};
				
				for (String vers : yes)
				{
					lore.add(ChatColor.translateAlternateColorCodes('&', vers));
				}
			}
			
			ItemStack item = new ItemStack(Material.OAK_DOOR);
			ItemMeta meta = item.getItemMeta();
			
			meta.setDisplayName(" ");
			meta.setLore(lore);
			item.setItemMeta(meta);
			
			return item;
		}
		
		if (name.equalsIgnoreCase("homeinfo"))
		{
			String[] lore = {"&c&l» &a&lINFORMACJE", "", "&c&l» &7Domki sluza do szybkiej teleportacji", "   &7miedzy wybranymi lokacjami na swiecie!", "",
							"&c&l» &7Kazdy gracz ma przypisane dwa", "   &7mozliwe do ustawienia i uzycia domki!", ""};
			
			return createInvItem(lore, Material.BOOK, false);
		}

		if (name.equalsIgnoreCase("schowekinfo"))
		{
			String[] lore = {"&c&l» &a&lINFORMACJE", "", "&c&l» &7Za pomoca ulepszenia schowku", "   &7otrzymujesz mozliwosc powiekszenia go!", "",
							"&c&l» &7Kazdy gracz ma poczatkowo 27 slotow,", "   &7ktore moze powiekszyc do az 54!", ""};

			return createInvItem(lore, Material.BOOK, false);
		}
		
		if (name.equalsIgnoreCase("schowek1"))
		{
			String[] lore = {"&c&l» &a&lULEPSZENIE", "", "&c&l» &7Kazdorazowe ulepszenie schowku powieksza", "   &7go o 9 slotow, co jest rowne jednemu paskowi.", "   &7Zakup jest finalny i nie ma mozliwosci cofniecia go!", "", ColorUtil.formatHEX("&c&l» &7Cena: #ffc936200 ⛃"),
					ColorUtil.formatHEX(" &8> &7Aktualny poziom #80ff1f" + (DPlayerManager.getInstance().getSchowekLevel(player) + 1) + "&7/#80ff1f4"), "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Kupno ulepszenia)"), ""};

			return createInvItem(lore, Material.DIAMOND_PICKAXE, false);
		}
		
		if (name.equalsIgnoreCase("pomocinfo"))
		{
			String[] lore = {"&c&l» &a&lCENTRUM POMOCY SERWERA", "", "&c&l» &7Ponizej znajdziesz liste wszystkich", "   &7komend na serwerze, wskazowki i informacje!", "",
							"&c&l» &7Mamy nadzieje, ze uda Ci sie rozwiazac", "   &7problem!", ""};

			return createInvItem(lore, Material.BOOK, true);
		}
		
		if (name.equalsIgnoreCase("pomoc1"))
		{
			String[] lore = {"&c&l» &a&lKOMENDY", "", "&c&l» &7Wiecej informacji o danej komendzie", "   &7znajduje sie w GUI tej komendy, np.",
							"   &7wpisujac &c/dom &7otrzymasz pelen opis!", "", 
							"  &c/&c&lCRAFTINGI&r&7 - wyswietla panel z dodanymi recepturami",
							"  &c/&c&lDOM&r&7 - pozwala ustawic Twoj dom",
							"  &c/&c&lGANG&r&7 - wyswietla sub-komendy systemu gangow",
							"  &c/&c&lPALETA&r&7 - ukazuje dodatkowe informacje o szacie kolorow",
							"  &c/&c&lPING &r&c(gracz)&r&7 - wyswietla Twoj aktualny ping",
							"  &c/&c&lPOMOC&r&7 - wyswietla centrum pomocy serwera",
							"  &c/&c&lPOSTAC&r&7 - wyswietla ulepszenia i statystyki twojej postaci",
							"  &c/&c&lPOWROT&r&7 - teleportuje gracza w ostatnie miejsce",
							"  &c/&c&lSCHOWEK &r&c(ulepsz) &r&7 - otwiera prywatny schowek gracza",
							"  &c/&c&lSKLEP&r&7 - otwiera menu sklepu serwera",
							"  &c/&c&lSLUB&r&7 - wyswietla sub-komendy systemu slubow",
							"  &c/&c&lSPAWN&r&7 - teleportuje gracza na spawn swiata",
							"  &c/&c&lSWIAT&r&7 - pozwala przenosic sie miedzy swiatami",
							"  &c/&c&lTPA &r&c(gracz/akceptuj/odrzuc) [gracz]&r&7 - teleportacja",
							"  &c/&c&lQUESTY&r&7 - pokazuje dostepne zadania serwerowe",
							"  &c/&c&lWIADOMOSC &r&c(gracz) (tresc)&r&7 - wysysla prywatne wiadomosci",
							"  &c/&c&lWYSPA &r&c(nazwa)&r&7 - teleportuje gracza na wybrana wyspe",
							"  &c/&c&lZAPLAC &r&c(gracz) (ilosc)&r&7 - pozwala przelac pieniadze komus innemu", ""};

			return createInvItem(lore, Material.MAP, false);
		}
		
		if (name.equalsIgnoreCase("pomoc2"))
		{
			String[] lore = {"&c&l» &a&lSWIATY", "", "&c&l» &7Na serwerze istnieja 2 swiaty:", "   &7survivalowy oraz surowcowy, na obydwu",
							"   &7istnieje mozliwosc budowania i pozyskiwania", "   &7przedmiotow, ekspolorowania, itp.", 
							"",
							"&c&l» &7Aby przeniesc sie z jednego na drugi",
							"   &7mozesz uzyc komendy &c/swiat!",
							"",
							"&c&l» &7Mozesz ustawic &c/dom &7w roznych swiatach,",
							"   &7system teleportacji przeniesie Cie nawet",
							"   &7do piwnicy Natali PL!", ""};

			return createInvItem(lore, Material.GRASS_BLOCK, false);
		}

		if (name.equalsIgnoreCase("pomoc3"))
		{
			String[] lore = {"&c&l» &a&lPLACEHOLDERS", "", "&c&l» &7Jesli nie wiesz czegos o sobie, badz", "   &7chcesz komus cos szybko przekazac, mozesz",
					"   &7uzyc zamiennikow czatowych. Ponizej znajdziesz", "   &7ich liste:",
					"",
					"   &c%swiat%&7 - wyswietla aktualny swiat, w jakim sie znajdujesz",
					"   &c%xyz%&7 - pokazuje Twoje aktualne koordynaty",
					"   &c%ping%&7 - wyswietla Twoj aktualny ping",
					"   &c%monety%&7 - wyswietla ilosc monet jaka posiadasz",
					"   &c%zabojstwa%&7 - ukazuje liczbe zabitych przez Ciebie osob",
					"   &c%smierci%&7 - pokazuje ile razy odszedles z tego swiata",
					"   &c%item%&7 - pokazuje aktualnie trzymany przedmiot",
					"   &c%itemmeta%&7 - wyswietla szczegolowe informacje o",
					"   &7aktualnie trzymanym przedmiocie", ""};

			return createInvItem(lore, Material.OAK_TRAPDOOR, false);
		}
		
		if (name.equalsIgnoreCase("pomoc4"))
		{
			String[] lore = {"&c&l» &a&lREGULAMIN", "", "&c&l» &7Zaczniejmy od podstawowej hierarchii.", "   &7Administracja ma zawsze racje, chyba ze jest", "   &7pijana!",
							"",
							"&c&l» &7Milosc, przyjazn i muzyka - mowiac prosciej", "   &7postaraj sie przestrzegac podstawowych zasad", "   &7etyki, &minaczej ci zapierdole!",
							"",
							"&c&l» &7Serwer posiada wbudowany system anty-toxic,", "   &7ktory z wysoka skutecznoscia upokorzy tych", "   &7najbardziej wrazliwych, spam oraz flooding nie", "   &7sa mu straszne!",
							"",
							"&c&l» &7Do tych na odlocie - cheaty oraz inne rodzaje", "   &7wspomagaczy sa surowo zakazane! Griefing podlega", "   &7rowniez temu podpunktowi!", ""};

			return createInvItem(lore, Material.NETHER_STAR, false);
		}

		if (name.equalsIgnoreCase("pomoc5"))
		{
			String[] lore = {"&c&l» &a&lTARYFIKATOR", "", "&c&l» &7Nie ma dokladnie ustalonych kar za", "   &7zlamanie regulaminu, kazdorazowe jego nieprzestrzeganie", "   &7skutkuje nadanie jednej z 3 mozliwych pokut!",
			"",
			"&c&l» &e/mute &7jest lagodnym wymiarem kary, ktory", "   &7blokuje ci mozliwosc udzielania sie na czacie", "   &7na okreslony czas!",
			"",
			"&c&l» &e/kick &7jest lagodnym wymiarem kary, ktory", "   &7powoduje wyrzucenie cie z serwera!",
			"",
			"&c&l» &e/ban &7jest surowym wymiarem kary, ktory", "   &7uniemozliwia ci granie na serwerze!", ""};

			return createInvItem(lore, Material.DIAMOND_AXE, false);
		}

		if (name.equalsIgnoreCase("pomoc6"))
		{
			String[] lore = {"&c&l» &a&lWHITELISTA", "", "&c&l» &7Aby dolaczyc na serwer musisz znajdowac", "   &7sie na liscie osob do tego opowaznionych -", "   &7tzw. whitelist'y. Jezeli chcesz dodac na nia", "   &7jakas osobe napisz do nas na discordzie!", ""};

			return createInvItem(lore, Material.PAPER, false);
		}

		if (name.equalsIgnoreCase("pomoc7"))
		{
			String[] lore = {"&c&l» &a&lZGLASZANIE PROBLEMOW", "", "&c&l» &7Aby zglosic problem techniczny lub", "   &7zapisac jakas osobe na serwerowej", "   &7whiteliscie skontaktuj sie z &eEhide&a#7158&7!", "",
					"&c&l» &7Aby zglosic problem z graczem, np.", "   &7griefing, nieprzestrzeganie regulaminu,", "   &7itp. skontaktuj sie z &eProsek&a#1307&7!", "",
					ColorUtil.formatHEX("   #666666&o(W przypadku braku odpowiedzi, zglos"), ColorUtil.formatHEX("   #666666&oproblem bezposrednio na serwerze)"), ""};

			return createInvItem(lore, Material.EMERALD, false);
		}
		
		if (name.equalsIgnoreCase("wyspyspawn"))
		{
			String[] lore = {"&c&l» &a&lWYSPA GLOWNA (SPAWN)", "", "&c&l» &7Glowna wyspa, na ktorej pojawia", "   &7sie kazdy nowy gracz!",
							"",
							"   &8&o(LPM/PPM - Teleportacja. Mozesz takze",
							"   &8&ouzyc komendy /spawn)", ""};

			return createInvItem(lore, Material.NETHER_STAR, false);
		}
		
		if (name.equalsIgnoreCase("wyspy1"))
		{
			String[] lore = {"&c&l» &a&lWYSPA DZUNGLOWA", "", "&c&l» &7Winorosla, wysokie, siegajace chmur drzewa", "   &7oraz czysta dzika przyroda!",
					"",
					"   &8&o(LPM/PPM - Teleportacja. Mozesz takze",
					"   &8&ouzyc komendy /wyspa dzungla)", ""};

			return createInvItem(lore, Material.COCOA_BEANS, false);
		}
		
		if (name.equalsIgnoreCase("wyspy2"))
		{
			String[] lore = {"&c&l» &a&lWYSPA GRZYBOWA", "", "&c&l» &7Lubisz grzyby? Ta wyspa jest", "   &7dla Ciebie idealnym miejscem!",
					"",
					"   &8&o(LPM/PPM - Teleportacja. Mozesz takze",
					"   &8&ouzyc komendy /wyspa grzybowa)", ""};

			return createInvItem(lore, Material.RED_MUSHROOM, false);
		}
		
		if (name.equalsIgnoreCase("wyspy3"))
		{
			String[] lore = {"&c&l» &a&lWYSPA LESNA", "", "&c&l» &7Znajdziesz tam kazdy rodzaj drzewa", "   &7o jakim tylko sobie marzysz!",
					"",
					"   &8&o(LPM/PPM - Teleportacja. Mozesz takze",
					"   &8&ouzyc komendy /wyspa las)", ""};

			return createInvItem(lore, Material.OAK_SAPLING, false);
		}
		
		if (name.equalsIgnoreCase("wyspy4"))
		{
			String[] lore = {"&c&l» &a&lWYSPA LODOWA", "", "&c&l» &7Naturalne, zimowe klimaty, zielone pokryte", "   &7sniegiem choinki oraz Ty ze sniezkami!",
					"",
					"   &8&o(LPM/PPM - Teleportacja. Mozesz takze",
					"   &8&ouzyc komendy /wyspa lodowa)", ""};

			return createInvItem(lore, Material.PACKED_ICE, false);
		}
		
		if (name.equalsIgnoreCase("wyspy5"))
		{
			String[] lore = {"&c&l» &a&lWYSPA SAVANNA", "", "&c&l» &7Afrykanskie upaly, egzotyczne zwierzeta,", "   &7i piekne oazy, czego chciec wiecej!",
					"",
					"   &8&o(LPM/PPM - Teleportacja. Mozesz takze",
					"   &8&ouzyc komendy /wyspa savanna)", ""};

			return createInvItem(lore, Material.DEAD_BUSH, false);
		}
		
		if (name.equalsIgnoreCase("wyspy6"))
		{
			String[] lore = {"&c&l» &a&lWYSPA GORZYSTA", "", "&c&l» &7Ulubiony biom franeueue, a w nim wysokie", "   &7szczyty i piekne krajobrazy!",
					"",
					"   &8&o(LPM/PPM - Teleportacja. Mozesz takze",
					"   &8&ouzyc komendy /wyspa gory)", ""};

			return createInvItem(lore, Material.STONE, false);
		}
		
		if (name.equalsIgnoreCase("wyspy7"))
		{
			String[] lore = {"&c&l» &a&lWYSPA PUSTYNNA", "", "&c&l» &7Wielka wyspa pokryta milionami blokow", "   &7piasku, piramidy oraz nieliczne strumyki!",
					"",
					"   &8&o(LPM/PPM - Teleportacja. Mozesz takze",
					"   &8&ouzyc komendy /wyspy pustynna)", ""};

			return createInvItem(lore, Material.CACTUS, false);
		}
		
		if (name.equalsIgnoreCase("wyspy8"))
		{
			String[] lore = {"&c&l» &a&lWYSPA MESSA", "", "&c&l» &7Przepiekne kaniony, kolorowe wzgorza", "   &7oraz zachody slonca!",
					"",
					"   &8&o(LPM/PPM - Teleportacja. Mozesz takze",
					"   &8&ouzyc komendy /wyspa messa)", ""};

			return createInvItem(lore, Material.RED_SAND, false);
		}
		
		if (name.equalsIgnoreCase("arrownext"))
		{
			String[] lore = {"&c&l» &a&lNASTEPNA STRONA","", "&c&l» &7Kliknij, aby przejsc do nastepnej", "   &7strony w tej zakladce!", "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Przejscie do nastepnej strony)"),""};

			return createInvItem(lore, Material.ARROW, false);
		}
		
		if (name.equalsIgnoreCase("arrowback"))
		{
			String[] lore = {"&c&l» &a&lPOPRZEDNIA STRONA", "", "&c&l» &7Kliknij, aby cofnac sie do poprzedniej", "   &7strony w tej zakladce!", "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Przejscie do poprzedniej strony)"),""};

			return createInvItem(lore, Material.ARROW, false);
		}
		
		if (name.equalsIgnoreCase("powrot"))
		{
			String[] lore = {"&c&l» &a&lPOWROT", "", "&c&l» &7Kliknij, aby cofnac sie do poprzedniej", "   &7zakladki lub zamknac obecne menu!", "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Opuszczenie menu)"),""};

			return createInvItem(lore, Material.OAK_DOOR, false);
		}
		
		if (name.equalsIgnoreCase("paletainfo"))
		{
			String[] lore = {"&c&l» &a&lJAK UZYWAC 16.7M KOLOROW?", "", "&c&l» &7Tworzenie koloru polega na wpisaniu", "   &7go w postaci koloru HEX. Jak to zrobic?",
					"   &7Wpisz w Google fraze &o'hex color'&r&7, a nastepnie", "   &7wybierz swoj kolor i skopiuj jego postac HEX.", "", "&c&l» &7Przyklady takich kolorow to na przyklad:",
					"   &e#F83044&7, &e#8C8C8C &7czy &e#03FC41", "", "&c&l» &7Przykladowa wiadomosc: &e#03fc41Czesc!", ColorUtil.formatHEX(" &8> &7Finalny widok: #03fc41Czesc!"), ""};

			return createInvItem(lore, Material.BOOK, false);
		}
		
		if (name.equalsIgnoreCase("sklepprzedmioty"))
		{
			String[] lore = {"&c&l» &a&lPRZEDMIOTY", "", "&c&l» &7W sklepie z przedmiotami mozesz nabyc", "   &7prestizowe przedmioty specjalne, ksiazki z enchantami,",
					"   &7mineraly oraz wiele innych blokow i surowcow!", "", ColorUtil.formatHEX("   #666666&o(LPM/PPM - Wybranie zakladki)"), ""};

			return createInvItem(lore, Material.GRASS_BLOCK, false);
		}
		
		if (name.equalsIgnoreCase("sklepefekty"))
		{
			String[] lore = {"&c&l» &a&lEFEKTY", "", "&c&l» &7W sklepie z efektami czekaja na Ciebie", "   &7mozliwe do zakupu efekty wielu mikstur,",
					"   &7ktore mozesz zakupic na pewien okres czasu!", "", ColorUtil.formatHEX("   #666666&o(LPM/PPM - Wybranie zakladki)"), ""};

			return createInvItem(lore, Material.HONEY_BOTTLE, false);
		}
		
		if (name.equalsIgnoreCase("sklepgangi"))
		{
			String[] lore = {"&c&l» &a&lGANGI", "", "&c&l» &7Specjalna zakladka, w ktorej znajduja sie", "   &7ulepszenia do Twojego gangu!", "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Wybranie zakladki)"), ""};

			return createInvItem(lore, Material.DIAMOND_SWORD, false);
		}

		if (name.equalsIgnoreCase("sklepdodatki"))
		{
			String[] lore = {"&c&l» &a&lDODATKI", "", "&c&l» &7W sklepie z dodatkami mozesz zakupic", "   &7kosmetyki oraz ulepszenia tylko dla Ciebie!",
					"   &7Jesli interesuje Cie zmiana koloru nicku,", "   &7mozliwosc powiekszenia pojemnosci swojego", "   &7schowku, itp. to nie czekaj i klikaj!", "", ColorUtil.formatHEX("   #666666&o(LPM/PPM - Wybranie zakladki)"), ""};

			return createInvItem(lore, Material.NETHER_STAR, false);
		}
		
		if (name.equalsIgnoreCase("sklepspeed"))
		{
			ArrayList<String> lore = new ArrayList<String>();
			String[] text = {"&c&l» &a&lSZYBKOSC", "", "&c&l» &7Mozesz zakupic i nalozyc na siebie", "   &7jeden z 10 dostepnych efektow mikstur. Pamietaj",
					"   &7ze wchodza one w interakcje z pewnymi substancjami,",
					"   &7wiec nie zaleca sie ich sumowania!", "", "&c&l» &7Efekt: &bSzybkosc", " &8> &7Nasilenie: 1",
					" &8> &7Czas dzialania: 3m/8m/30m", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203300 ⛃&7/#fff203600 ⛃&7/#fff2031800 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno efektu na okres 3 minut"), ColorUtil.formatHEX("   #666666&o(SHIFT + LPM - Kupno efektu na okres 8 minut"),
					ColorUtil.formatHEX("   #666666&o(SCROLL - Kupno efektu na okres 30 minut"), ""};;
			
			for (String vers : text)
			{
				lore.add(ChatColor.translateAlternateColorCodes('&', vers));
			}
			ItemStack item = new ItemStack(Material.SPLASH_POTION);
			ItemMeta meta = item.getItemMeta();
			PotionMeta potion = (PotionMeta) meta;
			potion.setColor(Color.AQUA);
			potion.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			potion.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
			potion.setBasePotionData(new PotionData(PotionType.WATER));
			
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', " "));
			meta.setLore(lore);
			item.setItemMeta(potion);
			
			return item;
		}
		
		if (name.equalsIgnoreCase("sklepjumpboost"))
		{
			ArrayList<String> lore = new ArrayList<String>();
			String[] text = {"&c&l» &a&lWYSOKI SKOK", "", "&c&l» &7Mozesz zakupic i nalozyc na siebie", "   &7jeden z 10 dostepnych efektow mikstur. Pamietaj",
					"   &7ze wchodza one w interakcje z pewnymi substancjami,",
					"   &7wiec nie zaleca sie ich sumowania!", "", "&c&l» &7Efekt: &aWysoki skok", " &8> &7Nasilenie: 4",
					" &8> &7Czas dzialania: 3m/8m/30m", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203300 ⛃&7/#fff203600 ⛃&7/#fff2031800 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno efektu na okres 3 minut"), ColorUtil.formatHEX("   #666666&o(SHIFT + LPM - Kupno efektu na okres 8 minut"),
					ColorUtil.formatHEX("   #666666&o(SCROLL - Kupno efektu na okres 30 minut"), ""};;
			
			for (String vers : text)
			{
				lore.add(ChatColor.translateAlternateColorCodes('&', vers));
			}
			ItemStack item = new ItemStack(Material.SPLASH_POTION);
			ItemMeta meta = item.getItemMeta();
			PotionMeta potion = (PotionMeta) meta;
			potion.setColor(Color.GREEN);
			potion.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			potion.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
			potion.setBasePotionData(new PotionData(PotionType.WATER));
			
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', " "));
			meta.setLore(lore);
			item.setItemMeta(potion);
			
			return item;
		}
		
		if (name.equalsIgnoreCase("sklephaste"))
		{
			ArrayList<String> lore = new ArrayList<String>();
			String[] text = {"&c&l» &a&lSZYBKOSC KOPANIA", "", "&c&l» &7Mozesz zakupic i nalozyc na siebie", "   &7jeden z 10 dostepnych efektow mikstur. Pamietaj",
					"   &7ze wchodza one w interakcje z pewnymi substancjami,",
					"   &7wiec nie zaleca sie ich sumowania!", "", "&c&l» &7Efekt: &eSzybkosc kopania", " &8> &7Nasilenie: 1",
					" &8> &7Czas dzialania: 3m/8m/30m", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203300 ⛃&7/#fff203600 ⛃&7/#fff2031800 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno efektu na okres 3 minut"), ColorUtil.formatHEX("   #666666&o(SHIFT + LPM - Kupno efektu na okres 8 minut"),
					ColorUtil.formatHEX("   #666666&o(SCROLL - Kupno efektu na okres 30 minut"), ""};;
			
			for (String vers : text)
			{
				lore.add(ChatColor.translateAlternateColorCodes('&', vers));
			}
			ItemStack item = new ItemStack(Material.SPLASH_POTION);
			ItemMeta meta = item.getItemMeta();
			PotionMeta potion = (PotionMeta) meta;
			potion.setColor(Color.YELLOW);
			potion.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			potion.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
			potion.setBasePotionData(new PotionData(PotionType.WATER));
			
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', " "));
			meta.setLore(lore);
			item.setItemMeta(potion);
			
			return item;
		}
		
		if (name.equalsIgnoreCase("sklepstrength"))
		{
			ArrayList<String> lore = new ArrayList<String>();
			String[] text = {"&c&l» &a&lSILA", "", "&c&l» &7Mozesz zakupic i nalozyc na siebie", "   &7jeden z 10 dostepnych efektow mikstur. Pamietaj",
					"   &7ze wchodza one w interakcje z pewnymi substancjami,",
					"   &7wiec nie zaleca sie ich sumowania!", "", "&c&l» &7Efekt: &dSila", " &8> &7Nasilenie: 1",
					" &8> &7Czas dzialania: 3m/8m/30m", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203300 ⛃&7/#fff203600 ⛃&7/#fff2031800 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno efektu na okres 3 minut"), ColorUtil.formatHEX("   #666666&o(SHIFT + LPM - Kupno efektu na okres 8 minut"),
					ColorUtil.formatHEX("   #666666&o(SCROLL - Kupno efektu na okres 30 minut"), ""};;
			
			for (String vers : text)
			{
				lore.add(ChatColor.translateAlternateColorCodes('&', vers));
			}
			ItemStack item = new ItemStack(Material.SPLASH_POTION);
			ItemMeta meta = item.getItemMeta();
			PotionMeta potion = (PotionMeta) meta;
			potion.setColor(Color.FUCHSIA);
			potion.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			potion.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
			potion.setBasePotionData(new PotionData(PotionType.WATER));
			
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', " "));
			meta.setLore(lore);
			item.setItemMeta(potion);
			
			return item;
		}
		
		if (name.equalsIgnoreCase("sklepresistance"))
		{
			ArrayList<String> lore = new ArrayList<String>();
			String[] text = {"&c&l» &a&lODPORNOSC", "", "&c&l» &7Mozesz zakupic i nalozyc na siebie", "   &7jeden z 10 dostepnych efektow mikstur. Pamietaj",
					"   &7ze wchodza one w interakcje z pewnymi substancjami,",
					"   &7wiec nie zaleca sie ich sumowania!", "", "&c&l» &7Efekt: &7Odpornosc", " &8> &7Nasilenie: 1",
					" &8> &7Czas dzialania: 3m/8m/30m", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203300 ⛃&7/#fff203600 ⛃&7/#fff2031800 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno efektu na okres 3 minut"), ColorUtil.formatHEX("   #666666&o(SHIFT + LPM - Kupno efektu na okres 8 minut"),
					ColorUtil.formatHEX("   #666666&o(SCROLL - Kupno efektu na okres 30 minut"), ""};;
			
			for (String vers : text)
			{
				lore.add(ChatColor.translateAlternateColorCodes('&', vers));
			}
			ItemStack item = new ItemStack(Material.SPLASH_POTION);
			ItemMeta meta = item.getItemMeta();
			PotionMeta potion = (PotionMeta) meta;
			potion.setColor(Color.WHITE);
			potion.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			potion.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
			potion.setBasePotionData(new PotionData(PotionType.WATER));
			
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', " "));
			meta.setLore(lore);
			item.setItemMeta(potion);
			
			return item;
		}
		
		if (name.equalsIgnoreCase("sklepfireresistance"))
		{
			ArrayList<String> lore = new ArrayList<String>();
			String[] text = {"&c&l» &a&lODPORNOSC NA OGIEN", "", "&c&l» &7Mozesz zakupic i nalozyc na siebie", "   &7jeden z 10 dostepnych efektow mikstur. Pamietaj",
					"   &7ze wchodza one w interakcje z pewnymi substancjami,",
					"   &7wiec nie zaleca sie ich sumowania!", "", "&c&l» &7Efekt: &6Odpornosc na ogien", " &8> &7Nasilenie: 2",
					" &8> &7Czas dzialania: 3m/8m/30m", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203300 ⛃&7/#fff203600 ⛃&7/#fff2031800 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno efektu na okres 3 minut"), ColorUtil.formatHEX("   #666666&o(SHIFT + LPM - Kupno efektu na okres 8 minut"),
					ColorUtil.formatHEX("   #666666&o(SCROLL - Kupno efektu na okres 30 minut"), ""};;
			
			for (String vers : text)
			{
				lore.add(ChatColor.translateAlternateColorCodes('&', vers));
			}
			ItemStack item = new ItemStack(Material.SPLASH_POTION);
			ItemMeta meta = item.getItemMeta();
			PotionMeta potion = (PotionMeta) meta;
			potion.setColor(Color.ORANGE);
			potion.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			potion.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
			potion.setBasePotionData(new PotionData(PotionType.WATER));
			
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', " "));
			meta.setLore(lore);
			item.setItemMeta(potion);
			
			return item;
		}
		
		if (name.equalsIgnoreCase("sklepregeneration"))
		{
			ArrayList<String> lore = new ArrayList<String>();
			String[] text = {"&c&l» &a&lREGENERACJA", "", "&c&l» &7Mozesz zakupic i nalozyc na siebie", "   &7jeden z 10 dostepnych efektow mikstur. Pamietaj",
					"   &7ze wchodza one w interakcje z pewnymi substancjami,",
					"   &7wiec nie zaleca sie ich sumowania!", "", "&c&l» &7Efekt: &cRegeneracja", " &8> &7Nasilenie: 1",
					" &8> &7Czas dzialania: 3m/8m/30m", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203300 ⛃&7/#fff203600 ⛃&7/#fff2031800 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno efektu na okres 3 minut"), ColorUtil.formatHEX("   #666666&o(SHIFT + LPM - Kupno efektu na okres 8 minut"),
					ColorUtil.formatHEX("   #666666&o(SCROLL - Kupno efektu na okres 30 minut"), ""};;
			
			for (String vers : text)
			{
				lore.add(ChatColor.translateAlternateColorCodes('&', vers));
			}
			ItemStack item = new ItemStack(Material.SPLASH_POTION);
			ItemMeta meta = item.getItemMeta();
			PotionMeta potion = (PotionMeta) meta;
			potion.setColor(Color.RED);
			potion.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			potion.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
			potion.setBasePotionData(new PotionData(PotionType.WATER));
			
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', " "));
			meta.setLore(lore);
			item.setItemMeta(potion);
			
			return item;
		}
		
		if (name.equalsIgnoreCase("sklepinvis"))
		{
			ArrayList<String> lore = new ArrayList<String>();
			String[] text = {"&c&l» &a&lNIEWIDZIALNOSC", "", "&c&l» &7Mozesz zakupic i nalozyc na siebie", "   &7jeden z 10 dostepnych efektow mikstur. Pamietaj",
					"   &7ze wchodza one w interakcje z pewnymi substancjami,",
					"   &7wiec nie zaleca sie ich sumowania!", "", "&c&l» &7Efekt: &fNiewidzialnosc", " &8> &7Nasilenie: 2",
					" &8> &7Czas dzialania: 3m/8m/30m", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203300 ⛃&7/#fff203600 ⛃&7/#fff2031800 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno efektu na okres 3 minut"), ColorUtil.formatHEX("   #666666&o(SHIFT + LPM - Kupno efektu na okres 8 minut"),
					ColorUtil.formatHEX("   #666666&o(SCROLL - Kupno efektu na okres 30 minut"), ""};;
			
			for (String vers : text)
			{
				lore.add(ChatColor.translateAlternateColorCodes('&', vers));
			}
			ItemStack item = new ItemStack(Material.SPLASH_POTION);
			ItemMeta meta = item.getItemMeta();
			PotionMeta potion = (PotionMeta) meta;
			potion.setColor(Color.SILVER);
			potion.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			potion.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
			potion.setBasePotionData(new PotionData(PotionType.WATER));
			
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', " "));
			meta.setLore(lore);
			item.setItemMeta(potion);
			
			return item;
		}
		
		if (name.equalsIgnoreCase("sklepwaterbreathing"))
		{
			ArrayList<String> lore = new ArrayList<String>();
			String[] text = {"&c&l» &a&lODDYCHANIE POD WODA", "", "&c&l» &7Mozesz zakupic i nalozyc na siebie", "   &7jeden z 10 dostepnych efektow mikstur. Pamietaj",
					"   &7ze wchodza one w interakcje z pewnymi substancjami,",
					"   &7wiec nie zaleca sie ich sumowania!", "", "&c&l» &7Efekt: &1Oddychanie pod woda", " &8> &7Nasilenie: 1",
					" &8> &7Czas dzialania: 3m/8m/30m", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203300 ⛃&7/#fff203600 ⛃&7/#fff2031800 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno efektu na okres 3 minut"), ColorUtil.formatHEX("   #666666&o(SHIFT + LPM - Kupno efektu na okres 8 minut"),
					ColorUtil.formatHEX("   #666666&o(SCROLL - Kupno efektu na okres 30 minut"), ""};;
			
			for (String vers : text)
			{
				lore.add(ChatColor.translateAlternateColorCodes('&', vers));
			}
			ItemStack item = new ItemStack(Material.SPLASH_POTION);
			ItemMeta meta = item.getItemMeta();
			PotionMeta potion = (PotionMeta) meta;
			potion.setColor(Color.NAVY);
			potion.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			potion.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
			potion.setBasePotionData(new PotionData(PotionType.WATER));
			
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', " "));
			meta.setLore(lore);
			item.setItemMeta(potion);
			
			return item;
		}
		
		if (name.equalsIgnoreCase("sklepnightvision"))
		{
			ArrayList<String> lore = new ArrayList<String>();
			String[] text = {"&c&l» &a&lWIDZENIE W CIEMNOSCI", "", "&c&l» &7Mozesz zakupic i nalozyc na siebie", "   &7jeden z 10 dostepnych efektow mikstur. Pamietaj",
					"   &7ze wchodza one w interakcje z pewnymi substancjami,",
					"   &7wiec nie zaleca sie ich sumowania!", "", "&c&l» &7Efekt: &9Widzenie w ciemnosci", " &8> &7Nasilenie: 2",
					" &8> &7Czas dzialania: 3m/8m/30m", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203300 ⛃&7/#fff203600 ⛃&7/#fff2031800 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno efektu na okres 3 minut"), ColorUtil.formatHEX("   #666666&o(SHIFT + LPM - Kupno efektu na okres 8 minut"),
					ColorUtil.formatHEX("   #666666&o(SCROLL - Kupno efektu na okres 30 minut"), ""};;
			
			for (String vers : text)
			{
				lore.add(ChatColor.translateAlternateColorCodes('&', vers));
			}
			ItemStack item = new ItemStack(Material.SPLASH_POTION);
			ItemMeta meta = item.getItemMeta();
			PotionMeta potion = (PotionMeta) meta;
			potion.setColor(Color.BLUE);
			potion.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			potion.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
			potion.setBasePotionData(new PotionData(PotionType.WATER));
			
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', " "));
			meta.setLore(lore);
			item.setItemMeta(potion);
			
			return item;
		}

		if (name.equalsIgnoreCase("sklepschowek"))
		{
			String[] lore = {"&c&l» &a&lPOWIEKSZENIE SCHOWKU", "", "&c&l» &7Kliknij aby przeniesc sie do menu ulepszenia", "   &7schowku!", "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Wybranie zakladki)"), ""};

			return createInvItem(lore, Material.CHEST, false);
		}

		if (name.equalsIgnoreCase("sklepvoucher"))
		{
			String[] lore = {"&c&l» &a&lVOUCHER NA UNMUTE", "", "&c&l» &7Dodatek ten pozwoli Ci zdjac nalozone", "   &7wyciszenie na czacie, usuwajac je. Jezeli nie", "   &7jestes wyciszony - nie bedziesz w stanie zakupic",
					"   &7tego przedmiotu. Zakup jest finalny i jednorazowy!", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203300 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Zakup vouchera)"), ""};

			return createInvItem(lore, Material.MAP, false);
		}

		if (name.equalsIgnoreCase("sklepslub"))
		{
			String[] lore = {"&c&l» &a&lSLUB", "", "&c&l» &7Mozesz wziac slub z dowolnym graczem", "   &7kompletnie za darmo za pomoca komendy &c/slub!", "",
							"&c&l» &7Pre-view Twojego nicku przed slubem:", " &8> " + ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.getGangInChat(GangManager.getInstance().getGang(player)) + "&c" + "&r" + ChatUtil.returnPlayerColor(player) + Objects.requireNonNull(player.getPlayer()).getName() + "#8c8c8c Czesc!"), "",
							"&c&l» &7Pre-view Twojego nicku po slubie:", " &8> " + ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.getGangInChat(GangManager.getInstance().getGang(player)) + "&c❤ #fc7474" + "&r" + ChatUtil.returnPlayerColor(player) + Objects.requireNonNull(player.getPlayer()).getName() + "#8c8c8c Czesc!"), "",};

			return createInvItem(lore, Material.POPPY, false);
		}

		if (name.equalsIgnoreCase("sklepkolor1"))
		{
			String[] lore = {"&c&l» &a&lZMIANA KOLORU NICKU", "", "&c&l» &7Mozesz dowolnie kupic jeden z 8 dostepnych", "   &7kolorow nicku na czacie. Pamietaj jednak", "   &7ze zakup koloru jest finalny i jednorazowy!", "", "&c&l» &7Pre-view nowego koloru:",
					" &8> " + ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.getGangInChat(GangManager.getInstance().getGang(player)) + "&c" + ChatUtil.returnMarryPrefix(player) +  "&r#fc7474" + Objects.requireNonNull(player.getPlayer()).getName() + "#8c8c8c Czesc!"), "",
					ColorUtil.formatHEX("&c&l» &7Kolor: #fc7474CZERWONY"), " &8> &7Oznaczenie HEX: #fc7474", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203400 ⛃"), " &8> &7Status posiadania: " + ColorUtil.formatHEX(ChatUtil.isPlayerColor(player, "#fc7474")), "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Kupno i zmiana)"), ""};

			return createInvItem(lore, Material.RED_DYE, false);
		}

		if (name.equalsIgnoreCase("sklepkolor2"))
		{
			String[] lore = {"&c&l» &a&lZMIANA KOLORU NICKU", "", "&c&l» &7Mozesz dowolnie kupic jeden z 8 dostepnych", "   &7kolorow nicku na czacie. Pamietaj jednak", "   &7ze zakup koloru jest finalny i jednorazowy!", "", "&c&l» &7Pre-view nowego koloru:",
					" &8> " + ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.getGangInChat(GangManager.getInstance().getGang(player)) + "&c" + ChatUtil.returnMarryPrefix(player) +  "&r#3075ff" + Objects.requireNonNull(player.getPlayer()).getName() + "#8c8c8c Czesc!"), "",
					ColorUtil.formatHEX("&c&l» &7Kolor: #3075ffNIEBIESKI"), " &8> &7Oznaczenie HEX: #3075ff", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203400 ⛃"), " &8> &7Status posiadania: " + ColorUtil.formatHEX(ChatUtil.isPlayerColor(player, "#3075ff")), "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Kupno i zmiana)"), ""};

			return createInvItem(lore, Material.LIGHT_BLUE_DYE, false);
		}

		if (name.equalsIgnoreCase("sklepkolor3"))
		{
			String[] lore = {"&c&l» &a&lZMIANA KOLORU NICKU", "", "&c&l» &7Mozesz dowolnie kupic jeden z 8 dostepnych", "   &7kolorow nicku na czacie. Pamietaj jednak", "   &7ze zakup koloru jest finalny i jednorazowy!", "", "&c&l» &7Pre-view nowego koloru:",
					" &8> " + ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.getGangInChat(GangManager.getInstance().getGang(player)) + "&c" + ChatUtil.returnMarryPrefix(player) +  "&r#02d645" + Objects.requireNonNull(player.getPlayer()).getName() + "#8c8c8c Czesc!"), "",
					ColorUtil.formatHEX("&c&l» &7Kolor: #02d645ZIELONY"), " &8> &7Oznaczenie HEX: #02d645", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203400 ⛃"), " &8> &7Status posiadania: " + ColorUtil.formatHEX(ChatUtil.isPlayerColor(player, "#02d645")), "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Kupno i zmiana)"), ""};

			return createInvItem(lore, Material.LIME_DYE, false);
		}

		if (name.equalsIgnoreCase("sklepkolor4"))
		{
			String[] lore = {"&c&l» &a&lZMIANA KOLORU NICKU", "", "&c&l» &7Mozesz dowolnie kupic jeden z 8 dostepnych", "   &7kolorow nicku na czacie. Pamietaj jednak", "   &7ze zakup koloru jest finalny i jednorazowy!", "", "&c&l» &7Pre-view nowego koloru:",
					" &8> " + ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.getGangInChat(GangManager.getInstance().getGang(player)) + "&c" + ChatUtil.returnMarryPrefix(player) +  "&r#fcff33" + Objects.requireNonNull(player.getPlayer()).getName() + "#8c8c8c Czesc!"), "",
					ColorUtil.formatHEX("&c&l» &7Kolor: #fcff33ZOLTY"), " &8> &7Oznaczenie HEX: #fcff33", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203400 ⛃"), " &8> &7Status posiadania: " + ColorUtil.formatHEX(ChatUtil.isPlayerColor(player, "#fcff33")), "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Kupno i zmiana)"), ""};

			return createInvItem(lore, Material.YELLOW_DYE, false);
		}

		if (name.equalsIgnoreCase("sklepkolor5"))
		{
			String[] lore = {"&c&l» &a&lZMIANA KOLORU NICKU", "", "&c&l» &7Mozesz dowolnie kupic jeden z 8 dostepnych", "   &7kolorow nicku na czacie. Pamietaj jednak", "   &7ze zakup koloru jest finalny i jednorazowy!", "", "&c&l» &7Pre-view nowego koloru:",
					" &8> " + ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.getGangInChat(GangManager.getInstance().getGang(player)) + "&c" + ChatUtil.returnMarryPrefix(player) +  "&r#ffffff" + Objects.requireNonNull(player.getPlayer()).getName() + "#8c8c8c Czesc!"), "",
					ColorUtil.formatHEX("&c&l» &7Kolor: #ffffffBIALY"), " &8> &7Oznaczenie HEX: #ffffff", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203400 ⛃"), " &8> &7Status posiadania: " + ColorUtil.formatHEX(ChatUtil.isPlayerColor(player, "#ffffff")), "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Kupno i zmiana)"), ""};

			return createInvItem(lore, Material.WHITE_DYE, false);
		}

		if (name.equalsIgnoreCase("sklepkolor6"))
		{
			String[] lore = {"&c&l» &a&lZMIANA KOLORU NICKU", "", "&c&l» &7Mozesz dowolnie kupic jeden z 8 dostepnych", "   &7kolorow nicku na czacie. Pamietaj jednak", "   &7ze zakup koloru jest finalny i jednorazowy!", "", "&c&l» &7Pre-view nowego koloru:",
					" &8> " + ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.getGangInChat(GangManager.getInstance().getGang(player)) + "&c" + ChatUtil.returnMarryPrefix(player) +  "&r#242424" + Objects.requireNonNull(player.getPlayer()).getName() + "#8c8c8c Czesc!"), "",
					ColorUtil.formatHEX("&c&l» &7Kolor: #242424SZARY"), " &8> &7Oznaczenie HEX: #242424", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203400 ⛃"), " &8> &7Status posiadania: " + ColorUtil.formatHEX(ChatUtil.isPlayerColor(player, "#242424")), "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Kupno i zmiana)"), ""};

			return createInvItem(lore, Material.LIGHT_GRAY_DYE, false);
		}

		if (name.equalsIgnoreCase("sklepkolor7"))
		{
			String[] lore = {"&c&l» &a&lZMIANA KOLORU NICKU", "", "&c&l» &7Mozesz dowolnie kupic jeden z 8 dostepnych", "   &7kolorow nicku na czacie. Pamietaj jednak", "   &7ze zakup koloru jest finalny i jednorazowy!", "", "&c&l» &7Pre-view nowego koloru:",
					" &8> " + ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.getGangInChat(GangManager.getInstance().getGang(player)) + "&c" + ChatUtil.returnMarryPrefix(player) +  "&r#ffb338" + Objects.requireNonNull(player.getPlayer()).getName() + "#8c8c8c Czesc!"), "",
					ColorUtil.formatHEX("&c&l» &7Kolor: #ffb338POMARANCZOWY"), " &8> &7Oznaczenie HEX: #ffb338", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203400 ⛃"), " &8> &7Status posiadania: " + ColorUtil.formatHEX(ChatUtil.isPlayerColor(player, "#ffb338")), "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Kupno i zmiana)"), ""};

			return createInvItem(lore, Material.ORANGE_DYE, false);
		}

		if (name.equalsIgnoreCase("sklepkolor8"))
		{
			String[] lore = {"&c&l» &a&lZMIANA KOLORU NICKU", "", "&c&l» &7Mozesz dowolnie kupic jeden z 8 dostepnych", "   &7kolorow nicku na czacie. Pamietaj jednak", "   &7ze zakup koloru jest finalny i jednorazowy!", "", "&c&l» &7Pre-view nowego koloru:",
					" &8> " + ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.getGangInChat(GangManager.getInstance().getGang(player)) + "&c" + ChatUtil.returnMarryPrefix(player) +  "&r#ff9ee7" + Objects.requireNonNull(player.getPlayer()).getName() + "#8c8c8c Czesc!"), "",
					ColorUtil.formatHEX("&c&l» &7Kolor: #ff9ee7ROZOWY"), " &8> &7Oznaczenie HEX: #ff9ee7", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203400 ⛃"), " &8> &7Status posiadania: " + ColorUtil.formatHEX(ChatUtil.isPlayerColor(player, "#ff9ee7")), "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Kupno i zmiana)"), ""};

			return createInvItem(lore, Material.PINK_DYE, false);
		}

		if (name.equalsIgnoreCase("sklepnogang"))
		{
			String[] lore = {"&c&l» &a&lSKLEP GANGU", "", "&c&l» &7Ta zakladka sklepu jest niedostepna, poniewaz", "   &7nie nalezysz do zadnego z gangow!", "",
					ColorUtil.formatHEX("   #666666&o(Gang mozesz stworzyc za 1000⛃ za pomoca komendy"), ColorUtil.formatHEX("   #666666&o/gang stworz. Mozesz takze dolaczyc do istniejacego"),
					ColorUtil.formatHEX("   #666666&ojuz gangu, lecz musisz najpierw otrzymac zaproszenie)"), ""};

			return createInvItem(lore, Material.BARRIER, false);
		}

		if (name.equalsIgnoreCase("sklepgang1"))
		{
			String[] lore = {"&c&l» &a&lZMIANA KOLORU GANGU", "", "&c&l» &7Mozesz dowolnie kupic jeden z 8 dostepnych", "   &7kolorow gangu na czacie. Pamietaj jednak", "   &7ze zakup koloru jest finalny i jednorazowy!", "", "&c&l» &7Pre-view nowego koloru gangu:",
					" &8> " + ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.returnCustomGangPrefix(player, "&c", GangManager.getInstance().getPrefixes(GangManager.getInstance().getGang(player)), ChatUtil.returnGangStar(GangManager.getInstance().getGang(player))) + "&c" + ChatUtil.returnMarryPrefix(player) + ChatUtil.returnPlayerColor(player) + Objects.requireNonNull(player.getPlayer()).getName() + "#8c8c8c Czesc!"), "",
					"&c&l» &7Kolor: &cCZERWONY", " &8> &7Oznaczenie Bukkit: & c", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203250 ⛃"), " &8> &7Status posiadania: " + ColorUtil.formatHEX(ChatUtil.isGangColor(GangManager.getInstance().getGang(player), "&c")), "",
					 ColorUtil.formatHEX("   #666666&o(LPM/PPM - Kupno i zmiana)"), ""};

			return createInvItem(lore, Material.RED_DYE, false);
		}

		if (name.equalsIgnoreCase("sklepgang2"))
		{
			String[] lore = {"&c&l» &a&lZMIANA KOLORU GANGU", "", "&c&l» &7Mozesz dowolnie kupic jeden z 8 dostepnych", "   &7kolorow gangu na czacie. Pamietaj jednak", "   &7ze zakup koloru jest finalny i jednorazowy!", "", "&c&l» &7Pre-view nowego koloru gangu:",
					" &8> " + ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.returnCustomGangPrefix(player, "&b", GangManager.getInstance().getPrefixes(GangManager.getInstance().getGang(player)), ChatUtil.returnGangStar(GangManager.getInstance().getGang(player))) + "&c" + ChatUtil.returnMarryPrefix(player) + ChatUtil.returnPlayerColor(player) + Objects.requireNonNull(player.getPlayer()).getName() + "#8c8c8c Czesc!"), "",
					"&c&l» &7Kolor: &bNIEBIESKI", " &8> &7Oznaczenie Bukkit: & b", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203250 ⛃"), " &8> &7Status posiadania: " + ColorUtil.formatHEX(ChatUtil.isGangColor(GangManager.getInstance().getGang(player), "&b")), "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Kupno i zmiana)"), ""};

			return createInvItem(lore, Material.LIGHT_BLUE_DYE, false);
		}

		if (name.equalsIgnoreCase("sklepgang3"))
		{
			String[] lore = {"&c&l» &a&lZMIANA KOLORU GANGU", "", "&c&l» &7Mozesz dowolnie kupic jeden z 8 dostepnych", "   &7kolorow gangu na czacie. Pamietaj jednak", "   &7ze zakup koloru jest finalny i jednorazowy!", "", "&c&l» &7Pre-view nowego koloru gangu:",
					" &8> " + ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.returnCustomGangPrefix(player, "&a", GangManager.getInstance().getPrefixes(GangManager.getInstance().getGang(player)), ChatUtil.returnGangStar(GangManager.getInstance().getGang(player))) + "&c" + ChatUtil.returnMarryPrefix(player) + ChatUtil.returnPlayerColor(player) + Objects.requireNonNull(player.getPlayer()).getName() + "#8c8c8c Czesc!"), "",
					"&c&l» &7Kolor: &aZIELONY", " &8> &7Oznaczenie Bukkit: & a", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203250 ⛃"), " &8> &7Status posiadania: " + ColorUtil.formatHEX(ChatUtil.isGangColor(GangManager.getInstance().getGang(player), "&a")), "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Kupno i zmiana)"), ""};

			return createInvItem(lore, Material.LIME_DYE, false);
		}

		if (name.equalsIgnoreCase("sklepgang4"))
		{
			String[] lore = {"&c&l» &a&lZMIANA KOLORU GANGU", "", "&c&l» &7Mozesz dowolnie kupic jeden z 8 dostepnych", "   &7kolorow gangu na czacie. Pamietaj jednak", "   &7ze zakup koloru jest finalny i jednorazowy!", "", "&c&l» &7Pre-view nowego koloru gangu:",
					" &8> " + ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.returnCustomGangPrefix(player, "&e", GangManager.getInstance().getPrefixes(GangManager.getInstance().getGang(player)), ChatUtil.returnGangStar(GangManager.getInstance().getGang(player))) + "&c" + ChatUtil.returnMarryPrefix(player) + ChatUtil.returnPlayerColor(player) + Objects.requireNonNull(player.getPlayer()).getName() + "#8c8c8c Czesc!"), "",
					"&c&l» &7Kolor: &eZOLTY", " &8> &7Oznaczenie Bukkit: & e", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203250 ⛃"), " &8> &7Status posiadania: " + ColorUtil.formatHEX(ChatUtil.isGangColor(GangManager.getInstance().getGang(player), "&e")), "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Kupno i zmiana)"), ""};

			return createInvItem(lore, Material.YELLOW_DYE, false);
		}

		if (name.equalsIgnoreCase("sklepgang5"))
		{
			String[] lore = {"&c&l» &a&lZMIANA KOLORU GANGU", "", "&c&l» &7Mozesz dowolnie kupic jeden z 8 dostepnych", "   &7kolorow gangu na czacie. Pamietaj jednak", "   &7ze zakup koloru jest finalny i jednorazowy!", "", "&c&l» &7Pre-view nowego koloru gangu:",
					" &8> " + ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.returnCustomGangPrefix(player, "&f", GangManager.getInstance().getPrefixes(GangManager.getInstance().getGang(player)), ChatUtil.returnGangStar(GangManager.getInstance().getGang(player))) + "&c" + ChatUtil.returnMarryPrefix(player) + ChatUtil.returnPlayerColor(player) + Objects.requireNonNull(player.getPlayer()).getName() + "#8c8c8c Czesc!"), "",
					"&c&l» &7Kolor: &fBIALY", " &8> &7Oznaczenie Bukkit: & f", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203250 ⛃"), " &8> &7Status posiadania: " + ColorUtil.formatHEX(ChatUtil.isGangColor(GangManager.getInstance().getGang(player), "&f")), "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Kupno i zmiana)"), ""};

			return createInvItem(lore, Material.WHITE_DYE, false);
		}

		if (name.equalsIgnoreCase("sklepgang6"))
		{
			String[] lore = {"&c&l» &a&lZMIANA KOLORU GANGU", "", "&c&l» &7Mozesz dowolnie kupic jeden z 8 dostepnych", "   &7kolorow gangu na czacie. Pamietaj jednak", "   &7ze zakup koloru jest finalny i jednorazowy!", "", "&c&l» &7Pre-view nowego koloru gangu:",
					" &8> " + ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.returnCustomGangPrefix(player, "&7", GangManager.getInstance().getPrefixes(GangManager.getInstance().getGang(player)), ChatUtil.returnGangStar(GangManager.getInstance().getGang(player))) + "&c" + ChatUtil.returnMarryPrefix(player) + ChatUtil.returnPlayerColor(player) + Objects.requireNonNull(player.getPlayer()).getName() + "#8c8c8c Czesc!"), "",
					"&c&l» &7Kolor: &7SZARY", " &8> &7Oznaczenie Bukkit: & 7", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203250 ⛃"), " &8> &7Status posiadania: " + ColorUtil.formatHEX(ChatUtil.isGangColor(GangManager.getInstance().getGang(player), "&7")), "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Kupno i zmiana)"), ""};

			return createInvItem(lore, Material.LIGHT_GRAY_DYE, false);
		}

		if (name.equalsIgnoreCase("sklepgang7"))
		{
			String[] lore = {"&c&l» &a&lZMIANA KOLORU GANGU", "", "&c&l» &7Mozesz dowolnie kupic jeden z 8 dostepnych", "   &7kolorow gangu na czacie. Pamietaj jednak", "   &7ze zakup koloru jest finalny i jednorazowy!", "", "&c&l» &7Pre-view nowego koloru gangu:",
					" &8> " + ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.returnCustomGangPrefix(player, "&6", GangManager.getInstance().getPrefixes(GangManager.getInstance().getGang(player)), ChatUtil.returnGangStar(GangManager.getInstance().getGang(player))) + "&c" + ChatUtil.returnMarryPrefix(player) + ChatUtil.returnPlayerColor(player) + Objects.requireNonNull(player.getPlayer()).getName() + "#8c8c8c Czesc!"), "",
					"&c&l» &7Kolor: &6POMARANCZOWY", " &8> &7Oznaczenie Bukkit: & 6", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203250 ⛃"), " &8> &7Status posiadania: " + ColorUtil.formatHEX(ChatUtil.isGangColor(GangManager.getInstance().getGang(player), "&6")), "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Kupno i zmiana)"), ""};

			return createInvItem(lore, Material.ORANGE_DYE, false);
		}

		if (name.equalsIgnoreCase("sklepgang8"))
		{
			String[] lore = {"&c&l» &a&lZMIANA KOLORU GANGU", "", "&c&l» &7Mozesz dowolnie kupic jeden z 8 dostepnych", "   &7kolorow gangu na czacie. Pamietaj jednak", "   &7ze zakup koloru jest finalny i jednorazowy!", "", "&c&l» &7Pre-view nowego koloru gangu:",
					" &8> " + ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.returnCustomGangPrefix(player, "&d", GangManager.getInstance().getPrefixes(GangManager.getInstance().getGang(player)), ChatUtil.returnGangStar(GangManager.getInstance().getGang(player))) + "&c" + ChatUtil.returnMarryPrefix(player) + ChatUtil.returnPlayerColor(player) + Objects.requireNonNull(player.getPlayer()).getName() + "#8c8c8c Czesc!"), "",
					"&c&l» &7Kolor: &dROZOWY", " &8> &7Oznaczenie Bukkit: & d", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203250 ⛃"), " &8> &7Status posiadania: " + ColorUtil.formatHEX(ChatUtil.isGangColor(GangManager.getInstance().getGang(player), "&d")), "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Kupno i zmiana)"), ""};

			return createInvItem(lore, Material.PINK_DYE, false);
		}

		if (name.equalsIgnoreCase("sklepgangp1"))
		{
			String[] lore = {"&c&l» &a&lZMIANA PREFIXOW GANGU", "", "&c&l» &7Mozesz dowolnie kupic jeden z 3 dostepnych", "   &7prefixow gangu na czacie. Pamietaj jednak", "   &7ze zakup prefixow jest finalny i jednorazowy!", "", "&c&l» &7Pre-view nowego prefixu gangu:",
					" &8> " + ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.returnCustomGangPrefix(player, ChatUtil.getValidGangColor(GangManager.getInstance().getGang(player)), "normal", ChatUtil.returnGangStar(GangManager.getInstance().getGang(player))) + "&c" + ChatUtil.returnMarryPrefix(player) + ChatUtil.returnPlayerColor(player) + Objects.requireNonNull(player.getPlayer()).getName() + "#8c8c8c Czesc!"), "",
					"&c&l» &7Prefixy: Kwadratowe", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203350 ⛃"), " &8> &7Status posiadania: " + ColorUtil.formatHEX(ChatUtil.isGangPrefix(GangManager.getInstance().getGang(player), "normal")), "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Kupno i zmiana)"), ""};

			return createInvItem(lore, Material.WOODEN_AXE, false);
		}

		if (name.equalsIgnoreCase("sklepgangp2"))
		{
			String[] lore = {"&c&l» &a&lZMIANA PREFIXOW GANGU", "", "&c&l» &7Mozesz dowolnie kupic jeden z 3 dostepnych", "   &7prefixow gangu na czacie. Pamietaj jednak", "   &7ze zakup prefixow jest finalny i jednorazowy!", "", "&c&l» &7Pre-view nowego prefixu gangu:",
					" &8> " + ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.returnCustomGangPrefix(player, ChatUtil.getValidGangColor(GangManager.getInstance().getGang(player)), "rounded", ChatUtil.returnGangStar(GangManager.getInstance().getGang(player))) + "&c" + ChatUtil.returnMarryPrefix(player) + ChatUtil.returnPlayerColor(player) + Objects.requireNonNull(player.getPlayer()).getName() + "#8c8c8c Czesc!"), "",
					"&c&l» &7Prefixy: Zaokraglone", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203350 ⛃"), " &8> &7Status posiadania: " + ColorUtil.formatHEX(ChatUtil.isGangPrefix(GangManager.getInstance().getGang(player), "rounded")), "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Kupno i zmiana)"), ""};

			return createInvItem(lore, Material.STONE_AXE, false);
		}

		if (name.equalsIgnoreCase("sklepgangp3"))
		{
			String[] lore = {"&c&l» &a&lZMIANA PREFIXOW GANGU", "", "&c&l» &7Mozesz dowolnie kupic jeden z 3 dostepnych", "   &7prefixow gangu na czacie. Pamietaj jednak", "   &7ze zakup prefixow jest finalny i jednorazowy!", "", "&c&l» &7Pre-view nowego prefixu gangu:",
					" &8> " + ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.returnCustomGangPrefix(player, ChatUtil.getValidGangColor(GangManager.getInstance().getGang(player)), "arrows", ChatUtil.returnGangStar(GangManager.getInstance().getGang(player))) + "&c" + ChatUtil.returnMarryPrefix(player) + ChatUtil.returnPlayerColor(player) + Objects.requireNonNull(player.getPlayer()).getName() + "#8c8c8c Czesc!"), "",
					"&c&l» &7Prefixy: Strzalkowe", "", ColorUtil.formatHEX("&c&l» &7Cena: #fff203350 ⛃"), " &8> &7Status posiadania: " + ColorUtil.formatHEX(ChatUtil.isGangPrefix(GangManager.getInstance().getGang(player), "arrows")), "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Kupno i zmiana)"), ""};

			return createInvItem(lore, Material.GOLDEN_AXE, false);
		}

		if (name.equalsIgnoreCase("sklepgangchat"))
		{
			String[] lore = {"&c&l» &a&lPRYWATNY CZAT GANGU", "", "&c&l» &7Mozesz zakupic specjalny, ekskluzywny prywatny", "   &7czat dla czlonkow swojego gangu. Aby go wykorzystac", "   &7pzed swoja wiadomoscia wpisz znak &e!&7.", "", "&c&l» &7Pre-view prywatnego czatu gangu:",
					" &8> " + ColorUtil.formatHEX(ChatUtil.returnGangPlayerChatMessage(player, "!Czesc!")), "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff203500 ⛃"), " &8> &7Status posiadania: " + ColorUtil.formatHEX(ChatUtil.isGangChat(GangManager.getInstance().getGang(player))), "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Kupno i zmiana)"), ""};

			return createInvItem(lore, Material.FEATHER, false);
		}

		if (name.equalsIgnoreCase("sklepgangstar"))
		{
			String[] lore = {"&c&l» &a&lKOSMETYK GWIAZDY", "", "&c&l» &7Mozesz zakupic specjalny, ekskluzywny dodatek", "   &7do Twojego gangu na czacie, ktory uswiadomi", "   &7reszcie ktory gang jest najbogatszy!", "", "&c&l» &7Pre-view nowego wygladu gangu:",
					" &8> " + ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.returnCustomGangPrefix(player, ChatUtil.getValidGangColor(GangManager.getInstance().getGang(player)), GangManager.getInstance().getPrefixes(GangManager.getInstance().getGang(player)), "#ffc936★ ") + "&c" + ChatUtil.returnMarryPrefix(player) + ChatUtil.returnPlayerColor(player) + Objects.requireNonNull(player.getPlayer()).getName() + "#8c8c8c Czesc!"), "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff203900 ⛃"), " &8> &7Status posiadania: " + ColorUtil.formatHEX(ChatUtil.isGangStar(GangManager.getInstance().getGang(player))), "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Kupno i zmiana)"), ""};

			return createInvItem(lore, Material.NETHER_STAR, false);
		}

		if (name.equalsIgnoreCase("sklepinfo"))
		{
			String[] lore = {"&c&l» &a&lWYBIERZ KATEGORIE", "", "&c&l» &7Wybierz interesujaca Cie zakladke, sposrod", "   &73 dostepnych po lewej stronie menu!", ""};

			return createInvItem(lore, Material.BARRIER, false);
		}

		if (name.equalsIgnoreCase("sklepkupno"))
		{
			String[] lore = {"&c&l» &a&lKUPNO I SPRZEDAZ", "", "&c&l» &7Aby kupic przedmiot, najedz na niego", "   &7i kliknij LPM, jesli chcesz sprzedac jakis",
					"   &7przedmiot - kliknij PPM. Dodatkowo kiedy uzyjesz", "   &7kombinacji SHIFT + LPM lub SHIFT + PPM, wowczas", "   &7manipulujesz calym stakiem danego przedmiotu!",
					"", "&c&l» &7Wszystkie dokonane kupna i sprzedaze w sklepie", "   &7sa finalne i nie ma mozliwosci ich cofniecia.", "   &7Oznacza to, ze jesli sie pomylisz i kupisz",
					"   &7cos innego - to chuj ci w dupie, sklep", "   &7umywa od ciebie rece!", ""};

			return createInvItem(lore, Material.GOLD_INGOT, false);
		}

		if (name.equalsIgnoreCase("sklepitemy"))

		{
			String[] lore = {"&c&l» &a&lPRZEDMIOTY, BLOKI I SUROWCE", "", "&c&l» &7Kategoria sklepu z przedmiotami zawierajaca", "   &7wiekszosc znanych Ci blokow, ktore mozesz",
					"&7   swobodnie zakupic lub sprzedac!", "", ColorUtil.formatHEX("   #666666&o(LPM/PPM - Wybranie zakladki)"), ""};

			return createInvItem(lore, Material.GRASS_BLOCK, false);
		}

		if (name.equalsIgnoreCase("sklepitemyselected"))
		{
			String[] lore = {"&c&l» &a&lPRZEDMIOTY, BLOKI I SUROWCE", "", "&c&l» &7Kategoria sklepu z przedmiotami zawierajaca", "   &7wiekszosc znanych Ci blokow, ktore mozesz",
					"&7   swobodnie zakupic lub sprzedac!", "", ColorUtil.formatHEX("   #666666&o(LPM/PPM - Wybranie zakladki)"), ""};

			return createInvItem(lore, Material.GRASS_BLOCK, true);
		}

		if (name.equalsIgnoreCase("sklepksiazki"))
		{
			String[] lore = {"&c&l» &a&lKSIAZKI Z ENCHANTAMI", "", "&c&l» &7Kategoria sklepu z enchantowanymi", "   &7ksiazkami, ktore mozesz zakupic juz teraz!", "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Wybranie zakladki)"), ""};

			return createInvItem(lore, Material.ENCHANTING_TABLE, false);
		}

		if (name.equalsIgnoreCase("sklepksiazkiselected"))
		{
			String[] lore = {"&c&l» &a&lKSIAZKI Z ENCHANTAMI", "", "&c&l» &7Kategoria sklepu z enchantowanymi", "   &7ksiazkami, ktore mozesz zakupic juz teraz!", "",
					ColorUtil.formatHEX("   #666666&o(LPM/PPM - Wybranie zakladki)"), ""};

			return createInvItem(lore, Material.ENCHANTING_TABLE, true);
		}

		if (name.equalsIgnoreCase("sklepspecjalne"))
		{
			String[] lore = {"&c&l» &a&lPRZEDMIOTY SPECJALNE", "", "&c&l» &7Kategoria sklepu z ekskluzywnymi itemami,", "   &7ktorych nie da sie wytworzyc w craftingu. Ich dzialanie",
					"   &7jest dokladnie opisane, a satysfakcja z jego posiadania", "   &7i mozliwosci - gwarantowana!", "", ColorUtil.formatHEX("   #666666&o(LPM/PPM - Wybranie zakladki)"), ""};

			return createInvItem(lore, Material.EMERALD, false);
		}

		if (name.equalsIgnoreCase("sklepspecjalneselected"))
		{
			String[] lore = {"&c&l» &a&lPRZEDMIOTY SPECJALNE", "", "&c&l» &7Kategoria sklepu z ekskluzywnymi itemami,", "   &7ktorych nie da sie wytworzyc w craftingu. Ich dzialanie",
							"   &7jest dokladnie opisane, a satysfakcja z jego posiadania", "   &7i jego mozliwosci - gwarantowana!", "", ColorUtil.formatHEX("   #666666&o(LPM/PPM - Wybranie zakladki)"), ""};

			return createInvItem(lore, Material.EMERALD, true);
		}

		if (name.equalsIgnoreCase("sklepksiazki1"))
		{
			String[] lore = {"&c&l» &a&lENCHANTOWANA KSIAZKA", "", "&c&l» &7Ksiazka zakleta na podany enchant, ktorej", "   &7mozesz uzyc by ulepszyc swoje przedmioty!", "",
					ColorUtil.formatHEX("&c&l» &7Rodzaj zaklecia: #80ff1fMending"), ColorUtil.formatHEX(" &8> &7Poziom zaklecia: #fc7474✖"), "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2034200 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.ENCHANTED_BOOK, false);
		}

		if (name.equalsIgnoreCase("sklepksiazki2"))
		{
			String[] lore = {"&c&l» &a&lENCHANTOWANA KSIAZKA", "", "&c&l» &7Ksiazka zakleta na podany enchant, ktorej", "   &7mozesz uzyc by ulepszyc swoje przedmioty!", "",
					ColorUtil.formatHEX("&c&l» &7Rodzaj zaklecia: #80ff1fInfinity"), ColorUtil.formatHEX(" &8> &7Poziom zaklecia: #fc7474✖"), "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2032700 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.ENCHANTED_BOOK, false);
		}

		if (name.equalsIgnoreCase("sklepksiazki3"))
		{
			String[] lore = {"&c&l» &a&lENCHANTOWANA KSIAZKA", "", "&c&l» &7Ksiazka zakleta na podany enchant, ktorej", "   &7mozesz uzyc by ulepszyc swoje przedmioty!", "",
					ColorUtil.formatHEX("&c&l» &7Rodzaj zaklecia:#80ff1f Silk Touch"), ColorUtil.formatHEX(" &8> &7Poziom zaklecia: #fc7474✖"), "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2033000 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.ENCHANTED_BOOK, false);
		}

		if (name.equalsIgnoreCase("sklepksiazki4"))
		{
			String[] lore = {"&c&l» &a&lENCHANTOWANA KSIAZKA", "", "&c&l» &7Ksiazka zakleta na podany enchant, ktorej", "   &7mozesz uzyc by ulepszyc swoje przedmioty!", "",
					ColorUtil.formatHEX("&c&l» &7Rodzaj zaklecia:#80ff1f Protection"), ColorUtil.formatHEX(" &8> &7Poziom zaklecia: &b4"), "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2033900 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.ENCHANTED_BOOK, false);
		}

		if (name.equalsIgnoreCase("sklepksiazki5"))
		{
			String[] lore = {"&c&l» &a&lENCHANTOWANA KSIAZKA", "", "&c&l» &7Ksiazka zakleta na podany enchant, ktorej", "   &7mozesz uzyc by ulepszyc swoje przedmioty!", "",
					ColorUtil.formatHEX("&c&l» &7Rodzaj zaklecia:#80ff1f Sharpness"), ColorUtil.formatHEX(" &8> &7Poziom zaklecia: &b5"), "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2033700 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.ENCHANTED_BOOK, false);
		}

		if (name.equalsIgnoreCase("sklepksiazki6"))
		{
			String[] lore = {"&c&l» &a&lENCHANTOWANA KSIAZKA", "", "&c&l» &7Ksiazka zakleta na podany enchant, ktorej", "   &7mozesz uzyc by ulepszyc swoje przedmioty!", "",
					ColorUtil.formatHEX("&c&l» &7Rodzaj zaklecia:#80ff1f Fire Aspect"), ColorUtil.formatHEX(" &8> &7Poziom zaklecia: &b2"), "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2032200 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.ENCHANTED_BOOK, false);
		}

		if (name.equalsIgnoreCase("sklepksiazki7"))
		{
			String[] lore = {"&c&l» &a&lENCHANTOWANA KSIAZKA", "", "&c&l» &7Ksiazka zakleta na podany enchant, ktorej", "   &7mozesz uzyc by ulepszyc swoje przedmioty!", "",
					ColorUtil.formatHEX("&c&l» &7Rodzaj zaklecia:#80ff1f Power"), ColorUtil.formatHEX(" &8> &7Poziom zaklecia: &b5"), "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2033900 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.ENCHANTED_BOOK, false);
		}

		if (name.equalsIgnoreCase("sklepksiazki8"))
		{
			String[] lore = {"&c&l» &a&lENCHANTOWANA KSIAZKA", "", "&c&l» &7Ksiazka zakleta na podany enchant, ktorej", "   &7mozesz uzyc by ulepszyc swoje przedmioty!", "",
					ColorUtil.formatHEX("&c&l» &7Rodzaj zaklecia:#80ff1f Flame"), ColorUtil.formatHEX(" &8> &7Poziom zaklecia: #fc7474✖"), "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2031400 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.ENCHANTED_BOOK, false);
		}

		if (name.equalsIgnoreCase("sklepksiazki9"))
		{
			String[] lore = {"&c&l» &a&lENCHANTOWANA KSIAZKA", "", "&c&l» &7Ksiazka zakleta na podany enchant, ktorej", "   &7mozesz uzyc by ulepszyc swoje przedmioty!", "",
					ColorUtil.formatHEX("&c&l» &7Rodzaj zaklecia:#80ff1f Punch"), ColorUtil.formatHEX(" &8> &7Poziom zaklecia: &b2"), "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2032900 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.ENCHANTED_BOOK, false);
		}

		if (name.equalsIgnoreCase("sklepksiazki10"))
		{
			String[] lore = {"&c&l» &a&lENCHANTOWANA KSIAZKA", "", "&c&l» &7Ksiazka zakleta na podany enchant, ktorej", "   &7mozesz uzyc by ulepszyc swoje przedmioty!", "",
					ColorUtil.formatHEX("&c&l» &7Rodzaj zaklecia:#80ff1f Efficiency"), ColorUtil.formatHEX(" &8> &7Poziom zaklecia: &b5"), "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2034000 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.ENCHANTED_BOOK, false);
		}

		if (name.equalsIgnoreCase("sklepksiazki11"))
		{
			String[] lore = {"&c&l» &a&lENCHANTOWANA KSIAZKA", "", "&c&l» &7Ksiazka zakleta na podany enchant, ktorej", "   &7mozesz uzyc by ulepszyc swoje przedmioty!", "",
					ColorUtil.formatHEX("&c&l» &7Rodzaj zaklecia:#80ff1f Unbreaking"), ColorUtil.formatHEX(" &8> &7Poziom zaklecia: &b3"), "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2032500 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.ENCHANTED_BOOK, false);
		}

		if (name.equalsIgnoreCase("sklepksiazki12"))
		{
			String[] lore = {"&c&l» &a&lENCHANTOWANA KSIAZKA", "", "&c&l» &7Ksiazka zakleta na podany enchant, ktorej", "   &7mozesz uzyc by ulepszyc swoje przedmioty!", "",
					ColorUtil.formatHEX("&c&l» &7Rodzaj zaklecia:#80ff1f Looting"), ColorUtil.formatHEX(" &8> &7Poziom zaklecia: &b3"), "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2033400 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.ENCHANTED_BOOK, false);
		}

		if (name.equalsIgnoreCase("sklepksiazki13"))
		{
			String[] lore = {"&c&l» &a&lENCHANTOWANA KSIAZKA", "", "&c&l» &7Ksiazka zakleta na podany enchant, ktorej", "   &7mozesz uzyc by ulepszyc swoje przedmioty!", "",
					ColorUtil.formatHEX("&c&l» &7Rodzaj zaklecia:#80ff1f Fortune"), ColorUtil.formatHEX(" &8> &7Poziom zaklecia: &b3"), "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2033600 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.ENCHANTED_BOOK, false);
		}

		if (name.equalsIgnoreCase("sklepksiazki14"))
		{
			String[] lore = {"&c&l» &a&lENCHANTOWANA KSIAZKA", "", "&c&l» &7Ksiazka zakleta na podany enchant, ktorej", "   &7mozesz uzyc by ulepszyc swoje przedmioty!", "",
					ColorUtil.formatHEX("&c&l» &7Rodzaj zaklecia:#80ff1f Feather Falling"), ColorUtil.formatHEX(" &8> &7Poziom zaklecia: &b4"), "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2033300 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.ENCHANTED_BOOK, false);
		}

		if (name.equalsIgnoreCase("sklepksiazki15"))
		{
			String[] lore = {"&c&l» &a&lENCHANTOWANA KSIAZKA", "", "&c&l» &7Ksiazka zakleta na podany enchant, ktorej", "   &7mozesz uzyc by ulepszyc swoje przedmioty!", "",
					ColorUtil.formatHEX("&c&l» &7Rodzaj zaklecia:#80ff1f Thorns"), ColorUtil.formatHEX(" &8> &7Poziom zaklecia: &b3"), "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2033000 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.ENCHANTED_BOOK, false);
		}

		if (name.equalsIgnoreCase("sklepksiazki16"))
		{
			String[] lore = {"&c&l» &a&lENCHANTOWANA KSIAZKA", "", "&c&l» &7Ksiazka zakleta na podany enchant, ktorej", "   &7mozesz uzyc by ulepszyc swoje przedmioty!", "",
					ColorUtil.formatHEX("&c&l» &7Rodzaj zaklecia:#80ff1f Depth Strider"), ColorUtil.formatHEX(" &8> &7Poziom zaklecia: &b3"), "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2033600 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.ENCHANTED_BOOK, false);
		}

		if (name.equalsIgnoreCase("sklepksiazki17"))
		{
			String[] lore = {"&c&l» &a&lENCHANTOWANA KSIAZKA", "", "&c&l» &7Ksiazka zakleta na podany enchant, ktorej", "   &7mozesz uzyc by ulepszyc swoje przedmioty!", "",
					ColorUtil.formatHEX("&c&l» &7Rodzaj zaklecia:#80ff1f Luck Of The Sea"), ColorUtil.formatHEX(" &8> &7Poziom zaklecia: &b3"), "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2032700 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.ENCHANTED_BOOK, false);
		}

		if (name.equalsIgnoreCase("sklepksiazki18"))
		{
			String[] lore = {"&c&l» &a&lENCHANTOWANA KSIAZKA", "", "&c&l» &7Ksiazka zakleta na podany enchant, ktorej", "   &7mozesz uzyc by ulepszyc swoje przedmioty!", "",
					ColorUtil.formatHEX("&c&l» &7Rodzaj zaklecia:#80ff1f Lure"), ColorUtil.formatHEX(" &8> &7Poziom zaklecia: &b3"), "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2032600 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.ENCHANTED_BOOK, false);
		}

		if (name.equalsIgnoreCase("sklepksiazki19"))
		{
			String[] lore = {"&c&l» &a&lENCHANTOWANA KSIAZKA", "", "&c&l» &7Ksiazka zakleta na podany enchant, ktorej", "   &7mozesz uzyc by ulepszyc swoje przedmioty!", "",
					ColorUtil.formatHEX("&c&l» &7Rodzaj zaklecia:#80ff1f Quick Charge"), ColorUtil.formatHEX(" &8> &7Poziom zaklecia: &b3"), "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2032400 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.ENCHANTED_BOOK, false);
		}

		if (name.equalsIgnoreCase("sklepksiazki20"))
		{
			String[] lore = {"&c&l» &a&lENCHANTOWANA KSIAZKA", "", "&c&l» &7Ksiazka zakleta na podany enchant, ktorej", "   &7mozesz uzyc by ulepszyc swoje przedmioty!", "",
					ColorUtil.formatHEX("&c&l» &7Rodzaj zaklecia:#80ff1f Channelling"), ColorUtil.formatHEX(" &8> &7Poziom zaklecia: #fc7474✖"), "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2032900 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.ENCHANTED_BOOK, false);
		}

		if (name.equalsIgnoreCase("sklepksiazki21"))
		{
			String[] lore = {"&c&l» &a&lENCHANTOWANA KSIAZKA", "", "&c&l» &7Ksiazka zakleta na podany enchant, ktorej", "   &7mozesz uzyc by ulepszyc swoje przedmioty!", "",
					ColorUtil.formatHEX("&c&l» &7Rodzaj zaklecia:#80ff1f Multishot"), ColorUtil.formatHEX(" &8> &7Poziom zaklecia: #fc7474✖"), "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2033500 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.ENCHANTED_BOOK, false);
		}

		if (name.equalsIgnoreCase("sklepksiazki22"))
		{
			String[] lore = {"&c&l» &a&lENCHANTOWANA KSIAZKA", "", "&c&l» &7Ksiazka zakleta na podany enchant, ktorej", "   &7mozesz uzyc by ulepszyc swoje przedmioty!", "",
					ColorUtil.formatHEX("&c&l» &7Rodzaj zaklecia:#80ff1f Impaling"), ColorUtil.formatHEX(" &8> &7Poziom zaklecia: &b5"), "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2033700 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.ENCHANTED_BOOK, false);
		}

		if (name.equalsIgnoreCase("sklepksiazki23"))
		{
			String[] lore = {"&c&l» &a&lENCHANTOWANA KSIAZKA", "", "&c&l» &7Ksiazka zakleta na podany enchant, ktorej", "   &7mozesz uzyc by ulepszyc swoje przedmioty!", "",
					ColorUtil.formatHEX("&c&l» &7Rodzaj zaklecia:#80ff1f Piercing"), ColorUtil.formatHEX(" &8> &7Poziom zaklecia: &b4"), "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2033400 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.ENCHANTED_BOOK, false);
		}

		if (name.equalsIgnoreCase("sklepksiazki24"))
		{
			String[] lore = {"&c&l» &a&lENCHANTOWANA KSIAZKA", "", "&c&l» &7Ksiazka zakleta na podany enchant, ktorej", "   &7mozesz uzyc by ulepszyc swoje przedmioty!", "",
					ColorUtil.formatHEX("&c&l» &7Rodzaj zaklecia:#80ff1f Riptide"), ColorUtil.formatHEX(" &8> &7Poziom zaklecia: &b3"), "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2031900 ⛃"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.ENCHANTED_BOOK, false);
		}

		if (name.equalsIgnoreCase("sklepspecjalne1"))
		{
			String[] lore = {"&c&l» &a&lNIEZNISZCZALNA KUSZA", "", "&c&l» &7Kusza z permanentnym zakleciem unbreakable,", "   &7ktore sprawi, ze Twoja kusza nigdy sie nie zepsuje!", "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2033100 ⛃"), "", ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.CROSSBOW, false);
		}

		if (name.equalsIgnoreCase("sklepspecjalne2"))
		{
			String[] lore = {"&c&l» &a&lNIEZNISZCZALNY KILOF", "", "&c&l» &7Kilof z permanentnym zakleciem unbreakable,", "   &7ktore sprawi, ze Twoj kilof nigdy sie nie zepsuje!", "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2034600 ⛃"), "", ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.GOLDEN_PICKAXE, false);
		}

		if (name.equalsIgnoreCase("sklepspecjalne3"))
		{
			String[] lore = {"&c&l» &a&lNIEZNISZCZALNA SIEKIERA", "", "&c&l» &7Siekiera z permanentnym zakleciem unbreakable,", "   &7ktore sprawi, ze Twoja siekiera nigdy sie nie zepsuje!", "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2032900 ⛃"), "", ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.GOLDEN_AXE, false);
		}

		if (name.equalsIgnoreCase("sklepspecjalne4"))
		{
			String[] lore = {"&c&l» &a&lNIEZNISZCZALNA LOPATA", "", "&c&l» &7Lopata z permanentnym zakleciem unbreakable,", "   &7ktore sprawi, ze Twoja lopata nigdy sie nie zepsuje!", "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2032600 ⛃"), "", ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.GOLDEN_SHOVEL, false);
		}

		if (name.equalsIgnoreCase("sklepspecjalne5"))
		{
			String[] lore = {"&c&l» &a&lNIEZNISZCZALNA MOTYKA", "", "&c&l» &7Motyka z permanentnym zakleciem unbreakable,", "   &7ktore sprawi, ze Twoja motyka nigdy sie nie zepsuje!", "",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff2031400 ⛃"), "", ColorUtil.formatHEX("   #666666&o(LPM - Kupno przedmiotu)"), ""};

			return createInvItem(lore, Material.GOLDEN_HOE, false);
		}

		if (name.equalsIgnoreCase("sklepitemy1"))
		{
			String[] lore = {"&c&l» &a&lCOBBLESTONE", "", "&c&l» &7Przedmiot: Cobblestone", " &8> &7Ilosc: 16", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f4 ⛃ &7(16 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74742 ⛃ &7(8 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.COBBLESTONE, 16, false);
		}

		if (name.equalsIgnoreCase("sklepitemy2"))
		{
			String[] lore = {"&c&l» &a&lNETHERRACK", "", "&c&l» &7Przedmiot: Netherrack", " &8> &7Ilosc: 16", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f4 ⛃ &7(16 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74742 ⛃ &7(8 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.NETHERRACK, 16, false);
		}

		if (name.equalsIgnoreCase("sklepitemy3"))
		{
			String[] lore = {"&c&l» &a&lKAMIEN", "", "&c&l» &7Przedmiot: Kamien", " &8> &7Ilosc: 16", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f4 ⛃ &7(16 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74743 ⛃ &7(12 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.STONE, 16, false);
		}

		if (name.equalsIgnoreCase("sklepitemy4"))
		{
			String[] lore = {"&c&l» &a&lZIEMIA", "", "&c&l» &7Przedmiot: Ziemia", " &8> &7Ilosc: 16", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(32 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74743 ⛃ &7(12 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.DIRT, 16, false);
		}

		if (name.equalsIgnoreCase("sklepitemy5"))
		{
			String[] lore = {"&c&l» &a&lBLOK TRAWY", "", "&c&l» &7Przedmiot: Blok trawy", " &8> &7Ilosc: 16", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f16 ⛃ &7(64 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74746 ⛃ &7(24 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.GRASS_BLOCK, 16, false);
		}

		if (name.equalsIgnoreCase("sklepitemy6"))
		{
			String[] lore = {"&c&l» &a&lBLOK NETHEROWEJ TRAWY", "", "&c&l» &7Przedmiot: Blok netherowej trawy", " &8> &7Ilosc: 16", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f16 ⛃ &7(64 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74748 ⛃ &7(32 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.CRIMSON_NYLIUM, 16, false);
		}

		if (name.equalsIgnoreCase("sklepitemy7"))
		{
			String[] lore = {"&c&l» &a&lBLOK NETHEROWEJ TRAWY", "", "&c&l» &7Przedmiot: Blok netherowej trawy", " &8> &7Ilosc: 16", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f16 ⛃ &7(64 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74748 ⛃ &7(32 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.WARPED_NYLIUM, 16, false);
		}

		if (name.equalsIgnoreCase("sklepitemy8"))
		{
			String[] lore = {"&c&l» &a&lGRANIT", "", "&c&l» &7Przedmiot: Granit", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(64 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74744 ⛃ &7(32 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.GRANITE, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy9"))
		{
			String[] lore = {"&c&l» &a&lDIORYT", "", "&c&l» &7Przedmiot: Dioryt", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(64 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74744 ⛃ &7(32 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.DIORITE, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy10"))
		{
			String[] lore = {"&c&l» &a&lANDEZYT", "", "&c&l» &7Przedmiot: Andezyt", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(64 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74744 ⛃ &7(32 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.ANDESITE, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy11"))
		{
			String[] lore = {"&c&l» &a&lZWIR", "", "&c&l» &7Przedmiot: Zwir", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(64 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74744 ⛃ &7(32 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.GRAVEL, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy12"))
		{
			String[] lore = {"&c&l» &a&lPIASEK", "", "&c&l» &7Przedmiot: Piasek", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f4 ⛃ &7(32 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74742 ⛃ &7(16 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.SAND, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy13"))
		{
			String[] lore = {"&c&l» &a&lSANDSTONE", "", "&c&l» &7Przedmiot: Sandstone", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f4 ⛃ &7(32 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74743 ⛃ &7(24 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.SANDSTONE, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy14"))
		{
			String[] lore = {"&c&l» &a&lCZERWONY PIASEK", "", "&c&l» &7Przedmiot: Czerwony piasek", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(64 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74743 ⛃ &7(24 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.RED_SAND, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy15"))
		{
			String[] lore = {"&c&l» &a&lCZERWONY SANDSTONE", "", "&c&l» &7Przedmiot: Czerwony sandstone", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(64 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74744 ⛃ &7(32 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.RED_SANDSTONE, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy16"))
		{
			String[] lore = {"&c&l» &a&lDREWNO DEBOWE", "", "&c&l» &7Przedmiot: Drewno debowe", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(64 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74744 ⛃ &7(32 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.OAK_LOG, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy17"))
		{
			String[] lore = {"&c&l» &a&lDREWNO SWIERKOWE", "", "&c&l» &7Przedmiot: Drewno swierkowe", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(64 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74744 ⛃ &7(32 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.SPRUCE_LOG, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy18"))
		{
			String[] lore = {"&c&l» &a&lDREWNO BRZOZOWE", "", "&c&l» &7Przedmiot: Drewno brzozowe", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(64 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74744 ⛃ &7(32 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.BIRCH_LOG, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy19"))
		{
			String[] lore = {"&c&l» &a&lDREWNO DZUNGLOWE", "", "&c&l» &7Przedmiot: Drewno dzunglowe", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(64 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74744 ⛃ &7(32 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.JUNGLE_LOG, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy20"))
		{
			String[] lore = {"&c&l» &a&lDREWNO AKACJOWE", "", "&c&l» &7Przedmiot: Drewno akacjowe", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(64 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74744 ⛃ &7(32 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.ACACIA_LOG, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy21"))
		{
			String[] lore = {"&c&l» &a&lDREWNO CIEMNEGO DEBU", "", "&c&l» &7Przedmiot: Drewno ciemnego debu", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(64 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74744 ⛃ &7(32 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.DARK_OAK_LOG, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy22"))
		{
			String[] lore = {"&c&l» &a&lDREWNO NETHEROWE", "", "&c&l» &7Przedmiot: Drewno netherowe", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f12 ⛃ &7(96 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74746 ⛃ &7(48 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.CRIMSON_STEM, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy23"))
		{
			String[] lore = {"&c&l» &a&lDREWNO NETHEROWE", "", "&c&l» &7Przedmiot: Drewno netherowe", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f12 ⛃ &7(96 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74746 ⛃ &7(48 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.WARPED_STEM, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy24"))
		{
			String[] lore = {"&c&l» &a&lLISCIE DEBOWE", "", "&c&l» &7Przedmiot: Liscie debowe", " &8> &7Ilosc: 16", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(32 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74743 ⛃ &7(12 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.OAK_LEAVES, 16, false);
		}

		if (name.equalsIgnoreCase("sklepitemy25"))
		{
			String[] lore = {"&c&l» &a&lLISCIE SWIERKOWE", "", "&c&l» &7Przedmiot: Liscie swierkowe", " &8> &7Ilosc: 16", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(32 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74743 ⛃ &7(12 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.SPRUCE_LEAVES, 16, false);
		}

		if (name.equalsIgnoreCase("sklepitemy26"))
		{
			String[] lore = {"&c&l» &a&lLISCIE BRZOZOWE", "", "&c&l» &7Przedmiot: Liscie brzozowe", " &8> &7Ilosc: 16", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(32 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74743 ⛃ &7(12 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.BIRCH_LEAVES, 16, false);
		}

		if (name.equalsIgnoreCase("sklepitemy27"))
		{
			String[] lore = {"&c&l» &a&lLISCIE DZUNGLOWE", "", "&c&l» &7Przedmiot: Liscie dzunglowe", " &8> &7Ilosc: 16", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(32 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74743 ⛃ &7(12 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.JUNGLE_LEAVES, 16, false);
		}

		if (name.equalsIgnoreCase("sklepitemy28"))
		{
			String[] lore = {"&c&l» &a&lLISCIE AKACJOWE", "", "&c&l» &7Przedmiot: Liscie akacjowe", " &8> &7Ilosc: 16", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(32 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74743 ⛃ &7(12 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.ACACIA_LEAVES, 16, false);
		}

		if (name.equalsIgnoreCase("sklepitemy29"))
		{
			String[] lore = {"&c&l» &a&lLISCIE CIEMNEGO DEBU", "", "&c&l» &7Przedmiot: Liscie ciemnego debu", " &8> &7Ilosc: 16", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(32 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74743 ⛃ &7(12 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.DARK_OAK_LEAVES, 16, false);
		}

		if (name.equalsIgnoreCase("sklepitemy30"))
		{
			String[] lore = {"&c&l» &a&lBLOK SNIEGU", "", "&c&l» &7Przedmiot: Blok sniegu", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f32 ⛃ &7(256 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc747416 ⛃ &7(128 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.SNOW_BLOCK, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy31"))
		{
			String[] lore = {"&c&l» &a&lLOD", "", "&c&l» &7Przedmiot: Lod", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f16 ⛃ &7(96 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74748 ⛃ &7(64 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.ICE, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy32"))
		{
			String[] lore = {"&c&l» &a&lZBITY LOD", "", "&c&l» &7Przedmiot: Zbity lod", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f24 ⛃ &7(192 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc747412 ⛃ &7(96 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.PACKED_ICE, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy33"))
		{
			String[] lore = {"&c&l» &a&lMORSKA LATARNIA", "", "&c&l» &7Przedmiot: Morska latarnia", " &8> &7Ilosc: 1", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(512 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc7474✖ &7(Nie ma kurwa!)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.SEA_LANTERN, 1, false);
		}

		if (name.equalsIgnoreCase("sklepitemy34"))
		{
			String[] lore = {"&c&l» &a&lPRYZMARYN", "", "&c&l» &7Przedmiot: Pryzmaryn", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f16 ⛃ &7(128 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc7474✖ &7(Nie ma kurwa!)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.PRISMARINE, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy35"))
		{
			String[] lore = {"&c&l» &a&lCIEMNY PRYZMARYN", "", "&c&l» &7Przedmiot: Ciemny pryzmaryn", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f24 ⛃ &7(192 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc747412 ⛃ &7(96 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.DARK_PRISMARINE, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy36"))
		{
			String[] lore = {"&c&l» &a&lCEGLY NETHEROWE", "", "&c&l» &7Przedmiot: Cegly netherowe", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(64 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74744 ⛃ &7(32 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.NETHER_BRICKS, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy37"))
		{
			String[] lore = {"&c&l» &a&lJASNOGLAZ", "", "&c&l» &7Przedmiot: Jasnoglaz", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f24 ⛃ &7(192 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc747412 ⛃ &7(96 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.GLOWSTONE, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy38"))
		{
			String[] lore = {"&c&l» &a&lPIASEK DUSZ", "", "&c&l» &7Przedmiot: Piasek dusz", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f24 ⛃ &7(192 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74746 ⛃ &7(48 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.SOUL_SAND, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy39"))
		{
			String[] lore = {"&c&l» &a&lBLACKSTONE", "", "&c&l» &7Przedmiot: Blackstone", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(64 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74743 ⛃ &7(24 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.BLACKSTONE, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy40"))
		{
			String[] lore = {"&c&l» &a&lOBSYDIAN", "", "&c&l» &7Przedmiot: Obsydian", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f32 ⛃ &7(256 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc747416 ⛃ &7(128 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.OBSIDIAN, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy41"))
		{
			String[] lore = {"&c&l» &a&lKAMIEN KRESU", "", "&c&l» &7Przedmiot: Kamien kresu", " &8> &7Ilosc: 16", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f12 ⛃ &7(48 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74748 ⛃ &7(32 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.END_STONE, 16, false);
		}

		if (name.equalsIgnoreCase("sklepitemy42"))
		{
			String[] lore = {"&c&l» &a&lCEGLY KRESU", "", "&c&l» &7Przedmiot: Cegly kresu", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f36 ⛃ &7(288 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc747412 ⛃ &7(96 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.END_STONE_BRICKS, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy43"))
		{
			String[] lore = {"&c&l» &a&lBLOK PURPURU", "", "&c&l» &7Przedmiot: Blok purpuru", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f24 ⛃ &7(192 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc7474✖ &7(Nie ma kurwa!)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.PURPUR_BLOCK, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy44"))
		{
			String[] lore = {"&c&l» &a&lOWOC CHORUSU", "", "&c&l» &7Przedmiot: Owoc chorus", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f32 ⛃ &7(256 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74748 ⛃ &7(64 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.CHORUS_FRUIT, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy45"))
		{
			String[] lore = {"&c&l» &a&lKWIAT CHORUSU", "", "&c&l» &7Przedmiot: Kwiat chorusu", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f✖ &7(Nie ma kurwa!)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc747412 ⛃ &7(96 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.CHORUS_FLOWER, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy46"))
		{
			String[] lore = {"&c&l» &a&lNETHERITE", "", "&c&l» &7Przedmiot: Netherite", " &8> &7Ilosc: 1", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #fc74741000 ⛃ &7(64.000 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #80ff1f400 ⛃ &7(25.600 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.NETHERITE_INGOT, 1, false);
		}

		if (name.equalsIgnoreCase("sklepitemy47"))
		{
			String[] lore = {"&c&l» &a&lSZMARAGD", "", "&c&l» &7Przedmiot: Szmaragd", " &8> &7Ilosc: 1", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f40 ⛃ &7(2.560 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc747420 ⛃ &7(1.280 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.EMERALD, 1, false);
		}

		if (name.equalsIgnoreCase("sklepitemy48"))
		{
			String[] lore = {"&c&l» &a&lDIAMENT", "", "&c&l» &7Przedmiot: Diament", " &8> &7Ilosc: 1", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f160 ⛃ &7(10.240 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc747460 ⛃ &7(3.840 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.DIAMOND, 1, false);
		}

		if (name.equalsIgnoreCase("sklepitemy49"))
		{
			String[] lore = {"&c&l» &a&lZLOTO", "", "&c&l» &7Przedmiot: Zloto", " &8> &7Ilosc: 1", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f32 ⛃ &7(2.048 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74746 ⛃ &7(384 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.GOLD_INGOT, 1, false);
		}

		if (name.equalsIgnoreCase("sklepitemy50"))
		{
			String[] lore = {"&c&l» &a&lZELAZO", "", "&c&l» &7Przedmiot: Zelazo", " &8> &7Ilosc: 1", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f16 ⛃ &7(1.024 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74744 ⛃ &7(256 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.IRON_INGOT, 1, false);
		}

		if (name.equalsIgnoreCase("sklepitemy51"))
		{
			String[] lore = {"&c&l» &a&lKWARC", "", "&c&l» &7Przedmiot: Kwarc", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f24 ⛃ &7(192 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc747416 ⛃ &7(128 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.QUARTZ, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy52"))
		{
			String[] lore = {"&c&l» &a&lMIEDZ", "", "&c&l» &7Przedmiot: Miedz", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f24 ⛃ &7(192 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74748 ⛃ &7(64 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.BARRIER, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy53"))
		{
			String[] lore = {"&c&l» &a&lREDSTONE", "", "&c&l» &7Przedmiot: Redstone", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f12 ⛃ &7(96 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74748 ⛃ &7(64 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.REDSTONE, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy54"))
		{
			String[] lore = {"&c&l» &a&lLAPIS LAZULI", "", "&c&l» &7Przedmiot: Lapis Lazuli", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f24 ⛃ &7(192 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74746 ⛃ &7(48 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.LAPIS_LAZULI, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy55"))
		{
			String[] lore = {"&c&l» &a&lWEGIEL", "", "&c&l» &7Przedmiot: Wegiel", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f12 ⛃ &7(96 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74744 ⛃ &7(32 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.COAL, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy56"))
		{
			String[] lore = {"&c&l» &a&lPSZENICA", "", "&c&l» &7Przedmiot: Pszenica", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f12 ⛃ &7(96 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74744 ⛃ &7(32 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.WHEAT, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy57"))
		{
			String[] lore = {"&c&l» &a&lMARCHEWKI", "", "&c&l» &7Przedmiot: Marchewki", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f16 ⛃ &7(128 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74746 ⛃ &7(48 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.CARROT, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy58"))
		{
			String[] lore = {"&c&l» &a&lZIEMNIAKI", "", "&c&l» &7Przedmiot: Ziemniaki", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f12 ⛃ &7(96 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74744 ⛃ &7(32 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.POTATO, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy59"))
		{
			String[] lore = {"&c&l» &a&lMELONY", "", "&c&l» &7Przedmiot: Melony", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f32 ⛃ &7(256 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74746 ⛃ &7(48 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.MELON_SLICE, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy60"))
		{
			String[] lore = {"&c&l» &a&lBURAKI", "", "&c&l» &7Przedmiot: Buraki", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f24 ⛃ &7(192 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74748 ⛃ &7(64 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.BEETROOT, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy61"))
		{
			String[] lore = {"&c&l» &a&lBAMBUS", "", "&c&l» &7Przedmiot: Bambus", " &8> &7Ilosc: 16", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f48 ⛃ &7(192 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74744 ⛃ &7(16 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.BAMBOO, 16, false);
		}

		if (name.equalsIgnoreCase("sklepitemy62"))
		{
			String[] lore = {"&c&l» &a&lTRZCINA", "", "&c&l» &7Przedmiot: Trzcina", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f16 ⛃ &7(128 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74748 ⛃ &7(64 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.SUGAR_CANE, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy63"))
		{
			String[] lore = {"&c&l» &a&lKAKTUS", "", "&c&l» &7Przedmiot: Kaktus", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(64 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74746 ⛃ &7(48 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.CACTUS, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy64"))
		{
			String[] lore = {"&c&l» &a&lDYNIA", "", "&c&l» &7Przedmiot: Dynia", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f12 ⛃ &7(96 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74748 ⛃ &7(64 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.PUMPKIN, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy65"))
		{
			String[] lore = {"&c&l» &a&lJAJKA", "", "&c&l» &7Przedmiot: Jajka", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f12 ⛃ &7(24 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74744 ⛃ &7(8 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.EGG, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy66"))
		{
			String[] lore = {"&c&l» &a&lJABLKO", "", "&c&l» &7Przedmiot: Jablko", " &8> &7Ilosc: 1", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(512 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74742 ⛃ &7(128 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.APPLE, 1, false);
		}

		if (name.equalsIgnoreCase("sklepitemy67"))
		{
			String[] lore = {"&c&l» &a&lZLOTE JABLKO", "", "&c&l» &7Przedmiot: Zlote jablko", " &8> &7Ilosc: 1", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f200 ⛃ &7(12.800 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc747464 ⛃ &7(4.096 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.GOLDEN_APPLE, 1, false);
		}

		if (name.equalsIgnoreCase("sklepitemy68"))
		{
			String[] lore = {"&c&l» &a&lSUROWE MIESO", "", "&c&l» &7Przedmiot: Surowe mieso", " &8> &7Ilosc: 16", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(32 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74744 ⛃ &7(16 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.PORKCHOP, 16, false);
		}

		if (name.equalsIgnoreCase("sklepitemy69"))
		{
			String[] lore = {"&c&l» &a&lSUROWE MIESO", "", "&c&l» &7Przedmiot: Surowe mieso", " &8> &7Ilosc: 16", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(32 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74744 ⛃ &7(16 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.RABBIT, 16, false);
		}

		if (name.equalsIgnoreCase("sklepitemy70"))
		{
			String[] lore = {"&c&l» &a&lSUROWE MIESO", "", "&c&l» &7Przedmiot: Surowe mieso", " &8> &7Ilosc: 16", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(32 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74744 ⛃ &7(16 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.BEEF, 16, false);
		}

		if (name.equalsIgnoreCase("sklepitemy71"))
		{
			String[] lore = {"&c&l» &a&lSUROWE MIESO", "", "&c&l» &7Przedmiot: Surowe mieso", " &8> &7Ilosc: 16", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(32 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74744 ⛃ &7(16 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.CHICKEN, 16, false);
		}

		if (name.equalsIgnoreCase("sklepitemy72"))
		{
			String[] lore = {"&c&l» &a&lSUROWE MIESO", "", "&c&l» &7Przedmiot: Surowe mieso", " &8> &7Ilosc: 16", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f8 ⛃ &7(32 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74744 ⛃ &7(16 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.MUTTON, 16, false);
		}

		if (name.equalsIgnoreCase("sklepitemy73"))
		{
			String[] lore = {"&c&l» &a&lSKORA", "", "&c&l» &7Przedmiot: Skora", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f24 ⛃ &7(256 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc747412 ⛃ &7(128 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.LEATHER, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy74"))
		{
			String[] lore = {"&c&l» &a&lPLASTRY MIODU", "", "&c&l» &7Przedmiot: Plastry miody", " &8> &7Ilosc: 4", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f16 ⛃ &7(256 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc747412 ⛃ &7(192 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.HONEYCOMB, 4, false);
		}

		if (name.equalsIgnoreCase("sklepitemy75"))
		{
			String[] lore = {"&c&l» &a&lBRODAWKI", "", "&c&l» &7Przedmiot: Brodawki", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f48 ⛃ &7(384 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc747416 ⛃ &7(128 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.NETHER_WART, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy76"))
		{
			String[] lore = {"&c&l» &a&lKULA SZLAMU", "", "&c&l» &7Przedmiot: Kula szlamu", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f64 ⛃ &7(512 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc7474✖ &7(Nie ma kurwa!)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.SLIME_BALL, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy77"))
		{
			String[] lore = {"&c&l» &a&lENDERPERLA", "", "&c&l» &7Przedmiot: Enderperla", " &8> &7Ilosc: 1", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f32 ⛃ &7(512 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74744 ⛃ &7(64 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.ENDER_PEARL, 1, false);
		}

		if (name.equalsIgnoreCase("sklepitemy78"))
		{
			String[] lore = {"&c&l» &a&lBLAZE ROD", "", "&c&l» &7Przedmiot: Blaze rod", " &8> &7Ilosc: 1", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f48 ⛃ &7(3.072 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74748 ⛃ &7(512 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.BLAZE_ROD, 1, false);
		}

		if (name.equalsIgnoreCase("sklepitemy79"))
		{
			String[] lore = {"&c&l» &a&lLZA GHASTA", "", "&c&l» &7Przedmiot: Lza ghasta", " &8> &7Ilosc: 1", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f128 ⛃ &7(8.192 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc747432 ⛃ &7(2.048 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.GHAST_TEAR, 1, false);
		}

		if (name.equalsIgnoreCase("sklepitemy80"))
		{
			String[] lore = {"&c&l» &a&lOKO PAJAKA", "", "&c&l» &7Przedmiot: Oko pajaka", " &8> &7Ilosc: 1", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f24 ⛃ &7(1.536 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc74748 ⛃ &7(512 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.SPIDER_EYE, 1, false);
		}

		if (name.equalsIgnoreCase("sklepitemy81"))
		{
			String[] lore = {"&c&l» &a&lGUNPOWDER", "", "&c&l» &7Przedmiot: Gunpowder", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f32 ⛃ &7(256 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc747416 ⛃ &7(128 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.GUNPOWDER, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy82"))
		{
			String[] lore = {"&c&l» &a&lKOSCI", "", "&c&l» &7Przedmiot: Kosci", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f24 ⛃ &7(192 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc747412 ⛃ &7(96 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.BONE, 8, false);
		}

		if (name.equalsIgnoreCase("sklepitemy83"))
		{
			String[] lore = {"&c&l» &a&lNICI", "", "&c&l» &7Przedmiot: Nici", " &8> &7Ilosc: 8", "",
					ColorUtil.formatHEX("&c&l» &7Cena kupna: #80ff1f24 ⛃ &7(192 ⛃/stak)"),
					ColorUtil.formatHEX("&c&l» &7Cena sprzedazy: #fc747418 ⛃ &7(144 ⛃/stak)"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oPPM - Sprzedaz jednej sztuki przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + LPM - Kupno staka przedmiotu"),
					ColorUtil.formatHEX("   #666666&oSHIFT + PPM - Sprzedaz staka przedmiotu)"),""};

			return createAmountedInvItem(lore, Material.STRING, 8, false);
		}

        if (name.equalsIgnoreCase("postacslub"))
        {
            String[] lore = {""};

            if (DPlayerManager.getInstance().getMarry(player) == null)
                lore = new String[]{"&c&l» &a&lSLUB",
                        "",
                        "&c&l» &7Zakochanym wiedza o ich partnerze jest bardzo",
                        "   &7wazna, wszelkie informacje o Twoim zwiazku znajdziesz",
                        "   &7pod komenda /wyspy. Nie no zartuje, sa ponizej!",
                        "",
                        " &8> &7Jednak najpierw musze Cie czegos nauczyc..",
                        "   &7Nie posiadasz zadnego partnera, wiec",
                        "   &7sugeruje umyc swoj brzydki ryj, kupic",
                        "   &7cos ladnego i znalezc sobie kogos!",
                        ""};
            else
            {
                int timexp = TimeUtil.getDifferenceInMinutes(DPlayerManager.getInstance().getMarryDate(player)) / 6;
                int xp = DPlayerManager.getInstance().getMarryLevel(player) + DPlayerManager.getInstance().getMarryLevel(DPlayerManager.getInstance().getMarry(player)) + timexp;
                int level = xp / 100;
                String s = Integer.toString(xp);
                String percentage;
                if (xp < 10)
                    percentage = "0";
                else
                {
                    percentage = Integer.toString(xp).charAt(s.length() - 2) + "0";
                    if (percentage.equalsIgnoreCase("00"))
                        percentage = "0";
                }

                lore = new String[]{"&c&l» &a&lSLUB",
                        "",
                        "&c&l» &7Zakochanym wiedza o ich partnerze jest bardzo",
                        "   &7wazna, wszelkie informacje o Twoim zwiazku znajdziesz",
                        "   &7pod komenda /wyspy. Nie no zartuje, sa ponizej!",
                        "",
                        ColorUtil.formatHEX(" &8> #ffc936Jestes w zwiazku z: &7" + ChatUtil.returnPlayerColor(DPlayerManager.getInstance().getMarry(player)) + DPlayerManager.getInstance().getMarry(player)),
                        ColorUtil.formatHEX(" &8> #ffc936Data waszego slubu: &7" + ChatUtil.returnPlayerColor(DPlayerManager.getInstance().getMarry(player)) + DPlayerManager.getInstance().getMarryDate(player)),
                        ColorUtil.formatHEX(" &8> #ffc936Poziom waszego zwiazku: &7" + ChatUtil.returnPlayerColor(DPlayerManager.getInstance().getMarry(player)) + level + " &7(" + percentage + "%)"),
                        ""};
            }

            return createInvItem(lore, Material.POPPY, false);
        }

        if (name.equalsIgnoreCase("postacstatystyki"))
        {
            String[] lore = {"&c&l» &a&lSTATYSTYKI", "", "&c&l» &7Dzien dobry nasz &mwartosciowy&r &7graczu &6☻", "   &7Ponizej znajdziesz wszystkie swoje statystyki",
                    "   &7oraz informacje jakie o Tobie posiadamy!", "", ColorUtil.formatHEX(" &8> #ffc936Nazwa: &7" + player.getName()),
                    ColorUtil.formatHEX(" &8> #ffc936UUID: &7" + DPlayerManager.getInstance().getUUID(player)),
                    ColorUtil.formatHEX(" &8> #ffc936Adres IP: &7" + Objects.requireNonNull(player.spigot().getRawAddress())),
                    ColorUtil.formatHEX(" &8> #ffc936Opoznienie z serwerem: &7" + ServerUtil.getPing(player) + "ms"),
                    ColorUtil.formatHEX(" &8> #ffc936Konto premium #fcff33(BETA)#ffc936: &7" + ChatUtil.isPlayerPremium(player)),
                    "",
                    ColorUtil.formatHEX(" &8> #ffc936Ranga: &7" + ChatUtil.getColoredRank(player)),
                    ColorUtil.formatHEX(" &8> #ffc936Kolor: &7" + ChatUtil.returnColoredPlayerColor(player.getName())),
                    ColorUtil.formatHEX(" &8> #ffc936Ostatnie wyciszenie: &7" + ChatUtil.getLastMuteColored(player.getName())),
                    ColorUtil.formatHEX(" &8> #ffc936Wykonane questy: #fcff33" + QuestUtil.getCompletedQuests(player) + "/#fcff3314"),
                    ColorUtil.formatHEX(" &8> #ffc936Poziom ulepszenia schowku: #80ff1f" + (DPlayerManager.getInstance().getSchowekLevel(player) + 1) + "&7/#80ff1f4"),
                    "",
                    ColorUtil.formatHEX(" &8> #ffc936Monety: #fcff33" + DPlayerManager.getInstance().getMoney(player) + " ⛃"),
                    ColorUtil.formatHEX(" &8> #ffc936Punkty umiejetnosci: &e" + DPlayerManager.getInstance().getSP(player) + " ☀"),
                    ColorUtil.formatHEX(" &8> #ffc936Zabojstwa: #80ff1f" + DPlayerManager.getInstance().getKills(player) + " ⚔"),
                    ColorUtil.formatHEX(" &8> #ffc936Smierci: #fc7474" + DPlayerManager.getInstance().getDeaths(player) + " ☠"),
                    ""};

            return createPlayerHeadInvItem(lore, player.getName(), true);
        }

		if (name.equalsIgnoreCase("postacloading"))
		{
			String[] lore = {"&c&l» &a&lSTATYSTYKI", "", "&c&l» &7Wczytywanie statystyk...", ""};

			return createPlayerHeadInvItem(lore, player.getName(), true);
		}

        if (name.equalsIgnoreCase("postacgang"))
        {
            String[] lore = {""};

            if (GangManager.getInstance().getGang(player) == null)
                lore = new String[]{"&c&l» &a&lGANG",
                        "",
                        "&c&l» &7Nie wiesz czegos o swoim gangu lub chcesz",
                        "   &7cos sprawdzic? Wszystkie szczegolowe informacje",
                        "   &7na temat gangu znajduja sie... no zgadnij gdzie!",
                        "",
                        " &8> &7Jednak najpierw musze Cie czegos nauczyc..",
                        "   &7Dzisiejszym tematem zajec bedzie idiotyzm!",
                        "   &7Nic nie sugeruje ale moglbys chociaz byc",
                        "   &7w ktoryms z gangow na serwerze!",
                        ""};
            else
            {
                boolean base = GangManager.getInstance().getBase(GangManager.getInstance().getGang(player)) != null;

                if (base)
                {
                    String world = Objects.requireNonNull(GangManager.getInstance().getBase(GangManager.getInstance().getGang(player)).getWorld()).getName();
                    int x = GangManager.getInstance().getBase(GangManager.getInstance().getGang(player)).getBlockX();
                    int y = GangManager.getInstance().getBase(GangManager.getInstance().getGang(player)).getBlockY();
                    int z = GangManager.getInstance().getBase(GangManager.getInstance().getGang(player)).getBlockZ();

                    lore = new String[]{"&c&l» &a&lGANG",
                            "",
                            "&c&l» &7Nie wiesz czegos o swoim gangu lub chcesz",
                            "   &7cos sprawdzic? Wszystkie szczegolowe informacje",
                            "   &7na temat gangu znajduja sie... no zgadnij gdzie!",
                            "",
                            ColorUtil.formatHEX(" &8> #ffc936Nazwa gangu: &7" + ChatUtil.getValidGangColor(GangManager.getInstance().getGang(player)) + GangManager.getInstance().getGang(player)),
                            ColorUtil.formatHEX(" &8> #ffc936Design gangu: &7" + ChatUtil.getGangInChat(GangManager.getInstance().getGang(player))),
                            "",
                            ColorUtil.formatHEX(" &8> #ffc936Liczba czlonkow: &7" + ChatUtil.getValidGangColor(GangManager.getInstance().getGang(player)) + GangManager.getInstance().getMembers(GangManager.getInstance().getGang(player))),
                            ColorUtil.formatHEX(" &8> #ffc936Lider gangu: &7" + ChatUtil.getValidGangColor(GangManager.getInstance().getGang(player)) + GangManager.getInstance().getLider(GangManager.getInstance().getGang(player))),
                            ColorUtil.formatHEX(" &8> #ffc936Friendly Fire: &7" + ChatUtil.getValidGangColor(GangManager.getInstance().getGang(player)) + ChatUtil.isGangFriendlyFire(GangManager.getInstance().getGang(player))),
                            "",
                            ColorUtil.formatHEX(" &8> #ffc936Kolor gangu: &7" + ChatUtil.getColoredValidGangColor(GangManager.getInstance().getGang(player))),
                            ColorUtil.formatHEX(" &8> #ffc936Prefixy gangu: &7" + ChatUtil.getValidGangColor(GangManager.getInstance().getGang(player)) + ChatUtil.getColoredGangPrefixes(GangManager.getInstance().getGang(player))),
                            ColorUtil.formatHEX(" &8> #ffc936Ekskluzywna gwiazda: &7" + ChatUtil.isGangStar(GangManager.getInstance().getGang(player))),
                            ColorUtil.formatHEX(" &8> #ffc936Czat prywatny gangu: &7" + ChatUtil.isGangChat(GangManager.getInstance().getGang(player))),
                            "",
                            ColorUtil.formatHEX(" &8> #ffc936Lokalizacja bazy gangu:"),
                            ColorUtil.formatHEX("   &8- &7swiat: " + world),
                            ColorUtil.formatHEX("   &8- &7x: " + x),
                            ColorUtil.formatHEX("   &8- &7y: " + y),
                            ColorUtil.formatHEX("   &8- &7z: " + z),
                            ""};
                }
                else
                {
                    lore = new String[]{"&c&l» &a&lGANG",
                            "",
                            "&c&l» &7Nie wiesz czegos o swoim gangu lub chcesz",
                            "   &7cos sprawdzic? Wszystkie szczegolowe informacje",
                            "   &7na temat gangu znajduja sie... no zgadnij gdzie!",
                            "",
                            ColorUtil.formatHEX(" &8> #ffc936Nazwa gangu: &7" + ChatUtil.getValidGangColor(GangManager.getInstance().getGang(player)) + GangManager.getInstance().getGang(player)),
                            ColorUtil.formatHEX(" &8> #ffc936Design gangu: &7" + ChatUtil.getGangInChat(GangManager.getInstance().getGang(player))),
                            "",
                            ColorUtil.formatHEX(" &8> #ffc936Liczba czlonkow: &7" + ChatUtil.getValidGangColor(GangManager.getInstance().getGang(player)) + GangManager.getInstance().getMembers(GangManager.getInstance().getGang(player))),
                            ColorUtil.formatHEX(" &8> #ffc936Lider gangu: &7" + ChatUtil.getValidGangColor(GangManager.getInstance().getGang(player)) + GangManager.getInstance().getLider(GangManager.getInstance().getGang(player))),
                            ColorUtil.formatHEX(" &8> #ffc936Friendly Fire: &7" + ChatUtil.getValidGangColor(GangManager.getInstance().getGang(player)) + ChatUtil.isGangFriendlyFire(GangManager.getInstance().getGang(player))),
                            "",
                            ColorUtil.formatHEX(" &8> #ffc936Kolor gangu: &7" + ChatUtil.getColoredValidGangColor(GangManager.getInstance().getGang(player))),
                            ColorUtil.formatHEX(" &8> #ffc936Prefixy gangu: &7" + ChatUtil.getValidGangColor(GangManager.getInstance().getGang(player)) + ChatUtil.getColoredGangPrefixes(GangManager.getInstance().getGang(player))),
                            ColorUtil.formatHEX(" &8> #ffc936Ekskluzywna gwiazda: &7" + ChatUtil.isGangStar(GangManager.getInstance().getGang(player))),
                            ColorUtil.formatHEX(" &8> #ffc936Czat prywatny gangu: &7" + ChatUtil.isGangChat(GangManager.getInstance().getGang(player))),
                            "",
                            ColorUtil.formatHEX(" &8> #ffc936Lokalizacja bazy gangu:"),
                            ColorUtil.formatHEX("   &8- &7/gang baza ustaw"),
                            ""};
                }
            }

            return createInvItem(lore, Material.DIAMOND_SWORD, false);
        }

        if (name.equalsIgnoreCase("postacupgrades"))
        {
            String[] lore = {"&c&l» &a&lINFORMACJE O UMIEJETNOSCIACH",
					"",
					"&c&l» &7Na serwerze istnieje mozliwosc ulepszania",
					"   &7siebie jako swojej postaci i nie, nie chodzi nam",
					"   &7o poprawe Twojej tlustej talii czy ujemnego IQ!",
					"",
					"&c&l» &7Twoja postac moze zyskac 5 dodatkowych umiejetnosci,",
					"   &7ktore posiadaja 5 poziomow ulepszen. Opis kazdej",
					"   &7z umiejetnosci zawarty jest ponizej!",
					"",
					"&c&l» &7Aby zakupic ulepszenie, musisz posiadac",
					"   &7specjalna walute, ktora mozesz otrzymac m. in.",
					"   &7wykonujac &c/questy &7na serwerze. Mowa tu",
					"   &7oczywiscie o &ePunktach umiejetnosci (☀)&7.",
					"   &7Kazde pojedyncze ulepszenie kosztuje 1 ☀",
					"   &7i nie ma mozliwosci jego cofniecia!",
					""};

            return createInvItem(lore, Material.LEGACY_BOOK_AND_QUILL, true);
        }

        if (name.equalsIgnoreCase("postacupgrade1"))
        {
            String[] lore = {"&c&l» &c&lWITALNOSC",
					"",
					"&c&l» &7Witalnosc pozwala Twojej postaci zwiekszyc",
					"   &7swoje maksymalne zdrowie!",
					"",
					ColorUtil.formatHEX("&c&l» &7Wskaznik ulepszenia ilosc zdrowia:"),
					ColorUtil.formatHEX(" &8> &f(+2❤) " + DPlayerManager.getInstance().getUpgradeBar(player.getName(), "vitality", "&c") + " #fc7474+10❤"),
					"",
					ColorUtil.formatHEX("&c&l» &7Cena: &e1 ☀"),
					ColorUtil.formatHEX(" &8> &7Aktualny poziom: &c" + DPlayerManager.getInstance().getUpgradeLevel(player.getName(), "vitality") + "&7/&c5"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno ulepszenia)"), ""};

            return createInvItem(lore, Material.BOOK, false);
        }

        if (name.equalsIgnoreCase("postacupgrade2"))
        {
            String[] lore = {"&c&l» &e&lSZCZESCIE",
					"",
					"&c&l» &7Szczescie Twojej postaci odpowiada za",
					"   &7zwiekszenie szansy na wylowienie legendarnych morskich",
					"   &7skarbow oraz zmniejsza pobor monet po smierci!",
					"",
					ColorUtil.formatHEX("&c&l» &7Wskaznik zmniejszenia poboru monet po smierci:"),
					ColorUtil.formatHEX(" &8> &f(-2%⛃) " + DPlayerManager.getInstance().getUpgradeBar(player.getName(), "luck", "&e") + " #ffc9360%⛃"),
					"",
					ColorUtil.formatHEX("&c&l» &7Wskaznik zwiekszenia szczescia w lowieniu:"),
					ColorUtil.formatHEX(" &8> &f(+1%✪) " + DPlayerManager.getInstance().getUpgradeBar(player.getName(), "luck", "&e") + " #ffc9366%✪"),
					"",
					ColorUtil.formatHEX("&c&l» &7Cena: &e1 ☀"),
					ColorUtil.formatHEX(" &8> &7Aktualny poziom: &e" + DPlayerManager.getInstance().getUpgradeLevel(player.getName(), "luck") + "&7/&e5"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno ulepszenia)"), ""};

            return createInvItem(lore, Material.BOOK, false);
        }

        if (name.equalsIgnoreCase("postacupgrade3"))
        {
            String[] lore = {"&c&l» &a&lGRABIEZ",
					"",
					"&c&l» &7Grabiez pozwoli Ci na zwiekszenie dropu",
					"   &7z rud netheritu w netherze i jablek z drzew!",
					"",
					ColorUtil.formatHEX("&c&l» &7Wskaznik zwiekszenia szansy na obfitszy drop:"),
					ColorUtil.formatHEX(" &8> &f(+2%☠) " + DPlayerManager.getInstance().getUpgradeBar(player.getName(), "loot", "&a") + " #80ff1f10%☠"),
					"",
					ColorUtil.formatHEX("&c&l» &7Cena: &e1 ☀"),
					ColorUtil.formatHEX(" &8> &7Aktualny poziom: &a" + DPlayerManager.getInstance().getUpgradeLevel(player.getName(), "loot") + "&7/&a5"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno ulepszenia)"), ""};

            return createInvItem(lore, Material.BOOK, false);
        }

        if (name.equalsIgnoreCase("postacupgrade4"))
        {
            String[] lore = {"&c&l» &b&lWALECZNOSC",
					"",
					"&c&l» &7Walecznosc to typowa umiejetnosc bitewna.",
					"   &7Pozwala absorpcje obrazen od teleportacji perla!",
					"",
					ColorUtil.formatHEX("&c&l» &7Wskaznik absorpcji obrazen od perel:"),
					ColorUtil.formatHEX(" &8> &f(+20%☄) " + DPlayerManager.getInstance().getUpgradeBar(player.getName(), "honorable", "&b") + " #3075ff100%☄"),
					"",
					ColorUtil.formatHEX("&c&l» &7Cena: &e1 ☀"),
					ColorUtil.formatHEX(" &8> &7Aktualny poziom: &b" + DPlayerManager.getInstance().getUpgradeLevel(player.getName(), "honorable") + "&7/&b5"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno ulepszenia)"), ""};

            return createInvItem(lore, Material.BOOK, false);
        }

        if (name.equalsIgnoreCase("postacupgrade5"))
        {
            String[] lore = {"&c&l» &6&lPRZEBIEGLOSC",
					"",
					"&c&l» &7Przebieglosc pozwoli zwiekszyc Twoj",
					"   &7wspolczynnik otrzymywanego XP!",
					"",
					ColorUtil.formatHEX("&c&l» &7Wskaznik zwiekszenia otrzymywanego XP:"),
					ColorUtil.formatHEX(" &8> &60% &f(+10%❀) " + DPlayerManager.getInstance().getUpgradeBar(player.getName(), "thiefy", "&6") + " #ffb33850%❀"),
					"",
					ColorUtil.formatHEX("&c&l» &7Cena: &e1 ☀"),
					ColorUtil.formatHEX(" &8> &7Aktualny poziom: &6" + DPlayerManager.getInstance().getUpgradeLevel(player.getName(), "thiefy") + "&7/&65"), "",
					ColorUtil.formatHEX("   #666666&o(LPM - Kupno ulepszenia)"), ""};

            return createInvItem(lore, Material.BOOK, false);
        }

        if (name.equalsIgnoreCase("questy1"))
        {
            String[] lore = {"&c&l» &a&lQUEST",
                    "",
                    "&c&l» &7Wykonuj serwerowe zadania, aby zdobyc punkty",
                    "   &7umiejetnosci i ulepszac swoja postac!",
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nazwa: #ffc936Drwal"),
                    ColorUtil.formatHEX(" &8> &7Trudnosc: #80ff1fLatwy"),
                    ColorUtil.formatHEX(" &8> &7Szczegoly questa: &7Silny badz jak drwale,"),
                    ColorUtil.formatHEX("   &7sztywno stawiaj pale. Chwyc za swoj topor i"),
                    ColorUtil.formatHEX("   &7zetnij co nieco drewna!"),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Status ukonczenia questa:"),
                    ColorUtil.formatHEX(" &8> " + DPlayerManager.getInstance().getQuestBar(player.getName(), 1)),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nagrody za ukonczenie:"),
                    ColorUtil.formatHEX(" &8> &e1 ☀"),
                    ColorUtil.formatHEX(" &8> #fff20350 ⛃"),
                    ""};

            return createInvItem(lore, Material.BOOK, false);
        }

        if (name.equalsIgnoreCase("questy2"))
        {
            String[] lore = {"&c&l» &a&lQUEST",
                    "",
                    "&c&l» &7Wykonuj serwerowe zadania, aby zdobyc punkty",
                    "   &7umiejetnosci i ulepszac swoja postac!",
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nazwa: #ffc936Zbieracz kamykow"),
                    ColorUtil.formatHEX(" &8> &7Trudnosc: #80ff1fLatwy"),
                    ColorUtil.formatHEX(" &8> &7Szczegoly questa: &7Jezeli jestes gornikiem,"),
                    ColorUtil.formatHEX("   &7i nie posiadasz RTX 3090 to lepiej chwyc za"),
                    ColorUtil.formatHEX("   &7swoj kilof i wykop mi troche kamienia!"),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Status ukonczenia questa:"),
                    ColorUtil.formatHEX(" &8> " + DPlayerManager.getInstance().getQuestBar(player.getName(), 2)),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nagrody za ukonczenie:"),
                    ColorUtil.formatHEX(" &8> &e1 ☀"),
                    ColorUtil.formatHEX(" &8> #fff203100 ⛃"),
                    ""};

            return createInvItem(lore, Material.BOOK, false);
        }

        if (name.equalsIgnoreCase("questy3"))
        {
            String[] lore = {"&c&l» &a&lQUEST",
                    "",
                    "&c&l» &7Wykonuj serwerowe zadania, aby zdobyc punkty",
                    "   &7umiejetnosci i ulepszac swoja postac!",
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nazwa: #ffc936Szpadel"),
                    ColorUtil.formatHEX(" &8> &7Trudnosc: #80ff1fLatwy"),
                    ColorUtil.formatHEX(" &8> &7Szczegoly questa: &7Widzisz ten piasek,"),
                    ColorUtil.formatHEX("   &7ziemie, zwir? Najebalo go tutaj ze japie**"),
                    ColorUtil.formatHEX("   &7Skop troche i wroc po nagrode!"),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Status ukonczenia questa:"),
                    ColorUtil.formatHEX(" &8> " + DPlayerManager.getInstance().getQuestBar(player.getName(), 3)),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nagrody za ukonczenie:"),
                    ColorUtil.formatHEX(" &8> &e1 ☀"),
                    ColorUtil.formatHEX(" &8> #fff20375 ⛃"),
                    ""};

            return createInvItem(lore, Material.BOOK, false);
        }

        if (name.equalsIgnoreCase("questy4"))
        {
            String[] lore = {"&c&l» &a&lQUEST",
                    "",
                    "&c&l» &7Wykonuj serwerowe zadania, aby zdobyc punkty",
                    "   &7umiejetnosci i ulepszac swoja postac!",
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nazwa: #ffc936Najeb sie!"),
                    ColorUtil.formatHEX(" &8> &7Trudnosc: #80ff1fLatwy"),
                    ColorUtil.formatHEX(" &8> &7Szczegoly questa: &7Z pewnoscia zaliczysz"),
                    ColorUtil.formatHEX("   &7to cudowne zadanie, chwyc kilka piwek i"),
                    ColorUtil.formatHEX("   &7rozluznij sie troszke!"),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Status ukonczenia questa:"),
					ColorUtil.formatHEX(" &8> " + DPlayerManager.getInstance().getQuestBar(player.getName(), 4)),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nagrody za ukonczenie:"),
                    ColorUtil.formatHEX(" &8> &e1 ☀"),
                    ColorUtil.formatHEX(" &8> #fff20350 ⛃"),
                    ColorUtil.formatHEX(" &8> &fPiwo 5% &f(x1)"),
                    ""};

            return createInvItem(lore, Material.BOOK, false);
        }

        if (name.equalsIgnoreCase("questy5"))
        {
            String[] lore = {"&c&l» &a&lQUEST",
                    "",
                    "&c&l» &7Wykonuj serwerowe zadania, aby zdobyc punkty",
                    "   &7umiejetnosci i ulepszac swoja postac!",
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nazwa: #ffc936Lowca"),
                    ColorUtil.formatHEX(" &8> &7Trudnosc: #80ff1fLatwy"),
                    ColorUtil.formatHEX(" &8> &7Szczegoly questa: &7Potwory sa straszne"),
                    ColorUtil.formatHEX("   &7tylko w nocy, ich tez dotyczy to zadanie."),
                    ColorUtil.formatHEX("   &7Wystarczy ze przyniesiesz mi ich glowy!"),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Status ukonczenia questa:"),
					ColorUtil.formatHEX(" &8> " + DPlayerManager.getInstance().getQuestBar(player.getName(), 5)),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nagrody za ukonczenie:"),
                    ColorUtil.formatHEX(" &8> &e1 ☀"),
                    ColorUtil.formatHEX(" &8> #fff203100 ⛃"),
                    ""};

            return createInvItem(lore, Material.BOOK, false);
        }

        if (name.equalsIgnoreCase("questy6"))
        {
            String[] lore = {"&c&l» &a&lQUEST",
                    "",
                    "&c&l» &7Wykonuj serwerowe zadania, aby zdobyc punkty",
                    "   &7umiejetnosci i ulepszac swoja postac!",
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nazwa: #ffc936Psychopata"),
                    ColorUtil.formatHEX(" &8> &7Trudnosc: #80ff1fLatwy"),
                    ColorUtil.formatHEX(" &8> &7Szczegoly questa: &7Swiat jest pelen zlych"),
                    ColorUtil.formatHEX("   &7i falszywych ludzi, mnie obchodza wszyscy,"),
                    ColorUtil.formatHEX("   &7wiec bede wdzieczny jesli zabijesz ich"),
                    ColorUtil.formatHEX("   &7minimum 10!"),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Status ukonczenia questa:"),
					ColorUtil.formatHEX(" &8> " + DPlayerManager.getInstance().getQuestBar(player.getName(), 6)),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nagrody za ukonczenie:"),
                    ColorUtil.formatHEX(" &8> &e1 ☀"),
                    ColorUtil.formatHEX(" &8> #fff203150 ⛃"),
                    ""};

            return createInvItem(lore, Material.BOOK, false);
        }

        if (name.equalsIgnoreCase("questy7"))
        {
            String[] lore = {"&c&l» &a&lQUEST",
                    "",
                    "&c&l» &7Wykonuj serwerowe zadania, aby zdobyc punkty",
                    "   &7umiejetnosci i ulepszac swoja postac!",
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nazwa: #ffc936Poszukiwacz"),
                    ColorUtil.formatHEX(" &8> &7Trudnosc: #fff203Sredni"),
                    ColorUtil.formatHEX(" &8> &7Szczegoly questa: &7Kazdemu zlodziejowi"),
                    ColorUtil.formatHEX("   &7jak i wedrowcowi potrzebne sa pojemne i"),
                    ColorUtil.formatHEX("   &7glebokie kieszenie, ulepsz wiec swoj"),
                    ColorUtil.formatHEX("   &7schowek na maksymalny poziom!"),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Status ukonczenia questa:"),
					ColorUtil.formatHEX(" &8> " + DPlayerManager.getInstance().getQuestBar(player.getName(), 7)),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nagrody za ukonczenie:"),
                    ColorUtil.formatHEX(" &8> &e2 ☀"),
                    ColorUtil.formatHEX(" &8> #fff203250 ⛃"),
                    ""};

            return createInvItem(lore, Material.BOOK, false);
        }

        if (name.equalsIgnoreCase("questy8"))
        {
            String[] lore = {"&c&l» &a&lQUEST",
                    "",
                    "&c&l» &7Wykonuj serwerowe zadania, aby zdobyc punkty",
                    "   &7umiejetnosci i ulepszac swoja postac!",
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nazwa: #ffc936Psychonauta"),
                    ColorUtil.formatHEX(" &8> &7Trudnosc: #fff203Sredni"),
                    ColorUtil.formatHEX(" &8> &7Szczegoly questa: &7Gdzies tam daleko"),
                    ColorUtil.formatHEX("   &7swiat jest inny, a moze nawet i przed nami"),
                    ColorUtil.formatHEX("   &7lecz nie umiemy otworzyc oczu.."),
                    ColorUtil.formatHEX("   &7Wyprobuj jeden z serwerowych specjalow!"),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Status ukonczenia questa:"),
					ColorUtil.formatHEX(" &8> " + DPlayerManager.getInstance().getQuestBar(player.getName(), 8)),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nagrody za ukonczenie:"),
                    ColorUtil.formatHEX(" &8> &e2 ☀"),
                    ColorUtil.formatHEX(" &8> #fff203200 ⛃"),
                    ColorUtil.formatHEX(" &8> &fHeroina &f(x1)"),
                    ""};

            return createInvItem(lore, Material.BOOK, false);
        }

        if (name.equalsIgnoreCase("questy9"))
        {
            String[] lore = {"&c&l» &a&lQUEST",
                    "",
                    "&c&l» &7Wykonuj serwerowe zadania, aby zdobyc punkty",
                    "   &7umiejetnosci i ulepszac swoja postac!",
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nazwa: #ffc936Forget"),
                    ColorUtil.formatHEX(" &8> &7Trudnosc: #fff203Sredni"),
                    ColorUtil.formatHEX(" &8> &7Szczegoly questa: &7Twoim wyzwaniem jest"),
                    ColorUtil.formatHEX("   &7zostac wyciszonym na czacie minimum 10 razy!"),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Status ukonczenia questa:"),
					ColorUtil.formatHEX(" &8> " + DPlayerManager.getInstance().getQuestBar(player.getName(), 9)),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nagrody za ukonczenie:"),
                    ColorUtil.formatHEX(" &8> &e2 ☀"),
                    ColorUtil.formatHEX(" &8> #fff203200 ⛃"),
                    ""};

            return createInvItem(lore, Material.BOOK, false);
        }

        if (name.equalsIgnoreCase("questy10"))
        {
            String[] lore = {"&c&l» &a&lQUEST",
                    "",
                    "&c&l» &7Wykonuj serwerowe zadania, aby zdobyc punkty",
                    "   &7umiejetnosci i ulepszac swoja postac!",
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nazwa: #ffc936Spoceniec"),
                    ColorUtil.formatHEX(" &8> &7Trudnosc: #fff203Sredni"),
                    ColorUtil.formatHEX(" &8> &7Szczegoly questa: &7Kto pierwszy ten.."),
                    ColorUtil.formatHEX("   &7najbardziej spocony! Przegraj na serwerze"),
                    ColorUtil.formatHEX("   &7czas wynoszacy minimum 7 dni!"),
                    ColorUtil.formatHEX("   &7Jesli ukonczyles questa, wejdz i wejdz"),
                    ColorUtil.formatHEX("   &7ponownie na serwer!"),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Status ukonczenia questa:"),
					ColorUtil.formatHEX(" &8> " + DPlayerManager.getInstance().getQuestBar(player.getName(), 10)),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nagrody za ukonczenie:"),
                    ColorUtil.formatHEX(" &8> &e2 ☀"),
                    ColorUtil.formatHEX(" &8> #fff203300 ⛃"),
                    ""};

            return createInvItem(lore, Material.BOOK, false);
        }

        if (name.equalsIgnoreCase("questy11"))
        {
            String[] lore = {"&c&l» &a&lQUEST",
                    "",
                    "&c&l» &7Wykonuj serwerowe zadania, aby zdobyc punkty",
                    "   &7umiejetnosci i ulepszac swoja postac!",
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nazwa: #ffc936Ogrodnik"),
                    ColorUtil.formatHEX(" &8> &7Trudnosc: #fff203Sredni"),
                    ColorUtil.formatHEX(" &8> &7Szczegoly questa: &7Na swoim duzym"),
                    ColorUtil.formatHEX("   &7i pieknym polu, bez chaszczy i robali"),
                    ColorUtil.formatHEX("   &7poswiec czas zbierajac plony ze swojej"),
                    ColorUtil.formatHEX("   &7plantacji!"),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Status ukonczenia questa:"),
					ColorUtil.formatHEX(" &8> " + DPlayerManager.getInstance().getQuestBar(player.getName(), 11)),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nagrody za ukonczenie:"),
                    ColorUtil.formatHEX(" &8> &e2 ☀"),
                    ColorUtil.formatHEX(" &8> #fff203250 ⛃"),
                    ""};

            return createInvItem(lore, Material.BOOK, false);
        }

        if (name.equalsIgnoreCase("questy12"))
        {
            String[] lore = {"&c&l» &a&lQUEST",
                    "",
                    "&c&l» &7Wykonuj serwerowe zadania, aby zdobyc punkty",
                    "   &7umiejetnosci i ulepszac swoja postac!",
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nazwa: #ffc936Zakochani po uszy"),
                    ColorUtil.formatHEX(" &8> &7Trudnosc: #fc7474Trudny"),
                    ColorUtil.formatHEX(" &8> &7Szczegoly questa: &7Im wiecej milosci"),
                    ColorUtil.formatHEX("   &7i szczescia w twym zwiazku, tym jeszcze"),
                    ColorUtil.formatHEX("   &7wiecej w kieszeni majatku. Osiagnij 100"),
                    ColorUtil.formatHEX("   &7poziom slubu! Jesli ukonczyles questa"),
                    ColorUtil.formatHEX("   &7na chwile przed otrzymaniem nagrody"),
                    ColorUtil.formatHEX("   &7pocaluj swoja druga polowke!"),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Status ukonczenia questa:"),
					ColorUtil.formatHEX(" &8> " + DPlayerManager.getInstance().getQuestBar(player.getName(), 12)),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nagrody za ukonczenie:"),
                    ColorUtil.formatHEX(" &8> &e3 ☀"),
                    ColorUtil.formatHEX(" &8> #fff203450 ⛃"),
                    ""};

            return createInvItem(lore, Material.BOOK, false);
        }

        if (name.equalsIgnoreCase("questy13"))
        {
            String[] lore = {"&c&l» &a&lQUEST",
                    "",
                    "&c&l» &7Wykonuj serwerowe zadania, aby zdobyc punkty",
                    "   &7umiejetnosci i ulepszac swoja postac!",
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nazwa: #ffc936Partia serwera"),
                    ColorUtil.formatHEX(" &8> &7Trudnosc: #fc7474Trudny"),
                    ColorUtil.formatHEX(" &8> &7Szczegoly questa: &7Twoim zadaniem jest"),
                    ColorUtil.formatHEX("   &7zakupic ekskluzywny kosmetyk gwiazdy"),
                    ColorUtil.formatHEX("   &7dla swojego gangu. Jezeli jestes w gangu"),
                    ColorUtil.formatHEX("   &7i gwiazde kupi jego lider, wowczas"),
                    ColorUtil.formatHEX("   &7quest nie zostaje zaliczony poniewaz"),
                    ColorUtil.formatHEX("   &7nie tolerujemy darmozjadow!"),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Status ukonczenia questa:"),
					ColorUtil.formatHEX(" &8> " + DPlayerManager.getInstance().getQuestBar(player.getName(), 13)),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nagrody za ukonczenie:"),
                    ColorUtil.formatHEX(" &8> &e3 ☀"),
                    ColorUtil.formatHEX(" &8> #fff203500 ⛃"),
                    ""};

            return createInvItem(lore, Material.BOOK, false);
        }

        if (name.equalsIgnoreCase("questy14"))
        {
            String[] lore = {"&c&l» &a&lQUEST",
                    "",
                    "&c&l» &7Wykonuj serwerowe zadania, aby zdobyc punkty",
                    "   &7umiejetnosci i ulepszac swoja postac!",
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nazwa: #ffc936Piraci z karaibow"),
                    ColorUtil.formatHEX(" &8> &7Trudnosc: #fc7474Trudny"),
                    ColorUtil.formatHEX(" &8> &7Szczegoly questa: &7W morzu i jeziorach"),
                    ColorUtil.formatHEX("   &7znajduja sie fascynujace przedmioty,"),
                    ColorUtil.formatHEX("   &7ktore mozesz wylowic za pomoca swojego"),
                    ColorUtil.formatHEX("   &7kija ze sznurkiem i haczykiem. Twoim"),
                    ColorUtil.formatHEX("   &7zadaniem jest wylowienie 15 legendarnych"),
                    ColorUtil.formatHEX("   &7morskich skarbow!"),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Status ukonczenia questa:"),
					ColorUtil.formatHEX(" &8> " + DPlayerManager.getInstance().getQuestBar(player.getName(), 14)),
                    "",
                    ColorUtil.formatHEX("&c&l» &7Nagrody za ukonczenie:"),
                    ColorUtil.formatHEX(" &8> &e3 ☀"),
                    ColorUtil.formatHEX(" &8> #fff203600 ⛃"),
                    ""};

            return createInvItem(lore, Material.BOOK, false);
        }

		if (name.equalsIgnoreCase("monopolowypiwo"))
		{
			String[] lore = {"&c&l» &a&lPIWO",
					"",
					"&c&l» &7Jezeli chcesz sie napic dla towarzystwa",
					"   &7nie wahaj sie i natychmiast kup piwo!",
					"",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff20360 ⛃"),
					""};

			return createInvItem(lore, Material.LIME_DYE, false);
		}

		if (name.equalsIgnoreCase("monopolowywino"))
		{
			String[] lore = {"&c&l» &a&lWINO",
					"",
					"&c&l» &7Potrzebujesz czegos na romantyczny wieczor",
					"   &7lub cos slodkiego i owocowego? Kup wino!",
					"",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff203100 ⛃"),
					""};

			return createInvItem(lore, Material.LIME_DYE, false);
		}

		if (name.equalsIgnoreCase("monopolowyszampan"))
		{
			String[] lore = {"&c&l» &a&lSZAMPAN",
					"",
					"&c&l» &7Gdy w kosciele nowa para,",
					"   &7biegnij do mnie po szampana!",
					"",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff203150 ⛃"),
					""};

			return createInvItem(lore, Material.LIME_DYE, false);
		}

		if (name.equalsIgnoreCase("monopolowywhisky"))
		{
			String[] lore = {"&c&l» &a&lWHISKY",
					"",
					"&c&l» &7Wytrawny luksusowy alkohol, ktory",
					"   &7oswiadczy sie Twoim kubkom smakowym!",
					"",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff203340 ⛃"),
					""};

			return createInvItem(lore, Material.LIME_DYE, false);
		}

		if (name.equalsIgnoreCase("monopolowywodka"))
		{
			String[] lore = {"&c&l» &a&lWODKA",
					"",
					"&c&l» &7Polska czy ruska, zawsze dobrze smakuje",
					"   &7kto wodki nie pije, ten nie baluje!",
					"",
					ColorUtil.formatHEX("&c&l» &7Cena: #fff203400 ⛃"),
					""};

			return createInvItem(lore, Material.LIME_DYE, false);
		}

		return null;
	}

	public static boolean isFullInventory(Player player)
	{
		for (int slot = 0; slot < 36; slot++)
			if (player.getInventory().getItem(slot) == null)
				return false;

		return true;
	}

	public static boolean canBeStacked(ItemStack item, int amount)
	{
		if (item != null)
			return item.getAmount() + amount <= item.getMaxStackSize();

		return false;
	}

	public static boolean canStackItem(Player player, Material material, int amount)
	{
		for (int slot = 0; slot < 36; slot++)
			if (Objects.requireNonNull(player.getInventory().getItem(slot)).getType() == material)
				if (canBeStacked(player.getInventory().getItem(slot), amount))
					return true;

		return false;
	}

	public static ItemStack getEnchantmentBook(Enchantment enchantment, int tier)
	{
		ItemStack enchBook = new ItemStack(Material.ENCHANTED_BOOK, 1);
		EnchantmentStorageMeta meta = (EnchantmentStorageMeta) enchBook.getItemMeta();
		assert meta != null;
		meta.addStoredEnchant(enchantment, tier, true);
		enchBook.setItemMeta(meta);

		return enchBook;
	}

	public static ItemStack createInvItem(String[] lore, Material material, boolean enchGlowing)
	{
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();

		ArrayList<String> itemLore = new ArrayList<>();

		for (String vers : lore)
			itemLore.add(ChatColor.translateAlternateColorCodes('&', vers));

		assert meta != null;

		if (enchGlowing)
			meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);

		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setDisplayName(" ");
		meta.setLore(itemLore);
		item.setItemMeta(meta);

		return item;
	}

    public static ItemStack createPlayerHeadInvItem(String[] lore, String name, boolean enchGlowing)
    {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skull_meta = (SkullMeta) skull.getItemMeta();


        ArrayList<String> itemLore = new ArrayList<>();

        for (String vers : lore)
            itemLore.add(ChatColor.translateAlternateColorCodes('&', vers));

        assert skull_meta != null;

        if (enchGlowing)
            skull_meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);

        skull_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        skull_meta.setOwningPlayer(Bukkit.getOfflinePlayer(name));
        skull.setItemMeta(skull_meta);
        skull_meta.setDisplayName(" ");
        skull_meta.setLore(itemLore);
        skull.setItemMeta(skull_meta);

        return skull;
    }

	public static ItemStack createAmountedInvItem(String[] lore, Material material, int amount, boolean enchGlowing)
	{
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();

		ArrayList<String> itemLore = new ArrayList<>();

		for (String vers : lore)
			itemLore.add(ChatColor.translateAlternateColorCodes('&', vers));

		assert meta != null;

		if (enchGlowing)
			meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);

		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setDisplayName(" ");
		meta.setLore(itemLore);
		item.setItemMeta(meta);

		return item;
	}
	
	public static void createSchowek(Player player) 
	{
		Inventory inventory = Bukkit.createInventory(
				player, 
				9*3 + (DPlayerManager.getInstance().getSchowekLevel(player) * 9), 		
				ChatColor.translateAlternateColorCodes('&', "&c&lSCHOWEK"));
		
		if (FileManager.getInstance().configuration().get(DPlayerManager.getDPlayer(player.getName()) + ".data.schowek.items") != null)
		{
			@SuppressWarnings("unchecked")
			ArrayList<ItemStack> items = (ArrayList<ItemStack>) FileManager.getInstance().configuration().get(DPlayerManager.getDPlayer(player.getName()) + ".data.schowek.items");
			ItemStack[] contents;
            contents = Objects.requireNonNull(items).toArray(new ItemStack[items.size()]);

            inventory.setContents(contents);
			player.openInventory(inventory);
			
			return;
		}
		
		ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "&7Trwa tworzenie Twojego schowku... &e⌛");
		
		ItemStack[] items = inventory.getContents();
		List<ItemStack> contents = new ArrayList<>(Arrays.asList(items));

        DPlayerManager.getInstance().setSchowek(player, contents);
		ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "&7Twoj schowek zostal utworzony, mozesz go otworzyc pod komenda #fc7474/schowek");
	}
}