package controller.record;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.DBUtility;
import models.Patient_Transfer;
import utility.fxmlL;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class RecordPatientController implements Initializable{
    ObservableList<Patient_Transfer> patient_transfers= FXCollections.observableArrayList();
    Connection con= DBUtility.dbConnected();
    Statement statement;
    ResultSet rs;

    @FXML
    void report_back(ActionEvent event) {
        new fxmlL((Stage)((Node)event.getSource()).getScene().getWindow(),"/view/HomePage.fxml");
    }

    @FXML
    private TextField tf_search;

    @FXML
    private TableView<Patient_Transfer> tv_patient;

    @FXML
    private TableColumn<Patient_Transfer, Integer> col_id;

    @FXML
    private TableColumn<Patient_Transfer, String> col_name;

    @FXML
    private TableColumn<Patient_Transfer, String> col_blood_type;

    @FXML
    private TableColumn<Patient_Transfer, Date> col_transfer_date;

    @FXML
    private TableColumn<Patient_Transfer, Integer> col_no_of_bags;

    public void showData(){
        try {
            statement=con.createStatement();
            rs=statement.executeQuery("Select * from patient_transfer");
            while (rs.next()){
                patient_transfers.addAll(new Patient_Transfer(
                        rs.getInt("id"),
                        rs.getString("pa_name"),
                        rs.getString("blood_type"),
                        rs.getDate("tf_date"),
                        rs.getInt("no_of_bags")
                ));
            }
            tv_patient.setItems(patient_transfers);
            col_id.setCellValueFactory(new PropertyValueFactory<Patient_Transfer,Integer>("id"));
            col_name.setCellValueFactory(new PropertyValueFactory<Patient_Transfer,String>("pa_name"));
            col_blood_type.setCellValueFactory(new PropertyValueFactory<Patient_Transfer,String>("blood_type"));
            col_transfer_date.setCellValueFactory(new PropertyValueFactory<Patient_Transfer,Date>("tf_date"));
            col_no_of_bags.setCellValueFactory(new PropertyValueFactory<Patient_Transfer,Integer>("no_of_bags"));
        }catch (SQLException se){
            se.printStackTrace();
            System.out.println(se);
        }
    }

    @FXML
    void btn_search(ActionEvent event) {
        FilteredList<Patient_Transfer>filteredData=new FilteredList<Patient_Transfer>(patient_transfers,p->true);
        filteredData.setPredicate(login->{
            if(tf_search.getText().toString().isEmpty()||tf_search.getText().equals("")){
                return true;
            }
            String lowerCase=tf_search.getText().toLowerCase();
            if(login.getPa_name().toLowerCase().contains(lowerCase)){
                return true;
            }else if(login.getBlood_type().toLowerCase().contains(lowerCase)){
                return true;
            }
            return false;
        });
        SortedList<Patient_Transfer> sortedData=new SortedList<Patient_Transfer>(filteredData);
        sortedData.comparatorProperty().bind(tv_patient.comparatorProperty());
        tv_patient.setItems(sortedData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tf_search.textProperty().addListener((observable, oldValue, newValue) -> {
            if(tf_search.getText().equals("")){
                patient_transfers.clear();
                tv_patient.getSelectionModel().clearSelection();
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
