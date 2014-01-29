package com.locatemystickers;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.locatemystickers.menu.MenuActivity;

public class MapActivity extends Fragment {

    private MenuActivity _context;

    public static Fragment newInstance(MenuActivity context) {
        return new MapActivity(context);
    }

    public MapActivity(MenuActivity context) {
        _context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_activity, container, false);
        if (savedInstanceState == null) {
            SupportMapFragment mapView = (SupportMapFragment)_context.getSupportFragmentManager().findFragmentById(R.id.mapView);
            GoogleMap googleMap = mapView.getMap();
        }
        return view;
    }
}
