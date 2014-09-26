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
				.setText("���˹���")
				.setTabListener(
						new TabListener(this, "rules", MorningSignRules.class));
		actionBar.addTab(tab);

		tab = actionBar
				.newTab()
				.setText("�ҵĳ�����Ϣ")
				.setTabListener(
						new TabListener(this, "mySignInfo", MySignInfo.class));
		actionBar.addTab(tab);

		tab = actionBar
				.newTab()
				.setText("����������")
				.setTabListener(
						new TabListener(this, "AllSignInfo",
								AllSignInfo.class));
		actionBar.addTab(tab);

		tab = actionBar
				.newTab()
				.setText("�㽶�Ͱ���")
				.setTabListener(
						new TabListener(this, "goodAndBad", GoodAndBad.class));
		actionBar.addTab(tab);
		// ��ҳ����ʾ��ǰʱ��
		showCurrentTime();

		Button sign_in = (Button) findViewById(R.id.sign_in);
		sign_in.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String[] str = tv.getText().toString().split(":");
				float time = Float.parseFloat(str[0] + "." + str[1]);
				signIn("������", time);
			}
		});

	}

	// ִ��ǩ������
	private void signIn(String name, float signTime) {
		// ͨ�������� ��ȡ����ǩ����Ϣ
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
			// �жϽ����Ƿ��Ѿ�ǩ��
			if (signedOrNot()) {
				Toast.makeText(this, "������ǩ���������ظ���", 500).show();
			} else {
				// ���´����ж��Ƿ��ڹ涨ʱ����ǩ��
				if (signTime > 7.25) {
					Toast.makeText(this, "�ѹ�ǩ��ʱ�䣡", Toast.LENGTH_SHORT).show();
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
				// ����ǩ����Ϣ����ֹ�ظ�ǩ��
				Editor editor = prefer.edit();
				editor.putFloat("date", date);
				editor.putBoolean("checked", true);
				editor.commit();
			}

		} else {
			Toast.makeText(this, "�޷���ѯ���û�������Ϣ��", Toast.LENGTH_SHORT).show();
		}
	}

	// һ��ֻ�ܹ�ǩ��һ�ι���ʵ��
	private boolean signedOrNot() {
		prefer = getSharedPreferences("signState", Context.MODE_PRIVATE);
		float signDate = prefer.getFloat("date", 0);
		Calendar c = Calendar.getInstance();
		date = Float.parseFloat(c.get(Calendar.MONTH) + "."
				+ c.get(Calendar.DAY_OF_MONTH));
		boolean checked = prefer.getBoolean("checked", false);
		if (signDate == date && checked == true) {//���ϵͳ�ļ��洢��ǩ�����ں͵�ǰǩ��������һ������ǩ��״̬��Ϊtrue,��ô��return true��ʾǩ����ɡ�
			return true;
		} else {								  //������ǵ�����δǩ��������false��ʾδǩ��״̬	
			return false;
		}
	}
	//ͨ�����߳�ѭ���ṩʱ�䣬��UI�߳���ʵʱ����ʱ��
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
		new Timer().schedule(new TimerTask() {//�½����߳���ʱ���ѭ������

			@Override
			public void run() {
				handler.sendEmptyMessage(112);
			}
		}, 0, 20000);

	}
}
