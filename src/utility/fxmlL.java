package utility;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class fxmlL {
    public fxmlL(Stage stage,String filelocation){
        try {
            Parent root= FXMLLoader.load(getClass().getResource(filelocation));
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
