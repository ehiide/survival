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

public class Metylenoamina
{
    public static ItemStack getItem()
    {
        ArrayList<String> lore = new ArrayList<String>();

        ItemStack item = new ItemStack(Material.SUGAR, 1);
        ItemMeta meta = item.getItemMeta();

        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX(" &8> &7Nazwa: &fMetylenoamina"));
        lore.add(ColorUtil.formatHEX(" &8> &7Klasyfikacja: &fN/A"));
        lore.add(ColorUtil.formatHEX(" &8> &7Rodzina substancji: &fN/A"));
        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX("   &e&o(Ciekawostka: Metylenoamina moze"));
        lore.add(ColorUtil.formatHEX("   &e&obyc uzyta jako srodek lagodzacy"));
        lore.add(ColorUtil.formatHEX("   &e&odzialanie alkoholi)"));
        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX("   #666666&o(PPM - Konsumpcja)"));
        lore.add(ColorUtil.formatHEX(""));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fMetylenoamina"));
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static ShapedRecipe getRecipe()
    {
        NamespacedKey key = new NamespacedKey(Main.getInstance(), "metylenoamina_crafting");
        ShapedRecipe recipe = new ShapedRecipe(key, getItem());

        recipe.shape("ARA", "RXR", "ARA");
        recipe.setIngredient('X', new RecipeChoice.ExactChoice(Amina.getItem()));
        recipe.setIngredient('A', Material.AIR);
        recipe.setIngredient('R', Material.GLOWSTONE_DUST);

        return recipe;
    }
}