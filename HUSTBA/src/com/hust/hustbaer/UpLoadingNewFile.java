package com.hust.hustbaer;

import com.hust.hustba.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UpLoadingNewFile extends Activity {
	Button findFile,confirm;
	TextView filePath;
	static final int NEED_FILE_NAME = 112;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.up_loading_new_file);
		findView();
		findFile.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(UpLoadingNewFile.this,FindingFile.class);
				UpLoadingNewFile.this.startActivityForResult(intent, NEED_FILE_NAME);
			}
		});
		confirm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			 Intent intent = new Intent();
			 intent.putExtra("filePath", filePath.getText().toString());
			 intent.setClass(UpLoadingNewFile.this, WritingNewFile.class);
			 UpLoadingNewFile.this.startActivity(intent);
			 UpLoadingNewFile.this.finish();
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode==NEED_FILE_NAME&&resultCode==1){
			String path = data.getStringExtra("path");
			filePath.setText(path);
		}
	}
	//findview的封装方法
	private void findView(){
		findFile=(Button) findViewById(R.id.finding_file);
		confirm = (Button) findViewById(R.id.confirm);
		filePath = (TextView) findViewById(R.id.fileLocation);
		
	}
}
