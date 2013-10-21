package com.locatemystickers;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UserSettingsFragment extends Fragment {

    private Context _context;

    static UserSettingsFragment newInstance(Context context) {
        return new UserSettingsFragment(context);
    }

    public UserSettingsFragment(Context context) {
        _context = context;
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.user_settings_fragment, container, false);
		return v;
	}

}
