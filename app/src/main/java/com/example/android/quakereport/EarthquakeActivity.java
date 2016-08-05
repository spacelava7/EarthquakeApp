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
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private static final String mREQUEST_URL =
    "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

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

    private class EarthquakeAsyncTask extends AsyncTask<URL, Void, Void>{

        @Override
        protected void doInBackground(URL... urls){
            //create URL
            URL url = createURL(mREQUEST_URL);
            String jsonResponse = "";

            try{
                jsonResponse = makeHTTPRequest(url);

            } catch (IOException e){
                Log.e(LOG_TAG, "IOException ", e);
            }

        }


    }

    private URL createURL(String stringURL){
        URL url = null;
        try{
            url = new URL(stringURL);
        } catch (MalformedURLException e){
            Log.e(LOG_TAG, "Error creating URL", e);
            return null;
        }
        return url;
    }

    /**
     * Makes an HTTP request to the given URL
     * @param url - given by the user
     * @return jsonResponse - inputStream read the data from the URL with the provided request method in this
     * case it is a GET method
     * @throws IOException any error that might occure while connecting or reading the input streams
     */
    private String makeHTTPRequest(URL url ) throws IOException{
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try{
            //set up the http connection
            urlConnection =  (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // handle the http connection

        }catch (IOException e){
            Log.e(LOG_TAG, "IOException", e);
            return null;
        } finally {

            //if url connection is on must disconnect after obtaining the data
            if (urlConnection != null){
                urlConnection.disconnect();
            }
        }

        return jsonResponse;
    }
}
