package com.locatemystickers;

import org.json.JSONException;

import com.chute.sdk.v2.api.Chute;
import com.chute.sdk.v2.api.authentication.AuthConstants;
import com.chute.sdk.v2.api.authentication.AuthenticationFactory;
import com.chute.sdk.v2.model.enums.AccountType;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

public class UserAccountInfoFragment extends Fragment {
	private ProgressDialog _pd;
    private Context _context;
    private User _user = null;

    static UserAccountInfoFragment newInstance(Context context) {
        return new UserAccountInfoFragment(context);
    }

    public UserAccountInfoFragment(Context context) {
        _context = context;
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        /*Thread th = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    _user = Singleton.getInstance()._uj.readUser(4);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        View v = inflater.inflate(R.layout.user_accountinfo_fragment, container, false);
        EditText name = (EditText) v.findViewById(R.id.account_name);
        EditText email = (EditText) v.findViewById(R.id.account_mail);
        if (_user != null)
        {
            name.setText(_user.get_name().toString());
            email.setText(_user.get_email().toString());
        }
        ImageView image = (ImageView)v.findViewById(R.id.account_img);
        return v;
	}
	
	@Override
	public void onStop() {
		EditText name = (EditText) getView().findViewById(R.id.account_name);
		Log.d("Test", name.getText().toString());
		EditText email = (EditText) getView().findViewById(R.id.account_mail);
		Log.d("Test", email.getText().toString());
		super.onStop();
	}
}
