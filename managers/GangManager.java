package mc.server.survival.managers;

import mc.server.survival.utils.ServerUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Objects;

public class GangManager
{
    private GangManager() {}

    static GangManager instance = new GangManager();

    FileConfiguration configuration = FileManager.getInstance().configuration();

    public static GangManager getInstance()
    {
        return instance;
    }

    public void loadGang()
    {
        if (configuration.get("gangi.default") == null)
        {
            configuration.set("gangi.default.name", "DEFAU");
            configuration.set("gangi.default.members", "[default, default2, default3]");
            configuration.set("gangi.default.lider", "default");
            configuration.set("gangi.default.friendlyfire", true);
            configuration.set("gangi.default.baza", null);
            configuration.set("gangi.default.settings.color", "white");
            configuration.set("gangi.default.settings.prefixes", "normal");  //[defau] (defau) {defau} <defau>
            configuration.set("gangi.default.settings.star", false); //[* defau]
            configuration.set("gangi.default.settings.chat", false); //[* defau]
        }

        FileManager.getInstance().save();
    }

    public void createGang(Player player, String name)
    {
        ArrayList<String> members = new ArrayList<>();
        members.add(player.getName());
        name = name.toUpperCase();

        configuration.set(DPlayerManager.getDPlayer(player.getName()) + ".data.gang", name.toUpperCase());
        configuration.set("gangi." + name + ".name", name.toUpperCase());
        configuration.set("gangi." + name + ".members", members);
        configuration.set("gangi." + name + ".lider", player.getName());
        configuration.set("gangi." + name + ".friendlyfire", true);
        configuration.set("gangi." + name + ".baza", null);
        configuration.set("gangi." + name + ".settings.color", "white");
        configuration.set("gangi." + name + ".settings.prefixes", "normal");
        configuration.set("gangi." + name + ".settings.star", false);
        configuration.set("gangi." + name + ".settings.chat", false);

        FileManager.getInstance().save();
    }

    public String getGang(Player player)
    {
        return (String) configuration.get(DPlayerManager.getDPlayer(player.getName()) + ".data.gang");
    }

    public boolean isExist(String name)
    {
        return (String) configuration.get("gangi." + name.toUpperCase() + ".name") != null;
    }

    public void removeGang(String name)
    {
        if (configuration.get("gangi." + name.toUpperCase() + ".name") == null) return;

        configuration.set("gangi." + name.toUpperCase(), null);

        for (String player : DPlayerManager.getInstance().getPlayerData())
           if (configuration.get(DPlayerManager.getDPlayer(player) + ".data.gang", name.toUpperCase()) != null)
               configuration.set(DPlayerManager.getDPlayer(player) + ".data.gang", null);

        FileManager.getInstance().save();
    }

    public int getMembers(String name)
    {
        ArrayList<String> members = (ArrayList<String>) Objects.requireNonNull(configuration.get("gangi." + name.toUpperCase() + ".members"));

        return members.size();
    }

    public ArrayList<String> getPlayerMembers(String name)
    {
        return (ArrayList<String>) Objects.requireNonNull(configuration.get("gangi." + name.toUpperCase() + ".members"));
    }

    public void addPlayerMembers(String name, String adder)
    {
        configuration.set(DPlayerManager.getDPlayer(adder) + ".data.gang", name.toUpperCase());

        ArrayList<String> members = (ArrayList<String>) Objects.requireNonNull(configuration.get("gangi." + name.toUpperCase() + ".members"));
        members.add(adder);
        configuration.set("gangi." + name.toUpperCase() + ".members", members);

        FileManager.getInstance().save();
    }

    public String getLider(String name)
    {
        return configuration.get("gangi." + name.toUpperCase() + ".lider") + "";
    }

    public void setLider(Player player)
    {
        configuration.set("gangi." + getGang(player) + ".lider", player.getName());

        FileManager.getInstance().save();
    }

    public boolean getFriendlyFire(String name)
    {
        return (boolean) configuration.get("gangi." + name.toUpperCase() + ".friendlyfire");
    }

    public void toggleFriendlyFire(String name)
    {
        if (getFriendlyFire(name))
            configuration.set("gangi." + name.toUpperCase() + ".friendlyfire", false);
        else
            configuration.set("gangi." + name.toUpperCase() + ".friendlyfire", true);

        FileManager.getInstance().save();
    }

