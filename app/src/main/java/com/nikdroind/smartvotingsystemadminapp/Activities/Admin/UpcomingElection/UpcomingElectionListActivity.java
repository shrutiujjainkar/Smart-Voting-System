package com.nikdroind.smartvotingsystemadminapp.Activities.Admin.UpcomingElection;

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
import com.nikdroind.smartvotingsystemadminapp.AdapterClass.UpcomingElectionAdapter;
import com.nikdroind.smartvotingsystemadminapp.Comman.Config;
import com.nikdroind.smartvotingsystemadminapp.HomeActivity;
import com.nikdroind.smartvotingsystemadminapp.Pojoclass.pojo_Upcoming_Election;
import com.nikdroind.smartvotingsystemadminapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class UpcomingElectionListActivity extends AppCompatActivity {

    List<pojo_Upcoming_Election> list;
    UpcomingElectionAdapter upcoming_electionAdapter;
    ListView lv_upcoming_election;
    ProgressBar progress;
    TextView tv_no_record;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    FloatingActionButton fab_add_upcoming_election;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_election_list);

        setTitle("Upcoming Election");
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        list = new ArrayList<>();
        lv_upcoming_election = findViewById(R.id.lv_upcoming_election);
        progress = findViewById(R.id.progress);
        tv_no_record = findViewById(R.id.tv_no_record);
        fab_add_upcoming_election = findViewById(R.id.fab_add_upcoming_election);

        fab_add_upcoming_election.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpcomingElectionListActivity.this,AddUpcomingElectionActivity.class));
                finish();
            }
        });

        getUpcomingElection();
    }

    private void getUpcomingElection() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        client.post(Config.url_upcoming_election,params,new JsonHttpResponseHandler()
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
                    JSONArray jsonArray = response.getJSONArray("getUpcomingElection");
                    for (int i=0; i<jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String election_type = jsonObject.getString("election_type");
                        String election_state = jsonObject.getString("election_state");
                        String total_seat_title = jsonObject.getString("total_seat_title");
                        String total_seat_no = jsonObject.getString("total_seat_no");
                        String date_of_polling_title = jsonObject.getString("date_of_polling_title");
                        String phase1_title = jsonObject.getString("phase1_title");
                        String phase1_date = jsonObject.getString("phase1_date");
                        String phase2_title = jsonObject.getString("phase2_title");
                        String phase2_date = jsonObject.getString("phase2_date");
                        String phase3_title = jsonObject.getString("phase3_title");
                        String phase3_date = jsonObject.getString("phase3_date");
                        String phase4_title = jsonObject.getString("phase4_title");
                        String phase4_date = jsonObject.getString("phase4_date");
                        String counting_date_title = jsonObject.getString("counting_date_title");
                        String counting_date = jsonObject.getString("counting_date");

                        list.add(new pojo_Upcoming_Election(election_type,election_state,total_seat_title,total_seat_no,
                                date_of_polling_title,phase1_title,phase1_date,phase2_title,phase2_date,phase3_title,phase3_date,
                                phase4_title,phase4_date,counting_date_title,counting_date));
                    }

                    upcoming_electionAdapter = new UpcomingElectionAdapter(list,UpcomingElectionListActivity.this,tv_no_record);
                    lv_upcoming_election.setAdapter(upcoming_electionAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(UpcomingElectionListActivity.this, "Could Not Connect", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(UpcomingElectionListActivity.this, HomeActivity.class));
        finish();
    }
}
