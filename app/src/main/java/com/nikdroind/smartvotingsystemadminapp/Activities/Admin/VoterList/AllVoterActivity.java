package com.nikdroind.smartvotingsystemadminapp.Activities.Admin.VoterList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nikdroind.smartvotingsystemadminapp.Activities.Admin.CandidateList.Candidate_List_By_Candidate_Election_Type_and_ConstituencyScreen;
import com.nikdroind.smartvotingsystemadminapp.Activities.Admin.TypeofElection.TypeofElectionActivity;
import com.nikdroind.smartvotingsystemadminapp.AdapterClass.CandidateListByCandidateElectionStateAdapter;
import com.nikdroind.smartvotingsystemadminapp.AdapterClass.TypeofElectionAdapter;
import com.nikdroind.smartvotingsystemadminapp.AdapterClass.ViewAllVoterAdapter;
import com.nikdroind.smartvotingsystemadminapp.Comman.Config;
import com.nikdroind.smartvotingsystemadminapp.HomeActivity;
import com.nikdroind.smartvotingsystemadminapp.Pojoclass.pojo_Candidate_List_By_Candidate_Election_State;
import com.nikdroind.smartvotingsystemadminapp.Pojoclass.pojo_Type_of_Election;
import com.nikdroind.smartvotingsystemadminapp.Pojoclass.pojo_View_All_Voters;
import com.nikdroind.smartvotingsystemadminapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class AllVoterActivity extends AppCompatActivity {


    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    SearchView searchView_voter_by_name_or_voter_id;
    ProgressBar progress;
    List<pojo_View_All_Voters> list;
    ViewAllVoterAdapter viewAllVoterAdapter;
    ListView lv_view_all_voter;
    TextView tv_no_record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_voter);

        setTitle("All Voters");

        init();

    }

    private void init() {

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        searchView_voter_by_name_or_voter_id = findViewById(R.id.searchview_voter_by_name_or_voter_id);
        list = new ArrayList<>();
        lv_view_all_voter = findViewById(R.id.lv_view_all_voter);
        progress = findViewById(R.id.progress);
        tv_no_record = findViewById(R.id.tv_no_record);

        searchView_voter_by_name_or_voter_id.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchVoter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchVoter(newText);
                return false;
            }
        });

        getViewAllVoter();
    }

    private void searchVoter(String query) {

        List<pojo_View_All_Voters> tempcenterlist = new ArrayList();
        tempcenterlist.clear();

        for (pojo_View_All_Voters d : list) {
            if (d.getVoter_no().toUpperCase().contains(query.toUpperCase()) || d.getFull_name().toUpperCase().contains(query
                    .toUpperCase()))
                tempcenterlist.add(d);
        }

        viewAllVoterAdapter = new ViewAllVoterAdapter(tempcenterlist, AllVoterActivity.this,tv_no_record);
        lv_view_all_voter.setAdapter(viewAllVoterAdapter);
    }

    private void getViewAllVoter() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        client.post(Config.url_get_all_voters,params,new JsonHttpResponseHandler() {

            public void onStart()
            {
                progress.setVisibility(View.VISIBLE);
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    progress.setVisibility(View.GONE);
                    JSONArray jsonArray = response.getJSONArray("getAllVoters");
                    for (int i = 0; i<jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String full_name = jsonObject.getString("full_name");
                        String voter_no = jsonObject.getString("voter_no");
                        String gender = jsonObject.getString("gender");
                        String mobile_no = jsonObject.getString("mobile_no");
                        String address = jsonObject.getString("address");
                        String your_voting_address = jsonObject.getString("voting_place");

                        list.add(new pojo_View_All_Voters(full_name,voter_no,gender,mobile_no,address,your_voting_address));
                    }
                    viewAllVoterAdapter = new ViewAllVoterAdapter(list, AllVoterActivity.this,tv_no_record);
                    lv_view_all_voter.setAdapter(viewAllVoterAdapter);

                } catch (JSONException e) {

                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(AllVoterActivity.this, "Could not Connect", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AllVoterActivity.this, HomeActivity.class));
        finish();
    }
}
