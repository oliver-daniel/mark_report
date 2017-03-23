import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AttendanceForm extends JDialog implements ActionListener {

  public Object[][] students={
    {"1","Daniel","Oliver"},
    {"2","Daniel","Oliver"},
    {"3","Daniel","Oliver"},
    {"4","Daniel","Oliver"},
    {"5","Daniel","Oliver"},
    {"6","Daniel","Oliver"},
    {"7","Daniel","Oliver"},
    {"8","Daniel","Oliver"},
    {"9","Daniel","Oliver"},
    {"10","Daniel","Oliver"},
    {"11","Daniel","Oliver"}

  };


  private AttendanceComponent[] components;

  private CalendarWidget cal;

  public AttendanceForm(){
    super((JFrame)null);
    setTitle(App.CLASS_NAME + " Attendance");
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());

    components = new AttendanceComponent[students.length];

    JPanel pane = new JPanel();
    pane.setLayout(new GridLayout(0,1));


    int i=0;
    for (Object[] student: students) {
      AttendanceComponent comp = new AttendanceComponent(student);
      components[i++]=comp;
      pane.add(comp);
    }

    JScrollPane scrollPane = new JScrollPane(pane);

    add(new JScrollPane(pane), BorderLayout.CENTER);


    add(new AttendanceFormHeader(), BorderLayout.NORTH);

    cal = new CalendarWidget();
    add(cal, BorderLayout.SOUTH);

    pack();


    setVisible(true);
    requestFocusInWindow();
  }

  public void actionPerformed(ActionEvent event){
    System.out.println(event.getActionCommand());
  }

  public static void main(String[] args){
    new AttendanceForm();
  }

}