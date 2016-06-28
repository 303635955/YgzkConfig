package com.yunguo.TenantAdapter;

import java.util.ArrayList;
import java.util.List;

import com.yunguo.InfoBean.SwingHistorybean;
import com.yunguo.Tenant.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class HouseHistorySwingAdapter extends BaseAdapter{
	
	private List<SwingHistorybean>  list = new ArrayList<SwingHistorybean>();
    private Context context;
    private  ViewHolder viewHolder = null;
    private LayoutInflater mInflater;  
    
    public HouseHistorySwingAdapter(List<SwingHistorybean> data , Context context){
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
            convertView =  mInflater.inflate(R.layout.history_swingcard_adpter, null);
            viewHolder =new ViewHolder();
            viewHolder.historyhouseid = (TextView) convertView.findViewById(R.id.house_swinghistory_houseid);
            viewHolder.historyhousename = (TextView) convertView.findViewById(R.id.house_swinghistory_housename);
            viewHolder.historything = (TextView) convertView.findViewById(R.id.house_swinghistory_housething);
            viewHolder.historytime = (TextView) convertView.findViewById(R.id.house_swinghistory_housetime);
            
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        
        viewHolder.historyhouseid.setText(list.get(position).getCreditCardDoorId());
        viewHolder.historyhousename.setText(list.get(position).getHouseName());
        viewHolder.historything.setText(list.get(position).getUserName());
        viewHolder.historytime.setText(list.get(position).getCreditCardTime());
       
        return convertView;
    }

    class ViewHolder{
        TextView historyhouseid,historyhousename,historything,historytime;
    }
}
