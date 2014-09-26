package morningSign;

import com.hust.hustba.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DemoFragment extends android.support.v4.app.Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//container.removeAllViews();
		View rootView = inflater.inflate(R.layout.demo_fragment, container,false);		
		return rootView;
	}
}
