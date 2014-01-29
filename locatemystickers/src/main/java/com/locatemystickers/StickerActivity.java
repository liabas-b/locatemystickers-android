package com.locatemystickers;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    private SwipeListView _swipelistview;
    public static Fragment newInstance(MenuActivity context) {
        return new StickerActivity(context);
    }

    public StickerActivity(MenuActivity context) {
        _context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stickers_activity, container, false);
        _swipelistview = (SwipeListView)view.findViewById(R.id.lvStickers);
        Singleton.getInstance()._sj = new StickersJSON(1);
        new StickerSyn().execute();
        return view;
    }

    public class StickerSyn extends AsyncTask<Void, Void, Void> {
        public StickerSyn() {
            super();
        }

        @Override
        protected Void doInBackground(Void... params) {
            Singleton.getInstance()._listStickers = new ArrayList<Sticker>();
            Singleton.getInstance()._listStickers = Singleton.getInstance()._sj.readAllStickers();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            _swipelistview.setAdapter(new StickerAdapter(_context, Singleton.getInstance()._listStickers, _swipelistview));
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}
