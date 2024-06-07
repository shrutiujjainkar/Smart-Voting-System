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
import com.nikdroind.smartvotingsystemadminapp.Pojoclass.pojo_Feedback_List;
import com.nikdroind.smartvotingsystemadminapp.R;

import java.util.List;

public class FeedbackListAdapter extends BaseAdapter {

    List<pojo_Feedback_List> list;
    Activity activity;
    TextView tv_no_record;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public FeedbackListAdapter(List<pojo_Feedback_List> list, Activity activity, TextView tv_no_record) {
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

        final com.nikdroind.smartvotingsystemadminapp.AdapterClass.FeedbackListAdapter.ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (view == null)
        {
            holder = new com.nikdroind.smartvotingsystemadminapp.AdapterClass.FeedbackListAdapter.ViewHolder();
            view = inflater.inflate(R.layout.list_feedback_list,null);

            holder.tv_feedback_username = view.findViewById(R.id.txt_feedback_username);
            holder.tv_feedback_mobile_no = view.findViewById(R.id.txt_feedback_mobile_no);
            holder.tv_feedback = view.findViewById(R.id.txt_feedback);

            view.setTag(holder);
        }else
        {
            holder = (com.nikdroind.smartvotingsystemadminapp.AdapterClass.FeedbackListAdapter.ViewHolder) view.getTag();
        }

        final pojo_Feedback_List obj = list.get(position);
        holder.tv_feedback_username.setText(obj.getFeedback_username());
        holder.tv_feedback_mobile_no.setText(obj.getFeedback_mobile_no());
        holder.tv_feedback.setText(obj.getFeedback());


        return view;
    }

    class ViewHolder
    {
        TextView tv_feedback_username,tv_feedback_mobile_no,tv_feedback;
    }
}
