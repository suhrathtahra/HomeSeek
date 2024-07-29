package com.example.homeseek;

public class model {
    String age,details,gender,idnumber,name,purl,phone;
    model()
    {

    }


 public model(String age, String details, String gender, String idnumber, String name, String purl,String phone) {
        this.age = age;
        this.details = details;
        this.gender = gender;
        this.idnumber = idnumber;
        this.name = name;
        this.purl = purl;
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public String getPhone() {return phone;}

    public void setPhone(String phone) {this.phone = phone;}


}
