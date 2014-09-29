package com.umaticapital.umatidemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class LoginActivity extends Activity{
	
	Button mLogin;
	Button mSignUp;
	
	String username;
	String pasword;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mLogin  = (Button) findViewById(R.id.button_login_submit);
		mSignUp = (Button) findViewById(R.id.button_login_signup); 
	}
	

}
