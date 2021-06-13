package mc.server.survival.files;

import mc.server.Broadcaster;
import mc.server.survival.commands.*;
import mc.server.survival.events.*;
import mc.server.survival.managers.DPlayerManager;
import mc.server.survival.managers.FileManager;
import mc.server.survival.managers.GangManager;
import mc.server.survival.managers.NeuroManager;
import mc.server.survival.utils.NPCUtil;
import mc.server.survival.utils.RecipeUtil;
import mc.server.survival.utils.ServerUtil;
import mc.server.survival.utils.WorldUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class Main
extends JavaPlugin
{
	private static Main instance;
	
	public static Main getInstance()
	{
		return instance;
	}
	
	private void registerEvent(Listener event) 
	{
		PluginManager plugin = getServer().getPluginManager();
		
		plugin.registerEvents(event, this);
	}
	
	private void registerEvents() 
	{
		registerEvent(new ServerPing());
		registerEvent(new EntityDamageByEntity());
		registerEvent(new AsyncChat());
		registerEvent(new PlayerJoin());
		registerEvent(new Inventory());
		registerEvent(new PlayerDeath());
		registerEvent(new BlockBreak());
		registerEvent(new CommandPreProcess());
		registerEvent(new Portal());
		registerEvent(new PlayerInteract());
		registerEvent(new Respawn());
		registerEvent(new BlockPlace());
		registerEvent(new ItemDrop());
		registerEvent(new EntityInteract());
		registerEvent(new PrepareAnvil());
		registerEvent(new ItemConsume());
		registerEvent(new PlayerItemDamage());
		registerEvent(new EntityDeath());
		registerEvent(new PlayerFish());
		registerEvent(new PlayerTeleport());
		registerEvent(new ItemMerge());
		registerEvent(new ItemHit());
	}
	
	private void registerCommand(String name, CommandExecutor command)
	{
		Objects.requireNonNull(getCommand(name)).setExecutor(command);
	}
	
	private void registerCommands() 
	{
		registerCommand("powrot", new Powrot());
		registerCommand("spawn", new Spawn());
		registerCommand("dom", new Dom());
		registerCommand("wiadomosc", new Wiadomosc());
		registerCommand("vanish", new Vanish());
		registerCommand("swiat", new Swiat());
		registerCommand("schowek", new Schowek());
		registerCommand("pomoc", new Pomoc());
		registerCommand("ping", new Ping());
		registerCommand("wyspy", new Wyspy());
		registerCommand("craftingi", new Craftingi());
		registerCommand("zaplac", new Zaplac());
		registerCommand("paleta", new Paleta());
		registerCommand("tpa", new TPA());
		registerCommand("sklep", new Sklep());
		registerCommand("slub", new Slub());
		registerCommand("gang", new Gang());
		registerCommand("ip", new IP());
		registerCommand("debugger", new Debugger());
		registerCommand("mute", new Mute());
		registerCommand("unmute", new Unmute());
		registerCommand("kick", new Kick());
		registerCommand("powiedz", new Powiedz());
		registerCommand("postac", new Postac());
		registerCommand("questy", new Questy());
	}
	
	@Override
	public void onEnable() 
	{
		instance = this;
		
		FileManager.getInstance().start(this);
		FileManager.getInstance().save();
		
		for (Player dplayer : Bukkit.getOnlinePlayers())
		{
			DPlayerManager.getInstance().loadDplayer(dplayer);
			ServerUtil.reloadContents(dplayer);
		}

		GangManager.getInstance().loadGang();
		
		registerEvents();
		registerCommands();
		
		RecipeUtil.getInstance().addRecipes();
		
		WorldUtil.loadWorld("surowce");
		WorldUtil.loadWorld("survival");

		NPCUtil.getInstance().reloadEntities();

		NeuroManager.schedule(this);

		new BukkitRunnable() { @Override public void run() {
			Broadcaster.scheduleGlobalMessages();
		} }.runTaskTimer(this, 20*180, 20*180);
	}
	
	@Override
	public void onDisable() 
	{

	}
}