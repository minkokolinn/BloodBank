package controller.donation;

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

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AddDonaterController implements Initializable{
    PreparedStatement ps;
    ResultSet rs;
    Connection con= DBUtility.dbConnected();
    String genatorid;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_fathername;

    @FXML
    private TextField tf_nrc;

    @FXML
    private TextField tf_age;

    @FXML
    private TextField tf_address;

    @FXML
    private TextField tf_phone;

    @FXML
    private TextField tf_job;

    @FXML
    private DatePicker dp_dob;

    @FXML
    private ComboBox<String> cb_gender;
    ObservableList<String> gender= FXCollections.observableArrayList("Male","Female");

    @FXML
    private ComboBox<String> cb_blood;
    ObservableList<String> blood=FXCollections.observableArrayList("A","B","O","AB");

    @FXML
    private ComboBox<String> cb_region;
    ObservableList<String> region=FXCollections.observableArrayList("Yangon","Mandalay","Bago","Chin");

    @FXML
    private DatePicker dp_dsd;

    @FXML
    void btn_register(ActionEvent event) {
        inputData();
        clear();
    }

    public void clear(){
        tf_name.setText("");
        tf_fathername.setText("");
        tf_job.setText("");
        tf_phone.setText("");
        tf_address.setText("");
        tf_age.setText("");
        tf_nrc.setText("");
        dp_dsd.getEditor().setText("");
        dp_dob.getEditor().setText("");
        cb_region.getEditor().setText("");
        cb_blood.getEditor().setText("");
        cb_gender.getEditor().setText("");
    }

    public void genroll(){
        try{
            String sqlQuery="Select donaterid from donater order by donaterid DESC LIMIT 1";
            ps=(PreparedStatement)con.prepareStatement(sqlQuery);
            rs=ps.executeQuery();
            if(rs.next()){
                String rl=rs.getString("donaterid");
                int ln=rl.length();
                String stxt=rl.substring(0,2);
                String snum=rl.substring(2,ln);
                int i=Integer.parseInt(snum);
                i++;
                snum=Integer.toString(i);
                genatorid=stxt+snum;
            }else {
                genatorid="BD11111";
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void inputData(){
        try{
            genroll();
            if(isAllFillUp()){
                String sqlQuery="insert into donater(donaterid,name,fathername,nrc,age,dob,gender,address,phonenumber,bloodtype,region,donatedate,jobposition)" +
                        "values('"+genatorid+"' , '"+tf_name.getText().trim()+"' , '"+tf_fathername.getText().trim()+"' , " +
                        " '"+tf_nrc.getText().trim()+"' , '"+tf_age.getText().trim()+"' , '"+dp_dob.getValue()+"' , " +
                        " '"+cb_gender.getValue()+"' , '"+tf_address.getText().trim()+"' , '"+tf_phone.getText().trim()+"' , '"+cb_blood.getValue()+"' , " +
                        " '"+cb_region.getValue()+"' , '"+dp_dsd.getValue()+"' , '"+tf_job.getText().trim()+"')";

//                DBUtility.excutetogetStatment(sqlQuery);
                ps=con.prepareStatement(sqlQuery);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Add Donation successfully");
                System.out.println(sqlQuery);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean isAllFillUp(){
        boolean fillup=false;
        if(tf_name.getText().isEmpty()||tf_fathername.getText().isEmpty()||
                tf_nrc.getText().isEmpty()||tf_age.getText().isEmpty()||
                 dp_dob.getValue().toString().isEmpty()||cb_gender.getValue().isEmpty()||
                tf_address.getText().isEmpty()||tf_phone.getText().isEmpty()||
                cb_blood.getValue().isEmpty()||cb_region.getValue().isEmpty()||
                dp_dsd.getValue().toString().isEmpty()||tf_job.getText().isEmpty()){

            Alert al=new Alert(Alert.AlertType.ERROR,"Field shouldn't be empty!!!");
            al.showAndWait();
        }else {
            fillup=true;
        }
        return fillup;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cb_gender.setItems(gender);
        cb_blood.setItems(blood);
        cb_region.setItems(region);
    }
}
