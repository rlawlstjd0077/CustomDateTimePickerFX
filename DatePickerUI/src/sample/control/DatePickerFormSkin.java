package sample.control;

import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SkinBase;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;


/**
 * Created by GSD on 2016-12-19.
 */
public class DatePickerFormSkin extends SkinBase<DatePickerForm>{
    private HBox hBox;
    private ComboBox comboBox;

    public DatePickerFormSkin(DatePickerForm datePickerForm) {
        super(datePickerForm);

        hBox = new HBox();
        comboBox = new ComboBox();
        comboBox.setPrefWidth(220);
        comboBox.setPrefHeight(36);
        comboBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                    comboBox.hide();
                    datePickerForm.handlePopup(comboBox);
                }
            });
        hBox.getChildren().add(comboBox);
        getChildren().add(hBox);
        datePickerForm.setComboBox(comboBox);
    }
}
