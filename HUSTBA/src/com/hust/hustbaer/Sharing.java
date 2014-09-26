package com.hust.hustbaer;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.hust.fragment.OBRLearning;
import com.hust.fragment.RMLearning;
import com.hust.fragment.RefereeInBasicGames;
import com.hust.fragment.RefereeInGigGames;
import com.hust.hustba.R;

public class Sharing extends Activity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.hustbaer_sharing, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.writing:
			Intent intent = new Intent();
			intent.setClass(Sharing.this, WritingNewFile.class);
			Sharing.this.startActivity(intent);
			return true;
		case R.id.upLoad:
			Intent intent1 = new Intent();
			intent1.setClass(Sharing.this, UpLoadingNewFile.class);
			Sharing.this.startActivity(intent1);
			return true;

		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharing);
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(false);
		Tab tab = actionBar.newTab().setText("规则学习心得")
				.setTabListener(new TabListener(this, "OBRlearning",
						OBRLearning.class));
		actionBar.addTab(tab);

		tab = actionBar.newTab().setText("临场执法心得")
				.setTabListener(new TabListener(this, "RMLearning",
						RMLearning.class));
		actionBar.addTab(tab);

		tab = actionBar.newTab().setText("班塞总结专区")
				.setTabListener(new TabListener(this, "RefereeInBasicGames",
						RefereeInBasicGames.class));
		actionBar.addTab(tab);

		tab = actionBar.newTab().setText("联赛总结专区")
				.setTabListener(new TabListener(this, "RefereeInGigGames",
						RefereeInGigGames.class));
		actionBar.addTab(tab);
	}
}
