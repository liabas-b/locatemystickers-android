package com.locatemystickers;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.locatemystickers.utils.Utils;

public class StickerDetailActivity extends Fragment {
    private ScreenView _sv;
	private int _id_sticker;
	private Sticker _sticker;
    private GoogleMap _map;
    private ProgressDialog _pd;
    private SupportMapFragment _supportMapFragment;
    private LatLngBounds.Builder _builder;
    private LatLng _llngsticker;
    private LatLng _llngcurr;


    public static StickerDetailActivity newInstance(ScreenView sv, int id_sticker) {
        return new StickerDetailActivity(sv, id_sticker);
    }

    public StickerDetailActivity(ScreenView sv, int id_sticker) {
        _sv = sv;
        _id_sticker = id_sticker;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _pd = _pd.show(_sv, "Please wait", "Loading sticker...");
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                _sticker = Singleton.getInstance()._sj.readSticker(_id_sticker);
                _pd.dismiss();
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            Log.e(StickerDetailActivity.class.getName(), e.getMessage());
        }
        View v = inflater.inflate(R.layout.sticker_details, container);
        _supportMapFragment = ((SupportMapFragment)_sv.getSupportFragmentManager().findFragmentById(R.id.map));
        if (_supportMapFragment != null)
            _map = _supportMapFragment.getMap();
        if (_sticker != null && _map != null) {
            _map.getUiSettings().setZoomControlsEnabled(false);
            _map.setMyLocationEnabled(true);
            _map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            if (!_sticker.get_last_latitude().equals("null") && !_sticker.get_last_longitude().equals("null"))
            {
                _llngsticker = new LatLng(Double.parseDouble(_sticker.get_last_latitude()),
                        Double.parseDouble(_sticker.get_last_longitude()));
                Location l = Utils.getLastKnownLocation(_sv);
                _llngcurr = new LatLng(l.getLatitude(),l.getLongitude());
                _map.addMarker(new MarkerOptions()
                        .position(_llngsticker)
                        .title(_sticker.get_name())
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
                _map.addPolyline(new PolylineOptions()
                    .add(_llngcurr)
                    .add(_llngsticker)
                    .width(2.5f)
                    .color(Color.RED));
                _builder = new LatLngBounds.Builder();
                _builder.include(_llngsticker);
                _builder.include(_llngcurr);
            }
        } else {
            Log.e(StickerDetailActivity.class.getName(), "error map null sticker null");
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (_builder != null)
        {
            CameraUpdate cu;
            _map.moveCamera(cu = CameraUpdateFactory.newLatLngBounds(_builder.build(),
                    this.getResources().getDisplayMetrics().widthPixels,
                    this.getResources().getDisplayMetrics().heightPixels,
                    20));
            _map.animateCamera(cu);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        android.support.v4.app.FragmentTransaction fragTrans = _sv.getSupportFragmentManager().beginTransaction();
        fragTrans.remove(_supportMapFragment);
        fragTrans.commit();
    }


}
