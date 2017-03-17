import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppFrame extends JFrame implements ActionListener{
  private static final int WIDTH=600;
  private static final int HEIGHT=500;
  
  private JTabbedPane contentPane;
  
  public AppFrame(){
    super ("Classic Devine Markbook"); //TODO: better name
    setSize (WIDTH, HEIGHT); //TODO: may be unnecessary
    
    contentPane = new JTabbedPane();
    contentPane.addTab("[class_name]",new MainMenu()); //TODO: get class name
    contentPane.addTab("Reports", new MainMenu()); //TODO: create reports
    
    setJMenuBar(new AppMenuBar(this));
    add(contentPane);
    

    
    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); //TODO: add shutdown procedures
    setLocationRelativeTo(null);
    setVisible(true);
  }
  
  public void actionPerformed(ActionEvent ae){
    System.out.println(ae.getActionCommand());
  }

}