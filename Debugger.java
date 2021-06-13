package mc.server;

import mc.server.survival.managers.ChatManager;
import mc.server.survival.managers.GangManager;
import mc.server.survival.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Debugger
{
    private Debugger() {}

    static Debugger instance = new Debugger();

    public static Debugger getInstance()
    {
        return instance;
    }

    public void run()
    {
        long last = System.currentTimeMillis();

        mc.server.Debugger.debugLoadToOP("", "#8c8c8cRestartowanie...", "");
        Bukkit.reload();

        long difference = System.currentTimeMillis() - last;
        double time = Double.parseDouble(String.valueOf(difference)) / 1000;
        int ticks = Integer.parseInt(String.valueOf(difference)) / 50;

        mc.server.Debugger.debugLoadToOP("&0█████", "&0█&f█&0███   &0* #8c8c8cSerwer zostal zrestartowany! ",
        "&0██&f█&0██   &0* #8c8c8cCzas restartu wynosi " + time + " sekund!", "&0█&f█&0███   &0* #8c8c8cJest to inaczej " + ticks + " tickow!", "&0█████", "");
    }

    public static void debugLoadToOP(String... load)
    {
        for (Player operator : Bukkit.getOnlinePlayers())
            if (operator.isOp())
                ChatManager.sendMessages(operator, load);
    }
}