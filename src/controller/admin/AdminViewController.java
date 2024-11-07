package controller.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Admin;
import models.DBUtility;
import utility.utilities;

import javax.swing.text.Utilities;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable
{
    ObservableList<Admin> admins= FXCollections.observableArrayList();
    Connection con=DBUtility.dbConnected();
    Statement statement;
    ResultSet rs;

    public static int id;
    @FXML
    private TextField tf_search;

    @FXML
    private TableView<Admin> admin_tv;

    @FXML
    private TableColumn<Admin, Integer> col_id;

    @FXML
    private TableColumn<Admin, String> col_name;

    @FXML
    private TableColumn<Admin, String> col_gender;

    @FXML
    private TableColumn<Admin, String> col_phone;

    @FXML
    private TableColumn<Admin, Date> col_dob;

    @FXML
    private TableColumn<Admin, String> col_address;

    @FXML
    void btn_custom(ActionEvent event) {
        new utilities().SceneChangeShowAndWait("Custom","/view/admin/Custom.fxml");
    }

    @FXML
    void btn_delete(ActionEvent event) {
        Alert al=new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("Delete!");
        al.setHeaderText("Delete student List");
        al.setContentText("Are you sure??");

        Optional<ButtonType> result=al.showAndWait();
        if(result.get()==ButtonType.OK){
            Admin select=admin_tv.getSelectionModel().getSelectedItem();
            String sqlQuery="delete from admin where id='"+select.getId()+"'";
            DBUtility.deleteDate(sqlQuery);
            admins.remove(select);
        }
        admin_tv.getSelectionModel().clearSelection();
    }

    @FXML
    void btn_detail(ActionEvent event) {
        new utilities().SceneChangeShowAndWait("Admin Detail","/view/admin/Detail.fxml");
    }

    @FXML
    void btn_refresh(ActionEvent event) {
        for(int i=0;i<admin_tv.getItems().size();i++){
            admin_tv.getItems().clear();
            getAdminData();
        }
    }

    @FXML
    void btn_search(ActionEvent event) {
        FilteredList<Admin> filteredData=new FilteredList<Admin>(admins,p->true);
        filteredData.setPredicate(loginadd->{
            if(tf_search.getText().equals("")||tf_search.getText().isEmpty()){
                return true;
            }
            String lowerCase=tf_search.getText().toLowerCase();
            if(loginadd.getName().toLowerCase().contains(lowerCase)){
                return true;
            }else if(loginadd.getAddress().toLowerCase().contains(lowerCase)){
                return true;
            }else if(loginadd.getPhone().toLowerCase().contains(lowerCase)){
                return true;
            }
            return false;
        });
        //Wrap the filtered list in a sorted List//
        SortedList<Admin> sortedData=new SortedList<Admin>(filteredData);
        //Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(admin_tv.comparatorProperty());
        //Add sorted(and filtered)data to the table
        admin_tv.setItems(sortedData);
    }


    public void getAdminData(){
        try {
            statement=con.createStatement();
            rs=statement.executeQuery("SELECT * FROM admin");
            while (rs.next()){
                admins.addAll(new Admin(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("dob"),
                        rs.getString("phone"),
                        rs.getString("password"),
                        rs.getString("address"),
                        rs.getString("gender")

                ));
            }
            admin_tv.setItems(admins);
            col_id.setCellValueFactory(new PropertyValueFactory<Admin,Integer>("id"));
            col_name.setCellValueFactory(new PropertyValueFactory<Admin,String>("name"));
            col_dob.setCellValueFactory(new PropertyValueFactory<Admin,Date>("dob"));
            col_phone.setCellValueFactory(new PropertyValueFactory<Admin,String>("phone"));
            col_address.setCellValueFactory(new PropertyValueFactory<Admin,String>("address"));
            col_gender.setCellValueFactory(new PropertyValueFactory<Admin,String>("gender"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tf_search.textProperty().addListener((observable, oldValue, newValue) -> {
            if(tf_search.getText().equals("")){
                admins.clear();
                admin_tv.getSelectionModel().clearSelection();
                try{
                    getAdminData();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        getAdminData();

        admin_tv.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{
            id=admin_tv.getSelectionModel().getSelectedItem().getId();
        });
    }

}
