package com.hust.hustbaer;

import com.hust.hustba.R;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.Activity;
import android.os.Bundle;

public class RefLessons extends Activity {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ref_lessons);
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(false);
		Tab tab = actionBar
				.newTab()
				.setText("裁判法入门")
				.setTabListener(
						new TabListener(this, "simpleLessonOne",
								simpleIn1.class));
		actionBar.addTab(tab);

		tab = actionBar
				.newTab()
				.setText("规则入门")
				.setTabListener(
						new TabListener(this, "simpleLessonTwo",
								SimpleLessonTwo.class));
		actionBar.addTab(tab);

		tab = actionBar
				.newTab()
				.setText("临场入门")
				.setTabListener(
						new TabListener(this, "simpleLessonThree",
								SimpleLessonThree.class));
		actionBar.addTab(tab);

		tab = actionBar
				.newTab()
				.setText("问题答疑")
				.setTabListener(
						new TabListener(this, "ProblemSolve",
								ProblemSolve.class));
		actionBar.addTab(tab);

	}

}
