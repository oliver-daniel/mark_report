public class App {
  public static int CLASS_ID=1; //TODO: get, etc
  public static String CLASS_NAME = "Grade 11 MaCS Chem"; //TODO: get, etc

  public static void main(String[] args) {
    if(SQL.connect()) {
      System.out.println("Database connected successfully.");
    }else{
      System.out.println("Database failed to connect.");
    }

    Runtime.getRuntime().addShutdownHook(new Thread(){
      @Override
      public void run(){
        if(SQL.shutdown()) {
          System.out.println("Database shutdown successfully.");
        }else{
          System.out.println("Database shutdown failed");
        }
      }
    });

    //begin app run
    //new AttendanceForm();
    new AppFrame();
  }
}