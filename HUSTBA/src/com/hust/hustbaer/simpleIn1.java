package com.hust.hustbaer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hust.hustba.R;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class simpleIn1 extends ListFragment {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onActivityCreated(android.os.Bundle)
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map map = new HashMap();
		map.put("pic", R.drawable.ic_launcher);
		list.add(map);
		SimpleAdapter sa = new SimpleAdapter(getActivity(), list, R.layout.simple_adapter_item, new String[]{"pic"}, new int[]{R.id.pic});
		setListAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_activated_1, new String[] {
						"着装", "基本裁判设备", "场上跑位", "基本号码手势", "基本犯规手势", "基本违例手势" }));
		showDetails(0);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.simple_in_1, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.ListFragment#onListItemClick(android.widget.ListView,
	 * android.view.View, int, long)
	 */
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		showDetails(position);
	}

	// 当列表项被点击，调用showDetails()方法更新右侧的fragment
	private void showDetails(int index){
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		Details1Fragment details=Details1Fragment.newInstance(index);
		ft.replace(R.id.details, details);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.commit();
		
	}
}
