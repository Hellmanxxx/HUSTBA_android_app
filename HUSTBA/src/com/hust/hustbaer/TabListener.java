package com.hust.hustbaer;

import com.hust.hustba.R;

import android.app.ActionBar.Tab;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

public class TabListener implements ActionBar.TabListener {
	private Fragment mFragment;
	private final Activity mActivity;
	private final String mTag;
	private final Class mClz;
	
	public TabListener(Activity activity,String tag,Class clz){
		mActivity = activity;
		mTag = tag;
		mClz = clz;
		
	}
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		if(mFragment==null){
			mFragment=Fragment.instantiate(mActivity, mClz.getName());
			ft.add(R.id.fragment_content, mFragment, mTag);
		}else{
			ft.attach(mFragment);
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		if(mFragment!=null){
			ft.detach(mFragment);
		}
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

}
