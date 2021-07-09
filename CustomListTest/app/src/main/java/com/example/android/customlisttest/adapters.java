package com.example.android.customlisttest;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class adapters extends ArrayAdapter<custclass> {
    public adapters(MainActivity Context, ArrayList<custclass> cards) {
        super(Context,0,cards);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View views = convertView;
        if (views == null) {
            views = LayoutInflater.from(getContext()).inflate(R.layout.activity_frames, parent, false);
        }
        custclass current = getItem(position);
        Date dateObject=new Date(current.getClasses());
        String formattedDate=formatDate(dateObject);
        String formattedTime=formatTime(dateObject);
        TextView tv = views.findViewById(R.id.names);
        tv.setText(current.getNames());
        TextView tv3 = views.findViewById(R.id.address);
        tv3.setText(current.getAddress());
        TextView tv2 = views.findViewById(R.id.classes);
        tv2.setText(formattedDate);
        TextView tv4=views.findViewById(R.id.time);
        tv4.setText(formattedTime);
        return  views;
    }
    private String formatDate(Date dateObject){
        SimpleDateFormat formattedDate=new SimpleDateFormat("DD-MM-YYYY");
        return formattedDate.format(dateObject);
    }
    private String formatTime(Date dateObject){
        SimpleDateFormat formattedtime=new SimpleDateFormat("HH:MM a");
        return formattedtime.format(dateObject);
    }
}