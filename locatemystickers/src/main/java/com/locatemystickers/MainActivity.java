package com.locatemystickers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.locatemystickers.Menu.MenuActivity;

public class MainActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btn_auth = (Button)findViewById(R.id.btn_auth);
        btn_auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO check connection before
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
