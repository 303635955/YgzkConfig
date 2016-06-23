package com.yunguo.TenantFragment;


import com.yunguo.Tenant.R;
import com.yunguo.Tenant.R.layout;
import com.yunguo.Tenant.View.MyNoteActivity;
import com.yunguo.Tenant.View.MyOpenDoorHistoryActivity;
import com.yunguo.Tenant.View.MyRentHistoryActivity;
import com.yunguo.Tenant.View.MySwingHistoryActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.LinearLayout;
import android.widget.Toast;

public class RecordFragment extends Fragment implements OnClickListener{
	private LinearLayout rent;
	private LinearLayout swingcard;
	private LinearLayout opendoor;
	private LinearLayout note;
	
	private View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.record_fragment,null);
		init();
		return view;
	}
	
	public void init(){
		rent = (LinearLayout) view.findViewById(R.id.myrenthistory);
		swingcard = (LinearLayout) view.findViewById(R.id.myswinghistory);
		opendoor = (LinearLayout) view.findViewById(R.id.myopendoorhistory);
		note = (LinearLayout) view.findViewById(R.id.mynotehistory);
		
		rent.setOnClickListener(this);
		swingcard.setOnClickListener(this);
		opendoor.setOnClickListener(this);
		note.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.myrenthistory:
				Toast.makeText(getActivity(),"frist", Toast.LENGTH_SHORT).show();
				Intent intentrent = new Intent(getActivity(),
						MyRentHistoryActivity.class);
				startActivity(intentrent);
				break;
			case R.id.myswinghistory:
				Toast.makeText(getActivity(),"secoend", Toast.LENGTH_SHORT).show();
				Intent intentswing = new Intent(getActivity(),
						MySwingHistoryActivity.class);
				startActivity(intentswing);
				break;
			case R.id.myopendoorhistory:
				Toast.makeText(getActivity(),"thrid", Toast.LENGTH_SHORT).show();
				Intent intentopen = new Intent(getActivity(),
						MyOpenDoorHistoryActivity.class);
				startActivity(intentopen);
				break;
			case R.id.mynotehistory:
				Toast.makeText(getActivity(),"fourth", Toast.LENGTH_SHORT).show();
				Intent intentnote = new Intent(getActivity(),
						MyNoteActivity.class);
				startActivity(intentnote);
				break;
		}
	}
}
