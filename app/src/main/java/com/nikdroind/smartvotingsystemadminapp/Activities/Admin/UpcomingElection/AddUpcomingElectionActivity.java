package com.nikdroind.smartvotingsystemadminapp.Activities.Admin.UpcomingElection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.nikdroind.smartvotingsystemadminapp.Activities.Admin.TypeofElection.AddTypeofElectionActivity;
import com.nikdroind.smartvotingsystemadminapp.Activities.Admin.TypeofElection.TypeofElectionActivity;
import com.nikdroind.smartvotingsystemadminapp.Comman.Config;
import com.nikdroind.smartvotingsystemadminapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class AddUpcomingElectionActivity extends AppCompatActivity {

    EditText edt_election_type,edt_election_state,edt_election_total_seat,edt_election_total_seat_no,edt_election_date_of_polling,
            edt_election_phase1_title,edt_election_phase1_date,edt_election_phase2_title,edt_election_phase2_date,
            edt_election_phase3_title,edt_election_phase3_date,edt_election_phase4_title,edt_election_phase4_date,
            edt_election_counting_date_title_heading1,edt_election_counting_date;

    Button btn_add_upcoming_election;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upcoming_election);

        init();
    }

    private void init() {

        edt_election_type = findViewById(R.id.edt_add_upcoming_election_election_type);
        edt_election_state = findViewById(R.id.edt_add_upcoming_election_election_state);
        edt_election_total_seat = findViewById(R.id.edt_add_upcoming_election_total_seat);
        edt_election_total_seat_no = findViewById(R.id.edt_add_upcoming_election_total_seat_no);
        edt_election_date_of_polling = findViewById(R.id.edt_add_upcoming_election_date_of_polling);
        edt_election_phase1_title = findViewById(R.id.edt_add_upcoming_election_phase1_title);
        edt_election_phase1_date = findViewById(R.id.edt_add_upcoming_election_phase1_date);
        edt_election_phase2_title = findViewById(R.id.edt_add_upcoming_election_phase2_title);
        edt_election_phase2_date = findViewById(R.id.edt_add_upcoming_election_phase2_date);
        edt_election_phase3_title = findViewById(R.id.edt_add_upcoming_election_phase3_title);
        edt_election_phase3_date = findViewById(R.id.edt_add_upcoming_election_phase3_date);
        edt_election_phase4_title = findViewById(R.id.edt_add_upcoming_election_phase4_title);
        edt_election_phase4_date = findViewById(R.id.edt_add_upcoming_election_phase4_date);
        edt_election_counting_date_title_heading1 = findViewById(R.id.edt_add_upcoming_election_counting_date_title_heading1);
        edt_election_counting_date = findViewById(R.id.edt_add_upcoming_election_counting_date);

        btn_add_upcoming_election = findViewById(R.id.btn_add_upcoming_election);
        progress = findViewById(R.id.progress);

        btn_add_upcoming_election.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });
    }

    private void validation() {

        if(edt_election_type.getText().toString().isEmpty())
        {
            edt_election_type.setError("Please Enter Election Type");
        }
        else if (edt_election_state.getText().toString().isEmpty())
        {
            edt_election_state.setError("Please Enter Election State");
        }

        else if(edt_election_total_seat.getText().toString().isEmpty())
        {
            edt_election_total_seat.setError("Please Enter Election Total Seat");
        }

        else if (edt_election_total_seat_no.getText().toString().isEmpty())
        {
            edt_election_total_seat_no.setError("Please Enter Election Total Seat No");
        }

        else if (edt_election_phase1_title.getText().toString().isEmpty())
        {
            edt_election_phase1_title.setError("Please Enter Election Phase 1 Title");
        }
        else if (edt_election_phase1_date.getText().toString().isEmpty())
        {
            edt_election_phase1_date.setError("Please Enter Election Phase 1 Date");
        }

        else if (edt_election_phase2_title.getText().toString().isEmpty())
        {
            edt_election_phase2_title.setError("Please Enter Election Phase 2 Title");
        }
        else if (edt_election_phase2_date.getText().toString().isEmpty())
        {
            edt_election_phase2_date.setError("Please Enter Election Phase 2 Date");
        }

        else if (edt_election_counting_date_title_heading1.getText().toString().isEmpty())
        {
            edt_election_counting_date_title_heading1.setError("Please Enter Election Counting Date Title");
        }
        else if (edt_election_counting_date.getText().toString().isEmpty())
        {
            edt_election_counting_date.setError("Please Enter Election Counting Date");
        }
        else
        {
            addUpcomingElection();
        }

    }

    private void addUpcomingElection() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("upcoming_election_type",edt_election_type.getText().toString());
        params.put("upcoming_election_state",edt_election_state.getText().toString());
        params.put("upcoming_election_total_seat_name",edt_election_total_seat.getText().toString());
        params.put("upcoming_election_total_seat_no",edt_election_total_seat_no.getText().toString());
        params.put("upcoming_election_date_of_polling",edt_election_date_of_polling.getText().toString());
        params.put("upcoming_election_phase1_title",edt_election_phase1_title.getText().toString());
        params.put("upcoming_election_phase1_date",edt_election_phase1_date.getText().toString());
        params.put("upcoming_election_phase2_title",edt_election_phase2_title.getText().toString());
        params.put("upcoming_election_phase2_date",edt_election_phase2_date.getText().toString());
        params.put("upcoming_election_phase3_title",edt_election_phase3_title.getText().toString());
        params.put("upcoming_election_phase3_date",edt_election_phase3_date.getText().toString());
        params.put("upcoming_election_phase4_title",edt_election_phase4_title.getText().toString());
        params.put("upcoming_election_phase4_date",edt_election_phase4_date.getText().toString());
        params.put("upcoming_election_counting_date_title",edt_election_counting_date_title_heading1.getText().toString());
        params.put("upcoming_election_counting_date",edt_election_counting_date.getText().toString());

        client.post(Config.url_add_upcoming_election,params,new JsonHttpResponseHandler() {
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
                                Toast.makeText(AddUpcomingElectionActivity.this, "Upcoming Election Added Succesfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AddUpcomingElectionActivity.this, UpcomingElectionListActivity.class));
                                finish();
                            }
                        },2000);

                    }
                    else
                    {
                        Toast.makeText(AddUpcomingElectionActivity.this, "Already Added", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(AddUpcomingElectionActivity.this, "Could not Connect", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AddUpcomingElectionActivity.this,UpcomingElectionListActivity.class));
        finish();
    }
}
