package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import sample.Data.DateInfo;
import sample.control.DatePickerControl;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.GregorianCalendar;

public class SearchBarControl implements Initializable {

    @FXML
    private ComboBox combo_period;
    @FXML
    private DatePickerControl startPicker;
    @FXML
    private DatePickerControl endPicker;
    @FXML
    private Button search_btn;

    private static final String DAY = "1 day";
    private static final String WEEK = "1 week";
    private static final String MONTH = "1 month";
    private static final String YEAR = "1 year";
    private String selectedValue;
    private String currentDate;
    private String startTime;
    private Date startDate;
    private SimpleDateFormat mFullDateFormat;
    private ObservableList<String> list = FXCollections.observableArrayList(DAY, WEEK, MONTH, YEAR);

    @Override
    public void initialize(URL location, ResourceBundle resources){
        mFullDateFormat = new SimpleDateFormat( "yyyy-MM-dd    HH : mm : ss", Locale.KOREA );
        Date currentTime = new Date();
        currentDate = mFullDateFormat.format( currentTime );
        combo_period.setItems(list);
    }

    public void buttonClicked(ActionEvent event){
        if(isInvalidDateInterval(startPicker.getDate(), endPicker.getDate())){
            //정상적인 처리
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Date Interval is invalid.");
            alert.showAndWait();
        }
    }

    public boolean isInvalidDateInterval(Date start , Date end){
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(start);
        cal2.setTime(end);
        return cal1.compareTo(cal2) < 0 ? true : false;
    }

    public void comboChanged(ActionEvent event){
         startDate = startPicker.getDate();
         selectedValue = combo_period.getValue().toString();
         Calendar cal = modifyDate();
         String modifiedDate = mFullDateFormat.format(cal.getTime());
         DateInfo endDateInfo = new DateInfo();
         endDateInfo.setDate(modifiedDate);
         endPicker.setComboBoxText(endDateInfo);
    }

    public Calendar modifyDate(){
        Calendar cal = new GregorianCalendar();
        cal.setTime(startDate);
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