package mc.server.survival.managers;

import mc.server.Broadcaster;
import mc.server.survival.files.Configuration;
import mc.server.survival.files.Main;
import mc.server.survival.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ATManager
{
    /*
        Main AT prefix, whose displays on the chat.
     */

    public static final String prefix = "&eANTI-TOXIC &f";

    /*
        AT settings.
     */

    public static double AT_WORD_HEURISTIC_TRESHOLD = 0.8;
    public static boolean AT_ALWAYS_LISTENER = false;
    public static int AT_MAX_WARNS = 5;

    /*
        These letters will be removed from the message sended by a player.
        This is super usefull to detect advanved word re-translation.
     */

    private static final String[] uselessChars = {" ", ".", ",", "/", ":", ";", "'", "|", "~", "=", "+", "-", "!", "_", "?", "@", "#",
                                                  "$", "%", "^", "&", "*", "(", ")", "{", "}", "[", "]", "1", "2", "3", "4", "5", "6",
                                                  "7", "8", "9", "0"};

    /*
        Function that changes unknown letters to alphanumerical ones.
    */

    private static final HashMap<String, String> unknownChar = new HashMap<String, String>();
    private static final ArrayList<String> unknownCharsList = new ArrayList<>();
    private static final ArrayList<String> knownCharsList = new ArrayList<>();
    private static final String[] unknownChars = {"ą", "ć", "ę", "ł", "ń", "ó", "ś", "€", "ź", "ż"};
    private static final String[] knownChars = {"a", "c", "e", "l", "n", "o", "s", "u", "z", "z"};

    private static void collectUnknownChars()
    {
        Collections.addAll(unknownCharsList, unknownChars);
        Collections.addAll(knownCharsList, knownChars);
        int currentChar = 0;

        for (String unknown : unknownChars)
        {
            unknownChar.put(unknownCharsList.get(currentChar), knownCharsList.get(currentChar));
            currentChar = currentChar + 1;
        }
    }

    public static HashMap<Player, Integer> at_warns = new HashMap<Player, Integer>();
    public static HashMap<Player, Boolean> at_listener = new HashMap<Player, Boolean>();

    public static int getWarns(Player player) { return at_warns.get(player); }
    public static void setWarns(Player player, int warns) { at_warns.put(player, warns); }
    public static boolean checkWarns(Player player) { return at_warns.get(player) == null; }
    public static boolean getListener(Player player) { return at_listener.get(player); }
    public static void setListener(Player player, boolean listener) { at_listener.put(player, listener); }
    public static boolean checkListener(Player player) { return at_listener.get(player) == null; }

    /*
        Main listener for all messages on the chat.
     */

    public void check(Player player, String message)
    {
        // Lower-casing for optimal results.

        message = message.toLowerCase();

        /*
            AT listener for server operators.
         */

        if (!checkListener(player) && getListener(player))
        {
            boolean b = message.substring(message.length() - 1).equalsIgnoreCase(".") || message.substring(message.length() - 1).equalsIgnoreCase("?");

            if (message.contains("ile to jest "))
            {
                if (b)
                    message = message.substring(0, message.length() - 1);

                say(player, "Proste, ze " + MathUtil.eval(player, message.substring(12)), false);
                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.contains("ile to ") || message.contains("policz ") || message.contains("oblicz "))
            {
                if (b)
                    message = message.substring(0, message.length() - 1);

                say(player, "Proste, ze " + MathUtil.eval(player, message.substring(7)), false);
                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.contains("co to jest "))
            {
                if (b)
                    message = message.substring(0, message.length() - 1);

                say(player, getDefinitionOf(message.substring(11)), false);
                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.contains("co to "))
            {
                if (b)
                    message = message.substring(0, message.length() - 1);

                say(player, getDefinitionOf(message.substring(6)), false);
                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.contains("tak czy nie") || message.contains("tak lub nie"))
            {
                if (MathUtil.chanceOf(50))
                    say(player, "Powiedzmy, ze tak.", false);
                else
                    say(player, "Powiedzmy, ze nie.", false);

                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.contains("ktora jest godzina") || message.contains("ktora na osi"))
            {
                say(player, "Aktualnie " + TimeUtil.hour() + ":" + TimeUtil.minute() + ".", false);
                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.contains("dziekuje") || message.contains("dzieki")
                    || message.contains("dzieks") || message.contains("thx")
                    || message.contains("dzk") || message.contains("thanks")
                    || message.contains("thank you") || message.contains("thank u")
                    || message.contains("jestes najlepszy") || message.contains("kocham cie")
                    || message.contains("kc"))
            {
                say(player, "Zawsze do twoich uslug.", false);
                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.contains("podaj losowa liczbe") || message.contains("podaj losowa cyfre"))
            {
                say(player, "Niech bedzie " + new Random().nextInt(100) + ".", false);
                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.contains("sprawdz premke dla "))
            {
                if (b)
                    message = message.substring(0, message.length() - 1);

                if (ServerUtil.getPremiumState(message.substring(19)))
                {
                    say(player, "Oczywiscie, ze ma, na bogato!", false);
                    if (!AT_ALWAYS_LISTENER) setListener(player, false);
                    return;
                }

                say(player, "Nie ma, bieda w kraju.", false);
                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.contains("gdzie jest "))
            {
                if (b)
                    message = message.substring(0, message.length() - 1);

                if (Bukkit.getPlayer(message.substring(11)) != null && Bukkit.getPlayer(message.substring(11)).isOnline())
                {
                    Player request = Bukkit.getPlayer(message.substring(11));

                    assert request != null;
                    say(player, request.getName() + " znajduje sie na swiecie " + request.getWorld().getName().toLowerCase() + ", na kordach x: " + request.getLocation().getBlockX() + ", y: " + request.getLocation().getBlockY() + ", z: " + request.getLocation().getBlockZ() + ".", false);
                    if (!AT_ALWAYS_LISTENER) setListener(player, false);
                    return;
                }

                say(player, "Nie wiem, na pewno nie na serwerze.", false);
                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.contains("wybierz mi kogos") || message.contains("wybierz kogos") || message.contains("wybierz losowego gracza") || message.contains("wybierz gracza"))
            {
                ArrayList<String> allPlayers = new ArrayList<>();
                Player onlinePlayer = null;

                for (Player onlinePlayers : Bukkit.getOnlinePlayers())
                {
                    allPlayers.add(onlinePlayers.getName());
                    int randomSelect = new Random().nextInt(allPlayers.size());
                    onlinePlayer = Bukkit.getPlayer(allPlayers.get(randomSelect));
                }

                assert onlinePlayer != null;
                say(player, "Wybieram " + onlinePlayer.getName() + "!", false);
                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.contains("jaka jest pogoda w "))
            {
                if (b)
                    message = message.substring(0, message.length() - 1);

                say(player, getTodayWeatherIn(message.substring(19)), false);
                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.contains("jaka bedzie jutro pogoda w "))
            {
                if (b)
                    message = message.substring(0, message.length() - 1);

                say(player, getTommorowWeatherIn(message.substring(27)), false);
                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.contains("otworz nether") || message.contains("odblokuj nether") || message.contains("wlacz nether"))
            {
                if (!Configuration.SERVER_BLOCK_NETHER)
                    say(player, "Wrota do netheru sa juz otwarte!", false);
                else
                {
                    Configuration.SERVER_BLOCK_NETHER = false;
                    say(player, "Wrota do netheru zostaly otwarte!", false);
                }

                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.contains("zamknij nether") || message.contains("zablokuj nether") || message.contains("wylacz nether"))
            {
                if (Configuration.SERVER_BLOCK_NETHER)
                    say(player, "Wrota do netheru sa juz zamkniete!", false);
                else
                {
                    Configuration.SERVER_BLOCK_NETHER = true;
                    say(player, "Wrota do netheru zostaly zamkniete!", false);
                }

                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.contains("otworz end") || message.contains("odblokuj end") || message.contains("wlacz end"))
            {
                if (!Configuration.SERVER_BLOCK_THE_END)
                    say(player, "Wrota do endu sa juz otwarte!", false);
                else
                {
                    Configuration.SERVER_BLOCK_THE_END = false;
                    say(player, "Wrota do endu zostaly otwarte!", false);
                }

                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.contains("zamknij end") || message.contains("zablokuj end") || message.contains("wylacz end"))
            {
                if (Configuration.SERVER_BLOCK_THE_END)
                    say(player, "Wrota do endu sa juz zamkniete!", false);
                else
                {
                    Configuration.SERVER_BLOCK_THE_END = true;
                    say(player, "Wrota do endu zostaly zamkniete!", false);
                }

                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.contains("odblokuj niszczenie") || message.contains("wlacz niszczenie") || message.contains("wylacz blokade niszczenie"))
            {
                if (!Configuration.SERVER_TERRAIN_PROTECTION)
                    say(player, "Blokowanie modyfikacji terenu jest juz wylaczone!", false);
                else
                {
                    Configuration.SERVER_TERRAIN_PROTECTION = false;
                    say(player, "Blokowanie modyfikacji terenu zostalo wylaczone!", false);
                }

                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.contains("zablokuj niszczenie") || message.contains("wylacz niszczenie") || message.contains("wlacz blokade niszczenie"))
            {
                if (Configuration.SERVER_TERRAIN_PROTECTION)
                    say(player, "Blokowanie modyfikacji terenu jest juz wlaczone!", false);
                else
                {
                    Configuration.SERVER_TERRAIN_PROTECTION = true;
                    say(player, "Blokowanie modyfikacji terenu zostalo wlaczone!", false);
                }

                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.contains("wlacz timber"))
            {
                if (Configuration.SERVER_FUNCTION_TIMBER)
                    say(player, "Modyfikacja Timber jest juz wlaczona!", false);
                else
                {
                    Configuration.SERVER_FUNCTION_TIMBER = true;
                    say(player, "Modyfikacja Timber zostala wlaczona!", false);
                }

                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.contains("wylacz timber"))
            {
                if (!Configuration.SERVER_FUNCTION_TIMBER)
                    say(player, "Modyfikacja Timber jest juz wylaczona!", false);
                else
                {
                    Configuration.SERVER_FUNCTION_TIMBER = false;
                    say(player, "Modyfikacja Timber zostala wylaczona!", false);
                }

                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.contains("wlacz oreminer") || message.contains("wlacz ore miner"))
            {
                if (Configuration.SERVER_FUNCTION_OREMINER)
                    say(player, "Modyfikacja OreMiner jest juz wlaczona!", false);
                else
                {
                    Configuration.SERVER_FUNCTION_OREMINER = true;
                    say(player, "Modyfikacja OreMiner zostala wlaczona!", false);
                }

                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.contains("wylacz oreminer") || message.contains("wylacz ore miner"))
            {
                if (!Configuration.SERVER_FUNCTION_OREMINER)
                    say(player, "Modyfikacja OreMiner jest juz wylaczona!", false);
                else
                {
                    Configuration.SERVER_FUNCTION_OREMINER = false;
                    say(player, "Modyfikacja OreMiner zostala wylaczona!", false);
                }

                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.contains("nie sluchaj juz mnie") || message.contains("nie sluchaj mnie juz") || message.contains("nie sluchaj mnie"))
            {
                if (!AT_ALWAYS_LISTENER)
                    say(player, "No przeciez cie nie slucham.", false);
                else
                {
                    AT_ALWAYS_LISTENER = false;
                    say(player, "Od teraz pukaj lub uprzedz mnie jak bedziesz cos chcial!", false);
                }

                setListener(player, false);
                return;
            }

            if (message.contains("sluchaj mnie"))
            {
                if (AT_ALWAYS_LISTENER)
                    say(player, "No przeciez cie slucham.", false);
                else
                {
                    AT_ALWAYS_LISTENER = true;
                    say(player, "Od teraz mow do mnie bez pytania, jak bracia!", false);
                }

                setListener(player, true);
                return;
            }

            if (message.contains("wylacz sie") || message.contains("mozesz odejsc") || message.contains("to na tyle") || message.contains("zamknij sie"))
            {
                setListener(player, false);
                return;
            }

            if (message.contains("zmien limit ostrzezen na") || message.contains("ustaw limit ostrzezen na"))
            {
                if (b)
                    message = message.substring(0, message.length() - 1);

                if (!MathUtil.isInteger(message.substring(25)))
                    say(player, "Wpisz cyfre a nie jakies chuj wie co!", false);
                else
                {
                    AT_MAX_WARNS = Integer.parseInt(message.substring(25));
                    say(player, "Zmieniono limit ostrzezen na " + message.substring(25) + "!", false);
                }

                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.contains("kim jestes") || message.contains("kto ty") || message.contains("jak masz na imie") || message.contains("skad jestes"))
            {
                say(player, "Jestem zaawansowanym pomocnikiem serwera oraz moderatorem czatu.", false);
                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.contains("ile masz lat") || message.contains("jaka jest twoja wersja") || message.contains("jaka masz wersje") || message.contains("jaka masz aktualnie wersje"))
            {
                say(player, "Moja wersja to BETA-1.1", false);
                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.contains("daj mi "))
            {
                if (b)
                    message = message.substring(0, message.length() - 1);

                String itemName = message.substring(7, 8).toUpperCase() + message.substring(8);

                try
                {
                    Class<?> clazz = Class.forName("mc.server.survival.items." + itemName);
                    Method method = clazz.getMethod("getItem");
                    method.setAccessible(true);
                    Object collectedMethod = method.invoke(clazz);
                    ItemStack collectedItem = (ItemStack) collectedMethod;
                    player.getInventory().addItem(collectedItem);
                }
                catch (ClassNotFoundException | NoSuchMethodException e)
                {
                    e.printStackTrace();
                    say(player, "Nie przypominam sobie aby istnial taki przedmiot jak " + itemName + ".",false);
                }
                catch (IllegalAccessException | InvocationTargetException e)
                {
                    e.printStackTrace();
                }

                if (!AT_ALWAYS_LISTENER) setListener(player, false);
                return;
            }

            if (message.length() > 7)
                if (message.substring(0, 4).equalsIgnoreCase("czy "))
                {
                    ArrayList<String> strings = new ArrayList<>();
                    strings.add("Tak.");
                    strings.add("Nie.");
                    strings.add("Jak najbardziej.");
                    strings.add("Nawet nie pytaj...");
                    strings.add("Moze moze...");
                    strings.add("Nie wiem.");
                    say(player, strings, false);
                    if (!AT_ALWAYS_LISTENER) setListener(player, false);
                    return;
                }

            say(player, "Nie rozumiem.", false);
            if (!AT_ALWAYS_LISTENER) setListener(player, false);
            return;
        }

        /*
            Detector for toxic words on the chat.
         */

        collectUnknownChars();
        for (String unknown : unknownCharsList)
            message = message.replace(unknown, unknownChar.get(unknown));

        for (String uselessChar : uselessChars)
            message = message.replace(uselessChar, "");

        ArrayList<Integer> letters = new ArrayList<>();

        if (message.length() >= 3)
            for (int letter = 1; letter <= message.length(); letter++)
                if (letter > 1 && letter != message.length())
                    if (message.substring(letter - 1, letter).equalsIgnoreCase(message.substring(letter, letter + 1)))
                        letters.add(letter);

        if (letters.size() > 0)
            for (int slot : letters)
            {
                StringBuilder stringBuilder = new StringBuilder();

                stringBuilder.append(message);
                stringBuilder.setCharAt(slot, '<');
                message = stringBuilder.toString();
            }

        message = message.replace("<", "");
        message = message.replace(">", "");

        if (message.equalsIgnoreCase("L"))
        {
            new BukkitRunnable()
            {
                @Override
                public void run()
                {
                    Bukkit.broadcastMessage(ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.getGangInChat(GangManager.getInstance().getGang(player)) + ChatUtil.returnMarryPrefix(player) +  "&r" + ChatUtil.returnPlayerColor(player) + Objects.requireNonNull(player.getPlayer()).getName() + "#8c8c8c Nie no ja jestem wiekszym gownem."));
                    manageWarns(player);
                }
            }.runTaskLater(Main.getInstance(), 60);
        }
        else if (message.equalsIgnoreCase("ez") || message.equalsIgnoreCase("es") || message.equalsIgnoreCase("esz"))
            say(player, player.getName() + " jebal rudy pies!", true);
        else if (message.equalsIgnoreCase("latwo") || message.equalsIgnoreCase("latfo"))
            say(player, player.getName() + ", jednak nadal nie latwiej niz z twoja matka!", true);
        else if (message.equalsIgnoreCase("aha"))
            say(player, player.getName() + ", jakis problem?", false);
        else if (message.equalsIgnoreCase("co"))
        {
            ArrayList<String> strings = new ArrayList<>();
            strings.add("Gowno.");
            strings.add("Chujow sto.");
            strings.add("Jajco.");
            strings.add("Pstro.");
            say(player, strings, false);
        }
        else if (message.equalsIgnoreCase("no"))
            say(player, "Nu.", false);
        else if (message.equalsIgnoreCase("nu"))
            say(player, player.getName() + ", wez ty zamknij ryj!", false);
        else if (message.equalsIgnoreCase("ta") || message.equalsIgnoreCase("tia"))
            say(player, "Tak*", false);
        else if (message.equalsIgnoreCase("smacznego"))
            say(player, "Nie udlaw sie!", false);
        else if (message.equalsIgnoreCase("ej"))
            say(player, "Kup se klej!", false);
        else if (message.equalsIgnoreCase("aw") || message.equalsIgnoreCase("uwu") || message.equalsIgnoreCase("owo"))
            say(player, "Zaraz sie porzygam...", false);

        if (message.contains("niegram") || message.contains("niepogram") || message.contains("niezagram"))
            say(player, player.getName() + ", kto pytal?", false);
        if (message.contains("niemoge") || message.contains("nimoge"))
            say(player, player.getName() + ", mozesz tylko ci sie nie chce leniu jebany!", false);
        else if (stringContain(message, "antitoxic", "antytoxic"))
            if (DPlayerManager.getInstance().getRank(player).equalsIgnoreCase("administrator"))
            {
                ArrayList<String> strings = new ArrayList<>();
                strings.add(player.getName() + ", w czym moge ci pomoc?");
                strings.add(player.getName() + ", slucham.");
                strings.add(player.getName() + ", tak?");
                strings.add(player.getName() + ", do uslug.");
                strings.add(player.getName() + ", tak panie?");
                say(player, strings, false);
                setListener(player, true);
            }
            else
                say(player, player.getName() + ", odpierdol sie ode mnie!", false);
        else if (stringContain(message, "kurwa", "kurwo", "kurwy", "kurwe"))
            say(player, player.getName() + ", kurwa to twoja matka!", true);
        else if (stringContain(message, "spierdala"))
             say(player, player.getName() + ", spierdalac to zaraz ty bedziesz z tego serwera!", true);
        else if (stringContain(message, "wypierdala"))
            say(player, player.getName() + ", wypierdalac to zaraz ty bedziesz z tego serwera!", true);
        else if (stringContain(message, "wykurwia"))
            say(player, player.getName() + ", wykurwiac to zaraz ty bedziesz z tego serwera!", true);
        else if (stringContain(message, "jebac", "jebie"))
            say(player, player.getName() + ", uwazaj na slowa!", true);
        else if (stringContain(message, "japierdole", "jprdl"))
            say(player, player.getName() + ", ja ciebie tez!", true);
        else if (stringContain(message, "smiec", "cwel", "jebne", "pojeb", "jebany"))
             say(player, player.getName() + ", tak samo jechala po mnie twoja matka kiedy ja posuwalem!", true);
        else if (stringContain(message, "pedal", "penis", "pizda", "pizdo", "pizdy") || message.contains("huj") || message.contains("pedal"))
             say(player, player.getName() + ", mowisz o sobie?", true);
        else if (stringContain(message, "szmata", "szmato", "szmate", "szmaty"))
            say(player, player.getName() + ", takiej szmaty do podlogi jak ty nie ma lepszej na tym swiecie!", true);
        else if (stringContain(message, "dziwka", "dziwko", "dziwke", "dziwki"))
            say(player, player.getName() + ", lepszej dziwki niz ty nie ma!", true);
        else if (stringContain(message, "wypchaj"))
            say(player, player.getName() + ", wypchac to ja moge twoje usta!", true);
        else if (message.contains("haha") || message.contains("hahs") || message.contains("hsha") || message.contains("hshs"))
        {
            ArrayList<String> strings = new ArrayList<>();
            strings.add("XD");
            strings.add("XDD");
            strings.add("Beka w chuj.");
            strings.add("Nawet smiechlem.");
            strings.add(player.getName() + ", co cie tak smieszy co?");
            strings.add(player.getName() + " nikogo to nie bawi.");
            strings.add(player.getName() + ", ja rozumiem ze smutek pogarsza urode, ale to nie dziala w druga strone!");
            say(player, strings, false);
        }
    }

    /*
        Warnings system for punishing toxic players.
        You can get AT warnings when you're too toxic or you're flooding or spamming.
        AT will mute you for 5 minutes by the default.
     */

    private static void manageWarns(Player player)
    {
        if (checkWarns(player))
            setWarns(player, 0);

        int warns = getWarns(player);
        setWarns(player, warns + 1);

        if (warns >= AT_MAX_WARNS)
        {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String date = dateTimeFormatter.format(now);
            DPlayerManager.getInstance().setMute(player, date);
            DPlayerManager.getInstance().setMuteLength(player, 60*5);

            Broadcaster.broadcastMessages("       ", "#ffc936██&f█#ffc936██", "#ffc936██&f█#ffc936██                     &f&l<#ffc936&l!&f&l> #ffc936&lMUTE &f&l<#ffc936&l!&f&l>",
                    "#ffc936██&f█#ffc936██  #8c8c8cGracz #ffc936" + player.getName() + " #8c8c8czamknal morde na okres #ffc9365m#8c8c8c!", "#ffc936█████         #f8ff26Wymiar pokuty nalozyl ANTI-TOXIC!", "#ffc936██&f█#ffc936██", "");
            ChatManager.sendMessage(player, Configuration.PLUGIN_FULL_PREFIX + "#ffc936Zostales wyciszony na czacie za: ANTI-TOXIC#ffc936!");
            VisualUtil.showDelayedTitle(player, " ", "#ffc936✖", 0, 10, 10);
            QuestUtil.manageQuest(player, 9);
            setWarns(player, 0);

            new BukkitRunnable()
            {
                @Override
                public void run()
                {
                    Bukkit.broadcastMessage(ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + prefix + "Ez?"));
                }
            }.runTaskLater(Main.getInstance(), 20);
        }
    }

    public static void say(Player player, String message, boolean warn)
    {
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                Bukkit.broadcastMessage(ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + prefix + message));
                if (warn) manageWarns(player);
            }
        }.runTaskLater(Main.getInstance(), 20);
    }

    public static void say(Player player, ArrayList<String> message, boolean warn)
    {
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                Bukkit.broadcastMessage(ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + prefix + ChatUtil.randomString(message)));
                if (warn) manageWarns(player);
            }
        }.runTaskLater(Main.getInstance(), 20);
    }

    public static String getTodayWeatherIn(String city)
    {
        try
        {
            URL url = new URL("https://www.meteoprog.pl/en/weather/" + city + "/");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            StringBuilder result = new StringBuilder();

            while ((line = reader.readLine()) != null)
                result.append(line);

            String h = result.toString();

            for (int x = 0; x < h.length(); x++)
                if (h.substring(x, x + 2).equalsIgnoreCase("°C"))
                    return "Obecna temperatura wynosi " + h.substring(x - 3, x) + "°C.";

            return "Gdzie kurwa?!";
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return "Gdzie kurwa?!";
    }

    public static String getTommorowWeatherIn(String city)
    {
        try
        {
            URL url = new URL("https://www.meteoprog.pl/en/weather/" + city + "/");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            StringBuilder result = new StringBuilder();

            while ((line = reader.readLine()) != null)
                result.append(line);

            StringBuilder weather = new StringBuilder("Temperatura jutro wynosic bedzie ");
            String h = result.toString();
            int var = 0;

            for (int x = 0; x < h.length(); x++)
                if (h.substring(x, x + 2).equalsIgnoreCase("°C"))
                {
                    var = var + 1;

                    if (var == 22)
                        weather.append(h.substring(x - 3, x)).append("°C rankiem i ");

                    if (var == 23)
                        weather.append(h.substring(x - 3, x)).append("°C po polodniu.");

                    if (var >= 24)
                        return weather.toString();
                }

            return "Gdzie kurwa?!";
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return "Gdzie kurwa?!";
    }

    public static String getDefinitionOf(String word)
    {
        try
        {
            URL url = new URL("https://sjp.pwn.pl/szukaj/" + word + ".html");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            StringBuilder result = new StringBuilder();

            while ((line = reader.readLine()) != null)
                result.append(line);

            String h = result.toString();

            for (int x = 0; x < h.length(); x++)
                if (h.substring(x, x + 1).equalsIgnoreCase("«"))
                    for (int z = x; z < h.length(); z++)
                        if (h.substring(z, z + 1).equalsIgnoreCase("»"))
                            return "Jest to " + h.substring(x + 1, z) + ".";
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return "Nie znam takiego slowa.";
    }

    /*
     * Calculates the similarity (a number within 0 and 1) between two strings.
     */

    public static double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length())
        { // longer should always have greater length
            longer = s2; shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) { return 1.0; /* both strings are zero length */ }
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;

    }

    // Example implementation of the Levenshtein Edit Distance
    public static int editDistance(String s1, String s2)
    {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++)
        {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++)
            {
                if (i == 0)
                    costs[j] = j;
                else
                    {
                    if (j > 0)
                    {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
    }

    public static boolean stringContain(String string, String... words)
    {
        if (string.length() <= 4) return false;

        for (int x = 0; x <= string.length(); x++)
        {
            for (int z = 1; z <= string.length(); z++)
            {
                if (!(z <= x))
                {
                    for (String word : words)
                    {
                        if (similarity(string.substring(x, z), word) >= AT_WORD_HEURISTIC_TRESHOLD)
                            return true;
                    }
                }
            }
        }

        return false;
    }
}