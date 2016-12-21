package sample.control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javax.xml.soap.Text;


/**
 * Created by GSD on 2016-12-19.
 */
public class DatePickerFormSkin extends SkinBase<DatePickerForm>{
    private Button calButton;
    private TextField textField;
    private Pane pane;
    private HBox hBox;


    public DatePickerFormSkin(DatePickerForm datePickerForm) {
        super(datePickerForm);
        pane = new Pane();
        hBox = new HBox();
        textField = new TextField();
        calButton = new Button();

        textField.setPrefWidth(220);
        textField.setPrefHeight(36);
        textField.getStyleClass().add("form-text");

        calButton.setPrefWidth(32);
        calButton.setPrefHeight(32);
        calButton.setLayoutX(186);
        calButton.setLayoutY(2);
        calButton.getStyleClass().addAll("form-button");
        calButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                datePickerForm.handlePopup(textField);
            }
        });


        pane.getChildren().addAll(textField, calButton);
        hBox.getChildren().add(pane);
        hBox.getStyleClass().addAll("form");
        getChildren().add(hBox);
        datePickerForm.setTextField(this.textField);

    }
    public String getString(){
        return textField.getPromptText().substring(13);
    }
}