package mc.server.survival.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.Objects;

public class ItemDrop implements Listener
{
    @EventHandler
    public void onEvent(PlayerDropItemEvent event)
    {
        Entity entity = event.getItemDrop();

        if (!Objects.requireNonNull(event.getItemDrop().getItemStack().getItemMeta()).getDisplayName().equalsIgnoreCase(""))
            entity.setCustomName(ChatColor.translateAlternateColorCodes('&', "&a" + Objects.requireNonNull(event.getItemDrop().getItemStack().getItemMeta()).getDisplayName() + " &ex" + event.getItemDrop().getItemStack().getAmount()));
        else
            entity.setCustomName(ChatColor.translateAlternateColorCodes('&', "&a" + event.getItemDrop().getName() + " &ex" + event.getItemDrop().getItemStack().getAmount()));
        entity.setCustomNameVisible(true);
    }
}