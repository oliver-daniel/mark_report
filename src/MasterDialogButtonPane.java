import javax.swing.JPanel;
import javax.swing.JButton;

public class MasterDialogButtonPane extends JPanel {
  private MasterDialog owner;

  private static final String[] BUTTONS = {
    "+",
    "-",
    "Save",
    "Cancel"
  };

  public MasterDialogButtonPane(MasterDialog owner){
    super();
    this.owner = owner;

    for (String btnText: BUTTONS) {
      JButton button = new JButton(btnText);
      button.addActionListener(owner);
      add(button);
    }

    setVisible(true);
  }
}