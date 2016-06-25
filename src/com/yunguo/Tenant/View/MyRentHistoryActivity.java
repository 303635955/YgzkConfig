package com.yunguo.Tenant.View;

import java.util.ArrayList;
import java.util.List;





import java.util.Map;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.yunguo.InfoBean.HouseRentingBean;
import com.yunguo.Tenant.R;
import com.yunguo.TenantAdapter.HouseHistoryAdapter;
import com.yunguo.TenantModel.QueryRecordImpl;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class MyRentHistoryActivity extends Activity {
	private PullToRefreshListView HistoryMessge_list; 	//�б����
	private HouseHistoryAdapter messgeListAdapter;	//������
	private List<HouseRentingBean> Rentlist = new ArrayList<HouseRentingBean>();	//�ⷿ��¼��Ϣ
	private String masg = "";// ������Ϣ
	private Boolean fls = true;	//��¼��������
	private QueryRecordImpl queryRecordImpl = new QueryRecordImpl(); //�������ݽӿ�
	private ImageView gifimg;
	private AnimationDrawable animaition;
	private LinearLayout loadlinear;
	private TextView showtext;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history_rent_activity);
		findView();
		setAdapter();
		setOnClick();
		
		getUserRentingRecord("",handler);	//��ȡ����
	};
	
	/**
	 * ���ҿؼ�
	 */
	private void findView(){
		HistoryMessge_list = (PullToRefreshListView) findViewById(R.id.history_RentHouseMessge_list);
		loadlinear = (LinearLayout) findViewById(R.id.loadlinear);
		gifimg = (ImageView) findViewById(R.id.gifimg);
		showtext = (TextView) findViewById(R.id.showtext);
	}
	
	/**
	 * �������ݳ�ʼ��
	 */
	private void setAdapter(){
		queryRecordImpl = new QueryRecordImpl();
		messgeListAdapter = new HouseHistoryAdapter(Rentlist, this);
		HistoryMessge_list.setAdapter(messgeListAdapter);
		HistoryMessge_list.setMode(Mode.BOTH);
		HistoryMessge_list.setVisibility(View.GONE);
		
		gifimg.setBackgroundResource(R.anim.gifload);
		animaition = (AnimationDrawable) gifimg.getBackground();
		animaition.setOneShot(false);
	}
	
	/**
	 * �¼�����
	 */
	private void setOnClick(){
		HistoryMessge_list.setOnRefreshListener(new OnRefreshListener2<ListView>() {
			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				fls = true;
				getUserRentingRecord("",handler);
			}
			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				fls = false;
				getUserRentingRecord("",handler);
			}
		});
	}

	/**
	 * ����ˢ��
	 */
	private Handler handler = new Handler(){
		@SuppressWarnings("unchecked")
		@Override
		public void handleMessage(android.os.Message msg) {
			animaition.stop();
			switch (msg.what) {
			case 0:
				@SuppressWarnings("rawtypes")
				List<HouseRentingBean> list = new ArrayList();
				list = (List<HouseRentingBean>) msg.obj;
				HistoryMessge_list.setVisibility(View.VISIBLE);
				if(fls){
					Rentlist.clear();
				}
				Rentlist.addAll(list);
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
	/**
	 * �첽����
	 */
	private void getUserRentingRecord(String param,final Handler handler){
		animaition.start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				//queryRecordImpl.GetHouseRecord("",handler);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				List<HouseRentingBean> list = new ArrayList<HouseRentingBean>();
				for (int i = 0; i < 10; i++) {
					HouseRentingBean houseRentingBean = new HouseRentingBean();
					houseRentingBean.setHouseName("���������"+i);
					houseRentingBean.setOwnerName("����");
					houseRentingBean.setHouseAdress("�ɶ���ۯ�ذٲ�·���������11��625��");
					houseRentingBean.setRentingTime("2016/6/26");
					list.add(houseRentingBean);
				}
				Message message = new Message();
				message.obj = list;
				message.what = 0 ;
				handler.sendMessage(message);
			}
		}).start();
	}

}
