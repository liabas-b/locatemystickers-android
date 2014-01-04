package com.locatemystickers;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.locatemystickers.menu.MenuActivity;

public class UserAccountInfoFragment extends Fragment implements View.OnClickListener {
    private ProgressDialog _pd;
    private MenuActivity _context;

    static UserAccountInfoFragment newInstance(MenuActivity context) {
        return new UserAccountInfoFragment(context);
    }

    public UserAccountInfoFragment(MenuActivity context) {
        _context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_accountinfo_fragment, container, false);
        EditText name = (EditText) v.findViewById(R.id.account_name);
        EditText email = (EditText) v.findViewById(R.id.account_mail);
        if (Singleton.getInstance()._user != null)
        {
            name.setText(Singleton.getInstance()._user.get_name().toString());
            email.setText(Singleton.getInstance()._user.get_email().toString());
        }
        ImageView image = (ImageView)v.findViewById(R.id.account_img);
//        ImageButton imgbutton = (ImageButton)_context.findViewById(R.id.topbar_back_imagebutton);
//        imgbutton.setOnClickListener(this);
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
/*        switch (v.getId())
        {
            case R.id.topbar_back_imagebutton:
                UserActivity.goToBackActivity(_context);
                break;
        }*/
    }
}
