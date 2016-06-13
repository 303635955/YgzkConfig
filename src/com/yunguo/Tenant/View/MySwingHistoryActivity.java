package com.yunguo.Tenant.View;

import java.util.ArrayList;
import java.util.List;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.yunguo.InfoBean.SwingHistorybean;
import com.yunguo.Tenant.R;
import com.yunguo.TenantAdapter.HouseHistoryAdapter;
import com.yunguo.TenantAdapter.HouseHistorySwingAdapter;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class MySwingHistoryActivity extends Activity {
	/**
	 * listview
	 */
	private PullToRefreshListView HistoryMessge_list;
	/**
	 * �����б�������
	 */
	private HouseHistorySwingAdapter messgeListAdapter;
	private String[] list = {"ˮ��֭��","����","�߲�","����","ʨ��","ˮƿ","��з","Ħ��","���","��Ů��"};
	private List<SwingHistorybean> data = new ArrayList<SwingHistorybean>();
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.history_swingcard_activity);
		HistoryMessge_list = (PullToRefreshListView) findViewById(R.id.history_SwingHouseMessge_list);
		data = getData();  
		messgeListAdapter = new HouseHistorySwingAdapter(data, this);
		HistoryMessge_list.setAdapter(messgeListAdapter);
		HistoryMessge_list.setMode(Mode.BOTH);

		init();
		HistoryMessge_list
				.setOnRefreshListener(new OnRefreshListener2<ListView>() {
					@Override
					public void onPullDownToRefresh(
							PullToRefreshBase<ListView> refreshView) {
						// TODO Auto-generated method stub
						SwingHistorybean bean = new SwingHistorybean();
						bean.setHistoryid("1002��");
						bean.setHistoryhousename(MySwingHistoryActivity.this.list[9]);
						bean.setHistoryhousething("����");
						bean.setHistorytime("2015/12/15  13:17");
		               //messgeListAdapter.addFirst(bean);
						new FinishRefresh().execute();
						messgeListAdapter.notifyDataSetChanged();
					}

					@Override
					public void onPullUpToRefresh(
							PullToRefreshBase<ListView> refreshView) {
						// TODO Auto-generated method stub
						SwingHistorybean bean = new SwingHistorybean();
						bean.setHistoryid("1002��");
						bean.setHistoryhousename(MySwingHistoryActivity.this.list[9]);
						bean.setHistoryhousething("����");
						bean.setHistorytime("2015/12/15  13:17");
						//messgeListAdapter.addLast(bean);
						new FinishRefresh().execute();
						messgeListAdapter.notifyDataSetChanged();
					}
				});
	};

	private void init() {
		ILoadingLayout startLabels = HistoryMessge_list.getLoadingLayoutProxy(
				true, false);
		startLabels.setPullLabel("����ˢ��...");// ������ʱ����ʾ����ʾ
		startLabels.setRefreshingLabel("��������...");// ˢ��ʱ
		startLabels.setReleaseLabel("�ſ�ˢ��...");// �����ﵽһ������ʱ����ʾ����ʾ

		ILoadingLayout endLabels = HistoryMessge_list.getLoadingLayoutProxy(
				false, true);
		endLabels.setPullLabel("����ˢ��...");// ������ʱ����ʾ����ʾ
		endLabels.setRefreshingLabel("��������...");// ˢ��ʱ
		endLabels.setReleaseLabel("�ſ�ˢ��...");// �����ﵽһ������ʱ����ʾ����ʾ
	}

	private List<SwingHistorybean> getData() {
		List<SwingHistorybean> list = new ArrayList<SwingHistorybean>();
		for (int i = 0; i < 10; i++) {
			SwingHistorybean bean = new SwingHistorybean();
			bean.setHistoryid("1002"+i+"��");
			bean.setHistoryhousename(MySwingHistoryActivity.this.list[i]);
			if(i%2 == 0){
				bean.setHistoryhousething("�ؼ�");
			}else{
				bean.setHistoryhousething("����");
			}
			bean.setHistorytime("2015/12/15  13:17");
			data.add(bean);
		}

		return list;
	}

	private class FinishRefresh extends AsyncTask<Void, Void, Void> {
		List<SwingHistorybean> list = new ArrayList<SwingHistorybean>();
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
				SwingHistorybean bean = new SwingHistorybean();
				bean.setHistoryid("1002"+i+"��");
				bean.setHistoryhousename(MySwingHistoryActivity.this.list[i]);
				if(i%2 == 0){
					bean.setHistoryhousething("�ؼ�");
				}else{
					bean.setHistoryhousething("����");
				}
				bean.setHistorytime("2015/12/15  13:17");
				data.add(bean);
			}
 
			messgeListAdapter.notifyDataSetChanged();
			HistoryMessge_list.onRefreshComplete();
			Toast.makeText(MySwingHistoryActivity.this,"ִ�н���", Toast.LENGTH_SHORT).show();
		}
		
		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
	}
}

