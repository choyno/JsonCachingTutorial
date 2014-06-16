package com.example.jsoncachingtutorial.api_classes;

import java.util.List;

public class GetAnnouncements extends GetCommonResponse {
		
	public List<Announcement> announcement;
	
	public class Announcement {
		public String title;
		public String content;
		public String date;
	}
	
}
