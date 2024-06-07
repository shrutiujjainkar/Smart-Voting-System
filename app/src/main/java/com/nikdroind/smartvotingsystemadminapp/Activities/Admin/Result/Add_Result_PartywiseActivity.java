package com.nikdroind.smartvotingsystemadminapp.Activities.Admin.Result;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nikdroind.smartvotingsystemadminapp.Comman.Config;
import com.nikdroind.smartvotingsystemadminapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Add_Result_PartywiseActivity extends AppCompatActivity {
    
    EditText edt_election_party_name,edt_election_party_won_seats,edt_election_party_leading_seats,
            edt_election_party_total_seats;
    TextView tv_election_type_name,tv_election_place;
    Button btn_add_result;

    ProgressBar progress;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__result__partywise);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        setTitle("Add "+ preferences.getString("election_constituency","")+ " Result");
        init();
    }

    private void init() {

        tv_election_place = findViewById(R.id.tv_add_result_partywise_election_place);
        tv_election_type_name = findViewById(R.id.txt_add_result_partywise_election_type_name);
        edt_election_party_name = findViewById(R.id.edt_add_result_partywise_election_party_name);
        edt_election_party_won_seats = findViewById(R.id.edt_add_result_partywise_election_party_won_seats);
        edt_election_party_leading_seats = findViewById(R.id.edt_add_result_partywise_election_party_leading_seats);
        edt_election_party_total_seats = findViewById(R.id.edt_add_result_partywise_election_party_total_seats);
        btn_add_result= findViewById(R.id.btn_add_result_partywise_by_constituency_place);
        progress = findViewById(R.id.progress);

        String Election_Type_Name = preferences.getString("election_type_name","");
        tv_election_type_name.setText(Election_Type_Name);
        String Election_Constituency = preferences.getString("election_constituency","");
        tv_election_place.setText(Election_Constituency);


        btn_add_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });
    }

    private void validation() {

        if (edt_election_party_name.getText().toString().isEmpty()) {
            edt_election_party_name.setError("Please Enter Election Party Name");
        } else if (edt_election_party_won_seats.getText().toString().isEmpty()) {
            edt_election_party_won_seats.setError("Please Enter Election Party Won Seats");
        } else if (edt_election_party_leading_seats.getText().toString().isEmpty()) {
            edt_election_party_leading_seats.setError("Please Enter Election Party Leading Seats");
        } else if (edt_election_party_total_seats.getText().toString().isEmpty()) {
            edt_election_party_total_seats.setError("Please Enter Election Party Total Win Seats");
        } else {
            add_Result_By_Constituency_Place();
        }
    }

    private void add_Result_By_Constituency_Place() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("result_party_name",edt_election_party_name.getText().toString());
        params.put("result_party_won_seats",edt_election_party_won_seats.getText().toString());
        params.put("result_party_total_leading_seats",edt_election_party_leading_seats.getText().toString());
        params.put("result_party_total_won_seats",edt_election_party_total_seats.getText().toString());
        params.put("result_election_place",tv_election_place.getText().toString());



        client.post(Config.url_add_result_by_election_constituency,params,new JsonHttpResponseHandler() {
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

                        Toast.makeText(Add_Result_PartywiseActivity.this, "Election Result List Type and Constituency Added Succesfully Done", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Add_Result_PartywiseActivity.this,Result_SingleScreen.class);
                        intent.putExtra("election_type_name",tv_election_type_name.getText().toString());
                        intent.putExtra("election_place_name",tv_election_place.getText().toString());
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(Add_Result_PartywiseActivity.this, "Already Added", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(Add_Result_PartywiseActivity.this, "Could not Connect", Toast.LENGTH_SHORT).show();
            }
        });


    }
}