package mc.server.survival.utils;

import mc.server.Debugger;
import mc.server.Logger;
import mc.server.survival.files.Main;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

import java.util.Objects;

public class NPCUtil
{
    private NPCUtil() {}

    static NPCUtil instance = new NPCUtil();

    public static NPCUtil getInstance()
    {
        return instance;
    }

    public void reloadEntities()
    {
        removeEntities();
        createEntities();
    }

    public static void createNPC(EntityType entity, String name, Location location)
    {
        LivingEntity npc = (LivingEntity) Objects.requireNonNull(Bukkit.getWorld("survival")).spawnEntity(location, entity);

       npc.setCustomName(name);
       npc.setCustomNameVisible(false);

       npc.setCollidable(false);
       npc.setAI(false);
       npc.setInvulnerable(true);
       npc.setGravity(false);
       npc.setRemoveWhenFarAway(false);

       if (entity.equals(EntityType.WOLF))
       {
           Wolf w = (Wolf) npc;
           w.setTamed(true);
           w.setSitting(true);
           w.setCollarColor(DyeColor.LIGHT_BLUE);
       }
       else if (entity.equals(EntityType.VILLAGER))
       {
           Villager v = (Villager) npc;
           v.setProfession(Villager.Profession.WEAPONSMITH);
           v.getEquipment().setItemInMainHand(new ItemStack(Material.EMERALD));
       }
       else if (entity.equals(EntityType.WITHER_SKELETON))
       {
           WitherSkeleton w = (WitherSkeleton) npc;
           Objects.requireNonNull(w.getEquipment()).setItemInOffHand(new ItemStack(Material.SHIELD));
           ItemStack helmet = new ItemStack(Material.GOLDEN_HELMET);
           ItemMeta meta = helmet.getItemMeta();
           assert meta != null;
           meta.setUnbreakable(true);
           helmet.setItemMeta(meta);
           w.getEquipment().setHelmet(helmet);
       }
    }

