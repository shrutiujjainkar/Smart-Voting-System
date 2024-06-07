package com.nikdroind.smartvotingsystemadminapp.Pojoclass;

public class pojo_Current_Election_Type_Constituency {

    String election_type,election_constituency,election_date;

    public pojo_Current_Election_Type_Constituency(String election_type, String election_constituency, String election_date)
    {
        this.election_type = election_type;
        this.election_constituency = election_constituency;
        this.election_date = election_date;
    }

    public String getElection_type() {
        return election_type;
    }

    public void setElection_type(String election_type) {
        this.election_type = election_type;
    }

    public String getElection_constituency() {
        return election_constituency;
    }

    public void setElection_constituency(String election_constituency) {
        this.election_constituency = election_constituency;
    }

    public String getElection_date() {
        return election_date;
    }

    public void setElection_date(String election_date) {
        this.election_date = election_date;
    }
}
