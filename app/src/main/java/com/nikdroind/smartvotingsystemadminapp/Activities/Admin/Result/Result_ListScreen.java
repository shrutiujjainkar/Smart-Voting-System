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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nikdroind.smartvotingsystemadminapp.Activities.Admin.CurrentElection.Add_Current_Election_Type_and_ConstituencyScreen;
import com.nikdroind.smartvotingsystemadminapp.Activities.Admin.CurrentElection.CurrentElection_Type_ConstituencyActivity;
import com.nikdroind.smartvotingsystemadminapp.AdapterClass.ResultListAdapter;
import com.nikdroind.smartvotingsystemadminapp.Comman.Config;
import com.nikdroind.smartvotingsystemadminapp.HomeActivity;
import com.nikdroind.smartvotingsystemadminapp.Pojoclass.pojo_Result_List;
import com.nikdroind.smartvotingsystemadminapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class Result_ListScreen extends AppCompatActivity {

    List<pojo_Result_List> list;
    ResultListAdapter resultListAdapter;
    ListView lv_result_list;
    ProgressBar progress;
    TextView tv_no_record;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    FloatingActionButton fab_add_result_list_of_election_by_constituency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result__list_screen);

        setTitle("Result List");

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        fab_add_result_list_of_election_by_constituency = findViewById(R.id.fab_add_result_list_of_election_by_constituency);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        list = new ArrayList<>();
        lv_result_list = findViewById(R.id.lv_result_list);
        progress = findViewById(R.id.progress);
        tv_no_record = findViewById(R.id.tv_no_record);

        fab_add_result_list_of_election_by_constituency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Result_ListScreen.this, Add_Result_ListScreen.class));
                finish();
            }
        });

        getResultList();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            startActivity(new Intent(Result_ListScreen.this, HomeActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void getResultList() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        client.post(Config.url_result_list,params,new JsonHttpResponseHandler()
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
                            JSONArray jsonArray = response.getJSONArray("getResultList");

                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String election_type_name = jsonObject.getString("election_type_name");
                                String election_place_name = jsonObject.getString("election_place_name");
                                String election_total_seat = jsonObject.getString("election_total_seat");
                                String election_majority = jsonObject.getString("election_majority");

                                list.add(new pojo_Result_List(election_type_name,election_place_name,election_total_seat,election_majority));
                            }

                            resultListAdapter = new ResultListAdapter(list,Result_ListScreen.this,tv_no_record);
                            lv_result_list.setAdapter(resultListAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                    }
                }
        );
    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(Result_ListScreen.this,HomeActivity.class));
        finish();
    }
}