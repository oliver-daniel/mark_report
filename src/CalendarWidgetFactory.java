import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.DateModel;
import org.jdatepicker.impl.SqlDateModel;

public class CalendarWidgetFactory extends JDateComponentFactory {

  public CalendarWidgetFactory(){
    super(SqlDateModel.class,
          new DateLabelFormatter(),
          null);
  }

}