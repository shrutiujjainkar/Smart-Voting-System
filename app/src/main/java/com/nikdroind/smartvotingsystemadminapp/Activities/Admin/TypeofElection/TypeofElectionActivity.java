package com.nikdroind.smartvotingsystemadminapp.Activities.Admin.TypeofElection;

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
import com.nikdroind.smartvotingsystemadminapp.AdapterClass.TypeofElectionAdapter;
import com.nikdroind.smartvotingsystemadminapp.Comman.Config;
import com.nikdroind.smartvotingsystemadminapp.HomeActivity;
import com.nikdroind.smartvotingsystemadminapp.Pojoclass.pojo_Type_of_Election;
import com.nikdroind.smartvotingsystemadminapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class TypeofElectionActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    ProgressBar progress;
    FloatingActionButton fab_add_type_of_election;

    List<pojo_Type_of_Election> list;
    TypeofElectionAdapter type_of_electionAdapter;
    ListView lv_type_of_election;
    TextView tv_no_record;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typeof_election);

        setTitle("Election Type");

        init();



    }

    private void init() {


        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        list = new ArrayList<>();
        lv_type_of_election = findViewById(R.id.lv_type_of_election);
        progress = findViewById(R.id.progress);
        tv_no_record = findViewById(R.id.tv_no_record);

        getType_of_Election();
        fab_add_type_of_election = findViewById(R.id.fab_add_election_type);

        fab_add_type_of_election.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TypeofElectionActivity.this,AddTypeofElectionActivity.class));
                finish();
            }
        });
    }

    private void getType_of_Election() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        client.post(Config.url_type_of_election,params,new JsonHttpResponseHandler() {

            public void onStart()
            {
                progress.setVisibility(View.VISIBLE);
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    progress.setVisibility(View.GONE);
                    JSONArray jsonArray = response.getJSONArray("getTypeofElection");
                    for (int i = 0; i<jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String title = jsonObject.getString("title");
                        String type1 = jsonObject.getString("type1");
                        String type2 = jsonObject.getString("type2");
                        String type3 = jsonObject.getString("type3");
                        String type4 = jsonObject.getString("type4");

                        list.add(new pojo_Type_of_Election(title,type1,type2,type3,type4));
                    }
                    type_of_electionAdapter = new TypeofElectionAdapter(list,TypeofElectionActivity.this,tv_no_record);
                    lv_type_of_election.setAdapter(type_of_electionAdapter);

                } catch (JSONException e) {

                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(TypeofElectionActivity.this, "Could not Connect", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(TypeofElectionActivity.this, HomeActivity.class));
        finish();
    }
}
