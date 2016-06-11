package com.yunguo.InfoBean;

import java.util.List;
import java.util.Map;

public class Historybean {
	private List<Map<String, Object>> listItems;
	private String historyname;
	private String historyownername;
	private String historyadress;
	private String historytime;
	
	public List<Map<String, Object>> getListItems() {
		return listItems;
	}
	public void setListItems(List<Map<String, Object>> listItems) {
		this.listItems = listItems;
	}
	public String getHistoryname() {
		return historyname;
	}
	public void setHistoryname(String historyname) {
		this.historyname = historyname;
	}
	public String getHistoryownername() {
		return historyownername;
	}
	public void setHistoryownername(String historyownername) {
		this.historyownername = historyownername;
	}
	public String getHistoryadress() {
		return historyadress;
	}
	public void setHistoryadress(String historyadress) {
		this.historyadress = historyadress;
	}
	public String getHistorytime() {
		return historytime;
	}
	public void setHistorytime(String historytime) {
		this.historytime = historytime;
	}

}
