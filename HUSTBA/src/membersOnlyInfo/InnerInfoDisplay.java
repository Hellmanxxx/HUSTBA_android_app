package membersOnlyInfo;

import com.hust.fragment.NewInfo;
import com.hust.fragment.NormalInfo;
import com.hust.fragment.OBRLearning;
import com.hust.fragment.OldInfo;
import com.hust.fragment.RMLearning;
import com.hust.fragment.RefereeInBasicGames;
import com.hust.fragment.RefereeInGigGames;
import com.hust.hustba.R;
import com.hust.hustbaer.TabListener;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.Tab;
import android.os.Bundle;

public class InnerInfoDisplay extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharing);
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(false);
		Tab tab = actionBar.newTab().setText("最新信息")
				.setTabListener(new TabListener(this, "newInfo",
						NewInfo.class));
		actionBar.addTab(tab);

		tab = actionBar.newTab().setText("历史信息")
				.setTabListener(new TabListener(this, "oldInfo",
						OldInfo.class));
		actionBar.addTab(tab);

		tab = actionBar.newTab().setText("常驻信息")
				.setTabListener(new TabListener(this, "normalInfo",
						NormalInfo.class));
		actionBar.addTab(tab);
	}
}
