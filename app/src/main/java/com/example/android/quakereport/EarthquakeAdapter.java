package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yadia on 7/29/16.
 */
public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    /**
     *
     * @param context of the app activity
     * @param list list of objects from the Earthquake class
     */
    public EarthquakeAdapter(Context context, List<Earthquake> list){
        super(context, 0, list);
    }

    //returns list with custom listView
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listView = convertView;
        if (listView == null){
            listView = LayoutInflater.from(getContext()).inflate(
                    R.layout.data_item_list, parent, false);
        }
        //generates current eartquake data from the current listView position
        Earthquake currentEarthquake = getItem(position);

        TextView magnitudeView = (TextView) listView.findViewById(R.id.label_magnitude);
        // Format the magnitude to show 1 decimal place
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        magnitudeView.setText(formattedMagnitude);

        String originalLocation = currentEarthquake.getmPlace();
        String primaryLocation;
        String locationOffset;
        final String LOCATION_SEPARATOR =  " of ";
        if (originalLocation.contains(LOCATION_SEPARATOR)){
            String[] parts = originalLocation.split(LOCATION_SEPARATOR); // point to where the string should break
            locationOffset = parts[0] + LOCATION_SEPARATOR;  //part 1 of the string
            primaryLocation = parts[1];    //part 2 of the string
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        TextView coordinatesView = (TextView) listView.findViewById(R.id.label_coordinates);
        coordinatesView.setText(locationOffset);

        TextView placeView = (TextView) listView.findViewById(R.id.label_place);
        placeView.setText(primaryLocation);

        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());
        TextView dateView = (TextView) listView.findViewById(R.id.label_date);
        String formattedDate = formatDate(dateObject);
        dateView.setText(formattedDate);

        TextView timeView = (TextView) listView.findViewById(R.id.label_time);
        String formattedTime = formatTime(dateObject);
        timeView.setText(formattedTime);


        return listView;
    }

    /**
     * formates date to the desired simple format. For more formats
     * @param dateobject
     * @return formatted date
     */
    private String formatDate(Date dateobject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateobject);
    }

    private String formatTime (Date dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a");
        return dateFormat.format(dateObject);

    }
    //DEcimal Formatter to only show one decimal point
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

}
