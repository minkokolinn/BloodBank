package controller.transfer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.DBUtility;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TransferRegisterController implements Initializable{
    @FXML
    private TextField hospital_name;

    @FXML
    private TextField hospital_address;

    @FXML
    private TextField hospital_email;

    @FXML
    private TextField hospital_phone;

    @FXML
    private DatePicker hopital_register_date;

    @FXML
    private ComboBox<String> hospital_region;
    ObservableList<String> regions= FXCollections.observableArrayList("Yangon","Mandalay","NayPiTaw");

    @FXML
    private TextField patient_name;

    @FXML
    private TextField patient_nrc;

    @FXML
    private TextField patient_phone;

    @FXML
    private TextField patient_address;

    @FXML
    private DatePicker patient_register_date;

    @FXML
    private ComboBox<String> patient_gender;
    ObservableList<String> gender=FXCollections.observableArrayList("male","female");

    @FXML
    void btn_hospital_register(ActionEvent event) {
        insertHospital();
        clearHospital();
    }

    @FXML
    void btn_patient_register(ActionEvent event) {
        insertPatient();
        clearPatient();
    }

    Connection con= DBUtility.dbConnected();
    PreparedStatement ps;
    ResultSet rs;

    public void insertHospital(){
        try{
            if (isAllFillUpHospital()){
                String sqlQuery="insert into hospital(`hp_name` , `hp_address` , `hp_email` , `hp_register_date` , `hp_region` , `hp_phone`)" +
                        " values('"+hospital_name.getText().trim()+"' , '"+hospital_address.getText().trim()+"' , " +
                        " '"+hospital_email.getText().trim()+"' , '"+hopital_register_date.getValue()+"' , " +
                        " '"+hospital_region.getValue()+"' , '"+hospital_phone.getText().trim()+"')";
                ps=(PreparedStatement) con.prepareStatement(sqlQuery);
                ps.executeUpdate();
                System.out.println(sqlQuery);
                JOptionPane.showMessageDialog(null,"Hospital Database stored");
            }
        }catch (SQLException se){
            se.printStackTrace();
            System.out.println(se);
        }
    }

    public void clearHospital(){
        hospital_name.clear();
        hospital_address.clear();
        hospital_phone.clear();
        hospital_region.getEditor().clear();
        hopital_register_date.getEditor().clear();
        hospital_email.clear();
    }

    public void insertPatient(){
        try {
            if (isAllFillUpPatient()){
                String sqlQuery="insert into patient(`pa_name` , `pa_nrc` , `pa_phone` , `pa_address` , `pa_gender` , `pa_register_date`)" +
                        "values('"+patient_name.getText().trim()+"' , '"+patient_nrc.getText().trim()+"' , '"+patient_phone.getText().trim()+"' ," +
                        " '"+patient_address.getText().trim()+"' , '"+patient_gender.getValue()+"' , '"+patient_register_date.getValue()+"')";
                ps=(PreparedStatement)con.prepareStatement(sqlQuery);
                ps.executeUpdate();
                System.out.println(sqlQuery);
                JOptionPane.showMessageDialog(null,"Patient Database stored");
            }
        }catch (SQLException se){
            se.printStackTrace();
            System.out.println(se);
        }
    }

    public void clearPatient(){
        patient_name.clear();
        patient_nrc.clear();
        patient_phone.clear();
        patient_address.clear();
        patient_gender.getEditor().clear();
        patient_register_date.getEditor().clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hospital_region.setItems(regions);
        patient_gender.setItems(gender);
    }

    public boolean isAllFillUpHospital(){
        boolean b=false;
        if(!hospital_name.getText().isEmpty()||!hospital_address.getText().isEmpty()||!hospital_email.getText().isEmpty()||
                !hopital_register_date.getValue().toString().isEmpty()||!hospital_region.getValue().toString().isEmpty()||
                !hospital_phone.getText().isEmpty()){
            b=true;
        }
        return b;
    }

    public boolean isAllFillUpPatient(){
        boolean b=false;
        if(!patient_name.getText().isEmpty() || !patient_nrc.getText().isEmpty() || !patient_phone.getText().isEmpty() ||
                !patient_address.getText().isEmpty() || !patient_gender.getValue().toString().isEmpty() ||
                !patient_register_date.getValue().toString().isEmpty()){
            b=true;
        }
        return b;
    }

}
