package com.example.homeseek;

public class datahold {
    String IDNumber,NAME,GENDER,PURL,DETAILS,AGE,PHONE;



    public datahold(String IDNumber, String NAME, String GENDER, String PURL, String DETAILS, String AGE, String PHONE) {
        this.IDNumber = IDNumber;
        this.NAME = NAME;
        this.GENDER = GENDER;
        this.PURL = PURL;
        this.DETAILS = DETAILS;
        this.AGE = AGE;
        this.PHONE = PHONE;
    }

    public String getIDNumber() {return IDNumber;}

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getGENDER() {
        return GENDER;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }

    public String getPURL() {
        return PURL;
    }

    public void setPURL(String PURL) {
        this.PURL = PURL;
    }

    public String getDETAILS() {
        return DETAILS;
    }

    public void setDETAILS(String DETAILS) {
        this.DETAILS = DETAILS;
    }

    public String getAGE() {
        return AGE;
    }

    public void setAGE(String AGE) {
        this.AGE = AGE;
    }

    public String getPHONE() {return PHONE;}
    public void setPHONE(String PHONE) {this.PHONE = PHONE;}
}
