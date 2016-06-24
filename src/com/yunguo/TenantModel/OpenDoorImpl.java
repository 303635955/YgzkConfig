package com.yunguo.TenantModel;

import org.json.JSONException;
import org.json.JSONObject;

import com.yunguo.TenantUtil.AppConfigUrlUtil;
import com.yunguo.TenantUtil.HttpPostUtil;

import android.os.Handler;

public class OpenDoorImpl implements OpenDoorModel{

	@Override
	public void openDoorPost(String paramStr, Handler handler) {
		String postUrl = AppConfigUrlUtil.GetConfig("urlKey");
		String jsondata = HttpPostUtil.PostStringToUrl(postUrl, paramStr);
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(jsondata);
			String ret = jsonObject.getString("ret");
			
			if(ret.equals("0")){
				handler.sendEmptyMessage(5);
			}else if(ret.equals("1")){
				handler.sendEmptyMessage(6);
			}else{
				handler.sendEmptyMessage(7);
			}
		} catch (JSONException e) {
			handler.sendEmptyMessage(7);
			e.printStackTrace();
		}
		
	}

}
