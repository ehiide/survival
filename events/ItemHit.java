package mc.server.survival.events;

import mc.server.survival.utils.VelocityUtil;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class ItemHit implements Listener
{
    @EventHandler
    public void onEvent(ProjectileHitEvent event)
    {
        if (event.getHitBlock() != null)
            event.getEntity().getWorld().spawnParticle(Particle.SNOW_SHOVEL, event.getEntity().getLocation(), 20, 0.75, 0, 0.75);

        if (event.getHitEntity() != null)
            event.getEntity().getWorld().spawnParticle(Particle.SWEEP_ATTACK, event.getEntity().getLocation(), 1, 0, 0, 0);

        Projectile projectile = event.getEntity();
        Entity hitEntity = event.getHitEntity();

        if (projectile instanceof Snowball || projectile instanceof Egg)
            if (hitEntity instanceof Player)
            {
                Player player = (Player) hitEntity;
                player.damage(0.5, projectile);
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*3, 0));
                player.setVelocity(new Vector(projectile.getVelocity().getX() / 3, 0.25, projectile.getVelocity().getZ() / 3));
            }

        if (!(projectile instanceof FishHook))
            projectile.remove();
    }
}