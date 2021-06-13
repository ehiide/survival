package mc.server.survival.files;

import mc.server.survival.utils.ColorUtil;

public class Configuration 
{
	public static final String PLUGIN_PREFIX = ColorUtil.formatHEX("#f83044&lSERVER");
	public static final String PLUGIN_FULL_PREFIX = ColorUtil.formatHEX("#f83044&lSERVER &a&l✦ #8c8c8c");
	
	public static final int SERVER_MAX_PLAYERS = 200;
	public static final String SERVER_ICON_NAME = "server-icon.png";
	public static final String SERVER_MOTD = "&f&l✦ &7Wlacz &c&lMINECRAFT 1.17 &7i dolacz do nas! &f&l✦ \n&d&lINSTAGRAM &5@realehide &7| &b&lDISCORD &9c66DVjhJba";

	public static boolean SERVER_BLOCK_THE_END = true;
	public static boolean SERVER_BLOCK_NETHER = true;
	public static boolean SERVER_TERRAIN_PROTECTION = true;
	public static final int SERVER_SPAWN_PROTECTION = 16;
	public static int SERVER_DROP_CHANCE = 20;

	public static boolean SERVER_FUNCTION_OREMINER = true;
    public static boolean SERVER_FUNCTION_TIMBER = true;

	public static final int SERVER_TELEPORT_REQUEST_TIME = 60;
	public static final int SERVER_MARRY_REQUEST_TIME = 30;
	public static final int SERVER_GANG_REQUEST_TIME = 30;
	
	public static double CLIENT_ENTITY_KNOCKBACK = 0.67D;
}