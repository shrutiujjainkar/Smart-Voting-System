package com.nikdroind.smartvotingsystemadminapp.Pojoclass;

public class pojo_Candidate_List_By_Candidate_Election_State {

    public String candidate_name,candidate_father_husband,candidate_party,candidate_age,candidate_gender,candidate_address,
    candidate_applied_state,candidate_applied_constituency;
    public String image;

    public pojo_Candidate_List_By_Candidate_Election_State(String candidate_name, String candidate_father_husband, String candidate_party, String candidate_age, String candidate_gender, String candidate_address, String candidate_applied_state, String candidate_applied_constituency, String image) {

        this.candidate_name = candidate_name;
        this.candidate_father_husband = candidate_father_husband;
        this.candidate_party = candidate_party;
        this.candidate_age = candidate_age;
        this.candidate_gender = candidate_gender;
        this.candidate_address = candidate_address;
        this.candidate_applied_state = candidate_applied_state;
        this.candidate_applied_constituency = candidate_applied_constituency;
        this.image = image;
    }

    public String getCandidate_name() {
        return candidate_name;
    }

    public void setCandidate_name(String candidate_name) {
        this.candidate_name = candidate_name;
    }

    public String getCandidate_father_husband() {
        return candidate_father_husband;
    }

    public void setCandidate_father_husband(String candidate_father_husband) {
        this.candidate_father_husband = candidate_father_husband;
    }

    public String getCandidate_party() {
        return candidate_party;
    }

    public void setCandidate_party(String candidate_party) {
        this.candidate_party = candidate_party;
    }

    public String getCandidate_age() {
        return candidate_age;
    }

    public void setCandidate_age(String candidate_age) {
        this.candidate_age = candidate_age;
    }

    public String getCandidate_gender() {
        return candidate_gender;
    }

    public void setCandidate_gender(String candidate_gender) {
        this.candidate_gender = candidate_gender;
    }

    public String getCandidate_address() {
        return candidate_address;
    }

    public void setCandidate_address(String candidate_address) {
        this.candidate_address = candidate_address;
    }

    public String getCandidate_applied_state() {
        return candidate_applied_state;
    }

    public void setCandidate_applied_state(String candidate_applied_state) {
        this.candidate_applied_state = candidate_applied_state;
    }

    public String getCandidate_applied_constituency() {
        return candidate_applied_constituency;
    }

    public void setCandidate_applied_constituency(String candidate_applied_constituency) {
        this.candidate_applied_constituency = candidate_applied_constituency;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
