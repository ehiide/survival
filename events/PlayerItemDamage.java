package mc.server.survival.events;

import mc.server.survival.managers.DPlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class PlayerItemDamage implements Listener
{
    @EventHandler
    public void onEvent(PlayerItemDamageEvent event)
    {
        int noradrenaline = DPlayerManager.getInstance().getNoradrenaline(event.getPlayer().getName());

        if (noradrenaline > 30)
            event.setDamage(event.getDamage() * ((noradrenaline / 14)));
    }
}