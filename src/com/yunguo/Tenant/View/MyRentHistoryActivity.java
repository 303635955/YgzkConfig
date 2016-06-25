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
	private PullToRefreshListView HistoryMessge_list; 	//列表组件
	private HouseHistoryAdapter messgeListAdapter;	//适配器
	private List<HouseRentingBean> Rentlist = new ArrayList<HouseRentingBean>();	//租房记录信息
	private String masg = "";// 返回信息
	private Boolean fls = true;	//记录上拉下拉
	private QueryRecordImpl queryRecordImpl = new QueryRecordImpl(); //请求数据接口
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
		
		getUserRentingRecord("",handler);	//获取数据
	};
	
	/**
	 * 查找控件
	 */
	private void findView(){
		HistoryMessge_list = (PullToRefreshListView) findViewById(R.id.history_RentHouseMessge_list);
		loadlinear = (LinearLayout) findViewById(R.id.loadlinear);
		gifimg = (ImageView) findViewById(R.id.gifimg);
		showtext = (TextView) findViewById(R.id.showtext);
	}
	
	/**
	 * 适配数据初始化
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
	 * 事件监听
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
	 * 界面刷新
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
	/**
	 * 异步请求
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
					houseRentingBean.setHouseName("保利新天地"+i);
					houseRentingBean.setOwnerName("张三");
					houseRentingBean.setHouseAdress("成都市郫县百草路保利新天地11栋625室");
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
