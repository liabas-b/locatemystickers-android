package com.locatemystickers;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.locatemystickers.menu.MenuActivity;

public class SettingsFragment extends Fragment implements View.OnClickListener{

    private MenuActivity _context;

    static SettingsFragment newInstance(MenuActivity context) {
        return new SettingsFragment(context);
    }

    public SettingsFragment(MenuActivity context) {
        _context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.settings_fragment, container, false);
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
