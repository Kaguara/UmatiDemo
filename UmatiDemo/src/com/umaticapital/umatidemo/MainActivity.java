package com.umaticapital.umatidemo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends Activity {
    Spinner mSelect_farmer;
    ArrayAdapter<String> mFarmer_names;
	Button mSubmit;
    EditText mSet_date;
    DialogFragment mDate_fragment;

    String mFarmer;
    String mDate;
    String mMoisture_level;
    String mForeign_matter;
    String mDamaged_grain;
    String mInsects;
    String mOdour_taste;
    String mSoiled_grain;
    String mVariety_mix;
    String mWeight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        //set spinner data
        //initialize spinner adapter data
        final String[] data = getResources().getStringArray(R.array.farmer_names);
        mFarmer_names = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item,data);

        mSelect_farmer = ((Spinner) findViewById(R.id.spinner_farmer));
        mSelect_farmer.setAdapter(mFarmer_names);

        //set listener to launch the date dialog box
        mSet_date = (EditText) findViewById(R.id.et_set_date);
        mDate_fragment = new SelectDateFragment();
        mSet_date.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mDate_fragment.show(getFragmentManager(), "Select Today's Date");
            }
        });
		//initialize the Button
		mSubmit = (Button) findViewById(R.id.button_print_receipt);
		//When the submit button is hit, it sends the collected data
		mSubmit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//get the values of the selected items
				mFarmer = ((Spinner) findViewById(R.id.spinner_farmer)).getSelectedItem().toString();

			    //get data from date EditText
                mDate = mSet_date.getText().toString();
                //get Values from the Switch views
                if( ((Switch) findViewById(R.id.switch_moisture_level)).isChecked()) {
                     mMoisture_level = ((Switch) findViewById(R.id.switch_moisture_level)).getTextOn().toString();
                }else{
                     mMoisture_level = ((Switch) findViewById(R.id.switch_moisture_level)).getTextOff().toString();
                }
			    if( ((Switch) findViewById(R.id.switch_foreign_matters)).isChecked() ){
                    mForeign_matter = ((Switch) findViewById(R.id.switch_foreign_matters)).getTextOn().toString();
                }else{
                    mForeign_matter = ((Switch) findViewById(R.id.switch_foreign_matters)).getTextOff().toString();
                }
			    if( ((Switch) findViewById(R.id.switch_damaged_grain)).isChecked()){
                    mDamaged_grain = ((Switch) findViewById(R.id.switch_damaged_grain)).getTextOn().toString();
                }else{
                    mDamaged_grain = ((Switch) findViewById(R.id.switch_damaged_grain)).getTextOff().toString();
                }
			    if (((Switch) findViewById(R.id.switch_insects)).isChecked()){
                    mInsects = ((Switch) findViewById(R.id.switch_insects)).getTextOn().toString();
                }else{
                    mInsects = ((Switch) findViewById(R.id.switch_insects)).getTextOff().toString();
                }
			    if( ((Switch) findViewById(R.id.switch_odour_taste)).isChecked() ){
                   mOdour_taste = ((Switch) findViewById(R.id.switch_odour_taste)).getTextOn().toString();
                }else{
                    mOdour_taste = ((Switch) findViewById(R.id.switch_odour_taste)).getTextOff().toString();
                }
			    if( ((Switch) findViewById(R.id.switch_soiled_grain)).isChecked() ){
                    mSoiled_grain = ((Switch) findViewById(R.id.switch_soiled_grain)).getTextOn().toString();
                }else{
                    mSoiled_grain = ((Switch) findViewById(R.id.switch_soiled_grain)).getTextOff().toString();
                }
			    if( ((Switch) findViewById(R.id.switch_variety_mix)).isChecked() ){
                    mVariety_mix = ((Switch) findViewById(R.id.switch_variety_mix)).getTextOn().toString();
                }else{
                    mVariety_mix = ((Switch) findViewById(R.id.switch_variety_mix)).getTextOff().toString();
                }
                mWeight = ((EditText) findViewById(R.id.et_weight)).getText().toString();

                //Launch a new intent
                Intent i  = new Intent(MainActivity.this, ReceiptActivity.class);
                i.putExtra("Farmer", mFarmer);
                i.putExtra("Date", mDate);
                i.putExtra("Moisture_Level", mMoisture_level);
                i.putExtra("Foreign_Matter", mForeign_matter);
                i.putExtra("Damaged_Grain", mDamaged_grain);
                i.putExtra("Insects", mInsects);
                i.putExtra("Odour_Taste", mOdour_taste);
                i.putExtra("Soiled_Grain", mSoiled_grain);
                i.putExtra("Variety_Mix", mVariety_mix);
                i.putExtra("Weight", mWeight);
                startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    /* populateSetDate set's the format of the date entry
     * @year           integer value for the selected year
     * @month          integer value for the selected month
     * @day            integer value for the selected day
     *
     * @return        void
     */
    public void populateSetDate(int year, int month, int day) {
        mSet_date.setText(day+"/"+month+"/"+year);
    }

    /* SelectDateFragment Class that launches the date dialogue box
     *                    and allows user to select the date
     *
     */
    @SuppressLint("ValidFragment")
    public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, dd, mm+1);
        }
    }

}
