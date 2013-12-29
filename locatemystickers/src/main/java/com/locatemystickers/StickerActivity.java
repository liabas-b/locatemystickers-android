package com.locatemystickers;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ged74.swipelistview.SwipeListView;
import com.locatemystickers.adapter.StickerAdapter;
import com.locatemystickers.json.StickersJSON;
import com.locatemystickers.menu.MenuActivity;
import com.locatemystickers.type.Sticker;

import java.util.ArrayList;

public class StickerActivity extends Fragment {

    private MenuActivity _context;
    private ProgressDialog _pd;

    public static Fragment newInstance(MenuActivity context) {
        return new StickerActivity(context);
    }

    public StickerActivity(MenuActivity context) {
        _context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = View.inflate(_context, R.layout.stickers_activity, container);
        SwipeListView swipelistview = (SwipeListView)v.findViewById(R.id.lvStickers);
        Singleton.getInstance()._sj = new StickersJSON(1);
        if (Singleton.getInstance()._listStickers == null)
            refreshListStickers();
        swipelistview.setAdapter(new StickerAdapter(_context, Singleton.getInstance()._listStickers, swipelistview));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void refreshListStickers()
    {
        Singleton.getInstance()._listStickers = new ArrayList<Sticker>();
        _pd = _pd.show(_context, "Please wait", "Loading list of stickers...");
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                Singleton.getInstance()._listStickers = Singleton.getInstance()._sj.readAllStickers();
                _pd.dismiss();
            }
        });
        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
