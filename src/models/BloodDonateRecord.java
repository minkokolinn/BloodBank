package models;

import java.sql.Date;

public class BloodDonateRecord {
    private int blood_id;
    private String donate_id;
    private String blood_type;
    private String no_of_blood;
    private Date donate_date;

    public BloodDonateRecord(int blood_id, String donate_id, String blood_type, String no_of_blood, Date donate_date) {
        this.blood_id = blood_id;
        this.donate_id = donate_id;
        this.blood_type = blood_type;
        this.no_of_blood = no_of_blood;
        this.donate_date = donate_date;
    }

    public int getBlood_id() {
        return blood_id;
    }

    public void setBlood_id(int blood_id) {
        this.blood_id = blood_id;
    }

    public String getDonate_id() {
        return donate_id;
    }

    public void setDonate_id(String donate_id) {
        this.donate_id = donate_id;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    public String getNo_of_blood() {
        return no_of_blood;
    }

    public void setNo_of_blood(String no_of_blood) {
        this.no_of_blood = no_of_blood;
    }

    public Date getDonate_date() {
        return donate_date;
    }

    public void setDonate_date(Date donate_date) {
        this.donate_date = donate_date;
    }
}
