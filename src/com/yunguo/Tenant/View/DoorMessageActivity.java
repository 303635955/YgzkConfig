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
import com.yunguo.TenantUtil.MyDialogUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
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
	
	private MyDialogUtils dialog;
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
		dialog = new MyDialogUtils(this);
		dialog.setCancelable(false); 
		/*dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);  
		dialog.setCanceledOnTouchOutside(false);  */
		dialog.setTitle("请求中……");
		
		loadlinear = (LinearLayout) findViewById(R.id.loadlinear);
		gifimg = (ImageView) findViewById(R.id.gifimg);
		showtext = (TextView) findViewById(R.id.showtext);
		DoorMessge_list = (PullToRefreshListView) findViewById(R.id.DoorMessge_list);
		listlinear = (LinearLayout) findViewById(R.id.listlinear);
		door_back = (ImageButton) findViewById(R.id.door_back);
	}
	
	public void setAdapter(){
		doorMessageAdapter = new DoorMessageAdapter(doorMessageAdapterList, DoorMessageActivity.this,handler);
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
				// 下拉的时候数据重置 
				//获取数据
				getHouseMessage("",handler);
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				  // 上拉的时候添加选项  
				//获取数据
				getHouseMessage("",handler);
			}
        });
		
		/**
		 * 返回
		 */
		door_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	/**
	 * 刷新界面
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
				 masg = "查询成功！";
				 loadlinear.setVisibility(View.GONE);
				 Toast.makeText(getApplicationContext(), "查询成功！", Toast.LENGTH_SHORT).show();
				break;
			case 1:
				 masg = "您还没有房屋！";
				 showtext.setText(masg);
				 gifimg.setBackgroundResource(R.drawable.pic_emotion03_1);
				break;
			case 2:
				 masg = "查询失败，请检查网络！";
				 gifimg.setBackgroundResource(R.drawable.pic_emotion03_1);
				 showtext.setText(masg);
				break;
			case 3:
				dialog.show();
				break;
			case 4:
				dialog.dismiss();
				break;
			case 5:
				masg = "开门成功";
				break;
			case 6:
				masg = "开门失败";
				break;
			case 7:
				masg = "开门失败，请检查网络！";
				break;
			}
			/**
			 * 停止刷新
			 */
			DoorMessge_list.onRefreshComplete();
			/**
			 * 关闭等待窗
			 */
			/*if(progressDialog != null){
				progressDialog.dismiss();
			}*/
			//提示消息
			Toast.makeText(getApplicationContext(), masg, Toast.LENGTH_SHORT).show();
		};
	};
	
	
	/**
	 * 查询房屋信息
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
					map.put("DoorId", "编号：2467001"+i);
					map.put("DoorStatus", "当前状态：关门状态");
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
