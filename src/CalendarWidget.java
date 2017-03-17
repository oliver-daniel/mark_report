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
    return date;
  }

  public void stateChanged(ChangeEvent event){
    Date newDate = (Date)((SqlDateModel)event.getSource()).getValue();

    if (newDate == null)
      return;

    Calendar c = Calendar.getInstance();
    c.setTime(newDate);

    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

    if (dayOfWeek==Calendar.SATURDAY || dayOfWeek==Calendar.SUNDAY) {
      ((SqlDateModel)cal.getModel()).setValue(date);
    }else{
      date=newDate;
    }
  }
}