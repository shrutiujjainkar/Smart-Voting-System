

package com.nikdroind.smartvotingsystemadminapp.Activities.Admin.Result;

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
import com.nikdroind.smartvotingsystemadminapp.Activities.Admin.CurrentElection.Add_Current_Election_Type_and_ConstituencyScreen;
import com.nikdroind.smartvotingsystemadminapp.Activities.Admin.CurrentElection.CurrentElection_Type_ConstituencyActivity;
import com.nikdroind.smartvotingsystemadminapp.Comman.Config;
import com.nikdroind.smartvotingsystemadminapp.HomeActivity;
import com.nikdroind.smartvotingsystemadminapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Add_Result_ListScreen extends AppCompatActivity {

    EditText edt_eletion_type_name,edt_election_constituency_place,edt_election_constituency_total_seats,edt_election_constituency_total_seats_majority;
    Button btn_result_list_by_constituency_place;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__result__list_screen);

        setTitle("Add Result List By Constituency");
        init();
    }

    private void init() {

        edt_eletion_type_name = findViewById(R.id.edt_add_result_list_election_type_name);
        edt_election_constituency_place = findViewById(R.id.edt_add_result_list_election_constituency_place);
        edt_election_constituency_total_seats = findViewById(R.id.edt_add_result_list_election_constituency_total_seats);
        edt_election_constituency_total_seats_majority = findViewById(R.id.edt_add_result_list_election_constituency_total_seats_majority);
        btn_result_list_by_constituency_place = findViewById(R.id.btn_add_result_list_by_constituency_place);
        progress = findViewById(R.id.progress);

        btn_result_list_by_constituency_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });
    }

    private void validation() {

        if (edt_eletion_type_name.getText().toString().isEmpty()) {
            edt_eletion_type_name.setError("Please Enter Election Name");
        } else if (edt_election_constituency_place.getText().toString().isEmpty()) {
            edt_election_constituency_place.setError("Please Enter Election Constituency Place");
        } else if (edt_election_constituency_total_seats.getText().toString().isEmpty()) {
            edt_election_constituency_total_seats.setError("Please Enter Election Constituency Seats");
        } else if (edt_election_constituency_total_seats_majority.getText().toString().isEmpty()) {
            edt_election_constituency_total_seats_majority.setError("Please Enter Election Constituency Seats Majority");
        } else {
            add_Result_List_By_Constituency();
        }
    }

    private void add_Result_List_By_Constituency() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("constituency_election_name",edt_eletion_type_name.getText().toString());
        params.put("constituency_election_place",edt_election_constituency_place.getText().toString());
        params.put("constituency_election_total_seats",edt_election_constituency_total_seats.getText().toString());
        params.put("constituency_election_total_seats_majority",edt_election_constituency_total_seats_majority.getText().toString());


        client.post(Config.url_add_result_lis_by_constituency,params,new JsonHttpResponseHandler() {
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

                        Toast.makeText(Add_Result_ListScreen.this, "Election Result List Type and Constituency Added Succesfully Done", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Add_Result_ListScreen.this, Result_ListScreen.class));
                        finish();
                    }
                    else
                    {
                        Toast.makeText(Add_Result_ListScreen.this, "Already Added", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(Add_Result_ListScreen.this, "Could not Connect", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(Add_Result_ListScreen.this, HomeActivity.class));
        finish();
    }


}