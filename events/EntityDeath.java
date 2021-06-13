package mc.server.survival.events;

import mc.server.survival.files.Configuration;
import mc.server.survival.managers.DPlayerManager;
import mc.server.survival.utils.MathUtil;
import mc.server.survival.utils.QuestUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class EntityDeath implements Listener
{
    @EventHandler
    public void onEvent(EntityDeathEvent event)
    {
        if (!(event.getEntity() instanceof Player) && event.getEntity().getKiller() != null)
        {
            int serotonine = DPlayerManager.getInstance().getSerotonine(event.getEntity().getKiller().getName());
            int xp = event.getDroppedExp();

            if (serotonine >= 60)
                xp = xp * 2;

            if (serotonine <= -20)
                xp = 0;

            if (serotonine <= -40)
                event.getDrops().clear();

            if (MathUtil.chanceOf(Configuration.SERVER_DROP_CHANCE))
                event.getDrops().clear();

            for (ItemStack dropItem : event.getDrops())
            {
                Item item = event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), dropItem);
                item.setCustomName(ChatColor.translateAlternateColorCodes('&', "&a" + item.getName() + " &ex" + item.getItemStack().getAmount()));
                item.setCustomNameVisible(true);
            }

            event.getDrops().clear();

            event.setDroppedExp(xp + (DPlayerManager.getInstance().getUpgradeLevel(event.getEntity().getKiller().getName(), "thiefy")));
            QuestUtil.manageQuest(event.getEntity().getKiller(), 5);
        }
    }
}