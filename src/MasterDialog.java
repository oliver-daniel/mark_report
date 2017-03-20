import javax.swing.JDialog;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Frame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MasterDialog extends JDialog implements ActionListener {

  private MasterTable table;

  public MasterDialog(SQL.Table tableType){
    super((Frame)null);
    setLayout(new BorderLayout());
    setLocationRelativeTo(null);
    setUndecorated(false);

    table = new MasterTable(tableType);
    add(new JScrollPane(table), BorderLayout.CENTER);
    add(new MasterDialogButtonPane(this), BorderLayout.SOUTH);
    pack();

    setVisible(true);
    requestFocus();
  }

  public void actionPerformed(ActionEvent event){
    System.out.println(event.getActionCommand());
  }
}