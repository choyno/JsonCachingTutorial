package com.example.jsoncachingtutorial.ref;

import android.util.Log;

import com.androidquery.auth.BasicHandle;
 
public class Ref {
  
	public static final String URL = "http://192.168.1.119/dmmApi/";

	public static final String US = "?";
	public static final String KEY = "Uggk45hcn98";
	
	public static final String GET_URL = URL + US + "key=" + KEY;
	
	public static final String BASIC_AUTH_USERNAME = "dmm";
	public static final String BASIC_UATH_PASSWORD = "ldmm1";
	   	
	public static final String GETANNOUNCEMENTS = "ApiGetAnnouncements.php";
 
	public static final BasicHandle BASIC_AUTH = new BasicHandle(BASIC_AUTH_USERNAME, BASIC_UATH_PASSWORD);

	public static String getUrl(String api){ 
		Log.d("asd", URL + api + US + "key=" + KEY);
		return URL + api + US + "key=" + KEY;
	} 
	
}
