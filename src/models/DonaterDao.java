package models;

public class DonaterDao {
    public static void updateDonaterData(Donater donater)
    {
        String sqlQuery="Update donater set name='"+donater.getName()+"' , nrc='"+donater.getNrc()+"' , " +
                "address='"+donater.getAddress()+"' , phonenumber='"+donater.getPhonenumber()+"' ," +
                " dob='"+donater.getDob()+"' , donatedate='"+donater.getDonatedate()+"' ," +
                " bloodtype='"+donater.getBloodtype()+"' ," +
                " gender='"+donater.getGender()+"' , age='"+donater.getAge()+"' where id='"+donater.getId()+"'";
        DBUtility.updateData(sqlQuery);
        System.out.println(sqlQuery);
    }
}
