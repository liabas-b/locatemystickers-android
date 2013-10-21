package com.locatemystickers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.locatemystickers.utils.Utils;

public class MainActivity extends Activity {
	private Context context = this;
    private Boolean conn = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Button btn_auth = (Button)findViewById(R.id.btn_auth);

		btn_auth.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!(conn = checkConn()))
					Toast.makeText(context, getString(R.string.s_Msg01), Toast.LENGTH_LONG).show();
				else
				{
					Intent i = new Intent(MainActivity.this, ScreenView.class);
					startActivity(i);
					finish();
				}
			}
		});
	}

    private boolean checkConn()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                conn = Utils.isOnline("http://web-service.locatemystickers.com/", 10800);
            }
        });
        thread.start();
        try{
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return conn;
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
