package mc.server.survival.events;

import mc.server.survival.files.Configuration;
import mc.server.survival.managers.ATManager;
import mc.server.survival.managers.ChatManager;
import mc.server.survival.managers.DPlayerManager;
import mc.server.survival.managers.GangManager;
import mc.server.survival.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Objects;
import java.util.Random;

public class AsyncChat
implements Listener
{
	ATManager atManager = new ATManager();

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event)
	{
		event.setCancelled(true);

		if (TimeUtil.getDifferenceInSeconds(DPlayerManager.getInstance().getMute(event.getPlayer().getName())) < DPlayerManager.getInstance().getMuteLength(event.getPlayer().getName()))
		{
			ChatManager.sendMessage(event.getPlayer(), Configuration.PLUGIN_FULL_PREFIX + "#fc7474Nie mozesz pisac na czacie poniewaz jestes wyciszony, odczekaj chwile i sprobuj ponownie!");
			VisualUtil.showDelayedTitle(event.getPlayer(), " ", "#ffc936✖", 0, 10, 10);
			return;
		}

		String message = event.getMessage();
		compileMessage(ChatUtil.getPlaceholder(event.getPlayer(), ChatUtil.applyCorrection(changeLetters(event.getPlayer(), ChatUtil.addDruggedEmotes(event.getPlayer(), message)))), event.getPlayer());
	}

	public void compileMessage(String message, Player player)
	{
		boolean isGangPrefixed = message.substring(0, 1).equalsIgnoreCase("!");
		if (isGangPrefixed)
			ChatUtil.sendGangChatMessage(player, message);
		else
			Bukkit.broadcastMessage(ColorUtil.formatHEX("#80ff1f[" + TimeUtil.hour() + ":" + TimeUtil.minute() + "#80ff1f] " + ChatUtil.getGangInChat(GangManager.getInstance().getGang(player)) + ChatUtil.returnMarryPrefix(player) +  "&r" + ChatUtil.returnPlayerColor(player) + Objects.requireNonNull(player.getPlayer()).getName() + "#8c8c8c " + message));

		if (!isGangPrefixed)
			 atManager.check(player, message.toLowerCase());

		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1.0F, 8.0F);
	}

	private String changeLetters(Player player, String message)
	{
		int gaba = DPlayerManager.getInstance().getGABA(player.getName());
		if (gaba <= 0) return message;

		String alphabet = "abcdefghijklmnoprstuwyz";

		for (int slot = 0; slot < message.length(); slot++)
			if (MathUtil.chanceOf(gaba / 2) && message.charAt(slot) != ' ')
			{
				Random random = new Random();
				StringBuilder stringBuilder = new StringBuilder();

				stringBuilder.append(message);
				stringBuilder.setCharAt(slot, alphabet.charAt(random.nextInt(alphabet.length())));
				message = stringBuilder.toString();
			}

		return message;
	}
}