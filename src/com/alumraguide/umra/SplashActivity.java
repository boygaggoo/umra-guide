package com.alumraguide.umra;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;

public class SplashActivity extends SherlockActivity {

	private static final long DELAY = 3000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		//getSupportActionBar().hide();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				finish();
				Intent intent = new Intent(SplashActivity.this, TOCActivity.class);
				startActivity(intent);
			}
			
		}, DELAY);
	}
	
}
