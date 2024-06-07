package com.nikdroind.smartvotingsystemadminapp.AdapterClass;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nikdroind.smartvotingsystemadminapp.Activities.Admin.CurrentElection.CurrentElectionCandidateActivity;
import com.nikdroind.smartvotingsystemadminapp.Pojoclass.pojo_Current_Election_Type_Constituency;
import com.nikdroind.smartvotingsystemadminapp.R;

import java.util.List;

public class CurrentElectionTypeConstituencyAdapter extends BaseAdapter {

    List<pojo_Current_Election_Type_Constituency> list;
    Activity activity;
    TextView tv_no_record;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public CurrentElectionTypeConstituencyAdapter(List<pojo_Current_Election_Type_Constituency> list, Activity activity, TextView tv_no_record) {
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

        final CurrentElectionTypeConstituencyAdapter.ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (v == null)
        {
            holder = new CurrentElectionTypeConstituencyAdapter.ViewHolder();
            v = inflater.inflate(R.layout.list_current_election_type_constituency,null);

            holder.current_election_type = v.findViewById(R.id.txt_current_election_type_name);
            holder.current_election_constituency = v.findViewById(R.id.txt_current_election_constituency);
            holder.current_election_date = v.findViewById(R.id.txt_current_election_date);
            holder.btn_view_more = v.findViewById(R.id.btn_view_more);
            holder.progress = v.findViewById(R.id.progress);

            v.setTag(holder);
        }else
        {
            holder = (CurrentElectionTypeConstituencyAdapter.ViewHolder) v.getTag();
        }

        final pojo_Current_Election_Type_Constituency obj = list.get(position);

        holder.current_election_type.setText(obj.getElection_type());
        holder.current_election_constituency.setText(obj.getElection_constituency());
        holder.current_election_date.setText(obj.getElection_date());

        holder.btn_view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.progress.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        holder.progress.setVisibility(View.GONE);
                        Intent intent = new Intent(activity, CurrentElectionCandidateActivity.class);
                        editor.putString("election_constituency",obj.getElection_constituency()).commit();
                        editor.putString("election_date",obj.getElection_date()).commit();
                        activity.startActivity(intent);
                    }
                },2000);
            }
        });

        return v;
    }

    class ViewHolder
    {
        TextView current_election_type,current_election_constituency,current_election_date;
        Button btn_view_more;
        ProgressBar progress;
    }


}
