package com.yunguo.InfoBean;

public class SwingHistorybean {
	private String HouseName;	//房屋名
	private String UserName;	//刷卡用户名
	private String CreditCardDoorId;	//门ID
	private String CreditCardTime;	//刷卡时间
	public String getHouseName() {
		return HouseName;
	}
	public void setHouseName(String houseName) {
		HouseName = houseName;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getCreditCardDoorId() {
		return CreditCardDoorId;
	}
	public void setCreditCardDoorId(String creditCardDoorId) {
		CreditCardDoorId = creditCardDoorId;
	}
	public String getCreditCardTime() {
		return CreditCardTime;
	}
	public void setCreditCardTime(String creditCardTime) {
		CreditCardTime = creditCardTime;
	}
}
