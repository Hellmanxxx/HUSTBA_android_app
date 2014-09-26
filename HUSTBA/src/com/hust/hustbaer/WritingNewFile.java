package com.hust.hustbaer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Calendar;

import com.hust.DBO.DBOpenHelper;
import com.hust.fragment.ChooseToContinueDialog;
import com.hust.hustba.R;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class WritingNewFile extends Activity {
	DBOpenHelper dboh;
	EditText title, name, key_word, content;
	Button commit;
	Spinner sharing_type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.writing_new_file);
		findView();
		Intent intent = getIntent();
		String path = intent.getStringExtra("filePath");
		if (path != null) {

			readFileFromSDcard(path);
		}
		commit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(sharing_type.getSelectedItem().toString()!=null){
					writeDataToDatabase();
					new ChooseToContinueDialog().show(getFragmentManager(), "continueOrNot");
				}else{
					Toast.makeText(WritingNewFile.this, "请选则类型", 500).show();
				}
			}
		});
	}

	// 封装findview方法
	private void findView() {
		content = (EditText) findViewById(R.id.sharing_file_content);
		commit = (Button) findViewById(R.id.commit_sharing_file);
		title = (EditText) findViewById(R.id.sharing_file_title);
		name = (EditText) findViewById(R.id.writer_name);
		key_word = (EditText) findViewById(R.id.key_word);
		sharing_type = (Spinner) findViewById(R.id.type);
	}

	// 通过文件地址把指定文件内容显示在textview中
	private void readFileFromSDcard(String path) {
		FileInputStream fis;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
			String line="";
			StringBuilder sb = new StringBuilder("");
			while ((line = in.readLine())!=null) {
				sb.append(line);
				sb.append("\n");
			}
			in.close();
			content.setText(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 用户点击提交按钮后，把输入的内容写入数据库
	private void writeDataToDatabase() {
		SQLiteDatabase sqlite = new DBOpenHelper(this, "sharing.db", null, 2)
				.getWritableDatabase();
		Calendar c = Calendar.getInstance();
		String date = c.getTime().toString();
		String sql = "insert into sharing_file values(null,?,?,?,?,?,?)";
		sqlite.execSQL(sql, new String[] { title.getText().toString(),sharing_type.getSelectedItem().toString(),
				name.getText().toString(), key_word.getText().toString(),
				content.getText().toString(), date });
	}
}
