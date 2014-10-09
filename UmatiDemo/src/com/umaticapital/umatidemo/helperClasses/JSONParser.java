package com.umaticapital.umatidemo.helperClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {

	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	public int statusCode;

	// constructor
	public JSONParser() {

	}

	// function get json from url
	// by making HTTP POST or GET mehtod
	public JSONObject makeHttpRequest(String url, String method,
			List<NameValuePair> params) {

		// Making HTTP request
		try {
			
			// check for request method
			if(method == "POST"){
				// request method is POST
				// defaultHttpClient
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(url);
				
				httpPost.setHeader("Authorization", params.get(0).getValue());
				httpPost.setEntity(new UrlEncodedFormEntity(params));
				
				HttpResponse httpResponse = httpClient.execute(httpPost);
				StatusLine status = httpResponse.getStatusLine();
				statusCode = status.getStatusCode();
				Log.i("Servercode",""+statusCode);
				
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();
				
			}else if(method == "GET"){
				// request method is GET
				DefaultHttpClient httpClient = new DefaultHttpClient();
				String paramString = URLEncodedUtils.format(params, "utf-8");
				url += "?" + paramString;
				Log.i("URL",url);
				HttpGet httpGet = new HttpGet(url);

				HttpResponse httpResponse = httpClient.execute(httpGet);
				StatusLine status = httpResponse.getStatusLine();
				statusCode = status.getStatusCode();
				Log.i("Servercode",""+statusCode);
				
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();
			}			
			

		} catch (UnsupportedEncodingException e) {
			Log.i("ERR","Unsupported Encoding");
			try {
				jObj.put("success", -99);
				jObj.put("message", "Server Not Responding. Try again later.");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			Log.i("ERR","ClientProtocol");
			e.printStackTrace();
			try {
				jObj.put("success", -99);
				jObj.put("message", "Server Not Responding. Try again later.");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
		} catch (IOException e) {
			Log.i("ERR","IOException");
			 try {
				jObj.put("success", -99);
				jObj.put("message", "Server Not Responding. Try again later.");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
			e.printStackTrace();
		}
		switch(statusCode)
		{
			case 200:
				try 
				{
					BufferedReader reader = new BufferedReader(new InputStreamReader(
							is, "iso-8859-1"), 8);
					StringBuilder sb = new StringBuilder();
					String line = null;
					while ((line = reader.readLine()) != null) {
						//Log.i("Server response",line);
						sb.append(line + "\n");
					}
					is.close();
					json = sb.toString();
					Log.e("tag",json);
				} 
				catch (Exception e) 
				{
					Log.e("Buffer Error", "Error converting result " + e.toString());
				}
				break;
			case 408:
				try {
					jObj.put("success", 2);
					jObj.put("message", "Request Timeout");
					return jObj;
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				break;
		
			case 502:
				try {
					jObj.put("success", 2);
					jObj.put("message", "502 Bad Gateway Error");
					return jObj;
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				break;
			case 504:
				try {
					jObj.put("success", 2);
					jObj.put("message", "Gateway Time-Out");
					return jObj;
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				break;
				
			 default:
				 try {
					 jObj.put("success", 999);
					 jObj.put("message", "Internet not working");
					 return jObj;
				 } catch (JSONException e1) {
					 Log.e("GFF","No internet");
				 }
				 break;

		}
		//Log.e("JSON",json);
		// try parse the string to a JSON object
		try 
		{
			jObj = new JSONObject(json);
		}
		catch (JSONException e)
		{
			Log.e("JSON Parser", "Error parsing data " + e.toString());
			try {
				jObj.put("success", -99);
				jObj.put("message", "Bad Internet Connection. Try again later.");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
		}

		// return JSON String
		return jObj;

	}
	public void HttpRequest(String url, String method,
			List<NameValuePair> params) {

		// Making HTTP request
		try {
			
			// check for request method
			if(method == "POST"){
				// request method is POST
				// defaultHttpClient
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(url);
				httpPost.setHeader("Authorization", params.get(0).getValue());
				httpPost.setEntity(new UrlEncodedFormEntity(params));

				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();
				
			}else if(method == "GET"){
				// request method is GET
				DefaultHttpClient httpClient = new DefaultHttpClient();
				String paramString = URLEncodedUtils.format(params, "utf-8");
				url += "?" + paramString;
				HttpGet httpGet = new HttpGet(url);

				HttpResponse httpResponse = httpClient.execute(httpGet);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();
			}			
			

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
