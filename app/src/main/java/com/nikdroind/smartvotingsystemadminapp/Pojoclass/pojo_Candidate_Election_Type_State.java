package com.nikdroind.smartvotingsystemadminapp.Pojoclass;

public class pojo_Candidate_Election_Type_State {

    private String candidate_election_type,candidate_election_state;

    public pojo_Candidate_Election_Type_State(String candidate_election_type, String candidate_election_state) {
        this.candidate_election_type = candidate_election_type;
        this.candidate_election_state = candidate_election_state;
    }

    public String getCandidate_election_type() {
        return candidate_election_type;
    }

    public void setCandidate_election_type(String candidate_election_type) {
        this.candidate_election_type = candidate_election_type;
    }

    public String getCandidate_election_state() {
        return candidate_election_state;
    }

    public void setCandidate_election_state(String candidate_election_state) {
        this.candidate_election_state = candidate_election_state;
    }
}
