package com.nikdroind.smartvotingsystemadminapp.Activities.Admin.CurrentElection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nikdroind.smartvotingsystemadminapp.AdapterClass.CurrentElectionTypeConstituencyAdapter;
import com.nikdroind.smartvotingsystemadminapp.Comman.Config;
import com.nikdroind.smartvotingsystemadminapp.Pojoclass.pojo_Current_Election_Type_Constituency;
import com.nikdroind.smartvotingsystemadminapp.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

public class SingelCurrentElectionCandidateActivity extends AppCompatActivity {

    TextView txt_candidate_name, txt_candidate_father_husband, txt_candidate_party, txt_candidate_age, txt_candidate_gender,
            txt_candidate_address, txt_candidate_applied_state, txt_candidate_applied_constituency;
    TextView tv_vote_timing;
    String starttime = "07:00:00";
    String endtime = "18:00:00";

    ImageView img_candidate;

    Button btn_vote_to_candidate;
    ProgressBar progress;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singel_current_election_candidate);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        txt_candidate_name = findViewById(R.id.txt_vote_to_candidate_name);
        txt_candidate_father_husband = findViewById(R.id.txt_vote_to_candidate_father_husband);
        txt_candidate_party = findViewById(R.id.txt_vote_to_candidate_party);
        txt_candidate_age = findViewById(R.id.txt_vote_to_candidate_age);
        txt_candidate_gender = findViewById(R.id.txt_vote_to_candidate_gender);
        txt_candidate_address = findViewById(R.id.txt_vote_to_candidate_address);
        txt_candidate_applied_state = findViewById(R.id.txt_vote_to_candidate_applied_state);
        txt_candidate_applied_constituency = findViewById(R.id.txt_vote_to_candidate_applied_constituency);
        tv_vote_timing = findViewById(R.id.txt_vote_timing);
        progress = findViewById(R.id.progress);

        img_candidate = findViewById(R.id.img_vote_to_candidate_profile);

        btn_vote_to_candidate = findViewById(R.id.btn_vote_to_candidate);

        Picasso.with(SingelCurrentElectionCandidateActivity.this).load(Config.OnlineImageAddress + "" + getIntent().getStringExtra("candidate_image"))
                .error(R.drawable.image_not_load).into(img_candidate);

        String str_candidate_name = getIntent().getStringExtra("candidate_name");
        String str_candidate_father_husband = getIntent().getStringExtra("candidate_father_husband");
        String str_candidate_party = getIntent().getStringExtra("candidate_party");
        String str_candidate_age = getIntent().getStringExtra("candidate_age");
        String str_candidate_gender = getIntent().getStringExtra("candidate_gender");
        String str_candidate_address = getIntent().getStringExtra("candidate_address");
        String str_candidate_applied_state = getIntent().getStringExtra("candidate_applied_state");
        String str_candidate_applied_constituency = getIntent().getStringExtra("candidate_applied_constituency");
        String election_date = getIntent().getStringExtra("election_date");

        editor.putString("candidate_name1", str_candidate_name).commit();
        editor.putString("candidate_party", str_candidate_party).commit();

        txt_candidate_name.setText(str_candidate_name);
        txt_candidate_father_husband.setText(str_candidate_father_husband);
        txt_candidate_party.setText(str_candidate_party);
        txt_candidate_age.setText(str_candidate_age);
        txt_candidate_gender.setText(str_candidate_gender);
        txt_candidate_address.setText(str_candidate_address);
        txt_candidate_applied_state.setText(str_candidate_applied_state);
        txt_candidate_applied_constituency.setText(str_candidate_applied_constituency);

        String currentDate = new SimpleDateFormat("dd/M/yyyy", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        String firsttwodigits = currentTime.substring(0,2);
        Integer time = Integer.valueOf(firsttwodigits);
//        Toast.makeText(this, ""+firsttwodigits, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "" + currentDate + " " + election_date, Toast.LENGTH_SHORT).show();

        if (!currentDate.equals(election_date))  {
                btn_vote_to_candidate.setText("Vote Count On " + election_date );
            }
//        else if (time < 07 || time >=19)
//        {
//            btn_vote_to_candidate.setText("You Can't Vote Now");
//        }
        else
            {
                total_vote_get_by_candidates();
            }


    }

    private void total_vote_get_by_candidates() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("vote_candidate_name",txt_candidate_name.getText().toString());
        client.post(Config.url_get_total_vote_by_candidate,params,new JsonHttpResponseHandler()
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
                    JSONArray jsonArray = response.getJSONArray("gettotalvoteofcandidate");
                    for (int i=0; i<jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String total_count = jsonObject.getString("total_count");
                        Toast.makeText(SingelCurrentElectionCandidateActivity.this, ""+total_count, Toast.LENGTH_SHORT).show();
                        btn_vote_to_candidate.setText("Total Votes "+total_count);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(SingelCurrentElectionCandidateActivity.this, "Could Not Connect", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
