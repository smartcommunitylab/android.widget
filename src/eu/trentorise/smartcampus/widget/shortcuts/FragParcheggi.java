package eu.trentorise.smartcampus.widget.shortcuts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.actionbarsherlock.app.SherlockFragment;

import eu.trentorise.smartcampus.widget.R;
import eu.trentorise.smartcampus.widget.shortcuts.WidgetHelper.BookmarkDescriptor;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragParcheggi extends SherlockFragment {
	
	private ListView listViewObject;
	private WidgetHelper widgetHelper;
	private boolean[] checkBoxState = new boolean[32];

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragparcheggi, container, false);

		return rootView;
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		List<BookmarkDescriptor> bookmark = Arrays.asList(WidgetHelper
				.getDTBOOKMARKS());
	
		List<BookmarkDescriptor> filtered_bookmarksdescriptors = new ArrayList<WidgetHelper.BookmarkDescriptor>();

		for (BookmarkDescriptor bookmarkDescriptor : bookmark) {
			if(bookmarkDescriptor.getIntent()==getResources().getString(R.string.real_time_info_parking_intent_action)){
				if(bookmarkDescriptor.getName()!= getResources().getString(R.string.tab_places) 
						&& bookmarkDescriptor.getName()!= getResources().getString(R.string.tab_stories)
						&& bookmarkDescriptor.getName()!= getResources().getString(R.string.tab_events))
				filtered_bookmarksdescriptors.add(bookmarkDescriptor);
			}
		}

	
		listViewObject = (ListView) getSherlockActivity().findViewById(
				R.id.fragparcheggi);
		listViewObject.setAdapter(new ParcheggiListAdapter(getSherlockActivity(),
				R.layout.row_element, filtered_bookmarksdescriptors, checkBoxState));

	}
}

