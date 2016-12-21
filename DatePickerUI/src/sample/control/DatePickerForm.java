package sample.control;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Window;


/**
 * Created by GSD on 2016-12-19.
 */
public class DatePickerForm extends Control{
    private static final String DEFAULT_STYLE_CLASS = "date-picker-form";
    private final Popup popup;
    private final DateChooser dateChooser;
    private final StackPane pane;
    private TextField textField;
    private String date;
    private String time;
    private DatePickerFormSkin skin;

    public DatePickerForm(){
        getStyleClass().setAll(DEFAULT_STYLE_CLASS);
        skin = new DatePickerFormSkin(this);
        popup = new Popup();
        dateChooser = new DateChooser(this);
        dateChooser.getStylesheets().add("sample/css/chooser.css");
        pane = new StackPane();
        pane.getChildren().add(dateChooser);
    }


    @Override
    public String getUserAgentStylesheet() {
        return "sample/css/form.css";
    }
    public void handlePopup(TextField textField) {
        this.textField = textField;


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
            popup.getContent().addAll(pane);

            popup.show(this.getParent(), x, y);
        }
    }
    public void setComboBoxText(String date, String time){
        this.textField.setText(date + "     " +  time);
    }
    public void setComboBoxText(int year, int month, int day, String time){
        this.date = year + "-" + String.format("%02d", month + 1 )+ "-" + String.format("%02d", day);
        this.time = time;
        this.textField.setText( this.date  +  "     " + this.time);
        popup.hide();
    }
    public String getDate(){
        return this.date;
    }
    public String getTime(){
        return this.time;
    }
    public void setComboBox(ComboBox comboBox){
        this.textField = textField;
    }
    public void hidePopup(){
        popup.hide();
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setTextField(TextField textField){
        this.textField = textField;
    }
    public String getString(){
        return this.textField.getText().substring(0,10);
    }
}
