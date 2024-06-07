package com.nikdroind.smartvotingsystemadminapp.Pojoclass;

public class pojo_Upcoming_Election {

    private String election_type,election_state,total_seat_title,total_seat_no,date_of_polling_title
            ,phase1_title,phase1_date,phase2_title,phase2_date,phase3_title,phase3_date,phase4_title,phase4_date
            ,counting_date_title,counting_date;

    public pojo_Upcoming_Election(String election_type, String election_state, String total_seat_title, String total_seat_no, String date_of_polling_title, String phase1_title, String phase1_date, String phase2_title, String phase2_date, String phase3_title, String phase3_date, String phase4_title, String phase4_date, String counting_date_title, String counting_date) {
        this.election_type = election_type;
        this.election_state = election_state;
        this.total_seat_title = total_seat_title;
        this.total_seat_no = total_seat_no;
        this.date_of_polling_title = date_of_polling_title;
        this.phase1_title = phase1_title;
        this.phase1_date = phase1_date;
        this.phase2_title = phase2_title;
        this.phase2_date = phase2_date;
        this.phase3_title = phase3_title;
        this.phase3_date = phase3_date;
        this.phase4_title = phase4_title;
        this.phase4_date = phase4_date;
        this.counting_date_title = counting_date_title;
        this.counting_date = counting_date;
    }

    public String getElection_type() {
        return election_type;
    }

    public void setElection_type(String election_type) {
        this.election_type = election_type;
    }

    public String getElection_state() {
        return election_state;
    }

    public void setElection_state(String election_state) {
        this.election_state = election_state;
    }

    public String getTotal_seat_title() {
        return total_seat_title;
    }

    public void setTotal_seat_title(String total_seat_title) {
        this.total_seat_title = total_seat_title;
    }

    public String getTotal_seat_no() {
        return total_seat_no;
    }

    public void setTotal_seat_no(String total_seat_no) {
        this.total_seat_no = total_seat_no;
    }

    public String getDate_of_polling_title() {
        return date_of_polling_title;
    }

    public void setDate_of_polling_title(String date_of_polling_title) {
        this.date_of_polling_title = date_of_polling_title;
    }

    public String getPhase1_title() {
        return phase1_title;
    }

    public void setPhase1_title(String phase1_title) {
        this.phase1_title = phase1_title;
    }

    public String getPhase1_date() {
        return phase1_date;
    }

    public void setPhase1_date(String phase1_date) {
        this.phase1_date = phase1_date;
    }

    public String getPhase2_title() {
        return phase2_title;
    }

    public void setPhase2_title(String phase2_title) {
        this.phase2_title = phase2_title;
    }

    public String getPhase2_date() {
        return phase2_date;
    }

    public void setPhase2_date(String phase2_date) {
        this.phase2_date = phase2_date;
    }

    public String getPhase3_title() {
        return phase3_title;
    }

    public void setPhase3_title(String phase3_title) {
        this.phase3_title = phase3_title;
    }

    public String getPhase3_date() {
        return phase3_date;
    }

    public void setPhase3_date(String phase3_date) {
        this.phase3_date = phase3_date;
    }

    public String getPhase4_title() {
        return phase4_title;
    }

    public void setPhase4_title(String phase4_title) {
        this.phase4_title = phase4_title;
    }

    public String getPhase4_date() {
        return phase4_date;
    }

    public void setPhase4_date(String phase4_date) {
        this.phase4_date = phase4_date;
    }

    public String getCounting_date_title() {
        return counting_date_title;
    }

    public void setCounting_date_title(String counting_date_title) {
        this.counting_date_title = counting_date_title;
    }

    public String getCounting_date() {
        return counting_date;
    }

    public void setCounting_date(String counting_date) {
        this.counting_date = counting_date;
    }
}
