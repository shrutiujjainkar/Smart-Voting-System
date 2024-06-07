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


import com.nikdroind.smartvotingsystemadminapp.Activities.Admin.Result.Result_SingleScreen;
import com.nikdroind.smartvotingsystemadminapp.Pojoclass.pojo_Result_List;
import com.nikdroind.smartvotingsystemadminapp.R;

import java.util.List;

public class ResultListAdapter extends BaseAdapter {

    List<pojo_Result_List> list;
    Activity activity;
    TextView tv_no_record;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public ResultListAdapter(List<pojo_Result_List> list, Activity activity, TextView tv_no_record) {
        this.list = list;
        this.activity = activity;
        this.tv_no_record = tv_no_record;

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        editor = sharedPreferences.edit();
    }



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final com.nikdroind.smartvotingsystemadminapp.AdapterClass.ResultListAdapter.ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (view == null)
        {
            holder = new com.nikdroind.smartvotingsystemadminapp.AdapterClass.ResultListAdapter.ViewHolder();
            view = inflater.inflate(R.layout.list_result_list,null);

            holder.txt_election_type_name = view.findViewById(R.id.txt_result_list_election_type_name);
            holder.txt_election_place_name = view.findViewById(R.id.txt_result_list_election_place_name);
            holder.txt_election_seats = view.findViewById(R.id.txt_result_list_election_total_seat);
            holder.txt_election_majority = view.findViewById(R.id.txt_result_list_election_majority_seats_no);
            holder.btn_view_result = view.findViewById(R.id.btn_view_result);

            view.setTag(holder);
        }
        else
        {
            holder = (com.nikdroind.smartvotingsystemadminapp.AdapterClass.ResultListAdapter.ViewHolder) view.getTag();
        }

        final pojo_Result_List obj = list.get(i);
        holder.txt_election_place_name.setText(obj.getElection_type_name());
        holder.txt_election_place_name.setText(obj.getElection_place_name());
        holder.txt_election_seats.setText(obj.getElection_seats());
        holder.txt_election_majority.setText(obj.getElection_majority());

//        editor.putString("election_place",obj.getElection_place_name()).commit();

        holder.btn_view_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, Result_SingleScreen.class);
                intent.putExtra("election_type_name",obj.getElection_type_name());
                intent.putExtra("election_place_name",obj.getElection_place_name());
                intent.putExtra("election_total_seats",obj.getElection_seats());
                intent.putExtra("election_majority",obj.getElection_majority());
                activity.startActivity(intent);

                editor.putString("election_constituency",obj.getElection_place_name()).commit();
            }
        });


        return view;
    }

    class ViewHolder
    {
        TextView txt_election_type_name,txt_election_place_name,txt_election_seats,txt_election_majority;
        Button btn_view_result;
    }
}
