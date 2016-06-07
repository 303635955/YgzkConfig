package com.yunguo.TenantUtil;

import java.util.HashMap;
import java.util.Map;

public class AppConfigUrlUtil {
	
	private static Map<String,String> mCfgs;
	
	public AppConfigUrlUtil() {
		mCfgs =  new HashMap<String, String>();
		mCfgs.put("UrlAddhouse", "http://120.25.65.125:8118/Client1/AppAddhouse");
	}
	
	public static String GetConfig(String key){
		if(mCfgs.containsKey(key)){
			return mCfgs.get(key);
		}
		return "";
	}
}
