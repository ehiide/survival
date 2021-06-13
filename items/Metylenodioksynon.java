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

public class Metylenodioksynon
{
    public static ItemStack getItem()
    {
        ArrayList<String> lore = new ArrayList<String>();

        ItemStack item = new ItemStack(Material.SUGAR);
        ItemMeta meta = item.getItemMeta();

        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX(" &8> &7Nazwa: &fMetylenodioksynon"));
        lore.add(ColorUtil.formatHEX(" &8> &7Klasyfikacja: &fN/A"));
        lore.add(ColorUtil.formatHEX(" &8> &7Rodzina substancji: &fN/A"));
        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX("   #666666&o(PPM - Konsumpcja)"));
        lore.add(ColorUtil.formatHEX(""));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fMetylenodioksynon"));
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static ShapedRecipe getRecipe()
    {
        NamespacedKey key = new NamespacedKey(Main.getInstance(), "metylenodioksynon_crafting");
        ShapedRecipe recipe = new ShapedRecipe(key, getItem());

        recipe.shape("AMA", "MXM", "AMA");
        recipe.setIngredient('X', new RecipeChoice.ExactChoice(Metylenoamina.getItem()));
        recipe.setIngredient('M', Material.GREEN_DYE);
        recipe.setIngredient('A', Material.AIR);

        return recipe;
    }
}