    public static void createArmorStand(String name, Location location, Material hand, Color chestplate_color, Color leggings_color, Color boots_color,
                                        EulerAngle angle1, EulerAngle angle2, EulerAngle angle3, EulerAngle angle4, EulerAngle angle5)
    {
        ArmorStand armorStand = (ArmorStand) Objects.requireNonNull(Bukkit.getWorld("survival")).spawnEntity(location, EntityType.ARMOR_STAND);

        armorStand.setCustomName("ENTITY_" + name);
        armorStand.setCustomNameVisible(false);

        armorStand.setMarker(true);
        armorStand.setInvulnerable(true);
        armorStand.setCollidable(false);
        armorStand.setGravity(false);
        armorStand.setRemoveWhenFarAway(false);

        armorStand.setBasePlate(false);
        armorStand.setArms(true);

        armorStand.setItemInHand(new ItemStack(hand));

        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skull_meta = (SkullMeta) skull.getItemMeta();
        skull_meta.setOwningPlayer(Bukkit.getOfflinePlayer(name));
        skull.setItemMeta(skull_meta);
        armorStand.setHelmet(skull);

        if (name.equalsIgnoreCase("szachownica"))
            armorStand.setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));

        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta chestplate_meta = (LeatherArmorMeta) chestplate.getItemMeta();
        chestplate_meta.setColor(chestplate_color);
        chestplate.setItemMeta(chestplate_meta);
        armorStand.setChestplate(chestplate);

        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta leggings_meta = (LeatherArmorMeta) leggings.getItemMeta();
        leggings_meta.setColor(leggings_color);
        leggings.setItemMeta(leggings_meta);
        armorStand.setLeggings(leggings);

        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta boots_meta = (LeatherArmorMeta) boots.getItemMeta();
        boots_meta.setColor(boots_color);
        boots.setItemMeta(boots_meta);
        armorStand.setBoots(boots);

        armorStand.setLeftLegPose(angle1);
        armorStand.setRightLegPose(angle2);
        armorStand.setRightArmPose(angle3);
        armorStand.setLeftArmPose(angle4);
        armorStand.setHeadPose(angle5);
    }

    public static void createHologram(String text, Location location)
    {
        ArmorStand armorStand = (ArmorStand) Objects.requireNonNull(location.getWorld()).spawnEntity(location, EntityType.ARMOR_STAND);

        armorStand.setCustomName(text);
        armorStand.setCustomNameVisible(true);

        armorStand.setMarker(true);
        armorStand.setInvulnerable(true);
        armorStand.setCollidable(false);
        armorStand.setGravity(false);
        armorStand.setRemoveWhenFarAway(false);

        armorStand.setVisible(false);
    }

    public static void createTempHologram(String text, Location location)
    {
        ArmorStand armorStand = (ArmorStand) Objects.requireNonNull(location.getWorld()).spawnEntity(location.add(0, 200 ,0), EntityType.ARMOR_STAND);

        armorStand.setSmall(true);
        armorStand.setCustomName(text);
        armorStand.setCustomNameVisible(true);
        armorStand.setMarker(true);
        armorStand.setInvulnerable(true);
        armorStand.setCollidable(false);
        armorStand.setInvisible(true);

        new BukkitRunnable() { @Override public void run() {
            armorStand.teleport(location.add(0, -200, 0));
        } }.runTaskLater(Main.getInstance(), 2L);

        new BukkitRunnable() { @Override public void run() {
            removeEntity(armorStand);
        } }.runTaskLater(Main.getInstance(), 20L);
    }

    public static void createItem(String id, Location location, Material item, EulerAngle rotation)
    {
        ArmorStand armorStand = (ArmorStand) Objects.requireNonNull(Bukkit.getWorld("survival")).spawnEntity(location, EntityType.ARMOR_STAND);

        armorStand.setCustomName(id);
        armorStand.setCustomNameVisible(false);

        armorStand.setMarker(true);
        armorStand.setInvulnerable(true);
        armorStand.setCollidable(false);
        armorStand.setGravity(false);
        armorStand.setRemoveWhenFarAway(false);

        armorStand.setVisible(false);

        armorStand.setItemInHand(new ItemStack(item));

        armorStand.setRightArmPose(rotation);
    }

    public static void createEntities()
    {
        createNPC(EntityType.WOLF, ChatColor.translateAlternateColorCodes('&', "&aTadzik"), new Location(Bukkit.getWorld("survival"), 92.5, 70, -351.5, -142, 10));
        createNPC(EntityType.VILLAGER, "   ", new Location(Bukkit.getWorld("survival"), 41.5, 69, -417.5, -90, 0));
        createNPC(EntityType.VILLAGER, "   ", new Location(Bukkit.getWorld("survival"), 45.5, 69, -422.5, 0, 0));
        createNPC(EntityType.VILLAGER, "   ", new Location(Bukkit.getWorld("survival"), 48.5, 69, -422.5, 0, 0));
        createNPC(EntityType.VILLAGER, "   ", new Location(Bukkit.getWorld("survival"), 57.5, 69, -422.5, 0, 0));
        createNPC(EntityType.VILLAGER, "   ", new Location(Bukkit.getWorld("survival"), 93.5, 80, -425.5, -25, 0));
        createNPC(EntityType.WITHER_SKELETON, "   ", new Location(Bukkit.getWorld("survival"), 90.5, 69, -411.5, 90, 0));
        createNPC(EntityType.VILLAGER, "   ", new Location(Bukkit.getWorld("survival"), 64.5, 69, -429.5, 0, 0));

        createArmorStand("Eh1de", new Location(Bukkit.getWorld("survival"), 57.4, 75.4, -391.1, -80, 20), Material.SUGAR, Color.YELLOW, Color.RED, Color.FUCHSIA,
                new EulerAngle(20, -60, 0), new EulerAngle(20, 60, 0), new EulerAngle(100, 0, 0), new EulerAngle(125, 0, 0), new EulerAngle(195, 0, 0));

        createArmorStand("ProseczeqPL", new Location(Bukkit.getWorld("survival"), 56.2, 76.8, -389.3, -110, 10), Material.SUGAR, Color.AQUA, Color.BLUE, Color.GRAY,
                new EulerAngle(20, -60, 0), new EulerAngle(20, 60, 0), new EulerAngle(130, 20, 0), new EulerAngle(150, 0, -20), new EulerAngle(195, 0, 0));

        createArmorStand("yogo2222", new Location(Bukkit.getWorld("survival"), 103, 78.2, -396.7, 65, 30), Material.IRON_AXE, Color.BLACK, Color.GRAY, Color.WHITE,
                new EulerAngle(120, 55, -140), new EulerAngle(120, -55, 140), new EulerAngle(130, 20, 0), new EulerAngle(150, 0, -20), new EulerAngle(195, 0, 0));

        createArmorStand("Nxtyy", new Location(Bukkit.getWorld("survival"), 111.4, 74, -380.1, 120, 30), Material.ROSE_BUSH, Color.GRAY, Color.WHITE, Color.BLACK,
                new EulerAngle(0, 0, 0), new EulerAngle(25, 0, 0), new EulerAngle(100, 0, 0), new EulerAngle(125, 0, 0), new EulerAngle(195, 0, 0));

        createArmorStand("h3re", new Location(Bukkit.getWorld("survival"), 121.0, 49, -354.5, 90, -10), Material.AIR, Color.WHITE, Color.SILVER, Color.GRAY,
                new EulerAngle(0, 0, 0), new EulerAngle(25, 0, 0), new EulerAngle(150, 0, 20), new EulerAngle(150, 0, -20), new EulerAngle(-195, 0, 0));

        createArmorStand("milusia515", new Location(Bukkit.getWorld("survival"), 93.8, 69, -353.3, 35, 15), Material.BONE, Color.SILVER, Color.TEAL, Color.BLACK,
                new EulerAngle(25, 0, 0), new EulerAngle(0, 0, 0), new EulerAngle(100, 0, 0), new EulerAngle(125, 0, 0), new EulerAngle(195, 0, 0));

        createArmorStand("oAnxiety", new Location(Bukkit.getWorld("survival"), 73.5, 70.2, -358.5, -10, 65), Material.AIR, Color.NAVY, Color.BLACK, Color.SILVER,
                new EulerAngle(25, 0, 0), new EulerAngle(0, 0, 0), new EulerAngle(0, 0, 0), new EulerAngle(0, 0, 0), new EulerAngle(20, 0, 0));

        createArmorStand("schizis", new Location(Bukkit.getWorld("survival"), 49.0, 70, -376.8, 180, -10), Material.TOTEM_OF_UNDYING, Color.GREEN, Color.LIME, Color.BLACK,
                new EulerAngle(0, 0, 0), new EulerAngle(0, 0, 0), new EulerAngle(150, 0, 20), new EulerAngle(150, 0, -20), new EulerAngle(-195, 0, 0));

        createArmorStand("xpaati_", new Location(Bukkit.getWorld("survival"), 45.05, 69, -381.3, -30, -10), Material.AIR, Color.BLACK, Color.GRAY, Color.BLACK,
                new EulerAngle(20, -60, 0), new EulerAngle(20, 60, 0), new EulerAngle(100, 0, 0), new EulerAngle(125, 0, 0), new EulerAngle(-195, 0, 0));

        createArmorStand("suic1deboy", new Location(Bukkit.getWorld("survival"), 44, 69, -381.3, -40, -10), Material.POPPY, Color.BLACK, Color.BLACK, Color.BLACK,
                new EulerAngle(20, -60, 0), new EulerAngle(20, 60, 0), new EulerAngle(130, 20, 0), new EulerAngle(150, 0, -20), new EulerAngle(-195, 0, 0));

        createArmorStand("_QiXy", new Location(Bukkit.getWorld("survival"), 52.8, 69, -383.3, 25, -10), Material.AIR, Color.RED, Color.SILVER, Color.BLACK,
                new EulerAngle(20, -60, 0), new EulerAngle(20, 60, 0), new EulerAngle(100, 0, 0), new EulerAngle(125, 0, 0), new EulerAngle(-195, 0, 0));

        createArmorStand("Medson", new Location(Bukkit.getWorld("survival"),  121.2, 70.5, -337.0, 0, 0), Material.AIR, Color.BLACK, Color.BLACK, Color.BLACK,
                new EulerAngle(-30, 0, 0), new EulerAngle(-30, 0, 0), new EulerAngle(150, 0, -20), new EulerAngle(150, 0, 20), new EulerAngle(70, 0, 0));

        createArmorStand("Medson", new Location(Bukkit.getWorld("survival"),  124.0, 70.5, -335.3, 90, 0), Material.AIR, Color.BLACK, Color.BLACK, Color.BLACK,
                new EulerAngle(-30, 0, 0), new EulerAngle(-30, 0, 0), new EulerAngle(150, 0, -20), new EulerAngle(150, 0, 20), new EulerAngle(70, 0, 0));

        createArmorStand("Medson", new Location(Bukkit.getWorld("survival"),  124.0, 70.5, -333.4, 90, 0), Material.AIR, Color.BLACK, Color.BLACK, Color.BLACK,
                new EulerAngle(-30, 0, 0), new EulerAngle(-30, 0, 0), new EulerAngle(150, 0, -20), new EulerAngle(150, 0, 20), new EulerAngle(70, 0, 0));

        createArmorStand("StasiekChmura", new Location(Bukkit.getWorld("survival"), 107.3, 69, -384.5, 100, 0), Material.HONEY_BOTTLE, Color.ORANGE, Color.PURPLE, Color.BLACK,
                new EulerAngle(20, -60, 0), new EulerAngle(20, 60, 0), new EulerAngle(130, 20, 0), new EulerAngle(150, 0, -20), new EulerAngle(-195, 0, 0));

        createArmorStand("kalamous", new Location(Bukkit.getWorld("survival"), 49, 76, -390.4, 0, 30), Material.MUSIC_DISC_11, Color.AQUA, Color.SILVER, Color.BLACK,
                new EulerAngle(0, 0, 0), new EulerAngle(0, 0, 0), new EulerAngle(130, 20, 0), new EulerAngle(150, 0, -20), new EulerAngle(-195, 0, 0));

        createArmorStand("szachownica", new Location(Bukkit.getWorld("survival"), 113.5, 68.1, -413.5, 0, 0), Material.AIR, Color.YELLOW, Color.RED, Color.FUCHSIA,
                new EulerAngle(0, 0, 0), new EulerAngle(0, 0, 0), new EulerAngle(0, 0, 0), new EulerAngle(0, 0, 0), new EulerAngle(0, 0, 0));

        createArmorStand("veragor", new Location(Bukkit.getWorld("survival"), 113.5, 69, -411.75, -180, 20), Material.BOOK, Color.LIME, Color.YELLOW, Color.BLACK,
                new EulerAngle(20, -60, 0), new EulerAngle(20, 60, 0), new EulerAngle(100, 0, 0), new EulerAngle(125, 0, 0), new EulerAngle(195, 0, 0));

        createArmorStand("Kacapat", new Location(Bukkit.getWorld("survival"), 85.6, 69, -425.1, 77, 5), Material.GOLDEN_SWORD, Color.SILVER, Color.WHITE, Color.BLACK,
                new EulerAngle(-25, 0, 0), new EulerAngle(25, 0, 0), new EulerAngle(100, 0, 0), new EulerAngle(-115, 0, 0), new EulerAngle(195, 0, 0));

        createArmorStand("__Marcell", new Location(Bukkit.getWorld("survival"), 82.5, 69, -424.2, -105, 5), Material.FISHING_ROD, Color.SILVER, Color.GRAY, Color.WHITE,
                new EulerAngle(-32, 0, 0), new EulerAngle(39, 0, 0), new EulerAngle(-95, 0, 0), new EulerAngle(265, 0, 0), new EulerAngle(195, 0, 0));

        createArmorStand("fokusiara", new Location(Bukkit.getWorld("survival"), 65.5, 69.9, -353.1, 0, 25), Material.APPLE, Color.RED, Color.BLACK, Color.GRAY,
                new EulerAngle(20, -60, 0), new EulerAngle(20, 60, 0), new EulerAngle(130, 20, 0), new EulerAngle(130, -20, 0), new EulerAngle(195, 0, 0));

        createArmorStand("_vque", new Location(Bukkit.getWorld("survival"), 29.5, 70.5, -393.7, 40, -5), Material.AIR, Color.NAVY, Color.SILVER, Color.BLACK,
                new EulerAngle(-30, 0, 0), new EulerAngle(-30, 0, 0),  new EulerAngle(29, 358, 0), new EulerAngle(29, -358, 0), new EulerAngle(345, 0, 0));

        createArmorStand("vAmaterasu", new Location(Bukkit.getWorld("survival"), 29.05, 72, -392.9, -140, 40), Material.DIAMOND_SWORD, Color.fromBGR(20, 20, 255), Color.GRAY, Color.BLACK,
                new EulerAngle(-20, 0, 0), new EulerAngle(0, 5, 0), new EulerAngle(-40, -5, 0), new EulerAngle(310, 40, 0), new EulerAngle(340, 0, 0));

        createArmorStand("Wyga636", new Location(Bukkit.getWorld("survival"), 231.6, 63.1875, -279.5, 90, 10), Material.FISHING_ROD, Color.LIME, Color.GREEN, Color.GRAY,
                new EulerAngle(-25, 0, 0), new EulerAngle(25, 0, 0), new EulerAngle(100, 0, 0), new EulerAngle(-115, 0, 0), new EulerAngle(195, 0, 0));

        createArmorStand("LivePowerPlAW", new Location(Bukkit.getWorld("survival"), 239, 63.1875, -272.5, 0, 10), Material.TROPICAL_FISH, Color.AQUA, Color.BLUE, Color.GRAY,
                new EulerAngle(0, 0, 0), new EulerAngle(25, 0, 0), new EulerAngle(100, 0, 0), new EulerAngle(125, 0, 0), new EulerAngle(195, 0, 0));

        createHologram(ChatColor.translateAlternateColorCodes('&', "&7NPC &aEh1de"), new Location(Bukkit.getWorld("survival"), 57.4, 77.5, -391.1));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7NPC &aProseczeqPL"), new Location(Bukkit.getWorld("survival"), 56.2, 78.8, -389.3));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7NPC &ayogo2222"), new Location(Bukkit.getWorld("survival"), 103, 80.3, -396.7));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7NPC &aVendie_"), new Location(Bukkit.getWorld("survival"), 111.4, 76.2, -380.1));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7NPC &ah3re"), new Location(Bukkit.getWorld("survival"), 121.0, 51.2, -354.5));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7NPC &amilusia515"), new Location(Bukkit.getWorld("survival"), 93.8, 71.2, -353.3));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7NPC &axwiciaqx"), new Location(Bukkit.getWorld("survival"), 73.5, 72.2, -358.5));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7NPC &aschizis"), new Location(Bukkit.getWorld("survival"), 49.0, 72.2, -376.8));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7NPC &axpaati_"), new Location(Bukkit.getWorld("survival"), 45.05, 71.1, -381.3));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7NPC &asuic1deboy"), new Location(Bukkit.getWorld("survival"), 44, 71.1, -381.3));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7NPC &a_QiXy"), new Location(Bukkit.getWorld("survival"), 52.8, 71.1, -383.3));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7NPC &aStasiekChmura"), new Location(Bukkit.getWorld("survival"), 107.3, 71, -384.5));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7NPC &akalamous"), new Location(Bukkit.getWorld("survival"), 49, 78.1, -390.4));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&eAve prosek! Ave ehide!"), new Location(Bukkit.getWorld("survival"), 121.5, 72.5, -334.5));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7NPC &averagor"), new Location(Bukkit.getWorld("survival"), 113.5, 71, -411.75));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7NPC &aKacapat"), new Location(Bukkit.getWorld("survival"), 85.6, 71.2, -425.1));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7NPC &a__Marcell"), new Location(Bukkit.getWorld("survival"), 82.5, 71.2, -424.2));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7NPC &afokusiara"), new Location(Bukkit.getWorld("survival"), 65.5, 72, -353.1));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7NPC &aJoeKerrDC"), new Location(Bukkit.getWorld("survival"), 29.5, 72.4, -393.7));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7NPC &avAmaterasu"), new Location(Bukkit.getWorld("survival"), 29.05, 74, -392.9));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7NPC &aWyga636"), new Location(Bukkit.getWorld("survival"), 231.6, 65.1875, -279.5));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7NPC &aLivePowerPlAW"), new Location(Bukkit.getWorld("survival"), 239, 65.1875, -272.5));

        createHologram(ChatColor.translateAlternateColorCodes('&', "&e&lPRZEDMIOTY, DODATKI & EFEKTY"), new Location(Bukkit.getWorld("survival"), 41.5, 71.25, -417.5));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7Kliknij &cPPM &7aby otworzyc sklep"), new Location(Bukkit.getWorld("survival"), 41.5, 71, -417.5));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&e&lTELEPORTER SWIATOWY"), new Location(Bukkit.getWorld("survival"), 45.5, 71.25, -422.5));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7Kliknij &cPPM &7aby otworzyc menu"), new Location(Bukkit.getWorld("survival"), 45.5, 71, -422.5));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&e&lTELEPORTER MIEDZYSWIATOWY"), new Location(Bukkit.getWorld("survival"), 48.5, 71.25, -422.5));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7Kliknij &cPPM &7aby sie przeteleportowac"), new Location(Bukkit.getWorld("survival"), 48.5, 71, -422.5));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&e&lTWOJA POSTAC & ULEPSZENIA"), new Location(Bukkit.getWorld("survival"), 57.5, 71.25, -422.5));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7Kliknij &cPPM &7aby poznac, zmienic samego siebie"), new Location(Bukkit.getWorld("survival"), 57.5, 71, -422.5));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&e&lQUESTY I ZLECENIA"), new Location(Bukkit.getWorld("survival"), 93.5, 82.25, -425.5));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7Kliknij &cPPM &7aby wypelnic serwerowe zadania"), new Location(Bukkit.getWorld("survival"), 93.5, 82, -425.5));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&e&lLOWCA GLOW"), new Location(Bukkit.getWorld("survival"), 90.5, 71.75, -411.5));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7Kliknij &cPPM &7aby sprzedac glowe ofiary"), new Location(Bukkit.getWorld("survival"), 90.5, 71.5, -411.5));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&ePuk-puk!"), new Location(Bukkit.getWorld("survival"), 50.5, 72, -364));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&eOdkopcie mnie!"), new Location(Bukkit.getWorld("survival"), 74.5, 72, -328.5));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&eDosiadz sie przyjacielu!"), new Location(Bukkit.getWorld("survival"), 103.3, 70, -384.5));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&ePartyjka?"), new Location(Bukkit.getWorld("survival"), 113.5, 70, -415.5));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&e&lMONOPOLOWY U STASIA"), new Location(Bukkit.getWorld("survival"), 64.5, 71.5, -429.5));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&7Kliknij &cPPM &7aby otworzyc spizarnie"), new Location(Bukkit.getWorld("survival"), 64.5, 71.25, -429.5));
        createHologram(ChatColor.translateAlternateColorCodes('&', "&c&o(SKLEP CZYNNY OD 20:00 DO 6:00)"), new Location(Bukkit.getWorld("survival"), 64.5, 71, -429.5));

        createItem("I01", new Location(Bukkit.getWorld("survival"), 74.2, 68.35, -358.1), Material.DIAMOND_SWORD, new EulerAngle(180, -90, -180));
        createItem("I02", new Location(Bukkit.getWorld("survival"), 41.85, 69.65, -417.97), Material.GOLDEN_SWORD, new EulerAngle(-30, 0, 0));
        createItem("I03", new Location(Bukkit.getWorld("survival"), 66.6, 69.05, -353.2), Material.GOLDEN_AXE, new EulerAngle(-180, 0, -80));
        createItem("I04", new Location(Bukkit.getWorld("survival"), 56.7, 69.3, -421.85), Material.BOOK, new EulerAngle(-180, 0, -80));
        createItem("I05", new Location(Bukkit.getWorld("survival"), 238.4, 61.8, -272.5), Material.FISHING_ROD, new EulerAngle(-180, 0, -80));
    }

    public static void removeEntities()
    {
        String[] entitiesName = {"Eh1de", "ProseczeqPL", "yogo2222", "Nxtyy", "h3re", "milusia515", "Tadzik", "schizis", "xpaati_", "suic1deboy", "_QiXy", "Medson", "StasiekChmura", "kalamous", "szachownica",
                                 "veragor", "Kacapat", "__Marcell", "oAnxiety", "fokusiara", "_vque", "vAmaterasu", "Wyga636", "LivePowerPlAW",
                                 ChatColor.translateAlternateColorCodes('&', "&7NPC &aEh1de"),
                                 ChatColor.translateAlternateColorCodes('&', "&7NPC &aProseczeqPL"),
                                 ChatColor.translateAlternateColorCodes('&', "&7NPC &ayogo2222"),
                                 ChatColor.translateAlternateColorCodes('&', "&7NPC &aVendie_"),
                                 ChatColor.translateAlternateColorCodes('&', "&7NPC &ah3re"),
                                 ChatColor.translateAlternateColorCodes('&', "&7NPC &amilusia515"),
                                 ChatColor.translateAlternateColorCodes('&', "&7NPC &aJoeKerrDC"),
                                 ChatColor.translateAlternateColorCodes('&', "&7NPC &aschizis"),
                                 ChatColor.translateAlternateColorCodes('&', "&7NPC &axpaati_"),
                                 ChatColor.translateAlternateColorCodes('&', "&7NPC &asuic1deboy"),
                                 ChatColor.translateAlternateColorCodes('&', "&7NPC &a_QiXy"),
                                 ChatColor.translateAlternateColorCodes('&', "&7NPC &aStasiekChmura"),
                                 ChatColor.translateAlternateColorCodes('&', "&7NPC &akalamous"),
                                 ChatColor.translateAlternateColorCodes('&', "&7NPC &averagor"),
                                 ChatColor.translateAlternateColorCodes('&', "&7NPC &aKacapat"),
                                 ChatColor.translateAlternateColorCodes('&', "&7NPC &a__Marcell"),
                                 ChatColor.translateAlternateColorCodes('&', "&7NPC &axwiciaqx"),
                                 ChatColor.translateAlternateColorCodes('&', "&7NPC &afokusiara"),
                                 ChatColor.translateAlternateColorCodes('&', "&7NPC &aJoeKerrDC"),
                                 ChatColor.translateAlternateColorCodes('&', "&7NPC &avAmaterasu"),
                                 ChatColor.translateAlternateColorCodes('&', "&7NPC &aWyga636"),
                                 ChatColor.translateAlternateColorCodes('&', "&7NPC &aLivePowerPlAW"),
                                 ChatColor.translateAlternateColorCodes('&', "&eAve prosek! Ave ehide!"),
                                 ChatColor.translateAlternateColorCodes('&', "&e&lPRZEDMIOTY, DODATKI & EFEKTY"),
                                 ChatColor.translateAlternateColorCodes('&', "&7Kliknij &cPPM &7aby otworzyc sklep"),
                                 ChatColor.translateAlternateColorCodes('&', "&e&lTELEPORTER SWIATOWY"),
                                 ChatColor.translateAlternateColorCodes('&', "&7Kliknij &cPPM &7aby otworzyc menu"),
                                 ChatColor.translateAlternateColorCodes('&', "&e&lTELEPORTER MIEDZYSWIATOWY"),
                                 ChatColor.translateAlternateColorCodes('&', "&7Kliknij &cPPM &7aby sie przeteleportowac"),
                                 ChatColor.translateAlternateColorCodes('&', "&e&lTWOJA POSTAC & ULEPSZENIA"),
                                 ChatColor.translateAlternateColorCodes('&', "&7Kliknij &cPPM &7aby poznac, zmienic samego siebie"),
                                 ChatColor.translateAlternateColorCodes('&', "&e&lQUESTY I ZLECENIA"),
                                 ChatColor.translateAlternateColorCodes('&', "&7Kliknij &cPPM &7aby wypelnic serwerowe zadania"),
                                 ChatColor.translateAlternateColorCodes('&', "&e&lLOWCA GLOW"),
                                 ChatColor.translateAlternateColorCodes('&', "&7Kliknij &cPPM &7aby sprzedac glowe ofiary"),
                                 ChatColor.translateAlternateColorCodes('&', "&aTadzik"),
                                 ChatColor.translateAlternateColorCodes('&', "&ePuk-puk!"),
                                 ChatColor.translateAlternateColorCodes('&', "&eOdkopcie mnie!"),
                                 ChatColor.translateAlternateColorCodes('&', "&eDosiadz sie przyjacielu!"),
                                 ChatColor.translateAlternateColorCodes('&', "&ePartyjka?"),
                                 ChatColor.translateAlternateColorCodes('&', "&e&lMONOPOLOWY U STASIA"),
                                 ChatColor.translateAlternateColorCodes('&', "&7Kliknij &cPPM &7aby otworzyc spizarnie"),
                                 ChatColor.translateAlternateColorCodes('&', "&c&o(SKLEP CZYNNY OD 20:00 DO 6:00)"),
                                 "I01", "I02", "I03", "I04", "I05", "   ", "&fFinal hit!"};

        for (Entity entity : Objects.requireNonNull(Bukkit.getWorld("survival")).getEntities())
            for (String name : entitiesName)
            {
                if (entity.getName().equalsIgnoreCase("ENTITY_" + name))
                    removeEntity(entity);

                if (entity.getName().equalsIgnoreCase(name) && Bukkit.getPlayer(entity.getName()) == null)
                    removeEntity(entity);

                if (entity.getName().contains("-"))
                    removeEntity(entity);
            }
    }

    public static void removeEntity(Entity entity)
    {
        entity.remove();
    }

}