package morningSign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hust.DBO.SharingFileFactory;
import com.hust.DBO.SignInfoInAll;
import com.hust.entity.SharingFile;
import com.hust.entity.SignInfo;
import com.hust.hustba.R;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MySignInfo extends Fragment {
	TextView totalDays, signDays, lateDays, absentDays, signRate, goodSignRate,
			level;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mySignInfo();

	}

	// 倒入出勤情况列表
	private void mySignInfo() {
		SignInfoInAll sii = SignInfoInAll.getInstance(getActivity());
		ArrayList<SignInfo> si = sii.getSignInfoInall();
		String name = si.get(0).getName();
		int total = si.get(0).getTotalDays();
		int sign = si.get(0).getSignDays();
		int late = si.get(0).getLateDays();
		int absent = total - sign;
		int signRate1;
			if (total == 0) {
				signRate1 = 0;
			} else {
				signRate1 = (int) ((float)sign / total * 100);
			}
		int goodSignRate1;
			if (total == 0) {
				goodSignRate1 = 0;
			} else {
				goodSignRate1 = (int) ((sign - late) / (float)total * 100);
			}
		String level1;
			if (total == 0) {
				level1 = "暂无";
			} else if (signRate1 >= 80) {
				level1 = "积极者";
			} else if (signRate1 >= 60 && signRate1 < 80) {
				level1 = "中庸者";
			} else if (signRate1 > 50 && signRate1 < 60) {
				level1 = "待拯救者";
			} else {
				level1 = "无药可救";
			}

		totalDays = (TextView) getView().findViewById(R.id.total_day);
		totalDays.setText(total + "");
		signDays = (TextView) getView().findViewById(R.id.sign_day);
		signDays.setText(sign + "");
		lateDays = (TextView) getView().findViewById(R.id.late_day);
		lateDays.setText(late + "");
		absentDays = (TextView) getView().findViewById(R.id.absent_day);
		absentDays.setText(absent + "");

		signRate = (TextView) getView().findViewById(R.id.sign_rate);
		signRate.setText(signRate1 + "");

		goodSignRate = (TextView) getView().findViewById(R.id.good_sign_rate);
		goodSignRate.setText(goodSignRate1 + "");

		level = (TextView) getView().findViewById(R.id.level);
		level.setText(level1 + "");

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.my_sign_info, null);
	}

}
