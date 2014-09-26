package com.hust.hustbaer;

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

public class SimpleLessonTwo extends ListFragment {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onActivityCreated(android.os.Bundle)
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_activated_1, new String[] {
						"犯规", "违例", "赛前", "赛中", "赛后", "记录表" }));
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
