package sample.control;

import javafx.scene.control.Control;
import sample.Data.DateInfo;

import java.util.Date;

/**
 * Created by GSD on 2016-12-15.
 */
public class CalendarControl extends Control{
    private static final String DEFAULT_STYLE_CLASS = "calendar-form";
    private Date currentDate;
    private DateInfo dateInfo;
    /**
     * 시간 선택 콤보 박스의 시간 간격 설정 변수
     */
    private int intervalMin;
    private DatePickerControl datePickerControl;

    public CalendarControl(Date parent){
        getStyleClass().setAll(DEFAULT_STYLE_CLASS);
        this.currentDate = parent;
        this.intervalMin = 30;
    }

    public CalendarControl(DatePickerControl datePickerControl){
        this(new Date(System.currentTimeMillis()));
        this.datePickerControl = datePickerControl;
    }
    public CalendarControl(){
        this(new Date(System.currentTimeMillis()));
    }

    @Override
    public String getUserAgentStylesheet() {
        return "sample/css/calender.css";
    }

    public Date getCurrentDate(){
        return currentDate;
    }
    public void setTimeIntervalMin(int minute){
        this.intervalMin = minute;
    }
    public int getTimeIntervalMin(){
        return this.intervalMin;
    }
    public void onSelectDate(DateInfo dateInfo){
        this.dateInfo = dateInfo;
        datePickerControl.setComboBoxText(dateInfo);
        closeCalendar();
    }
    public void closeCalendar(){
        this.datePickerControl.hidePopup();
    }
}