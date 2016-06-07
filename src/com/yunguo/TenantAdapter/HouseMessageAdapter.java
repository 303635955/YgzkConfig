package com.yunguo.TenantAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yunguo.Tenant.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class HouseMessageAdapter extends BaseAdapter{
	
	private List<Map<String,String>> list = new ArrayList<Map<String,String>>();
    private Context context;
    private  ViewHolder viewHolder = null;

    public HouseMessageAdapter(List<Map<String,String>> list,Context context){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.housemessage_adapter, null);
            viewHolder =new ViewHolder();

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        
        
        Map<String,String> map = list.get(position);
        
        viewHolder.housename = (TextView) convertView.findViewById(R.id.housename);
        viewHolder.ownername = (TextView) convertView.findViewById(R.id.ownername);
        viewHolder.houseaddress = (TextView) convertView.findViewById(R.id.houseaddress);
        
        viewHolder.housename.setText(map.get("HouseName"));
        viewHolder.ownername.setText(map.get("OwnerName"));
        viewHolder.houseaddress.setText(map.get("HouseAddress"));
        
        return convertView;
    }

    class ViewHolder{
        TextView housename,ownername,houseaddress;
    }
}
