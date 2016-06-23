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
import com.yunguo.Tenant.View.DoorMessageActivity;
import com.yunguo.TenantAdapter.HouseMessageAdapter;
import com.yunguo.TenantModel.HouseMessageImpl;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnWindowAttachListener;
import android.view.ViewTreeObserver.OnWindowFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class HouseMessageFragment extends Fragment{
	
	/**
	 * ������
	 */
	private View view;
	
	/**
	 * listview
	 */
	private PullToRefreshListView HouseMessge_list;
	/**
	 * �����б�������
	 */
	private HouseMessageAdapter messgeListAdapter;
	private ArrayList<Map<String,String>> houseMessageList = new ArrayList<Map<String,String>>(); 
	List<Map<String,String>> listtmp = new ArrayList<Map<String,String>>();//��ʱ����
	
	/**
	 * ��������
	 */
	HouseMessageImpl houseMessageImpl = new HouseMessageImpl();
	
	/**
	 * �ȴ�����
	 */
	private ProgressDialog progressDialog;
	
	/**
	 * ��¼��������
	 */
	private Boolean fls = true;
	
	private String masg = "";	//��ʾ��Ϣ
	private ImageView gifimg;
	private AnimationDrawable animaition;
	private LinearLayout loadlinear;
	private TextView showtext;
	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.housemesage_fragment,null);
		
		/**
		 * ���ҿؼ�
		 */
		findView();
		
		/**
		 * ��ʼ��������
		 */
		setAdapter();
		
		/**
		 * ��Ӽ���
		 */
		setOnClik();
		
		getHouseMessage("",handler);
		
		
		return view;
	}
	
	public void findView(){
		loadlinear = (LinearLayout) view.findViewById(R.id.loadlinear);
		gifimg = (ImageView) view.findViewById(R.id.gifimg);
		showtext = (TextView) view.findViewById(R.id.showtext);
		HouseMessge_list = (PullToRefreshListView) view.findViewById(R.id.HouseMessge_list);
		
	}
	
	public void setAdapter(){
		messgeListAdapter = new HouseMessageAdapter(houseMessageList, getActivity());
		HouseMessge_list.setAdapter(messgeListAdapter);
		HouseMessge_list.setVisibility(View.GONE);
		
		gifimg.setBackgroundResource(R.anim.gifload);
		animaition = (AnimationDrawable) gifimg.getBackground();
		animaition.setOneShot(false);
		
		HouseMessge_list.setMode(Mode.BOTH);
	}
	
	
	public void setOnClik(){
		/**
		 * ����ˢ�¼���
		 */
		HouseMessge_list.setOnRefreshListener(new OnRefreshListener2<ListView>(){

			@Override
			public void onPullDownToRefresh(
				PullToRefreshBase<ListView> refreshView) {
				// ������ʱ���������� 
				//��ȡ����
				fls = true;
				getHouseMessage("",handler);
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				  // ������ʱ�����ѡ��  
				//��ȡ����
				fls = false;
				getHouseMessage("",handler);
			}
        }); 
		
		
		HouseMessge_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(getActivity(),DoorMessageActivity.class);
				@SuppressWarnings("unchecked")
				Map<String,String> map = (Map<String, String>) messgeListAdapter.getItem(arg2-1);
				intent.putExtra("HouseId", map.get("HouseId"));
				startActivity(intent);
				
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
				 HouseMessge_list.setVisibility(View.VISIBLE);
				 if(fls){
					 houseMessageList.clear();
				 }
				 houseMessageList.addAll(list); 
				 messgeListAdapter.notifyDataSetChanged();
				 masg = "��ѯ�ɹ���";
				 loadlinear.setVisibility(View.GONE);
				 Toast.makeText(getContext(), "��ѯ�ɹ���", Toast.LENGTH_SHORT).show();
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
			HouseMessge_list.onRefreshComplete();
			/**
			 * �رյȴ���
			 */
			/*if(progressDialog != null){
				progressDialog.dismiss();
			}*/
			//��ʾ��Ϣ
			Toast.makeText(getContext(), masg, Toast.LENGTH_SHORT).show();
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
					Map<String,String> map = new HashMap<String,String>();
					map.put("HouseName", "���ݣ����������"+i);
					map.put("OwnerName", "����������"+i);
					map.put("HouseAddress", "��ַ���ɶ���ۯ�ذٲ�·���������11��625��"+i);
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
	
	
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //animaition.start();
    }
	
	
}
