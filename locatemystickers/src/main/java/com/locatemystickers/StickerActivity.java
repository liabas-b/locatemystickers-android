package com.locatemystickers;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ged74.swipelistview.SwipeListView;
import com.locatemystickers.adapter.StickerAdapter;
import com.locatemystickers.json.StickersJSON;
import com.locatemystickers.menu.MenuActivity;
import com.locatemystickers.type.Sticker;

import java.util.ArrayList;
import java.util.List;

public class StickerActivity extends Fragment {
    private MenuActivity _context;
    private ProgressDialog _pd;
    private SwipeListView _swipelistview;
    private ProgressBar _pb;
    private List<Sticker> _lstickers;

    public static Fragment newInstance(MenuActivity context) {
        return new StickerActivity(context);
    }

    public StickerActivity(MenuActivity context) {
        _context = context;
        _lstickers = new ArrayList<Sticker>();
        Singleton.getInstance()._sj = new StickersJSON(Singleton.getInstance()._id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stickers_activity, container, false);
        _pb = (ProgressBar)view.findViewById(R.id.progressBar);
        _swipelistview = (SwipeListView)view.findViewById(R.id.lvStickers);
        new StickerSyn().execute();
        return view;
    }

    public class StickerSyn extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            _lstickers = Singleton.getInstance()._sj.readAllStickers();
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
            if (_lstickers != null)
                _swipelistview.setAdapter(new StickerAdapter(_context, _lstickers, _swipelistview));
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
