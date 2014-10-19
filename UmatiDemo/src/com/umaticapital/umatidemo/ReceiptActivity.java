package com.umaticapital.umatidemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by antonykaguara on 10/18/14.
 */
public class ReceiptActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        Intent i = getIntent();
        String farmer = i.getStringExtra("Farmer");
        ((TextView) findViewById(R.id.farmer_value)).setText(farmer);
        String date = i.getStringExtra("Date");
        ((TextView) findViewById(R.id.date_value)).setText(date);
        String moisture_level = i.getStringExtra("Moisture_Level");
        ((TextView) findViewById(R.id.moisture_value)).setText(moisture_level);
        String foreign_matter = i.getStringExtra("Foreign_Matter");
        ((TextView) findViewById(R.id.foreign_matter_value)).setText(foreign_matter);
        String damaged_grain = i.getStringExtra("Damaged_Grain");
        ((TextView) findViewById(R.id.damaged_grain_value)).setText(damaged_grain);
        String insects = i.getStringExtra("Insects");
        ((TextView) findViewById(R.id.insects_value)).setText(insects);
        String odour_taste = i.getStringExtra("Odour_Taste");
        ((TextView) findViewById(R.id.odour_value)).setText(odour_taste);
        String soiled_grain = i.getStringExtra("Soiled_Grain");
        ((TextView) findViewById(R.id.soiled_value)).setText(soiled_grain);
        String variety_mix = i.getStringExtra("Variety_Mix");
        ((TextView) findViewById(R.id.variety_value)).setText(variety_mix);
        String weight = i.getStringExtra("Weight");
        ((TextView) findViewById(R.id.weight_value)).setText(weight);
    }
}
