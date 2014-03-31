package com.sijstermans.easytabata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TableRow;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class MainActivity extends ActionBarActivity {

	private AdView adView;
	private Spinner exercises;
	private Spinner duration;
	private Spinner rest;
	private Spinner rounds;
	
	
	private static final String AD_UNIT_ID = "ca-app-pub-1863889756478321/8692970496";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	@Override
	protected void onStart(){
		super.onStart();
		
		exercises = (Spinner) findViewById(R.id.spinner1);
		duration = (Spinner) findViewById(R.id.spinner2);
		rest = (Spinner) findViewById(R.id.spinner3);
		rounds = (Spinner) findViewById(R.id.spinner4);

		// Create an ad.
	    adView = new AdView(this);
	    adView.setAdSize(AdSize.BANNER);
	    adView.setAdUnitId(AD_UNIT_ID);

	    // Add the AdView to the view hierarchy. The view will have no size
	    // until the ad is loaded.
	    TableRow row = (TableRow) findViewById(R.id.adcontainer);
	    TableRow.LayoutParams params = new TableRow.LayoutParams();
	    params.span = 2;
	    row.addView(adView,0,params);
	    
	    // Create an ad request. Check logcat output for the hashed device ID to
	    // get test ads on a physical device.
	    AdRequest adRequest = new AdRequest.Builder()
	        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
	        .addTestDevice("4df167ff01c79f97")
	        .build();

	    // Start loading the ad in the background.
	    adView.loadAd(adRequest);
	
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	public void nextScreen(View v){
		Intent i = new Intent(this, SecondScreen.class);
		i.putExtra("exercises", exercises.getSelectedItemId());
		i.putExtra("duration", duration.getSelectedItemId());
		i.putExtra("rest", rest.getSelectedItemId());
		i.putExtra("rounds", rounds.getSelectedItemId());
		startActivity(i);
	}
}
