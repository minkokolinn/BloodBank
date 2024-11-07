package controller.record;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.BloodDonateRecord;
import models.DBUtility;
import models.Donater;
import utility.fxmlL;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class RecordBloodDonateController implements Initializable{
    ObservableList<BloodDonateRecord> bloodDonateRecords= FXCollections.observableArrayList();
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
    private TableView<BloodDonateRecord> donate_table;

    @FXML
    private TableColumn<BloodDonateRecord, Integer> col_blood_id;

    @FXML
    private TableColumn<BloodDonateRecord, String> col_donater_id;

    @FXML
    private TableColumn<BloodDonateRecord,String> col_blood_type;

    @FXML
    private TableColumn<BloodDonateRecord, String> col_no_of_blood;

    @FXML
    private TableColumn<BloodDonateRecord, Date> col_donate_date;

    @FXML
    void btnSearch(ActionEvent event) {
        FilteredList<BloodDonateRecord> filteredData=new FilteredList<BloodDonateRecord>(bloodDonateRecords, p->true);
        filteredData.setPredicate(loginadd->{
            if(tf_search.getText().toString().isEmpty()||tf_search.getText().equals("")){
                return true;
            }
            String lowerCase=tf_search.getText().toLowerCase();
            if(loginadd.getBlood_type().toLowerCase().contains(lowerCase)){
                return true;
            }else if(loginadd.getNo_of_blood().toLowerCase().contains(lowerCase)){
                return true;
            }else if(loginadd.getDonate_id().toLowerCase().contains(lowerCase)){
                return true;
            }
            return false;
        });
        SortedList<BloodDonateRecord> sortedData=new SortedList<BloodDonateRecord>(filteredData);
        sortedData.comparatorProperty().bind(donate_table.comparatorProperty());
        donate_table.setItems(sortedData);
    }

    public void showData(){
        try {
            String sqlQuery="Select * from blood_donate_record";
            statement=con.createStatement();
            rs=statement.executeQuery(sqlQuery);
            while (rs.next()){
                bloodDonateRecords.addAll(new BloodDonateRecord(
                        rs.getInt("blood_id"),
                        rs.getString("donate_id"),
                        rs.getString("blood_type"),
                        rs.getString("no_of_blood"),
                        rs.getDate("donate_date")
                ));
            }
            donate_table.setItems(bloodDonateRecords);
            col_blood_id.setCellValueFactory(new PropertyValueFactory<BloodDonateRecord,Integer>("blood_id"));
            col_donater_id.setCellValueFactory(new PropertyValueFactory<BloodDonateRecord,String>("donate_id"));
            col_blood_type.setCellValueFactory(new PropertyValueFactory<BloodDonateRecord,String>("blood_type"));
            col_no_of_blood.setCellValueFactory(new PropertyValueFactory<BloodDonateRecord,String>("no_of_blood"));
            col_donate_date.setCellValueFactory(new PropertyValueFactory<BloodDonateRecord,Date>("donate_date"));
        }catch (SQLException se){
            se.printStackTrace();
            System.out.println(se);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tf_search.textProperty().addListener((observable, oldValue, newValue) -> {
            if(tf_search.getText().equals("")){
                bloodDonateRecords.clear();
                donate_table.getSelectionModel().clearSelection();
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
