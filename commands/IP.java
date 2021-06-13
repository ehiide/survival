package mc.server.survival.commands;

import mc.server.survival.files.Configuration;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.managers.DPlayerManager;
import mc.server.survival.utils.ChatUtil;
import mc.server.survival.utils.SoundUtil;
import mc.server.survival.utils.VisualUtil;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class IP
implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;

            if (!DPlayerManager.getInstance().getRank(player).equalsIgnoreCase("administrator"))
            {
                ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Chcialbys poznac kogos IP co? Musisz wiedziec, ze hakerami sa tylko admini!");
                VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                VisualUtil.showPlayerParticle(player, Particle.FLAME);
                SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                return true;
            }

            if (args.length == 0)
                ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cTwoj adres IP to: " + ChatUtil.returnPlayerColor(player) + Objects.requireNonNull(player.getAddress()).getAddress() + "!");
            else
            {
                String potencial_player = args[0];

                for (Player dplayer : Bukkit.getOnlinePlayers())
                    if (dplayer.getName().equalsIgnoreCase(potencial_player))
                    {
                        ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cAdres IP " + ChatUtil.returnPlayerColor(dplayer) + dplayer.getName() + "#8c8c8c to "  + ChatUtil.returnPlayerColor(dplayer)+ Objects.requireNonNull(dplayer.getAddress()).getAddress() + "!");
                        return true;
                    }

                ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Podany gracz nie jest on-line na serwerze!");
                VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                VisualUtil.showPlayerParticle(player, Particle.FLAME);
                SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
            }
            return true;
        }

        return false;
    }
}