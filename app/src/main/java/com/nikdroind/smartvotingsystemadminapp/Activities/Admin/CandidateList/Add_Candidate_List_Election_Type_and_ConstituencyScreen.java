package com.nikdroind.smartvotingsystemadminapp.Activities.Admin.CandidateList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nikdroind.smartvotingsystemadminapp.Activities.Admin.CurrentElection.CurrentElection_Type_ConstituencyActivity;
import com.nikdroind.smartvotingsystemadminapp.Comman.Config;
import com.nikdroind.smartvotingsystemadminapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Add_Candidate_List_Election_Type_and_ConstituencyScreen extends AppCompatActivity {

    EditText edt_add_candidate_list_election_type,edt_add_candidate_list_election_constituency;
    Button btn_add_candidate_list_election_type_constituency;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__candidate__list__election__type_and__state_screen);

        setTitle("Add Election Type/Constituency");
        init();
    }

    private void init() {

        edt_add_candidate_list_election_type = findViewById(R.id.edt_add_candidate_list_election_type);
        edt_add_candidate_list_election_constituency = findViewById(R.id.edt_add_candidate_list_election_constituency);
        btn_add_candidate_list_election_type_constituency = findViewById(R.id.btn_add_candidate_list_election_type_constituency);
        progress = findViewById(R.id.progress);

        btn_add_candidate_list_election_type_constituency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });


    }

    private void validation() {

        if(edt_add_candidate_list_election_type.getText().toString().isEmpty())
        {
            edt_add_candidate_list_election_type.setError("Please Enter Election Type");
        }
        else if(edt_add_candidate_list_election_constituency.getText().toString().isEmpty())
        {
            edt_add_candidate_list_election_constituency.setError("Please Enter Election Constituency");
        }
        else
        {
            add_Candidate_List_Election_Type_and_ConstituencyConstituency();
        }
    }

    private void add_Candidate_List_Election_Type_and_ConstituencyConstituency() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("candidate_election_type",edt_add_candidate_list_election_type.getText().toString());
        params.put("candidate_election_constituency",edt_add_candidate_list_election_constituency.getText().toString());


        client.post(Config.url_add_candidate_list_election_type_constituency,params,new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                progress.setVisibility(View.VISIBLE);
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                progress.setVisibility(View.GONE);
                try {
                    String aa = response.getString("success");

                    if (aa.equals("1"))
                    {

                        Toast.makeText(Add_Candidate_List_Election_Type_and_ConstituencyScreen.this, "Election Type and Constituency Added Succesfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Add_Candidate_List_Election_Type_and_ConstituencyScreen.this, Candidate_List_Elections_Type_ConstituencyScreen.class));
                        finish();
                    }
                    else
                    {
                        Toast.makeText(Add_Candidate_List_Election_Type_and_ConstituencyScreen.this, "Already Added", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(Add_Candidate_List_Election_Type_and_ConstituencyScreen.this, "Could not Connect", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(Add_Candidate_List_Election_Type_and_ConstituencyScreen.this,Candidate_List_Elections_Type_ConstituencyScreen.class));
        finish();
    }
}