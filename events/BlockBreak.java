package mc.server.survival.events;

import mc.server.survival.files.Configuration;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.managers.DPlayerManager;
import mc.server.survival.utils.*;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Crops;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.Objects;

public class BlockBreak 
implements Listener
{
    private final Material[] ores = {Material.COAL_ORE, Material.COPPER_ORE, Material.IRON_ORE, Material.GOLD_ORE, Material.NETHER_GOLD_ORE,
                                     Material.EMERALD_ORE, Material.DIAMOND_ORE, Material.LAPIS_ORE, Material.REDSTONE_ORE,
                                     Material.NETHER_QUARTZ_ORE, Material.DEEPSLATE_COAL_ORE, Material.DEEPSLATE_COPPER_ORE,
									 Material.DEEPSLATE_GOLD_ORE, Material.DEEPSLATE_EMERALD_ORE, Material.DEEPSLATE_DIAMOND_ORE,
									 Material.DEEPSLATE_LAPIS_ORE, Material.DEEPSLATE_REDSTONE_ORE,};

    private final Material[] woods = {Material.OAK_LOG, Material.BIRCH_LOG, Material.SPRUCE_LOG, Material.JUNGLE_LOG,
                                      Material.ACACIA_LOG, Material.DARK_OAK_LOG};

	@EventHandler
	public void onEvent(BlockBreakEvent event)
	{
		Player player = event.getPlayer();
		Block block = event.getBlock();
		
		if (player.getWorld().getName().equalsIgnoreCase("survival") && Configuration.SERVER_TERRAIN_PROTECTION)
		{
			Location blockLoc = block.getLocation();
			
			if (blockLoc.distance(WorldUtil.SURVIVAL_SPAWN) < 128 || blockLoc.distance(WorldUtil.SURVIVAL_PIER) < 32 ||
				blockLoc.distance(WorldUtil.SURVIVAL_MOUNTAIN) < 48 || allowedDist(blockLoc, WorldUtil.WYSPA_DZUNGLA_SPAWN) ||
				allowedDist(blockLoc, WorldUtil.WYSPA_GRZYBOWA_SPAWN) || allowedDist(blockLoc, WorldUtil.WYSPA_LAS_SPAWN) ||
				allowedDist(blockLoc, WorldUtil.WYSPA_LODOWA_SPAWN)|| allowedDist(blockLoc, WorldUtil.WYSPA_SAVANNA_SPAWN) ||
				allowedDist(blockLoc, WorldUtil.WYSPA_GORY_SPAWN) || allowedDist(blockLoc, WorldUtil.WYSPA_PUSTYNNA_SPAWN) ||
				allowedDist(blockLoc, WorldUtil.WYSPA_MESSA_SPAWN))
			{
				event.setCancelled(true);
				return;
			}
		}

		if (Configuration.SERVER_FUNCTION_OREMINER)
			for (Material ore : ores)
				if (block.getType() == ore)
					if (!player.isSneaking())
						runMiner(player, block, block.getType());
					else
					{
						if (MathUtil.chanceOf(100 - Configuration.SERVER_DROP_CHANCE))
						{
							if (block.getType() == Material.NETHER_QUARTZ_ORE || block.getType() == Material.NETHER_GOLD_ORE)
							{
								Item dropItem = player.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.NETHERRACK));
								if (!Objects.requireNonNull(dropItem.getItemStack().getItemMeta()).getDisplayName().equalsIgnoreCase(""))
									dropItem.setCustomName(ChatColor.translateAlternateColorCodes('&', "&a" + Objects.requireNonNull(dropItem.getItemStack().getItemMeta()).getDisplayName() + " &ex" + dropItem.getItemStack().getAmount()));
								else
									dropItem.setCustomName(ChatColor.translateAlternateColorCodes('&', "&a" + dropItem.getName() + " &ex" + dropItem.getItemStack().getAmount()));
								dropItem.setCustomNameVisible(true);
							}
							else
							{
								Item dropItem = player.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.COBBLESTONE));
								if (!Objects.requireNonNull(dropItem.getItemStack().getItemMeta()).getDisplayName().equalsIgnoreCase(""))
									dropItem.setCustomName(ChatColor.translateAlternateColorCodes('&', "&a" + Objects.requireNonNull(dropItem.getItemStack().getItemMeta()).getDisplayName() + " &ex" + dropItem.getItemStack().getAmount()));
								else
									dropItem.setCustomName(ChatColor.translateAlternateColorCodes('&', "&a" + dropItem.getName() + " &ex" + dropItem.getItemStack().getAmount()));
								dropItem.setCustomNameVisible(true);
							}

							block.setType(Material.AIR);
						}
					}

		if (Configuration.SERVER_FUNCTION_TIMBER)
			for (Material wood : woods)
				if (block.getType() == wood)
					if (!player.isSneaking())
						runTimber(player, block, block.getType());
					else
						if (MathUtil.chanceOf(100 - Configuration.SERVER_DROP_CHANCE))
							block.setType(Material.AIR);
		
		if (block.getType() == Material.ACACIA_LEAVES || block.getType() == Material.BIRCH_LEAVES || block.getType() == Material.OAK_LEAVES ||
			block.getType() == Material.SPRUCE_LEAVES || block.getType() == Material.JUNGLE_LEAVES || block.getType() == Material.DARK_OAK_LEAVES)
		{
			if (MathUtil.chanceOf(10 + (4 * (DPlayerManager.getInstance().getUpgradeLevel(player.getName(), "loot")))))
			{
				Item dropItem = player.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.APPLE));
				if (!Objects.requireNonNull(dropItem.getItemStack().getItemMeta()).getDisplayName().equalsIgnoreCase(""))
					dropItem.setCustomName(ChatColor.translateAlternateColorCodes('&', "&a" + Objects.requireNonNull(dropItem.getItemStack().getItemMeta()).getDisplayName() + " &ex" + dropItem.getItemStack().getAmount()));
				else
					dropItem.setCustomName(ChatColor.translateAlternateColorCodes('&', "&a" + dropItem.getName() + " &ex" + dropItem.getItemStack().getAmount()));
				dropItem.setCustomNameVisible(true);
			}
			if (MathUtil.chanceOf(20 + (2 * (DPlayerManager.getInstance().getUpgradeLevel(player.getName(), "loot")))))
			{
				Item dropItem = player.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.STICK));
				if (!Objects.requireNonNull(dropItem.getItemStack().getItemMeta()).getDisplayName().equalsIgnoreCase(""))
					dropItem.setCustomName(ChatColor.translateAlternateColorCodes('&', "&a" + Objects.requireNonNull(dropItem.getItemStack().getItemMeta()).getDisplayName() + " &ex" + dropItem.getItemStack().getAmount()));
				else
					dropItem.setCustomName(ChatColor.translateAlternateColorCodes('&', "&a" + dropItem.getName() + " &ex" + dropItem.getItemStack().getAmount()));
				dropItem.setCustomNameVisible(true);
			}
		}

		if (block.getType() == Material.ANCIENT_DEBRIS)
			if (MathUtil.chanceOf(1 + (2 * (DPlayerManager.getInstance().getUpgradeLevel(player.getName(), "loot")))))
			{
				Item dropItem = player.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.NETHERITE_SCRAP));
				if (!Objects.requireNonNull(dropItem.getItemStack().getItemMeta()).getDisplayName().equalsIgnoreCase(""))
					dropItem.setCustomName(ChatColor.translateAlternateColorCodes('&', "&a" + Objects.requireNonNull(dropItem.getItemStack().getItemMeta()).getDisplayName() + " &ex" + dropItem.getItemStack().getAmount()));
				else
					dropItem.setCustomName(ChatColor.translateAlternateColorCodes('&', "&a" + dropItem.getName() + " &ex" + dropItem.getItemStack().getAmount()));
				dropItem.setCustomNameVisible(true);
			}

		if (block.getType() == Material.ACACIA_LOG || block.getType() == Material.BIRCH_LOG || block.getType() == Material.OAK_LOG ||
			block.getType() == Material.SPRUCE_LOG || block.getType() == Material.JUNGLE_LOG || block.getType() == Material.DARK_OAK_LOG)
			QuestUtil.manageQuest(player, 1);

		if (block.getType() == Material.STONE || block.getType() == Material.COBBLESTONE)
			QuestUtil.manageQuest(player, 2);

		if (block.getType() == Material.SAND || block.getType() == Material.RED_SAND ||
			block.getType() == Material.GRAVEL || block.getType() == Material.DIRT || block.getType() == Material.GRASS_BLOCK)
			QuestUtil.manageQuest(player, 3);

		if (block.getType() == Material.WHEAT || block.getType() == Material.CARROTS ||
			block.getType() == Material.BEETROOTS || block.getType() == Material.POTATOES)
		{
			Ageable ageable = (Ageable) block.getBlockData();
			if (ageable.getAge() == ageable.getMaximumAge())
				QuestUtil.manageQuest(player, 11);
		}

		if (player.getGameMode() == GameMode.SURVIVAL)
		{
			for (ItemStack dropItem : block.getDrops(player.getItemInHand()))
			{
				Item item = player.getWorld().dropItemNaturally(block.getLocation(), dropItem);
				item.setCustomName(ChatColor.translateAlternateColorCodes('&', "&a" + item.getName() + " &ex" + item.getItemStack().getAmount()));
				item.setCustomNameVisible(true);
			}

			event.setDropItems(false);
		}
	}

	private boolean allowedDist(Location from, Location to)
	{
		return from.distance(to) <= Configuration.SERVER_SPAWN_PROTECTION;
	}

	private void runMiner(Player player, Block block, Material road)
    {
        if (player.isSneaking())
            return;

        ItemStack item = player.getItemInHand();
        ArrayList<Block> blockRoad = new ArrayList<>();

        for (int x = -1; x <= 1; x++)
        {
            for (int y = -1; y <= 1; y++)
            {
                for (int z = -1; z <= 1; z++)
                {
                    if (player.getWorld().getBlockAt(block.getLocation().add(x, y, z)).getType().toString().equalsIgnoreCase(road.toString()))
                    {
                        blockRoad.add(player.getWorld().getBlockAt(block.getLocation().add(x, y, z)));
                        if (MathUtil.chanceOf(Configuration.SERVER_DROP_CHANCE))
                        {
                        	Block targetBlock = player.getWorld().getBlockAt(block.getLocation().add(x, y, z));

							for (ItemStack dropItem : targetBlock.getDrops(item))
							{
								Item items = player.getWorld().dropItemNaturally(block.getLocation(), dropItem);
								items.setCustomName(ChatColor.translateAlternateColorCodes('&', "&a" + items.getName() + " &ex" + items.getItemStack().getAmount()));
								items.setCustomNameVisible(true);
							}

							targetBlock.setType(Material.AIR);
						}
                        else
                    	{
							player.getWorld().getBlockAt(block.getLocation().add(x, y, z)).breakNaturally(new ItemStack(Material.STICK));

							Item dropItem;
							if (road == Material.NETHER_QUARTZ_ORE || road == Material.NETHER_GOLD_ORE)
								dropItem = player.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.NETHERRACK));
							else
								dropItem = player.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.COBBLESTONE));

							if (!Objects.requireNonNull(dropItem.getItemStack().getItemMeta()).getDisplayName().equalsIgnoreCase(""))
								dropItem.setCustomName(ChatColor.translateAlternateColorCodes('&', "&a" + Objects.requireNonNull(dropItem.getItemStack().getItemMeta()).getDisplayName() + " &ex" + dropItem.getItemStack().getAmount()));
							else
								dropItem.setCustomName(ChatColor.translateAlternateColorCodes('&', "&a" + dropItem.getName() + " &ex" + dropItem.getItemStack().getAmount()));

							dropItem.setCustomNameVisible(true);
                    	}
                    }
                }
            }
        }

        for (Block blocks : blockRoad)
            runMiner(player, blocks, road);
    }

	private void runTimber(Player player, Block block, Material road)
	{
		if (player.isSneaking())
			return;

		ItemStack item = player.getItemInHand();
		ArrayList<Block> blockRoad = new ArrayList<>();

		Material roadEnd = null;

		if (road == Material.OAK_LOG)
			roadEnd = Material.OAK_WOOD;
		else if (road == Material.BIRCH_LOG)
			roadEnd = Material.BIRCH_WOOD;
		else if (road == Material.SPRUCE_LOG)
			roadEnd = Material.SPRUCE_WOOD;
		else if (road == Material.ACACIA_LOG)
			roadEnd = Material.ACACIA_WOOD;
		else if (road == Material.DARK_OAK_LOG)
			roadEnd = Material.DARK_OAK_WOOD;
		else if (road == Material.JUNGLE_LOG)
			roadEnd = Material.JUNGLE_WOOD;

		for (int x = -1; x <= 1; x++)
		{
			for (int y = -1; y <= 1; y++)
			{
				for (int z = -1; z <= 1; z++)
				{
					if (player.getWorld().getBlockAt(block.getLocation().add(x, y, z)).getType().toString().equalsIgnoreCase(roadEnd.toString()))
					{
						Block targetBlock = player.getWorld().getBlockAt(block.getLocation().add(x, y, z));

						for (ItemStack dropItem : targetBlock.getDrops(item))
						{
							Item items = player.getWorld().dropItemNaturally(block.getLocation(), dropItem);
							items.setCustomName(ChatColor.translateAlternateColorCodes('&', "&a" + items.getName() + " &ex" + items.getItemStack().getAmount()));
							items.setCustomNameVisible(true);
						}

						targetBlock.setType(Material.AIR);
						return;
					}

					if (player.getWorld().getBlockAt(block.getLocation().add(x, y, z)).getType().toString().equalsIgnoreCase(road.toString()))
					{
						blockRoad.add(player.getWorld().getBlockAt(block.getLocation().add(x, y, z)));
						if (MathUtil.chanceOf(Configuration.SERVER_DROP_CHANCE))
						{
							Block targetBlock = player.getWorld().getBlockAt(block.getLocation().add(x, y, z));

							for (ItemStack dropItem : targetBlock.getDrops(item))
							{
								Item items = player.getWorld().dropItemNaturally(block.getLocation(), dropItem);
								items.setCustomName(ChatColor.translateAlternateColorCodes('&', "&a" + items.getName() + " &ex" + items.getItemStack().getAmount()));
								items.setCustomNameVisible(true);
							}

							targetBlock.setType(Material.AIR);
						}
						else
						{
							Block targetBlock = player.getWorld().getBlockAt(block.getLocation().add(x, y, z));
							targetBlock.setType(Material.AIR);

							Item dropItem = player.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.STICK));
							if (!Objects.requireNonNull(dropItem.getItemStack().getItemMeta()).getDisplayName().equalsIgnoreCase(""))
								dropItem.setCustomName(ChatColor.translateAlternateColorCodes('&', "&a" + Objects.requireNonNull(dropItem.getItemStack().getItemMeta()).getDisplayName() + " &ex" + dropItem.getItemStack().getAmount()));
							else
								dropItem.setCustomName(ChatColor.translateAlternateColorCodes('&', "&a" + dropItem.getName() + " &ex" + dropItem.getItemStack().getAmount()));
							dropItem.setCustomNameVisible(true);
						}
					}
				}
			}
		}

		for (Block blocks : blockRoad)
			runTimber(player, blocks, road);
	}
}