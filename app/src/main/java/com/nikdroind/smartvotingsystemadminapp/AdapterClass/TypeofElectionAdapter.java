package com.nikdroind.smartvotingsystemadminapp.AdapterClass;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nikdroind.smartvotingsystemadminapp.HomeActivity;
import com.nikdroind.smartvotingsystemadminapp.Pojoclass.pojo_Type_of_Election;
import com.nikdroind.smartvotingsystemadminapp.R;

import java.util.List;

public class TypeofElectionAdapter extends BaseAdapter {

    List<pojo_Type_of_Election> list;
    Activity activity;
    TextView tv_no_record;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    Animation downtoup;

    public TypeofElectionAdapter(List<pojo_Type_of_Election> list, Activity activity, TextView tv_no_record) {
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

        final TypeofElectionAdapter.ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (v == null)
        {
            holder = new TypeofElectionAdapter.ViewHolder();
            v = inflater.inflate(R.layout.list_type_of_election,null);

            holder.title = v.findViewById(R.id.txt_types_of_election_title);
            holder.type1 = v.findViewById(R.id.txt_types_of_election_type1);
            holder.type2 = v.findViewById(R.id.txt_types_of_election_type2);
            holder.type3 = v.findViewById(R.id.txt_types_of_election_type3);
            holder.type4 = v.findViewById(R.id.txt_types_of_election_type4);
            holder.img_cancel = v.findViewById(R.id.img_cancel);

            v.setTag(holder);
        }else
        {
            holder = (TypeofElectionAdapter.ViewHolder) v.getTag();
        }

        final pojo_Type_of_Election obj = list.get(position);

        holder.title.setText(obj.getTitle());
        holder.type1.setText(obj.getType1());
        holder.type2.setText(obj.getType2());
        holder.type3.setText(obj.getType3());
        holder.type4.setText(obj.getType4());

        holder.img_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(activity, HomeActivity.class));
                activity.overridePendingTransition(R.anim.goup,R.anim.godown);
            }
        });

        return v;
    }

    class ViewHolder
    {
        TextView title,type1,type2,type3,type4;
        ImageView img_cancel;
    }
}
