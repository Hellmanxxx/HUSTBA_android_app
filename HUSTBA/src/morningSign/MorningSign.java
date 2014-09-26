package morningSign;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import com.hust.DBO.DBOpenHelper;
import com.hust.DBO.SignInfoInAll;
import com.hust.entity.SignInfo;
import com.hust.fragment.OBRLearning;
import com.hust.fragment.RMLearning;
import com.hust.fragment.RefereeInBasicGames;
import com.hust.fragment.RefereeInGigGames;
import com.hust.hustba.R;
import com.hust.hustbaer.TabListener;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MorningSign extends FragmentActivity {
	FragmentTabHost tabHost;
	TextView tv;
	SharedPreferences prefer;
	float date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.morning_sign);

		tv = (TextView) findViewById(R.id.current_time);

		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(false);
		Tab tab = actionBar
				.newTab()
				.setText("考核规则")
				.setTabListener(
						new TabListener(this, "rules", MorningSignRules.class));
		actionBar.addTab(tab);

		tab = actionBar
				.newTab()
				.setText("我的出操信息")
				.setTabListener(
						new TabListener(this, "mySignInfo", MySignInfo.class));
		actionBar.addTab(tab);

		tab = actionBar
				.newTab()
				.setText("出操总排名")
				.setTabListener(
						new TabListener(this, "AllSignInfo",
								AllSignInfo.class));
		actionBar.addTab(tab);

		tab = actionBar
				.newTab()
				.setText("香蕉和棒子")
				.setTabListener(
						new TabListener(this, "goodAndBad", GoodAndBad.class));
		actionBar.addTab(tab);
		// 在页面显示当前时间
		showCurrentTime();

		Button sign_in = (Button) findViewById(R.id.sign_in);
		sign_in.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String[] str = tv.getText().toString().split(":");
				float time = Float.parseFloat(str[0] + "." + str[1]);
				signIn("陈锡鄂", time);
			}
		});

	}

	// 执行签到操作
	private void signIn(String name, float signTime) {
		// 通过工具类 获取所有签到信息
		SignInfoInAll sii = SignInfoInAll.getInstance(this);
		ArrayList<SignInfo> si = sii.getSignInfoInall();
		int targetInfo = -1;
		for (int i = 0; i < si.size(); i++) {
			if (si.get(i).getName().equals(name)) {
				targetInfo = i;
			}
		}

		if (targetInfo != -1) {
			DBOpenHelper dboh = new DBOpenHelper(this, "sharing.db", null, 2);
			SQLiteDatabase sqlite = dboh.getWritableDatabase();

			String sql = "update sign_info set total_days=?,sign_days=?,late_days=? where name=?";
			// 判断今日是否已经签到
			if (signedOrNot()) {
				Toast.makeText(this, "今日已签到！请勿重复！", 500).show();
			} else {
				// 以下代码判断是否在规定时间内签到
				if (signTime > 7.25) {
					Toast.makeText(this, "已过签到时间！", Toast.LENGTH_SHORT).show();
				} else if (signTime <= 6.40) {
					sqlite.execSQL(sql,
							new Object[] {
									si.get(targetInfo).getTotalDays() + 1,
									si.get(targetInfo).getSignDays() + 1,
									si.get(targetInfo).getLateDays(), name });
				} else {
					sqlite.execSQL(
							sql,
							new Object[] {
									si.get(targetInfo).getTotalDays() + 1,
									si.get(targetInfo).getSignDays() + 1,
									si.get(targetInfo).getLateDays() + 1, name });
				}
				// 存入签到信息，防止重复签到
				Editor editor = prefer.edit();
				editor.putFloat("date", date);
				editor.putBoolean("checked", true);
				editor.commit();
			}

		} else {
			Toast.makeText(this, "无法查询该用户出勤信息！", Toast.LENGTH_SHORT).show();
		}
	}

	// 一日只能够签到一次功能实现
	private boolean signedOrNot() {
		prefer = getSharedPreferences("signState", Context.MODE_PRIVATE);
		float signDate = prefer.getFloat("date", 0);
		Calendar c = Calendar.getInstance();
		date = Float.parseFloat(c.get(Calendar.MONTH) + "."
				+ c.get(Calendar.DAY_OF_MONTH));
		boolean checked = prefer.getBoolean("checked", false);
		if (signDate == date && checked == true) {//如果系统文件存储的签到日期和当前签到的日期一样而且签到状态已为true,那么就return true表示签到完成。
			return true;
		} else {								  //否则就是当日尚未签到，返回false表示未签到状态	
			return false;
		}
	}
	//通过子线程循环提供时间，在UI线程中实时更新时间
	private void showCurrentTime() {

		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				String minute1="";
				if (msg.what == 112) {
					Calendar c = Calendar.getInstance();
					int hour = c.get(Calendar.HOUR_OF_DAY);
					int minute = c.get(Calendar.MINUTE);
					if(minute>=0&&minute<10){
						minute1= ""+0+minute;
					}else{
						minute1 = ""+minute;
					}
						
					tv.setText(hour + ":" + minute1);
				}
			}
		};
		new Timer().schedule(new TimerTask() {//新建子线程做时间的循环更新

			@Override
			public void run() {
				handler.sendEmptyMessage(112);
			}
		}, 0, 20000);

	}
}
