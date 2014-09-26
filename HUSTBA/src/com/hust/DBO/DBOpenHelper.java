package com.hust.DBO;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	public DBOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	public DBOpenHelper(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE IF NOT EXISTS sharing_file(_id integer primary key autoincrement,title text(20),type text(20),name text(20),key_word text(20),content text,date text)";
		String sql1 = "CREATE TABLE IF NOT EXISTS sign_info(_id integer primary key autoincrement,name text(20),total_days integer,sign_days integer,late_days integer)";
		
		String sql2 = "insert into sign_info values(null,'³ÂÎý¶õ',0,0,0)";
		String sql3 = "insert into sign_info values(null,'Öì½­',0,0,0)";
		String sql4 = "insert into sign_info values(null,'¹ù±þÒ«',0,0,0)";
		String sql5 = "insert into sign_info values(null,'¹ùÑôÃô',0,0,0)";
		String sql6 = "insert into sign_info values(null,'ÏòÑô',0,0,0)";
		
		
		db.execSQL(sql);
		db.execSQL(sql1);
		db.execSQL(sql2);
		db.execSQL(sql3);
		db.execSQL(sql4);
		db.execSQL(sql5);
		db.execSQL(sql6);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
