package com.project.wayne.addressmap.location;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by waynehuang on 2017/2/24.
 */

public class FollowMeHelper implements LocationSource, LocationListener {
    private final static String TAG = FollowMeHelper.class.getSimpleName();

    private Context mContext;
    private GoogleMap mMap;
    private LocationManager locationManager;
    private final Criteria criteria = new Criteria();
    private String bestAvailableProvider;
//    private GoogleMap.OnMyLocationChangeListener mListener;

    public FollowMeHelper(Context cxt, GoogleMap map){
        mContext = cxt;
        mMap = map;

        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAltitudeRequired(true);
        criteria.setBearingRequired(true);
        criteria.setSpeedRequired(true);
        criteria.setCostAllowed(true);

//        mListener = new GoogleMap.OnMyLocationChangeListener() {
//            @Override
//            public void onMyLocationChange(Location location) {
//
//            }
//        };
//        mMap.setOnMyLocationChangeListener();

    }

    private void getBestAvailableProvider() {
        bestAvailableProvider = locationManager.getBestProvider(criteria, true);
    }

    @Override
    public void onLocationChanged(Location location) {
        mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("Current Location"));
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

    }

    @Override
    public void deactivate() {

    }
}
