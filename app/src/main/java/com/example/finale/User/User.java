package com.example.finale.User;


public class User {

   private String LOGIN_ID,USERNAME,EMAIL_ID,PASSWORD,PHONE_NO;

    public User() {
    }

    public User(String LOGIN_ID, String USERNAME, String EMAIL_ID, String PASSWORD, String PHONE_NO) {
        this.LOGIN_ID = LOGIN_ID;
        this.USERNAME = USERNAME;
        this.EMAIL_ID = EMAIL_ID;
        this.PASSWORD = PASSWORD;
        this.PHONE_NO = PHONE_NO;
    }

    public String getLOGIN_ID() {
        return LOGIN_ID;
    }

    public void setLOGIN_ID(String LOGIN_ID) {
        this.LOGIN_ID = LOGIN_ID;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getEMAIL_ID() {
        return EMAIL_ID;
    }

    public void setEMAIL_ID(String EMAIL_ID) {
        this.EMAIL_ID = EMAIL_ID;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getPHONE_NO() {
        return PHONE_NO;
    }

    public void setPHONE_NO(String PHONE_NO) {
        this.PHONE_NO = PHONE_NO;
    }
}
