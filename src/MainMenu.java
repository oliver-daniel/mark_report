import javax.swing.JPanel;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;

public class MainMenu extends JPanel implements ActionListener {

  public MainMenu(){
    super();
    setBorder(BorderFactory.createRaisedBevelBorder());

    CalendarWidget cal = new CalendarWidget();
    cal.getCal().addActionListener(this);

    add(cal);

    setVisible(true);
  }

  public void actionPerformed(ActionEvent event){
    //TODO: handle date change
    System.out.println(event.getActionCommand());
  }

}