package com.umaticapital.umatidemo.helperClasses;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.umaticapital.umatidemo.MainActivity;
import com.umaticapital.umatidemo.helperClasses.SessionManager;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class LoginDBHelper{
	public ProgressDialog pDialog;
	public Context mContext;
	public List<NameValuePair> params = new ArrayList<NameValuePair>();
	public JSONParser jsonParser;
	public String callActivity=null;
	public int uploadState=0 ;
	public String error_state;
	private String TAG_SUCCESS = "error";
	public String userName ;
	public String message;
	static String EXTRA_USERNAME = "EXTRA_USERNAME";
	public Bitmap bmp;
	public SessionManager session;
	
	// url to create new product
	private String url_link;
	
	public LoginDBHelper(Context context){
		this.mContext = context;
		jsonParser = new JSONParser();
	}
	public void setUrl(String url){
		this.url_link = url;
	}
	public void setCaller(String activity)
	{
		this.callActivity = activity;
	}
	public void setParams(List<NameValuePair> params){
		this.params = params;
	}
	
	@SuppressWarnings("unchecked")
	public void execute(Context context){
		try
		{
			new Login(context).execute(params);
		}
		catch(Exception e)
		{
			Log.i("Async","Async Task Error");
			Toast.makeText(mContext, "Error Uploading", Toast.LENGTH_LONG).show();
		}
	}
	
	class Login extends AsyncTask<List<NameValuePair>, String, String> {
		
		
		Context context;
	    private Login(Context context) {
	        this.context = context.getApplicationContext();
	    }
		
		@Override
		protected void onPreExecute() {
			//super.onPreExecute();
			pDialog = new ProgressDialog(mContext);
			pDialog.setMessage("Authenticating ....");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		@Override
		protected String doInBackground(List<NameValuePair>... arg0) {
			//initialize the session manager
	        session = new SessionManager(context);
			
			// Building Parameters
			List<NameValuePair> params = arg0[0];
			
			// getting JSON Object
			JSONObject json = jsonParser.makeHttpRequest(url_link, "POST", params);
			
			// check for success tag
			try 
			{
				error_state = json.getString(TAG_SUCCESS);
				message = json.getString("message");
			} catch (JSONException e) {
				uploadState=999;
				e.printStackTrace();
				Log.e("tag3","Throwing JSON Exception");
			}
	
				return null;
		}
	
		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		@SuppressLint("NewApi")
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			if(error_state == "false"){
			   //Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
			   pDialog.dismiss();
			   
			   //initialize a new user login session
			   session.createLoginSession(message);
			   
			   //launch the main activity page
			   Intent launch_home_activity = new Intent(context,MainActivity.class);
			   launch_home_activity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			   launch_home_activity.putExtra(EXTRA_USERNAME, message);
			   context.startActivity(launch_home_activity);			   
			}
			else if(error_state == "true")
			{
				Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
				pDialog.dismiss();
			}
			else
			{
				Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
				pDialog.dismiss();
			}

		}

	}

}