package com.nikdroind.smartvotingsystemadminapp.Activities.Admin.UpcomingElection;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nikdroind.smartvotingsystemadminapp.R;

public class Upcoming_Election_SingleScreen extends AppCompatActivity {

    TextView txt_election_type,txt_election_state,txt_total_seat_title,txt_total_seat_no,txt_date_of_polling_title,
            txt_phase1_title,txt_phase1_date,txt_phase2_title,txt_phase2_date,txt_phase3_title,txt_phase3_date
            ,txt_phase4_title,txt_phase4_date,txt_counting_date_title,txt_counting_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming__election__single_screen);

        txt_election_type = findViewById(R.id.txt_election_type_name);
        txt_election_state = findViewById(R.id.txt_election_state);
        txt_total_seat_title = findViewById(R.id.txt_election_total_seat_title);
        txt_total_seat_no = findViewById(R.id.txt_election_total_seat_no);
        txt_date_of_polling_title = findViewById(R.id.txt_election_polling_date_title);
        txt_phase1_title = findViewById(R.id.txt_election_polling_phase1);
        txt_phase1_date = findViewById(R.id.txt_election_polling_date1);
        txt_phase2_title = findViewById(R.id.txt_election_polling_phase2);
        txt_phase2_date = findViewById(R.id.txt_election_polling_date2);
        txt_phase3_title = findViewById(R.id.txt_election_polling_phase3);
        txt_phase3_date = findViewById(R.id.txt_election_polling_date3);
        txt_phase4_title = findViewById(R.id.txt_election_polling_phase4);
        txt_phase4_date = findViewById(R.id.txt_election_polling_date4);
        txt_counting_date_title = findViewById(R.id.txt_election_polling_counting_date_title);
        txt_counting_date = findViewById(R.id.txt_election_polling_counting_date);

//        view1 = findViewById(R.id.view1);
//        view2 = findViewById(R.id.view2);

        String str_election_type = getIntent().getStringExtra("election_type");
        String str_election_state = getIntent().getStringExtra("election_state");
        String str_total_seat_title = getIntent().getStringExtra("total_seat_title");
        String str_total_seat_no = getIntent().getStringExtra("total_seat_no");
        String str_date_of_polling_title = getIntent().getStringExtra("date_of_polling_title");
        String str_phase1_title = getIntent().getStringExtra("phase1_title");
        String str_phase1_date = getIntent().getStringExtra("phase1_date");
        String str_phase2_title = getIntent().getStringExtra("phase2_title");
        String str_phase2_date = getIntent().getStringExtra("phase2_date");
        String str_phase3_title = getIntent().getStringExtra("phase3_title");
        String str_phase3_date = getIntent().getStringExtra("phase3_date");
        String str_phase4_title = getIntent().getStringExtra("phase4_title");
        String str_phase4_date = getIntent().getStringExtra("phase4_date");
        String str_counting_date_title = getIntent().getStringExtra("counting_date_title");
        String str_counting_date = getIntent().getStringExtra("counting_date");


        txt_election_type.setText(str_election_type);
        txt_election_state.setText(str_election_state);
        txt_total_seat_title.setText(str_total_seat_title);
        txt_total_seat_no.setText(str_total_seat_no);
        txt_date_of_polling_title.setText(str_date_of_polling_title);
        txt_phase1_title.setText(str_phase1_title);
        txt_phase1_date.setText(str_phase1_date);
        txt_phase2_title.setText(str_phase2_title);
        txt_phase2_date.setText(str_phase2_date);
        txt_phase3_title.setText(str_phase3_title);
        txt_phase3_date.setText(str_phase3_date);
        txt_phase4_title.setText(str_phase4_title);
        txt_phase4_date.setText(str_phase4_date);
        txt_counting_date_title.setText(str_counting_date_title);
        txt_counting_date.setText(str_counting_date);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(str_election_type);
        actionBar.setSubtitle(str_election_state);

        if (txt_phase3_date.getText().toString().isEmpty())
        {
            txt_phase3_title.setVisibility(View.GONE);
            txt_phase3_date.setVisibility(View.GONE);
            txt_phase4_title.setVisibility(View.GONE);
            txt_phase4_date.setVisibility(View.GONE);

        }
        if (txt_phase4_date.getText().toString().isEmpty())
        {
            txt_phase4_title.setVisibility(View.GONE);
            txt_phase4_date.setVisibility(View.GONE);
            txt_phase3_title.setGravity(View.TEXT_ALIGNMENT_CENTER);
            txt_phase3_date.setGravity(View.TEXT_ALIGNMENT_CENTER);

        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Upcoming_Election_SingleScreen.this,UpcomingElectionListActivity.class));
        finish();
    }
}
