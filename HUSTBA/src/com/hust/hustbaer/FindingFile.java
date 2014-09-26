package com.hust.hustbaer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.hust.hustba.R;

public class FindingFile extends Activity {
	ListView fileList;
	Button confirm, back;
	TextView path;
	File currentParent;
	File[] currentFiles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.finding_file);
		findView();
		setListeners();
		File root = new File("/mnt/sdcard/");
		if (root.exists()) {
			currentParent = root;
			currentFiles = root.listFiles();// 初始化，第一次调用的值
			inflateListView(currentFiles);
		}

	}

	// findview封装方法
	private void findView() {
		fileList = (ListView) findViewById(R.id.fileList);
		confirm = (Button) findViewById(R.id.confirm);
		back = (Button) findViewById(R.id.back);
		path = (TextView) findViewById(R.id.path);
	}

	// 封装监听器设置代码
	private void setListeners() {
		fileList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				path.setText(currentFiles[position].getAbsolutePath());//把点击的项目地址显示出来，不管是否符合标准
				if (currentFiles[position].isFile()){
					return;
				}
				File[] temp = currentFiles[position].listFiles();
				if (temp == null || temp.length == 0) {
					Toast.makeText(FindingFile.this, "当前路径不可访问或该路径下没有文件",
							Toast.LENGTH_SHORT).show();
				} else {
					// 判断当前点击项有子文件可以显示时，更新currentParent/
					// currentFiles数据，从而更新ListView数据，显示新的路径下的文件列表
					currentParent = currentFiles[position];
					currentFiles = temp;
					inflateListView(currentFiles);
				}
			}
		});
		// 上一层按钮功能实现
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!currentParent.getAbsolutePath().equals("/mnt/sdcard")) {
					currentParent = currentParent.getParentFile();
					currentFiles = currentParent.listFiles();
					inflateListView(currentFiles);
				}
			}
		});
		// 确定按钮(confirm)功能实现
		confirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				File file = new File(path.getText().toString());
				if (file.isFile()) {
					if (path.getText().toString().contains(".txt")) {
						Intent intent = new Intent();
						intent.putExtra("path", path.getText().toString());
						FindingFile.this.setResult(1, intent);
						FindingFile.this.finish();
					} else {
						Toast.makeText(FindingFile.this, "需要一个txt类型的文件！",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(FindingFile.this, "当前路径不是一个文件，必须选择一个文件",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	// 根据用户选择实时加载对于得数据到ListView中显示
	private void inflateListView(File[] files) {
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < files.length; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>();
			if (files[i].isDirectory()) {
				listItem.put("icon", R.drawable.path_icon);
			} else {
				listItem.put("icon", R.drawable.file_icon1);
			}
			listItem.put("fileName", files[i].getName());
			listItems.add(listItem);
		}
		// 创建一个adapter
		SimpleAdapter sa = new SimpleAdapter(this, listItems,
				R.layout.show_files, new String[] { "icon", "fileName" },
				new int[] { R.id.icon, R.id.fileName });
		fileList.setAdapter(sa);
		path.setText(currentParent.getAbsolutePath());
	}
}
