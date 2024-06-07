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

import androidx.cardview.widget.CardView;

import com.nikdroind.smartvotingsystemadminapp.Activities.Admin.CandidateList.Candidate_List_Single_Candidate_InformationScreen;
import com.nikdroind.smartvotingsystemadminapp.Comman.Config;
import com.nikdroind.smartvotingsystemadminapp.Pojoclass.pojo_Candidate_List_By_Candidate_Election_State;
import com.nikdroind.smartvotingsystemadminapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CandidateListByCandidateElectionStateAdapter extends BaseAdapter {

    List<pojo_Candidate_List_By_Candidate_Election_State> list;
    Activity activity;
    TextView tv_no_record;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public CandidateListByCandidateElectionStateAdapter(List<pojo_Candidate_List_By_Candidate_Election_State> list, Activity activity, TextView tv_no_record) {
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

        final CandidateListByCandidateElectionStateAdapter.ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (v == null)
        {
            holder = new CandidateListByCandidateElectionStateAdapter.ViewHolder();
            v = inflater.inflate(R.layout.list_candidate_list_by_candidate_election_state,null);

            holder.candidate_name = v.findViewById(R.id.txt_candidate_name);
            holder.candidate_party = v.findViewById(R.id.txt_candidate_constituency);
            holder.candidate_applied_state = v.findViewById(R.id.txt_candidate_applied_state);
            holder.cv_candidate_list = v.findViewById(R.id.cv_election_candidate_list);
            holder.img_candidate = v.findViewById(R.id.img_candidate_profile);

            v.setTag(holder);
        }else
        {
            holder = (CandidateListByCandidateElectionStateAdapter.ViewHolder) v.getTag();
        }

        final pojo_Candidate_List_By_Candidate_Election_State obj = list.get(position);
        holder.candidate_name.setText(obj.getCandidate_name());
        holder.candidate_party.setText(obj.getCandidate_party());
        holder.candidate_applied_state.setText(obj.getCandidate_applied_state());

        Picasso.with(activity).load(Config.OnlineImageAddress+""+obj.getImage()).placeholder(R.drawable.profileimage)
                .error(R.drawable.image_not_load).into(holder.img_candidate);

        holder.cv_candidate_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(activity, Candidate_List_Single_Candidate_InformationScreen.class);
                intent.putExtra("candidate_name",obj.getCandidate_name());
                intent.putExtra("candidate_father_husband",obj.getCandidate_father_husband());
                intent.putExtra("candidate_party",obj.getCandidate_party());
                intent.putExtra("candidate_age",obj.getCandidate_age());
                intent.putExtra("candidate_gender",obj.getCandidate_gender());
                intent.putExtra("candidate_address",obj.getCandidate_address());
                intent.putExtra("candidate_applied_state",obj.getCandidate_applied_state());
                intent.putExtra("candidate_applied_constituency",obj.getCandidate_applied_constituency());
                intent.putExtra("candidate_pic",obj.getImage());

                activity.startActivity(intent);
            }
        });

        return v;
    }

    class ViewHolder
    {
        TextView candidate_name,candidate_party,candidate_applied_state;

        ImageView img_candidate;
        CardView cv_candidate_list;
    }
}
