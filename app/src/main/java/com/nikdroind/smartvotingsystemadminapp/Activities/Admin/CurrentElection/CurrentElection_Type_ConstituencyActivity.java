package com.nikdroind.smartvotingsystemadminapp.Activities.Admin.CurrentElection;

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
import com.nikdroind.smartvotingsystemadminapp.AdapterClass.CurrentElectionTypeConstituencyAdapter;
import com.nikdroind.smartvotingsystemadminapp.Comman.Config;
import com.nikdroind.smartvotingsystemadminapp.HomeActivity;
import com.nikdroind.smartvotingsystemadminapp.Pojoclass.pojo_Current_Election_Type_Constituency;
import com.nikdroind.smartvotingsystemadminapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class CurrentElection_Type_ConstituencyActivity extends AppCompatActivity {

    List<pojo_Current_Election_Type_Constituency> list;
    CurrentElectionTypeConstituencyAdapter current_electionAdapter;
    ListView lv_current_eletion_type_condtituncy;
    ProgressBar progress;
    TextView tv_no_record;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    FloatingActionButton fab_add_current_election_type_constituency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_election__type__constituency);

        setTitle("Current Election");
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        fab_add_current_election_type_constituency = findViewById(R.id.fab_add_current_election_type_constituency);
        list = new ArrayList<>();
        lv_current_eletion_type_condtituncy = findViewById(R.id.lv_current_election_type_constituency);
        progress = findViewById(R.id.progress);
        tv_no_record = findViewById(R.id.tv_no_record);

        fab_add_current_election_type_constituency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CurrentElection_Type_ConstituencyActivity.this, Add_Current_Election_Type_and_ConstituencyScreen.class));
                finish();
            }
        });

        getCurrentElection();
    }

    private void getCurrentElection() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        client.post(Config.url_current_election,params,new JsonHttpResponseHandler()
        {
            @Override
            public void onStart() {
                progress.setVisibility(View.VISIBLE);
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    progress.setVisibility(View.GONE);
                    JSONArray jsonArray = response.getJSONArray("getCurrentElectionTypeConstituency");
                    for (int i=0; i<jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String election_type = jsonObject.getString("current_election_type");
                        String election_constituency = jsonObject.getString("current_election_constituency");
                        String election_date = jsonObject.getString("current_election_date");

                        list.add(new pojo_Current_Election_Type_Constituency(election_type,election_constituency,election_date));
                    }

                    current_electionAdapter = new CurrentElectionTypeConstituencyAdapter(list,CurrentElection_Type_ConstituencyActivity.this,tv_no_record);
                    lv_current_eletion_type_condtituncy.setAdapter(current_electionAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(CurrentElection_Type_ConstituencyActivity.this, "Could Not Connect", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CurrentElection_Type_ConstituencyActivity.this, HomeActivity.class));
        finish();
    }
}
