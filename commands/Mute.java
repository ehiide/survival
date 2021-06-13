package mc.server.survival.commands;

import mc.server.Broadcaster;
import mc.server.survival.files.Configuration;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.managers.DPlayerManager;
import mc.server.survival.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mute implements CommandExecutor
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
                ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Wyciszac graczy moze tylko administracja!");
                VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                VisualUtil.showPlayerParticle(player, Particle.FLAME);
                SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                return true;
            }

            if (args.length == 0)
            {
                ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cWprowadz nazwe gracza, ktorego chcesz wyciszyc!");
                return true;
            }
            else {
                String potencial_player = args[0];

                for (Player dplayer : Bukkit.getOnlinePlayers())
                    if (dplayer.getName().equalsIgnoreCase(potencial_player))
                    {
                        if (TimeUtil.getDifferenceInSeconds(DPlayerManager.getInstance().getMute(player.getName())) < DPlayerManager.getInstance().getMuteLength(player.getName()))
                        {
                            ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Ten gracz jest juz wyciszony, wiec nie mozesz go wyciszyc!");
                            VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                            VisualUtil.showPlayerParticle(player, Particle.FLAME);
                            SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                            return true;
                        }

                        if (args.length == 1)
                        {
                            ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cWprowadz czas, na jaki chcesz wyciszyc gracza!");
                            return true;
                        }

                        String init = args[1];

                        if (!isCorrectTime(init))
                        {
                            ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Podany czas nie jest obslugiwany, naucz sie cyfr!");
                            VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                            VisualUtil.showPlayerParticle(player, Particle.FLAME);
                            SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                            return true;
                        }

                        if (!isCorrectUnit(init))
                        {
                            ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Podany format czasowy nie jest obslugiwany!");
                            VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                            VisualUtil.showPlayerParticle(player, Particle.FLAME);
                            SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                            return true;
                        }

                        if (args.length == 2)
                        {
                            ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cWprowadz powod wyciszenia gracza!");
                        }
                        else {
                            StringBuilder message = new StringBuilder();

                            for (int word = 2; word < args.length; word++)
                                if (word + 1 >= args.length)
                                    message.append(args[word].toLowerCase());
                                else
                                    message.append(args[word].toLowerCase()).append(" ");

                            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                            LocalDateTime now = LocalDateTime.now();
                            String date = dateTimeFormatter.format(now);
                            DPlayerManager.getInstance().setMute(dplayer, date);
                            DPlayerManager.getInstance().setMuteLength(dplayer, catchTime(init));

                            String muter = player.getName();
                            String cause = ChatUtil.applyCorrection(message.toString());

                            if (args.length == 3 && args[2].equalsIgnoreCase("-konsola"))
                            {
                                muter = "ANTI-TOXIC";
                                cause = "ANTI-TOXIC!";
                            }

                            if (args.length == 3 && args[2].equalsIgnoreCase("-ukryj"))
                            {
                                muter = "anonim";
                                cause = "anonim!";
                            }

                            cause = cause.substring(0, cause.length() - 1);

                            Broadcaster.broadcastMessages("       ", "#ffc936██&f█#ffc936██", "#ffc936██&f█#ffc936██                     &f&l<#ffc936&l!&f&l> #ffc936&lMUTE &f&l<#ffc936&l!&f&l>",
                                    "#ffc936██&f█#ffc936██" + ChatUtil.centerString("#8c8c8cGracz #ffc936" + dplayer.getName() + " #8c8c8czamknal morde na okres #ffc936" + args[1].toLowerCase() + "#8c8c8c!", 5, 0), "#ffc936█████" + ChatUtil.centerString(" #f8ff26Wymiar pokuty nalozyl " + muter + "!", 1, 0), "#ffc936██&f█#ffc936██", "");
                            ChatManager.sendMessage(dplayer, Configuration.PLUGIN_FULL_PREFIX + "#ffc936Zostales wyciszony na czacie za: " + cause + "#ffc936!");
                            VisualUtil.showDelayedTitle(dplayer, " ", "#ffc936✖", 0, 10, 10);
                            QuestUtil.manageQuest(dplayer, 9);
                        }
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

    private boolean isCorrectUnit(String date)
    {
        String substring = date.substring(date.length() - 1);
        String[] units = {"s", "m", "h"};

        for (String unit : units)
        {
            if (substring.equalsIgnoreCase(unit))
            {
                return true;
            }
        }

        return false;
    }

    private int unit(String date)
    {
        final String substring = date.substring(date.length() - 1);

        if (substring.equalsIgnoreCase("s"))
            return 1;

        if (substring.equalsIgnoreCase("m"))
            return 60;

        if (substring.equalsIgnoreCase("h"))
            return 3600;

        return -1;
    }

    private boolean isCorrectTime(String date)
    {
        if (date.length() != 2 && date.length() != 3) return false;

        String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String substring = date.substring(date.length() - 2, date.length() - 1);

        if (date.length() == 2)
        {
            for (String number : numbers)
            {
                if (substring.equalsIgnoreCase(number))
                    return true;
            }

            return false;
        }

        for (String number : numbers)
        {
            if (substring.equalsIgnoreCase(number))
                for (String number2 : numbers)
                    if (date.substring(0, date.length() - 2).equalsIgnoreCase(number2))
                        return true;
        }

        return false;
    }

    private int catchTime(String date)
    {
        String time = date.substring(0, date.length() - 1);
        return Integer.parseInt(time) * unit(date);
    }
}