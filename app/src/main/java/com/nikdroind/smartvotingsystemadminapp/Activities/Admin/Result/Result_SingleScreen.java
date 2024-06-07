package com.nikdroind.smartvotingsystemadminapp.Activities.Admin.Result;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nikdroind.smartvotingsystemadminapp.AdapterClass.ResultSingleAdapter;
import com.nikdroind.smartvotingsystemadminapp.Comman.Config;
import com.nikdroind.smartvotingsystemadminapp.Pojoclass.pojo_Result_Single;
import com.nikdroind.smartvotingsystemadminapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class Result_SingleScreen extends AppCompatActivity {

    TextView txt_election_type_name,txt_election_place_name,txt_election_total_seats,txt_election_majority;

    List<pojo_Result_Single> list;
    ResultSingleAdapter adapter;
    TextView tv_no_record;
    ListView lv_result_single;
    ProgressBar progress;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    FloatingActionButton fab_add_result_partywise;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result__single_screen);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getSupportActionBar().setTitle(""+getIntent().getStringExtra("election_place_name"));

        fab_add_result_partywise = findViewById(R.id.fab_add_result_partywise);
        list = new ArrayList<>();
        lv_result_single = findViewById(R.id.lv_result_single);
        progress = findViewById(R.id.progress);
        tv_no_record = findViewById(R.id.tv_no_record);

        txt_election_type_name = findViewById(R.id.txt_result_single_election_type_name);
        txt_election_place_name = findViewById(R.id.txt_result_single_election_place_name);
        txt_election_total_seats = findViewById(R.id.txt_result_single_election_total_seats);
        txt_election_majority = findViewById(R.id.txt_result_single_election_majority);


        final String str_election_type_name  = getIntent().getStringExtra("election_type_name");
        final String str_election_place_name  = getIntent().getStringExtra("election_place_name");
        String str_election_total_seats  = getIntent().getStringExtra("election_total_seats");
        String str_election_majority  = getIntent().getStringExtra("election_majority");

        txt_election_type_name.setText(str_election_type_name);
        txt_election_place_name.setText(str_election_place_name);
        txt_election_total_seats.setText(str_election_total_seats);
        txt_election_majority.setText(str_election_majority);

        fab_add_result_partywise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Result_SingleScreen.this,Add_Result_PartywiseActivity.class));
                editor.putString("election_constituency",str_election_place_name).commit();
                editor.putString("election_type_name",str_election_type_name).commit();
                finish();
            }
        });

        getResultSingle();
    }

    private void getResultSingle() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("election_constituency",preferences.getString("election_constituency",""));
        client.post(Config.url_result_single, params,new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                progress.setVisibility(View.VISIBLE);
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                progress.setVisibility(View.GONE);
                try {
                    JSONArray jsonArray = response.getJSONArray("getpartywisereport");
                    for (int i= 0 ; i<jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String party_name = jsonObject.getString("party_name");
                        String party_won = jsonObject.getString("party_win");
                        String party_leading = jsonObject.getString("party_leading");
                        String party_total = jsonObject.getString("party_total");
                        String election_place = jsonObject.getString("election_place");

                        list.add(new pojo_Result_Single(party_name,party_won,party_leading,party_total,election_place));
                    }

                    adapter = new ResultSingleAdapter(list,Result_SingleScreen.this,tv_no_record);
                    lv_result_single.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(Result_SingleScreen.this, "Could Not Connect", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            startActivity(new Intent(Result_SingleScreen.this,Result_ListScreen.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Result_SingleScreen.this,Result_ListScreen.class));
        finish();
    }
}