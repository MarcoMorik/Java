package adroidapps.testapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ClockFragment extends Fragment {
	public static final String ARG_SECTION_NUMBER = "section_number";

	public ClockFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main_clock,
				container, false);
		
		return rootView;

}
}
