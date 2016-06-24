package com.yunguo.TenantModel;

import android.os.Handler;

public interface OpenDoorModel {
	/**
	 * 烤漆门，关门请求
	 * @param paramStr
	 * @param handler
	 */
	void openDoorPost(String paramStr,Handler handler);
}
