package com.nikdroind.smartvotingsystemadminapp.Activities.Admin.CandidateList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.nikdroind.smartvotingsystemadminapp.Comman.Config;
import com.nikdroind.smartvotingsystemadminapp.R;
import com.squareup.picasso.Picasso;

public class Candidate_List_Single_Candidate_InformationScreen extends AppCompatActivity {

    TextView txt_candidate_name,txt_candidate_father_husband,txt_candidate_party,txt_candidate_age,txt_candidate_gender,
            txt_candidate_address,txt_candidate_applied_state,txt_candidate_applied_constituency;

    ImageView img_candidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_list_single_candidate_information_screen);

        setTitle("Candidate Information");
        txt_candidate_name = findViewById(R.id.txt_candidate_name);
        txt_candidate_father_husband = findViewById(R.id.txt_candidate_father_husband);
        txt_candidate_party = findViewById(R.id.txt_candidate_party);
        txt_candidate_age = findViewById(R.id.txt_candidate_age);
        txt_candidate_gender = findViewById(R.id.txt_candidate_gender);
        txt_candidate_address = findViewById(R.id.txt_candidate_address);
        txt_candidate_applied_state = findViewById(R.id.txt_candidate_applied_state);
        txt_candidate_applied_constituency = findViewById(R.id.txt_candidate_applied_constituency);

        img_candidate = findViewById(R.id.img_profile);

        Picasso.with(Candidate_List_Single_Candidate_InformationScreen.this).load(Config.OnlineImageAddress+""+getIntent().getStringExtra("candidate_pic"))
                .error(R.drawable.image_not_load).into(img_candidate);

        String str_candidate_name = getIntent().getStringExtra("candidate_name");
        String str_candidate_father_husband = getIntent().getStringExtra("candidate_father_husband");
        String str_candidate_party = getIntent().getStringExtra("candidate_party");
        String str_candidate_age = getIntent().getStringExtra("candidate_age");
        String str_candidate_gender = getIntent().getStringExtra("candidate_gender");
        String str_candidate_address = getIntent().getStringExtra("candidate_address");
        String str_candidate_applied_state = getIntent().getStringExtra("candidate_applied_state");
        String str_candidate_applied_constituency = getIntent().getStringExtra("candidate_applied_constituency");

        txt_candidate_name.setText(str_candidate_name);
        txt_candidate_father_husband.setText(str_candidate_father_husband);
        txt_candidate_party.setText(str_candidate_party);
        txt_candidate_age.setText(str_candidate_age);
        txt_candidate_gender.setText(str_candidate_gender);
        txt_candidate_address.setText(str_candidate_address);
        txt_candidate_applied_state.setText(str_candidate_applied_state);
        txt_candidate_applied_constituency.setText(str_candidate_applied_constituency);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Candidate_List_Single_Candidate_InformationScreen.this, Candidate_List_By_Candidate_Election_Type_and_ConstituencyScreen.class));
        finish();
    }
}