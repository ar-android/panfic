package com.ocit;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {

	private static int SPLASH_TIME_OUT = 3000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		/* handler untuk menjalankan splashscreen selama 5 detik lalu 
		 * membuat HomeActivity 
		 */
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					Intent mainIntent = null;

					mainIntent = new Intent(Splash.this,
						MainActivity.class);

					Splash.this.startActivity(mainIntent);
					Splash.this.finish();
				}
			}, SPLASH_TIME_OUT);
    
    }   
}
