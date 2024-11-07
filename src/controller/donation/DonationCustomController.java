package controller.donation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.DBUtility;
import models.Donater;
import models.DonaterDao;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DonationCustomController implements Initializable{
    @FXML
    private TextField custom_donater_id;

    @FXML
    private TextField custom_name;

    @FXML
    private TextField custom_nrc;

    @FXML
    private TextField custom_address;

    @FXML
    private TextField custom_phone;

    @FXML
    private TextField custom_dob;

    @FXML
    private TextField custom_donate_date;

    @FXML
    private TextField custom_blood;

    @FXML
    private TextField custom_gender;

    @FXML
    private TextField custom_age;

    Statement statement;
    ResultSet rs;
    Connection con= DBUtility.dbConnected();
    ViewListDonationController vldc;
    PreparedStatement ps;

    @FXML
    void custom_update(ActionEvent event) {
        newData();
        clearData();
        Alert al=new Alert(Alert.AlertType.INFORMATION,"Admin Update Successfully");
        al.showAndWait();
        custom_name.getScene().getWindow().hide();
    }

    public void newData(){
        int id=vldc.id;
        String donateid= custom_donater_id.getText().trim();
        String name=custom_name.getText().trim();
        String nrc=custom_nrc.getText().trim();
        String address=custom_address.getText().trim();
        String phone=custom_phone.getText().trim();
        Date dob= Date.valueOf(custom_dob.getText().trim());
        Date donatedate= Date.valueOf(custom_donate_date.getText().trim());
        String blood=custom_blood.getText().trim();
        String gender=custom_gender.getText().trim();
        int age= Integer.parseInt(custom_age.getText().trim());


            try{
                String sqlQuery="Update `donater` set `donaterid`='"+donateid+"' ," +
                        " `name`='"+name+"' , `nrc`='"+nrc+"' ," +
                        "  `address`='"+address+"' , `phonenumber`='"+phone+"' , `dob`='"+dob+"' ," +
                        "  `donatedate`='"+donatedate+"' , `bloodtype`='"+blood+"'  , `gender`='"+gender+"' ," +
                        "  `age`='"+age+"' where id='"+vldc.id+"' " ;
                ps=con.prepareStatement(sqlQuery);
                ps.executeUpdate();
            }catch (SQLException se){
                se.printStackTrace();
            }

    }

    public void clearData(){
        custom_donater_id.setText("");
        custom_name.setText("");
        custom_nrc.setText("");
        custom_address.setText("");
        custom_phone.setText("");
        custom_dob.setText("");
        custom_donate_date.setText("");
        custom_blood.setText("");
        custom_gender.setText("");
        custom_age.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String sqlQuery="Select donaterid,name,nrc,address,phonenumber,dob,donatedate,bloodtype,gender,age from donater where id='"+vldc.id+"'";
        try{
            statement=con.createStatement();
            rs=statement.executeQuery(sqlQuery);
            while (rs.next()){
                custom_donater_id.setText(rs.getString("donaterid"));
                custom_name.setText(rs.getString("name"));
                custom_nrc.setText(rs.getString("nrc"));
                custom_address.setText(rs.getString("address"));
                custom_phone.setText(rs.getString("phonenumber"));
                custom_dob.setText(String.valueOf(rs.getDate("dob")));
                custom_donate_date.setText(String.valueOf(rs.getDate("donatedate")));
                custom_blood.setText(rs.getString("bloodtype"));
                custom_gender.setText(rs.getString("gender"));
                custom_age.setText(String.valueOf(rs.getInt("age")));
            }
        }catch (SQLException se){
            se.printStackTrace();
        }
        newData();
    }
}
