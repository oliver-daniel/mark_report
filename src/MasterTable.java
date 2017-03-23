import javax.swing.JTable;
import javax.swing.DefaultCellEditor;
import javax.swing.table.TableColumnModel;
import javax.swing.JComboBox;

import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class MasterTable extends JTable {
  public SQL.Table type;
  public MasterTable(SQL.Table tableType){
    super();
    this.type=tableType;
    ResultSet rs = SQL.getAll(tableType);
    setModel(DbUtils.resultSetToTableModel(rs));
    removeColumn(getColumn(getColumnName(0))); //remove ID
    removeColumn(getColumn(getColumnName(0))); //remove time_created
    setCellProperties(tableType);
  }

  private void setCellProperties(SQL.Table tableType){
    TableColumnModel cm = getColumnModel();

    JComboBox<Integer> range = new JComboBox<Integer>(new Integer[] {Integer.valueOf(0),Integer.valueOf(1),Integer.valueOf(2),Integer.valueOf(3),Integer.valueOf(4),Integer.valueOf(5),
                                                                     Integer.valueOf(6),Integer.valueOf(7),Integer.valueOf(8),Integer.valueOf(9),Integer.valueOf(10)});

    switch(tableType) {
    case DAYS_MASTER:
      cm.getColumn(cm.getColumnIndex("TYPE"))
      .setCellEditor(new DefaultCellEditor(new JComboBox<String>(new String[] {"Regular", "Quiz", "Test", "Assignment Due"})));
      break;
    case STUDENTS_MASTER:
      cm.getColumn(cm.getColumnIndex("ACTIVE"))
      .setCellEditor(new DefaultCellEditor(new JComboBox<String>(new String[] {"true", "false"})));
      break;
    case ATTENDANCE_LOG:
      cm.getColumn(cm.getColumnIndex("ACTIVE"))
      .setCellEditor(new DefaultCellEditor(new JComboBox<String>(new String[] {"Present","Absent","Late"})));
      break;
    case HOMEWORK_MASTER:
      JComboBox<Integer> options = new JComboBox<Integer>(new Integer[] {Integer.valueOf(5), Integer.valueOf(10), Integer.valueOf(20), Integer.valueOf(30)});
      options.setEditable(true);
      cm.getColumn(cm.getColumnIndex("OUT_OF"))
      .setCellEditor(new DefaultCellEditor(options));
      break;
    case EVALS_MASTER:
      cm.getColumn(cm.getColumnIndex("TYPE"))
      .setCellEditor(new DefaultCellEditor(new JComboBox<String>(new String[] {"Assignment","Quiz","Test"})));
      break;
    case EVALS_LOG:
      cm.getColumn(cm.getColumnIndex("STATUS"))
      .setCellEditor(new DefaultCellEditor(new JComboBox<String>(new String[] {"Submitted","Late","Not Submitted"})));
      break;
    case ORGANIZATION_MASTER:
      cm.getColumn(cm.getColumnIndex("DATES_RECORDED_SCORE_MAX"))
      .setCellEditor(new DefaultCellEditor(range));
      cm.getColumn(cm.getColumnIndex("NOTES_IN_ORDER_SCORE_MAX"))
      .setCellEditor(new DefaultCellEditor(range));
      cm.getColumn(cm.getColumnIndex("NOTES_SECURED_IN_BINDER_SCORE_MAX"))
      .setCellEditor(new DefaultCellEditor(range));
      break;
    case ORGANIZATION_LOG:
      cm.getColumn(cm.getColumnIndex("DATES_RECORDED_SCORE"))
      .setCellEditor(new DefaultCellEditor(range));
      cm.getColumn(cm.getColumnIndex("NOTES_IN_ORDER"))
      .setCellEditor(new DefaultCellEditor(range));
      cm.getColumn(cm.getColumnIndex("NOTES_SECURED_IN_BINDER"))
      .setCellEditor(new DefaultCellEditor(range));
      break;
    default:
      break;
    }
  }
}