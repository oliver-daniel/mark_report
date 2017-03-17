import java.sql.*;
import java.io.*;

public class SQL {

  private static final String DB_NOT_FOUND = "XJ004";
  private static final String SHUTDOWN_OK  = "XJ015";

  public static boolean connect(){
    try{
      System.out.println("Attempting to connect...");
      DriverManager.getConnection("jdbc:derby:data");
      return true;

    }catch(SQLException e) {
      if (DB_NOT_FOUND.equals(e.getSQLState())) {
        System.out.println("Database not found. Creating database...");
        if(!create()) {
          System.out.println("Database creation failed. Aborting...");
          return false;
        }
        System.out.println("Database creation success.");
        return connect();
      }else{
        return false;
      }
    }
  }

  public static boolean create(){
    try(BufferedReader br = new BufferedReader(new FileReader("res/db_create.sql"))) {
      Connection conn = DriverManager.getConnection("jdbc:derby:data;create=true");
      StringBuilder sb=new StringBuilder();
      final String DELIMITER=" ";
      String line="";
      while ((line=br.readLine())!=null) {
        sb.append(line.trim()+DELIMITER);
      }
      String[] commands = sb.toString().split(";");
      for (String command: commands) {
        if (command.equals(DELIMITER)) break;

        conn.createStatement().execute(command);
      }
      return true;
    }
    catch(SQLException e) {
      e.printStackTrace();
      return false;
    }
    catch(IOException i) {
      i.printStackTrace();
      return false;
    }
  }

  public static boolean shutdown(){
    try{
      DriverManager.getConnection("jdbc:derby:;shutdown=true");
    } catch(SQLException e) {
      if (SHUTDOWN_OK.equals(e.getSQLState())) {
        return true;
      }else{
        e.printStackTrace();
        return false;
      }
    }
    return false;
  }
}