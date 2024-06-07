package com.nikdroind.smartvotingsystemadminapp.Activities.Admin.VoterList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nikdroind.smartvotingsystemadminapp.AdapterClass.ViewAllVoterAdapter;
import com.nikdroind.smartvotingsystemadminapp.Comman.Config;
import com.nikdroind.smartvotingsystemadminapp.HomeActivity;
import com.nikdroind.smartvotingsystemadminapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ViewVoterCardActivity extends AppCompatActivity {

    TextView tv_epic_no,tv_full_name,tv_gender,tv_mobile_no,tv_address,tv_your_voting_address;
    ImageView img_sataymev_jayete;
    ProgressBar progress;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_voter_card);

        setTitle("Election Identity Card");

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        img_sataymev_jayete = findViewById(R.id.img_satamev_jayte_logo);
        tv_epic_no = findViewById(R.id.tv_epic_no);
        tv_full_name = findViewById(R.id.tv_full_name);
        tv_gender = findViewById(R.id.tv_gender);
        tv_mobile_no = findViewById(R.id.tv_mobile_no);
        tv_address = findViewById(R.id.tv_address);
        tv_your_voting_address = findViewById(R.id.tv_your_voting_place);
        progress = findViewById(R.id.progress);

        tv_full_name.setText(getIntent().getStringExtra("full_name"));
        tv_epic_no.setText(getIntent().getStringExtra("voter_no"));
        tv_gender.setText(getIntent().getStringExtra("gender"));
        tv_mobile_no.setText(getIntent().getStringExtra("mobile_no"));
        tv_address.setText(getIntent().getStringExtra("address"));
        tv_your_voting_address.setText(getIntent().getStringExtra("your_voting_address"));


//        getMyVotingCardDetails();
    }

    private void getMyVotingCardDetails() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("search_by_full_name",preferences.getString("search_by_full_name",""));
        client.post(Config.url_search_by_full_name, params,new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                progress.setVisibility(View.VISIBLE);
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                progress.setVisibility(View.GONE);
                try {
                    JSONArray jsonArray = response.getJSONArray("getUserInformation");
                    for (int i= 0 ; i<jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        String gender = jsonObject.getString("gender");
                        String epic_no = jsonObject.getString("epic_no");
                        String mobile_no = jsonObject.getString("mobile_no");
                        String address = jsonObject.getString("address");
                        String pin_code = jsonObject.getString("pin_code");
                        String username = jsonObject.getString("username");
                        String voting_place = jsonObject.getString("voting_place");


                        tv_epic_no.setText(epic_no);
                        tv_full_name.setText(name);
                        tv_gender.setText(gender);
                        tv_mobile_no.setText(mobile_no);
                        tv_address.setText(address+" "+pin_code);
                        tv_your_voting_address.setText(voting_place);
                        if (tv_your_voting_address.getText().toString().isEmpty() || tv_your_voting_address.getText().toString().equals("null"))
                        {
                            tv_your_voting_address.setText("There is no current election");
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(ViewVoterCardActivity.this, "Could Not Connect", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ViewVoterCardActivity.this, AllVoterActivity.class));
        finish();
    }
}
