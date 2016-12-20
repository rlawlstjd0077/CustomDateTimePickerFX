package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import sample.control.DatePickerForm;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.GregorianCalendar;

public class Controller implements Initializable {

    @FXML
    private ComboBox combo_period;
    @FXML
    private DatePickerForm startPicker;
    @FXML
    private DatePickerForm endPicker;

    private static final String DAY = "1 day";
    private static final String WEEK = "1 week";
    private static final String MONTH = "1 month";
    private static final String YEAR = "1 year";
    private String selectedValue;
    private String currentDate;
    private String startTime;
    private String startDate;
    private SimpleDateFormat mSimpleDateFormat;
    private ObservableList<String> list = FXCollections.observableArrayList(DAY, WEEK, MONTH, YEAR);

    @Override
    public void initialize(URL location, ResourceBundle resources){
        mSimpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd", Locale.KOREA );
        Date currentTime = new Date();
        currentDate = mSimpleDateFormat.format( currentTime );
        combo_period.setItems(list);
    }


    public void comboChanged(ActionEvent event){
        if ((startDate = startPicker.getDate()) != null) {
            startTime = startPicker.getTime();
            selectedValue = combo_period.getValue().toString();
            Calendar cal = modifyDate();
            String modifiedDate = mSimpleDateFormat.format(cal.getTime());
            endPicker.setComboBoxText(modifiedDate, startTime);
        }
    }

    public String judgeOverCurrentDate(String objDate){     //check objDate is over current
        Date day1 = null;
        Date day2 = null;
        try {
            day1 = mSimpleDateFormat.parse(objDate);
            day2 = mSimpleDateFormat.parse(currentDate);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return day1.compareTo(day2) > 0 ? currentDate : objDate;
    }

    public Calendar modifyDate(){
        Date date = null;
        try {
            date = mSimpleDateFormat.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        switch (selectedValue) {
            case DAY:
                cal.add(Calendar.DATE, 1);
                break;
            case WEEK:
                cal.add(Calendar.DATE, 7);
                break;
            case MONTH:
                cal.add(Calendar.MONTH, 1);
                break;
            case YEAR:
                cal.add(Calendar.YEAR, 1);
                break;
            default:
                break;
        }
        return cal;
    }
}