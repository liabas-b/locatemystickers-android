package com.locatemystickers;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.List;

public class MapActivity extends MapFragment {
    private ScreenView _sv;
    private GoogleMap _map;
    private SupportMapFragment _mapView;

    static MapActivity newInstance(ScreenView sv) {
        return new MapActivity(sv);
    }

    public MapActivity(ScreenView sv) {
        _sv = sv;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null)
        {
            inflater.inflate(R.layout.map_activity, container);
            _mapView = ((SupportMapFragment) _sv.getSupportFragmentManager().findFragmentById(R.id.mapView));
            _map = _mapView.getMap();
            _map.setMyLocationEnabled(true);
            _map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
