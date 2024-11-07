package controller.record;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import utility.setNode;

import java.net.URL;
import java.util.ResourceBundle;

public class RecordHomeController implements Initializable{

    @FXML
    private StackPane hospital;

    @FXML
    private StackPane patient;

    @FXML
    private StackPane blood_donate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new setNode(hospital,"/view/record/Record_Hospital.fxml");
        new setNode(patient,"/view/record/Record_Patient.fxml");
        new setNode(blood_donate,"/view/record/RecordBloodDonate.fxml");
    }
}
