package com.yunguo.TenantFragment;


import com.yunguo.Tenant.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HouseMessageFragment extends Fragment{
	
	/**
	 * иообнд
	 */
	private View view;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.housemesage_fragment,null);
		return view;
	}
}
