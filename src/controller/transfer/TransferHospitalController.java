package controller.transfer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.DBUtility;
import models.Retrieve;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class TransferHospitalController implements Initializable{
    Retrieve retrieve=new Retrieve();
    Connection con= DBUtility.dbConnected();
    PreparedStatement ps;

    @FXML
    private TextField hosptial_blood_bag;

    @FXML
    private DatePicker hospital_transfer_date;

    @FXML
    private ComboBox<String> hospital_name;
    ObservableList<String> name= FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> hospital_blood_type;
    ObservableList<String> bloodtype=FXCollections.observableArrayList("A","B","AB","O");

    private static String blood;
    @FXML
    void btn_submit_hospital(ActionEvent event) {
        if(hospital_name.getSelectionModel().getSelectedItem().isEmpty()||
                hospital_blood_type.getSelectionModel().getSelectedItem().isEmpty()||
                hospital_transfer_date.getValue().toString().isEmpty()||
                hosptial_blood_bag.getText().isEmpty()){
            Alert a=new Alert(Alert.AlertType.ERROR,"Field shouldn't be empty!!!");
            a.showAndWait();
        }else {
            switch (hospital_blood_type.getSelectionModel().getSelectedItem().toString()){
                case "A":
                    blood="A";
                    break;
                case "B":
                    blood="B";
                    break;
                case "AB":
                    blood="AB";
                    break;
                case "O":
                    blood="O";
                    break;
            }
        }
        try{
            String sqlQuery="Update blood set quantity=quantity-1 where blood_type='"+blood+"'";
            ps=(PreparedStatement)con.prepareStatement(sqlQuery);
            ps.executeUpdate();
            System.out.println(sqlQuery);
            JOptionPane.showMessageDialog(null,"Database stored");
        }catch (SQLException se){
            se.printStackTrace();
            System.out.println(se);
        }

        try {
            String sqlQuery="insert into `hospital_transfer`(`hp_name` , `blood_type` , `tf_date` , `no_of_bags`)" +
                    " values('"+hospital_name.getSelectionModel().getSelectedItem().toString()+"' ," +
                    " '"+hospital_blood_type.getSelectionModel().getSelectedItem().toString()+"' ," +
                    " '"+hospital_transfer_date.getValue().toString()+"' , '"+hosptial_blood_bag.getText().trim()+"')";
            ps=(PreparedStatement)con.prepareStatement(sqlQuery);
            ps.executeUpdate();
            System.out.println(sqlQuery);
        }catch (SQLException se){
            se.printStackTrace();
            System.out.println(se);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hospital_blood_type.setItems(bloodtype);
        retrieve.retrieveforcombo(name,hospital_name,"hospital","hp_name");
    }
}
