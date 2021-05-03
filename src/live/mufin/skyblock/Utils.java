package live.mufin.skyblock;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public class Utils {

	private Main plugin;

	public Utils(Main plugin) {
		this.plugin = plugin;
	}
	
	
	
	public int booleanToInt(boolean value) {
		if(value) return 1;
		else return 0;
	}
	public boolean intToBoolean(int value) {
		if(value == 0) return false;
		else return true;
	}
	

	// Sending Messages
	public void sendFormattedMessage(Player player, String message) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&aSB&8]&r " + message));
		return;
	}

	public String formatSeconds(long timeInSeconds) {
		long secondsLeft = timeInSeconds % 3600 % 60;
		int minutes = (int) Math.floor(timeInSeconds % 3600 / 60);
		int hours = (int) Math.floor(timeInSeconds / 3600);

		String HH = ((hours < 10) ? "0" : "") + hours;
		String MM = ((minutes < 10) ? "0" : "") + minutes;
		String SS = ((secondsLeft < 10) ? "0" : "") + secondsLeft;

		return HH + ":" + MM + ":" + SS;
	}

	// Sending logger message (needed for command)
	public void sendLoggerMessage(Player player) {
		List<String> features = new ArrayList<String>();
		features.add("itemdrops");
		features.add("itemclicks");

		plugin.utils.sendFormattedMessage(player, "&7Showing logger options: ");
		plugin.utils.sendFormattedMessage(player, "&8================================");

		NamespacedKey itemDropsKey = new NamespacedKey(plugin, "logging_itemdrops");
		NamespacedKey itemClicksKey = new NamespacedKey(plugin, "logging_itemclicks");

		if (this.intToBoolean(player.getPersistentDataContainer().get(itemClicksKey, PersistentDataType.INTEGER))) {
			TextComponent msg = new TextComponent(TextComponent
					.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&8[&aEnabled&8] &7- &aitemclicks")));
			msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + "setlogger itemclicks false"));
			msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new Text(ChatColor.translateAlternateColorCodes('&', "&7Click to &cdisable &7this feature!"))));
			player.spigot().sendMessage(msg);
		} else {
			TextComponent msg = new TextComponent(TextComponent
					.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&8[&cDisabled&8] &7- &aitemclicks")));
			msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + "setlogger itemclicks true"));
			msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new Text(ChatColor.translateAlternateColorCodes('&', "&7Click to &aenable &7this feature!"))));
			player.spigot().sendMessage(msg);
		}
		
		if (this.intToBoolean(player.getPersistentDataContainer().get(itemDropsKey, PersistentDataType.INTEGER))) {
			TextComponent msg = new TextComponent(TextComponent
					.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&8[&aEnabled&8] &7- &aitemdrops")));
			msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + "setlogger itemdrops false"));
			msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new Text(ChatColor.translateAlternateColorCodes('&', "&7Click to &cdisable &7this feature!"))));
			player.spigot().sendMessage(msg);
		} else {
			TextComponent msg = new TextComponent(TextComponent
					.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&8[&cDisabled&8] &7- &aitemdrops")));
			msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + "setlogger itemdrops true"));
			msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new Text(ChatColor.translateAlternateColorCodes('&', "&7Click to &aenable &7this feature!"))));
			player.spigot().sendMessage(msg);
		}

	}

}
