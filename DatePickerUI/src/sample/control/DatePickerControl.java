package sample.control;

import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Window;

import java.util.Date;

/**
 * Created by GSD on 2016-12-19.
 */
public class DatePickerControl extends Control{
    private static final String DEFAULT_STYLE_CLASS = "date-picker-form";
    private Popup popup;
    private CalendarControl calendarControl;
    private TextField textField;
    private Date currentDate;


    public DatePickerControl(){
//        if (Platform.isFxApplicationThread()) {
//
//        } else {
//            // Intended for SceneBuilder
//            Platform.runLater(this::init);
//        }
        init();
    }
    public void init(){
        getStyleClass().setAll(DEFAULT_STYLE_CLASS);
        popup = new Popup();
        currentDate = new Date(System.currentTimeMillis());
        calendarControl = new CalendarControl(this);
        calendarControl.getStylesheets().add("sample/css/chooser.css");
    }

    @Override
    public String getUserAgentStylesheet() {
        return "sample/css/date_picker.css";
    }
    public void handlePopup() {
        if (popup.isShowing()) {
            popup.hide();
        } else {

            final Window window = textField.getScene().getWindow();
            popup.setAutoHide(true);
            popup.setAutoFix(true);
            popup.setHideOnEscape(true);
            popup.setWidth(100);
            popup.setHeight(300);

            final double x = window.getX()
                    + textField.localToScene(0, 0).getX()
                    + textField.getScene().getX()
                    - 50;
            final double y = window.getY()
                    + textField.localToScene(0, 0).getY()
                    + textField.getScene().getY()
                    + textField.getHeight();

            popup.getContent().clear();
            popup.getContent().addAll(calendarControl);
            popup.show(this.getParent(), x, y);
        }
    }
    public void setComboBoxText(String date, String time){
        this.textField.setText(date + "     " +  time);
    }
    public void setComboBoxText(int year, int month, int day, String time){
        this.textField.setText( year + "-" + String.format("%02d", month + 1 )+ "-" + String.format("%02d", day)
                +  "    " + time);
        popup.hide();
    }
    public String getTime(){
        return this.textField.getText().split("    ")[1];
    }
    public void hidePopup(){
        popup.hide();
    }
    public void setTextField(TextField textField){
        this.textField = textField;
    }
    public String getDate(){
        return this.textField.getText().split("    ")[0];
    }
    public void setTimeInterval(int minute){
        this.calendarControl.setTimeIntervalMin(minute);
    }
}