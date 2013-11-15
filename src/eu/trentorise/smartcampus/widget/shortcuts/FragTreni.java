package eu.trentorise.smartcampus.widget.shortcuts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.actionbarsherlock.app.SherlockFragment;

import eu.trentorise.smartcampus.jp.custom.data.SmartLine;
import eu.trentorise.smartcampus.jp.helper.RoutesHelper;
import eu.trentorise.smartcampus.jp.model.RouteDescriptor;
import eu.trentorise.smartcampus.widget.R;
import eu.trentorise.smartcampus.widget.shortcuts.WidgetHelper.BookmarkDescriptor;
import eu.trentorise.smartcampus.widget.shortcuts.WidgetHelper.Param;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragTreni extends SherlockFragment {
	
	private ListView listViewObject;
	private WidgetHelper widgetHelper;
	private List<SmartLine> busLines;
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
		View rootView = inflater.inflate(R.layout.fragtreni, container, false);

		return rootView;
	}

	@Override
	public void onStart() {
		super.onStart();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	
		//ArrayList<BookmarkDescriptor> bookmark = WidgetHelper.getDTBOOKMARKS();
		
		List<BookmarkDescriptor> bookmark = Arrays.asList(WidgetHelper
				.getJPBOOKMARKSTRAINS());
		
		List<BookmarkDescriptor> filtered_bookmarksdescriptors = new ArrayList<WidgetHelper.BookmarkDescriptor>();

		
		for (BookmarkDescriptor bookmarkDescriptor : bookmark) {
			filtered_bookmarksdescriptors.add(bookmarkDescriptor);
		}
		

		
		listViewObject = (ListView) getSherlockActivity().findViewById(
				R.id.fragtreni);
		listViewObject.setAdapter(new TreniListAdapter(getSherlockActivity(),
				R.layout.row_element, filtered_bookmarksdescriptors, checkBoxState));

	}
}
	