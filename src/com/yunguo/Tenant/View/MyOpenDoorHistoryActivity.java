package com.yunguo.Tenant.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.yunguo.InfoBean.OpenHistorybean;
import com.yunguo.Tenant.R;
import com.yunguo.TenantAdapter.HouseHistoryAdapter;
import com.yunguo.TenantAdapter.HouseHistoryOpenAdapter;
import com.yunguo.TenantAdapter.HouseMessageAdapter;
import com.yunguo.TenantModel.HouseMessageImpl;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class MyOpenDoorHistoryActivity extends Activity {
	/**
	 * listview
	 */
	private PullToRefreshListView HistoryMessge_list;
	/**
	 * 房屋列表适配器
	 */
	private HouseHistoryOpenAdapter messgeListAdapter;
	private List<OpenHistorybean> data = new ArrayList<OpenHistorybean>();
	/**
	 * 求情网络
	 */
	HouseMessageImpl houseMessageImpl = new HouseMessageImpl();
	String masg = "";// 返回信息

	/**
	 * 等待窗口
	 */
	private ProgressDialog progressDialog;

	/**
	 * 记录上拉下拉
	 */
	private Boolean fls = true;

	/*
	 * @Override protected void onResume() { // TODO Auto-generated method stub
	 * super.onResume(); // 当activity加载时,加载网络数据 // 显示等待框 progressDialog =
	 * ProgressDialog.show(this, "请稍等", "正在获取...", true); // 获取数据 new
	 * Thread(thread).start(); }
	 */

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.history_open_activity);
		HistoryMessge_list = (PullToRefreshListView) findViewById(R.id.history_OpenHouseMessge_list);
		data = getData();  
		messgeListAdapter = new HouseHistoryOpenAdapter(data, this);
		HistoryMessge_list.setAdapter(messgeListAdapter);
		HistoryMessge_list.setMode(Mode.BOTH);

		init();
		HistoryMessge_list
				.setOnRefreshListener(new OnRefreshListener2<ListView>() {
					@Override
					public void onPullDownToRefresh(
							PullToRefreshBase<ListView> refreshView) {
						// TODO Auto-generated method stub
						OpenHistorybean bean = new OpenHistorybean();  
						bean.setHistoryid("1002");
						bean.setHistoryname("王五");
						bean.setHistorytime("2015/12/15 12:29");  
		               //messgeListAdapter.addFirst(bean);
						new FinishRefresh().execute();
						messgeListAdapter.notifyDataSetChanged();
					}

					@Override
					public void onPullUpToRefresh(
							PullToRefreshBase<ListView> refreshView) {
						// TODO Auto-generated method stub
						OpenHistorybean bean = new OpenHistorybean();  
						bean.setHistoryid("1002");
						bean.setHistoryname("王五");
						bean.setHistorytime("2015/12/15 12:29");
						//messgeListAdapter.addLast(bean);
						new FinishRefresh().execute();
						messgeListAdapter.notifyDataSetChanged();
					}
				});
	};

	private void init() {
		ILoadingLayout startLabels = HistoryMessge_list.getLoadingLayoutProxy(
				true, false);
		startLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
		startLabels.setRefreshingLabel("正在载入...");// 刷新时
		startLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示

		ILoadingLayout endLabels = HistoryMessge_list.getLoadingLayoutProxy(
				false, true);
		endLabels.setPullLabel("上拉刷新...");// 刚下拉时，显示的提示
		endLabels.setRefreshingLabel("正在载入...");// 刷新时
		endLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示
	}

	private List<OpenHistorybean> getData() {
		List<OpenHistorybean> list = new ArrayList<OpenHistorybean>();
		for (int i = 0; i < 10; i++) {
			OpenHistorybean bean = new OpenHistorybean();
			bean.setHistoryid("1002"+i);
			bean.setHistoryname("王五");
			bean.setHistorytime("2015/12/15 12:29");
			list.add(bean);
		}

		return list;
	}

	private class FinishRefresh extends AsyncTask<Void, Void, Void> {
		List<OpenHistorybean> list = new ArrayList<OpenHistorybean>();
		@Override
		protected Void doInBackground(Void... params) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			for (int i = 0; i < 10; i++) {
				OpenHistorybean bean = new OpenHistorybean();
				bean.setHistoryid("1002"+i);
				bean.setHistoryname("王五");
				bean.setHistorytime("2015/12/15 12:29");
				data.add(bean);
			}
 
			messgeListAdapter.notifyDataSetChanged();
			HistoryMessge_list.onRefreshComplete();
			Toast.makeText(MyOpenDoorHistoryActivity.this,"执行结束", Toast.LENGTH_SHORT).show();
		}
		
		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
	}
}
