import javax.swing.JPanel;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;

public class MainMenu extends JPanel implements ActionListener {

  public MainMenu(){
    super();
    setBorder(BorderFactory.createRaisedBevelBorder());

    add(new CalendarWidget());

    setVisible(true);
  }

  public void actionPerformed(ActionEvent event){
  }

}