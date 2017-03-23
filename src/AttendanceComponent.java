import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class AttendanceComponent extends JPanel {
  private ButtonGroup group;

  private static final int HGAP = 10;

  public AttendanceComponent(Object[] student){ //probably ResultSet
    super(new BorderLayout());
    setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

    JPanel btnPane = new JPanel();
    ((FlowLayout)btnPane.getLayout()).setAlignment(FlowLayout.RIGHT);
    ((FlowLayout)btnPane.getLayout()).setHgap(HGAP);

    group = new ButtonGroup();
    String id = student[0].toString();
    String last_name = student[1].toString();
    String first_name = student[2].toString();

    JLabel label = new JLabel(id+". "+last_name+", "+first_name);
    add (label, BorderLayout.WEST);

    JRadioButton present = new JRadioButton();
    present.setActionCommand(id+"-present");
    JRadioButton absent = new JRadioButton();
    absent.setActionCommand(id+"-absent");
    JRadioButton late = new JRadioButton();
    late.setActionCommand(id+"-late");


    for (JRadioButton btn: new JRadioButton[] {present, absent, late}) {
      group.add(btn);
      btnPane.add(btn);
    }

    add(btnPane, BorderLayout.EAST);

    present.setSelected(true);

    setVisible(true);
  }

  public String getCode(){
    return group.getSelection().getActionCommand();
  }
}