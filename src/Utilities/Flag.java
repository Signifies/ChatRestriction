package Utilities;

public class Flag
{
  public static String flag;
  
  public static void setCustomFlag(String prefix)
  {
    flag = prefix;
  }
  
  public static String getFlag()
  {
    return flag;
  }
  
  public static String FAILED_ACTION = "[FAILED ACTION] ";
  public static String ACTION = "[ACTION] ";
  public static String LOG = "[LOG] ";
  public static String SEVERE = "[SEVERE] &c";
}
