package com.locatemystickers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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
    private MenuActivity _context;
    private TextView _name;
    private TextView _city;

    public static UserActivity newInstance(MenuActivity context) {
        return new UserActivity(context);
    }

    public UserActivity(MenuActivity context) {
        _context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment, container, false);
        _you_sticker = (ImageView)view.findViewById(R.id.user_qrcode);
        new GenerateQRCode().execute(Utils.md5("toto"));
        _name = (TextView) view.findViewById(R.id.user_name);
        _city = (TextView) view.findViewById(R.id.user_location);
        return view;
    }

    public class UserSync extends AsyncTask<Void, Void, Void> {
        public UserSync() {
            super();
        }

        @Override
        protected Void doInBackground(Void... params) {
            Singleton.getInstance()._user = Singleton.getInstance()._uj.readUser(1);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (Singleton.getInstance()._user != null)
            {
                _name.setText(Singleton.getInstance()._user.get_name().toString());
                _city.setText('@' + Singleton.getInstance()._user.get_city().toString());
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}
