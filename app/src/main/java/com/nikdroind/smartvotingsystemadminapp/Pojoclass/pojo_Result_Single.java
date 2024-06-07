package com.nikdroind.smartvotingsystemadminapp.Pojoclass;

public class pojo_Result_Single {

    String party_name,party_win,party_leading,party_total,election_place;

    public pojo_Result_Single(String party_name, String party_win, String party_leading, String party_total,String election_place) {
        this.party_name = party_name;
        this.party_win = party_win;
        this.party_leading = party_leading;
        this.party_total = party_total;
        this.election_place = election_place;
    }

    public String getParty_name() {
        return party_name;
    }

    public void setParty_name(String party_name) {
        this.party_name = party_name;
    }

    public String getParty_win() {
        return party_win;
    }

    public void setParty_win(String party_win) {
        this.party_win = party_win;
    }

    public String getParty_leading() {
        return party_leading;
    }

    public void setParty_leading(String party_leading) {
        this.party_leading = party_leading;
    }

    public String getElection_place() {
        return election_place;
    }

    public void setElection_place(String election_place) {
        this.election_place = election_place;
    }

    public String getParty_total() {
        return party_total;
    }

    public void setParty_total(String party_total) {
        this.party_total = party_total;
    }
}
