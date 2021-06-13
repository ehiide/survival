package mc.server.survival.managers;

import mc.server.survival.utils.ChatUtil;
import mc.server.survival.utils.MathUtil;
import mc.server.survival.utils.PacketUtil;
import mc.server.survival.utils.VisualUtil;
import org.bukkit.Bukkit;
import org.bukkit.WeatherType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class NeuroManager
{
    public static void schedule(Plugin plugin)
    {
        new BukkitRunnable()
        {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers())
                    if (isDrugged(player))
                        normalize(player);
            }
        }.runTaskTimer(plugin, 20 * 15, 20 * 15);
    }

    public static void apply(Player player)
    {
        int serotonine = DPlayerManager.getInstance().getSerotonine(player.getName());
        int dopamine = DPlayerManager.getInstance().getDopamine(player.getName());
        int noradrenaline = DPlayerManager.getInstance().getNoradrenaline(player.getName());
        int gaba = DPlayerManager.getInstance().getGABA(player.getName());

        if (serotonine > 200 || dopamine > 200 || noradrenaline > 200 || gaba > 200)
        {
            set(player, 0, 0, 0, 0);
            player.setHealth(0);
        }

        if (serotonine < -200 || dopamine < -200 || noradrenaline < -200 || gaba < -200)
        {
            set(player, 0, 0, 0, 0);
            player.setHealth(0);
        }

        if (serotonine > 100 || dopamine > 100 || noradrenaline > 100 || gaba > 100)
            player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20*60*60, 1));
        else
            player.removePotionEffect(PotionEffectType.POISON);

        if (serotonine < -100 || dopamine < -100 || noradrenaline < -100 || gaba < -100)
            player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20*60*60, 1));
        else
            player.removePotionEffect(PotionEffectType.POISON);

        float speed = 0.2f;
        int regeneration = 0;
        boolean nightvision = false;
        boolean nausea = false;
        int hunger = 0;
        boolean pickupitems = true;
        int resistance = 0;
        boolean slowfalling = false;
        boolean glowing = false;
        boolean saturation = false;
        int haste = 0;
        int fireresistance = 0;
        int poison = 0;
        int strength = 0;
        int weakness = 0;

        PacketUtil.cancelTask(player, PacketUtil.shakeEffect);
        PacketUtil.cancelTask(player, PacketUtil.overdoseEffect);
        PacketUtil.cancelTask(player, PacketUtil.heartEffect);

        player.resetPlayerTime();
        player.resetPlayerWeather();
        if (serotonine < Integer.MAX_VALUE && serotonine > 60)
        {
            PacketUtil.heartEffect(player, 4, 15, 3);
            player.setPlayerWeather(WeatherType.CLEAR);
            player.setPlayerTime(6000, true);
            nightvision = true;
            ChatUtil.scheduleDruggedMessages(player, 10);
        }
        else if (serotonine <= 60 && serotonine > 40)
        {
            PacketUtil.heartEffect(player, 4, 10, 2);
            player.setPlayerWeather(WeatherType.CLEAR);
            player.setPlayerTime(6000, true);
            nightvision = true;
            ChatUtil.scheduleDruggedMessages(player, 15);
        }
        else if (serotonine <= 40 && serotonine > 20)
        {
            PacketUtil.heartEffect(player, 4, 6, 2);
            player.setPlayerWeather(WeatherType.CLEAR);
            nightvision = true;
            ChatUtil.scheduleDruggedMessages(player, 45);
        }
        else if (serotonine <= 20 && serotonine > 10)
        {
            PacketUtil.heartEffect(player, 10, 3, 1);
            nightvision = true;
        }
        else if (serotonine <= 10 && serotonine > 5)
        {
            nightvision = true;
        }

        if (serotonine > -Integer.MAX_VALUE && serotonine <= -60)
        {
            player.setPlayerWeather(WeatherType.DOWNFALL);
            weakness = weakness + 2;
        }
        else if (serotonine > -60 && serotonine <= -40)
        {
            player.setPlayerWeather(WeatherType.DOWNFALL);
            weakness = weakness + 1;
        }
        else if (serotonine > -40 && serotonine <= -20)
        {
            player.setPlayerWeather(WeatherType.DOWNFALL);
        }
        else if (serotonine > -20 && serotonine < -5)
        {
           player.setPlayerWeather(WeatherType.DOWNFALL);
        }

        if (dopamine > -Integer.MAX_VALUE && dopamine <= -50)
        {
            if (MathUtil.chanceOf(40))
                player.dropItem(true);
            if (PacketUtil.getOverdoseEffect(player) == null)
                PacketUtil.overdoseEffect(player, 4);
            pickupitems = false;
            speed = speed - 0.16F;
            poison = poison + 2;
            weakness = weakness + 3;
        }
        else if (dopamine > -50 && dopamine <= -30)
        {
            if (MathUtil.chanceOf(30))
                player.dropItem(true);
            if (PacketUtil.getOverdoseEffect(player) == null)
                PacketUtil.overdoseEffect(player, 4);
            pickupitems = false;
            speed = speed - 0.12F;
            poison = poison + 2;
            weakness = weakness + 2;
        }
        else if (dopamine > -30 && dopamine <= -20)
        {
            if (MathUtil.chanceOf(20))
                player.dropItem(true);

            speed = speed - 0.09F;
            poison = poison + 1;
            weakness = weakness + 2;
        }
        else if (dopamine > -20 && dopamine <= -10)
        {
            speed = speed - 0.06F;
            weakness = weakness + 1;
        }
        else if (dopamine > -10 && dopamine < -6)
        {
            speed = speed - 0.04F;
        }

        if (dopamine < Integer.MAX_VALUE && dopamine > 50)
        {
            regeneration = regeneration + 2;
            saturation = true;
            strength = strength + 2;
            VisualUtil.simulateFakeExplosion(player, 15);
        }
        else if (dopamine <= 50 && dopamine > 20)
        {
            regeneration = regeneration + 2;
            strength = strength + 1;
        }
        else if (dopamine <= 20 && dopamine > 10)
        {
            regeneration = regeneration + 1;
        }
        else if (dopamine <= 10 && dopamine > 0)
        {
            regeneration = regeneration + 1;
        }

        if (noradrenaline < Integer.MAX_VALUE && noradrenaline > 70)
        {
            speed = speed + 0.22F;
            if (PacketUtil.getShakeEffect(player) == null)
                PacketUtil.shakeCamera(player, 1, 0.04);
            nightvision = true;
            glowing = true;
            saturation = true;
            haste = haste + 2;
            fireresistance = fireresistance + 2;
        }
        else if (noradrenaline <= 70 && noradrenaline > 50)
        {
            speed = speed + 0.17F;
            if (PacketUtil.getShakeEffect(player) == null)
                PacketUtil.shakeCamera(player, 2, 0.03);
            nightvision = true;
            glowing = true;
            saturation = true;
            haste = haste + 2;
            fireresistance = fireresistance + 2;
        }
        else if (noradrenaline <= 50 && noradrenaline > 30)
        {
            speed = speed + 0.13F;
            if (PacketUtil.getShakeEffect(player) == null)
                PacketUtil.shakeCamera(player, 2, 0.02);
            nightvision = true;
            glowing = true;
            saturation = true;
            haste = haste + 1;
            fireresistance = fireresistance + 1;
        }
        else if (noradrenaline <= 30 && noradrenaline > 20)
        {
            speed = speed + 0.07F;
            nightvision = true;
            haste = haste + 1;
            fireresistance = fireresistance + 1;
        }
        else if (noradrenaline <= 20 && noradrenaline > 10)
        {
            speed = speed + 0.05F;
            haste = haste + 1;
        }
        else if (noradrenaline <= 10 && noradrenaline > 5)
        {
            speed = speed + 0.03F;
        }
        else if (noradrenaline <= 5 && noradrenaline > 0)
        {
            speed = speed + 0.01F;
        }

        if (gaba < Integer.MAX_VALUE && gaba > 50)
        {
            if (MathUtil.chanceOf(30))
                player.dropItem(true);

            speed = speed - 0.12F;
            nausea = true;
            hunger = hunger + 3;
            pickupitems = false;
            resistance = resistance + 2;
            slowfalling = true;
        }
        else if (gaba <= 50 && gaba > 30)
        {
            if (MathUtil.chanceOf(15))
                player.dropItem(true);

            speed = speed - 0.09F;
            nausea = true;
            hunger = hunger + 2;
            resistance = resistance + 2;
        }
        else if (gaba <= 30 && gaba > 20)
        {
            if (MathUtil.chanceOf(5))
                player.dropItem(true);

            speed = speed - 0.06F;
            hunger = hunger + 1;
            resistance = resistance + 1;
        }
        else if (gaba <= 20 && gaba > 10)
        {
            speed = speed - 0.04F;
            nightvision = true;
            hunger = hunger + 1;
            resistance = resistance + 1;
        }
        else if (gaba <= 10 && gaba > 6)
        {
            speed = speed - 0.02F;
            regeneration = regeneration + 1;
            nightvision = true;
            hunger = hunger + 1;
        }
        else if (gaba <= 6 && gaba > 3)
        {
            speed = speed + 0.0F;
            regeneration = regeneration + 1;
        }
        else if (gaba <= 3 && gaba > 0)
        {
            speed = speed + 0.02F;
            regeneration = regeneration + 1;
        }

        if (gaba > -Integer.MAX_VALUE && gaba <= -50)
        {
            if (MathUtil.chanceOf(50))
                player.dropItem(true);
            if (PacketUtil.getShakeEffect(player) == null)
                PacketUtil.overdoseEffect(player, 4);
            pickupitems = false;
            speed = speed - 0.16F;
            nausea = true;
            resistance = resistance + 2;
        }
        else if (gaba > -50 && gaba <= -30)
        {
            if (MathUtil.chanceOf(30))
                player.dropItem(true);
            if (PacketUtil.getShakeEffect(player) == null)
                PacketUtil.overdoseEffect(player, 4);
            speed = speed - 0.11F;
            resistance = resistance + 1;
        }
        else if (gaba > -30 && gaba <= -20)
        {
            speed = speed - 0.07F;
        }
        else if (gaba > -20 && gaba < -10)
        {
            speed = speed - 0.04F;
        }

        if (!(speed < 0F))
            player.setWalkSpeed(speed);
        else
            player.setWalkSpeed(0F);

        player.removePotionEffect(PotionEffectType.REGENERATION);
        if (regeneration > 0)
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20*60*60, regeneration - 1));

        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
        if (nightvision)
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20*60*60, 0));

        if (nausea)
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20*60*60, 0));
        else
            player.removePotionEffect(PotionEffectType.CONFUSION);

        player.removePotionEffect(PotionEffectType.HUNGER);
        if (hunger > 0)
            player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 20*60*60, hunger - 1));

        player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
        if (resistance > 0)
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*60*60, resistance - 1));

        player.removePotionEffect(PotionEffectType.SLOW_FALLING);
        if (slowfalling)
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20*60*60, 0));

        player.removePotionEffect(PotionEffectType.GLOWING);
        if (glowing)
            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20*60*60, 0));

        player.removePotionEffect(PotionEffectType.SATURATION);
        if (saturation)
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 20*60*60, 0));

        player.removePotionEffect(PotionEffectType.FAST_DIGGING);
        if (haste > 0)
            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20*60*60, haste - 1));

        player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
        if (fireresistance > 0)
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20*60*60, fireresistance - 1));

        player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        if (strength > 0)
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20*60*60, strength - 1));

        player.removePotionEffect(PotionEffectType.WEAKNESS);
        if (weakness > 0)
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20*60*60, weakness - 1));

        if (poison > 0)
        {
            player.removePotionEffect(PotionEffectType.POISON);
            player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20*60*60, poison - 1));
        }

        player.setCanPickupItems(pickupitems);
    }

    public static void set(Player player, int s, int d, int n, int g)
    {
        DPlayerManager.getInstance().setSerotonine(player,  s);
        DPlayerManager.getInstance().setDopamine(player, d);
        DPlayerManager.getInstance().setNoradrenaline(player,  n);
        DPlayerManager.getInstance().setGABA(player, g);
        apply(player);
    }

    public static void modify(Player player, int s, int d, int n, int g)
    {
        DPlayerManager.getInstance().setSerotonine(player, DPlayerManager.getInstance().getSerotonine(player.getName()) + s);
        DPlayerManager.getInstance().setDopamine(player, DPlayerManager.getInstance().getDopamine(player.getName()) + d);
        DPlayerManager.getInstance().setNoradrenaline(player, DPlayerManager.getInstance().getNoradrenaline(player.getName()) + n);
        DPlayerManager.getInstance().setGABA(player, DPlayerManager.getInstance().getGABA(player.getName()) + g);
        apply(player);
    }

    public static void normalize(Player player)
    {
        int serotonine = DPlayerManager.getInstance().getSerotonine(player.getName());
        int dopamine = DPlayerManager.getInstance().getDopamine(player.getName());
        int noradrenaline = DPlayerManager.getInstance().getNoradrenaline(player.getName());
        int gaba = DPlayerManager.getInstance().getGABA(player.getName());

        if (serotonine > 0)
            serotonine = serotonine - 1;
        else if (serotonine < 0)
            serotonine = serotonine + 1;

        if (dopamine > 0)
            dopamine = dopamine - 1;
        else if (dopamine < 0)
            dopamine = dopamine + 1;

        if (noradrenaline > 0)
            noradrenaline = noradrenaline - 1;
        else if (noradrenaline < 0)
            noradrenaline = noradrenaline + 1;

        if (gaba > 0)
            gaba = gaba - 1;
        else if (gaba < 0)
            gaba = gaba + 1;

        set(player, serotonine, dopamine, noradrenaline, gaba);
    }

    public static boolean isDrugged(Player player)
    {
        if (DPlayerManager.getInstance().getSerotonine(player.getName()) != 0)
            return true;
        if (DPlayerManager.getInstance().getDopamine(player.getName()) != 0)
            return true;
        if (DPlayerManager.getInstance().getNoradrenaline(player.getName()) != 0)
            return true;
        if (DPlayerManager.getInstance().getGABA(player.getName()) != 0)
            return true;

        return false;
    }
}