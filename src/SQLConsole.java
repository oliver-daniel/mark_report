import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import net.proteanit.sql.DbUtils;

public class SQLConsole extends JPanel implements ActionListener {

  private JTextArea text;
  private JTable table;
  private JScrollPane scroll;
  private JButton btn;


  public SQLConsole(){
    super();
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    text = new JTextArea("SELECT * FROM students_master WHERE CLASS_ID = "+App.CLASS_ID,5,0);
    text.setLineWrap(true);
    text.setWrapStyleWord(true); //TODO: implement undo+redo using UndoManager

    JButton btn = new JButton("Go");
    btn.setAlignmentX(Component.CENTER_ALIGNMENT);
    btn.addActionListener(this);

    table = new JTable();
    scroll = new JScrollPane(table);

    add(text);
    add(btn);
    add(scroll);

    setVisible(true);
  }

  private void updateTable(String command){
    try{
      ResultSet rs = SQL.execute(command);
      table.setModel(DbUtils.resultSetToTableModel(rs));
    }catch(SQLException e) {
      JTextArea errorText = new JTextArea(e.getMessage(),20,20);
      errorText.setEditable(false);
      errorText.setLineWrap(true);
      errorText.setWrapStyleWord(true);
      JOptionPane.showMessageDialog(null,new JScrollPane(errorText), "SQL Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  public void actionPerformed(ActionEvent event){
    if ("Go".equals(event.getActionCommand())) {
      updateTable(text.getText());
    }
  }
}