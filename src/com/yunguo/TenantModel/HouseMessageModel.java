package com.yunguo.TenantModel;


import android.os.Handler;


public interface HouseMessageModel {
	/**
	 * 刷新房屋列表
	 * @return
	 */
	String refreshHouseMessage(String paramStr,Handler handler);
	
	
}
