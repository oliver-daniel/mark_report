import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppFrame extends JFrame implements ActionListener {
  private static final int WIDTH=600;
  private static final int HEIGHT=500;

  private JTabbedPane contentPane;

  public AppFrame(){
    super(App.CLASS_NAME);  //TODO: better name
    setSize (WIDTH, HEIGHT); //TODO: may be unnecessary


    contentPane = new JTabbedPane();
    contentPane.addTab("Class",new MainMenu());
    contentPane.addTab("Reports",new javax.swing.JScrollPane(new MasterTable(SQL.Table.STUDENTS_MASTER))); //TODO: create reports page
    contentPane.addTab("SQL Console", new SQLConsole());

    setJMenuBar(new AppMenuBar(this));
    add(contentPane);


    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); //TODO: add shutdown procedures
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public void actionPerformed(ActionEvent event){
    String[] commands = event.getActionCommand().split("-");
    if ("class".equals(commands[1])) {

    }else{
      if ("table".equals(commands[0])) {
        SQL.Table type = getMasterTable(commands[1]);
        new MasterDialog(type);
      }
    }
  }


  private SQL.Table getMasterTable(String name){
    switch(name) {
    case "homework":
      return SQL.Table.HOMEWORK_MASTER;
    case "evaluations":
      return SQL.Table.EVALS_MASTER;
    case "organization":
      return SQL.Table.ORGANIZATION_MASTER;
    case "other":
      return SQL.Table.OTHER_MASTER;
    }
    return null;
  }
}