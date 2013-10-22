package com.locatemystickers;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;

public class MapActivity extends Fragment {
    private ScreenView _sv;
    private GoogleMap _map;
    private SupportMapFragment _mapView;
    private FragmentTransaction _fragTrans;

    static MapActivity newInstance(ScreenView sv) {
        return new MapActivity(sv);
    }

    public MapActivity(ScreenView sv) {
        _sv = sv;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.map_activity, container);
        _mapView = ((SupportMapFragment)_sv.getSupportFragmentManager().findFragmentById(R.id.mapView));
        if (_mapView != null)
        {
            _map = _mapView.getMap();
            _map.setMyLocationEnabled(true);
            _map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        FragmentTransaction _fragTrans = _sv.getSupportFragmentManager().beginTransaction();
        _fragTrans.remove(_mapView);
        _fragTrans.commit();
    }
}
