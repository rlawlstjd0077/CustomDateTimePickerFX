package sample.control;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Window;

/**
 * Created by GSD on 2016-12-19.
 */
public class DatePickerForm extends Control{
    private static final String DEFAULT_STYLE_CLASS = "date-picker-form";
    final Popup popup;
    final DateChooser dateChooser;
    final StackPane pane;
    private ComboBox comboBox;
    private String date;
    private String time;

    public DatePickerForm(){
        getStyleClass().setAll(DEFAULT_STYLE_CLASS);
        popup = new Popup();
        dateChooser = new DateChooser(this);
        pane = new StackPane();
        pane.getChildren().add(dateChooser);
    }

    @Override
    public String getUserAgentStylesheet() {
        return "sample/css/form.css";
    }
    public void handlePopup(ComboBox comboBox) {
        this.comboBox = comboBox;


        if (popup.isShowing()) {
            popup.hide();
        } else {
            final Window window = comboBox.getScene().getWindow();
            popup.setAutoHide(true);
            popup.setAutoFix(true);
            popup.setHideOnEscape(true);
            popup.setWidth(100);
            popup.setHeight(300);

            final double x = window.getX()
                    + comboBox.localToScene(0, 0).getX()
                    + comboBox.getScene().getX()
                    - 50;
            final double y = window.getY()
                    + comboBox.localToScene(0, 0).getY()
                    + comboBox.getScene().getY()
                    + comboBox.getHeight();

            popup.getContent().clear();
            popup.getContent().addAll(pane);

            popup.show(this.getParent(), x, y);
        }
    }
    public void setComboBoxText(String date, String time){
        this.comboBox.setPromptText(date + "     " +  time);
    }
    public void setComboBoxText(int year, int month, int day, String time){
        this.date = year + "-" + String.format("%02d", month + 1 )+ "-" + String.format("%02d", day);
        this.time = time;
        this.comboBox.setPromptText( this.date  +  "     " + this.time);
        popup.hide();
    }
    public String getDate(){
        return this.date;
    }
    public String getTime(){
        return this.time;
    }
    public void setComboBox(ComboBox comboBox){
        this.comboBox = comboBox;
    }
//    public void hidePopup(){
//        popipContainer.hide();
//    }
}
