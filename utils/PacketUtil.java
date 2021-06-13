package mc.server.survival.utils;

import mc.server.survival.files.Main;
import net.minecraft.network.protocol.game.PacketPlayOutAnimation;
import net.minecraft.network.protocol.game.PacketPlayOutPosition;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class PacketUtil
{
    private static final Set<PacketPlayOutPosition.EnumPlayerTeleportFlags> teleportFlags = new HashSet<>(Arrays.asList(PacketPlayOutPosition.EnumPlayerTeleportFlags.a,
            PacketPlayOutPosition.EnumPlayerTeleportFlags.b, PacketPlayOutPosition.EnumPlayerTeleportFlags.c));

    public static HashMap<Player, BukkitTask> shakeEffect = new HashMap<Player, BukkitTask>();
    public static HashMap<Player, BukkitTask> overdoseEffect = new HashMap<Player, BukkitTask>();
    public static HashMap<Player, BukkitTask> heartEffect = new HashMap<Player, BukkitTask>();
    private static final HashMap<Player, Float> lastYaw = new HashMap<Player, Float>();
    private static final HashMap<Player, Float> lastPitch = new HashMap<Player, Float>();

    public static BukkitTask getShakeEffect(Player player)
    {
        return shakeEffect.get(player);
    }

    public static void setShakeEffect(Player player, BukkitTask task)
    {
        shakeEffect.put(player, task);
    }

    public static BukkitTask getOverdoseEffect(Player player)
    {
        return overdoseEffect.get(player);
    }

    public static void setOverdoseEffect(Player player, BukkitTask task)
    {
        overdoseEffect.put(player, task);
    }

    public static BukkitTask getHeartEffect(Player player)
    {
        return heartEffect.get(player);
    }

    public static void setHeartEffect(Player player, BukkitTask task)
    {
        heartEffect.put(player, task);
    }

    public static void shakeCamera(Player player, int delay, double strength)
    {
        setShakeEffect(player, new BukkitRunnable()
        {
            @Override
            public void run()
            {
                float yaw = player.getLocation().getYaw();
                float pitch = player.getLocation().getPitch();
                Random random = new Random();

                int a = random.nextInt(20) - 10;
                int b = random.nextInt(20) - 10;
                int c = random.nextInt(20) - 10;
                int d = random.nextInt(20) - 10;

                float randomShakeMove = (float) ((strength * a) - (strength * b));
                float randomShakeMove2 = (float) ((strength * c) - (strength * d));

                float finalYaw = yaw + randomShakeMove;
                float finalPitch = pitch + randomShakeMove2;

                if (lastYaw.get(player) == null) lastYaw.put(player, (float) 0);
                if (lastPitch.get(player) == null) lastPitch.put(player, (float) 0);

                if (MathUtil.chanceOf((int) (strength * 7)))
                    player.swingMainHand();

                if (Math.abs(yaw - lastYaw.get(player)) <= (strength * 10) && Math.abs(pitch - lastPitch.get(player)) <= (strength * 10))
                {
                    PacketPlayOutPosition packet = new PacketPlayOutPosition(0.0, 0.0, 0.0, finalYaw, finalPitch, teleportFlags, 0, player.isOnGround());
                    ((CraftPlayer) player).getHandle().b.sendPacket(packet);
                }

                lastYaw.put(player, yaw);
                lastPitch.put(player, pitch);
            }
        }.runTaskTimerAsynchronously(Main.getInstance(), 0, delay));
    }

    public static void overdoseEffect(Player player, int delay)
    {
        setOverdoseEffect(player, new BukkitRunnable()
        {
            @Override
            public void run()
            {
                PacketPlayOutAnimation blind = new PacketPlayOutAnimation(((CraftPlayer) player).getHandle(), 2);
                ((CraftPlayer) player).getHandle().b.sendPacket(blind);
            }
        }.runTaskTimerAsynchronously(Main.getInstance(), 0, delay));
    }

    public static void heartEffect(Player player, int delay, int heartAmount, double strength)
    {
        setHeartEffect(player, new BukkitRunnable()
        {
            @Override
            public void run()
            {
                player.spawnParticle(Particle.HEART, player.getLocation().add(0, 1, 0), heartAmount, strength, strength, strength);
            }
        }.runTaskTimerAsynchronously(Main.getInstance(), 0, delay));
    }

    public static void cancelTask(Player player, HashMap<Player, BukkitTask> task)
    {
        if (task.isEmpty() || task.get(player) == null) return;

        task.get(player).cancel();
        task.put(player, null);
    }
}