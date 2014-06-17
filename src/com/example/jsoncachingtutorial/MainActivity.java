package com.example.jsoncachingtutorial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.example.jsoncachingtutorial.api_classes.GetAnnouncements;
import com.example.jsoncachingtutorial.api_classes.GetAnnouncements.Announcement;
import com.example.jsoncachingtutorial.lib.cachingSetting;
import com.example.jsoncachingtutorial.ref.Cal;
import com.example.jsoncachingtutorial.ref.Ref;
import com.google.gson.Gson;

public class MainActivity extends Activity {

	
	AQuery aq;
	Gson gson = new Gson();
	Bundle bundleData;
	
	cachingSetting cSetting;
	  
	GetAnnouncements announcementList = new GetAnnouncements();
	List<Announcement> announcement_list = new ArrayList<Announcement>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); 
		this.loadView(); 
	}
	
	public void loadView(){
		cSetting = new cachingSetting(this, getCacheDir());
		 aq = new AQuery(this);
		aq.progress(R.id.progressBarAnnouncement).ajax(Ref.getUrl(Ref.GETANNOUNCEMENTS), JSONObject.class, this, "jsonCb"); 
	} 
	 
	public void jsonCb(String url, JSONObject json, AjaxStatus status){ 
		String date = Cal.currentDate();
		String fileName =  "announcements" + "_" + "1"+ "_" + date;  
		
		if(cSetting.checkCachefile(fileName)){  
			announcementList = gson.fromJson(String.valueOf(cSetting.readCacheFile(fileName)), GetAnnouncements.class);
		}else{ 
			 
				if (status.getCode() == AjaxStatus.NETWORK_ERROR){
					aq.id(R.id.imgNoConnection).visible();
					return;
				} else if( json == null){
					Toast.makeText(this, "No data!!", Toast.LENGTH_SHORT).show(); 
				}
				cSetting.createCache(fileName, json.toString());
					announcementList = gson.fromJson(String.valueOf(json), GetAnnouncements.class); 
		} 

 
		if (announcementList.result_code == 0) { 
					populateAnnouncement();
					populateAnnouncementList();
					AnnouncementDetails();
		} else {
			Toast.makeText(this, announcementList.result_message, Toast.LENGTH_SHORT).show();
		} 
	}
	protected void AnnouncementDetails() {
		ListView lst_announcement = (ListView) findViewById(R.id.lst_announcement);
		lst_announcement.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				Announcement announce_details_holder = announcement_list.get(position);
						
				bundleData = new Bundle();
				 
				bundleData.putString("announce_title", announce_details_holder.title);
				bundleData.putString("announce_content", announce_details_holder.content);
				bundleData.putString("announce_date", announce_details_holder.date);
				
				//can pass using intent :D
				 Toast.makeText(getApplicationContext(),  announce_details_holder.title + "/ " +   announce_details_holder.content, Toast.LENGTH_SHORT ).show();
				}
			});
	}
	
	
	protected void populateAnnouncement() {
		announcement_list = announcementList.announcement;
	}
 
	private void populateAnnouncementList() { 
		ArrayAdapter<Announcement> adapter = new MyListAdapter();
		ListView lst_announcement = (ListView)  findViewById(R.id.lst_announcement);
		lst_announcement.setAdapter(adapter);
	}
	
	
	
private class MyListAdapter extends ArrayAdapter<Announcement> {
		
		public MyListAdapter() {
			super( MainActivity.this , R.layout.announcement_row, announcement_list);
		}
 
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View itemView = convertView;
			
			if (itemView == null) {
				itemView = getLayoutInflater().inflate(R.layout.announcement_row, parent, false);
			}
			
			itemView.setBackgroundColor(Color.parseColor((position%2) == 0 ? "#FFFFFF" : "#F0F2F5"));

			Announcement announce_holder = announcement_list.get(position);
			
			AQuery aqBody = new AQuery(itemView);
			
			String content = announce_holder.content;
			if (content.length() > 120){
				content = content.substring(0, 120) + " 。。。。";
			}
			 
			aqBody.id(R.id.announcement_title).text(announce_holder.title);
			aqBody.id(R.id.announcement_message).text(content);
			aqBody.id(R.id.announcement_date).text(announce_holder.date);

			return itemView;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
