package com.yunguo.TenantModel;


import android.os.Handler;


public interface HouseMessageModel {
	/**
	 * ˢ�·����б�
	 * @return
	 */
	void refreshHouseMessage(String paramStr,Handler handler);
	
	void getDoorMessage(String paramStr,Handler handler);
}
