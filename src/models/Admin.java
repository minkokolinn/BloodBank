package models;

import java.util.Date;

public class Admin {
    private int id;
    private String name;
    private Date dob;
    private String phone;
    private String password;
    private String address;
    private String gender;

    public Admin(int id, String name, Date dob, String phone, String password, String address, String gender) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.phone = phone;
        this.password = password;
        this.address = address;
        this.gender = gender;
    }

    public Admin() {
//        this.id=id;
//        this.name=name;
//        this.dob=dob;
//        this.phone=phone;
//        this.address=address;
//        this.gender=gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(java.sql.Date dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
