package com.hust.hustbaer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hust.hustba.R;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

public class Details1Fragment extends ListFragment {

	public static Details1Fragment newInstance(int index) {
		Details1Fragment f = new Details1Fragment();
		Bundle args = new Bundle();
		args.putInt("index", index);
		f.setArguments(args);
		return f;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onCreate(android.os.Bundle)
	 */
	
	public int getShownIndex() {
		if (getArguments() != null) {

			return this.getArguments().getInt("index", 0);
		} else {
			return 0;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.simple_in_1, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onActivityCreated(android.os.Bundle)
	 */

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < getShownIndex()+1; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", "一节");
			map.put("pic", R.drawable.hustbaer);
			map.put("content", "从存储文件中读取细节文件");
			data.add(map);
		}

		setListAdapter(new SimpleAdapter(getActivity(), data,
				R.layout.data_item, new String[] { "title", "pic", "content" },
				new int[] { R.id.title, R.id.pic, R.id.content }));
	}
}
