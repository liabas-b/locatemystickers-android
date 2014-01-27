package com.locatemystickers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.locatemystickers.R;
import com.locatemystickers.menu.MenuActivity;
import com.locatemystickers.qrcode.GenerateQRCode;
import com.locatemystickers.utils.Utils;

public class UserActivity extends Fragment {
    private int _nb = 0;
    private ImageView _you_sticker;
    private TextView _topBarTextView;
    private TextView _topBarBackTextView;
    private ImageButton _topBarBackImageButton;
    private ProgressDialog _pd;
    private MenuActivity _context;
    
    public static UserActivity newInstance(MenuActivity context) {
        return new UserActivity(context);
    }

    public UserActivity(MenuActivity context) {
        _context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = View.inflate(_context, R.layout.user_fragment, container);
        _you_sticker = (ImageView)v.findViewById(R.id.user_qrcode);
        _pd = _pd.show(_context, "Please wait", "Loading...");
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                Singleton.getInstance()._user = Singleton.getInstance()._uj.readUser(1);
                _pd.dismiss();
            }
        });
        new GenerateQRCode().execute(Utils.md5("toto"));

        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TextView name = (TextView) v.findViewById(R.id.user_name);
        TextView city = (TextView) v.findViewById(R.id.user_location);
        if (Singleton.getInstance()._user != null)
        {
            name.setText(Singleton.getInstance()._user.get_name().toString());
            city.setText('@' + Singleton.getInstance()._user.get_city().toString());
        }
    return super.onCreateView(inflater, container, savedInstanceState);
    }
}
