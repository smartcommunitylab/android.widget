package eu.trentorise.smartcampus.widget.shortcuts;

import com.actionbarsherlock.app.SherlockFragment;

import eu.trentorise.smartcampus.widget.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment1 extends SherlockFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment1, container, false);
		return rootView;
	}

}
