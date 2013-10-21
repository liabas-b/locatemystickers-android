package com.locatemystickers;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class StickerDetailActivity extends Fragment {
    private Context _context;
	private int _id_sticker;
	private Sticker _sticker;
    private ProgressDialog _pd;
    private TextView _stickerName;

    public static StickerDetailActivity newInstance(Context context, int id_sticker) {
        return new StickerDetailActivity(context, id_sticker);
    }

    public StickerDetailActivity(Context context, int id_sticker) {
        _context = context;
        _id_sticker = id_sticker;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = View.inflate(_context, R.layout.sticker_details, container);
        _stickerName = (TextView)v.findViewById(R.id.tv_stickername);
        // find something for check if id_sticker is correct else the next can be unlikely
        _pd = _pd.show(_context, "Please wait", "Loading sticker...");
        Thread th = new Thread(new Runnable() {

            @Override
            public void run() {
                _sticker = Singleton.getInstance()._sj.readSticker(_id_sticker);
                if (_sticker != null)
                {
                    _stickerName.setText(_sticker.get_name());
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
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
