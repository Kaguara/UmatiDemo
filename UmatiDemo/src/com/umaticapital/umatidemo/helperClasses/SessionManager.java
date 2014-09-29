package com.umaticapital.umatidemo.helperClasses;

import java.util.HashMap;

import com.umaticapital.umatidemo.LoginActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {
	 //Shared Preferences
	SharedPreferences pref;
	
	//Editor for shared preferences
	Editor editor;
	
	//Application Context
	Context _context;
	
	//shared preferences mode
	int PRIVATE_MODE = 0;
	
	//Shared pereferences file name
	private static final String PREF_NAME = "WALLET_LOGIN_PREF";
	
	//all shared preferences keys
	private static final String IS_LOGIN = "IsLoggedIn";
	
	//User name(public in order to be accessed from other activities)
	public static final String KEY_NAME = "username";
	
	//constructor
	@SuppressLint("CommitPrefEdits")
	public SessionManager(Context context){
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}
	
	/*
	 * Create Login Session
	 * @name the logged in user's username
	 */
	public void createLoginSession(String name){
		//set LoggedIn value to True
		editor.putBoolean(IS_LOGIN, true);
		
		//commit changes
		editor.commit();
	}
	
	/*
     * Get stored session data
     */
	public HashMap<String, String> getUserDetails(){
       HashMap<String, String> user = new HashMap<String, String>();
       // user name
       user.put(KEY_NAME, pref.getString(KEY_NAME, null));
           
       // return user
       return user;
   }
	
	/*
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             
            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             
            // Staring Login Activity
            _context.startActivity(i);
        }
         
    }
    
    /*
     * Clear session details
     */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
         
        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         
        // Staring Login Activity
        _context.startActivity(i);
    }
	
	/*
     * Quick check for login
     * #return the logged in state
     */
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
