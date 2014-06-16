package com.example.jsoncachingtutorial.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.widget.Toast;

public class cachingSetting {
	
	public File cacheDir;
	public Activity hActivity;
	
	public cachingSetting(Activity activity, File file){
		cacheDir = file;
		hActivity = activity; 
}

	//cache path in android
	public File cachePath(String fileName){
		File readFF = new File(cacheDir,  fileName);
		return readFF; 
	}
	
	//check the cache file is exist
	public boolean checkCachefile(String fileName){ 
		File file =new File(cacheDir+"/" + fileName); 
		if(file.exists()){ 
			return true;
		}else{ 
			return false;
		}
	}
	 //cache the value 
	public void createCache(String fileName, String JsonValue){
		FileOutputStream fos;
		try {   
			fos = new FileOutputStream(cachePath(fileName));
			String content = JsonValue;
			fos.write(content.getBytes());
			fos.close(); 
			Toast.makeText( hActivity,  fileName + " saved",  	Toast.LENGTH_LONG).show(); 
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		}   
	}
	@SuppressWarnings("resource")
	public String readCacheFile(String fileName){
		FileInputStream fis;
    	String content = "";
    	try { 
    		fis = new FileInputStream(cachePath(fileName));
    		byte[] input = new byte[fis.available()];
    		while (fis.read(input) != -1) {}
    		content += new String(input);
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();	
    	} 
		Toast.makeText(hActivity,  content,  	Toast.LENGTH_LONG).show();   
		return content; 
	}
}
