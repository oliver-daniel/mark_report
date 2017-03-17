import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import javax.swing.JFormattedTextField.AbstractFormatter;

public class DateLabelFormatter extends AbstractFormatter {

  private static final String DATE_PATTERN = "yyyy-MMM-dd (EEE)";
  private SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_PATTERN);

  @Override
  public Object stringToValue(String text) throws ParseException {
    return dateFormatter.parseObject(text);
  }

  @Override
  public String valueToString(Object value) throws ParseException {
    if (value != null) {
      Calendar cal = (Calendar) value;
      return dateFormatter.format(cal.getTime());
    }

    return "";
  }

}
