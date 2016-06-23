package com.yunguo.Tenant.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yunguo.Tenant.R;
import com.yunguo.TenantAdapter.DoorMessageAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DoorMessageActivity extends Activity{
	
	private DoorMessageAdapter doorMessageAdapter;
	private PullToRefreshListView DoorMessge_list;
	private ImageButton door_back;
	private ImageView gifimg;
	private AnimationDrawable animaition;
	private LinearLayout loadlinear;
	private LinearLayout listlinear;
	private TextView showtext;
	private String masg;
	private Boolean fls = true;
	private List<Map<String,String>> doorMessageAdapterList = new ArrayList<Map<String,String>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doormessage_activity);
		
		findView();
		setAdapter();
		setOnClick();
		getHouseMessage("",handler);
	}
		
	public void findView(){
		loadlinear = (LinearLayout) findViewById(R.id.loadlinear);
		gifimg = (ImageView) findViewById(R.id.gifimg);
		showtext = (TextView) findViewById(R.id.showtext);
		DoorMessge_list = (PullToRefreshListView) findViewById(R.id.DoorMessge_list);
		listlinear = (LinearLayout) findViewById(R.id.listlinear);
		door_back = (ImageButton) findViewById(R.id.door_back);
	}
	
	public void setAdapter(){
		doorMessageAdapter = new DoorMessageAdapter(doorMessageAdapterList, getApplicationContext());
		DoorMessge_list.setAdapter(doorMessageAdapter);
		DoorMessge_list.setMode(Mode.BOTH);
		gifimg.setBackgroundResource(R.anim.gifload);
		animaition = (AnimationDrawable) gifimg.getBackground();
		animaition.setOneShot(false);
	}
	
	public void setOnClick(){
		DoorMessge_list.setOnRefreshListener(new OnRefreshListener2<ListView>(){

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// ������ʱ���������� 
				//��ȡ����
				getHouseMessage("",handler);
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				  // ������ʱ�����ѡ��  
				//��ȡ����
				getHouseMessage("",handler);
			}
        });
		
		/**
		 * ����
		 */
		door_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	/**
	 * ˢ�½���
	 */
	Handler handler = new Handler(){
		@SuppressLint("HandlerLeak")
		@Override
		public void handleMessage(android.os.Message msg) {
			animaition.stop();
			switch (msg.what) {
			case 0:
				 @SuppressWarnings("unchecked")
				 List<Map<String,String>> list = (List<Map<String, String>>) msg.obj;
				 loadlinear.setVisibility(View.GONE);
				 listlinear.setVisibility(View.VISIBLE);
				 if(fls){
					 doorMessageAdapterList.clear();
				 }
				 doorMessageAdapterList.addAll(list); 
				 doorMessageAdapter.notifyDataSetChanged();
				 masg = "��ѯ�ɹ���";
				 loadlinear.setVisibility(View.GONE);
				 Toast.makeText(getApplicationContext(), "��ѯ�ɹ���", Toast.LENGTH_SHORT).show();
				break;
			case 1:
				 masg = "����û�з��ݣ�";
				 showtext.setText(masg);
				 gifimg.setBackgroundResource(R.drawable.pic_emotion03_1);
				break;
			case 2:
				 masg = "��ѯʧ�ܣ��������磡";
				 gifimg.setBackgroundResource(R.drawable.pic_emotion03_1);
				 showtext.setText(masg);
				break;
			}
			/**
			 * ֹͣˢ��
			 */
			DoorMessge_list.onRefreshComplete();
			/**
			 * �رյȴ���
			 */
			/*if(progressDialog != null){
				progressDialog.dismiss();
			}*/
			//��ʾ��Ϣ
			Toast.makeText(getApplicationContext(), masg, Toast.LENGTH_SHORT).show();
		};
	};
	
	
	/**
	 * ��ѯ������Ϣ
	 */
	
	public void getHouseMessage(final String pramr,final Handler handler){
		animaition.start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				List<Map<String,String>> list = new ArrayList<Map<String,String>>();
				for (int i = 0; i < 10; i++) {
					Map<String,String> map = new HashMap<String, String>();
					map.put("DoorId", "��ţ�2467001"+i);
					map.put("DoorStatus", "��ǰ״̬���ѹر�");
					//map.put("DoorId", "2467001"+i);
					list.add(map);
				}
				Message message = new Message();
				message.obj = list;
				message.what = 0 ;
				handler.sendMessage(message);
				//houseMessageImpl.refreshHouseMessage(pramr, handler);
			}
		}).start();
	}
}
