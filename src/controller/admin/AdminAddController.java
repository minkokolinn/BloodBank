package controller.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import models.DBUtility;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminAddController implements Initializable{
    @FXML
    private Button register_id;

    @FXML
    private TextField tf_name;

    @FXML
    private DatePicker tf_dob;

    @FXML
    private TextField tf_phone;

    @FXML
    private PasswordField tf_password;

    @FXML
    private TextField tf_address;

    @FXML
    private ComboBox<String> tf_gender;
    ObservableList<String> gender= FXCollections.observableArrayList("male","female");


    @FXML
    void btn_register(ActionEvent event) {
        inputAdmin();
        clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tf_gender.setItems(gender);
    }

    public void clear(){
        tf_name.setText("");
        tf_dob.getEditor().clear();
        tf_phone.setText("");
        tf_password.setText("");
        tf_gender.getEditor().clear();
        tf_address.setText("");
    }

    public boolean isAllFillUp(){
        boolean fillup=false;
        if(tf_name.getText().isEmpty()||tf_address.getText().isEmpty()||tf_gender.getSelectionModel().getSelectedItem().isEmpty()
                ||tf_password.getText().isEmpty()||tf_phone.getText().isEmpty()||tf_dob.getValue().toString().isEmpty()){
            Alert al=new Alert(Alert.AlertType.ERROR,"Field shouldn't be empty!!!");
            al.showAndWait();
        }else {
            fillup=true;
        }
        return fillup;
    }


    public void inputAdmin(){
        try{
            if(isAllFillUp()){
                String sqlQuery="insert into admin(name,dob,phone,password,address,gender)"+"values('"+tf_name.getText().trim()+"' " +
                        ", '"+tf_dob.getValue()+"' , '"+tf_phone.getText().trim()+"' , '"+tf_password.getText().trim()+"' , '"+tf_address.getText().trim()+"' " +
                        ", '"+tf_gender.getValue()+"' )";
                System.out.println(sqlQuery);
                DBUtility.excutetogetStatment(sqlQuery);

                JOptionPane.showMessageDialog(null,"Add Admin Data Stored!!!");
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }


}
