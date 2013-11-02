package com.locatemystickers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ged74.swipelistview.SwipeListView;
import com.locatemystickers.adapter.StickerAdapter;
import com.locatemystickers.json.StickersJSON;

public class StickerActivity extends Fragment {
	private ProgressDialog _pd;
	private List<Sticker> _ls;
    private ScreenView _sv;

    static StickerActivity newInstance(ScreenView sv) {
        return new StickerActivity(sv);
    }

    public StickerActivity(ScreenView sv) {
        _sv = sv;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = View.inflate(_sv, R.layout.home_activity, container);

        SwipeListView swipelistview = (SwipeListView)v.findViewById(R.id.lvStickers);
        //  TODO check connexion from webservice
        Singleton.getInstance()._sj = new StickersJSON(1);
        _ls = new ArrayList<Sticker>();
        _pd = _pd.show(_sv, "Please wait", "Loading list of stickers...");
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    _ls = Singleton.getInstance()._sj.readAllStickers();
                    Singleton.getInstance()._listStickers = _ls;
                    Singleton.getInstance()._nbr_stickers = _ls.size();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                _pd.dismiss();
            }
        });
        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        swipelistview.setAdapter(new StickerAdapter(_sv, _ls, swipelistview));
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
