package com.umaticapital.umatidemo;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.umaticapital.umatidemo.helperClasses.LoginDBHelper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity{
	
	Button mLogin;
	Button mSignUp;
	
	String mUsername;
	String mPassword;
	String LOG = "Login Activity";
	Integer sync_farmer;
	Integer sync_suppliers_grader;
	
	TextView mError_text;
	List<NameValuePair> mParams;
	
	LoginDBHelper login_helper;
	
	String login_url = "http://staging.umaticapital.com/api/v1/authenticate?sync_farmers=0&sync_suppliers_grader=0";

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		mSignUp = (Button) findViewById(R.id.button_login_signup); 
		mError_text = (TextView) findViewById(R.id.tv_login_errormsg);
		
		login_helper = new LoginDBHelper(this);
		
		mLogin  = (Button) findViewById(R.id.button_login_submit);
		mLogin.setOnClickListener(new View.OnClickListener() {
			
			@SuppressLint("ShowToast")
			@Override
			public void onClick(View v) {
				//get the username and password from the edit text views
				mUsername = (((EditText) findViewById(R.id.et_login_username)).getText()).toString();
				mPassword = (((EditText) findViewById(R.id.et_login_password)).getText()).toString();
				//set the sync_farmer values
				sync_farmer = 0;
				sync_suppliers_grader = 0;
				//wrap the username and password as name value pairs
				//NameValuePair user_nvp = new BasicNameValuePair("username", mUsername);
				//NameValuePair password_nvp = new BasicNameValuePair("password", mPassword);
				String base64EncodedCredentials = "Basic " + Base64.encodeToString( 
	                    (mUsername + ":" + mPassword).getBytes(), 
	                    Base64.NO_WRAP);
				NameValuePair base64EncodedCredentials_nvp = new BasicNameValuePair("base64EncodedCredentials", base64EncodedCredentials);
				//wrap the sync_values as name-value pairs
				NameValuePair sync_farmer_nvp = new BasicNameValuePair("sync_farmer", Integer.toString(sync_farmer));
				NameValuePair sync_suppliers_grader_nvp = new BasicNameValuePair("sync_suppliers_grader", Integer.toString(sync_suppliers_grader));
				//declare a new ArrayList to store the name value pairs
				mParams = new ArrayList<NameValuePair>();
				//mParams.add(user_nvp);
				//mParams.add(password_nvp);
				mParams.add(base64EncodedCredentials_nvp);
				mParams.add(sync_farmer_nvp);
				mParams.add(sync_suppliers_grader_nvp);
				Log.d(LOG,mUsername);
				Log.d(LOG,mPassword);
				//get the UploadData helper class to interact with the MySQL database
				login_helper.setUrl(login_url);
				login_helper.setParams(mParams);
				login_helper.execute(getApplicationContext());				
			}
		});	
		
		
		
	}
	

}
