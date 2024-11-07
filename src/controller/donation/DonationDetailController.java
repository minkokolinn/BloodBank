package controller.donation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.DBUtility;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DonationDetailController implements Initializable{
    @FXML
    private Label detail_donate_id;

    @FXML
    private Label detail_name;

    @FXML
    private Label detail_nrc;

    @FXML
    private Label detail_dob;

    @FXML
    private Label detail_gender;

    @FXML
    private Label detail_address;

    @FXML
    private Label detail_phone;

    @FXML
    private Label detail_blood;

    @FXML
    private Label detail_donate_date;

    ViewListDonationController vldc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection con= DBUtility.dbConnected();
        try{
            String sqlQuery="SELECT * FROM donater WHERE id='"+vldc.id+"'";
            Statement statement=con.createStatement();
            ResultSet rs=statement.executeQuery(sqlQuery);
            System.out.println(sqlQuery);
            while (rs.next()){
                detail_donate_id.setText(rs.getString("donaterid"));
                detail_name.setText(rs.getString("name"));
                detail_nrc.setText(rs.getString("nrc"));
                detail_dob.setText(String.valueOf(rs.getDate("dob")));
                detail_gender.setText(rs.getString("gender"));
                detail_address.setText(rs.getString("address"));
                detail_phone.setText(rs.getString("phonenumber"));
                detail_blood.setText(rs.getString("bloodtype"));
                detail_donate_date.setText(String.valueOf(rs.getDate("donatedate")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
