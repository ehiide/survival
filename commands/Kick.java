package mc.server.survival.commands;

import mc.server.Broadcaster;
import mc.server.survival.files.Configuration;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.managers.DPlayerManager;
import mc.server.survival.utils.ChatUtil;
import mc.server.survival.utils.ColorUtil;
import mc.server.survival.utils.SoundUtil;
import mc.server.survival.utils.VisualUtil;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kick  implements CommandExecutor
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
                player.kickPlayer(ColorUtil.formatHEX("\n#fc7474██&f█#fc7474██\n#fc7474██&f█#fc7474██\n#fc7474██&f█#fc7474██\n#fc7474█████\n#fc7474██&f█#fc7474██\n\n" +
                        "#f83044&lUTRACONO POLACZENIE\n#fc7474Zostales wyrzucony z serwera!\n\n#8c8c8cPowod: #fc7474Kto komu dolki kopie ten sam w nie wpada!\n#8c8c8cSprawca: #fc7474ANTI-TOXIC" +
                        "\n\n#666666&o(Nastepnym razem wymiar kary moze byc surowszy!)"));
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
                    {
                        String kicker = player.getName();

                        if (args.length == 1)
                        {
                            dplayer.kickPlayer(ColorUtil.formatHEX("\n#fc7474██&f█#fc7474██\n#fc7474██&f█#fc7474██\n#fc7474██&f█#fc7474██\n#fc7474█████\n#fc7474██&f█#fc7474██\n\n" +
                                    "#f83044&lUTRACONO POLACZENIE\n#fc7474Zostales wyrzucony z serwera!\n\n#8c8c8cPowod: #fc7474Brak powodu.\n#8c8c8cSprawca: #fc7474" + kicker +
                                    "\n\n#666666&o(Nastepnym razem wymiar kary moze byc surowszy!)"));
                        }
                        else
                        {

                            StringBuilder message = new StringBuilder();

                            for (int word = 1; word < args.length; word++)
                                if (word + 1 >= args.length)
                                    message.append(args[word].toLowerCase());
                                else
                                    message.append(args[word].toLowerCase()).append(" ");

                            String cause = ChatUtil.applyCorrection(String.valueOf(message));

                            if (args.length == 2 & args[1].equalsIgnoreCase("-konsola"))
                            {
                                kicker = "ANTI-TOXIC";
                                cause = "ANTI-TOXIC";
                            }

                            if (args.length == 2 & args[1].equalsIgnoreCase("-ukryj"))
                            {
                                kicker = "anonim";
                                cause = "Powod jest anonimowy!";
                            }

                            if (args.length == 2 & args[1].equalsIgnoreCase("-fake"))
                            {
                                dplayer.kickPlayer("Internal Exception: java.lang.NullPointerException");
                                return true;
                            }

                            if (args.length == 2 & args[1].equalsIgnoreCase("-fake:Null"))
                            {
                                dplayer.kickPlayer("Internal Exception: java.lang.NullPointerException");
                                return true;
                            }

                            if (args.length == 2 & args[1].equalsIgnoreCase("-fake:Packets"))
                            {
                                dplayer.kickPlayer("You are sending too many packets!");
                                return true;
                            }

                            if (args.length == 2 & args[1].equalsIgnoreCase("-fake:TimedOut"))
                            {
                                dplayer.kickPlayer("Timed out");
                                return true;
                            }

                            dplayer.kickPlayer(ColorUtil.formatHEX("\n#fc7474██&f█#fc7474██\n#fc7474██&f█#fc7474██\n#fc7474██&f█#fc7474██\n#fc7474█████\n#fc7474██&f█#fc7474██\n\n" +
                                    "#f83044&lUTRACONO POLACZENIE\n#fc7474Zostales wyrzucony z serwera!\n\n#8c8c8cPowod: #fc7474" + cause + "\n#8c8c8cSprawca: #fc7474" + kicker +
                                    "\n\n#666666&o(Nastepnym razem wymiar kary moze byc surowszy!)"));
                        }

                        Broadcaster.broadcastMessages("       ", "#fc7474██&f█#fc7474██", "#fc7474██&f█#fc7474██                     &f&l<#fc7474&l!&f&l> #fc7474&lKICK &f&l<#fc7474&l!&f&l>",
                                "#fc7474██&f█#fc7474██" + ChatUtil.centerString("#8c8c8cGracz #fc7474" + dplayer.getName() + " #8c8c8czostal wyjebany!", 3, 0), "#fc7474█████" + ChatUtil.centerString(" &cWymiar pokuty nalozyl " + kicker + "!", 0, 1), "#fc7474██&f█#fc7474██", "");
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