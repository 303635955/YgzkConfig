package com.yunguo.InfoBean;

/**
 * ��¼��־
 * @author Administrator
 *
 */
public class UserLogbean {
	private String OperationAddress;	//�����ص�
	private String OperationTime;	//����ʱ��
	private String OperationType;	//��������
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
