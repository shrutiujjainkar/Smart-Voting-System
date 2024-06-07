package com.nikdroind.smartvotingsystemadminapp.Activities.Admin.CurrentElection;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nikdroind.smartvotingsystemadminapp.Comman.Config;
import com.nikdroind.smartvotingsystemadminapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class Add_Current_Election_Type_and_ConstituencyScreen extends AppCompatActivity {

    ScrollView sv_Add_Current_Election_Type_and_ConstituencyScreen;
    EditText edt_eletion_type,edt_election_constituency;
    TextView tv_select_election_date;
    Button btn_add_type_constituency;
    ProgressBar progress;

    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_current_election_type_constituency);

        setTitle("Add Election Type/Constituency");
        init();
    }

    private void init() {

        sv_Add_Current_Election_Type_and_ConstituencyScreen = findViewById(R.id.sv_Add_Current_Election_Type_and_ConstituencyScreen);
        edt_eletion_type = findViewById(R.id.edt_add_current_election_type);
        edt_election_constituency = findViewById(R.id.edt_add_current_election_constituency);
        tv_select_election_date = findViewById(R.id.tv_select_election_date);
        btn_add_type_constituency = findViewById(R.id.btn_add_type_constituency);
        progress = findViewById(R.id.progress);

        tv_select_election_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

                // date picker dialog
                datePickerDialog = new DatePickerDialog(Add_Current_Election_Type_and_ConstituencyScreen.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                tv_select_election_date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();

            }
        });

        btn_add_type_constituency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });


    }

    private void validation() {

        if(edt_eletion_type.getText().toString().isEmpty())
        {
            edt_eletion_type.setError("Please Enter Eletion Type");
        }
        else if(edt_election_constituency.getText().toString().isEmpty())
        {
            edt_election_constituency.setError("Please Enter Election Constituency");
        }
        else if (tv_select_election_date.getText().toString().equals("Select Date of Election"))
        {

            Snackbar.make(sv_Add_Current_Election_Type_and_ConstituencyScreen,"Please Enter Date Of Election",Snackbar.LENGTH_SHORT).show();
        }
        else
        {
            addTypeConstituency();
        }
    } //validation

    private void addTypeConstituency() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("current_election_type",edt_eletion_type.getText().toString());
        params.put("current_election_constituency",edt_election_constituency.getText().toString());
        params.put("current_election_date",tv_select_election_date.getText().toString());


        client.post(Config.url_add_current_election_type_constituency,params,new JsonHttpResponseHandler() {
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

                        Toast.makeText(Add_Current_Election_Type_and_ConstituencyScreen.this, "Election Type and Constituency Succesfully Done", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Add_Current_Election_Type_and_ConstituencyScreen.this,CurrentElection_Type_ConstituencyActivity.class));
                        finish();
                    }
                    else
                    {
                        Toast.makeText(Add_Current_Election_Type_and_ConstituencyScreen.this, "Already Added", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(Add_Current_Election_Type_and_ConstituencyScreen.this, "Could not Connect", Toast.LENGTH_SHORT).show();
            }
        });

    } //addTypeConstituency


    @Override
    public void onBackPressed() {
        startActivity(new Intent(Add_Current_Election_Type_and_ConstituencyScreen.this,CurrentElection_Type_ConstituencyActivity.class));
        finish();
    } //onBackPressed
}
