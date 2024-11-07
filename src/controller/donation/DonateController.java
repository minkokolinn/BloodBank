package controller.donation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.DBUtility;
import models.Retrieve;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DonateController implements Initializable{
    PreparedStatement ps;
    Connection con= DBUtility.dbConnected();
    ResultSet rs;
    Retrieve retrieve=new Retrieve();
    Statement statement;
    public static String id;

    @FXML
    private TextField tf_blood_quantity;

    @FXML
    private TextField tf_blood_type;

    @FXML
    private ComboBox<String> cb_donate_id;
    ObservableList<String> donater_id= FXCollections.observableArrayList();

    @FXML
    private DatePicker donate_date;

    @FXML
    void btn_donate(ActionEvent event) {
        if(isAllFillUp()){
            insertData();
            clear();
        }
    }

    @FXML
    void select(ActionEvent event) {
        if(cb_donate_id.getSelectionModel().getSelectedItem().toString()!=null){
            id=cb_donate_id.getSelectionModel().getSelectedItem();
            try {
                String sqlQuery="Select `bloodtype` from `donater` where donaterid='"+id+"' ";
                statement=con.createStatement();
                rs=statement.executeQuery(sqlQuery);
                while (rs.next()){
                    tf_blood_type.setText(rs.getString("bloodtype"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            showDonation();
            System.out.println(id);
        }

    }

    public void showDonation(){
        Stage stage=new Stage();
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/view/donation/show_donation.fxml"));
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void insertData(){
        try {
            String sqlQuery="Insert into `blood_donate_record`(`donate_id`,`blood_type`,`no_of_blood`,`donate_date`) values('"+cb_donate_id.getSelectionModel().getSelectedItem().toString()+"' , '"+tf_blood_type.getText()+"' , '"+Integer.parseInt(tf_blood_quantity.getText().trim())+"' , '"+Date.valueOf(donate_date.getValue())+"')";
            ps=con.prepareStatement(sqlQuery);
            ps.executeUpdate();
            System.out.println(sqlQuery);
        }catch (SQLException se){
            se.printStackTrace();
            System.out.println(se.getMessage());
        }
        try {
            String sqlQuery="Update blood set quantity=quantity+1 where blood_type='"+tf_blood_type.getText()+"'";
            ps=con.prepareStatement(sqlQuery);
            ps.executeUpdate();
            System.out.println(sqlQuery);
        }catch (SQLException se){
            se.printStackTrace();
            System.out.println(se);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        retrieve.retrieveforcombo(donater_id,cb_donate_id,"donater","donaterid");
    }

    private boolean isAllFillUp(){
        boolean fillUp=false;
        if(cb_donate_id.getSelectionModel().getSelectedItem().toString().isEmpty()||
                tf_blood_type.getText().isEmpty()||tf_blood_quantity.getText().isEmpty()||
                donate_date.getValue().toString().isEmpty()){
            Alert al=new Alert(Alert.AlertType.ERROR,"Field shouldn't be empty");
            al.showAndWait();
        }else {
            fillUp=true;
        }
        return fillUp;
    }

    public void clear(){
        cb_donate_id.getEditor().clear();
        tf_blood_type.clear();
        tf_blood_quantity.clear();
    }
}
