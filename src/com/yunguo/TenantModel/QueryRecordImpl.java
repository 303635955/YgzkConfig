package com.yunguo.TenantModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yunguo.InfoBean.HouseRentingBean;
import com.yunguo.InfoBean.OpenDoorbean;
import com.yunguo.TenantUtil.AppConfigUrlUtil;
import com.yunguo.TenantUtil.HttpPostUtil;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

public class QueryRecordImpl implements QueryRecordModel{
	
	/**
	 * 租房记录
	 */
	@SuppressLint("NewApi")
	@Override
	public void GetHouseRecord(String param, Handler handler) {
		// TODO Auto-generated method stub
		String postUrl = AppConfigUrlUtil.GetConfig("urlKey");
		String jsondata = HttpPostUtil.PostStringToUrl(postUrl, param);
		JSONObject jsonObject = null;
		Message message = new Message();
		try {
			jsonObject = new JSONObject(jsondata);
			String ret = jsonObject.getString("ret");
			List<HouseRentingBean> list = new ArrayList<HouseRentingBean>();
			if(ret.equals("0")){
				JSONArray jsonarray = new JSONArray(jsonObject.get("data"));
				for (int i = 0; i < jsonarray.length(); i++) {
					JSONObject jsonObjectSon= (JSONObject)jsonarray.opt(i);
					HouseRentingBean houseRentingBean = new HouseRentingBean();
					houseRentingBean.setHouseName(jsonObjectSon.get("HouseName")+"");
					houseRentingBean.setOwnerName(jsonObjectSon.get("OwnerName")+"");
					houseRentingBean.setHouseAdress(jsonObjectSon.get("HouseAddress")+"");
					houseRentingBean.setRentingTime(jsonObjectSon.get("RentingTime")+"");
			        list.add(houseRentingBean);
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
			message.what = 2 ;
			handler.sendMessage(message);
			e.printStackTrace();
		}
	}
	
	/**
	 * 开门记录
	 */
	@SuppressLint("NewApi")
	@Override
	public void GetHOpenDoorRecord(String param, Handler handler) {
		// TODO Auto-generated method stub
		String postUrl = AppConfigUrlUtil.GetConfig("urlKey");
		String jsondata = HttpPostUtil.PostStringToUrl(postUrl, param);
		JSONObject jsonObject = null;
		Message message = new Message();
		try {
			jsonObject = new JSONObject(jsondata);
			String ret = jsonObject.getString("ret");
			List<OpenDoorbean> list = new ArrayList<OpenDoorbean>();
			if(ret.equals("0")){
				JSONArray jsonarray = new JSONArray(jsonObject.get("data"));
				for (int i = 0; i < jsonarray.length(); i++) {
					JSONObject jsonObjectSon= (JSONObject)jsonarray.opt(i);
					OpenDoorbean openDoorbean = new OpenDoorbean();
					openDoorbean.setHouseName(jsonObjectSon.get("HouseName")+"");
					openDoorbean.setDoorId(jsonObjectSon.get("DoorId")+"");
					openDoorbean.setUserName(jsonObjectSon.get("UserName")+"");
					openDoorbean.setOpenDoorTime(jsonObjectSon.get("OpenDoorTime")+"");
			        list.add(openDoorbean);
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
			message.what = 2 ;
			handler.sendMessage(message);
			e.printStackTrace();
		}
	}
	
	/**
	 * 刷卡记录
	 */
	@Override
	public void GetSlotCardRecord(String param, Handler handler) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 登录日志
	 */
	@Override
	public void GetLoginRecord(String param, Handler handler) {
		// TODO Auto-generated method stub
		
	}

}
