package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import java.io.IOException;

/**
 * Created by GSD on 2016-12-23.
 */
public class MyComponent extends GridPane {
    public MyComponent(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/picker.fxml"));
            loader.setRoot(this);
            loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
