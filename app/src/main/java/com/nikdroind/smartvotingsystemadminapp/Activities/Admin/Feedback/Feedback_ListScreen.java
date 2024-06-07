package com.nikdroind.smartvotingsystemadminapp.Activities.Admin.Feedback;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nikdroind.smartvotingsystemadminapp.Activities.Admin.CurrentElection.Add_Current_Election_Type_and_ConstituencyScreen;
import com.nikdroind.smartvotingsystemadminapp.Activities.Admin.CurrentElection.CurrentElection_Type_ConstituencyActivity;
import com.nikdroind.smartvotingsystemadminapp.AdapterClass.CurrentElectionTypeConstituencyAdapter;
import com.nikdroind.smartvotingsystemadminapp.AdapterClass.FeedbackListAdapter;
import com.nikdroind.smartvotingsystemadminapp.Comman.Config;
import com.nikdroind.smartvotingsystemadminapp.HomeActivity;
import com.nikdroind.smartvotingsystemadminapp.Pojoclass.pojo_Current_Election_Type_Constituency;
import com.nikdroind.smartvotingsystemadminapp.Pojoclass.pojo_Feedback_List;
import com.nikdroind.smartvotingsystemadminapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class Feedback_ListScreen extends AppCompatActivity {

    List<pojo_Feedback_List> list;
    FeedbackListAdapter feedback_Adapter;
    ListView lv_feedback_list;
    ProgressBar progress;
    TextView tv_no_record;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_listscreen);

        setTitle("Feedback List");
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        list = new ArrayList<>();
        lv_feedback_list = findViewById(R.id.lv_feedback_list);
        progress = findViewById(R.id.progress);
        tv_no_record = findViewById(R.id.tv_no_record);

        getFeedbackList();
    }

    private void getFeedbackList() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        client.post(Config.url_get_feedback_list,params,new JsonHttpResponseHandler()
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
                    JSONArray jsonArray = response.getJSONArray("getFeedbackList");
                    for (int i=0; i<jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String feedback_username = jsonObject.getString("feedback_username");
                        String feedback_mobile_no = jsonObject.getString("feedback_mobile_no");
                        String feedback = jsonObject.getString("feedback");


                        list.add(new pojo_Feedback_List(feedback_username,feedback_mobile_no,feedback));
                    }

                    feedback_Adapter = new FeedbackListAdapter(list,Feedback_ListScreen.this,tv_no_record);
                    lv_feedback_list.setAdapter(feedback_Adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(Feedback_ListScreen.this, "Could Not Connect", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Feedback_ListScreen.this, HomeActivity.class));
        finish();
    }
}