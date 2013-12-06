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
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragAutobus extends SherlockFragment {
	
	private ListView listViewObject;
	private WidgetHelper widgetHelper;
	private List<SmartLine> busLines;
	private boolean[] checkBoxState = new boolean[50]; //fisso???

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		checkBoxState=load();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragautobus, container, false);

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
		
		List<BookmarkDescriptor> bookmark = Arrays.asList(WidgetHelper
				.getJPBOOKMARKSBUS());
		
		List<BookmarkDescriptor> filtered_bookmarksdescriptors = new ArrayList<WidgetHelper.BookmarkDescriptor>();
		List<RouteDescriptor> route_descriptors = new ArrayList<RouteDescriptor>();
		List<String> route_names = new ArrayList<String>();
		String agencyId = null;
		String routeId = null;
		//WidgetHelper.PARAM_AGENCY_ID, WidgetHelper.PARAM_ROUTE_ID));
		

		
		for (BookmarkDescriptor bookmarkDescriptor : bookmark) {	
			
			List<Param> params=bookmarkDescriptor.getParams();
			//get params
				//get agencyid and routeid
			
			for (Param parametri : params){
				if("AGENCY_ID".equals(parametri.getName())){
					agencyId=parametri.getValue();
				}
				if("ROUTE_ID".equals(parametri.getName())){
					routeId=parametri.getValue();
				}
			}
			
			RouteDescriptor route_descriptor= RoutesHelper.getRouteDescriptorByRouteId(agencyId, routeId);
			route_descriptors.add(route_descriptor);
			//Lista di nomi delle direzioni
			route_names.add(getString(route_descriptor.getNameResource()));
			
			filtered_bookmarksdescriptors.add(bookmarkDescriptor);
				
		}
		
	
		
		listViewObject = (ListView) getSherlockActivity().findViewById(
				R.id.fragautobus);
		listViewObject.setAdapter(new AutobusListAdapter(getSherlockActivity(),
				R.layout.row_element, filtered_bookmarksdescriptors, route_names, checkBoxState));

	}
	
	
	  public boolean[] load() {
		  SharedPreferences sharedPreferences = getSherlockActivity().getSharedPreferences("sharedPrefsAutobus", Context.MODE_PRIVATE);
	        boolean[] reChecked = new boolean[checkBoxState.length];
	        for(Integer i = 0; i < checkBoxState.length; i++)
	        {
	             reChecked[i] = sharedPreferences.getBoolean(i.toString(), false);
	        }
	        return reChecked;
	    }
	    
}
	
	
