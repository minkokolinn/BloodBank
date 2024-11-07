package controller.donation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.DBUtility;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ShowDonationController implements Initializable{
    DonateController dc;
    Connection con= DBUtility.dbConnected();
    Statement st;
    ResultSet rs;

    @FXML
    private Label lb_donate_id;

    @FXML
    private Label lb_blood_type;

    @FXML
    private Label id_donate_time;

    @FXML
    private ListView<String> lb_donate_date;
    ObservableList<String> donate_date= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lb_donate_id.setText(dc.id);
        String sqlQuery1="Select `blood_type` from `blood_donate_record` where donate_id='"+dc.id+"'";
        String sqlQuery2="Select count(donate_id) from `blood_donate_record` where donate_id='"+dc.id+"'";
        String sqlQuery3="Select `donate_date` from `blood_donate_record` where donate_id='"+dc.id+"'";

        try {
            st=con.createStatement();
            rs=st.executeQuery(sqlQuery1);
            while (rs.next()){
                lb_blood_type.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        try {
            st=con.createStatement();
            rs=st.executeQuery(sqlQuery2);
            while (rs.next()){
                id_donate_time.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        try {
            st=con.createStatement();
            rs=st.executeQuery(sqlQuery3);
            while (rs.next()){
                donate_date.addAll(rs.getString(1));
            }
            lb_donate_date.setItems(donate_date);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
