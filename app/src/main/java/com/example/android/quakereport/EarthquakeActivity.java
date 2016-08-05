/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
       /* ArrayList<Earthquake> earthquakes = new ArrayList<>();
        earthquakes.add(new Earthquake("5.0", "San Francisco", "Feb 2, 2017"));
        earthquakes.add(new Earthquake("4.0", "London", "June 8, 2016"));
        earthquakes.add(new Earthquake("6.4", "Tokyo", "July 12, 2016"));
        earthquakes.add(new Earthquake("9.0", "Mexico City", "January 4, 2016"));
        earthquakes.add(new Earthquake("3.0", "Moscow", "May 24, 2016"));
        earthquakes.add(new Earthquake("8.8", "Rio de Janeiro", "July 17, 2016"));
        earthquakes.add(new Earthquake("5.5", "Paris", "March 30, 2016")); */
        ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();


        JSONObject jsonObject;
        JSONArray jsonArray;

        //needed  mag  place time   in type: Feature

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.data_item_list, earthquakes);
        EarthquakeAdapter adapter = new EarthquakeAdapter(this,earthquakes);


        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

      /*  earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent earthquakeIntent = new Intent(Earthquake.this, );
                startActivity(earthquakeIntent);
            }
        });*/
    }
}
