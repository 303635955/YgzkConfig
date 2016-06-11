package com.yunguo.TenantAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yunguo.Tenant.R;
import com.yunguo.TenantAdapter.HouseMessageAdapter.ViewHolder;
import com.yunguo.TenantUtil.SlipButtonView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DoorMessageAdapter extends BaseAdapter{

	private List<Map<String,String>> list = new ArrayList<Map<String,String>>();
    private Context context;
    private  ViewHolder viewHolder = null;

    public DoorMessageAdapter(List<Map<String,String>> list,Context context){
        this.list = list;
        this.context = context;
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
        
        
        Map<String,String> map = list.get(position);
        
        viewHolder.doorId = (TextView) convertView.findViewById(R.id.doorId);
        viewHolder.doorStatus = (TextView) convertView.findViewById(R.id.doorStatus);
        viewHolder.doorStatusbut = (SlipButtonView) convertView.findViewById(R.id.doorStatusbut);
        
        viewHolder.doorId.setText(map.get("DoorId"));
        String DoorStatusStr = map.get("DoorStatus");
        viewHolder.doorStatus.setText(DoorStatusStr);
        
        if(DoorStatusStr.equals("ÒÑ¹Ø±Õ")){
        	
        	viewHolder.doorStatusbut.setChecked(false);
        }
        
        return convertView;
    }

    class ViewHolder{
        TextView doorId,doorStatus;
        SlipButtonView doorStatusbut;
    }

}
