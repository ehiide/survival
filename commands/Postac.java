package mc.server.survival.commands;

import mc.server.survival.files.Configuration;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.utils.InventoryUtil;
import mc.server.survival.utils.SoundUtil;
import mc.server.survival.utils.VisualUtil;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Postac
implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;

            if (args.length == 0)
            {
                ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cTrwa otwieranie menu postaci... #fff203⌛");
                InventoryUtil.createNewInventory(player, 54, ChatColor.translateAlternateColorCodes('&', "&c&lPOSTAC"), "postac");
            }
            else
            {
                ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Umiesz pisac?! Wystarczy napisac #ffc936/postac!");
                VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                VisualUtil.showPlayerParticle(player, Particle.FLAME);
                SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
            }
            return true;
        }

        return false;
    }
}