package com.umaticapital.umatidemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView mCollection_info;
	TextView mQuality_standard;
	TextView mWeight;
	
	ListView mlv_Collection_info;
	ListView mlv_Quality_standard;
	ListView mlv_Weight;
	
	Button mSubmit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//initializing the TextViews
		mCollection_info = (TextView) findViewById(R.id.tv_one);
		mQuality_standard = (TextView) findViewById(R.id.tv_two);
		mWeight = (TextView) findViewById(R.id.tv_three);
		
		//initializing the ListViews
		mlv_Collection_info = (ListView) findViewById(R.id.lv_one);
		mlv_Quality_standard = (ListView) findViewById(R.id.lv_two);
		mlv_Weight = (ListView) findViewById(R.id.lv_three);
		
		//initialize the Button
		mSubmit = (Button) findViewById(R.id.button_print_receipt);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
