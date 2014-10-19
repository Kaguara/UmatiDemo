package com.umaticapital.umatidemo.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.umaticapital.umatidemo.R;

/**
 * Created by antonykaguara on 10/19/14.
 */
public class CustomizedSpinnerAdapter extends ArrayAdapter<String> {

    private Activity context;
    String[] data = null;

    public CustomizedSpinnerAdapter(Activity context, int resource, String[] data2) {
        super(context, resource, data2);
        this.context = context;
        this.data = data2;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            //inflate your customlayout for the textview
            LayoutInflater inflater = context.getLayoutInflater();
            //row = inflater.inflate(R.layout.simple_spinner_item, parent, false);
        }
        //put the data in it
        String item = data[position];
        if (item != null) {
            //TextView text1 = (TextView) row.findViewById(R.id.rowText);
            //text1.setTextColor(Color.WHITE);
            //text1.setText(item);
        }
        return row;
    }
}
