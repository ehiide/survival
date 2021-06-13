package mc.server.survival.events;

import mc.server.survival.files.Configuration;
import mc.server.survival.utils.WorldUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace
implements Listener
{
    @EventHandler
    public void onEvent(BlockPlaceEvent event)
    {
        Player player = event.getPlayer();

        if (player.getWorld().getName().equalsIgnoreCase("survival") && Configuration.SERVER_TERRAIN_PROTECTION)
        {
            Location blockLoc = event.getBlock().getLocation();

            if (blockLoc.distance(WorldUtil.SURVIVAL_SPAWN) < 128 || blockLoc.distance(WorldUtil.SURVIVAL_PIER) < 32 ||
                blockLoc.distance(WorldUtil.SURVIVAL_MOUNTAIN) < 48 || allowedDist(blockLoc, WorldUtil.WYSPA_DZUNGLA_SPAWN) ||
                allowedDist(blockLoc, WorldUtil.WYSPA_GRZYBOWA_SPAWN) || allowedDist(blockLoc, WorldUtil.WYSPA_LAS_SPAWN) ||
                allowedDist(blockLoc, WorldUtil.WYSPA_LODOWA_SPAWN)|| allowedDist(blockLoc, WorldUtil.WYSPA_SAVANNA_SPAWN) ||
                allowedDist(blockLoc, WorldUtil.WYSPA_GORY_SPAWN) || allowedDist(blockLoc, WorldUtil.WYSPA_PUSTYNNA_SPAWN) ||
                allowedDist(blockLoc, WorldUtil.WYSPA_MESSA_SPAWN))
                event.setCancelled(true);
        }
    }

    private boolean allowedDist(Location from, Location to)
    {
        return from.distance(to) <= Configuration.SERVER_SPAWN_PROTECTION;
    }
}