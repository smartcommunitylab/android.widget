package eu.trentorise.smartcampus.widget.shortcuts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.Toast;
import eu.trentorise.smartcampus.dt.notifications.NotificationsSherlockFragmentDT;
import eu.trentorise.smartcampus.jp.helper.RoutesHelper;
import eu.trentorise.smartcampus.jp.model.RouteDescriptor;
import eu.trentorise.smartcampus.widget.R;
import eu.trentorise.smartcampus.widget.shortcuts.WidgetHelper.BookmarkDescriptor;
import eu.trentorise.smartcampus.widget.shortcuts.WidgetHelper.Param;

public class FragResume extends SherlockFragment {

	AppWidgetManager SmartCampusShortCuts;
	int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
	Button conferma;
	private ListView listViewObject;
	private WidgetHelper widgetHelper;
	private boolean[] checkBoxState = new boolean[100];
	boolean[] checkTOTALE = new boolean[150];
	int[] iDChecked;
	boolean[] checkStories = new boolean[150];
	boolean[] checkEvents = new boolean[150];
	boolean[] checkPOI = new boolean[150];
	boolean[] checkAutobus = new boolean[150];
	boolean[] checkParcheggi = new boolean[150];
	boolean[] checkTreni = new boolean[150];

	
	CheckBox checkBox;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
         
		
		// Set the result to CANCELED. This will cause the widget host to cancel
        // out of the widget placement if they press the back button.
        getSherlockActivity().setResult(SherlockFragmentActivity.RESULT_CANCELED);
		
         // Find the widget id from the intent.
         Intent intent = getSherlockActivity().getIntent();
         Bundle extras = intent.getExtras();
         if (extras != null) {
         mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
         }

