package com.locatemystickers;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;


@SuppressWarnings("deprecation")
public class MenuTab extends TabActivity {
	TabHost tabHost;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tabHost = getTabHost();
		setTabs();
	}
	
	private void setTabs()
	{
		addTab("Home", R.drawable.tab_home, StickerActivity.class);
		addTab("Map", R.drawable.tab_world, MapActivity.class);
		addTab("Map", R.drawable.tab_world, MapActivity.class); // fake
		addTab("User", R.drawable.tab_account, UserActivity.class);
		addTab("Search", R.drawable.tab_search, SearchActivity.class);
	}
	
	private void addTab(String labelId, int drawableId, Class<?> c)
	{
		Intent intent = new Intent(this, c);
		TabHost.TabSpec spec = tabHost.newTabSpec("tab" + labelId);	

		View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);
		TextView title = (TextView) tabIndicator.findViewById(R.id.title);
		title.setText(labelId);
		ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
		icon.setImageResource(drawableId);		
		spec.setIndicator(tabIndicator);
		spec.setContent(intent);
		tabHost.addTab(spec);
		
	}

}