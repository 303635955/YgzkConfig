package com.yunguo.TenantFragment;


import com.yunguo.Tenant.R;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UserMessageFragment extends Fragment{
	
	private View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.usermesage_fragment,null);
		return view;
	}
}
