package com.nikdroind.smartvotingsystemadminapp.Pojoclass;

public class pojo_Result_List {

    String election_type_name, election_place_name, election_seats, election_majority;

    public pojo_Result_List(String election_type_name, String election_place_name, String election_seats, String election_majority) {
        this.election_type_name = election_type_name;
        this.election_place_name = election_place_name;
        this.election_seats = election_seats;
        this.election_majority = election_majority;
    }

    public String getElection_type_name() {
        return election_type_name;
    }

    public void setElection_type_name(String election_type_name) {
        this.election_type_name = election_type_name;
    }

    public String getElection_place_name() {
        return election_place_name;
    }

    public void setElection_place_name(String election_place_name) {
        this.election_place_name = election_place_name;
    }

    public String getElection_seats() {
        return election_seats;
    }

    public void setElection_seats(String election_seats) {
        this.election_seats = election_seats;
    }

    public String getElection_majority() {
        return election_majority;
    }

    public void setElection_majority(String election_majority) {
        this.election_majority = election_majority;
    }
}