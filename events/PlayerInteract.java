package mc.server.survival.events;

import mc.server.survival.files.Configuration;
import mc.server.survival.items.*;
import mc.server.survival.managers.DPlayerManager;
import mc.server.survival.managers.NeuroManager;
import mc.server.survival.utils.QuestUtil;
import mc.server.survival.utils.VisualUtil;
import mc.server.survival.utils.WorldUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class PlayerInteract 
implements Listener
{
	@EventHandler
	public void onEvent(PlayerInteractEvent event)
	{
		if (event.getAction() == Action.PHYSICAL && Objects.requireNonNull(event.getClickedBlock()).getType() == Material.FARMLAND)
			event.setCancelled(true);

		if (event.getPlayer().getWorld().getName().equalsIgnoreCase("survival") && Configuration.SERVER_TERRAIN_PROTECTION)
			if (event.getAction() == Action.RIGHT_CLICK_BLOCK && !Objects.requireNonNull(event.getClickedBlock()).getLocation().equals(new Location(Bukkit.getWorld("survival"), 55.0, 69.0, -383.0)) &&
				!Objects.requireNonNull(event.getClickedBlock()).getLocation().equals(new Location(Bukkit.getWorld("survival"), 55.0, 70.0, -383.0)) &&
				!Objects.requireNonNull(event.getClickedBlock()).getLocation().equals(new Location(Bukkit.getWorld("survival"), 55.0, 68.0, -384.0)) &&
				event.getPlayer().getLocation().distance(WorldUtil.SURVIVAL_SPAWN) < 128 || event.getPlayer().getLocation().distance(WorldUtil.SURVIVAL_PIER) < 32 || event.getPlayer().getLocation().distance(WorldUtil.SURVIVAL_MOUNTAIN) < 48)
				{
					event.setCancelled(true);
					return;
				}

		if (event.getPlayer().getWorld().getName().equalsIgnoreCase("survival"))
			if (event.getPlayer().getItemInHand().isSimilar(new ItemStack(Material.ENDER_PEARL)) || event.getPlayer().getItemInHand().isSimilar(new ItemStack(Material.CHORUS_FRUIT)))
				if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
					if (event.getPlayer().getLocation().distance(WorldUtil.SURVIVAL_SPAWN) < 128)
					{
						event.setCancelled(true);
						return;
					}

		if (event.getAction() == Action.RIGHT_CLICK_AIR)
		{
			if (event.getPlayer().getInventory().getItemInOffHand().getType() != Material.AIR) return;

			if (event.getPlayer().getInventory().getItemInMainHand().isSimilar(Amina.getItem()) && !event.getPlayer().hasCooldown(Material.SUGAR))
			{
				Inventory.removeItem(event.getPlayer(), Amina.getItem(), 1);
				NeuroManager.modify(event.getPlayer(), 0, 0, 0, -1);
				event.getPlayer().setCooldown(Material.SUGAR, 60);
			}
			else if (event.getPlayer().getInventory().getItemInMainHand().isSimilar(Metyloamina.getItem()) && !event.getPlayer().hasCooldown(Material.SUGAR))
			{
				Inventory.removeItem(event.getPlayer(), Metyloamina.getItem(), 1);
				NeuroManager.modify(event.getPlayer(), 0, -40, 0, -9);
				event.getPlayer().setCooldown(Material.SUGAR, 60);
			}
			else if (event.getPlayer().getInventory().getItemInMainHand().isSimilar(Metylenoamina.getItem()) && !event.getPlayer().hasCooldown(Material.SUGAR))
			{
				Inventory.removeItem(event.getPlayer(), Metylenoamina.getItem(), 1);
				if (!NeuroManager.isDrugged(event.getPlayer()))
					NeuroManager.modify(event.getPlayer(), -40, -5, -10, 10);
				else
					NeuroManager.modify(event.getPlayer(), -5, -1, 1, -25);
				event.getPlayer().setCooldown(Material.SUGAR, 60);
			}
			else if (event.getPlayer().getInventory().getItemInMainHand().isSimilar(Xanax.getItem()) && !event.getPlayer().hasCooldown(Material.SUGAR))
			{
				Inventory.removeItem(event.getPlayer(), Xanax.getItem(), 1);
				Xanax.applyEffects(event.getPlayer());
				event.getPlayer().setCooldown(Material.SUGAR, 60);
			}
			else if (event.getPlayer().getInventory().getItemInMainHand().isSimilar(Fenyloaceton.getItem()) && !event.getPlayer().hasCooldown(Material.SUGAR))
			{
				Inventory.removeItem(event.getPlayer(), Fenyloaceton.getItem(), 1);
				NeuroManager.modify(event.getPlayer(), 0, 1, 2, 0);
				event.getPlayer().setCooldown(Material.SUGAR, 60);
			}
			else if (event.getPlayer().getInventory().getItemInMainHand().isSimilar(Formyloamfetamina.getItem()) && !event.getPlayer().hasCooldown(Material.SUGAR))
			{
				Inventory.removeItem(event.getPlayer(), Formyloamfetamina.getItem(), 1);
				NeuroManager.modify(event.getPlayer(), -17, 10, 19, -32);
				event.getPlayer().setCooldown(Material.SUGAR, 60);
			}
			else if (event.getPlayer().getInventory().getItemInMainHand().isSimilar(Amfetamina.getItem()) && !event.getPlayer().hasCooldown(Material.SUGAR))
			{
				Inventory.removeItem(event.getPlayer(), Amfetamina.getItem(), 1);
				NeuroManager.modify(event.getPlayer(), -5, 27, 34, -5);
				event.getPlayer().setCooldown(Material.SUGAR, 60);
				QuestUtil.manageQuest(event.getPlayer(), 8);
			}
			else if (event.getPlayer().getInventory().getItemInMainHand().isSimilar(Metyloformyloamid.getItem()) && !event.getPlayer().hasCooldown(Material.SUGAR))
			{
				Inventory.removeItem(event.getPlayer(), Metyloformyloamid.getItem(), 1);
				NeuroManager.modify(event.getPlayer(), 2, 2, 1, -1);
				event.getPlayer().setCooldown(Material.SUGAR, 60);
			}
			else if (event.getPlayer().getInventory().getItemInMainHand().isSimilar(Metamfetamina.getItem()) && !event.getPlayer().hasCooldown(Material.SUGAR))
			{
				Inventory.removeItem(event.getPlayer(), Metamfetamina.getItem(), 1);
				NeuroManager.modify(event.getPlayer(), 26, 16, 19, -5);
				event.getPlayer().setCooldown(Material.SUGAR, 60);
				QuestUtil.manageQuest(event.getPlayer(), 8);
			}
			else if (event.getPlayer().getInventory().getItemInMainHand().isSimilar(Metylenodioksynon.getItem()) && !event.getPlayer().hasCooldown(Material.SUGAR))
			{
				Inventory.removeItem(event.getPlayer(), Metylenodioksynon.getItem(), 1);
				NeuroManager.modify(event.getPlayer(), 1, 1, 1, 0);
				event.getPlayer().setCooldown(Material.SUGAR, 60);
			}
			else if (event.getPlayer().getInventory().getItemInMainHand().isSimilar(Metylenodioksyamfetamina.getItem()) && !event.getPlayer().hasCooldown(Material.SUGAR))
			{
				Inventory.removeItem(event.getPlayer(), Metylenodioksyamfetamina.getItem(), 1);
				NeuroManager.modify(event.getPlayer(), 2, 39, 46, 3);
				event.getPlayer().setCooldown(Material.SUGAR, 60);
				QuestUtil.manageQuest(event.getPlayer(), 8);
			}
			else if (event.getPlayer().getInventory().getItemInMainHand().isSimilar(Metylenodioksymetamfetamina.getItem()) && !event.getPlayer().hasCooldown(Material.SUGAR))
			{
				Inventory.removeItem(event.getPlayer(), Metylenodioksymetamfetamina.getItem(), 1);
				NeuroManager.modify(event.getPlayer(), 76, 29, 35, 1);
				event.getPlayer().setCooldown(Material.SUGAR, 60);
				QuestUtil.manageQuest(event.getPlayer(), 8);
			}
			else if (event.getPlayer().getInventory().getItemInMainHand().isSimilar(Naftyloaminopropan.getItem()) && !event.getPlayer().hasCooldown(Material.SUGAR))
			{
				Inventory.removeItem(event.getPlayer(), Naftyloaminopropan.getItem(), 1);
				NeuroManager.modify(event.getPlayer(), DPlayerManager.getInstance().getSerotonine(event.getPlayer().getName()),
						DPlayerManager.getInstance().getDopamine(event.getPlayer().getName()),
						DPlayerManager.getInstance().getNoradrenaline(event.getPlayer().getName()),
						DPlayerManager.getInstance().getGABA(event.getPlayer().getName()));
				event.getPlayer().setCooldown(Material.SUGAR, 60);
				QuestUtil.manageQuest(event.getPlayer(), 8);
			}
			else if (event.getPlayer().getInventory().getItemInMainHand().isSimilar(Heroina.getItem()) && !event.getPlayer().hasCooldown(Material.SUGAR))
			{
				Inventory.removeItem(event.getPlayer(), Heroina.getItem(), 1);
				Heroina.applyEffects(event.getPlayer());
				NeuroManager.modify(event.getPlayer(), 0, 0, 6, -14);
				event.getPlayer().setCooldown(Material.SUGAR, 60);
			}
			else if (event.getPlayer().getInventory().getItemInMainHand().isSimilar(Kokaina.getItem()) && !event.getPlayer().hasCooldown(Material.SUGAR))
			{
				Inventory.removeItem(event.getPlayer(), Kokaina.getItem(), 1);
				NeuroManager.modify(event.getPlayer(), -10, 45, 5, 0);
				event.getPlayer().setCooldown(Material.SUGAR, 60);
				QuestUtil.manageQuest(event.getPlayer(), 8);
			}
		}
	}
}