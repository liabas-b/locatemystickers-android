package com.locatemystickers.utils;

import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

public class MyMapFragment extends MapFragment {
    private MyMapFragment.MapCallBack _callBack;

    public static MyMapFragment newInstance() {
        return new MyMapFragment();
    }

    public void setMapCallBack(MapCallBack callBack) {
        _callBack = callBack;
    }

    public static interface MapCallBack {
        public void onMapReady(GoogleMap map);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (_callBack != null) { _callBack.onMapReady(getMap()); }
    }
}
