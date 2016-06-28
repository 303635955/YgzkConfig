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
	private PullToRefreshListView HistoryMessge_list;	//列表控件
	private HouseHistorySwingAdapter messgeListAdapter;	//列表适配器
	private String masg;	//提示信息
	private List<SwingHistorybean> CreditCardRecordlist = new ArrayList<SwingHistorybean>(); //开门记录
	private boolean fla;	//记录上拉下拉 
	private ImageView gifimg;	//等待图片
	private AnimationDrawable animaition;	//动画
	private LinearLayout loadlinear;	
	private TextView showtext;	//等待提示
	private QueryRecordImpl queryRecordImpl = new QueryRecordImpl();	//请求数据接口
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
				masg = "查询成功！";
				loadlinear.setVisibility(View.GONE);
				break;
			case 1:
				masg = "目前没有刷卡记录哦！";
				showtext.setText(masg);
				gifimg.setBackgroundResource(R.drawable.pic_emotion03_1);
				break;
			case 2:
				masg = "查询失败，请检查网络！";
				gifimg.setBackgroundResource(R.drawable.pic_emotion03_1);
				showtext.setText(masg);
				break;
			}
			HistoryMessge_list.onRefreshComplete();//停止刷新
			Toast.makeText(getApplicationContext(), masg, Toast.LENGTH_SHORT).show(); //提示信息
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
					swingHistorybean.setHouseName("保利新天地"+i);
					swingHistorybean.setUserName("李四");
					swingHistorybean.setCreditCardDoorId("1263号门");
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

