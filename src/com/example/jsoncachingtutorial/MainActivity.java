package com.example.jsoncachingtutorial;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.example.jsoncachingtutorial.api_classes.GetAnnouncements;
import com.example.jsoncachingtutorial.ref.Ref;
import com.google.gson.Gson;

public class MainActivity extends Activity {

	
	AQuery aq;
	Gson gson = new Gson();
	GetAnnouncements announcementList = new GetAnnouncements();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); 
		this.loadView(); 
	}
	
	public void loadView(){
		  aq = new AQuery(this);
		aq.progress(R.id.progressBarAnnouncement).ajax(Ref.getUrl(Ref.GETANNOUNCEMENTS), JSONObject.class, this, "jsonCb"); 
	} 
	
	public void jsonCb(String url, JSONObject json, AjaxStatus status){
		Log.d("asdasd", "=============");
		
		
		if (status.getCode() == AjaxStatus.NETWORK_ERROR){
			aq.id(R.id.imgNoConnection).visible();
			return;
		} 
		announcementList = gson.fromJson(String.valueOf(json), GetAnnouncements.class);
		
		if (announcementList.result_code == 0) {
					Log.d("asdasd", announcementList+"");
					Log.d("asdasd", json.toString());
		} else {
			Toast.makeText(this, announcementList.result_message, Toast.LENGTH_SHORT).show();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