         // If they gave us an intent without the widget id, just bail.
         if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
         getSherlockActivity().finish();
         }
		
		
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
		
		conferma = (Button) getSherlockActivity().findViewById(R.id.confermascelte);
		conferma.setOnClickListener(new View.OnClickListener() {
		
			
			@Override
			public void onClick(View v) {
				
				 final Context context = getSherlockActivity();
				
				 for (int i=0; i < checkTOTALE.length; i++ ){
					  if(checkTOTALE[i]==true){
						  iDChecked[i]=i;
					  }
				 }
				 
				 
		        // Push widget update to surface with newly set prefix
		         AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
		         
		         // Instantiating the class RemoteViews with widget_layout
		         RemoteViews views = new RemoteViews(getSherlockActivity().getBaseContext().getPackageName(), R.layout.widget_layout); 
		         
		         // Tell the AppWidgetManager to perform an update on the app widget
		         appWidgetManager.updateAppWidget(mAppWidgetId, views);
		         
		        /*
		        SmartCampusShortCuts.updateAppWidget(context, appWidgetManager,
		        	 mAppWidgetId, null);
		        */
		         
		         // Make sure we pass back the original appWidgetId
		         WidgetHelper helper = new WidgetHelper(context);
		                 String[] DTPREFERENCES;
		                 String[] JPPREFERENCES;
		                 
		                        /* Load prefix values */
		                        /*
		                         DTPREFERENCES = new String[2];
		                         DTPREFERENCES[0] =JSONSharedPreferences.convertToJSON(helper.DTBOOKMARKS[0]);
		                         DTPREFERENCES[1] =JSONSharedPreferences.convertToJSON(helper.DTBOOKMARKS[1]);                
		                         JPPREFERENCES = new String[4];
		                         JPPREFERENCES[0] =JSONSharedPreferences.convertToJSON(helper.JPBOOKMARKS[0]);
		                         JPPREFERENCES[1] =JSONSharedPreferences.convertToJSON(helper.JPBOOKMARKS[1]);        
		                         JPPREFERENCES[2] =JSONSharedPreferences.convertToJSON(helper.JPBOOKMARKS[2]);
		                         JPPREFERENCES[3] =JSONSharedPreferences.convertToJSON(helper.JPBOOKMARKS[3]);        
		                         JSONSharedPreferences.saveJSONArray(context, "WIDGET", "DTPREFERENCES",new JSONArray(Arrays.asList(DTPREFERENCES)));
		                         JSONSharedPreferences.saveJSONArray(context, "WIDGET", "JPPREFERENCES",new JSONArray(Arrays.asList(JPPREFERENCES)));
		                        */

		        Toast.makeText(getSherlockActivity().getApplicationContext(), "this is my Toast message!!! =)",
		        		Toast.LENGTH_LONG).show();
		                 
		                 
				Intent resultValue = new Intent();
				
		        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
		        
		        resultValue.putExtra("final_result", iDChecked);
		        
		        getSherlockActivity().setResult(SherlockFragmentActivity.RESULT_OK, resultValue);
		        
		        getSherlockActivity().finish();
     
			}
		});
		
		
		/*
		System.arraycopy(checkStories, 0, checkTOTALE, 0, checkStories.length);
		System.arraycopy(checkEvents, 0, checkTOTALE, checkStories.length, checkEvents.length);
		System.arraycopy(checkPOI, 0, checkTOTALE, checkEvents.length, checkPOI.length);
		System.arraycopy(checkAutobus, 0, checkTOTALE, checkPOI.length, checkAutobus.length);
		System.arraycopy(checkParcheggi, 0, checkTOTALE, checkAutobus.length, checkParcheggi.length);
		System.arraycopy(checkTreni, 0, checkTOTALE, checkParcheggi.length, checkTreni.length);
		*/
		
		List<BookmarkDescriptor> DTbookmark = Arrays.asList(WidgetHelper
				.getDTBOOKMARKS());
		
		List<BookmarkDescriptor> TRAINbookmark = Arrays.asList(WidgetHelper
				.getJPBOOKMARKSTRAINS());
		
		List<BookmarkDescriptor> bookmark = Arrays.asList(WidgetHelper
				.getJPBOOKMARKSBUS());
		
		List<BookmarkDescriptor> filtered_bookmarksdescriptors = new ArrayList<WidgetHelper.BookmarkDescriptor>();
		List<RouteDescriptor> route_descriptors = new ArrayList<RouteDescriptor>();
		List<String> route_names = new ArrayList<String>();
		String agencyId = null;
		String routeId = null;
		
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
		
		for (BookmarkDescriptor bookmarkDescriptor : DTbookmark) {
				if(bookmarkDescriptor.getName()!= getResources().getString(R.string.tab_places) 
						&& bookmarkDescriptor.getName()!= getResources().getString(R.string.tab_stories)
						&& bookmarkDescriptor.getName()!= getResources().getString(R.string.tab_events))
			filtered_bookmarksdescriptors.add(bookmarkDescriptor);
			route_names.add("");
		}
		
		for (BookmarkDescriptor bookmarkDescriptor : TRAINbookmark) {
			filtered_bookmarksdescriptors.add(bookmarkDescriptor);
			route_names.add("");
		}
		
		
		
		listViewObject = (ListView) getSherlockActivity().findViewById(
				R.id.fragresume);
		listViewObject.setAdapter(new ResumeListAdapter(getSherlockActivity(),
				R.layout.row_element, filtered_bookmarksdescriptors, route_names, checkTOTALE));

	
	}
	
	
	  public boolean[] load() {
		  
		  SharedPreferences sharedPreferences= getSherlockActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
		  
		  /*
		  SharedPreferences sharedPreferencesPoi= getSherlockActivity().getSharedPreferences("sharedPrefsPOI", Context.MODE_PRIVATE);
		  SharedPreferences sharedPreferencesEvents = getSherlockActivity().getSharedPreferences("sharedPrefsEvents", Context.MODE_PRIVATE);
		  SharedPreferences sharedPreferencesStories = getSherlockActivity().getSharedPreferences("sharedPrefsStories", Context.MODE_PRIVATE);
		  SharedPreferences sharedPreferencesAutobus = getSherlockActivity().getSharedPreferences("sharedPrefsAutobus", Context.MODE_PRIVATE);
		  SharedPreferences sharedPreferencesParcheggi = getSherlockActivity().getSharedPreferences("sharedPrefsParcheggi", Context.MODE_PRIVATE);
		  SharedPreferences sharedPreferencesTreni = getSherlockActivity().getSharedPreferences("sharedPrefsTreni", Context.MODE_PRIVATE);
		  */
		  
	        boolean [] reChecked = new boolean[checkBoxState.length];
	        
	        for(Integer i = 0; i < checkBoxState.length; i++)
	        {
	             reChecked[i] = sharedPreferences.getBoolean(i.toString(), false);
	             
	        }
	        return reChecked;
	    }
	
	
	
	
}