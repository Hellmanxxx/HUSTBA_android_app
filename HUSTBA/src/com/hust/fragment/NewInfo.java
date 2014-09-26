package com.hust.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hust.DBO.SharingFileFactory;
import com.hust.entity.SharingFile;
import com.hust.hustba.R;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class NewInfo extends ListFragment {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 5; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("title", "新生杯");
				map.put("date", "2014.10.13");
				map.put("content","热烈庆祝新生杯篮球赛正式开幕！" );
				list.add(map);
		}
		
		SimpleAdapter sa = new SimpleAdapter(getActivity(), list,
				R.layout.info_item, new String[] { "title", "date","content" }, new int[] {
						R.id.title,R.id.date , R.id.content
						});
		setListAdapter(sa);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.simple_in_1, null);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
	}
}
