package sample.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by GSD on 2016-12-27.
 */
public class DateInfo {
    private Date date;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd    HH : mm : ss");

    public Date getDate() {
        return date;
    }

    public void setDate(int year, int month, int day, String time) {
        try {
            this.date = dateFormat.parse(year + "-" + month + "-" + day + "    " + time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setDate(Calendar cal) {
        try {
            this.date = dateFormat.parse(cal.get(Calendar.YEAR) + "-"
                    + cal.get(Calendar.MONTH) + "-"
                    + cal.get(Calendar.DAY_OF_MONTH) + "    "
                    + cal.get(Calendar.HOUR) + " : "
                    + cal.get(Calendar.MINUTE) + " : "
                    + cal.get(Calendar.SECOND));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setDate(String date) {
        try {
            this.date = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getDateString() {
        return dateFormat.format(this.date);
    }
}

