package com.nikdroind.smartvotingsystemadminapp.Comman;

public class Config {

    private static String OnlineAddress = "http://192.168.195.161:80/smart_voting_system/adminapp/";
    public static String OnlineImageAddress="http://192.168.195.161:80/smart_voting_system/adminapp/upload/";

    public static String url_login = OnlineAddress+ "loginUser.php";
    public static String url_add_election_types = OnlineAddress+ "addElectionTypes.php";
    public static String url_type_of_election = OnlineAddress+ "gettypeofelection.php";
    public static String url_get_all_voters = OnlineAddress+ "getAllVoters.php";
    public static String url_search_by_full_name = OnlineAddress+ "SearchByFullName.php";

    public static String url_current_election = OnlineAddress+ "getcurrent_election_type_constituency.php";
    public static String url_current_election_candidate_list = OnlineAddress+ "getcurrent_election_candidate_list.php";
    public static String url_add_current_election_type_constituency = OnlineAddress+ "addelection_type_constituency.php";
    public static String url_add_current_election_candidate_image = OnlineAddress+ "addcurrent_election_candidate_image.php";
    public static String url_add_current_election_candidate = OnlineAddress+ "addcurrent_election_candidate.php";

    public static String url_upcoming_election = OnlineAddress+ "getupcomingelection.php";
    public static String url_add_upcoming_election = OnlineAddress+ "addupcomingelection.php";

    public static String url_candidate_election_type_constituency = OnlineAddress+ "getcandidate_list_election_type_constituency.php";
    public static String url_candidate_list = OnlineAddress+ "getcandidate_list_by_candidate_election_constituency.php";
    public static String url_add_candidate_list_election_type_constituency = OnlineAddress+ "addcandidate_list_election_type_constituency.php";
    public static String url_add_candidate_list_election_candidate_image = OnlineAddress+ "add_candidate_list_election_candidate_image.php";
    public static String url_add_candidate_list_election_candidate = OnlineAddress+ "addcandidate_list_election_candidate.php";
    public static String url_result_list = OnlineAddress+ "getresult_list.php";
    public static String url_result_single = OnlineAddress+ "getpartywisereport.php";
    public static String url_add_result_lis_by_constituency = OnlineAddress+ "addresultlistbyconstituency.php";
    public static String url_add_result_by_election_constituency = OnlineAddress+ "addsingleresultbyconstituency.php";

    public static String url_get_feedback_list = OnlineAddress+ "getfeedback_list.php";
    public static String url_get_total_vote_by_candidate = OnlineAddress+ "gettotalvotesofcandidate.php";

}
