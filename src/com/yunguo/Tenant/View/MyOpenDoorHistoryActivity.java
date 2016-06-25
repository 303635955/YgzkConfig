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
import com.yunguo.InfoBean.HouseRentingBean;
import com.yunguo.InfoBean.OpenDoorbean;
import com.yunguo.Tenant.R;
import com.yunguo.TenantAdapter.HouseHistoryAdapter;
import com.yunguo.TenantAdapter.HouseHistoryOpenAdapter;
import com.yunguo.TenantAdapter.HouseMessageAdapter;
import com.yunguo.TenantModel.HouseMessageImpl;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class MyOpenDoorHistoryActivity extends Activity {
	private PullToRefreshListView HistoryMessge_list;	//listview�����б�
	private HouseHistoryOpenAdapter messgeListAdapter;	//����������
	private List<OpenDoorbean> openDoorList = new ArrayList<OpenDoorbean>();	//���ż�¼��Ϣ
	HouseMessageImpl houseMessageImpl = new HouseMessageImpl();	//�������ݽӿ�
	String masg = "";// ������Ϣ
	private Boolean fls = true;	//��¼��������
	private ImageView gifimg;
	private AnimationDrawable animaition;
	private LinearLayout loadlinear;
	private TextView showtext;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history_open_activity);
		
		findView();
		setAdapter();
		setOnClick();
		getOpenDoorRecord("",handler);

	};
	
	private void findView(){
		HistoryMessge_list = (PullToRefreshListView) findViewById(R.id.history_OpenHouseMessge_list);
		
		loadlinear = (LinearLayout) findViewById(R.id.loadlinear);
		gifimg = (ImageView) findViewById(R.id.gifimg);
		showtext = (TextView) findViewById(R.id.showtext);
	}
	
	/**
	 * ���ݳ�ʼ��
	 */
	private void setAdapter(){
		messgeListAdapter = new HouseHistoryOpenAdapter(openDoorList, this);
		HistoryMessge_list.setAdapter(messgeListAdapter);
		HistoryMessge_list.setMode(Mode.BOTH);
		HistoryMessge_list.setVisibility(View.GONE);
		
		gifimg.setBackgroundResource(R.anim.gifload);
		animaition = (AnimationDrawable) gifimg.getBackground();
		animaition.setOneShot(false);
	}
	
	private void setOnClick(){
		HistoryMessge_list.setOnRefreshListener(new OnRefreshListener2<ListView>() {
			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				fls = true;
				getOpenDoorRecord("",handler);
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				fls = true;
				getOpenDoorRecord("",handler);
			}
		});
	}
	
	private Handler handler = new Handler(){
		@SuppressWarnings("unchecked")
		@Override
		public void handleMessage(android.os.Message msg) {
			animaition.stop();
			switch (msg.what) {
			case 0:
				List<OpenDoorbean> list = new ArrayList<OpenDoorbean>();
				list = (List<OpenDoorbean>) msg.obj;
				HistoryMessge_list.setVisibility(View.VISIBLE);
				if(fls){
					openDoorList.clear();
				}
				openDoorList.addAll(list);
				messgeListAdapter.notifyDataSetChanged();
				masg = "��ѯ�ɹ���";
				loadlinear.setVisibility(View.GONE);
				break;
			case 1:
				masg = "Ŀǰû���ⷿ��¼Ŷ��";
				showtext.setText(masg);
				gifimg.setBackgroundResource(R.drawable.pic_emotion03_1);
				break;
			case 2:
				masg = "��ѯʧ�ܣ��������磡";
				gifimg.setBackgroundResource(R.drawable.pic_emotion03_1);
				showtext.setText(masg);
				break;
			}
			HistoryMessge_list.onRefreshComplete();//ֹͣˢ��
			Toast.makeText(getApplicationContext(), masg, Toast.LENGTH_SHORT).show(); //��ʾ��Ϣ
		};
	};
	
	private void getOpenDoorRecord(String param,final Handler handler){
		animaition.start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				//queryRecordImpl.GetHOpenDoorRecord("",handler);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				List<OpenDoorbean> list = new ArrayList<OpenDoorbean>();
				for (int i = 0; i < 10; i++) {
					OpenDoorbean openDoorbean = new OpenDoorbean();
					openDoorbean.setHouseName("���������"+i);
					openDoorbean.setDoorId("1����");
					openDoorbean.setUserName("�ɶ���ۯ�ذٲ�·���������11��625��");
					openDoorbean.setOpenDoorTime("2016/6/26");
					list.add(openDoorbean);
				}
				Message message = new Message();
				message.obj = list;
				message.what = 0 ;
				handler.sendMessage(message);
			}
		}).start();
	}


}
