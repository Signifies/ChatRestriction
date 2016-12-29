package Utilities;

import me.ES359.ChatRestriction.ChatRestriction;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Debug
{
  public static String color(String message)
  {
    return message.replaceAll("&", "��");
  }
  
  public static void log(String msg, boolean useSystemPrint)
  {
    if (ChatRestriction.DEBUG) {
      if (useSystemPrint) {
        System.out.println(color(msg));
      } else {
        Bukkit.getServer().getConsoleSender().sendMessage(color(msg));
      }
    }
  }
  
  public static void log(String message)
  {
    if (ChatRestriction.DEBUG) {
      Bukkit.getServer().getConsoleSender().sendMessage(Flag.LOG + color(message));
    }
  }
  
  public static void log(Player p, String message)
  {
    if (ChatRestriction.DEBUG) {
      p.sendMessage(message);
    }
  }
}
