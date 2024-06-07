package com.nikdroind.smartvotingsystemadminapp.Activities.Admin.CurrentElection;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nikdroind.smartvotingsystemadminapp.Comman.Config;
import com.nikdroind.smartvotingsystemadminapp.Comman.VolleyMultipartRequest;
import com.nikdroind.smartvotingsystemadminapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class Add_Current_Election_By_Candidate_ConstituencyScreen extends AppCompatActivity {

    EditText edt_candidate_name,edt_candidate_father_husband_name,edt_candidate_party,edt_candidate_age,edt_candidate_gender,
            edt_candidate_address,edt_candidate_applied_state,edt_candidate_applied_constituency_pincode;
    TextView tv_candidate_applied_constituency;
//    EditText edt_candidate_applied_constituency;
    String candidate_applied_constituency;

    Button btn_add_candidate;
    ProgressBar progress;

    Button btn_select_candidate_image;
    ImageView img_candidate_profile;
    private int PICK_IMAGE_REQUEST = 1;
    private Bitmap bitmap;
    private Uri filePath;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_current_election_candidate);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        setTitle(preferences.getString("election_constituency","")+" "+"Candidates");

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        init();
    }

    private void init() {
    edt_candidate_name = findViewById(R.id.edt_add_current_election_candidate_name);
    edt_candidate_father_husband_name = findViewById(R.id.edt_add_current_election_candidate_father_husband_name);
    edt_candidate_party = findViewById(R.id.edt_add_current_election_candidate_party);
    edt_candidate_age = findViewById(R.id.edt_add_current_election_candidate_age);
    edt_candidate_gender = findViewById(R.id.edt_add_current_election_candidate_gender);
    edt_candidate_address = findViewById(R.id.edt_add_current_election_candidate_address);
    edt_candidate_applied_state = findViewById(R.id.edt_add_current_election_candidate_applied_state);
    tv_candidate_applied_constituency = findViewById(R.id.tv_current_election_candidate_applied_Constitueny);
//    edt_candidate_applied_constituency = findViewById(R.id.edt_add_current_election_candidate_applied_Constitueny);
    edt_candidate_applied_constituency_pincode = findViewById(R.id.edt_add_current_election_candidate_applied_constituncy_pincode);

    btn_select_candidate_image = findViewById(R.id.btn_add_current_election_candidate_image);
    img_candidate_profile = findViewById(R.id.img_add_current_election_candidate_image);
    btn_add_candidate = findViewById(R.id.btn_add_current_election_candidate);
    progress = findViewById(R.id.progress);

    candidate_applied_constituency =preferences.getString("election_constituency","");
        Toast.makeText(this, ""+candidate_applied_constituency, Toast.LENGTH_SHORT).show();
    tv_candidate_applied_constituency.setText(candidate_applied_constituency);

    btn_select_candidate_image.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showFileChooser();
        }
    });

    btn_add_candidate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            validation();
        }
    });
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                img_candidate_profile.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void validation() {

        if(edt_candidate_name.getText().toString().isEmpty())
        {
            edt_candidate_name.setError("Please Enter Your Full Name");
        }
        else if(edt_candidate_father_husband_name.getText().toString().isEmpty())
        {
            edt_candidate_father_husband_name.setError("Please Enter Your Father/Husband Name");
        }
        else if (edt_candidate_party.getText().toString().isEmpty())
        {
            edt_candidate_party.setError("Please Enter Your Party Name");
        }
        else if (edt_candidate_age.getText().toString().isEmpty())
        {
            edt_candidate_age.setError("Please Enter Candidate Age ");
        }
        else if (edt_candidate_gender.getText().toString().isEmpty())
        {
            edt_candidate_gender.setError("Please Enter Candidate Gender");
        }
        else if (edt_candidate_address.getText().toString().isEmpty())
        {
            edt_candidate_address.setError("Please Enter Candidate Address");
        }
        else if (edt_candidate_applied_state.getText().toString().isEmpty())
        {
            edt_candidate_applied_state.setError("Please Enter Candidate Applied State");
        }
//        else if (edt_candidate_applied_constituency.getText().toString().isEmpty())
//        {
//            edt_candidate_applied_constituency.setError("Please Enter Candidate Applied Constituency");
//        }
        else if (edt_candidate_applied_constituency_pincode.getText().toString().isEmpty())
        {
            edt_candidate_applied_constituency_pincode.setError("Please Enter Candidate Applied Constituency Pincode");
        }

        else
        {
            addCurrentElectionCandidate();
        }
    }

    private void addCurrentElectionCandidate() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("candidate_full_name",edt_candidate_name.getText().toString());
        params.put("candidate_father_husband_name",edt_candidate_father_husband_name.getText().toString());
        params.put("candidate_party_name",edt_candidate_party.getText().toString());
        params.put("candidate_age",edt_candidate_age.getText().toString());
        params.put("candidate_gender",edt_candidate_gender.getText().toString());
        params.put("candidate_address",edt_candidate_address.getText().toString());
        params.put("candidate_applied_state",edt_candidate_applied_state.getText().toString());
        params.put("candidate_applied_consituency",tv_candidate_applied_constituency.getText().toString());
        params.put("candidate_applied_consituency_pincode",edt_candidate_applied_constituency_pincode.getText().toString());

        client.post(Config.url_add_current_election_candidate,params,new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                progress.setVisibility(View.VISIBLE);
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, final JSONObject response) {
                progress.setVisibility(View.GONE);
                try {
                    String aa = response.getString("success");

                    if (aa.equals("1"))
                    {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    uploadBitmap(bitmap, response.getInt("lastinsertedid"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },1000);

                    }
                    else
                    {
                        Toast.makeText(Add_Current_Election_By_Candidate_ConstituencyScreen.this, "Already Register", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(Add_Current_Election_By_Candidate_ConstituencyScreen.this, "Could not Connect", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void uploadBitmap(final Bitmap bitmap, final int lastinsertedid) {
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, Config.url_add_current_election_candidate_image,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        startActivity(new Intent(Add_Current_Election_By_Candidate_ConstituencyScreen.this,CurrentElectionCandidateActivity.class));
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("tags",""+ lastinsertedid);
                return params;
            }

            /*
             * Here we are passing image by renaming it with a unique name
             * */
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("pic", new DataPart(imagename + ".png", getFileDataFromDrawable1(bitmap)));
                return params;
            }
        };

        //adding the request to volley
        Volley.newRequestQueue(this).add(volleyMultipartRequest);

    }


    public byte[] getFileDataFromDrawable1(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            startActivity(new Intent(Add_Current_Election_By_Candidate_ConstituencyScreen.this,CurrentElectionCandidateActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(Add_Current_Election_By_Candidate_ConstituencyScreen.this,CurrentElection_Type_ConstituencyActivity.class));
        finish();
    }
}
