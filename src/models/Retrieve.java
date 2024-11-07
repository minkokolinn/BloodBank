package models;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Retrieve {
    static Connection con=DBUtility.dbConnected();
    Statement statement;
    public static int total;
    public static int noMale;
    public static int noFemale;
    public static int O;
    public static int A;
    public static int B;
    public static int AB;

    public void retrieveforcombo(ObservableList<String>list, ComboBox<String> comboBox, String table, String colname){
        try{
            statement=con.createStatement();
            ResultSet rs=statement.executeQuery("Select * from "+table+" ");
            while (rs.next())
            comboBox.setItems(list);{
                list.add(rs.getString(colname));
            }
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    public void getTotal(Label totalLBL,String tablename){
        try{
            statement=con.createStatement();
            ResultSet rs=statement.executeQuery("Select COUNT(id) from "+ tablename +" ");
            while (rs.next()){
                total=rs.getInt(1);
                totalLBL.setText(String.valueOf(total));
            }
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    public void getMale(Label maleLBL,String tablename){
        String genmale="male";
        try {
            statement=con.createStatement();
            ResultSet rs=statement.executeQuery("Select count(gender) from "+tablename+" where gender='"+genmale+"' ");
            while (rs.next()){
                noMale=rs.getInt(1);
                maleLBL.setText(String.valueOf(noMale));
            }
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    public void getFemale(Label femaleLBL,String tablename){
        String genfemale="female";
        try {
            statement=con.createStatement();
            ResultSet rs=statement.executeQuery("Select count(gender) from "+tablename+" where gender='"+genfemale+"' ");
            while (rs.next()){
                noFemale=rs.getInt(1);
                femaleLBL.setText(String.valueOf(noFemale));
            }
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

}
