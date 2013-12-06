package eu.trentorise.smartcampus.widget.shortcuts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.actionbarsherlock.app.SherlockFragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import eu.trentorise.smartcampus.dt.notifications.NotificationsSherlockFragmentDT;
import eu.trentorise.smartcampus.widget.R;
import eu.trentorise.smartcampus.widget.shortcuts.WidgetHelper.BookmarkDescriptor;

public class FragEvent extends SherlockFragment{

	private ListView listViewObject;
	private WidgetHelper widgetHelper;
	private boolean[] checkBoxState=new boolean[32];
	CheckBox checkBox;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		checkBox=(CheckBox)getSherlockActivity().findViewById(R.id.checkBox);
		checkBoxState=load();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragevent, container,
				false);

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
			if(bookmarkDescriptor.getIntent()==getResources().getString(R.string.events_intent_action)){
				if(bookmarkDescriptor.getName()!= getResources().getString(R.string.tab_places) 
						&& bookmarkDescriptor.getName()!= getResources().getString(R.string.tab_stories)
						&& bookmarkDescriptor.getName()!= getResources().getString(R.string.tab_events))
				filtered_bookmarksdescriptors.add(bookmarkDescriptor);
			}
		}
		
		listViewObject = (ListView) getSherlockActivity().findViewById(
				R.id.fragevent);
		listViewObject.setAdapter(new EventListAdapter(getSherlockActivity(),
				R.layout.row_element, filtered_bookmarksdescriptors, checkBoxState));
	}


	  public boolean[] load() {
		  SharedPreferences sharedPreferences = getSherlockActivity().getSharedPreferences("sharedPrefsEvents", Context.MODE_PRIVATE);
	        boolean[] reChecked = new boolean[checkBoxState.length];
	        for(Integer i = 0; i < checkBoxState.length; i++)
	        {
	             reChecked[i] = sharedPreferences.getBoolean(i.toString(), false);
	        }
	        return reChecked;
	    }
	    

	
	}
