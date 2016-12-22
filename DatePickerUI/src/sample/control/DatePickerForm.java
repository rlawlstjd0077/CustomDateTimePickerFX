package sample.control;

import javafx.beans.property.StringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Window;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by GSD on 2016-12-19.
 */
public class DatePickerForm extends Control{
    private static final String DEFAULT_STYLE_CLASS = "date-picker-form";
    private final Popup popup;
    private final DateChooser dateChooser;
    private TextField textField;
    private StringProperty text;
    private Date currentDate;

    public DatePickerForm(){
        getStyleClass().setAll(DEFAULT_STYLE_CLASS);
        popup = new Popup();
        currentDate = new Date(System.currentTimeMillis());
        dateChooser = new DateChooser(this);
        dateChooser.getStylesheets().add("sample/css/chooser.css");
    }

    @Override
    public String getUserAgentStylesheet() {
        return "sample/css/form.css";
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
            popup.getContent().addAll(dateChooser);
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
    public Date getCurrentDate(){
        return this.currentDate;
    }
    public void setTimeInterval(int minute){
        this.dateChooser.setTimeInterval(minute);
    }
}