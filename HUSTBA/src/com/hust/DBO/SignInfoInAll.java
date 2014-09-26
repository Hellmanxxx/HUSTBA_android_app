package com.hust.DBO;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hust.entity.SignInfo;

public class SignInfoInAll {
	private Context context;
	private static SignInfoInAll sii;
	public static SignInfoInAll getInstance(Context context){
		if(sii!=null)
			return sii;
		else
			return new SignInfoInAll(context);
	}
	private SignInfoInAll(Context context){
		this.context = context;
	}
	public  ArrayList<SignInfo> getSignInfoInall(){
		ArrayList<SignInfo> signInfoInall = new ArrayList<SignInfo>();
		DBOpenHelper dboh = new DBOpenHelper(context, "sharing.db", null, 2);
		SQLiteDatabase sqlite = dboh.getReadableDatabase();
		Cursor c = sqlite.rawQuery("select * from sign_info", null);
		while(c.moveToNext()){
			SignInfo si = new SignInfo();
			si.setId(c.getInt(0));
			si.setName(c.getString(1));
			si.setTotalDays(c.getInt(2));
			si.setSignDays(c.getInt(3));
			si.setLateDays(c.getInt(4));
			signInfoInall.add(si);
		}
		return signInfoInall;
	}
}
