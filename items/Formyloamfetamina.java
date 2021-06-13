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

public class Formyloamfetamina
{
    public static ItemStack getItem()
    {
        ArrayList<String> lore = new ArrayList<String>();

        ItemStack item = new ItemStack(Material.SUGAR);
        ItemMeta meta = item.getItemMeta();

        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX(" &8> &7Nazwa: &fN-Formyloamfetamina"));
        lore.add(ColorUtil.formatHEX(" &8> &7Klasyfikacja: &fStymulanty"));
        lore.add(ColorUtil.formatHEX(" &8> &7Rodzina substancji: &fNDRA"));
        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX("   &e&o(Ciekawostka: N-Formyloamfetamina jest"));
        lore.add(ColorUtil.formatHEX("   &e&oostatnim produktem w procesie syntezy"));
        lore.add(ColorUtil.formatHEX("   &e&oamfetamin. Sama rowniez wywiera dzialanie"));
        lore.add(ColorUtil.formatHEX("   &e&ostymulujace, jednak duzo slabsze i"));
        lore.add(ColorUtil.formatHEX("   &e&onieselektywne. Jest czesto podawana jako"));
        lore.add(ColorUtil.formatHEX("   &e&otanszy zamiennik L-Amfetaminy)"));
        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX(" &8> &7Efekty po pojedynczym zazyciu:"));
        lore.add(ColorUtil.formatHEX("   &8:::::::::::::::::::::::::&f:&a:::::::&8::::::::::::::::: &f(Przyspieszenie)"));
        lore.add(ColorUtil.formatHEX("   &8:::::::::::::::::::::::::&f:&a::::&8:::::::::::::::::::: &f(Saturacja)"));
        lore.add(ColorUtil.formatHEX("   &8:::::::::::::::::::::::::&f:&a::&8:::::::::::::::::::::: &f(Pobudzenie)"));
        lore.add(ColorUtil.formatHEX("   &8::::::::::::::::::::&c:::::&f:&8:::::::::::::::::::::::: &f(Impulsywnosc)"));
        lore.add(ColorUtil.formatHEX("   &8:::::::::::::::&c::::::::::&f:&8:::::::::::::::::::::::: &f(Rozdraznienie)"));
        lore.add(ColorUtil.formatHEX("   &8::::::::::::&c:::::::::::::&f:&8:::::::::::::::::::::::: &f(Wykonczenie)"));
        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX("   #666666&o(PPM - Konsumpcja)"));
        lore.add(ColorUtil.formatHEX(""));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fN-Formyloamfetamina"));
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static ShapedRecipe getRecipe()
    {
        NamespacedKey key = new NamespacedKey(Main.getInstance(), "formyloamfetamina_crafting");
        ShapedRecipe recipe = new ShapedRecipe(key, getItem());

        recipe.shape("AGA", "GXG", "AGA");
        recipe.setIngredient('X', new RecipeChoice.ExactChoice(Fenyloaceton.getItem()));
        recipe.setIngredient('A', Material.AIR);
        recipe.setIngredient('G', Material.GUNPOWDER);

        return recipe;
    }
}