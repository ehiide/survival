package mc.server.survival.items;

import mc.server.survival.utils.ColorUtil;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;

public class Monster4
{
    public static ItemStack getItem()
    {
        ArrayList<String> lore = new ArrayList<String>();

        ItemStack item = new ItemStack(Material.POTION);
        ItemMeta meta = item.getItemMeta();
        PotionMeta potion = (PotionMeta) meta;
        potion.setColor(Color.fromRGB(195, 176, 145));
        potion.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        potion.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        potion.setBasePotionData(new PotionData(PotionType.WATER));

        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX(" &8> &7Nazwa: &fMonster Java"));
        lore.add(ColorUtil.formatHEX(" &8> &7Klasyfikacja: &fStymulanty"));
        lore.add(ColorUtil.formatHEX(" &8> &7Zawartosc kofeiny: &f200mg"));
        lore.add(ColorUtil.formatHEX(" &8> &7Rodzina substancji: &fN/A"));
        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX("   &e&o(Ciekawostka: Monster Java to"));
        lore.add(ColorUtil.formatHEX("   &e&onajmocniejsza mieszanka, ktora"));
        lore.add(ColorUtil.formatHEX("   &e&oz pewnoscia cie pobudzi)"));
        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX(" &8> &7Efekty po pojedynczym zazyciu:"));
        lore.add(ColorUtil.formatHEX("   &8:::::::::::::::::::::::::&f:&a:::::&8::::::::::::::::::: &f(Pobudzenie)"));
        lore.add(ColorUtil.formatHEX("   &8:::::::::::::::::::::::::&f:&a:::&8::::::::::::::::::::: &f(Motywacja)"));
        lore.add(ColorUtil.formatHEX("   &8:::::::::::::::::::::::&c::&f:&8:::::::::::::::::::::::: &f(Rozdraznienie)"));
        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX("   #666666&o(PPM - Konsumpcja)"));
        lore.add(ColorUtil.formatHEX(""));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fMonster"));
        meta.setLore(lore);
        item.setItemMeta(potion);

        return item;
    }
}