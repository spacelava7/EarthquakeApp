package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
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
        //set background
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

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

    //sets background color Id depending on the amount
    private int getMagnitudeColor(double magnitude){
        int magnitudeResourceColorId;
        int magnitudeValue = (int) Math.floor(magnitude);

        switch (magnitudeValue){
            case 1: case 2:
                magnitudeResourceColorId = R.color.colorMag12;
                break;
            case 3: case 4:
                magnitudeResourceColorId = R.color.colorMag34;
                break;
            case 5: case 6:
                magnitudeResourceColorId = R.color.colorMag56;
                break;
            case 7: case 8:
                magnitudeResourceColorId = R.color.colorMag78;
                break;
            case  9:
                magnitudeResourceColorId  = R.color.colorMag910;
                break;
            default:
                magnitudeResourceColorId = R.color.colorMag910;
                break;
        }
        //ContextCompat getColor() to convert the color resource ID into an actual integer color value
        return ContextCompat.getColor(getContext(), magnitudeResourceColorId);
    }

}
