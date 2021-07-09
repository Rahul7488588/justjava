package com.example.android.customlisttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String SAMPLE_JSON_RESPONSE =  "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<custclass> cards=new ArrayList<custclass>();
        ListView listV=findViewById(R.id.lst);
        URL url=null;
        InputStream iostream=null;
        try {
          url=new URL(SAMPLE_JSON_RESPONSE);
        } catch ( MalformedURLException e ) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

        } catch ( IOException e ) {
            e.printStackTrace();
        }

        try {
// TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
// build up a list of Earthquake objects with the corresponding data.
            JSONObject root=new JSONObject();
            JSONArray arrayOfRoot=root.getJSONArray("features");
            for(int i=0;i<arrayOfRoot.length();i++){
                JSONObject objectInArrays=arrayOfRoot.getJSONObject(i);
                JSONObject properties=objectInArrays.getJSONObject("properties");
                String magnitude=properties.getString("mag");
                String place=properties.getString("place");
                long time=properties.getLong("time");
                String ur=properties.getString("url");
                cards.add(new custclass(magnitude, place, time, ur));
            }
        } catch ( JSONException e) {
// If an error is thrown when executing any of the above statements in the "try" block,
// catch the exception here, so the app doesn't crash. Print a log message
// with the message from the exception.
            Log.e("MainActivity", "Problem parsing the earthquake JSON results", e);
        }
       final adapters ada=new adapters(this,cards);
        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                custclass current=ada.getItem(i);
                Uri siteuri=Uri.parse(current.getUrls());
                Intent websiteIntent=new Intent(Intent.ACTION_VIEW,siteuri);
                startActivity(websiteIntent);
            }
        });
        listV.setAdapter(ada);
    }
}