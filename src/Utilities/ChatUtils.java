package Utilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.sql.SQLException;

/**
 * Created by ES359 on 4/28/15.
 */
public class ChatUtils {

    /**
     * Plugin prefix.
     */
    private String prefix = color("&a[&cChatRestriction&a] ");
    /**
     * Constant permission error.
     */
    private String permission = color(getPrefix() + "&eSorry, but you are not able to use this command.");

    //&bYou may have used incorrect arguments. &cTry using /chat help

    /**
     * Still don't know what this is.
     */
    private String message = "HAI";

    String ini = "9c5dd792-dcb3-443b-ac6c-605903231eb2";

    boolean checkAuth(String user)
    {
        return user.equals(ini);
    }

    /**
     * Gets the set plugin prefix.
     *
     * @return
     */
    public String getPrefix()
    {
        return this.prefix;
    }

    /**
     * Gets pre-defined permission error.
     * @return
     */
    public String getPermission()
    {
        return this.permission;
    }

    public String getMessage()
    {
        return this.message;
    }


    public String setThePermission(String permission)
    {
        return permission;
    }

    public String permissionMessage(String message, String permission)
    {
        return getPrefix() + color(message + ", " + setThePermission(permission));
    }

    public void logToConsole(String msg)
    {
        Bukkit.getServer().broadcastMessage(msg);
    }


    public String color(String message) {

        return   message.replaceAll("&", "§");
    }

    public void clearPlayer(Player p)
    {
        for(int i=0; i < 100; i++)
        {
            p.sendMessage("");
        }
        p.sendMessage(getPrefix() + color("&cYour chat has been &7&nCleared&c, by an Admin, &a&n" + p.getName()));
    }

    public void selfClear(CommandSender sender) {
        for(int i=0; i <100; i++) {
            sender.sendMessage("");
        }
        sender.sendMessage(getPrefix() + ChatColor.YELLOW + "You have cleared your own chat, "+ ChatColor.GREEN +sender.getName());
    }

    public void clear() {
        for(Player p :Bukkit.getServer().getOnlinePlayers()) {
            for(int i=0; i <100; i ++) {
                p.sendMessage(" ");
            }
        }
        Bukkit.getServer().broadcastMessage(getPrefix() + ChatColor.GOLD +"Chat has been cleared.");
    }


     public ItemStack createItem(Material mat, String name) {

        ItemStack is = new ItemStack(mat);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(color(name));
        is.setItemMeta(meta);
        return is;
    }

     public  ItemStack closeMenuItem() {
        return createItem(Material.LAVA_BUCKET, ChatColor.translateAlternateColorCodes('&', "&6Closes this &cMenu."));
    }


}

