package controller.donation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Admin;
import models.DBUtility;
import models.Donater;
import utility.utilities;

import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class ViewListDonationController implements Initializable{
    ObservableList<Donater>donaters= FXCollections.observableArrayList();
    Connection con= DBUtility.dbConnected();
    Statement statement;
    ResultSet rs;

    public static int id;

    @FXML
    private TextField tf_search;

    @FXML
    private TableView<Donater> donater_tv;

    @FXML
    private TableColumn<Donater, Integer> col_id;

    @FXML
    private TableColumn<Donater, String> col_admin_id;

    @FXML
    private TableColumn<Donater, String> col_name;

    @FXML
    private TableColumn<Donater, String> col_nrc;

    @FXML
    private TableColumn<Donater, Date> col_dob;

    @FXML
    private TableColumn<Donater, String> col_gender;

    @FXML
    private TableColumn<Donater, String> col_address;

    @FXML
    private TableColumn<Donater, String> col_phone;

    @FXML
    private TableColumn<Donater, String> col_blood;

    @FXML
    private TableColumn<Donater, Date> col_donate_date;

    @FXML
    void donater_custom(ActionEvent event) {
        new utilities().SceneChangeShowAndWait("Donater Custom","/view/donation/DonationCustom.fxml");
    }

    @FXML
    void donater_delete(ActionEvent event) {
        Alert al=new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("Delete!!!");
        al.setHeaderText("Delete Student List!");
        al.setContentText("Are u sure to delete");
        Optional<ButtonType> result=al.showAndWait();

        if(result.get()==ButtonType.OK){
            Donater select=donater_tv.getSelectionModel().getSelectedItem();
            String sql="Delete from donater where id='"+select.getId()+"'";
            DBUtility.deleteDate(sql);
            donaters.remove(select);
        }
        donater_tv.getSelectionModel().clearSelection();
    }

    @FXML
    void donater_detail(ActionEvent event) {
        new utilities().SceneChangeShowAndWait("Donater Detail","/view/donation/DonationDetail.fxml");
    }

    @FXML
    void donater_refresh(ActionEvent event) {
        for(int i=0;i<donater_tv.getItems().size();i++){
            donater_tv.getItems().clear();
            getDonaterData();
        }
    }

    @FXML
    void donater_search(ActionEvent event) {
        FilteredList<Donater> filteredData=new FilteredList<Donater>(donaters,p->true);
        filteredData.setPredicate(loginadd->{
            if(tf_search.getText().equals("")||tf_search.getText().isEmpty()){
                return true;
            }
            String lowerCase=tf_search.getText().toLowerCase();
            if(loginadd.getName().toLowerCase().contains(lowerCase)){
                return true;
            }else if(loginadd.getAddress().toLowerCase().contains(lowerCase)){
                return true;
            }else if(loginadd.getPhonenumber().toLowerCase().contains(lowerCase)){
                return true;
            }
            return false;
        });
        SortedList<Donater> sortedData=new SortedList<Donater>(filteredData);
        sortedData.comparatorProperty().bind(donater_tv.comparatorProperty());
        donater_tv.setItems(sortedData);
    }

    public void getDonaterData(){
        try{
            statement=con.createStatement();
            rs=statement.executeQuery("SELECT * FROM donater");
            while (rs.next()){
                donaters.addAll(new Donater(
                        rs.getInt("id"),
                        rs.getString("donaterid"),
                        rs.getString("name"),
                        rs.getString("fathername"),
                        rs.getString("nrc"),
                        rs.getInt("age"),
                        rs.getDate("dob"),
                        rs.getString("gender"),
                        rs.getString("address"),
                        rs.getString("phonenumber"),
                        rs.getString("bloodtype"),
                        rs.getString("region"),
                        rs.getDate("donatedate"),
                        rs.getString("jobposition")
                ));
            }
            donater_tv.setItems(donaters);
            col_id.setCellValueFactory(new PropertyValueFactory<Donater,Integer>("id"));
            col_admin_id.setCellValueFactory(new PropertyValueFactory<Donater,String>("donaterid"));
            col_name.setCellValueFactory(new PropertyValueFactory<Donater,String>("name"));
            col_nrc.setCellValueFactory(new PropertyValueFactory<Donater,String>("nrc"));
            col_dob.setCellValueFactory(new PropertyValueFactory<Donater,Date>("dob"));
            col_gender.setCellValueFactory(new PropertyValueFactory<Donater,String>("gender"));
            col_address.setCellValueFactory(new PropertyValueFactory<Donater,String>("address"));
            col_phone.setCellValueFactory(new PropertyValueFactory<Donater,String>("phonenumber"));
            col_blood.setCellValueFactory(new PropertyValueFactory<Donater,String>("bloodtype"));
            col_donate_date.setCellValueFactory(new PropertyValueFactory<Donater,Date>("donatedate"));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tf_search.textProperty().addListener((observable, oldValue, newValue) -> {
            if(tf_search.getText().equals("")){
                donaters.clear();
                donater_tv.getSelectionModel().clearSelection();
                try{
                    getDonaterData();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        getDonaterData();

        donater_tv.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{
            id=donater_tv.getSelectionModel().getSelectedItem().getId();
        });
    }
}
