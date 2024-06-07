package com.nikdroind.smartvotingsystemadminapp.AdapterClass;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


import com.nikdroind.smartvotingsystemadminapp.Activities.Admin.CandidateList.Candidate_List_By_Candidate_Election_Type_and_ConstituencyScreen;
import com.nikdroind.smartvotingsystemadminapp.Pojoclass.pojo_Candidate_Election_Type_State;
import com.nikdroind.smartvotingsystemadminapp.R;

import java.util.List;

public class CandidateElectionTypeStateAdapter extends BaseAdapter {

    List<pojo_Candidate_Election_Type_State> list;
    Activity activity;
    TextView tv_no_record;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public CandidateElectionTypeStateAdapter(List<pojo_Candidate_Election_Type_State> list, Activity activity, TextView tv_no_record) {
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

        final com.nikdroind.smartvotingsystemadminapp.AdapterClass.CandidateElectionTypeStateAdapter.ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (v == null)
        {
            holder = new com.nikdroind.smartvotingsystemadminapp.AdapterClass.CandidateElectionTypeStateAdapter.ViewHolder();
            v = inflater.inflate(R.layout.list_candidate_election_type_state,null);

            holder.txt_candidate_election_type = v.findViewById(R.id.txt_candidate_election_type);
            holder.txt_candidate_election_state = v.findViewById(R.id.txt_candidate_election_state);
            holder.btn_view_more = v.findViewById(R.id.btn_view_more);

            v.setTag(holder);
        }else
        {
            holder = (com.nikdroind.smartvotingsystemadminapp.AdapterClass.CandidateElectionTypeStateAdapter.ViewHolder) v.getTag();
        }

        final pojo_Candidate_Election_Type_State obj = list.get(position);
        holder.txt_candidate_election_type.setText(obj.getCandidate_election_type());
        holder.txt_candidate_election_state.setText(obj.getCandidate_election_state());


        holder.btn_view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(activity, Candidate_List_By_Candidate_Election_Type_and_ConstituencyScreen.class));
                editor.putString("election_constituency",obj.getCandidate_election_state()).commit();
                activity.finish();

            }
        });

        return v;
    }

    class ViewHolder
    {
        TextView txt_candidate_election_type,txt_candidate_election_state;
        Button btn_view_more;
    }
}
