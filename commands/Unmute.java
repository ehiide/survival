package mc.server.survival.commands;

import mc.server.Broadcaster;
import mc.server.survival.files.Configuration;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.managers.DPlayerManager;
import mc.server.survival.utils.ChatUtil;
import mc.server.survival.utils.SoundUtil;
import mc.server.survival.utils.TimeUtil;
import mc.server.survival.utils.VisualUtil;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Unmute
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
                ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Tylko administracja ma prawo odciszac graczy!");
                VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                VisualUtil.showPlayerParticle(player, Particle.FLAME);
                SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                return true;
            }

            if (args.length == 0)
            {
                ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cWprowadz nazwe gracza, ktorego chcesz odciszyc!");
                return true;
            }
            else
            {
                String potencial_player = args[0];

                for (Player dplayer : Bukkit.getOnlinePlayers())
                    if (dplayer.getName().equalsIgnoreCase(potencial_player))
                    {
                        if (!(TimeUtil.getDifferenceInSeconds(DPlayerManager.getInstance().getMute(dplayer.getName())) < DPlayerManager.getInstance().getMuteLength(dplayer.getName())))
                        {
                            ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Ten gracz nie jest nawet wyciszony, wiemy ze masz dobre serduszko ale bez przesady!");
                            VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                            VisualUtil.showPlayerParticle(player, Particle.FLAME);
                            SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                            return true;
                        }

                        String unmuter = player.getName();

                        if (args.length == 2 && args[1].equalsIgnoreCase("-ukryj"))
                        {
                            unmuter = "anonima";
                        }

                        DPlayerManager.getInstance().setMuteLength(dplayer, 0);
                        Broadcaster.broadcastMessages("       ", "#80ff1f██&f█#80ff1f██", "#80ff1f██&f█#80ff1f██                    &f&l<#80ff1f&l!&f&l> #80ff1f&lUNMUTE &f&l<#80ff1f&l!&f&l>",
                                "#80ff1f██&f█#80ff1f██" + ChatUtil.centerString("#8c8c8cGracz #80ff1f" + dplayer.getName() + " #8c8c8cwyjal chuja z buzi!", 3, 0), "#80ff1f█████" + ChatUtil.centerString(" &aOznacza to, ze moze juz pisac i mowic!", 0, 1), "#80ff1f██&f█#80ff1f██", "");
                        ChatManager.sendMessage(dplayer, Configuration.PLUGIN_FULL_PREFIX + "#80ff1fZostales odciszony przez " + unmuter + ", lecz uwazaj bo nastepnym razem moze nie byc taki mily!");
                        VisualUtil.showDelayedTitle(dplayer, " ", "#80ff1f✔", 0, 10, 10);
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