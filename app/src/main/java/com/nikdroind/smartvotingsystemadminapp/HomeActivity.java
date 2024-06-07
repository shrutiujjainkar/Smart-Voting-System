package com.nikdroind.smartvotingsystemadminapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ProgressBar;

import com.nikdroind.smartvotingsystemadminapp.Activities.Admin.CandidateList.Candidate_List_Elections_Type_ConstituencyScreen;
import com.nikdroind.smartvotingsystemadminapp.Activities.Admin.CurrentElection.CurrentElection_Type_ConstituencyActivity;
import com.nikdroind.smartvotingsystemadminapp.Activities.Admin.Feedback.Feedback_ListScreen;
import com.nikdroind.smartvotingsystemadminapp.Activities.Admin.Result.Result_ListScreen;
import com.nikdroind.smartvotingsystemadminapp.Activities.Admin.TypeofElection.TypeofElectionActivity;
import com.nikdroind.smartvotingsystemadminapp.Activities.Admin.UpcomingElection.UpcomingElectionListActivity;
import com.nikdroind.smartvotingsystemadminapp.Activities.Admin.VoterList.AllVoterActivity;

public class HomeActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    CardView cardView11,cardView22,cardView33,cardView44,cardView55,cardView66,cardView77,cardView88;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setTitle("SVS Admin App");

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = preferences.edit();

        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        boolean firsttime = prefs.getBoolean("firsttime",true);

        if (firsttime)
        {
            welcome();
        }


        progress = findViewById(R.id.progress);
        cardView11 = findViewById(R.id.cardview11);
        cardView22 = findViewById(R.id.cardview22);
        cardView33 = findViewById(R.id.cardview33);
        cardView44 = findViewById(R.id.cardview44);
        cardView55 = findViewById(R.id.cardview55);
        cardView66 = findViewById(R.id.cardview66);
        cardView77 = findViewById(R.id.cardview77);
        cardView88 = findViewById(R.id.cardview88);

        cardView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progress.setVisibility(View.GONE);
                        startActivity(new Intent(HomeActivity.this, TypeofElectionActivity.class));
                        finish();
                    }
                },2000);

            }
        });

        cardView22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progress.setVisibility(View.GONE);
                        startActivity(new Intent(HomeActivity.this, AllVoterActivity.class));
                        finish();
                    }
                },2000);
            }
        });


        cardView33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progress.setVisibility(View.GONE);
                        startActivity(new Intent(HomeActivity.this, CurrentElection_Type_ConstituencyActivity.class));
                        finish();
                    }
                },2000);
            }
        });

        cardView44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progress.setVisibility(View.GONE);
                        startActivity(new Intent(HomeActivity.this, UpcomingElectionListActivity.class));
                        finish();
                    }
                },2000);
            }
        });

        cardView55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progress.setVisibility(View.GONE);
                        startActivity(new Intent(HomeActivity.this, Candidate_List_Elections_Type_ConstituencyScreen.class));
                        finish();
                    }
                },2000);
            }
        });

        cardView66.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progress.setVisibility(View.GONE);
                        startActivity(new Intent(HomeActivity.this, Result_ListScreen.class));
                        finish();
                    }
                },2000);
            }
        });

        cardView77.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progress.setVisibility(View.GONE);
                        startActivity(new Intent(HomeActivity.this, Feedback_ListScreen.class));
                        finish();
                    }
                },2000);
            }
        });

        cardView88.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progress.setVisibility(View.GONE);
                       logout();
                    }
                },2000);
            }
        });


    }


    public void welcome()
    {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("SVS Admin App");
        ad.setMessage("Welcome to Smart Voting System Admin Panel");
        ad.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = ad.create();
        alertDialog.show();

        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("firsttime", false);
        editor.apply();
    }

    public void logout()
    {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Logout")
                .setMessage("Are You Sure You Want To Logout")
                .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setNegativeButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        editor.putBoolean("islogin",false).commit();
                        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                        finish();
                    }
                });

        AlertDialog alertDialog = ad.create();
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.darker_gray);
    }

    @Override
    public void onBackPressed() {
        logout();
    }
}
