package com.simplytadka;

import java.util.HashMap;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends SherlockActivity
{

	WebView mainWebView;
	private DrawerLayout drawerLayout;
	private ListView drawerList;
	ActionBarDrawerToggle drawerToggle;
	HashMap<String, String> items;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_PROGRESS);

		setContentView(R.layout.activity_main);

		ActionBar actionBar = getSupportActionBar();
		// actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);

		mainWebView = (WebView) findViewById(R.id.mainWebView);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerList = (ListView) findViewById(R.id.left_drawer);

		mainWebView.getSettings().setJavaScriptEnabled(true);
		mainWebView.setScrollBarStyle(WebView.SCROLLBAR_POSITION_RIGHT);

		final Activity activity = this;
		mainWebView.setWebChromeClient(new WebChromeClient()
		{
			public void onProgressChanged(WebView view, int progress)
			{
				// Activities and WebViews measure progress with different
				// scales.
				// The progress meter will automatically disappear when we reach
				// 100%
				activity.setProgress(progress * 1000);
			}
		});
		
		

		mainWebView.setWebViewClient(new WebViewClient()
		{
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
			{
				Toast.makeText(activity, "Oh no! " + description, Toast.LENGTH_SHORT).show();
			}
		});

		mainWebView.loadUrl(Consts.SIMPLY_TADKA_URL);

		drawerList.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub

			}
		});

		drawerToggle = new MyActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_closed);
		drawerLayout.setDrawerListener(drawerToggle);

		items = new HashMap<String, String>();
		items.put("http://wwww.facebook.com", "Facebook");
		items.put("http://wwww.twitter.com", "Twitter");
		items.put("http://wwww.simplytadka.com", "Simply Tadka");

		HashMapAdapter adapter = new HashMapAdapter(this, items);
		drawerList.setAdapter(adapter);

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState)
	{
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}
	
	@Override
	public boolean onPrepareOptionsMenu(com.actionbarsherlock.view.Menu menu)
	{
		 boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
	        
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public void setTitle(CharSequence title)
	{
		getSupportActionBar().setTitle(title);
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu)
	// {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }
	
	private class MyActionBarDrawerToggle extends ActionBarDrawerToggle
	{
		MainActivity mainActivity;
		

		public MyActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, int drawerImageRes, int openDrawerContentDescRes, int closeDrawerContentDescRes)
		{
			super(activity, drawerLayout, drawerImageRes, openDrawerContentDescRes, closeDrawerContentDescRes);
			this.mainActivity=(MainActivity) activity;
		}
		
		@Override
		public boolean isDrawerIndicatorEnabled()
		{
			return true;
		}
		
		
		
		@Override
		public void onDrawerClosed(View drawerView)
		{
			// TODO Auto-generated method stub
			super.onDrawerClosed(drawerView);
			
			supportInvalidateOptionsMenu();
		}
		
		@Override
		public void onDrawerOpened(View drawerView)
		{
			// TODO Auto-generated method stub
			super.onDrawerOpened(drawerView);
			
			supportInvalidateOptionsMenu();
			
		}
		
		@Override
		public boolean onOptionsItemSelected(MenuItem item)
		{
			// TODO Auto-generated method stub
			return super.onOptionsItemSelected(item);
		}

	}


}
