package models;

import java.sql.Date;

public class Donater {
    private int id;
    private String donaterid;
    private String name;
    private String fathername;
    private String nrc;
    private int age;
    private Date dob;
    private String gender;
    private String address;
    private String phonenumber;
    private String bloodtype;
    private String region;
    private Date donatedate;
    private String jobposition;

    public Donater(int id, String donaterid, String name, String fathername, String nrc, int age, Date dob, String gender, String address, String phonenumber, String bloodtype, String region, Date donatedate, String jobposition) {
        this.id = id;
        this.donaterid = donaterid;
        this.name = name;
        this.fathername = fathername;
        this.nrc = nrc;
        this.age = age;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.phonenumber = phonenumber;
        this.bloodtype = bloodtype;
        this.region = region;
        this.donatedate = donatedate;
        this.jobposition = jobposition;
    }

    public Donater(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDonaterid() {
        return donaterid;
    }

    public void setDonaterid(String donaterid) {
        this.donaterid = donaterid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Date getDonatedate() {
        return donatedate;
    }

    public void setDonatedate(Date donatedate) {
        this.donatedate = donatedate;
    }

    public String getJobposition() {
        return jobposition;
    }

    public void setJobposition(String jobposition) {
        this.jobposition = jobposition;
    }
}
