package mc.server.survival.events;

import mc.server.survival.files.Configuration;
import mc.server.survival.files.Main;
import mc.server.survival.managers.DPlayerManager;
import mc.server.survival.managers.GangManager;
import mc.server.survival.utils.ColorUtil;
import mc.server.survival.utils.NPCUtil;
import mc.server.survival.utils.VelocityUtil;
import mc.server.survival.utils.WorldUtil;
import net.minecraft.network.protocol.game.PacketPlayOutAnimation;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class EntityDamageByEntity 
implements Listener
{
	@EventHandler
	public void onEvent(EntityDamageByEntityEvent event) 
	{
		if (event.getEntity() instanceof Player)
		{
			Player player = (Player) event.getEntity();

			if (player.getLocation().distance(WorldUtil.SURVIVAL_SPAWN) < 128)
			{
				event.setCancelled(true);
				return;
			}

			if (event.getDamager().getFallDistance() > 0)
			{
				PacketPlayOutAnimation blind = new PacketPlayOutAnimation(((CraftPlayer) player).getHandle(), 2);
				PacketPlayOutAnimation particle = new PacketPlayOutAnimation(((CraftPlayer) player).getHandle(), 5);

				((CraftPlayer) player).getHandle().b.sendPacket(blind);
				((CraftPlayer) player).getHandle().b.sendPacket(particle);
			}

			if (DPlayerManager.getInstance().getSerotonine(player.getName()) < -5)
				event.setDamage(event.getDamage() + Math.abs((DPlayerManager.getInstance().getSerotonine(player.getName()) / 30.0)));

			if (DPlayerManager.getInstance().getGABA(player.getName()) > 40)
				event.setDamage(event.getDamage() - Math.abs((DPlayerManager.getInstance().getGABA(player.getName()) / 30.0)));
		}
		if (event.getDamager() instanceof Player) 
		{
			Player player = (Player) event.getDamager();
			
			double distance = player.getLocation().distance(event.getEntity().getLocation());
			double Y = Math.abs(player.getLocation().getY() - event.getEntity().getLocation().getY());
			
			if (distance - Y > (getReach(player) + (event.getEntity().getWidth() / 2)) & event.getCause() == DamageCause.ENTITY_ATTACK)
			{
				event.setCancelled(true);
				return;
			}

			LivingEntity damagedEntity = (LivingEntity) event.getEntity();

			if (damagedEntity.getHealth() - event.getFinalDamage() <= 0)
				player.getWorld().spawnParticle(Particle.BLOCK_CRACK, event.getEntity().getLocation().add(0, damagedEntity.getEyeHeight(), 0), 50, 0.02, 0.02, 0.02, Material.REDSTONE_BLOCK.createBlockData());
            else
            	if (player.getFallDistance() <= 0)
                	NPCUtil.createTempHologram(ColorUtil.formatHEX("&c-" + ((int) event.getFinalDamage()) + "❤"), damagedEntity.getLocation().add(0, damagedEntity.getEyeHeight(), 0));
				else
					NPCUtil.createTempHologram(ColorUtil.formatHEX("&c-" + ((int) event.getFinalDamage()) + "❤ &6(Critical)"), damagedEntity.getLocation().add(0, damagedEntity.getEyeHeight(), 0));

			new BukkitRunnable() { @Override public void run() {
                damagedEntity.setNoDamageTicks(2);
            } }.runTaskLaterAsynchronously(Main.getInstance(), 1L);

            event.getEntity().getWorld().spawnParticle(Particle.SWEEP_ATTACK, event.getEntity().getLocation().add(0, 0.5, 0), 2, 0.5, 0.5, 0.5);
		}

		if (event.getDamager() instanceof Player && event.getEntity() instanceof Player)
		{
			Player attacker = (Player) event.getDamager();
			Player victim = (Player) event.getEntity();

			if (GangManager.getInstance().getGang(attacker) != null && GangManager.getInstance().getGang(victim) != null)
				if (GangManager.getInstance().getGang(attacker).equalsIgnoreCase(GangManager.getInstance().getGang(victim)))
					if (!GangManager.getInstance().getFriendlyFire(GangManager.getInstance().getGang(attacker)))
					{
						event.setCancelled(true);
						return;
					}
		}

		if (event.getDamager() instanceof Player && !(event.getEntity() instanceof Player))
			VelocityUtil.applyVelocity(event.getEntity(), Configuration.CLIENT_ENTITY_KNOCKBACK +
				(double) (DPlayerManager.getInstance().getDopamine(Objects.requireNonNull(((Player) event.getDamager()).getPlayer()).getName()) / 85));
	}

	private double getReach(Player player)
	{
		double reach = 2.5;

		if (DPlayerManager.getInstance().getNoradrenaline(player.getName()) > 30)
			reach = reach + 0.5;

		if (DPlayerManager.getInstance().getGABA(player.getName()) > 60)
			reach = reach - 0.7;

		if (DPlayerManager.getInstance().getDopamine(player.getName()) < -40)
			reach = reach - 0.9;

		if (reach < 0) reach = 0;

		return reach;
	}
} 