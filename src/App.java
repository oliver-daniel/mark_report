public class App {
  public static void main(String[] args) {
    if(SQL.connect()) {
      System.out.println("Database connected successfully.");
    }else{
      System.out.println("Database failed to connect.");
    }

    if(SQL.shutdown()) {
      System.out.println("Database shutdown successfully.");
    }else{
      System.out.println("Database shutdown failed");
    }
  }
}