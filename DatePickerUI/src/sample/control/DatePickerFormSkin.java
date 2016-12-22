package sample.control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.Calendar;
import java.util.Date;


/**
 * Created by GSD on 2016-12-19.
 */
public class DatePickerFormSkin extends SkinBase<DatePickerForm>{
    private Button calButton;
    private TextField textField;
    private Pane pane;

    public DatePickerFormSkin(DatePickerForm datePickerForm) {
        super(datePickerForm);
        pane = new Pane();
        textField = new TextField();
        calButton = new Button();

        textField.setPrefWidth(220);
        textField.setPrefHeight(36);
        textField.getStyleClass().add("form-text");
        textField.setText(settingCurrentDate(datePickerForm.getCurrentDate()));

        calButton.setPrefWidth(32);
        calButton.setPrefHeight(32);
        calButton.setLayoutX(186);
        calButton.setLayoutY(2);
        calButton.getStyleClass().addAll("form-button");
        calButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                datePickerForm.handlePopup();
            }
        });

        pane.getChildren().addAll(textField, calButton);
        pane.getStyleClass().addAll("form");
        getChildren().add(pane);
        datePickerForm.setTextField(this.textField);
    }

    private String settingCurrentDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1 )+ "-" + cal.get(Calendar.DAY_OF_MONTH) + "    00 : 00 : 00";
    }
}