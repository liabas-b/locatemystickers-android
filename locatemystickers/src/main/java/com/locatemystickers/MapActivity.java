package com.locatemystickers;

import android.app.FragmentTransaction;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.locatemystickers.json.StickersJSON;
import com.locatemystickers.menu.MenuActivity;
import com.locatemystickers.type.Sticker;
import com.locatemystickers.utils.MyMapFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MapActivity extends Fragment implements MyMapFragment.MapCallBack {

    private final String MAP_TAG = "maptag";

    private MenuActivity _context;
    private MyMapFragment _mapFragment = null;
    private GoogleMap _googleMap = null;
    private Timer _timerStickers;
    private Map<Integer, Marker> _mMaker;
    private ProgressBar _pb;
    private List<Sticker> _lstickers;

    public static Fragment newInstance(MenuActivity context) {
        return new MapActivity(context);
    }

    public MapActivity(MenuActivity context) {
        _context = context;
        _lstickers = new ArrayList<Sticker>();
        _timerStickers = new Timer();
        _mMaker = new HashMap<Integer, Marker>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_activity, container, false);
        _pb = (ProgressBar)view.findViewById(R.id.progressBar);
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
        _googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        TimerTask timerTaskSitckers = new TimerTask() {
            @Override
            public void run() {
                new MarkersSync().execute();
            }
        };
        _timerStickers.schedule(timerTaskSitckers, 0, 2000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

// -----------------------------------------------------------------------
//  Display in background the Marker on the map
// -----------------------------------------------------------------------

    public class MarkersSync extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            _lstickers = new StickersJSON(Singleton.getInstance()._id).readAllStickers();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            _context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    _pb.setVisibility(View.VISIBLE);
                }
            });
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            for (Sticker s: _lstickers) {
                if (!s.get_last_latitude().equals("null") && !s.get_last_longitude().equals("null")) {
                    _mMaker.put(s.get_id(), _googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(Double.parseDouble(s.get_last_latitude()),
                                    Double.parseDouble(s.get_last_longitude())))
                            .title(s.get_name())
                            .snippet(s.get_text())
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher))));
                }
            }
            _context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    _pb.setVisibility(View.INVISIBLE);
                }
            });
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);
            _context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    _pb.setVisibility(View.INVISIBLE);
                }
            });
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            _context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    _pb.setVisibility(View.INVISIBLE);
                }
            });
        }
    }
}
