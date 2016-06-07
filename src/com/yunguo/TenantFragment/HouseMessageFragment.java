package com.yunguo.TenantFragment;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.yunguo.Tenant.R;
import com.yunguo.TenantAdapter.HouseMessageAdapter;
import com.yunguo.TenantModel.HouseMessageImpl;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class HouseMessageFragment extends Fragment{
	
	/**
	 * 上下文
	 */
	private View view;
	
	/**
	 * listview
	 */
	private PullToRefreshListView HouseMessge_list;
	
	/**
	 * 房屋列表适配器
	 */
	private HouseMessageAdapter messgeListAdapter;
	private ArrayList<Map<String,String>> houseMessageList = new ArrayList<Map<String,String>>(); 
	List<Map<String,String>> listtmp = new ArrayList<Map<String,String>>();//临时数据
	
	/**
	 * 求情网络
	 */
	HouseMessageImpl houseMessageImpl = new HouseMessageImpl();
	String masg = "";//返回信息
	
	/**
	 * 等待窗口
	 */
	private ProgressDialog progressDialog;
	
	/**
	 * 记录上拉下拉
	 */
	private Boolean fls = true;
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		// TODO Auto-generated method stub
		super.setUserVisibleHint(isVisibleToUser);
		
		 if(isVisibleToUser){
             //只有当该Fragment被用户可见的时候,才加载网络数据
			//显示等待框
				progressDialog = ProgressDialog.show(getActivity(), "请稍等", "正在获取...", true);
			//获取数据
			new Thread(thread).start(); 
     }else{
             //否则不加载网络数据
     }
	}
	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.housemesage_fragment,null);
		
		/**
		 * 查找控件
		 */
		findView();
		
		/**
		 * 初始化适配器
		 */
		setAdapter();
		
		/**
		 * 添加监听
		 */
		setOnClik();
		
		return view;
	}
	
	public void findView(){
		HouseMessge_list = (PullToRefreshListView) view.findViewById(R.id.HouseMessge_list);
		HouseMessge_list.setMode(Mode.BOTH);
	}
	
	public void setAdapter(){
		messgeListAdapter = new HouseMessageAdapter(houseMessageList, getActivity());
		HouseMessge_list.setAdapter(messgeListAdapter);
	}
	
	
	public void setOnClik(){
		/**
		 * 下拉刷新加载
		 */
		HouseMessge_list.setOnRefreshListener(new OnRefreshListener2<ListView>(){

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// 下拉的时候数据重置 
				//获取数据
				new Thread(thread).start(); 
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				  // 上拉的时候添加选项  
				//获取数据
				new Thread(thread).start(); 
			}
        }); 
	}
	
	
	/**
	 * 更新数据
	 */
	Handler handler = new Handler(){
		@SuppressLint("HandlerLeak")
		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				 houseMessageList.clear();
				 houseMessageList.addAll(listtmp); 
				 messgeListAdapter.notifyDataSetChanged();
				 HouseMessge_list.setAdapter(messgeListAdapter);
				break;
			case 1:
				break;
			case 2:
				break;
			}
			
			/**
			 * 停止刷新
			 */
			HouseMessge_list.onRefreshComplete();
			/**
			 * 关闭等待窗
			 */
			if(progressDialog != null){
				progressDialog.dismiss();
			}
			
			Toast.makeText(getContext(), masg, Toast.LENGTH_SHORT).show();
		};
	};
	
	
	/**
	 * 查询房屋信息
	 */
	Thread thread = new Thread(){
		@Override
		public void run() {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			listtmp.clear();
			for (int i = 0; i < 10; i++) {
				Map<String,String> map = new HashMap<String, String>();
		          map.put("HouseId", "");
		          map.put("OwnerName", "小强");
		          map.put("HouseName", "保利国际花园"+i);
		          map.put("HouseAddress", "成都市百草路保利国际11栋605");
		          listtmp.add(map);
			}
			masg = "获取成功!";
			handler.sendEmptyMessage(0);
			/*String ret = houseMessageImpl.refreshHouseMessage("id", handler);
			
			if(ret != null){
				try {
					JSONObject jsonObject2 =new JSONObject(ret);
					String tmp = jsonObject2.getString("ret");
					masg = jsonObject2.getString("message");
					*//**
					 * 清除缓存
					 *//*
					listtmp.clear();
					if(!tmp.equals("0")){
							JSONArray jsonArray = jsonObject2.getJSONArray("houses"); 
						 for (int i=0;i<jsonArray.length();i++){
							  Map<String,String> map = new HashMap<String, String>();
					          JSONObject jsonObjectSon= (JSONObject)jsonArray.opt(i);
					          map.put("HouseId", jsonObjectSon.getString("HouseId"));
					          map.put("OwnerName", jsonObjectSon.getString("OwnerName"));
					          map.put("HouseName", jsonObjectSon.getString("HouseName"));
					          map.put("HouseAddress", jsonObjectSon.getString("HouseAddress"));
					          listtmp.add(map);
					          }
						 handler.sendEmptyMessage(0);
					}else{
						 handler.sendEmptyMessage(1);
						 masg = "请求失败，求情稍后重试!";
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}else{
				masg = "请求网络失败，请检查网络是否可用！";
				handler.sendEmptyMessage(2);
			}*/
		};
	};
	
	
}
