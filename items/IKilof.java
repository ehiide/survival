package mc.server.survival.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class IKilof
{
    public static ItemStack getItem()
    {
        ArrayList<String> lore = new ArrayList<String>();

        ItemStack item = new ItemStack(Material.GOLDEN_PICKAXE);
        ItemMeta meta = item.getItemMeta();

        assert meta != null;
        meta.setUnbreakable(true);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eNiezniszczalny Kilof"));

        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7&oEkskluzywny serwerowy kilof z"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7&ozakleciem wiecznej niezniszczalnosci"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7&oKilofy i inne specjalne przedmioty"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7&omozesz zakupic tutaj &c&o/sklep"));
        lore.add("");

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }
}