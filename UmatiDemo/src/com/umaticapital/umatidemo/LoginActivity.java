package com.umaticapital.umatidemo;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.umaticapital.umatidemo.helperClasses.LoginDBHelper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity{
	
	Button mLogin;
	Button mSignUp;
	
	String username;
	String password;
	String LOG = "Login Activity";
	
	TextView error_text;
	List<NameValuePair> params;
	
	LoginDBHelper login_helper;
	
	String login_url = "http://staging.umaticapital.com/api /v1/authenticate";

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mSignUp = (Button) findViewById(R.id.button_login_signup); 
		error_text = (TextView) findViewById(R.id.tv_login_errormsg);
		
		login_helper = new LoginDBHelper(this);
		
		mLogin  = (Button) findViewById(R.id.button_login_submit);
		mLogin.setOnClickListener(new View.OnClickListener() {
			
			@SuppressLint("ShowToast")
			@Override
			public void onClick(View v) {
				//get the username and password from the edit text views
				username = (((EditText) findViewById(R.id.et_login_username)).getText()).toString();
				password = (((EditText) findViewById(R.id.et_login_password)).getText()).toString();
				//wrap the username and password as name value pairs
				NameValuePair user_nvp = new BasicNameValuePair("username", username);
				NameValuePair password_nvp = new BasicNameValuePair("password", password);
				params = new ArrayList<NameValuePair>();
				params.add(user_nvp);
				params.add(password_nvp);
				Log.d(LOG,username);
				Log.d(LOG,password);
				//get the UploadData helper class to interact with the MySQL database
				login_helper.setUrl(login_url);
				login_helper.setParams(params);
				login_helper.execute(getApplicationContext());				
			}
		});	
		
		
		
	}
	

}
