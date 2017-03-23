import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.SqlDateModel;
import java.sql.Date;

import java.util.Calendar;


public class CalendarWidget extends JPanel implements ChangeListener {

  private static CalendarWidgetFactory factory = new CalendarWidgetFactory();
  private Date date;
  private JDatePicker cal;

  public CalendarWidget(){
    super();

    cal = factory.createJDatePicker();
    cal.getModel().addChangeListener(this);
    date = (Date)cal.getModel().getValue();

    add((JComponent)cal);

  }

  public Date getDate(){
    return (Date)date.clone();
  }

  private void setDate(Date newDate){
    ((SqlDateModel)cal.getModel()).setValue(date);
  }

  public JDatePicker getCal(){
    return cal;
  }

  public void stateChanged(ChangeEvent event){
    Date newDate = (Date)((SqlDateModel)event.getSource()).getValue();

    if (newDate == null) {
      setDate(date);
      return;
    }

    Calendar c = Calendar.getInstance();
    c.setTime(newDate);

    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

    if (dayOfWeek==Calendar.SATURDAY || dayOfWeek==Calendar.SUNDAY) {
      setDate(date);
    }else{
      date=newDate;
    }
  }
}