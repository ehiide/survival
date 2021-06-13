package mc.server.survival.events;

import mc.server.survival.items.*;
import mc.server.survival.managers.NeuroManager;
import mc.server.survival.utils.QuestUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class ItemConsume implements Listener
{
    @EventHandler
    public void onEvent(PlayerItemConsumeEvent event)
    {
        Player player = event.getPlayer();

        if (event.getItem().getType() == Material.MILK_BUCKET && NeuroManager.isDrugged(player))
            event.setCancelled(true);

        if (event.getItem().equals(Piwo.getItem()))
        {
            player.setCooldown(Material.POTION, 120);
            NeuroManager.modify(player, 1, 0, 1, 3);
            feedPlayer(player, 3);
            QuestUtil.manageQuest(player, 4);
        }
        else if (event.getItem().equals(Wino.getItem()))
        {
            player.setCooldown(Material.POTION, 120);
            NeuroManager.modify(player, 2, 0, 1, 6);
            feedPlayer(player, 3);
        }
        else if (event.getItem().equals(Szampan.getItem()))
        {
            player.setCooldown(Material.POTION, 120);
            NeuroManager.modify(player, 4, 1, 2, 10);
            feedPlayer(player, 3);
        }
        else if (event.getItem().equals(Whisky.getItem()))
        {
            player.setCooldown(Material.POTION, 60);
            NeuroManager.modify(player, 7, 5, 3, 20);
            feedPlayer(player, 6);
        }
        else if (event.getItem().equals(Wodka.getItem()))
        {
            player.setCooldown(Material.POTION, 60);
            NeuroManager.modify(player, 9, 11, 3, 35);
            feedPlayer(player, 6);
        }
        else if (event.getItem().equals(Bimber.getItem()))
        {
            player.setCooldown(Material.POTION, 60);
            NeuroManager.modify(player, 12, 19, 7, 65);
            feedPlayer(player, 8);
        }
        else if (event.getItem().equals(Kawa.getItem()))
        {
            player.setCooldown(Material.POTION, 120);
            NeuroManager.modify(player, -2, -3, 5, -2);
            feedPlayer(player, 4);
        }
        else if (event.getItem().equals(Monster.getItem()))
        {
            player.setCooldown(Material.POTION, 120);
            NeuroManager.modify(player, -1, -1, 6, -1);
            feedPlayer(player, 6);
        }
        else if (event.getItem().equals(Monster2.getItem()))
        {
            player.setCooldown(Material.POTION, 120);
            NeuroManager.modify(player, 2, 1, 5, 0);
            feedPlayer(player, 6);
        }
        else if (event.getItem().equals(Monster3.getItem()))
        {
            player.setCooldown(Material.POTION, 120);
            NeuroManager.modify(player, 1, 0, 6, 2);
            feedPlayer(player, 6);
        }
        else if (event.getItem().equals(Monster4.getItem()))
        {
            player.setCooldown(Material.POTION, 120);
            NeuroManager.modify(player, -1, 2, 9, -4);
            feedPlayer(player, 6);
        }
        else if (event.getItem().equals(Monster5.getItem()))
        {
            player.setCooldown(Material.POTION, 120);
            NeuroManager.modify(player, -1, 1, 7, 3);
            feedPlayer(player, 6);
        }
    }

    private void feedPlayer(Player player, int amount)
    {
        if (player.getFoodLevel() >= 20) return;

        if (player.getFoodLevel() + amount >= 20)
            player.setFoodLevel(20);
        else
            player.setFoodLevel(player.getFoodLevel() + amount);
    }
}