package com.locatemystickers;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.locatemystickers.menu.MenuActivity;
import com.locatemystickers.utils.MyMapFragment;

public class MapActivity extends Fragment implements MyMapFragment.MapCallBack {

    private final String MAP_TAG = "maptag";

    private MenuActivity _context;
    private MyMapFragment _mapFragment = null;
    GoogleMap _googleMap = null;

    public static Fragment newInstance(MenuActivity context) {
        return new MapActivity(context);
    }

    public MapActivity(MenuActivity context) {
        _context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_activity, container, false);
        _mapFragment = MyMapFragment.newInstance();
        _mapFragment.setMapCallBack(this);
        FragmentTransaction transaction = _context.getFragmentManager().beginTransaction();
        transaction.add(R.id.mapfrag, _mapFragment, MAP_TAG);
        transaction.commit();
        return view;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        _googleMap = _mapFragment.getMap();
        if (_googleMap != null) {
            setUpMap();
        }
    }

    private void setUpMap() {
        _googleMap.setMyLocationEnabled(true);
        // code HERE
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _context.getFragmentManager().beginTransaction().remove(_mapFragment).commit();
    }
}
