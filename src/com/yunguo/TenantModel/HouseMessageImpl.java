package com.yunguo.TenantModel;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yunguo.TenantUtil.AppConfigUrlUtil;
import com.yunguo.TenantUtil.HttpPostUtil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class HouseMessageImpl implements HouseMessageModel{

	/**
	 * 获取房屋列表
	 */
	@SuppressLint("NewApi")
	@Override
	public  void refreshHouseMessage( String paramStr, Handler handler) {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		String postUrl = AppConfigUrlUtil.GetConfig("urlKey");

		String jsondata = HttpPostUtil.PostStringToUrl(postUrl, paramStr);
		Message message = new Message();
		try {
			JSONObject jsonObject = new JSONObject(jsondata);
			String ret = jsonObject.getString("ret");
			
			if(ret.equals("0")){
				JSONArray jsonarray = new JSONArray(jsonObject.get("data"));
				for (int i = 0; i < jsonarray.length(); i++) {
					Map<String,String> map = new HashMap<String, String>();
			        JSONObject jsonObjectSon= (JSONObject)jsonarray.opt(i);
			        map.put("id",jsonObjectSon.get("id")+"");
			        map.put("housename",jsonObjectSon.get("housename")+"");
			        map.put("address",jsonObjectSon.get("address")+"");
			        map.put("owdername",jsonObjectSon.get("owdername")+"");
			        list.add(map);
				}
				message.what = 0 ;
				message.obj = list;
				handler.sendMessage(message);
			}else if(ret.equals("1")){
				message.what = 1 ;
				handler.sendMessage(message);
			}else{
				message.what = 2 ;
				handler.sendMessage(message);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			message.what = 2 ;
			handler.sendMessage(message);
		}
	}

	@SuppressLint("NewApi")
	@Override
	public void getDoorMessage(String paramStr, Handler handler) {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		String postUrl = AppConfigUrlUtil.GetConfig("urlKey");

		String jsondata = HttpPostUtil.PostStringToUrl(postUrl, paramStr);
		Message message = new Message();
		try {
			JSONObject jsonObject = new JSONObject(jsondata);
			String ret = jsonObject.getString("ret");
			
			if(ret.equals("0")){
				JSONArray jsonarray = new JSONArray(jsonObject.get("data"));
				for (int i = 0; i < jsonarray.length(); i++) {
					Map<String,String> map = new HashMap<String, String>();
			        JSONObject jsonObjectSon= (JSONObject)jsonarray.opt(i);
			        map.put("id",jsonObjectSon.get("id")+"");
			        map.put("doorname",jsonObjectSon.get("doorname")+"");
			        map.put("doorstatus",jsonObjectSon.get("doorstatus")+"");
			        list.add(map);
				}
				message.what = 0 ;
				message.obj = list;
				handler.sendMessage(message);
			}else if(ret.equals("1")){
				message.what = 1 ;
				handler.sendMessage(message);
			}else{
				message.what = 2 ;
				handler.sendMessage(message);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			message.what = 2 ;
			handler.sendMessage(message);
		}
	}
}
