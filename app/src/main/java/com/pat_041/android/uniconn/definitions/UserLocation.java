package com.pat_041.android.uniconn.definitions;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.pat_041.android.uniconn.SearchingActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Abhijit on 14-10-2017.
 */

public class UserLocation implements LocationListener {
    public String userLatitude;
    public String userLongitude;
    public String userAddress; //Add a new TextView to your activity_main to display the address
    public LocationManager locationManager;
    //public String provider;
    public boolean locate;
    public Context mContext;
    public UserLocation(Context context) {
        mContext = context;
        try{
            Log.v("UserLocation Error : ","What is happening oi  thiuds worL" + "");
            locationManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);
            List<String> providers = locationManager.getProviders(true);
            Location bestLocation = null;
            Log.v("UserLocation Error : ","What is happening" + "");
            for (String provider : providers) {
                Location l = locationManager.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                    // Found best last known location: %s", l);
                    bestLocation = l;
                }
            }
            //Location location = locationManager.getLastKnownLocation(provider);
            if (bestLocation != null) {
                locate = true;
                System.out.println("Location " + bestLocation + " has been selected.");
                onLocationChanged(bestLocation);
            } else {
                locate = false;
                userLatitude = "Latitude is not available";
                userLongitude = "Longitude is not Available";
                Log.v("UserLocation Error : ","No Location is Available");
            }
        }catch(SecurityException e){
            e.printStackTrace();
            Log.v("UserLocation Error : ","Constructor Error");
            locate = false;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        //You had this as int. It is advised to have Lat/Loing as double.
        double lat = location.getLatitude();
        double lng = location.getLongitude();

        Geocoder geoCoder = new Geocoder(mContext, Locale.getDefault());
        try {
            List<Address> address = geoCoder.getFromLocation(lat, lng, 1);
            String stateName = address.get(0).getAddressLine(0);
            Log.v("state name",""+stateName);
            userLongitude = String.valueOf(lng);
            userLatitude = String.valueOf(lat);
            userAddress = stateName;
        } catch (IOException e) {
            // Handle IOException
            e.printStackTrace();
            locate = false;
            Log.v("UserLocation Error : ","IOException");
        } catch (NullPointerException e) {
            // Handle NullPointerException
            e.printStackTrace();
            locate = false;
            Log.v("UserLocation Error : ","NUll Pointer Exc");
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(mContext, "Enabled new provider " + provider, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(mContext, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }
    public boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
}
