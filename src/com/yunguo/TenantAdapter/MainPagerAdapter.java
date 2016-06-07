package com.yunguo.TenantAdapter;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MainPagerAdapter extends FragmentStatePagerAdapter{
	
	private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>(); 

	public MainPagerAdapter(FragmentManager fm,ArrayList fragmentList) {
		super(fm);
		this.fragmentList = fragmentList;
	}
	
	 @Override  
     public Fragment getItem(int position) {  
         return fragmentList.get(position);  
     }  

     @Override  
     public int getCount() {  
         return fragmentList.size();  
     }
     
}
