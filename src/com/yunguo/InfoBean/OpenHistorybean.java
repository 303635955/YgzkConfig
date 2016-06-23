package com.yunguo.InfoBean;

import java.util.List;
import java.util.Map;

public class OpenHistorybean {
	private List<Map<String, Object>> listItems;
	private String historyid;
	private String historyname;
	private String historytime;
	
	public String getHistoryid() {
		return historyid;
	}

	public void setHistoryid(String historyid) {
		this.historyid = historyid;
	}

	public String getHistoryname() {
		return historyname;
	}

	public void setHistoryname(String historyname) {
		this.historyname = historyname;
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
