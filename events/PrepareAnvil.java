package mc.server.survival.events;

import mc.server.survival.utils.ColorUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class PrepareAnvil implements Listener
{
    @EventHandler
    public void onEvent(PrepareAnvilEvent event)
    {
        if (event.getResult() != null && event.getResult().hasItemMeta() && !Objects.equals(event.getInventory().getRenameText(), ""))
        {
            ItemStack result = event.getResult();
            ItemMeta meta = result.getItemMeta();
            meta.setDisplayName(ColorUtil.formatHEX(Objects.requireNonNull(event.getInventory().getRenameText())));
            result.setItemMeta(meta);
        }
    }
}