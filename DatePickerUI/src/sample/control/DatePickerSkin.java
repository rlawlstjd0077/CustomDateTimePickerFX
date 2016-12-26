package sample.control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.Calendar;


/**
 * Created by GSD on 2016-12-19.
 */
public class DatePickerSkin extends SkinBase<DatePickerControl>{
    private Button calendarButton;
    private TextField textField;
    private Pane pane;

    public DatePickerSkin(){
        this(new DatePickerControl());
    }

    public DatePickerSkin(DatePickerControl datePickerControl) {
        super(datePickerControl);
        pane = new Pane();
        textField = new TextField();
        calendarButton = new Button();

        textField.setPrefWidth(220);
        textField.setPrefHeight(36);
        textField.getStyleClass().add("form-text");
        textField.setText(settingCurrentDate());

        calendarButton.setPrefWidth(32);
        calendarButton.setPrefHeight(32);
        calendarButton.setLayoutX(186);
        calendarButton.setLayoutY(2);
        calendarButton.getStyleClass().addAll("form-button");
        calendarButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                datePickerControl.handlePopup();
            }
        });

        pane.getChildren().addAll(textField, calendarButton);
        pane.getStyleClass().addAll("form");
        getChildren().add(pane);
        datePickerControl.setTextField(this.textField);
    }

    private String settingCurrentDate() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1 )+ "-" + cal.get(Calendar.DAY_OF_MONTH) + "    00 : 00 : 00";
    }
}