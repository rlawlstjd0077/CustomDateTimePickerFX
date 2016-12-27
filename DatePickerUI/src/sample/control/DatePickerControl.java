package sample.control;

import com.sun.javafx.event.RedirectedEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Window;
import sample.Data.DateInfo;

import java.util.Date;

import static com.sun.javaws.ui.SplashScreen.hide;

/**
 * Created by GSD on 2016-12-19.
 */
public class DatePickerControl extends Control{
    private static final String DEFAULT_STYLE_CLASS = "date-picker-form";
    private Popup popup;
    private CalendarControl calendarControl;
    private TextField textField;
    private DateInfo dateInfo;
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
            popup.setAutoHide(true);
        }
    }
    public void setComboBoxText(DateInfo dateInfo){
        this.dateInfo = dateInfo;
        this.textField.setText(dateInfo.getDateString());
    }

    public Date getDate(){
        this.dateInfo.setDate(textField.getText());
        return this.dateInfo.getDate();
    }
    public void hidePopup(){
        popup.hide();
    }
    public void initTextField(TextField textField){
        this.textField = textField;
    }
    public void setTimeInterval(int minute){
        this.calendarControl.setTimeIntervalMin(minute);
    }
    public void setDateInfo(DateInfo date) {
        this.dateInfo = date;
    }
}