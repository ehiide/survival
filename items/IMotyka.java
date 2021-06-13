package mc.server.survival.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class IMotyka
{
    public static ItemStack getItem()
    {
        ArrayList<String> lore = new ArrayList<String>();

        ItemStack item = new ItemStack(Material.GOLDEN_HOE);
        ItemMeta meta = item.getItemMeta();

        assert meta != null;
        meta.setUnbreakable(true);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eNiezniszczalna Motyka"));

        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7&oEkskluzywna serwerowa motyka z"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7&ozakleciem wiecznej niezniszczalnosci"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7&oMotyki i inne specjalne przedmioty"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7&omozesz zakupic tutaj &c&o/sklep"));
        lore.add("");

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }
}