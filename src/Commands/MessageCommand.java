package Commands;

import Utilities.ChatUtils;
import Utilities.Permissions;
import me.ES359.ChatRestriction.ChatRestriction;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.Conversation;
import org.bukkit.entity.Player;

/**
 * Created by ES359 on 12/16/15.
 */
public class MessageCommand extends ChatUtils implements CommandExecutor
{
    private ChatRestriction main;
    public MessageCommand(ChatRestriction instance)
    {
        this.main = instance;
    }


    public boolean onCommand(CommandSender console, Command cmd, String commandLabel, String args[])
    {

        if(!(console instanceof Player))
        {
            return true;
        }

        Player sender = (Player)console;

        if(cmd.getName().equalsIgnoreCase("message"))
        {
            if(!sender.hasPermission(Permissions.Message_CMD_perm))
            {
                sender.sendMessage( color("&6Error: &c You don't have permission."));
            }else if(args.length < 1)
            {
                sender.sendMessage(color("&a&o/message &7<&ausername&7> <&amessage&7>"));
            }else if(args.length > 0)
            {
                Player p = Bukkit.getPlayer(args[0]);



                if(p == null)
                {
                    sender.sendMessage( color("&6Error: &cIs the user &7"+args[0] +" &conline?"));
                }else if(args.length > 1 )
                {
                    StringBuilder str = new StringBuilder();

                    for(int j = 0; j <args.length; j++) {
                        str.append(args[j] + " ");
                    }

                    String msg = str.toString().replace(args[0], "");
                    String message = main.getConfig().getString("Message.Format");
                    message = message.replace("%prefix%",main.getConfig().getString("Message.Prefix"));
                    message = message.replace("%recip%", p.getName());
                    message = message.replace("%sender%",sender.getName());
                    message = message.replace("%message%", msg);
                    message = message.replace("%world%", p.getWorld().getName());
                    message = message.replace("%sender_world%", sender.getWorld().getName());
                    message = message.replace("%UUID%", "" + p.getUniqueId());
                    message = message.replace("%IP%", "" + p.getAddress());
                    msg = msg.replace("&", "§");
                    p.sendMessage(color(message));
                    sender.sendMessage(color(message));
                }
            }
        }

        return true;
    }

}
