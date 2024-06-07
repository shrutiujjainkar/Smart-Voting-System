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

import com.nikdroind.smartvotingsystemadminapp.Activities.Admin.UpcomingElection.Upcoming_Election_SingleScreen;
import com.nikdroind.smartvotingsystemadminapp.Pojoclass.pojo_Upcoming_Election;
import com.nikdroind.smartvotingsystemadminapp.R;

import java.util.List;

public class UpcomingElectionAdapter extends BaseAdapter {

    List<pojo_Upcoming_Election> list;
    Activity activity;
    TextView tv_no_record;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public UpcomingElectionAdapter(List<pojo_Upcoming_Election> list, Activity activity, TextView tv_no_record) {
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
    public View getView(int position, View view, ViewGroup parent) {
        final UpcomingElectionAdapter.ViewHolder holder;
        final LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (view == null)
        {
            holder = new UpcomingElectionAdapter.ViewHolder();
            view = inflater.inflate(R.layout.list_upcoming_election,null);

            holder.election_type = view.findViewById(R.id.txt_upcoming_election_type_name);
            holder.election_state = view.findViewById(R.id.txt_upcoming_election_state);
            holder.btn_view_more = view.findViewById(R.id.btn_upcoming_election_view_more);

            view.setTag(holder);
        }else
        {
            holder = (UpcomingElectionAdapter.ViewHolder) view.getTag();
        }

        final pojo_Upcoming_Election obj = list.get(position);
        holder.election_type.setText(obj.getElection_type());
        holder.election_state.setText(obj.getElection_state());


        holder.btn_view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(activity, Upcoming_Election_SingleScreen.class);
                intent.putExtra("election_type", obj.getElection_type());
                intent.putExtra("election_state",obj.getElection_state());
                intent.putExtra("total_seat_title",obj.getTotal_seat_title());
                intent.putExtra("total_seat_no",obj.getTotal_seat_no());
                intent.putExtra("date_of_polling_title",obj.getDate_of_polling_title());
                intent.putExtra("phase1_title",obj.getPhase1_title());
                intent.putExtra("phase1_date",obj.getPhase1_date());
                intent.putExtra("phase2_title",obj.getPhase2_title());
                intent.putExtra("phase2_date",obj.getPhase2_date());
                intent.putExtra("phase3_title",obj.getPhase3_title());
                intent.putExtra("phase3_date",obj.getPhase3_date());
                intent.putExtra("phase4_title",obj.getPhase4_title());
                intent.putExtra("phase4_date",obj.getPhase4_date());
                intent.putExtra("counting_date_title",obj.getCounting_date_title());
                intent.putExtra("counting_date",obj.getCounting_date());
                activity.startActivity(intent);

            }
        });

        return view;
    }

    class ViewHolder
    {
        TextView election_type,election_state;
        Button btn_view_more;
    }
}
