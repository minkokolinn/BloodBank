package controller.blood;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import utility.fxmlL;


public class BloodRoomController {
    @FXML
    void bloodroom_back(ActionEvent event) {
        new fxmlL((Stage)((Node)event.getSource()).getScene().getWindow(),"/view/HomePage.fxml");
    }



}
