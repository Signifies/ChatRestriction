package me.ES359.ChatRestriction;

import Utilities.ChatUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by ES359 on 5/14/15.
 */
public class ChatEvents implements Listener
{
    ChatUtils util = new ChatUtils();
    private boolean enabled;
    private ChatRestriction main;

    public ChatEvents(ChatRestriction instance)
    {
        this.main = instance;
    }

    public void setStatus(boolean chat)
    {
        this.enabled = chat;
    }

    public boolean getStatus()
    {
        return this.enabled;
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent event)
    {
        Player p = event.getPlayer();

        String name = p.getName();

        String uuid = "" + p.getUniqueId();
        if (getStatus())
        {
            if (p.hasPermission("chatcontrol.bypass"))
            {
                event.setCancelled(false);
            }
            else
            {
                String access = ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("chat-denied"));
                event.setCancelled(true);
                access = access.replaceAll("%player%", p.getName());
                p.sendMessage(this.util.getPrefix() + access);
            }
        }
        else {
            event.setCancelled(false);
        }
    }
}
