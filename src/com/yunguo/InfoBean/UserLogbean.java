package com.yunguo.InfoBean;

/**
 * 登录日志
 * @author Administrator
 *
 */
public class UserLogbean {
	private String OperationAddress;	//操作地点
	private String OperationTime;	//操作时间
	private String OperationType;	//操作类型
	public String getOperationType() {
		return OperationType;
	}
	public void setOperationType(String operationType) {
		OperationType = operationType;
	}
	public String getOperationAddress() {
		return OperationAddress;
	}
	public void setOperationAddress(String operationAddress) {
		OperationAddress = operationAddress;
	}
	public String getOperationTime() {
		return OperationTime;
	}
	public void setOperationTime(String operationTime) {
		OperationTime = operationTime;
	}
}
