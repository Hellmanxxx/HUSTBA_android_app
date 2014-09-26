package com.hust.DBO;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hust.entity.SharingFile;

public class SharingFileFactory {
	private Context context;
	private static SharingFileFactory sff;
	public static SharingFileFactory getInstance(Context context){
		if(sff!=null)
			return sff;
		else
			return new SharingFileFactory(context);
	}
	private SharingFileFactory(Context context){
		this.context = context;
	}
	public  ArrayList<SharingFile> getSharingFile(){
		ArrayList<SharingFile> fileList = new ArrayList<SharingFile>();
		DBOpenHelper dboh = new DBOpenHelper(context, "sharing.db", null, 2);
		SQLiteDatabase sqlite = dboh.getReadableDatabase();
		Cursor c = sqlite.rawQuery("select * from sharing_file", null);
		while(c.moveToNext()){
			SharingFile sf = new SharingFile();
			sf.setTitle(c.getString(1));
			sf.setType(c.getString(2));
			sf.setName(c.getString(3));
			sf.setKey_word(c.getString(4));
			sf.setContent(c.getString(5));
			sf.setDate(c.getString(6));
			fileList.add(sf);
		}
		return fileList;
	}
}
