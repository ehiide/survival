package mc.server.survival.items;

import mc.server.survival.files.Main;
import mc.server.survival.utils.ColorUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Fenyloaceton
{
    public static ItemStack getItem()
    {
        ArrayList<String> lore = new ArrayList<String>();

        ItemStack item = new ItemStack(Material.SUGAR);
        ItemMeta meta = item.getItemMeta();

        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX(" &8> &7Nazwa: &fFenyloaceton"));
        lore.add(ColorUtil.formatHEX(" &8> &7Klasyfikacja: &fN/A"));
        lore.add(ColorUtil.formatHEX(" &8> &7Rodzaj substancji: &fN/A"));
        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX("   &e&o(Ciekawostka: fenyloaceton jest"));
        lore.add(ColorUtil.formatHEX("   &e&opol-produktem w procesie syntezy"));
        lore.add(ColorUtil.formatHEX("   &e&oamfetaminy i metamfetaminy)"));
        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX("   #666666&o(PPM - Konsumpcja)"));
        lore.add(ColorUtil.formatHEX(""));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bFenyloaceton"));
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static ShapedRecipe getRecipe()
    {
        NamespacedKey key = new NamespacedKey(Main.getInstance(), "fenyloaceton_crafting");
        ShapedRecipe recipe = new ShapedRecipe(key, getItem());

        recipe.shape("CCC", "CXC", "CCC");
        recipe.setIngredient('X', new RecipeChoice.ExactChoice(Metyloamina.getItem()));
        recipe.setIngredient('C', Material.CHARCOAL);

        return recipe;
    }
}