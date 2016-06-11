package com.yunguo.TenantAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yunguo.InfoBean.Historybean;
import com.yunguo.Tenant.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class HouseHistoryOpenAdapter extends BaseAdapter{
	
	private List<Historybean>  list = new ArrayList<Historybean>();
    private Context context;
    private  ViewHolder viewHolder = null;
    private LayoutInflater mInflater;  
    
    public HouseHistoryOpenAdapter(List<Historybean> data , Context context){
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
            convertView =  mInflater.inflate(R.layout.history_rent_adpter, null);
            viewHolder =new ViewHolder();
            viewHolder.historyhousename = (TextView) convertView.findViewById(R.id.house_history_name);
            viewHolder.historyownername = (TextView) convertView.findViewById(R.id.house_history_ownername);
            viewHolder.historyhouseaddress = (TextView) convertView.findViewById(R.id.house_history_houseaddress);
            viewHolder.historytime= (TextView) convertView.findViewById(R.id.house_history_housetime);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        
        viewHolder.historyhousename.setText(list.get(position).getHistoryname());
        viewHolder.historyownername.setText(list.get(position).getHistoryownername());
        viewHolder.historyhouseaddress.setText(list.get(position).getHistoryadress());
        viewHolder.historytime.setText(list.get(position).getHistorytime());
       
        return convertView;
    }

    class ViewHolder{
        TextView historyhousename,historyownername,historyhouseaddress,historytime;
    }
}
