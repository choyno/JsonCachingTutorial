package com.example.jsoncachingtutorial.ref;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

 
public class Cal {
	
	public static SimpleDateFormat yearMonthFormat() {
		return new SimpleDateFormat("yyyy-MM", Locale.JAPAN);
	}

	public static String currentDate() {
		return new SimpleDateFormat("yyyy-MM-dd", Locale.JAPAN)
				.format(new Date());
	}
}
