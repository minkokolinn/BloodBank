package controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.Admin;
import models.AdminDao;
import models.DBUtility;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CustomController implements Initializable{
    @FXML
    private TextField custom_id;

    @FXML
    private TextField custom_name;

    @FXML
    private TextField custom_phone;

    @FXML
    private TextField custom_dob;

    @FXML
    private TextField custom_address;

    @FXML
    private TextField custom_gender;

    @FXML
    void btn_update(ActionEvent event) {
        newData();
        clearData();
        Alert al=new Alert(Alert.AlertType.INFORMATION,"Admin Update Successfully");
        al.showAndWait();
    }

    public void clearData(){
        custom_id.setText("");
        custom_name.setText("");
        custom_dob.setText("");
        custom_gender.setText("");
        custom_address.setText("");
        custom_phone.setText("");
    }

    Statement statement;
    ResultSet rs;
    Connection con= DBUtility.dbConnected();
    AdminViewController av;
//    AdminDao amd;

    public void newData(){
        int id=Integer.parseInt(custom_id.getText().trim());
        String name=custom_name.getText().trim();
        Date dob= Date.valueOf(custom_dob.getText().trim());
        String phone=custom_phone.getText().trim();
        String address=custom_address.getText().trim();
        String gender=custom_gender.getText().trim();

        if((!custom_id.getText().isEmpty())||(!custom_name.getText().isEmpty())||
                (!custom_phone.getText().isEmpty())||(!custom_dob.getText().isEmpty())
                ||(!custom_address.getText().isEmpty())||(!custom_gender.getText().isEmpty())){
            Admin admin=new Admin();
            admin.setId(id);
            admin.setName(name);
            admin.setDob(dob);
            admin.setPhone(phone);
            admin.setAddress(address);
            admin.setGender(gender);
            AdminDao.updateAdminData(admin);

//            amd.updateAdminData(admin);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String sqlQuery="Select id,name,phone,dob,address,gender from admin where id='"+av.id+"'";
        try {
            statement=con.createStatement();
            rs=statement.executeQuery(sqlQuery);
            while (rs.next()){
                custom_id.setText(rs.getInt("id")+"");
                custom_name.setText(rs.getString("name"));
                custom_dob.setText(String.valueOf(rs.getDate("dob")));
                custom_phone.setText(rs.getString("phone"));
                custom_address.setText(rs.getString("address"));
                custom_gender.setText(rs.getString("gender"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        newData();

    }
}
