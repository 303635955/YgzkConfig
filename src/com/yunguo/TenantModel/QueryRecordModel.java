package com.yunguo.TenantModel;

import android.os.Handler;

public interface QueryRecordModel {
	void GetHouseRecord(String param,Handler handler);
	void GetHOpenDoorRecord(String param,Handler handler);
	void GetSlotCardRecord(String param,Handler handler);
	void GetLoginRecord(String param,Handler handler);
}
