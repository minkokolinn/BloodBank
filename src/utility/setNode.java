package utility;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class setNode {
    public setNode(StackPane root,String filepath){
        Node node;
        root.getChildren().clear();
        try{
            node= FXMLLoader.load(getClass().getResource(filepath));
            root.getChildren().add(node);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
