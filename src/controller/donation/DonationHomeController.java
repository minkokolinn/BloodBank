package controller.donation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.Retrieve;

import java.net.URL;
import java.util.ResourceBundle;

public class DonationHomeController implements Initializable{
    Retrieve retrieve=new Retrieve();

    @FXML
    private Label registered_donater;

    @FXML
    private Label gents_donater;

    @FXML
    private Label ladies_donater;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        forNumberOfDonater();
    }

    public void forNumberOfDonater(){
        retrieve.getTotal(registered_donater,"donater");
        retrieve.getMale(gents_donater,"donater");
        retrieve.getFemale(ladies_donater,"donater");
    }
}
