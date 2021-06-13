package mc.server.survival.utils;

import mc.server.survival.items.Heroina;
import mc.server.survival.items.Piwo;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.managers.DPlayerManager;
import org.bukkit.entity.Player;

public class QuestUtil
{
    public static void manageQuest(Player player, int quest)
    {
        if (!DPlayerManager.getInstance().getQuestStatus(player.getName(), quest))
        {
            DPlayerManager.getInstance().addQuestomplete(player.getName(), quest);

            if (DPlayerManager.getInstance().getQuestCompleting(player.getName(), quest) == 100)
            {
                addQuestTreasure(player, quest);
                DPlayerManager.getInstance().setQuestStatus(player.getName(), quest, true);
                DPlayerManager.getInstance().setSP(player, DPlayerManager.getInstance().getSP(player) + getSP(quest));
                ChatManager.sendMessage(player, "&e★ #80ff1fPomyslnie ukonczyles questa: #ffc936" + getName(quest) + "#80ff1f");
            }
        }
    }

    public static void addQuestTreasure(Player player, int quest)
    {
        if (quest == 1)
            DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) + 50);
        else if (quest == 2)
            DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) + 100);
        else if (quest == 3)
            DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) + 75);
        else if (quest == 4)
        {
            DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) + 50);
            if (InventoryUtil.isFullInventory(player))
                player.getWorld().dropItemNaturally(player.getLocation().add(0, 1, 0), Piwo.getItem());
            else
                player.getInventory().addItem(Piwo.getItem());
        }
        else if (quest == 5)
            DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) + 100);
        else if (quest == 6)
            DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) + 150);
        else if (quest == 7)
            DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) + 250);
        else if (quest == 8)
        {
            DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) + 200);
            if (InventoryUtil.isFullInventory(player))
                player.getWorld().dropItemNaturally(player.getLocation().add(0, 1, 0), Heroina.getItem());
            else
                player.getInventory().addItem(Heroina.getItem());
        }
        else if (quest == 9)
            DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) + 200);
        else if (quest == 10)
            DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) + 300);
        else if (quest == 11)
            DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) + 250);
        else if (quest == 12)
            DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) + 450);
        else if (quest == 13)
            DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) + 500);
        else if (quest == 14)
            DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) + 600);

        ServerUtil.reloadContents(player);
    }

    public static int getSP(int quest)
    {
        if (quest > 6 && quest <= 11)
            return 2;
        else if (quest > 11)
            return 3;

        return 1;
    }

    public static String getName(int quest)
    {
        if (quest == 1)
            return "Drwal";

        if (quest == 2)
            return "Zbieracz kamykow";

        if (quest == 3)
            return "Szpadel";

        if (quest == 4)
            return "Najeb sie!";

        if (quest == 5)
            return "Lowca";

        if (quest == 6)
            return "Psychopata";

        if (quest == 7)
            return "Poszukiwacz";

        if (quest == 8)
            return "Psychonauta";

        if (quest == 9)
            return "Forget";

        if (quest == 10)
            return "Spoceniec";

        if (quest == 11)
            return "Ogrodnik";

        if (quest == 12)
            return "Zakochani po uszy";

        if (quest == 13)
            return "Partia serwera";

        return "Piraci z karaibow";
    }

    public static int getCompletedQuests(Player player)
    {
        int quests = 0;

        for (int quest = 1; quest <= 14; quest++)
            if (DPlayerManager.getInstance().getQuestStatus(player.getName(), quest))
                quests++;

        return quests;
    }
}