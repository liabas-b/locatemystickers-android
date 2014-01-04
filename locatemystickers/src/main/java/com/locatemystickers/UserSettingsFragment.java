package com.locatemystickers;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.locatemystickers.menu.MenuActivity;

public class UserSettingsFragment extends Fragment implements View.OnClickListener{

    private MenuActivity _context;

    static UserSettingsFragment newInstance(MenuActivity context) {
        return new UserSettingsFragment(context);
    }

    public UserSettingsFragment(MenuActivity context) {
        _context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_settings_fragment, container, false);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        onDestroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
    }

}
