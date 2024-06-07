package com.nikdroind.smartvotingsystemadminapp.Activities.Admin.TypeofElection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nikdroind.smartvotingsystemadminapp.Comman.Config;
import com.nikdroind.smartvotingsystemadminapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class AddTypeofElectionActivity extends AppCompatActivity {


    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    ProgressBar progress;
    EditText ed_election_type_title,ed_election_type_type1,ed_election_type_type2,ed_election_type_type3,ed_election_type_type4;
    Button btn_add_election_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_typeof_election);

        setTitle("Add Election Type");

        init();
    }

    private void init() {

        progress = findViewById(R.id.progress);
        ed_election_type_title = findViewById(R.id.ed_election_type_title);
        ed_election_type_type1 = findViewById(R.id.ed_election_type_type1);
        ed_election_type_type2 = findViewById(R.id.ed_election_type_type2);
        ed_election_type_type3 = findViewById(R.id.ed_election_type_type3);
        ed_election_type_type4 = findViewById(R.id.ed_election_type_type4);
        btn_add_election_type = findViewById(R.id.btn_election_type);

        btn_add_election_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ed_election_type_title.getText().toString().isEmpty())
                {
                    ed_election_type_title.setError("Please Enter Election Title");
                }
                else if (ed_election_type_type1.getText().toString().isEmpty())
                {
                    ed_election_type_type1.setError("Please Enter Election Type1");
                }

                else if(ed_election_type_type2.getText().toString().isEmpty())
                {
                    ed_election_type_type2.setError("Please Enter Election Type2");
                }

                else if (ed_election_type_type3.getText().toString().isEmpty())
                {
                    ed_election_type_type3.setError("Please Enter Election Type3");
                }

                else if (ed_election_type_type4.getText().toString().isEmpty())
                {
                    ed_election_type_type4.setError("Please Enter Election Type4");
                }

                else {
                    addElectionType();
                }
            }
        });
    }

    private void addElectionType() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("election_type_title",ed_election_type_title.getText().toString());
        params.put("election_type_type1",ed_election_type_type1.getText().toString());
        params.put("election_type_type2",ed_election_type_type2.getText().toString());
        params.put("election_type_type3",ed_election_type_type3.getText().toString());
        params.put("election_type_type4",ed_election_type_type4.getText().toString());

        client.post(Config.url_add_election_types,params,new JsonHttpResponseHandler() {
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
                        progress.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progress.setVisibility(View.GONE);
                                Toast.makeText(AddTypeofElectionActivity.this, "Election Types Added Succesfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AddTypeofElectionActivity.this,TypeofElectionActivity.class));
                                finish();
                            }
                        },2000);

                    }
                    else
                    {
                        Toast.makeText(AddTypeofElectionActivity.this, "Already Added", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(AddTypeofElectionActivity.this, "Could not Connect", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AddTypeofElectionActivity.this, TypeofElectionActivity.class));
        finish();
    }
}
