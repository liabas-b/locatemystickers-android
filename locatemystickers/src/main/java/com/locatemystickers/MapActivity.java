package com.locatemystickers;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;
import com.locatemystickers.menu.MenuActivity;

public class MapActivity extends Fragment {

    private final String MAP_TAG = "maptag";

    private MenuActivity _context;
    private MapFragment _mapFragment;
    GoogleMap _googleMap;

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
            _mapFragment = MapFragment.newInstance();
            FragmentTransaction transaction = _context.getFragmentManager().beginTransaction();
            transaction.add(R.id.mapfrag, _mapFragment, MAP_TAG);
            transaction.commit();
        } else {
            _mapFragment = (MapFragment)_context.getFragmentManager().findFragmentByTag(MAP_TAG);

        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((_googleMap = _mapFragment.getMap()) != null)
            _googleMap.setMyLocationEnabled(true);

    }
}
