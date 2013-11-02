package com.locatemystickers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.internal.ca;
import com.locatemystickers.qrcode.GenerateQRCode;
import com.locatemystickers.utils.Utils;

import org.json.JSONException;

public class UserActivity extends Fragment implements View.OnClickListener{
/*	private TopBarFragment topBarFragment;
	private MenuFragment menuFragment;
	private UserFragment userFragment;
	private UserAccountInfoFragment userAccountInfoFragment;
	private UserSettingsFragment userSettingsFragment;*/
    private ScreenView _sv;
    private int _nb = 0;
    private ImageView _you_sticker;

    static UserActivity newInstance(ScreenView sv) {
        return new UserActivity(sv);
    }

    public UserActivity(ScreenView sv) {
        _sv = sv;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = View.inflate(_sv, R.layout.user_fragment, container);
        TableRow tr_your_stickers = (TableRow)v.findViewById(R.id.your_stickers_row);
        TableRow tr_sharring_stickers = (TableRow)v.findViewById(R.id.sharring_stickers_row);
        TableRow tr_account_info = (TableRow)v.findViewById(R.id.account_info_row);
        TableRow tr_log_out = (TableRow)v.findViewById(R.id.log_out_row);
        TableRow tr_settings = (TableRow)v.findViewById(R.id.settings_row);
        TextView your_stickers = (TextView)v.findViewById(R.id.your_stickers_textview);
        _you_sticker = (ImageView)v.findViewById(R.id.you_sticker);
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
        ts.start();
        try {
            ts.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        your_stickers.setText(getString(R.string.your_stickers) + " (" + Singleton.getInstance()._nbr_stickers + ")");
        tr_your_stickers.setOnClickListener(this);
        tr_sharring_stickers.setOnClickListener(this);
        tr_account_info.setOnClickListener(this);
        tr_log_out.setOnClickListener(this);
        tr_settings.setOnClickListener(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.topbar_back_imagebutton:
                goToBackActivity(v);
                break;
            case R.id.your_stickers_row:
                goToYourStickersActivity(v);
                break;
            case R.id.sharring_stickers_row:
                goToSharringStickersActivity(v);
                break;
            case R.id.account_info_row:
                goToUserAccountInfoActivity(v);
                break;
            case R.id.log_out_row:
                goToLogOutActivity(v);
                break;
            case R.id.settings_row:
                goToUserSettingsActivity(v);
                break;

        }
    }

    public void goToBackActivity(final View v) {
        _sv.fragIntent(this);
		final TextView topBarTextView = (TextView)_sv.findViewById(R.id.topbar_textview);
		topBarTextView.setText("User");
/*		final TextView topBarBackTextView = (TextView)_sv.findViewById(R.id.topbar_back_textview);
		topBarBackTextView.setVisibility(8);
		final ImageButton topBarBackImageButton = (ImageButton)_sv.findViewById(R.id.topbar_back_imagebutton);
		topBarBackImageButton.setVisibility(8);*/
	}

    public void goToYourStickersActivity(final View v) {
        _sv.fragIntent(StickerActivity.newInstance(_sv));
        final TextView topBarTextView = (TextView)_sv.findViewById(R.id.topbar_textview);
        topBarTextView.setText("Stickers");
	}
	
	public void goToSharringStickersActivity(final View v) {

	}
	
	public void goToUserAccountInfoActivity(final View v) {
        _sv.fragIntent(UserAccountInfoFragment.newInstance(_sv));
		final TextView topBarTextView = (TextView)_sv.findViewById(R.id.topbar_textview);
		topBarTextView.setText("Account Info");
		//final TextView topBarBackTextView = (TextView)_sv.findViewById(R.id.topbar_back_textview);
		//topBarBackTextView.setVisibility(0);
		//topBarBackTextView.setText("User");
		//final ImageButton topBarBackImageButton = (ImageButton)_sv.findViewById(R.id.topbar_back_imagebutton);
		//topBarBackImageButton.setVisibility(0);
	}

	public void goToLogOutActivity(final View v) {
        Intent i = new Intent(_sv, MainActivity.class);
        _sv.startActivity(i);
        _sv.finish();
	}	
	
	public void goToUserSettingsActivity(final View v) {
        _sv.fragIntent(UserSettingsFragment.newInstance(_sv));
		final TextView topBarTextView = (TextView)_sv.findViewById(R.id.topbar_textview);
		topBarTextView.setText("Settings");
/*		final TextView topBarBackTextView = (TextView)_sv.findViewById(R.id.topbar_back_textview);
		topBarBackTextView.setVisibility(0);
		topBarBackTextView.setText("User");
		final ImageButton topBarBackImageButton = (ImageButton)_sv.findViewById(R.id.topbar_back_imagebutton);
		topBarBackImageButton.setVisibility(0);*/
	}
	
	public void onUserSettingsLocateToggleButtonClicked(final View v) {
		
	}
	
	public void onUserSettingsDisplayToggleButtonClicked(final View v) {
		
	}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //android.support.v4.app.FragmentTransaction _fragTrans = _sv.getSupportFragmentManager().beginTransaction();
        //_fragTrans.remove();
        //_fragTrans.commit();
    }
}
