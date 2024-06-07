package com.nikdroind.smartvotingsystemadminapp.AdapterClass;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.nikdroind.smartvotingsystemadminapp.Activities.Admin.CurrentElection.SingelCurrentElectionCandidateActivity;
import com.nikdroind.smartvotingsystemadminapp.Comman.Config;
import com.nikdroind.smartvotingsystemadminapp.Pojoclass.pojo_Current_Election_Candidate;
import com.nikdroind.smartvotingsystemadminapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CurrentElectionCandidateAdapter extends BaseAdapter {

    List<pojo_Current_Election_Candidate> list;
    Activity activity;
    TextView tv_no_record;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public CurrentElectionCandidateAdapter(List<pojo_Current_Election_Candidate> list, Activity activity, TextView tv_no_record) {
        this.list = list;
        this.activity = activity;
        this.tv_no_record = tv_no_record;

        preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        editor = preferences.edit();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        final CurrentElectionCandidateAdapter.ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (v == null)
        {
            holder = new CurrentElectionCandidateAdapter.ViewHolder();
            v = inflater.inflate(R.layout.list_current_election_candidate,null);

            holder.candidate_name = v.findViewById(R.id.txt_current_election_candidate_name);
            holder.candidate_party = v.findViewById(R.id.txt_current_election_candidate_party);
            holder.candidate_applied_constituency = v.findViewById(R.id.txt_current_election_candidate_constituency);
            holder.candidate_applied_state = v.findViewById(R.id.txt_current_election_candidate_applied_state);
            holder.cv_candidate_list = v.findViewById(R.id.cv_current_election_candidate_list);
            holder.img_candidate = v.findViewById(R.id.img_current_election_candidate_profile);

            v.setTag(holder);
        }else
        {
            holder = (CurrentElectionCandidateAdapter.ViewHolder) v.getTag();
        }

        final pojo_Current_Election_Candidate obj = list.get(position);
        holder.candidate_name.setText(obj.getCandidate_name());
        holder.candidate_party.setText(obj.getCandidate_party());
        holder.candidate_applied_constituency.setText(obj.getCandidate_applied_constituency());
        holder.candidate_applied_state.setText(obj.getCandidate_applied_state());

        Picasso.with(activity).load(Config.OnlineImageAddress+""+obj.getCandidate_image()).placeholder(R.drawable.profileimage)
                .error(R.drawable.image_not_load).into(holder.img_candidate);

//        if (!(holder.img_candidate.getDrawable() == null))
//        {
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(activity, "Image in not empty", Toast.LENGTH_SHORT).show();
////                    Toast.makeText(activity, "वाट पहा आपले Profile Picture ओपन होईल  ", Toast.LENGTH_SHORT).show();
//                }
//            }, 5000);
//
//        }

        final String election_date = preferences.getString("election_date","");

        holder.cv_candidate_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(activity, SingelCurrentElectionCandidateActivity.class);
                intent.putExtra("candidate_image",obj.getCandidate_image());
                intent.putExtra("candidate_name",obj.getCandidate_name());
                intent.putExtra("candidate_father_husband",obj.getCandidate_father_husband());
                intent.putExtra("candidate_party",obj.getCandidate_party());
                intent.putExtra("candidate_age",obj.getCandidate_age());
                intent.putExtra("candidate_gender",obj.getCandidate_gender());
                intent.putExtra("candidate_address",obj.getCandidate_address());
                intent.putExtra("candidate_applied_state",obj.getCandidate_applied_state());
                intent.putExtra("candidate_applied_constituency",obj.getCandidate_applied_constituency());
                intent.putExtra("election_date",election_date);
                activity.startActivity(intent);
            }
        });

        return v;
    }

    class ViewHolder
    {
        TextView candidate_name,candidate_party,candidate_applied_constituency,candidate_applied_state;
        ImageView img_candidate;
        CardView cv_candidate_list;
    }

}
