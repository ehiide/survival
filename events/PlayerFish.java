package mc.server.survival.events;

import mc.server.survival.items.*;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.managers.DPlayerManager;
import mc.server.survival.utils.InventoryUtil;
import mc.server.survival.utils.MathUtil;
import mc.server.survival.utils.QuestUtil;
import mc.server.survival.utils.ServerUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.ArrayList;
import java.util.Random;

public class PlayerFish implements Listener
{
    @EventHandler
    public void onEvent(PlayerFishEvent event)
    {
        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH)
        {
            Player player = event.getPlayer();

            if (MathUtil.chanceOf(1 + (DPlayerManager.getInstance().getSerotonine(player.getName()) / 10) + DPlayerManager.getInstance().getUpgradeLevel(player.getName(), "luck")))
            {
                legendaryLootFound(player);
                return;
            }

            if (MathUtil.chanceOf(5 + (DPlayerManager.getInstance().getSerotonine(player.getName()) / 10)))
            {
                mysteryLootFound(player);
                return;
            }

            if (MathUtil.chanceOf(10 + (DPlayerManager.getInstance().getSerotonine(player.getName()) / 10)))
                lootFound(player);
        }
    }

    private void lootFound(Player player)
    {
        ArrayList<String> loot = new ArrayList<>();

        int money = 30 + new Random().nextInt(70);
        boolean piwo = MathUtil.chanceOf(50);

        loot.add("#fcff33" + money + " monet(y)&7");
        if (piwo)
            loot.add("&ePiwo &f(x1)&7");

        String loots = loot.toString().substring(1, loot.toString().length() - 1);

        DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) + money);
        ServerUtil.reloadContents(player);

        if (piwo)
            if (InventoryUtil.isFullInventory(player))
                player.getWorld().dropItemNaturally(player.getLocation().add(0, 1, 0), Piwo.getItem());
            else
                player.getInventory().addItem(Piwo.getItem());

        ChatManager.sendMessage(player, "&e★ #ffc936Wyglada na to, ze znalazles morski skarb, a oto jego zawartosc: " + loots);
    }

    private void mysteryLootFound(Player player)
    {
        ArrayList<String> loot = new ArrayList<>();

        int money = 100 + new Random().nextInt(150);
        boolean kawa = MathUtil.chanceOf(90);
        boolean piwo = MathUtil.chanceOf(70);
        boolean wino = MathUtil.chanceOf(50);
        boolean szampan = MathUtil.chanceOf(30);

        loot.add("#fcff33" + money + " monet(y)&7");
        if (kawa)
            loot.add("&fKawa &f(x1)&7");
        if (piwo)
            loot.add("&ePiwo &f(x1)&7");
        if (wino)
            loot.add("&eWino &f(x1)&7");
        if (szampan)
            loot.add("&eSzampan &f(x1)&7");

        String loots = loot.toString().substring(1, loot.toString().length() - 1);

        DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) + money);
        ServerUtil.reloadContents(player);

        if (kawa)
            if (InventoryUtil.isFullInventory(player))
                player.getWorld().dropItemNaturally(player.getLocation().add(0, 1, 0), Kawa.getItem());
            else
                player.getInventory().addItem(Kawa.getItem());

        if (piwo)
            if (InventoryUtil.isFullInventory(player))
                player.getWorld().dropItemNaturally(player.getLocation().add(0, 1, 0), Piwo.getItem());
            else
                player.getInventory().addItem(Piwo.getItem());

        if (wino)
            if (InventoryUtil.isFullInventory(player))
                player.getWorld().dropItemNaturally(player.getLocation().add(0, 1, 0), Wino.getItem());
            else
                player.getInventory().addItem(Wino.getItem());

        if (szampan)
            if (InventoryUtil.isFullInventory(player))
                player.getWorld().dropItemNaturally(player.getLocation().add(0, 1, 0), Szampan.getItem());
            else
                player.getInventory().addItem(Szampan.getItem());

        ChatManager.sendMessage(player, "&e★ #ffc936Wyglada na to, ze znalazles mityczny morski skarb, a oto jego zawartosc: " + loots);
    }

    private void legendaryLootFound(Player player)
    {
        ArrayList<String> loot = new ArrayList<>();

        int money = 200 + new Random().nextInt(150);
        boolean kawa = MathUtil.chanceOf(100);
        boolean monster = MathUtil.chanceOf(90);
        boolean piwo = MathUtil.chanceOf(90);
        boolean wino = MathUtil.chanceOf(70);
        boolean szampan = MathUtil.chanceOf(50);
        boolean whisky = MathUtil.chanceOf(30);
        boolean xanax = MathUtil.chanceOf(10);

        loot.add("#fcff33" + money + " monet(y)&7");
        if (kawa)
            loot.add("&fKawa &f(x1)&7");
        if (monster)
            loot.add("&fMonster &f(x1)&7");
        if (piwo)
            loot.add("&ePiwo &f(x1)&7");
        if (wino)
            loot.add("&eWino &f(x1)&7");
        if (szampan)
            loot.add("&eSzampan &f(x1)&7");
        if (whisky)
            loot.add("&eWhisky &f(x1)&7");
        if (xanax)
            loot.add("&aXanax &f(x1)&7");

        String loots = loot.toString().substring(1, loot.toString().length() - 1);

        DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) + money);
        ServerUtil.reloadContents(player);

        if (kawa)
            if (InventoryUtil.isFullInventory(player))
                player.getWorld().dropItemNaturally(player.getLocation().add(0, 1, 0), Kawa.getItem());
            else
                player.getInventory().addItem(Kawa.getItem());

        if (monster)
            if (InventoryUtil.isFullInventory(player))
                player.getWorld().dropItemNaturally(player.getLocation().add(0, 1, 0), Monster.getItem());
            else
                player.getInventory().addItem(Monster.getItem());

        if (piwo)
            if (InventoryUtil.isFullInventory(player))
                player.getWorld().dropItemNaturally(player.getLocation().add(0, 1, 0), Piwo.getItem());
            else
                player.getInventory().addItem(Piwo.getItem());

        if (wino)
            if (InventoryUtil.isFullInventory(player))
                player.getWorld().dropItemNaturally(player.getLocation().add(0, 1, 0), Wino.getItem());
            else
                player.getInventory().addItem(Wino.getItem());

        if (szampan)
            if (InventoryUtil.isFullInventory(player))
                player.getWorld().dropItemNaturally(player.getLocation().add(0, 1, 0), Szampan.getItem());
            else
                player.getInventory().addItem(Szampan.getItem());

        if (whisky)
            if (InventoryUtil.isFullInventory(player))
                player.getWorld().dropItemNaturally(player.getLocation().add(0, 1, 0), Whisky.getItem());
            else
                player.getInventory().addItem(Whisky.getItem());

        if (xanax)
            if (InventoryUtil.isFullInventory(player))
                player.getWorld().dropItemNaturally(player.getLocation().add(0, 1, 0), Xanax.getItem());
            else
                player.getInventory().addItem(Xanax.getItem());

        ChatManager.sendMessage(player, "&e★ #ffc936Wyglada na to, ze znalazles legendarny morski skarb, a oto jego zawartosc: " + loots);
        QuestUtil.manageQuest(player, 14);
    }
}