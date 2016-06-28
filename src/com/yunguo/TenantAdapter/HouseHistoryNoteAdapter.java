package com.yunguo.TenantAdapter;

import java.util.ArrayList;
import java.util.List;

import com.yunguo.InfoBean.UserLogbean;
import com.yunguo.Tenant.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class HouseHistoryNoteAdapter extends BaseAdapter{
	
	private List<UserLogbean>  list = new ArrayList<UserLogbean>();
    private Context context;
    private  ViewHolder viewHolder = null;
    private LayoutInflater mInflater;  
    
    public HouseHistoryNoteAdapter(List<UserLogbean> data , Context context){
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
            convertView =  mInflater.inflate(R.layout.history_note_adpter, null);
            viewHolder =new ViewHolder();
            viewHolder.historyhousething = (TextView) convertView.findViewById(R.id.house_notehistory_name);
            viewHolder.historyownername = (TextView) convertView.findViewById(R.id.house_notehistory_ownername);
            viewHolder.historytime = (TextView) convertView.findViewById(R.id.house_notehistory_time);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        
        viewHolder.historyhousething.setText(list.get(position).getOperationType());
        viewHolder.historyownername.setText(list.get(position).getOperationAddress());
        viewHolder.historytime.setText(list.get(position).getOperationTime());
       
        return convertView;
    }

    class ViewHolder{
        TextView historyhousething,historyownername,historytime;
    }
}
