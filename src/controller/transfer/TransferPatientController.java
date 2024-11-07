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
import java.util.ResourceBundle;

public class TransferPatientController implements Initializable{
    Retrieve retrieve=new Retrieve();
    Connection con= DBUtility.dbConnected();
    PreparedStatement ps;


    @FXML
    private TextField patient_no_of_bags;

    @FXML
    private DatePicker patient_transfer_date;

    @FXML
    private ComboBox<String> patient_name;
    ObservableList<String> name= FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> patient_blood_type;
    ObservableList<String> bloodtype=FXCollections.observableArrayList("A","B","AB","O");

    private static String blood;
    @FXML
    void btn_submit_patient(ActionEvent event) {
        if(patient_name.getSelectionModel().getSelectedItem().isEmpty()||
                patient_blood_type.getSelectionModel().getSelectedItem().isEmpty()||
                patient_transfer_date.getValue().toString().isEmpty()||
                patient_no_of_bags.getText().isEmpty()){
            Alert al=new Alert(Alert.AlertType.ERROR,"Field shouldn't be empty");
            al.showAndWait();
        }else {
            switch (patient_blood_type.getSelectionModel().getSelectedItem().toString()){
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

        try {
            String sql="Update blood set quantity=quantity-1 where blood_type='"+blood+"'";
            ps=(PreparedStatement)con.prepareStatement(sql);
            ps.executeUpdate();
            System.out.println(sql);
            JOptionPane.showMessageDialog(null,"Database stored!!!");
        }catch (SQLException se){
            se.printStackTrace();
            System.out.println(se);
        }

        try {
            String sql="insert into patient_transfer(`pa_name`,`blood_type`,`tf_date`,`no_of_bags`)" +
                    "values('"+patient_name.getSelectionModel().getSelectedItem().toString()+"' ," +
                    " '"+patient_blood_type.getSelectionModel().getSelectedItem().toString()+"' ," +
                    " '"+patient_transfer_date.getValue().toString()+"' ," +
                    " '"+patient_no_of_bags.getText().trim()+"')";
            ps=(PreparedStatement)con.prepareStatement(sql);
            ps.executeUpdate();
            System.out.println(sql);
        }catch (SQLException se){
            se.printStackTrace();
            System.out.println(se);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        patient_blood_type.setItems(bloodtype);
        retrieve.retrieveforcombo(name,patient_name,"patient","pa_name");
    }
}
