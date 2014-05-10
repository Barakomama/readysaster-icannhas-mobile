package com.icannhas.readysaster;

import android.util.Log;

public class Utilities {
	
	public static final String DEBUG_TAG = "READYSASTER_DEBUG";
	public static final String ERROR_TAG = "READYSASTER_ERROR";
	public static final String INFO_TAG = "READYSASTER_INFO";
	
	// Helper methods
	
	public static void logD(String msg) {
		Log.d(DEBUG_TAG, msg);
	}
	
	public static void logE(String msg) {
		Log.e(ERROR_TAG, msg);
	}
	
	public static void logI(String msg) {
		Log.i(INFO_TAG, msg);
	}
}
