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
			currentFiles = root.listFiles();// ��ʼ������һ�ε��õ�ֵ
			inflateListView(currentFiles);
		}

	}

	// findview��װ����
	private void findView() {
		fileList = (ListView) findViewById(R.id.fileList);
		confirm = (Button) findViewById(R.id.confirm);
		back = (Button) findViewById(R.id.back);
		path = (TextView) findViewById(R.id.path);
	}

	// ��װ���������ô���
	private void setListeners() {
		fileList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				path.setText(currentFiles[position].getAbsolutePath());//�ѵ������Ŀ��ַ��ʾ�����������Ƿ���ϱ�׼
				if (currentFiles[position].isFile()){
					return;
				}
				File[] temp = currentFiles[position].listFiles();
				if (temp == null || temp.length == 0) {
					Toast.makeText(FindingFile.this, "��ǰ·�����ɷ��ʻ��·����û���ļ�",
							Toast.LENGTH_SHORT).show();
				} else {
					// �жϵ�ǰ����������ļ�������ʾʱ������currentParent/
					// currentFiles���ݣ��Ӷ�����ListView���ݣ���ʾ�µ�·���µ��ļ��б�
					currentParent = currentFiles[position];
					currentFiles = temp;
					inflateListView(currentFiles);
				}
			}
		});
		// ��һ�㰴ť����ʵ��
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
		// ȷ����ť(confirm)����ʵ��
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
						Toast.makeText(FindingFile.this, "��Ҫһ��txt���͵��ļ���",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(FindingFile.this, "��ǰ·������һ���ļ�������ѡ��һ���ļ�",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	// �����û�ѡ��ʵʱ���ض��ڵ����ݵ�ListView����ʾ
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
		// ����һ��adapter
		SimpleAdapter sa = new SimpleAdapter(this, listItems,
				R.layout.show_files, new String[] { "icon", "fileName" },
				new int[] { R.id.icon, R.id.fileName });
		fileList.setAdapter(sa);
		path.setText(currentParent.getAbsolutePath());
	}
}
