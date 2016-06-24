package com.yunguo.TenantAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yunguo.Tenant.R;
import com.yunguo.TenantAdapter.HouseMessageAdapter.ViewHolder;
import com.yunguo.TenantModel.OnChangedListenerImpl;
import com.yunguo.TenantModel.OpenDoorImpl;
import com.yunguo.TenantUtil.MyDialogUtils;
import com.yunguo.TenantUtil.SlipButtonView;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class DoorMessageAdapter extends BaseAdapter {

	private List<Map<String,String>> list = new ArrayList<Map<String,String>>();
    private Context context;
    private Map<String,String> map;
    private  ViewHolder viewHolder = null;
    private Handler handler;
    private OpenDoorImpl openDoorImpl;

    public DoorMessageAdapter(List<Map<String,String>> list,Context context,Handler handler){
        this.list = list;
        this.context = context;
        this.handler = handler;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.doormessage_adapter, null);
            viewHolder =new ViewHolder();

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        
        
        map = list.get(position);
        
        viewHolder.doorId = (TextView) convertView.findViewById(R.id.doorId);
        viewHolder.doorStatus = (TextView) convertView.findViewById(R.id.doorStatus);
        viewHolder.doorStatusbut = (Button) convertView.findViewById(R.id.doorStatusbut);
        
        viewHolder.doorId.setText(map.get("DoorId"));
        String DoorStatusStr = map.get("DoorStatus");
        viewHolder.doorStatus.setText(DoorStatusStr);
        
        if(DoorStatusStr.equals("当前状态：关门状态")){
        	viewHolder.doorStatusbut.setEnabled(true);
        }else{
        	viewHolder.doorStatusbut.setEnabled(false);
        	viewHolder.doorStatusbut.setBackgroundColor(Color.parseColor("#E5E5E5"));
        }
        
        viewHolder.doorStatusbut.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new Thread(thread).start();
			}
		});
        
        return convertView;
    }
    

    class ViewHolder{
        TextView doorId,doorStatus;
        Button doorStatusbut;
    }
    private Thread thread = new Thread(){
    	@Override
    	public void run() {
    		//开门
    		Log.i("DoorStatus", "开门");
    		handler.sendEmptyMessage(3);
    		
    		/*//开门关门请求
    		openDoorImpl = new OpenDoorImpl();
    		
    		openDoorImpl.openDoorPost(map.get("DoorId"), handler);*/
    		try {
    			Thread.sleep(3000);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    		handler.sendEmptyMessage(4);
    	};
    };

}
