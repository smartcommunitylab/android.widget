package eu.trentorise.smartcampus.widget.shortcuts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.actionbarsherlock.app.SherlockFragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import eu.trentorise.smartcampus.dt.notifications.NotificationsSherlockFragmentDT;
import eu.trentorise.smartcampus.widget.R;
import eu.trentorise.smartcampus.widget.shortcuts.WidgetHelper.BookmarkDescriptor;
import eu.trentorise.smartcampus.widget.shortcuts.WidgetHelper.Param;

public class FragResume extends SherlockFragment {

	private ListView listViewObject;
	private WidgetHelper widgetHelper;
	private boolean[] checkBoxState = new boolean[32];
	
	//POIListAdapter fpoi = new POIListAdapter();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Definire get Check con getter in POIListAdapter
	//	Boolean ciao[] = fpoi.getCheck();
		
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragresume, container, false);

		return rootView;
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		
		
		List<BookmarkDescriptor> filtered_bookmarksdescriptors = new ArrayList<WidgetHelper.BookmarkDescriptor>();

		
	
		listViewObject = (ListView) getSherlockActivity().findViewById(
				R.id.fragresume);
		listViewObject.setAdapter(new POIListAdapter(getSherlockActivity(),
				R.layout.row_element, filtered_bookmarksdescriptors, checkBoxState));

	}
}