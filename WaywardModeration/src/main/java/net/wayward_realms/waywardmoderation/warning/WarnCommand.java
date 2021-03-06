package net.wayward_realms.waywardmoderation.warning;

import net.wayward_realms.waywardlib.moderation.Warning;
import net.wayward_realms.waywardmoderation.WaywardModeration;
import net.wayward_realms.waywardmoderation.warning.WarningImpl;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarnCommand implements CommandExecutor {

    private WaywardModeration plugin;

    public WarnCommand(WaywardModeration plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("wayward.moderation.command.warn")) {
            if (sender instanceof Player) {
                if (args.length > 1) {
                    Player issuer = (Player) sender;
                    OfflinePlayer player = plugin.getServer().getOfflinePlayer(args[0]);
                    StringBuilder reason = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        reason.append(args[i]).append(' ');
                    }
                    Warning warning = new WarningImpl(reason.toString(), player, issuer);
                    plugin.addWarning(player, warning);
                    sender.sendMessage(plugin.getPrefix() + ChatColor.GREEN + "Warned " + player.getName() + ".");
                    if (player.isOnline()) {
                        player.getPlayer().sendMessage(plugin.getPrefix() + ChatColor.RED + "You have recieved a warning. (You have a total of " + plugin.getWarnings(player).size() + ")");
                        player.getPlayer().sendMessage(ChatColor.RED + warning.getReason());
                        player.getPlayer().sendMessage(ChatColor.RED + "(Issued by " + warning.getIssuer().getName() + ")");
                    }
                } else {
                    sender.sendMessage(plugin.getPrefix() + ChatColor.RED + "You must specify a player and a warning.");
                }
            } else {
                sender.sendMessage(plugin.getPrefix() + ChatColor.RED + "You must be a player to perform this command.");
            }
        } else {
            sender.sendMessage(plugin.getPrefix() + ChatColor.RED + "You do not have permission.");
        }
        return true;
    }

}
