package com.yunguo.InfoBean;

import java.util.List;
import java.util.Map;

public class NoteHistorybean {
	private List<Map<String, Object>> listItems;
	private String historything;
	private String historyname;
	private String historytime;
	

	public String getHistorything() {
		return historything;
	}

	public void setHistorything(String historything) {
		this.historything = historything;
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
