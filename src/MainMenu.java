import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;

public class MainMenu extends JPanel implements ActionListener{

  
  public MainMenu(){
    super();
    setBorder(BorderFactory.createRaisedBevelBorder());
   
    
    setVisible(true);
  }
  
  public void actionPerformed(ActionEvent event){
    System.out.println(event.getActionCommand());
  }
  
}