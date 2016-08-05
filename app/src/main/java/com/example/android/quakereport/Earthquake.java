package com.example.android.quakereport;

/**
 * Created by yadia on 7/29/16.
 */
public class Earthquake {

    private double mMagnitude;
    private String mPlace;
    private long mTimeInMilliseconds;

    /**
     *
     * @param magnitude  -- magnitude of the earthquake
     * @param place
     * @param timeMiliseconds  - time is given in millseconds using UNIX
     */
    public Earthquake(double magnitude, String place, long timeMiliseconds ){
        mMagnitude = magnitude;
        mPlace = place;
        mTimeInMilliseconds = timeMiliseconds;
    }

    public double getMagnitude(){
        return mMagnitude;
    }

    public void setMagnitude(double magnitude){
        mMagnitude  = magnitude;
    }

    public String getmPlace(){
        return mPlace;
    }

    public void setPlace(String place){
        mPlace = place;
    }

    public long getTimeInMilliseconds(){
        return mTimeInMilliseconds;
    }

    public void setTime(long time){
        mTimeInMilliseconds = time;
    }
}
