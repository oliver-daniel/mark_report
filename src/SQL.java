import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

public class SQL {
  
  private static final String DB_NOT_FOUND = "XJ004";
  private static final String SHUTDOWN_OK  = "XJ015";
  
  public static enum Table {
    CLASSES_MASTER,
      DAYS_MASTER,
      STUDENTS_MASTER,
      ATTENDANCE_LOG,
      HOMEWORK_MASTER,
      HOMEWORK_LOG,
      EVALS_MASTER,
      EVALS_LOG,
      ORGANIZATION_MASTER,
      ORGANIZATION_LOG,
      OTHER_MASTER,
      OTHER_LOG
  };
  
  private static Connection conn;
  
  public static boolean connect(){
    try{
      System.out.println("Attempting to connect...");
      conn = DriverManager.getConnection("jdbc:derby:data");
      conn.setAutoCommit(false);
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
    StringBuilder sb=new StringBuilder();
    final String DELIMITER=" ";
    String line="";
    try(Connection createConn = DriverManager.getConnection("jdbc:derby:data;create=true");
        Statement stmt = createConn.createStatement();
        BufferedReader br = new BufferedReader(new FileReader("res/db_create.sql"))) {
          
          while ((line=br.readLine())!=null) {
            sb.append(line.trim()+DELIMITER);
          }
          String[] commands = sb.toString().split(";");
          for (String command: commands) {
            if (command.equals(DELIMITER)) break;
            
            stmt.execute(command);
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
  
  public static ResultSet execute(String command){
    try{
      return conn.createStatement().executeQuery(command);
    }catch(SQLException e){
      e.printStackTrace();
    }
    return null;
  }
  
  public static void printResult(ResultSet rs){
    if (rs == null){
      return;
    }
    try{
      while(rs.next()){
        System.out.println(rs.getObject(1));
      } 
      rs.getStatement().close();
      rs.close();
    }catch(SQLException e){
      e.printStackTrace();
    }
  }
  
  public static boolean shutdown(){
    try{
      conn.close();
      DriverManager.getConnection("jdbc:derby:;shutdown=true");
    }catch(SQLException e){
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