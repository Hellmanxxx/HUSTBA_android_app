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

public class OBRLearning extends ListFragment {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		SharingFileFactory sff = SharingFileFactory.getInstance(getActivity());
		ArrayList<SharingFile> fileList = sff.getSharingFile();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < fileList.size(); i++) {
			if (fileList.get(i).getType().equals("规则学习心得")) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("title", fileList.get(i).getTitle());
				map.put("name", fileList.get(i).getName());
				map.put("key_word", fileList.get(i).getKey_word());
				//在listview中部分显示内容
				String content = fileList.get(i).getContent();
				if (content.length() > 20) {
					content = content.substring(0, 19) + "...";
				}
				map.put("content", content);
				map.put("date", fileList.get(i).getDate());
				list.add(map);
			}
		}
		SimpleAdapter sa = new SimpleAdapter(getActivity(), list,
				R.layout.obrlearning_list_item, new String[] { "title", "name",
						"key_word", "content", "date" }, new int[] {
						R.id.title, R.id.name, R.id.key_word, R.id.content,
						R.id.date });
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
