package com.yunguo.Tenant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LogingActivity extends Activity{
	
	private Button loginBut;	//µÇÂ¼ÇëÇó
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		findView();
		setOnlick();
	}
	
	private void findView(){
		loginBut = (Button) findViewById(R.id.loginBut);
	}
	
	private void setOnlick(){
		loginBut.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LogingActivity.this,MainActivity.class); 
				startActivity(intent);
			}
		});
	}
	
	
}
