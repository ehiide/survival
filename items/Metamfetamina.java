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

public class Metamfetamina
{
    public static ItemStack getItem()
    {
        ArrayList<String> lore = new ArrayList<String>();

        ItemStack item = new ItemStack(Material.SUGAR);
        ItemMeta meta = item.getItemMeta();

        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX(" &8> &7Nazwa: &fMetamfetamina"));
        lore.add(ColorUtil.formatHEX(" &8> &7Klasyfikacja: &fStymulanty empatogenne"));
        lore.add(ColorUtil.formatHEX(" &8> &7Rodzina substancji: &fSNDRA"));
        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX("   &e&o(Ciekawostka: Metamfetamina byla"));
        lore.add(ColorUtil.formatHEX("   &e&ostosowana podczas I i II wojny swiatowej"));
        lore.add(ColorUtil.formatHEX("   &e&oaby zwiekszyc wydolnosc zolnierzy)"));
        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX(" &8> &7Efekty po pojedynczym zazyciu:"));
        lore.add(ColorUtil.formatHEX("   &8:::::::::::::::::::::::::&f:&a::::::::::&8:::::::::::::: &f(Euforia)"));
        lore.add(ColorUtil.formatHEX("   &8:::::::::::::::::::::::::&f:&a::::::::&8:::::::::::::::: &f(Mobilizacja)"));
        lore.add(ColorUtil.formatHEX("   &8:::::::::::::::::::::::::&f:&a:::::::&8::::::::::::::::: &f(Pobudzenie)"));
        lore.add(ColorUtil.formatHEX("   &8:::::::::::::::::::::::::&f:&a:::::::&8::::::::::::::::: &f(Przyspieszenie)"));
        lore.add(ColorUtil.formatHEX("   &8:::::::::::::::::::::::::&f:&a::::::&8:::::::::::::::::: &f(Saturacja)"));
        lore.add(ColorUtil.formatHEX("   &8::::::::::::::::&c:::::::::&f:&8:::::::::::::::::::::::: &f(Nerwowosc)"));
        lore.add(ColorUtil.formatHEX("   &8:::::::::::::::&c::::::::::&f:&8:::::::::::::::::::::::: &f(Impulsywnosc)"));
        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX("   #666666&o(PPM - Konsumpcja)"));
        lore.add(ColorUtil.formatHEX(""));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fMetamfetamina"));
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static ShapedRecipe getRecipe()
    {
        NamespacedKey key = new NamespacedKey(Main.getInstance(), "metamfetamina_crafting");
        ShapedRecipe recipe = new ShapedRecipe(key, getItem());

        recipe.shape("AAA", "AXA", "TTT");
        recipe.setIngredient('X', new RecipeChoice.ExactChoice(Metyloformyloamid.getItem()));
        recipe.setIngredient('A', Material.AIR);
        recipe.setIngredient('T', Material.TORCH);

        return recipe;
    }
}