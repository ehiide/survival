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

public class Xanax
{
    public static ItemStack getItem()
    {
        ArrayList<String> lore = new ArrayList<String>();

        ItemStack item = new ItemStack(Material.SUGAR, 1);
        ItemMeta meta = item.getItemMeta();

        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX(" &8> &7Nazwa: &fXanax"));
        lore.add(ColorUtil.formatHEX(" &8> &7Klasyfikacja: &aOpioidy"));
        lore.add(ColorUtil.formatHEX(" &8> &7Rodzina substancji: &fORA"));
        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX("   &e&o(Ciekawostka: Xanax, czyli alprazolam"));
        lore.add(ColorUtil.formatHEX("   &e&oma dzialanie synergistyczne"));
        lore.add(ColorUtil.formatHEX("   &e&ow polaczeniu z alkoholami)"));
        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX(" &8> &7Efekty po pojedynczym zazyciu:"));
        lore.add(ColorUtil.formatHEX("   &8:::::::::::::::::::::::::&f:&a:::::::&8::::::::::::::::: &f(Uspokojenie)"));
        lore.add(ColorUtil.formatHEX(""));
        lore.add(ColorUtil.formatHEX("   #666666&o(PPM - Konsumpcja)"));
        lore.add(ColorUtil.formatHEX(""));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aXanax"));
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static ShapedRecipe getRecipe()
    {
        NamespacedKey key = new NamespacedKey(Main.getInstance(), "xanax_crafting");
        ShapedRecipe recipe = new ShapedRecipe(key, getItem());

        recipe.shape("CCC", "CXC", "CCC");
        recipe.setIngredient('X', new RecipeChoice.ExactChoice(Metylenoamina.getItem()));
        recipe.setIngredient('C', Material.SUGAR);

        return recipe;
    }

    public static void applyEffects(Player player)
    {
        int serotonine = DPlayerManager.getInstance().getSerotonine(player.getName());
        int dopamine = DPlayerManager.getInstance().getDopamine(player.getName());
        int noradrenaline = DPlayerManager.getInstance().getNoradrenaline(player.getName());
        int gaba = DPlayerManager.getInstance().getGABA(player.getName());

        if (Math.abs(serotonine) <= 30)
            serotonine = 0;
        else
            {
            if (serotonine > 30)
                serotonine = serotonine - 30;
            else if (serotonine < -30)
                serotonine = serotonine + 30;
        }

        if (Math.abs(dopamine) <= 30)
            dopamine = 0;
        else
            {
            if (dopamine > 30)
                dopamine = dopamine - 30;
            else if (dopamine < -30)
                dopamine = dopamine + 30;
        }

        if (Math.abs(noradrenaline) <= 30)
            noradrenaline = 0;
        else
        {
            if (noradrenaline > 30)
                noradrenaline = noradrenaline - 30;
            else if (noradrenaline < -30)
                noradrenaline = noradrenaline + 30;
        }

        if (Math.abs(gaba) <= 30)
            gaba = 0;
        else
        {
            if (gaba > 30)
                gaba = gaba - 30;
            else if (gaba < -30)
                gaba = gaba + 30;
        }

        NeuroManager.set(player, serotonine, dopamine, noradrenaline, gaba);
    }
}