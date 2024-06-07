package com.nikdroind.smartvotingsystemadminapp.AdapterClass;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.nikdroind.smartvotingsystemadminapp.Pojoclass.pojo_Result_Single;
import com.nikdroind.smartvotingsystemadminapp.R;

import java.util.List;

public class ResultSingleAdapter extends BaseAdapter {

    List<pojo_Result_Single> list;
    Activity activity;
    TextView tv_no_record;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public ResultSingleAdapter(List<pojo_Result_Single> list, Activity activity, TextView tv_no_record) {
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
    public View getView(int position, View view, ViewGroup viewGroup) {

        final com.nikdroind.smartvotingsystemadminapp.AdapterClass.ResultSingleAdapter.ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);

        if (view == null)
        {
            holder = new com.nikdroind.smartvotingsystemadminapp.AdapterClass.ResultSingleAdapter.ViewHolder();
            view = inflater.inflate(R.layout.list_result_single,null);


            holder.txt_party_name = view.findViewById(R.id.txt_result_single_party_name);
            holder.txt_party_won = view.findViewById(R.id.txt_result_single_party_won);
            holder.txt_party_leading = view.findViewById(R.id.txt_result_single_party_leading);
            holder.txt_party_total = view.findViewById(R.id.txt_result_single_party_total);

            view.setTag(holder);
        }
        else
        {
            holder = (com.nikdroind.smartvotingsystemadminapp.AdapterClass.ResultSingleAdapter.ViewHolder) view.getTag();
        }

        final pojo_Result_Single obj = list.get(position);
        holder.txt_party_name.setText(obj.getParty_name());
        holder.txt_party_won.setText(obj.getParty_win());
        holder.txt_party_leading.setText(obj.getParty_leading());
        holder.txt_party_total.setText(obj.getParty_total());

        return view;
    }

    class ViewHolder
    {
        TextView txt_party_name,txt_party_won,txt_party_leading,txt_party_total;
    }
}
