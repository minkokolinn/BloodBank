package controller.admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.Admin;
import models.DBUtility;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DetailController implements Initializable{
    private Admin admin;


    @FXML
    private Label detail_id;

    @FXML
    private Label detail_name;

    @FXML
    private Label detail_dob;

    @FXML
    private Label detail_gender;

    @FXML
    private Label detail_address;

    @FXML
    private Label detail_phone;

    AdminViewController av;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection con=DBUtility.dbConnected();
        try {
            String sqlQuery="SELECT * FROM admin WHERE id='"+av.id+"'";
            Statement statement= con.createStatement();
            ResultSet rs=statement.executeQuery(sqlQuery);
            System.out.println(sqlQuery);
            while (rs.next()){
                detail_id.setText(String.valueOf(rs.getInt("id")));
                detail_name.setText(rs.getString("name"));
                detail_phone.setText(rs.getString("phone"));
                detail_address.setText(rs.getString("address"));
                detail_gender.setText(rs.getString("gender"));
                detail_dob.setText(String.valueOf(rs.getDate("dob")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
