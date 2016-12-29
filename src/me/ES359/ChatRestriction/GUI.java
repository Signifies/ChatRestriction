package me.ES359.ChatRestriction;

import Utilities.ChatUtils;
import Utilities.Debug;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

/**
 * Created by ES359 on 8/23/15.
 */
public class GUI extends ChatUtils implements Listener
{
    private Inventory chatGui;
    ChatRestriction main;
     ItemStack clearChat,DisableChat,EnableChat,ClearSelf;

    public void callGUI(Player p)
    {
        p.openInventory(chatGui);
    }

    public GUI(ChatRestriction instance,Plugin plugin)
    {
        main = instance;
        Bukkit.getServer().getPluginManager().registerEvents(this,plugin);

        chatGui = Bukkit.getServer().createInventory(null, 18, color("&6&lChat &a&lGUI:"));

        clearChat = createItem(Material.BOOKSHELF,"&8&lClears the &cChat.");
        DisableChat = createItem(Material.BEDROCK, "&cDisable the &6Chat.");
        EnableChat = createItem(Material.BOOK, "&aEnable &cthe &6Chat.");
        ClearSelf = createItem(Material.BEACON, "&6&lClear your own &cChat.");
        chatGui.setItem(0,clearChat);
        chatGui.setItem(3,DisableChat);
        chatGui.setItem(5,EnableChat);
        chatGui.setItem(7,ClearSelf);
        chatGui.setItem(17,closeMenuItem());

    }


    @EventHandler
    public void click(InventoryClickEvent event)
    {


        try
        {

            Player p = (Player)event.getWhoClicked();
            if(event.getCurrentItem().equals(ClearSelf))
            {
                if(event.isRightClick() || event.isLeftClick() || event.isShiftClick())
                {
                    event.setCancelled(true);
                    clearPlayer(p);
                    p.closeInventory();
                }
            }

            if(event.getCurrentItem().equals(clearChat))
            {
                if(event.isRightClick() || event.isLeftClick() || event.isShiftClick())
                {
                    event.setCancelled(true);
                    clear();
                    p.closeInventory();
                }
            }

            if(event.getCurrentItem().equals(closeMenuItem()))
            {
                if(event.isRightClick() || event.isLeftClick() || event.isShiftClick())
                {
                    event.setCancelled(true);
                    p.closeInventory();
                }
            }

            if(event.getCurrentItem().equals(DisableChat))
            {
                if(event.isRightClick() || event.isLeftClick() || event.isShiftClick())
                {
                    event.setCancelled(true);
                    this.main.chat.setStatus(true);
                    Bukkit.getServer().broadcastMessage(color("&cGlobal Chat has been disabled by, &b" + p.getName()));
                    p.closeInventory();
                }
            }

            if(event.getCurrentItem().equals(EnableChat))
            {
                if(event.isRightClick() || event.isLeftClick() || event.isShiftClick())
                {
                    event.setCancelled(true);
                    this.main.chat.setStatus(false);
                    Bukkit.getServer().broadcastMessage(color("&aGlobal Chat has been enabled by, &b" + p.getName()));
                    p.closeInventory();
                }
            }
        }catch (Exception e)
        {
            if(ChatRestriction.DEBUG)
            {
                e.printStackTrace();
            }else
            {
//                logToConsole(color("&cError occurred."));
            }
        }
    }

}
