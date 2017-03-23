import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;


public class AttendanceFormHeader extends JPanel {
  private static final int HGAP = 10;
  private static final int VGAP = 5;
  public AttendanceFormHeader(){
    super(new BorderLayout());

    setBorder(BorderFactory.createEmptyBorder(VGAP, HGAP, VGAP, HGAP));

    add(new JLabel("Name"), BorderLayout.WEST);
    add(new JLabel("Present  Absent  Late"), BorderLayout.EAST);
  }
}