package controller.record;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.DBUtility;
import models.Hospital_Transfer;
import utility.fxmlL;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class RecordHospitalController implements Initializable{
    ObservableList<Hospital_Transfer> hospital_transfers= FXCollections.observableArrayList();
    Connection con= DBUtility.dbConnected();
    Statement statement;
    ResultSet rs;

    @FXML
    private TextField tf_search;

    @FXML
    private Button btn_search;

    @FXML
    void btn_search(ActionEvent event) {
        FilteredList<Hospital_Transfer> filteredData=new FilteredList<Hospital_Transfer>(hospital_transfers,p->true);
        filteredData.setPredicate(loginadd->{
            if(tf_search.getText().toString().isEmpty()||tf_search.getText().equals("")){
                return true;
            }
            String lowerCase=tf_search.getText().toLowerCase();
            if(loginadd.getHp_name().toLowerCase().contains(lowerCase)){
                return true;
            }else if(loginadd.getBlood_type().toLowerCase().contains(lowerCase)){
                return true;
            }
            return false;
        });
        SortedList<Hospital_Transfer>sortedData=new SortedList<Hospital_Transfer>(filteredData);
        sortedData.comparatorProperty().bind(tv_hospital.comparatorProperty());
        tv_hospital.setItems(sortedData);
    }

    @FXML
    private TableView<Hospital_Transfer> tv_hospital;

    @FXML
    private TableColumn<Hospital_Transfer, Integer> col_id;

    @FXML
    private TableColumn<Hospital_Transfer, String> col_hospital_name;

    @FXML
    private TableColumn<Hospital_Transfer, String> col_blood_type;

    @FXML
    private TableColumn<Hospital_Transfer, Date> col_transfer_date;

    @FXML
    private TableColumn<Hospital_Transfer, Integer> col_no_of_bags;


    @FXML
    void report_back(ActionEvent event) {
        new fxmlL((Stage)((Node)event.getSource()).getScene().getWindow(),"/view/HomePage.fxml");
    }

    public void showData(){
        try {
            statement=con.createStatement();
            rs=statement.executeQuery("Select * from hospital_transfer");
            while (rs.next()){
                hospital_transfers.addAll(new Hospital_Transfer(
                        rs.getInt("id"),
                        rs.getString("hp_name"),
                        rs.getString("blood_type"),
                        rs.getDate("tf_date"),
                        rs.getInt("no_of_bags")
                ));
            }
            tv_hospital.setItems(hospital_transfers);
            col_id.setCellValueFactory(new PropertyValueFactory<Hospital_Transfer,Integer>("id"));
            col_hospital_name.setCellValueFactory(new PropertyValueFactory<Hospital_Transfer,String>("hp_name"));
            col_blood_type.setCellValueFactory(new PropertyValueFactory<Hospital_Transfer,String>("blood_type"));
            col_transfer_date.setCellValueFactory(new PropertyValueFactory<Hospital_Transfer,Date>("tf_date"));
            col_no_of_bags.setCellValueFactory(new PropertyValueFactory<Hospital_Transfer,Integer>("no_of_bags"));
        }catch (SQLException se){
            se.printStackTrace();
            System.out.println(se);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tf_search.textProperty().addListener((observable, oldValue, newValue) -> {
            if(tf_search.getText().equals("")){
                hospital_transfers.clear();
                tv_hospital.getSelectionModel().clearSelection();
                try {
                    showData();
                }catch (Exception se){
                    se.printStackTrace();
                }
            }
        });
        showData();
    }
}
