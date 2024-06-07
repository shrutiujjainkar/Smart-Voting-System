package com.nikdroind.smartvotingsystemadminapp.Activities.Admin.CandidateList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

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
import com.nikdroind.smartvotingsystemadminapp.AdapterClass.CandidateListByCandidateElectionStateAdapter;
import com.nikdroind.smartvotingsystemadminapp.Comman.Config;
import com.nikdroind.smartvotingsystemadminapp.Pojoclass.pojo_Candidate_List_By_Candidate_Election_State;
import com.nikdroind.smartvotingsystemadminapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class Candidate_List_By_Candidate_Election_Type_and_ConstituencyScreen extends AppCompatActivity {

    List<pojo_Candidate_List_By_Candidate_Election_State> list;
    CandidateListByCandidateElectionStateAdapter adapter;
    SearchView searchView_candidate;
    TextView txt_election_category,tv_no_record;
    ListView lv_candidate_list;
    ProgressBar progress;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    FloatingActionButton fab_add_constituencywise_candidate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candiate__list__by__candidate__election__state_screen);

        setTitle("Candidate List");

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        list = new ArrayList<>();
        searchView_candidate = findViewById(R.id.searchview_candidate);
        txt_election_category = findViewById(R.id.txt_election_category);
        tv_no_record = findViewById(R.id.tv_no_record);
        lv_candidate_list = findViewById(R.id.lv_candidate_list);
        progress = findViewById(R.id.progress);

        fab_add_constituencywise_candidate = findViewById(R.id.fab_add_constituencywise_candiate);

        final String election_constituency = preferences.getString("election_constituency","");
        fab_add_constituencywise_candidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Candidate_List_By_Candidate_Election_Type_and_ConstituencyScreen.this, Add_Candidate_List_By_Candidate_ConstituencyScreen.class));
                editor.putString("election_constituency",election_constituency).commit();
                finish();
            }
        });

        searchView_candidate.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchCandidates(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchCandidates(newText);
                return false;
            }
        });

        getCandidateList();
    }

    private void searchCandidates(String query) {

        List<pojo_Candidate_List_By_Candidate_Election_State> tempcenterlist = new ArrayList();
        tempcenterlist.clear();

        for (pojo_Candidate_List_By_Candidate_Election_State d : list) {
            if (d.getCandidate_name().toUpperCase().contains(query.toUpperCase()) || d.candidate_party.toUpperCase().contains(query
                    .toUpperCase()))
                tempcenterlist.add(d);
        }

        adapter = new CandidateListByCandidateElectionStateAdapter(tempcenterlist, Candidate_List_By_Candidate_Election_Type_and_ConstituencyScreen.this,tv_no_record);
        lv_candidate_list.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            startActivity(new Intent(Candidate_List_By_Candidate_Election_Type_and_ConstituencyScreen.this, Candidate_List_Elections_Type_ConstituencyScreen.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getCandidateList() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("election_constituency",preferences.getString("election_constituency",""));
        client.post(Config.url_candidate_list, params,new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                progress.setVisibility(View.VISIBLE);
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                progress.setVisibility(View.GONE);
                try {
                    JSONArray jsonArray = response.getJSONArray("getCandidateList");
                    for (int i= 0 ; i<jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String candidate_name = jsonObject.getString("candidate_name");
                        String candidate_father_husband = jsonObject.getString("candidate_father_husband");
                        String candidate_party = jsonObject.getString("candidate_party");
                        String candidate_age = jsonObject.getString("candidate_age");
                        String candidate_gender = jsonObject.getString("candidate_gender");
                        String candidate_address = jsonObject.getString("candidate_address");
                        String candidate_applied_state = jsonObject.getString("candidate_applied_state");
                        String candidate_applied_constituency = jsonObject.getString("candidate_applied_constituency");
                        String image = jsonObject.getString("candidate_pic");

                        list.add(new pojo_Candidate_List_By_Candidate_Election_State(candidate_name,candidate_father_husband,candidate_party,candidate_age,candidate_gender, candidate_address,candidate_applied_state,candidate_applied_constituency,image));
                    }

                    adapter = new CandidateListByCandidateElectionStateAdapter(list, Candidate_List_By_Candidate_Election_Type_and_ConstituencyScreen.this,tv_no_record);
                    lv_candidate_list.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(Candidate_List_By_Candidate_Election_Type_and_ConstituencyScreen.this, "Could Not Connect", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Candidate_List_By_Candidate_Election_Type_and_ConstituencyScreen.this, Candidate_List_Elections_Type_ConstituencyScreen.class));
        finish();
    }
}