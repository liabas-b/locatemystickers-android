package com.locatemystickers;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MapActivity extends Fragment {
    private final int MARKER_UPDATE_INTERVAL = 6000;

    private ScreenView _sv;
    private GoogleMap _map;
    private SupportMapFragment _mapView = null;
    private List<Sticker> _lstickers;
    private Map<Integer, Marker> _mMaker = new HashMap<Integer, Marker>();
    private ProgressDialog _pd;

    static MapActivity newInstance(ScreenView sv) {
        return new MapActivity(sv);
    }

    public MapActivity(ScreenView sv) {
        _sv = sv;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _pd = _pd.show(_sv, "Please wait", "Loading stickers...");
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Singleton.getInstance()._listStickers = Singleton.getInstance()._sj.readAllStickers();
                _pd.dismiss();
            }
        });
        t.start();
        View v = inflater.inflate(R.layout.map_activity, container);
        _mapView = ((SupportMapFragment)_sv.getSupportFragmentManager().findFragmentById(R.id.mapView));
        if (_mapView != null)
        {
            _map = _mapView.getMap();
            _map.setMyLocationEnabled(true);
            _map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            try {
                t.join();
            } catch (InterruptedException e) {
                Log.e(MapActivity.class.getName(), e.getMessage());
            }
            for (Sticker s : Singleton.getInstance()._listStickers) {
                if (s != null) {
                    if (!s.get_last_latitude().equals("null") && !s.get_last_longitude().equals("null")) {
                        _mMaker.put(s.get_id(), _map.addMarker(new MarkerOptions()
                                .position(new LatLng(Double.parseDouble(s.get_last_latitude()),
                                                     Double.parseDouble(s.get_last_longitude())))
                                .title(s.get_name())
                                .snippet(s.get_text())
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher))
                        ));
                    }
                }

            }
           // _handler.postDelayed(_runnable, MARKER_UPDATE_INTERVAL);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (_handler != null) {
            _handler.removeCallbacks(_runnable);
        }
        FragmentTransaction fragTrans = _sv.getSupportFragmentManager().beginTransaction();
        fragTrans.remove(_mapView);
        fragTrans.commit();
    }

    private android.os.Handler _handler = new android.os.Handler();
    private Runnable _runnable = new Runnable() {
        @Override
        public void run() {
            /*Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    Singleton.getInstance()._listStickers = Singleton.getInstance()._sj.readAllStickers();
                }
            });
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                Log.e(MapActivity.class.getName(), e.getMessage());
            }*/
            for (Sticker s : Singleton.getInstance()._listStickers) {
                if (!s.get_last_latitude().equals("null") && !s.get_last_longitude().equals("null"))
                {
                    for (Map.Entry<Integer, Marker> entry : _mMaker.entrySet()) {
                        if (s.get_id() == entry.getKey()) {
                            if (Double.parseDouble(s.get_last_latitude()) == entry.getValue().getPosition().latitude &&
                                    Double.parseDouble(s.get_last_longitude()) == entry.getValue().getPosition().longitude)
                                entry.getValue().setPosition(new LatLng(Double.parseDouble(s.get_last_latitude()),
                                        Double.parseDouble(s.get_last_longitude())));
                        }
                    }
                }
            }
            //_handler.postDelayed(this, MARKER_UPDATE_INTERVAL);
        }
    };;
}
