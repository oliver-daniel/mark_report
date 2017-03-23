import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;

public class MainMenu extends JPanel implements ActionListener {

  public MainMenu(){
    super();
    setBorder(BorderFactory.createRaisedBevelBorder());

    CalendarWidget cal = new CalendarWidget();
    cal.getCal().addActionListener(this);

    JButton tblClassBtn = new JButton("Manage Class");
    tblClassBtn.addActionListener(this);
    JButton attendanceBtn= new JButton("Take Attendance");
    attendanceBtn.addActionListener(this);

    add(tblClassBtn);
    add(attendanceBtn);

    setVisible(true);
  }

  public void actionPerformed(ActionEvent event){
    //TODO: handle date change
    String ae = event.getActionCommand();
    if ("Manage Class".equals(ae)) {
      new MasterDialog(SQL.Table.STUDENTS_MASTER);
    } else if ("Take Attendance".equals(ae)) {
      new AttendanceForm();
    }
  }

}