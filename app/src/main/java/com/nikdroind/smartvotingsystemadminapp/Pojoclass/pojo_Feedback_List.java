package com.nikdroind.smartvotingsystemadminapp.Pojoclass;

public class pojo_Feedback_List {

    String feedback_username,feedback_mobile_no,feedback;

    public pojo_Feedback_List(String feedback_username, String feedback_mobile_no, String feedback) {
        this.feedback_username = feedback_username;
        this.feedback_mobile_no = feedback_mobile_no;
        this.feedback = feedback;
    }

    public String getFeedback_username() {
        return feedback_username;
    }

    public void setFeedback_username(String feedback_username) {
        this.feedback_username = feedback_username;
    }

    public String getFeedback_mobile_no() {
        return feedback_mobile_no;
    }

    public void setFeedback_mobile_no(String feedback_mobile_no) {
        this.feedback_mobile_no = feedback_mobile_no;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
