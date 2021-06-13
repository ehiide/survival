package mc.server.survival.items;

import mc.server.survival.files.Main;
import mc.server.survival.utils.ColorUtil;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;

public class Monster
{
    public static ItemStack getItem()
    {
        ArrayList<String> lore = new ArrayList<String>();

        ItemStack item = new ItemStack(Material.POTION);
        ItemMeta meta = item.getItemMeta();
        PotionMeta potion = (PotionMeta) meta;
        potion.setColor(Color.fromRGB(25, 165, 111));
        potion.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        potion.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        potion.setBasePotionData(new PotionData(PotionType.WATER));

        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX(" &8> &7Nazwa: &fMonster Energy"));
        lore.add(ColorUtil.formatHEX(" &8> &7Klasyfikacja: &fStymulanty"));
        lore.add(ColorUtil.formatHEX(" &8> &7Zawartosc kofeiny: &f150mg"));
        lore.add(ColorUtil.formatHEX(" &8> &7Rodzina substancji: &fN/A"));
        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX("   &e&o(Ciekawostka: Obecnie na swiecie"));
        lore.add(ColorUtil.formatHEX("   &e&odostepnych jest ponad 150 roznych"));
        lore.add(ColorUtil.formatHEX("   &e&otypow napoju monster)"));
        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX(" &8> &7Efekty po pojedynczym zazyciu:"));
        lore.add(ColorUtil.formatHEX("   &8:::::::::::::::::::::::::&f:&a:::::&8::::::::::::::::::: &f(Pobudzenie)"));
        lore.add(ColorUtil.formatHEX("   &8:::::::::::::::::::::::::&f:&a::&8:::::::::::::::::::::: &f(Relaksacja)"));
        lore.add(ColorUtil.formatHEX("   &8:::::::::::::::::::::::::&f:&a:&8::::::::::::::::::::::: &f(Motywacja)"));
        lore.add(ColorUtil.formatHEX("   &8:::::::::::::::::::::::&c::&f:&8:::::::::::::::::::::::: &f(Wykonczenie)"));
        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX("   #666666&o(PPM - Konsumpcja)"));
        lore.add(ColorUtil.formatHEX(""));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fMonster"));
        meta.setLore(lore);
        item.setItemMeta(potion);

        return item;
    }

    public static ShapedRecipe getRecipe()
    {
        NamespacedKey key = new NamespacedKey(Main.getInstance(), "monster_crafting");
        ShapedRecipe recipe = new ShapedRecipe(key, getItem());

        recipe.shape("KKK", "SBS", "KKK");
        recipe.setIngredient('B', Material.GLASS_BOTTLE);
        recipe.setIngredient('S', Material.SUGAR);
        recipe.setIngredient('K', new RecipeChoice.ExactChoice(Kofeina.getItem()));

        return recipe;
    }
}