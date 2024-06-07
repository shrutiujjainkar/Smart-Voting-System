package com.nikdroind.smartvotingsystemadminapp.Activities.Admin.CandidateList;

import androidx.appcompat.app.AppCompatActivity;

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
import com.nikdroind.smartvotingsystemadminapp.AdapterClass.CandidateElectionTypeStateAdapter;
import com.nikdroind.smartvotingsystemadminapp.Comman.Config;
import com.nikdroind.smartvotingsystemadminapp.HomeActivity;
import com.nikdroind.smartvotingsystemadminapp.Pojoclass.pojo_Candidate_Election_Type_State;
import com.nikdroind.smartvotingsystemadminapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class Candidate_List_Elections_Type_ConstituencyScreen extends AppCompatActivity {

    List<pojo_Candidate_Election_Type_State> list;
    CandidateElectionTypeStateAdapter adapter;
    ListView lv_candidate_election_type_state;
    ProgressBar progress;
    TextView tv_no_record;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    FloatingActionButton fab_add_candidate_list_election_type_and_constituency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate__election__type__state);

        setTitle("Elections");
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        fab_add_candidate_list_election_type_and_constituency = findViewById(R.id.fab_add_candidate_list_election_type_and_constituency);
        list = new ArrayList<>();
        lv_candidate_election_type_state = findViewById(R.id.lv_candidate_election_type_constituency);
        progress = findViewById(R.id.progress);
        tv_no_record = findViewById(R.id.tv_no_record);

        fab_add_candidate_list_election_type_and_constituency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Candidate_List_Elections_Type_ConstituencyScreen.this, Add_Candidate_List_Election_Type_and_ConstituencyScreen.class));
                finish();
            }
        });

        getCandidateElectionTypeState();

    }

    private void getCandidateElectionTypeState() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        client.post(Config.url_candidate_election_type_constituency, params,new JsonHttpResponseHandler()
        {
            @Override
            public void onStart() {
                progress.setVisibility(View.VISIBLE);
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                progress.setVisibility(View.GONE);
                try {
                    JSONArray jsonArray = response.getJSONArray("getCandidateElectionTypeState");
                    for (int i = 0; i<jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String Candidate_Election_Type = jsonObject.getString("candidate_election_type");
                        String Candidate_Election_State = jsonObject.getString("candidate_election_state");
                        editor.putString("election_state", Candidate_Election_State);
                        list.add(new pojo_Candidate_Election_Type_State(Candidate_Election_Type,Candidate_Election_State));
                    }

                    adapter = new CandidateElectionTypeStateAdapter(list, Candidate_List_Elections_Type_ConstituencyScreen.this,tv_no_record);
                    lv_candidate_election_type_state.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                Toast.makeText(Candidate_List_Elections_Type_ConstituencyScreen.this, "Could Not Connect", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Candidate_List_Elections_Type_ConstituencyScreen.this, HomeActivity.class));
        finish();
    }
}