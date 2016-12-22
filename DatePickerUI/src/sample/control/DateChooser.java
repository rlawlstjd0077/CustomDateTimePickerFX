package sample.control;

import javafx.scene.control.Control;

import java.util.Date;

/**
 * Created by GSD on 2016-12-15.
 */
public class DateChooser extends Control{
    private static final String DEFAULT_STYLE_CLASS = "date-chooser";
    private Date date;
    private int interval;
    private DatePickerForm datePickerForm;

    public DateChooser(Date parent){
        getStyleClass().setAll(DEFAULT_STYLE_CLASS);
        this.date = parent;
        this.interval = 30;
    }

    public DateChooser(DatePickerForm datePickerForm){
        this(new Date(System.currentTimeMillis()));
        this.datePickerForm = datePickerForm;
    }

    @Override
    public String getUserAgentStylesheet() {
        return "sample/css/calender.css";
    }

    public Date getDate(){
        return date;
    }
    public void setTimeInterval(int minute){
        this.interval = minute;
    }
    public int getTimeInterval(){
        return this.interval;
    }
    public void onChooseDate(int year, int month, int day, String time){
        this.datePickerForm.setComboBoxText(year, month, day, time);
    }
    public void closeChooser(){
        this.datePickerForm.hidePopup();
    }
}