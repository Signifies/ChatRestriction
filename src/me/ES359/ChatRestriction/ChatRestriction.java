package me.ES359.ChatRestriction;


import Commands.ChatCommand;
import Commands.MessageCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by ES359 on 5/14/15.
 */
public class ChatRestriction extends JavaPlugin
{

    public PluginDescriptionFile pdfFile = getDescription();
    public ChatEvents chat;
    GUI gui;
    static public boolean DEBUG;

    public void onEnable()
    {


        gui = new GUI(this,this);
        getCommand("chat").setExecutor(new ChatCommand(this,gui));
        getCommand("message").setExecutor(new MessageCommand(this));
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerChat(this), this );
        PluginManager pm = Bukkit.getServer().getPluginManager();
        chat = new ChatEvents(this);

        pm.registerEvents(this.chat, this);
        getConfig().options().copyDefaults(true);
        saveConfig();

    }
}