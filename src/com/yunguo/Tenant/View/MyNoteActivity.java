package com.yunguo.Tenant.View;

import java.util.ArrayList;
import java.util.List;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.yunguo.InfoBean.HouseRentingBean;
import com.yunguo.InfoBean.UserLogbean;
import com.yunguo.Tenant.R;
import com.yunguo.TenantAdapter.HouseHistoryNoteAdapter;
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
public class MyNoteActivity extends Activity {
	private PullToRefreshListView HistoryMessge_list;	//�б�ؼ�
	private HouseHistoryNoteAdapter messgeListAdapter;	//�б�������
	private List<UserLogbean> UserLogList = new ArrayList<UserLogbean>();	//�б�����
	private QueryRecordImpl queryRecordImpl = new QueryRecordImpl();		//�������ݽӿ�
	private String masg = "";// ������Ϣ
	private Boolean fls = true;		//��¼��������
	private ImageView gifimg;
	private AnimationDrawable animaition;
	private LinearLayout loadlinear;
	private TextView showtext;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history_note_activity);
		findView();
		setAdapter();
		setOnClick();
		getUserLog("",handler);
	}
	
	private void findView(){
		HistoryMessge_list = (PullToRefreshListView) findViewById(R.id.history_NoteHouseMessge_list);
		loadlinear = (LinearLayout) findViewById(R.id.loadlinear);
		gifimg = (ImageView) findViewById(R.id.gifimg);
		showtext = (TextView) findViewById(R.id.showtext);
	}
	
	private void setAdapter(){
		messgeListAdapter = new HouseHistoryNoteAdapter(UserLogList, this);
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
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				fls = true;
				getUserLog("",handler);
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				fls = false;
				getUserLog("",handler);
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
				List<UserLogbean> list = new ArrayList<UserLogbean>();
				list = (List<UserLogbean>) msg.obj;
				HistoryMessge_list.setVisibility(View.VISIBLE);
				if(fls){
					UserLogList.clear();
				}
				UserLogList.addAll(list);
				messgeListAdapter.notifyDataSetChanged();
				masg = "��ѯ�ɹ���";
				loadlinear.setVisibility(View.GONE);
				break;
			case 1:
				masg = "Ŀǰû�е�¼��־��";
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
	
	private void getUserLog(String param,final Handler handler){
		animaition.start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				//queryRecordImpl.GetLoginRecord("",handler);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				List<UserLogbean> list = new ArrayList<UserLogbean>();
				for (int i = 0; i < 10; i++) {
					UserLogbean userLogbean = new UserLogbean();
					userLogbean.setOperationType("��������:��¼����");
					userLogbean.setOperationAddress("��½�ص�:�Ĵ��ɶ�");
					userLogbean.setOperationTime("��¼ʱ��:2016/6/27 15:33");
					list.add(userLogbean);
				}
				Message message = new Message();
				message.obj = list;
				message.what = 0 ;
				handler.sendMessage(message);
			}
		}).start();
	}


}
