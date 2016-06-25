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
	private PullToRefreshListView HistoryMessge_list;	//listview数据列表
	private HouseHistoryOpenAdapter messgeListAdapter;	//数据适配器
	private List<OpenDoorbean> openDoorList = new ArrayList<OpenDoorbean>();	//开门记录信息
	HouseMessageImpl houseMessageImpl = new HouseMessageImpl();	//请求数据接口
	String masg = "";// 返回信息
	private Boolean fls = true;	//记录上拉下拉
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
	 * 数据初始化
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
				masg = "查询成功！";
				loadlinear.setVisibility(View.GONE);
				break;
			case 1:
				masg = "目前没有租房记录哦！";
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
					openDoorbean.setHouseName("保利新天地"+i);
					openDoorbean.setDoorId("1号门");
					openDoorbean.setUserName("成都市郫县百草路保利新天地11栋625室");
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
