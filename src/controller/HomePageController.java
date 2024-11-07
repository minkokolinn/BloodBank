package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import utility.fxmlL;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable{

    private String filelocation;
    @FXML
    private AnchorPane anchor;

    @FXML
    private AnchorPane anchor2;

    @FXML
    void btn_logout(ActionEvent event) {

    }
    @FXML
    void btn_exit(ActionEvent event) {
        Alert al=new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("Exit");
        al.setContentText("do u want to exit ?");
        al.showAndWait().ifPresent(rs->{
            if(rs== ButtonType.OK){
                Platform.exit();
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Node node:anchor2.getChildren()){
            HBox hb=(HBox)node;
            for(Node node1:hb.getChildren()){
                if(node1.getAccessibleText()!=null){
                    node1.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{
                        switch (node1.getAccessibleText()){
                            case "blood":
                                filelocation="/view/blood/BloodRoom.fxml";
                                break;
                            case "donation":
                                filelocation="/view/donation/DonationHomePage.fxml";
                                break;
                            case "transfer":
                                filelocation="/view/transfer/TransferHome.fxml";
                                break;
                            case "records":
                                filelocation="/view/record/RecordHome.fxml";
                                break;
                            case "admin":
                                filelocation="/view/admin/AdminHome.fxml";
                                break;
                        }
                        new fxmlL((Stage)((Node)e.getSource()).getScene().getWindow(),filelocation);
                    });
                }
            }
        }
    }
}
