package com.ocit;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class Asuransi extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_asuransi);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

	}
}
