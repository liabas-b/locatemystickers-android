package com.locatemystickers;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

public class UserActivity extends Fragment implements View.OnClickListener {

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
       // _topBarTextView = (TextView)_context.findViewById(R.id.topbar_textview);
       // _topBarBackTextView = (TextView)_context.findViewById(R.id.topbar_back_textview);
       // _topBarBackImageButton = (ImageButton)_context.findViewById(R.id.topbar_back_imagebutton);

//        TableRow tr_your_stickers = (TableRow)v.findViewById(R.id.your_stickers_row);
//        TableRow tr_sharring_stickers = (TableRow)v.findViewById(R.id.sharring_stickers_row);
//        TableRow tr_account_info = (TableRow)v.findViewById(R.id.account_info_row);
//        TextView your_stickers = (TextView)v.findViewById(R.id.your_stickers_textview);
        _you_sticker = (ImageView)v.findViewById(R.id.user_qrcode);
        _pd = _pd.show(_context, "Please wait", "Loading...");
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                Singleton.getInstance()._user = Singleton.getInstance()._uj.readUser(1);
                _pd.dismiss();
            }
        });
        Thread ts = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    _you_sticker.setImageBitmap(new GenerateQRCode().createQRImage(Utils.md5("toto"), 128));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        th.start();
        ts.start();
        try {
            ts.join();
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

//        your_stickers.setText(getString(R.string.your_stickers) + " (" + Singleton.getInstance()._listStickers.size() + ")");
//        tr_your_stickers.setOnClickListener(this);
//        tr_sharring_stickers.setOnClickListener(this);
//        tr_account_info.setOnClickListener(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
/*        switch(v.getId()) {
            case R.id.your_stickers_row:
                goToYourStickersActivity(v);
                break;
            case R.id.sharring_stickers_row:
                goToSharringStickersActivity(v);
                break;
            case R.id.account_info_row:
                goToUserAccountInfoActivity(v);
                break;
        }
*/    }
/*
    public static  void goToBackActivity(MenuActivity context) {
        context.fragIntent(UserActivity.newInstance(context));
        final TextView topBarTextView = (TextView)context.findViewById(R.id.topbar_textview);
        topBarTextView.setText("User");
        final TextView topBarBackTextView = (TextView)context.findViewById(R.id.topbar_back_textview);
        topBarBackTextView.setVisibility(View.INVISIBLE);
        final ImageButton topBarBackImageButton = (ImageButton)context.findViewById(R.id.topbar_back_imagebutton);
        topBarBackImageButton.setVisibility(View.INVISIBLE);
    }
*/
    public void goToYourStickersActivity(final View v) {
/*        final TextView topBarTextView = (TextView)_context.findViewById(R.id.topbar_textview);
        topBarTextView.setText("Stickers");*/
        _context.fragIntent(StickerActivity.newInstance(_context));

    }

    public void goToSharringStickersActivity(final View v) {

    }

    public void onUserSettingsLocateToggleButtonClicked(final View v) {

    }

    public void onUserSettingsDisplayToggleButtonClicked(final View v) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onDestroy() {
        super.onDestroy();
    }

}
