package eu.trentorise.smartcampus.widget.shortcuts;

import java.util.ArrayList;
import java.util.List;

import com.actionbarsherlock.app.SherlockFragment;


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

public class FragPoiEvSto extends SherlockFragment {

	private ListView listViewObject;
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragpoievsto, container, false);
		
		List<BookmarkDescriptor> bookmark = new ArrayList<BookmarkDescriptor>();
		
		listViewObject = (ListView)getSherlockActivity().findViewById(R.id.fragpoievsto);
		listViewObject.setAdapter(new ObjectListAdapter(getSherlockActivity(), R.layout.row_element, bookmark));

	    
		return rootView;
	}

}

