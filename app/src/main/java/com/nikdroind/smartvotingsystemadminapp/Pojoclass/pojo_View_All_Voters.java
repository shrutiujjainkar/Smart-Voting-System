package com.nikdroind.smartvotingsystemadminapp.Pojoclass;

public class pojo_View_All_Voters {

    String voter_no, full_name, gender, mobile_no,address, your_voting_address;

    public pojo_View_All_Voters(String voter_no, String full_name, String gender, String mobile_no, String address, String your_voting_address) {
        this.voter_no = voter_no;
        this.full_name = full_name;
        this.gender = gender;
        this.mobile_no = mobile_no;
        this.address = address;
        this.your_voting_address = your_voting_address;
    }

    public String getVoter_no() {
        return voter_no;
    }

    public void setVoter_no(String voter_no) {
        this.voter_no = voter_no;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getYour_voting_address() {
        return your_voting_address;
    }

    public void setYour_voting_address(String your_voting_address) {
        this.your_voting_address = your_voting_address;
    }
}
