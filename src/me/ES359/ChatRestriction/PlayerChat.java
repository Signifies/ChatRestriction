package me.ES359.ChatRestriction;

import Utilities.ChatUtils;
import me.ES359.ChatRestriction.ChatRestriction;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by ES359 on 12/16/15.
 */
public class PlayerChat extends ChatUtils implements Listener
{
    private ChatRestriction main;

    public PlayerChat(ChatRestriction instance)
    {
        this.main = instance;
    }

    //private boolean usePermission = main.getConfig().getBoolean("Chat.use-permission");

    @EventHandler
    public void chatFormat(AsyncPlayerChatEvent event)
    {
      //  boolean useChat = this.main.getConfig().getBoolean("Chat.Enabled");

           Player player  = event.getPlayer();

           String form = main.getConfig().getString("Chat.Formatting");
           form = form.replaceAll("%username%", player.getName());
           form = form.replaceAll("%message%", event.getMessage());
           form = form.replaceAll("%world%", player.getWorld().getName());
           form = form.replaceAll("%UUID%", "" + player.getUniqueId());
           form = form.replaceAll("%IP%", "" + player.getAddress());

           event.setFormat(color(form));
       }
}

