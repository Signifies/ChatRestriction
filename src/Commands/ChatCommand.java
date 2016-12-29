package Commands;

import Utilities.ChatUtils;
import Utilities.Permissions;
import me.ES359.ChatRestriction.ChatRestriction;
import me.ES359.ChatRestriction.GUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by ES359 on 5/14/15.
 */
public class ChatCommand implements CommandExecutor
{


    ChatUtils util = new ChatUtils();
    GUI gui;
    ChatRestriction main;

    public ChatCommand(ChatRestriction instance, GUI guiInstance)
    {
        this.gui = guiInstance;
        this.main = instance;
    }


    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {
        if(cmd.getName().equalsIgnoreCase("chat") && !(sender.hasPermission(Permissions.Command_Perm)))
        {
            sender.sendMessage(util.getPermission());
        }else if(args.length < 1)
        {
            sender.sendMessage(util.color("&7=======[&eHelp&7]======="));
            sender.sendMessage(util.color("&8/chat permissions &b&l--&b&l> &aLists all permissions.\n" +
                    "&8/chat help &8&l--&b&l> &aLists configuration options/help.\n"));
        }else {
            if(args.length > 0)
            {
                switch (args[0])
                {
                    case "clear":
                    case "c":
                        if (!sender.hasPermission(Permissions.ClearChat_Perm)) sender.sendMessage(util.getPermission());
                        else
                            util.clear();
                        break;

                    case "clearself":
                    case "cs":
                        if(!sender.hasPermission(Permissions.ClearSelfChat_Perm)) sender.sendMessage(util.getPermission());
                        else
                            util.selfClear(sender);
                        break;

                    case "enable":
                    case "enabled":
                        if(!sender.hasPermission(Permissions.EnableChat_Perm)) sender.sendMessage(util.getPermission());
                            else
                            this.main.chat.setStatus(false);
                        Bukkit.getServer().broadcastMessage(util.getPrefix() + util.color("&aGlobal Chat has been enabled by, &b" + sender.getName()));
                        break;

                    case "disabled":
                    case "disable":
                        if(!sender.hasPermission(Permissions.DisableChat_Perm)) sender.sendMessage(util.getPermission());
                        else
                            this.main.chat.setStatus(true);
                            Bukkit.getServer().broadcastMessage(util.getPrefix() + util.color("&cGlobal Chat has been disabled by, &b" + sender.getName()));
                        break;

                    case "reload":
                    case "rl":
                        if(!sender.hasPermission(Permissions.ReloadChat_Perm)) sender.sendMessage(util.getPermission());
                        else
                            this.main.reloadConfig();
                            sender.sendMessage(util.getPrefix() +util.color("&cConfiguration has been reloaded, &3" + sender.getName() + "&c."));
                        break;

                    case "help":
                    case "?":
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                "&b&l████████ &6&l████████ &b&l████████\n"
                                        + "&6Plugin, " + main.pdfFile.getName() + ChatColor.GREEN + " v" + main.pdfFile.getVersion() + " &ccreated by," + main.pdfFile.getAuthors() + "\n"
                                        + "&cCommand usage: &e/chat < [enabled] || [disabled] || [clear] || [clearself || [reload] || [clearuser] <user> >\n"
                                        + "&6Permissions: &2chatrestriction.cmd &2&l|| &cchatrestriction.bypass\n"
                                        + "&cQuestions? &6Comments? &a&oBug reports?\n Use /chat-report to help out!"
                        ));
                        break;

                    case "permissions":
                        if(!sender.hasPermission(Permissions.DisplayPermissions_Perm)) sender.sendMessage(util.getPermission());
                        else
                            sender.sendMessage(util.color("&7=======[&ePermissions&7]======="));
                            sender.sendMessage(util.color("&7chatrestriction.* &b&l--&b&l> &bGrants all access to the plugin. \n" +
                                "&7chatrestriction.reload &8&l--&b&l> &bGrants permission to reload the plugin. \n" +
                                "&7chatrestriction.cmd &8&l--&b&l> &bGrants permission for base command. \n" +
                                "&7chatrestriction.enable &8&l--&b&l> &bAllows you to enable Chat.\n" +
                                "&7chatrestriction.disable &8&l--&b&l> &bAllows you to disable Chat.\n" +
                                "&7chatrestriction.bypass &8&l--&b&l> &bAllows you to bypass chat lock.\n" +
                                "&7chatrestriction.clear &8&l--&b&l> &bAllows you to clear Global chat.\n" +
                                "&7chatrestriction.clearself &b&l--&b&l> &bAllows you to clear your own chat.\n" +
                                "&7chatrestriction.permissions &8&l--&b&l> &bAllows you to see permissions.\n" +
                                "&7chatrestriction.clearothers &8&l--&b&l> &bAllows you to clear another users chat."));
                        break;

                    case "clearuser":
                    case "cu":
                            if(!sender.hasPermission(Permissions.ClearChatOthers_Perm)) sender.sendMessage(util.getPermission());
                                else if(args.length == 1)
                                    sender.sendMessage(util.getPrefix() + util.color("&6Command usage is &a&n/chat clearuser <username>"));
                            else if(args.length > 1)
                            {
                                Player target = Bukkit.getServer().getPlayer(args[1]);
                                if(target == null)
                                {
                                    sender.sendMessage(util.getPrefix() + util.color("&cWarning: &6The user, &a&o" + args[1] + " &6couldn't be found."));
                                }else {
                                    util.clearPlayer(target);
                                    sender.sendMessage(util.getPrefix() + util.color("&cYou have &6&ocleared &cthe chat for the user, &a&o" +target.getName() + "&c."));
                                }
                            }
                                break;

                    case "gui":
                       if(!sender.hasPermission(Permissions.ChatGui_Perm)) sender.sendMessage(util.getPermission());
                            else
                       if(!(sender instanceof Player))
                       {
                           sender.sendMessage(util.color("&cError.."));
                       }else if(args.length <=1)
                       {
                           Player p = (Player)sender;
                           gui.callGUI(p);
                           p.sendMessage(util.color("&8GUI &cOpened..."));
                       }
                        break;

                        default:
                             sender.sendMessage(util.color(util.getPrefix() +"&6Unknown argument, &a" + args[0] + " &6!"));
                }
            }
        }
        return true;
    }


}
