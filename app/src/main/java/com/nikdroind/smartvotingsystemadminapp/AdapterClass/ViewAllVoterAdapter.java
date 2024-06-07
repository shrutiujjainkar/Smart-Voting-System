package com.nikdroind.smartvotingsystemadminapp.AdapterClass;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nikdroind.smartvotingsystemadminapp.Activities.Admin.VoterList.ViewVoterCardActivity;
import com.nikdroind.smartvotingsystemadminapp.HomeActivity;
import com.nikdroind.smartvotingsystemadminapp.Pojoclass.pojo_Type_of_Election;
import com.nikdroind.smartvotingsystemadminapp.Pojoclass.pojo_View_All_Voters;
import com.nikdroind.smartvotingsystemadminapp.R;

import java.util.List;

public class ViewAllVoterAdapter extends BaseAdapter {

    List<pojo_View_All_Voters> list;
    Activity activity;
    TextView tv_no_record;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    Animation downtoup;

    public ViewAllVoterAdapter(List<pojo_View_All_Voters> list, Activity activity, TextView tv_no_record) {
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

        final ViewAllVoterAdapter.ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (v == null)
        {
            holder = new ViewAllVoterAdapter.ViewHolder();
            v = inflater.inflate(R.layout.list_view_all_voter,null);

            holder.voter_no = v.findViewById(R.id.txt_voter_voter_card_number);
            holder.full_name = v.findViewById(R.id.txt_voter_full_name);
            holder.btn_view_voter_card = v.findViewById(R.id.btn_view_voter_card);
            holder.progress = v.findViewById(R.id.progress);

            v.setTag(holder);
        }else
        {
            holder = (ViewAllVoterAdapter.ViewHolder) v.getTag();
        }

        final pojo_View_All_Voters obj = list.get(position);

        holder.voter_no.setText(obj.getVoter_no());
        holder.full_name.setText(obj.getFull_name());

        holder.btn_view_voter_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.progress.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        holder.progress.setVisibility(View.GONE);
                        Intent intent = new Intent(activity,ViewVoterCardActivity.class);
                        intent.putExtra("full_name",obj.getFull_name());
                        intent.putExtra("voter_no",obj.getVoter_no());
                        intent.putExtra("gender",obj.getGender());
                        intent.putExtra("mobile_no",obj.getMobile_no());
                        intent.putExtra("address",obj.getAddress());
                        intent.putExtra("your_voting_address",obj.getYour_voting_address());
                        activity.startActivity(intent);
                    }
                },2000);
            }
        });

        return v;
    }

    class ViewHolder
    {
        TextView voter_no,full_name,gender,mobile_no,address,your_voting_address;
        Button btn_view_voter_card;
        ProgressBar progress;
    }


}
