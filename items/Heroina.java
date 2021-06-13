package mc.server.survival.items;

import mc.server.survival.files.Main;
import mc.server.survival.managers.DPlayerManager;
import mc.server.survival.managers.NeuroManager;
import mc.server.survival.utils.ColorUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Heroina
{
    public static ItemStack getItem()
    {
        ArrayList<String> lore = new ArrayList<String>();

        ItemStack item = new ItemStack(Material.SUGAR, 1);
        ItemMeta meta = item.getItemMeta();

        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX(" &8> &7Nazwa: &fHeroina"));
        lore.add(ColorUtil.formatHEX(" &8> &7Klasyfikacja: &aOpioidy"));
        lore.add(ColorUtil.formatHEX(" &8> &7Rodzina substancji: &fORA"));
        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX("   &e&o(Ciekawostka: Heroina jest otrzymywana"));
        lore.add(ColorUtil.formatHEX("   &e&oz opium, przetwarzanego z maku)"));
        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX(" &8> &7Efekty po pojedynczym zazyciu:"));
        lore.add(ColorUtil.formatHEX("   &8:::::::::::::::::::::::::&f:&a:::::::::::::::::::&8::::: &f(Uspokojenie)"));
        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX("   #666666&o(PPM - Konsumpcja)"));
        lore.add(ColorUtil.formatHEX(""));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fHeroina"));
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static ShapedRecipe getRecipe()
    {
        NamespacedKey key = new NamespacedKey(Main.getInstance(), "heroina_crafting");
        ShapedRecipe recipe = new ShapedRecipe(key, getItem());

        recipe.shape("OOO", "OXO", "OOO");
        recipe.setIngredient('O', new RecipeChoice.ExactChoice(Opium.getItem()));
        recipe.setIngredient('X', new RecipeChoice.ExactChoice(Metyloamina.getItem()));

        return recipe;
    }

    public static void applyEffects(Player player)
    {
        int serotonine = DPlayerManager.getInstance().getSerotonine(player.getName());
        int dopamine = DPlayerManager.getInstance().getDopamine(player.getName());
        int noradrenaline = DPlayerManager.getInstance().getNoradrenaline(player.getName());
        int gaba = DPlayerManager.getInstance().getGABA(player.getName());

        if (Math.abs(serotonine) <= 50)
            serotonine = 0;
        else
        {
            if (serotonine > 50)
                serotonine = serotonine - 50;
            else if (serotonine < -50)
                serotonine = serotonine + 50;
        }

        if (Math.abs(dopamine) <= 50)
            dopamine = 0;
        else
        {
            if (dopamine > 50)
                dopamine = dopamine - 50;
            else if (dopamine < -50)
                dopamine = dopamine + 50;
        }

        if (Math.abs(noradrenaline) <= 50)
            noradrenaline = 0;
        else
        {
            if (noradrenaline > 50)
                noradrenaline = noradrenaline - 50;
            else if (noradrenaline < -50)
                noradrenaline = noradrenaline + 50;
        }

        if (Math.abs(gaba) <= 50)
            gaba = 0;
        else
        {
            if (gaba > 50)
                gaba = gaba - 50;
            else if (gaba < -50)
                gaba = gaba + 50;
        }

        NeuroManager.set(player, serotonine, dopamine, noradrenaline, gaba);
    }
}