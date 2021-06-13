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

public class Bimber
{
    public static ItemStack getItem()
    {
        ArrayList<String> lore = new ArrayList<String>();

        ItemStack item = new ItemStack(Material.POTION);
        ItemMeta meta = item.getItemMeta();
        PotionMeta potion = (PotionMeta) meta;
        potion.setColor(Color.fromBGR(100, 255, 255));
        potion.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        potion.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        potion.setBasePotionData(new PotionData(PotionType.WATER));

        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX(" &8> &7Nazwa: &fBimber"));
        lore.add(ColorUtil.formatHEX(" &8> &7Klasyfikacja: &fAlkohole"));
        lore.add(ColorUtil.formatHEX(" &8> &7Zawartosc alkoholu: &f80%"));
        lore.add(ColorUtil.formatHEX(" &8> &7Rodzina substancji: &fGRA"));
        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX("   &e&o(Ciekawostka: jest to najmocniejszy"));
        lore.add(ColorUtil.formatHEX("   &e&oalkohol na serwerze, produkowany przez"));
        lore.add(ColorUtil.formatHEX("   &e&osamego proseczka)"));
        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX(" &8> &7Efekty po pojedynczym zazyciu:"));
        lore.add(ColorUtil.formatHEX("   &8:::::::::::::::::::::::::&f:&a:::::::::::::::::&8::::::: &f(Relaksacja)"));
        lore.add(ColorUtil.formatHEX("   &8:::::::::::::::::::::::::&f:&a:::::::::&8::::::::::::::: &f(Pobudzenie)"));
        lore.add(ColorUtil.formatHEX("   &8:::::::::::::::::::::::::&f:&a::::::::&8:::::::::::::::: &f(Szczescie)"));
        lore.add(ColorUtil.formatHEX("   &8:::::::::::::::::::::::::&f:&a::::::&8:::::::::::::::::: &f(Euforia)"));
        lore.add(ColorUtil.formatHEX("   &8::::::::::::::&c:::::::::::&f:&8:::::::::::::::::::::::: &f(Glod)"));
        lore.add(ColorUtil.formatHEX("   &8:::::::::::::&c::::::::::::&f:&8:::::::::::::::::::::::: &f(Dezorientacja)"));
        lore.add(ColorUtil.formatHEX("   &8::::::::::::&c:::::::::::::&f:&8:::::::::::::::::::::::: &f(Spowolnienie)"));
        lore.add(ColorUtil.formatHEX("   &8::::::::&c:::::::::::::::::&f:&8:::::::::::::::::::::::: &f(Samokontrola)"));
        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX("   #666666&o(PPM - Konsumpcja)"));
        lore.add(ColorUtil.formatHEX(""));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fBimber"));
        meta.setLore(lore);
        item.setItemMeta(potion);

        return item;
    }

    public static ShapedRecipe getRecipe()
    {
        NamespacedKey key = new NamespacedKey(Main.getInstance(), "bimber_crafting");
        ShapedRecipe recipe = new ShapedRecipe(key, getItem());

        recipe.shape("ASA", "CBC", "CCC");
        recipe.setIngredient('B', Material.WATER_BUCKET);
        recipe.setIngredient('S', Material.SUGAR);
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(Chmiel.getItem()));
        recipe.setIngredient('A', Material.APPLE);

        return recipe;
    }
}