    public void setBase(String name, Location location)
    {
        configuration.set("gangi." + name.toUpperCase() + ".baza.world", location.getWorld().getName());
        configuration.set("gangi." + name.toUpperCase() + ".baza.x", location.getX());
        configuration.set("gangi." + name.toUpperCase() + ".baza.y", location.getY());
        configuration.set("gangi." + name.toUpperCase() + ".baza.z", location.getZ());
        configuration.set("gangi." + name.toUpperCase() + ".baza.yaw", ((float) location.getYaw()));
        configuration.set("gangi." + name.toUpperCase() + ".baza.pitch", ((float) location.getPitch()));
        FileManager.getInstance().save();
    }

    public Location getBase(String name)
    {
        if (configuration.get("gangi." + name.toUpperCase() + ".baza.world") == null) return null;

        String world = (String) configuration.get("gangi." + name.toUpperCase() + ".baza.world");
        double x = (double) configuration.get("gangi." + name.toUpperCase() + ".baza.x");
        double y = (double) configuration.get("gangi." + name.toUpperCase() + ".baza.y");
        double z = (double) configuration.get("gangi." + name.toUpperCase() + ".baza.z");
        float yaw = (float) Float.valueOf(String.valueOf(configuration.get("gangi." + name.toUpperCase() + ".baza.yaw")));
        float pitch = (float) Float.valueOf(String.valueOf(configuration.get("gangi." + name.toUpperCase() + ".baza.pitch")));

        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

    public void removePlayer(Player player)
    {
        if (getGang(player) == null) return;

        ArrayList<String> members = (ArrayList<String>) Objects.requireNonNull(configuration.get("gangi." + getGang(player) + ".members"));
        members.remove(player.getName());

        if (!members.isEmpty())
            configuration.set("gangi." + getGang(player) + ".members", members);

        if (getLider(getGang(player)).equalsIgnoreCase(player.getName()))
            removeGang(getGang(player));

        configuration.set(DPlayerManager.getDPlayer(player.getName()) + ".data.gang", null);

        FileManager.getInstance().save();
    }

    public String getColor(String name)
    {
        return (String) configuration.get("gangi." + name + ".settings.color");
    }

    public void setColor(String name, String color)
    {
        configuration.set("gangi." + name.toUpperCase() + ".settings.color", color);

        FileManager.getInstance().save();

        for (String playerName : getPlayerMembers(name))
            if (Bukkit.getPlayer(playerName) != null && Bukkit.getPlayer(playerName).isOnline())
            {
                Player player = Bukkit.getPlayer(playerName);
                ServerUtil.reloadContents(player);
            }
    }

    public String getPrefixes(String name)
    {
        return (String) configuration.get("gangi." + name + ".settings.prefixes");
    }

    public void setPrefixes(String name, String prefixes)
    {
        configuration.set("gangi." + name.toUpperCase() + ".settings.prefixes", prefixes);

        FileManager.getInstance().save();

        for (String playerName : getPlayerMembers(name))
            if (Bukkit.getPlayer(playerName) != null && Bukkit.getPlayer(playerName).isOnline())
            {
                Player player = Bukkit.getPlayer(playerName);
                ServerUtil.reloadContents(player);
            }
    }

    public boolean getStar(String name)
    {
        return (boolean) configuration.get("gangi." + name + ".settings.star");
    }

    public void setStar(String name, boolean star)
    {
        configuration.set("gangi." + name.toUpperCase() + ".settings.star", star);

        FileManager.getInstance().save();

        for (String playerName : getPlayerMembers(name))
            if (Bukkit.getPlayer(playerName) != null && Bukkit.getPlayer(playerName).isOnline())
            {
                Player player = Bukkit.getPlayer(playerName);
                ServerUtil.reloadContents(player);
            }
    }

    public boolean getChat(String name)
    {
        return (boolean) configuration.get("gangi." + name + ".settings.chat");
    }

    public void setChat(String name, boolean chat)
    {
        configuration.set("gangi." + name.toUpperCase() + ".settings.chat", chat);

        FileManager.getInstance().save();

        for (String playerName : getPlayerMembers(name))
            if (Bukkit.getPlayer(playerName) != null && Bukkit.getPlayer(playerName).isOnline())
            {
                Player player = Bukkit.getPlayer(playerName);
                ServerUtil.reloadContents(player);
            }
    }
}