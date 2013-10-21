package com.locatemystickers;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import com.google.android.gms.maps.SupportMapFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class ScreenView extends FragmentActivity implements View.OnClickListener
{
    private FrameLayout _frame;
    private Fragment _frag = null;
    private FragmentTransaction _fragTrans;
    private TextView _topBarTextView;
    private Button _menu_stickers;
    private Button _menu_map;
    private ImageButton _menu_scan;
    private Button _menu_user;
    private Button _menu_search;

    public final void fragIntent(Fragment frag) {
        if (_frag != frag)
        {
            if (_frag != null)
            {
                _fragTrans.remove(_frag);
            }
            _frag = frag;
            _frame.removeAllViews();
            _fragTrans = getFragmentManager().beginTransaction();
            _fragTrans.replace(R.id.center_fragmentlayout, frag);
            _fragTrans.commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);
        _frame = (FrameLayout)findViewById(R.id.center_fragmentlayout);
        _topBarTextView = (TextView)findViewById(R.id.topbar_textview);
        _topBarTextView.setText("Stickers");
        fragIntent(StickerActivity.newInstance(this));
        _menu_stickers = (Button)findViewById(R.id.button_menu_stickers);
        _menu_stickers.setOnClickListener(this);
        _menu_map = (Button)findViewById(R.id.button_menu_map);
        _menu_map.setOnClickListener(this);
        _menu_scan = (ImageButton)findViewById(R.id.button_menu_scan);
        _menu_scan.setOnClickListener(this);
        _menu_user = (Button)findViewById(R.id.button_menu_user);
        _menu_user.setOnClickListener(this);
        _menu_search = (Button)findViewById(R.id.button_menu_search);
        _menu_search.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_menu_stickers:
                _topBarTextView.setText("Stickers");
                fragIntent(StickerActivity.newInstance(this));
                break;
            case R.id.button_menu_map:
                _topBarTextView.setText("Map");
                fragIntent(MapActivity.newInstance(this));
                break;
            case R.id.button_menu_scan:
                break;
            case R.id.button_menu_user:
                _topBarTextView.setText("User");
                fragIntent(UserActivity.newInstance(this));
                break;
            case R.id.button_menu_search:
                _topBarTextView.setText("Search");
                fragIntent(SearchActivity.newInstance(this));
                break;
        }
    }
}
