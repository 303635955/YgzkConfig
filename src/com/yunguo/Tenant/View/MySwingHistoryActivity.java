package com.yunguo.Tenant.View;

import java.util.ArrayList;
import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.yunguo.InfoBean.OpenDoorbean;
import com.yunguo.InfoBean.SwingHistorybean;
import com.yunguo.Tenant.R;
import com.yunguo.TenantAdapter.HouseHistorySwingAdapter;
import com.yunguo.TenantModel.QueryRecordImpl;

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

public class MySwingHistoryActivity extends Activity {
	private PullToRefreshListView HistoryMessge_list;	//�б�ؼ�
	private HouseHistorySwingAdapter messgeListAdapter;	//�б�������
	private String masg;	//��ʾ��Ϣ
	private List<SwingHistorybean> CreditCardRecordlist = new ArrayList<SwingHistorybean>(); //���ż�¼
	private boolean fla;	//��¼�������� 
	private ImageView gifimg;	//�ȴ�ͼƬ
	private AnimationDrawable animaition;	//����
	private LinearLayout loadlinear;	
	private TextView showtext;	//�ȴ���ʾ
	private QueryRecordImpl queryRecordImpl = new QueryRecordImpl();	//�������ݽӿ�
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history_swingcard_activity);
		
		findView();
		setAdapter();
		setOnClick();
		getCreditCardRecord("",handler);
		}
	
	
	private void findView(){
		HistoryMessge_list = (PullToRefreshListView) findViewById(R.id.history_SwingHouseMessge_list);
		loadlinear = (LinearLayout) findViewById(R.id.loadlinear);
		gifimg = (ImageView) findViewById(R.id.gifimg);
		showtext = (TextView) findViewById(R.id.showtext);
	}
	
	private void setAdapter(){
		messgeListAdapter = new HouseHistorySwingAdapter(CreditCardRecordlist, this);
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
				fla = true;
				getCreditCardRecord("",handler);
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				fla = false;
				getCreditCardRecord("",handler);
			}
		});
		
	}
	
	private Handler handler = new Handler(){
		@SuppressWarnings("unchecked")
		@Override
		public void handleMessage(Message msg) {
			animaition.stop();
			switch (msg.what) {
			case 0:
				List<SwingHistorybean> list = new ArrayList<SwingHistorybean>();
				list = (List<SwingHistorybean>) msg.obj;
				HistoryMessge_list.setVisibility(View.VISIBLE);
				if(fla){
					CreditCardRecordlist.clear();
				}
				CreditCardRecordlist.addAll(list);
				messgeListAdapter.notifyDataSetChanged();
				masg = "��ѯ�ɹ���";
				loadlinear.setVisibility(View.GONE);
				break;
			case 1:
				masg = "Ŀǰû��ˢ����¼Ŷ��";
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
		}
	};
	
	
	private void getCreditCardRecord(String param,final Handler handler){
		animaition.start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				//queryRecordImpl.GetSlotCardRecord("",handler);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				List<SwingHistorybean> list = new ArrayList<SwingHistorybean>();
				for (int i = 0; i < 10; i++) {
					SwingHistorybean swingHistorybean = new SwingHistorybean();
					swingHistorybean.setHouseName("���������"+i);
					swingHistorybean.setUserName("����");
					swingHistorybean.setCreditCardDoorId("1263����");
					swingHistorybean.setCreditCardTime("2016/6/27  12:30");
					list.add(swingHistorybean);
				}
				Message message = new Message();
				message.obj = list;
				message.what = 0 ;
				handler.sendMessage(message);
			}
		}).start();
	}
	
	
}

