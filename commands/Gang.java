package mc.server.survival.commands;

import mc.server.Broadcaster;
import mc.server.survival.files.Configuration;
import mc.server.survival.files.Main;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.managers.DPlayerCloudManager;
import mc.server.survival.managers.DPlayerManager;
import mc.server.survival.managers.GangManager;
import mc.server.survival.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class Gang
implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;

            if (args.length == 0)
                ChatManager.sendMessages(player, "",
                         "#f83044&m---------------------------------------",
                         "#fc7474/gang stworz (nazwa) #8c8c8c- tworzy Twoj wlasny gang, musisz jednak posiadac 1000 monet oraz nie byc w zadnym z innych klanow",
                         "#fc7474/gang zapros (gracz) #8c8c8c- zaprasza gracza do Twojego gangu, nie moze byc on jednak w zadnym innym gangu",
                         "#fc7474/gang dolacz (gracz) #8c8c8c- akceptuje zaproszenie do gangu od konkretnego gracza",
                         "#fc7474/gang wyrzuc (gracz) #8c8c8c- usuwa wskzanego gracza z Twojego gangu, wymaga rangi lidera",
                         "#fc7474/gang lider (gracz) #8c8c8c- ustawia nowego lidera gangu",
                         "#fc7474/gang baza [ustaw] #8c8c8c- teleportuje badz ustawia baze gangu, do ktorej mozesz sie teleportowac",
                         "#fc7474/gang info [nazwa] #8c8c8c- wyswietla informacje o Twoim gangu, opcjonalnie innego",
                         "#fc7474/gang friendlyfire #8c8c8c- blokuje/odblokowuje mozliwosc wzajemnego bicia czlonkow gangu",
                         "#fc7474/gang tpall #8c8c8c- teleportuje wszystkich czlonkow gangu do lidera",
                         "#fc7474/gang ulepsz #8c8c8c- otwiera menu w sklepie, w ktorym mozesz personalizowac i ulepszac swoj gang",
                         "#fc7474/gang opusc #8c8c8c- opuszcza gang, w ktorym sie znajdujesz, jesli jestes liderem, gang sie usunie",
                         "&8&o(argumenty wymagane - (), argumewnty opcjonalne - [])",
                         "#f83044&m---------------------------------------", "");
            else
            {
                if (args.length > 2)
                {
                    ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Co kurwa?! Wpisz #ffc936/gang#fc7474 i nie pierdol!");
                    VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                    VisualUtil.showPlayerParticle(player, Particle.FLAME);
                    SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                    return true;
                }

                if (args[0].equalsIgnoreCase("stworz"))
                    if (args.length > 1)
                    {
                        if (args[1].length() > 5 || args[1].length() < 3)
                        {
                            ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Nazwa Twojego gangu moze miec od 3 do 5 znakow!");
                            VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                            VisualUtil.showPlayerParticle(player, Particle.FLAME);
                            SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                            return true;
                        }

                        if (DPlayerManager.getInstance().getMoney(player) < 1000)
                        {
                            ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Masz za malo monet, aby stworzyc gang!");
                            VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                            VisualUtil.showPlayerParticle(player, Particle.FLAME);
                            SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                            return true;
                        }

                        if (GangManager.getInstance().isExist(args[1].toUpperCase()))
                        {
                            ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Gang o takiej nazwie juz istnieje!");
                            VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                            VisualUtil.showPlayerParticle(player, Particle.FLAME);
                            SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                            return true;
                        }

                        if (GangManager.getInstance().getGang(player) == null)
                        {
                            Broadcaster.broadcastMessages("       ", "&f█████", "&f█#85f5ff█&f█#85f5ff█&f█               &f&l<#85f5ff&l!&f&l> #85f5ff&lPOWSTAL GANG &f&l<#85f5ff&l!&f&l>",
                                    "&f█████" + ChatUtil.centerString(" #8c8c8cPrzywitajmy nowy gang, &f" + args[1].toUpperCase() + "#8c8c8c!", 2, 1), "#85f5ff█&f███#85f5ff█" + ChatUtil.centerString("&fZalozycielem gangu jest " + player.getName() + "!", 0, 1), "#85f5ff█&f█#85f5ff█&f█#85f5ff█", "");
                            GangManager.getInstance().createGang(player, args[1].toUpperCase());
                            DPlayerManager.getInstance().setMoney(player, DPlayerManager.getInstance().getMoney(player) - 1000);
                            ServerUtil.reloadContents(player);
                        }
                        else
                        {
                            ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Znajdujesz sie juz w gangu, wiec nie mozesz stworzyc nowego!");
                            VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                            VisualUtil.showPlayerParticle(player, Particle.FLAME);
                            SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                        }
                        return true;
                    }
                    else
                    {
                        ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cWpisz nazwe swojego gangu, pamietaj, ze moze ona miec od 3 do 5 znakow!");
                        return true;
                    }
                else if (args[0].equalsIgnoreCase("opusc"))
                {
                    if (GangManager.getInstance().getGang(player) == null)
                    {
                        ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Nie znajdujesz sie w zadnym gangu, wiec nie mozesz go opuscic!");
                        VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                        VisualUtil.showPlayerParticle(player, Particle.FLAME);
                        SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                    }
                    else
                    {
                        ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cOdszedles od swojego gangu!");
                        GangManager.getInstance().removePlayer(player);
                    }

                    return true;
                }
                else if (args[0].equalsIgnoreCase("lider"))
                {
                    if (GangManager.getInstance().getGang(player) == null)
                    {
                        ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Ziomek, Ty nawet nie masz gangu!");
                        VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                        VisualUtil.showPlayerParticle(player, Particle.FLAME);
                        SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                        return true;
                    }

                    if (!GangManager.getInstance().getLider(GangManager.getInstance().getGang(player)).equalsIgnoreCase(player.getName()))
                    {
                        ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Tylko lider moze dokonac zmiany lidera!");
                        VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                        VisualUtil.showPlayerParticle(player, Particle.FLAME);
                        SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                        return true;
                    }

                    if (args.length < 2)
                    {
                        ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cWpisz nazwe gracza, ktoremu chcesz oddac stanowisko lidera!");
                        return true;
                    }

                    String potencial_lider = args[1];

                    for (Player lider : Bukkit.getOnlinePlayers())
                        if (lider.getName().equalsIgnoreCase(potencial_lider))
                        {
                            if (!GangManager.getInstance().getGang(lider).equalsIgnoreCase(GangManager.getInstance().getGang(player)))
                            {
                                ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Ten gracz nawet nie jest w Twoim gangu!");
                                VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                                VisualUtil.showPlayerParticle(player, Particle.FLAME);
                                SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                                return true;
                            }

                            if (GangManager.getInstance().getLider(GangManager.getInstance().getGang(lider)).equalsIgnoreCase(lider.getName()))
                            {
                                ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Jestes juz na stanowisku lidera!");
                                VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                                VisualUtil.showPlayerParticle(player, Particle.FLAME);
                                SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                                return true;
                            }

                            ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cUstawiles nowego lidera gangu, " + ChatUtil.returnPlayerColor(lider) + lider.getName() + "!");
                            ChatManager.sendMessage(lider, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cZostales nowym liderem swojego gangu!");
                            GangManager.getInstance().setLider(lider);
                            return true;
                        }

                    ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Podany gracz nie jest on-line na serwerze!");
                    VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                    VisualUtil.showPlayerParticle(player, Particle.FLAME);
                    SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                    return true;
                }
                else if (args[0].equalsIgnoreCase("info"))
                {
                    if (args.length > 1)
                    {
                       String potencial_gang = args[1];
                       boolean isExist = GangManager.getInstance().isExist(potencial_gang);

                       if (!isExist)
                       {
                           ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Nie ma takiego gangu na serwerze!");
                           VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                           VisualUtil.showPlayerParticle(player, Particle.FLAME);
                           SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                           return true;
                       }

                        String gang = potencial_gang.toUpperCase();

                        ChatManager.sendMessages(player, "       ", "&f█████", "&f█#85f5ff█&f█#85f5ff█&f█   #85f5ff* #8c8c8cWyglad prefixu gangu " + gang.toUpperCase() + " to " + ChatUtil.getGangInChat(gang),
                                "&f█████   #85f5ff* #8c8c8cObecnym liderem jest " + ChatUtil.getValidGangColor(gang) + GangManager.getInstance().getLider(gang), "#85f5ff█&f███#85f5ff█   #85f5ff* #8c8c8cLiczba czlonkow wynosi " + ChatUtil.getValidGangColor(gang) + GangManager.getInstance().getMembers(gang), "#85f5ff█&f█#85f5ff█&f█#85f5ff█", "");

                        return true;
                    }

                    String gang = GangManager.getInstance().getGang(player);

                    if (gang == null)
                    {
                        ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Nie nalezysz do zadnego gangu!");
                        VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                        VisualUtil.showPlayerParticle(player, Particle.FLAME);
                        SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                        return true;
                    }

                    ChatManager.sendMessages(player, "       ", "&f█████", "&f█#85f5ff█&f█#85f5ff█&f█   #85f5ff* #8c8c8cWyglad prefixu Twojego gangu to " + ChatUtil.getGangInChat(gang),
                            "&f█████   #85f5ff* #8c8c8cObecnym liderem jest " + ChatUtil.getValidGangColor(gang) + GangManager.getInstance().getLider(gang), "#85f5ff█&f███#85f5ff█   #85f5ff* #8c8c8cLiczba czlonkow wynosi " + ChatUtil.getValidGangColor(gang) + GangManager.getInstance().getMembers(gang), "#85f5ff█&f█#85f5ff█&f█#85f5ff█", "");

                    return true;
                }
                else if (args[0].equalsIgnoreCase("friendlyfire"))
                {
                    if (GangManager.getInstance().getGang(player) == null)
                    {
                        ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Ziomek, Ty nawet nie masz gangu!");
                        VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                        VisualUtil.showPlayerParticle(player, Particle.FLAME);
                        SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                        return true;
                    }

                    if (!GangManager.getInstance().getLider(GangManager.getInstance().getGang(player)).equalsIgnoreCase(player.getName()))
                    {
                        ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Musisz byc liderem gangu, aby zmienic to ustawienie!");
                        VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                        VisualUtil.showPlayerParticle(player, Particle.FLAME);
                        SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                        return true;
                    }

                    GangManager.getInstance().toggleFriendlyFire(GangManager.getInstance().getGang(player));
                    ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cZmieniono ustawienie FriendlyFire na " + ChatUtil.isGangFriendlyFire(GangManager.getInstance().getGang(player)) + "#8c8c8c!");
                    return true;
                }
                else if (args[0].equalsIgnoreCase("wyrzuc"))
                {
                    if (GangManager.getInstance().getGang(player) == null)
                    {
                        ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Ziomek, Ty nawet nie masz gangu!");
                        VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                        VisualUtil.showPlayerParticle(player, Particle.FLAME);
                        SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                        return true;
                    }

                    if (!GangManager.getInstance().getLider(GangManager.getInstance().getGang(player)).equalsIgnoreCase(player.getName()))
                    {
                        ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Tylko lider moze wyrzucac czlonkow gangu!");
                        VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                        VisualUtil.showPlayerParticle(player, Particle.FLAME);
                        SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                        return true;
                    }

                    if (args.length < 2)
                    {
                        ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cWpisz nazwe gracza, ktorego chcesz wyjebac!");
                        return true;
                    }

                    String potencial_kicker = args[1];

                    for (Player kicker : Bukkit.getOnlinePlayers())
                        if (kicker.getName().equalsIgnoreCase(potencial_kicker))
                        {
                            if (!GangManager.getInstance().getGang(kicker).equalsIgnoreCase(GangManager.getInstance().getGang(player)))
                            {
                                ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Ten gracz nawet nie jest w Twoim gangu!");
                                VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                                VisualUtil.showPlayerParticle(player, Particle.FLAME);
                                SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                                return true;
                            }

                            GangManager.getInstance().removePlayer(kicker);
                            ChatManager.sendMessage(kicker, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Zostales wyrzucony ze swojego gangu!");
                            ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cPomyslnie wyrzucono gracza " + ChatUtil.returnPlayerColor(kicker) + kicker.getName() + "!");
                            return true;
                        }

                    ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Podany gracz nie jest on-line na serwerze!");
                    VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                    VisualUtil.showPlayerParticle(player, Particle.FLAME);
                    SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                    return true;
                }
                else if (args[0].equalsIgnoreCase("ulepsz"))
                {
                    InventoryUtil.createNewInventory(player, 45, ChatColor.translateAlternateColorCodes('&', "&c&lSKLEP GANGU"), "sklep_gang");
                    return  true;
                }
                else if (args[0].equalsIgnoreCase("zapros"))
                {
                    if (GangManager.getInstance().getGang(player) == null)
                    {
                        ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Ziomek, Ty nawet nie masz gangu!");
                        VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                        VisualUtil.showPlayerParticle(player, Particle.FLAME);
                        SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                        return true;
                    }

                    if (args.length == 1)
                    {
                        ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cWpisz nazwe gracza, do ktorego chcesz wyslac zaproszenie do gangu!");
                        return true;
                    }

                    String potencial_player = args[1];

                    for (Player dplayer : Bukkit.getOnlinePlayers())
                        if (dplayer.getName().equalsIgnoreCase(potencial_player))
                        {
                            if (GangManager.getInstance().getGang(dplayer) != null)
                            {
                                ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Ten gracz posiada juz swoj gang!");
                                VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                                VisualUtil.showPlayerParticle(player, Particle.FLAME);
                                SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                                return true;
                            }

                            if (GangManager.getInstance().getPlayerMembers(GangManager.getInstance().getGang(player)).contains(dplayer.getName()))
                            {
                                ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Ten gracz nalezy juz do Twoich szeregow w gangu!");
                                VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                                VisualUtil.showPlayerParticle(player, Particle.FLAME);
                                SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                                return true;
                            }

                            DPlayerCloudManager.setGang(player, null);
                            DPlayerCloudManager.setGang(player, dplayer);
                            ChatManager.sendMessage(player, "&c&l» &f&oWyslano zaproszenie do gangu do gracza " + ChatUtil.returnPlayerColor(dplayer) + "&o" + dplayer.getName() + "!");
                            ChatManager.sendMessage(dplayer, "&c&l» &f&oOtrzymano zaproszenie do gangu od gracza " + ChatUtil.returnPlayerColor(player) +"&o" + player.getName() + "!\n#fc7474&o/gang dolacz (gracz)&f&o - zaakceptowanie" + "\n#fc7474&o" + Configuration.SERVER_GANG_REQUEST_TIME + " sekund&f&o - czas oczekiwania");
                            VisualUtil.showDelayedTitle(dplayer, "&7od: " + player.getName(), "#85f5ff★", 0, 20, 20);

                            new BukkitRunnable() { @Override public void run() {
                                DPlayerCloudManager.setGang(player, null);
                            } }.runTaskLaterAsynchronously(Main.getInstance(), 20*Configuration.SERVER_GANG_REQUEST_TIME);
                            return true;
                        }

                    ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Podany gracz nie jest on-line na serwerze!");
                    VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                    VisualUtil.showPlayerParticle(player, Particle.FLAME);
                    SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                    return true;
                }
                else if (args[0].equalsIgnoreCase("dolacz"))
                {
                    if (args.length == 1)
                    {
                        ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cWpisz nazwe gracza, od ktorego otrzymales zaproszenie do gangu!");
                        return true;
                    }

                    String potencial_player = args[1];

                    for (Player dplayer : Bukkit.getOnlinePlayers())
                        if (dplayer.getName().equalsIgnoreCase(potencial_player))
                        {
                            if (DPlayerCloudManager.getGang(dplayer) == player)
                            {
                                ChatManager.sendMessage(dplayer, "&c&l» &f&oZaproszenie do gangu do gracza " + ChatUtil.returnPlayerColor(dplayer) + "&o" + dplayer.getName() + "&f&o zostalo zaakceptowane!");
                                ChatManager.sendMessage(player, "&c&l» &f&oDolaczyles do gangu " + GangManager.getInstance().getGang(dplayer) + "!");
                                DPlayerCloudManager.setGang(dplayer, null);
                                GangManager.getInstance().addPlayerMembers(GangManager.getInstance().getGang(dplayer), player.getName());
                                return true;
                            }

                            ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Miales gdzies dolaczyc? Moze czas minal, moze gracz wyslal juz zaproszenie komus innemu, ja nic nie wiem!");
                            VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                            VisualUtil.showPlayerParticle(player, Particle.FLAME);
                            SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                            return true;
                        }

                    ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Podany gracz nie jest on-line na serwerze!");
                    VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                    VisualUtil.showPlayerParticle(player, Particle.FLAME);
                    SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                    return true;
                }
                else if (args[0].equalsIgnoreCase("tpall"))
                {
                    if (GangManager.getInstance().getGang(player) == null)
                    {
                        ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Ziomek, Ty nawet nie masz gangu!");
                        VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                        VisualUtil.showPlayerParticle(player, Particle.FLAME);
                        SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                        return true;
                    }

                    if (!GangManager.getInstance().getLider(GangManager.getInstance().getGang(player)).equalsIgnoreCase(player.getName()))
                    {
                        ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Tylko lider moze wywolac teleportacje wszystkich czlonkow!");
                        VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                        VisualUtil.showPlayerParticle(player, Particle.FLAME);
                        SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                        return true;
                    }

                    ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cTrwa teleportacja czlonkow gangu... #fff203⌛");

                    for (String member : GangManager.getInstance().getPlayerMembers(GangManager.getInstance().getGang(player)))
                        if (Bukkit.getPlayer(member) != null && Objects.requireNonNull(Bukkit.getPlayer(member)).isOnline())
                        {
                            Player dplayer = Bukkit.getPlayer(member);
                            assert dplayer != null;
                            dplayer.teleport(player);
                        }

                    return true;
                }
                else if (args[0].equalsIgnoreCase("baza"))
                {
                    if (GangManager.getInstance().getGang(player) == null)
                    {
                        ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Ziomek, Ty nawet nie masz gangu!");
                        VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                        VisualUtil.showPlayerParticle(player, Particle.FLAME);
                        SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                        return true;
                    }

                    if (args.length == 1)
                    {
                        if (GangManager.getInstance().getBase(GangManager.getInstance().getGang(player)) == null)
                        {
                            ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Twoj gang nie ma jeszcze ustawionej bazy!");
                            VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                            VisualUtil.showPlayerParticle(player, Particle.FLAME);
                            SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                            return true;
                        }

                        ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cTrwa teleportacja do bazy gangu... #fff203⌛");
                        player.teleport(GangManager.getInstance().getBase(GangManager.getInstance().getGang(player)));
                    }
                    else
                    {
                        if (!args[1].equalsIgnoreCase("ustaw"))
                        {
                            ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Nie rozumiem ani slowa! Sprobuj uzyc #ffc936/gang baza ustaw #fc7474i bedzie dobrze!");
                            VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                            VisualUtil.showPlayerParticle(player, Particle.FLAME);
                            SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                            return true;
                        }

                        if (!GangManager.getInstance().getLider(GangManager.getInstance().getGang(player)).equalsIgnoreCase(player.getName()))
                        {
                            ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Tylko lider moze ustawic lokalizacje bazy gangu!");
                            VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                            VisualUtil.showPlayerParticle(player, Particle.FLAME);
                            SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                            return true;
                        }

                        GangManager.getInstance().setBase(GangManager.getInstance().getGang(player), player.getLocation());
                        ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#8c8c8cBaza Twojego gangu zostala zaktualizowana!");
                    }
                    return true;
                }

                ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#fc7474Co kurwa?! Wpisz #ffc936/gang #fc7474i nie pierdol!");
                VisualUtil.showDelayedTitle(player, "#fc7474✖", "", 0, 20, 20);
                VisualUtil.showPlayerParticle(player, Particle.FLAME);
                SoundUtil.playPlayerSound(player, Sound.BLOCK_ANVIL_DESTROY, 4, 4);
                return true;

            }
            return true;
        }

        return false;
    }
}