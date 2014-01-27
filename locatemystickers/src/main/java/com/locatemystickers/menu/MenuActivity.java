package com.locatemystickers.menu;

import android.app.Activity;
;
import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.locatemystickers.MapActivity;
import com.locatemystickers.R;
import com.locatemystickers.StickerActivity;
import com.locatemystickers.UserActivity;

public class MenuActivity extends FragmentActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.Fragment frag = null;
        switch (position+1) {
            case 1://Map
                frag = MapActivity.newInstance(this);
                break;
            case 2://Stickers
                frag = StickerActivity.newInstance(this);
                break;
            case 3://User
                frag = UserActivity.newInstance(this);
                break;
            case 4://Friends
                break;
            case 5://Search
                break;
        }
        if (frag != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, frag)
                    .commit();
        }
    }

    public void onSectionAttached(int number) {
        mTitle = NavigationDrawerFragment.listItem.get(number-1).get_txtMenu();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
    }

    private Drawable resizeImage(int resId, int w, int h)
    {
        // load the origial Bitmap
        Bitmap BitmapOrg = BitmapFactory.decodeResource(getResources(), resId);
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width, height, null, true);
        return new BitmapDrawable(resizedBitmap);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.menu, menu);
            menu.add(Menu.NONE, Menu.NONE, 1, "Settings")
                    .setIcon(resizeImage(R.drawable.settings, 16, 16))
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            menu.add(Menu.NONE, Menu.NONE, 2, "MAP")
                    .setIcon(resizeImage(R.drawable.map, 16, 16))
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            menu.add(Menu.NONE, Menu.NONE, 999, "Avatar")
                    .setIcon(resizeImage(R.drawable.avatar, 16, 16))
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
