package com.phincon.wls.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtils {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	public void printLog(String msg) {
		System.out.println(sdf.format(new Date())+ " -> "+ msg);
	}

}
