package models;

import java.sql.Date;

public class Patient_Transfer {
    private int id;
    private String pa_name;
    private String blood_type;
    private Date tf_date;
    private int no_of_bags;

    public Patient_Transfer(int id, String pa_name, String blood_type, Date tf_date, int no_of_bags) {
        this.id = id;
        this.pa_name = pa_name;
        this.blood_type = blood_type;
        this.tf_date = tf_date;
        this.no_of_bags = no_of_bags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPa_name() {
        return pa_name;
    }

    public void setPa_name(String pa_name) {
        this.pa_name = pa_name;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    public Date getTf_date() {
        return tf_date;
    }

    public void setTf_date(Date tf_date) {
        this.tf_date = tf_date;
    }

    public int getNo_of_bags() {
        return no_of_bags;
    }

    public void setNo_of_bags(int no_of_bags) {
        this.no_of_bags = no_of_bags;
    }
}
