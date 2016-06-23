package com.yunguo.InfoBean;

import java.util.List;
import java.util.Map;

public class SwingHistorybean {
	private List<Map<String, Object>> listItems;
	private String historyid;
	private String historyhousename;
	private String historyhousething;
	private String historytime;
	
	public String getHistoryid() {
		return historyid;
	}

	public void setHistoryid(String historyid) {
		this.historyid = historyid;
	}


	public String getHistoryhousename() {
		return historyhousename;
	}

	public void setHistoryhousename(String historyhousename) {
		this.historyhousename = historyhousename;
	}

	public String getHistoryhousething() {
		return historyhousething;
	}

	public void setHistoryhousething(String historyhousething) {
		this.historyhousething = historyhousething;
	}

	public String getHistorytime() {
		return historytime;
	}

	public void setHistorytime(String historytime) {
		this.historytime = historytime;
	}

	public void setListItems(List<Map<String, Object>> listItems) {
		this.listItems = listItems;
	}

	public List<Map<String, Object>> getListItems() {
		return listItems;
	}
}
