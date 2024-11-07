package models;

public class AdminDao{
    public static void updateAdminData(Admin admin){
        String sqlQuery="Update admin set id='"+admin.getId()+"' ,name='"+admin.getName()+"' ,dob='"+admin.getDob()+"' ,phone='"+admin.getPhone()+"' ,address='"+admin.getAddress()+"' ,gender='"+admin.getGender()+"' where id='"+admin.getId()+"'";
        DBUtility.updateData(sqlQuery);
        System.out.println(sqlQuery);
    }
}
