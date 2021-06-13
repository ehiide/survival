package mc.server.survival.commands;

import mc.server.Broadcaster;
import mc.server.survival.files.Configuration;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.managers.DPlayerManager;
import mc.server.survival.managers.GangManager;
import mc.server.survival.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Powiedz implements CommandExecutor
{
    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;

            if (!DPlayerManager.getInstance().getRank(player).equalsIgnoreCase("administrator"))
            {
                Broadcaster.broadcastMessage(ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.getGangInChat(GangManager.getInstance().getGang(player)) + ChatUtil.returnMarryPrefix(player) +  "&r" + ChatUtil.returnPlayerColor(player) + Objects.requireNonNull(player.getPlayer()).getName() + "#8c8c8c Debil ze mnie."));
                return true;
            }

            if (args.length == 0)
            {
                ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cWprowadz nazwe gracza, aby kontynuowac!");
                return true;
            }
            else
            {
                String potencial_player = args[0];

                for (Player dplayer : Bukkit.getOnlinePlayers())
                    if (dplayer.getName().equalsIgnoreCase(potencial_player))
                        if (args.length == 1)
                        {
                            ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cWprowadz swoja falszywa wiadomosc, ktora chcesz wyslac!");
                            return true;
                        }
                        else
                        {
                            StringBuilder message = new StringBuilder();

                            for (int word = 1; word < args.length; word++)
                                if (word + 1 >= args.length)
                                    message.append(args[word].toLowerCase());
                                else
                                    message.append(args[word].toLowerCase()).append(" ");

                            String fakeMessage = ChatUtil.getPlaceholder(dplayer, ChatUtil.applyCorrection(message.toString()));
                            Broadcaster.broadcastMessage(ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.getGangInChat(GangManager.getInstance().getGang(dplayer)) + ChatUtil.returnMarryPrefix(dplayer) +  "&r" + ChatUtil.returnPlayerColor(dplayer) + Objects.requireNonNull(dplayer.getPlayer()).getName() + "#8c8c8c " + fakeMessage));
                            return true;
                        }

                if (!Bukkit.getOfflinePlayer(potencial_player).isOnline())
                {
                    ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Podany gracz nie jest online na serwerze!");
                    VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                    VisualUtil.showPlayerParticle(player, Particle.FLAME);
                    SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                    return true;
                }
            }
        }

        return false;
    }
}