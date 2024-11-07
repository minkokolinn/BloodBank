package utility;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class utilities {
    public void SceneChangeShowAndWait(String stageTitle,String path){
        Parent root=null;
        try{
            root= FXMLLoader.load(getClass().getResource(path));
        }catch (IOException io){
            io.printStackTrace();
        }

        Stage stage=new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle(stageTitle);
        stage.showAndWait();
    }
}
