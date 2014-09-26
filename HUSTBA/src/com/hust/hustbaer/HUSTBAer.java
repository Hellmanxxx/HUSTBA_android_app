package com.hust.hustbaer;


import membersOnlyInfo.InnerInfoDisplay;
import morningSign.MorningSign;

import com.hust.hustba.HomePage;
import com.hust.hustba.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HUSTBAer extends Activity {
	ImageButton lessons,sharing,info_inside,sign_in;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hustbaer);
		findView();
		setOnClickListener();
	}
	
	//封装findView方法
		private void findView(){
			lessons = (ImageButton) findViewById(R.id.lessons);
			sharing=(ImageButton) findViewById(R.id.sharing);
			info_inside=(ImageButton) findViewById(R.id.info_inside);
			sign_in=(ImageButton) findViewById(R.id.sign_in);
		}
		//封装setOnClickListener方法
		private void setOnClickListener(){
			lessons.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(HUSTBAer.this, RefLessons.class);
					HUSTBAer.this.startActivity(intent);					
				}
			});
			sharing.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(HUSTBAer.this, Sharing.class);
					HUSTBAer.this.startActivity(intent);
				}
			});
			info_inside.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(HUSTBAer.this, InnerInfoDisplay.class);
					HUSTBAer.this.startActivity(intent);
				}
			});
			sign_in.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(HUSTBAer.this, MorningSign.class);
					HUSTBAer.this.startActivity(intent);
				}
			});
		}
}
