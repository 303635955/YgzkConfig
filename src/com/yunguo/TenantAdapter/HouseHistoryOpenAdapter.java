package com.yunguo.TenantAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yunguo.InfoBean.OpenDoorbean;
import com.yunguo.Tenant.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class HouseHistoryOpenAdapter extends BaseAdapter{
	
	private List<OpenDoorbean>  list = new ArrayList<OpenDoorbean>();
    private Context context;
    private  ViewHolder viewHolder = null;
    private LayoutInflater mInflater;  
    
    public HouseHistoryOpenAdapter(List<OpenDoorbean> data , Context context){
        this.list = data;
        this.context = context;
        mInflater = LayoutInflater.from(context); 
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
            convertView =  mInflater.inflate(R.layout.history_open_adpter, null);
            viewHolder =new ViewHolder();
            viewHolder.historyhouseid = (TextView) convertView.findViewById(R.id.house_openhistory_id);
            viewHolder.historyownername = (TextView) convertView.findViewById(R.id.house_openhistory_name);
            viewHolder.historytime = (TextView) convertView.findViewById(R.id.house_openhistory_housetime);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        
        viewHolder.historyhouseid.setText(list.get(position).getDoorId());
        viewHolder.historyownername.setText(list.get(position).getHouseName());
        viewHolder.historytime.setText(list.get(position).getOpenDoorTime());
       
        return convertView;
    }

    class ViewHolder{
        TextView historyhouseid,historyownername,historytime;
    }
}
