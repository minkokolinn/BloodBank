package models;

import java.sql.*;

public class DBUtility {
    private static Connection connection;
    private static Statement statement;
    private static final String url="jdbc:mysql://localhost:3306/blood_bank";
    private static final String user="root";
    private static final String pass="";

    public static Connection dbConnected(){
        if(connection==null){
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                connection=(Connection) DriverManager.getConnection(url,user,pass);
            }catch (ClassNotFoundException c){
                c.printStackTrace();
            }catch (SQLException s){
                s.printStackTrace();
            }
        }
        return connection;
    }

    public static void insertData(String sqlQuery){
        dbConnected();
        try{
            statement=(Statement) connection.createStatement();
            statement.executeUpdate(sqlQuery);
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    public static PreparedStatement excutetogetStatment(String sqlQuery){
        dbConnected();
        PreparedStatement ps=null;
        try{
            ps=(PreparedStatement)connection.prepareStatement(sqlQuery);
            ps.executeUpdate();
        }catch (SQLException se){
            se.printStackTrace();
        }
        return ps;
    }

    public static void updateData(String sqlQuery){
        dbConnected();
        try{
            statement=(Statement)connection.createStatement();
            statement.executeUpdate(sqlQuery);
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    public static void deleteDate(String sqlQuery){
        dbConnected();
        try{
            statement=(Statement)connection.createStatement();
            statement.executeUpdate(sqlQuery);
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    public static ResultSet retrieve(String sqlQuery){
        dbConnected();
        ResultSet rs=null;
        try {
            statement=(Statement)connection.createStatement();
            rs=statement.executeQuery(sqlQuery);
        }catch (SQLException se){
            se.printStackTrace();
        }
        return rs;
    }


}
