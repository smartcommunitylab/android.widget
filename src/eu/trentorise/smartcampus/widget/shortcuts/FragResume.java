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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;
import eu.trentorise.smartcampus.jp.helper.RoutesHelper;
import eu.trentorise.smartcampus.jp.model.RouteDescriptor;
import eu.trentorise.smartcampus.widget.R;
import eu.trentorise.smartcampus.widget.shortcuts.WidgetHelper.BookmarkDescriptor;
import eu.trentorise.smartcampus.widget.shortcuts.WidgetHelper.Param;

public class FragResume extends SherlockFragment {

	AppWidgetManager SmartCampusShortCuts;
	int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
	Button conferma;
	Context ctx;
	private ListView listViewObject;
	private WidgetHelper widgetHelper;
	private boolean[] checkBoxState = new boolean[100];
	boolean[] checkTOTALE = new boolean[150];
	ArrayList<Integer> iDChecked = new ArrayList<Integer>();
	boolean[] reChecked;
	int badCheck = 0;
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

		ctx = getSherlockActivity().getBaseContext();
		// Set the result to CANCELED. This will cause the widget host to cancel
		// out of the widget placement if they press the back button.
		getSherlockActivity().setResult(
				Activity.RESULT_CANCELED);

		// Find the widget id from the intent.
		Intent intent = getSherlockActivity().getIntent();
		Bundle extras = intent.getExtras();
		if (extras != null) {
			mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
					AppWidgetManager.INVALID_APPWIDGET_ID);
		}

		SmartCampusShortCuts = AppWidgetManager.getInstance(ctx);

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
		// aggiunto
	//	load();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		//aggiunto
		badCheck=0;
		for (int i=0; i<reChecked.length; i++){
			reChecked[i]=false;
		}
		//Pulisco SharedPreferences
		SharedPreferences sharedPreferencesResume = getSherlockActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
		sharedPreferencesResume.edit().clear().commit();
		
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		conferma = (Button) getSherlockActivity().findViewById(
				R.id.confermascelte);
		conferma.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				final Context context = getSherlockActivity();
				load();

				
				for (int i = 0; i < reChecked.length; i++) {
					if (reChecked[i] == true) {
						iDChecked.add(i);
					} else {
						badCheck = badCheck + 1;
					}
				}

				// controllo di aver selezionato qualcosa
				if (badCheck == 100) {
					badCheck=0;
					Toast.makeText(
							getSherlockActivity().getApplicationContext(),
							"Non hai selezionato nulla!", Toast.LENGTH_LONG)
							.show();
					getSherlockActivity().finish();
				} else {
					Toast.makeText(
							getSherlockActivity().getApplicationContext(),
							"Hai selezionato almeno un elemento!", Toast.LENGTH_LONG)
							.show();
				}

				final AppWidgetManager appWidgetManager = AppWidgetManager
						.getInstance(context);
				
				//trasformo in array int da arraylist
				int[] ArrayWrapper = new int[iDChecked.size()];

				for(int i = 0; i < iDChecked.size(); i++) {
				    if (iDChecked.get(i) != null) {
				        ArrayWrapper[i] = iDChecked.get(i);
				    }
				}
				
				StackWidgetProvider.updateAppWidget(context,
						appWidgetManager, ArrayWrapper);  //mAppWidgetId
				
				
				
				/*
				 * // Push widget update to surface with newly set prefix
				 * AppWidgetManager appWidgetManager =
				 * AppWidgetManager.getInstance(context);
				 */
/*
				// Instantiating the class RemoteViews with widget_layout
				RemoteViews views = new RemoteViews(ctx.getPackageName(),
						R.layout.widget_layout);

				// Tell the AppWidgetManager to perform an update on the app
				// widget
				SmartCampusShortCuts.updateAppWidget(mAppWidgetId, views);
*/
				/*
				 * SmartCampusShortCuts.updateAppWidget(context,
				 * appWidgetManager, mAppWidgetId, null);
				 */

				// Make sure we pass back the original appWidgetId
				WidgetHelper helper = new WidgetHelper(context);
				
				String[] ALLPREFERENCES;
	
				ALLPREFERENCES = new String[ArrayWrapper.length];
				
				//ERRORE QUI! ??
				for (int j=0; j<ArrayWrapper.length; j++){
					ALLPREFERENCES[j] = JSONSharedPreferences.convertToJSON(WidgetHelper.ALLBOOKMARKS[ArrayWrapper[j]]); 
				}
				
				JSONSharedPreferences.saveJSONArray(context, "WIDGET","ALLPREFERENCES", new JSONArray(Arrays.asList(ALLPREFERENCES)));

				final Intent resultValue = new Intent();

				resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);	//mAppWidgetId

				//resultValue.putExtra("final_result", iDChecked);

				getSherlockActivity().setResult(Activity.RESULT_OK, resultValue);

				getSherlockActivity().finish();

			}
		});

		/*
		 * System.arraycopy(checkStories, 0, checkTOTALE, 0,
		 * checkStories.length); System.arraycopy(checkEvents, 0, checkTOTALE,
		 * checkStories.length, checkEvents.length); System.arraycopy(checkPOI,
		 * 0, checkTOTALE, checkEvents.length, checkPOI.length);
		 * System.arraycopy(checkAutobus, 0, checkTOTALE, checkPOI.length,
		 * checkAutobus.length); System.arraycopy(checkParcheggi, 0,
		 * checkTOTALE, checkAutobus.length, checkParcheggi.length);
		 * System.arraycopy(checkTreni, 0, checkTOTALE, checkParcheggi.length,
		 * checkTreni.length);
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

			List<Param> params = bookmarkDescriptor.getParams();
			// get params
			// get agencyid and routeid

			for (Param parametri : params) {
				if ("AGENCY_ID".equals(parametri.getName())) {
					agencyId = parametri.getValue();
				}
				if ("ROUTE_ID".equals(parametri.getName())) {
					routeId = parametri.getValue();
				}
			}

			RouteDescriptor route_descriptor = RoutesHelper
					.getRouteDescriptorByRouteId(agencyId, routeId);
			route_descriptors.add(route_descriptor);
			// Lista di nomi delle direzioni
			route_names.add(getString(route_descriptor.getNameResource()));

			filtered_bookmarksdescriptors.add(bookmarkDescriptor);

		}

		for (BookmarkDescriptor bookmarkDescriptor : DTbookmark) {
			if (bookmarkDescriptor.getName() != getResources().getString(
					R.string.tab_places)
					&& bookmarkDescriptor.getName() != getResources()
							.getString(R.string.tab_stories)
					&& bookmarkDescriptor.getName() != getResources()
							.getString(R.string.tab_events))
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
				R.layout.row_element, filtered_bookmarksdescriptors,
				route_names, checkTOTALE));

	}

	public boolean[] load() {

		SharedPreferences sharedPreferences = getSherlockActivity()
				.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);

		/*
		 * SharedPreferences sharedPreferencesPoi=
		 * getSherlockActivity().getSharedPreferences("sharedPrefsPOI",
		 * Context.MODE_PRIVATE); SharedPreferences sharedPreferencesEvents =
		 * getSherlockActivity().getSharedPreferences("sharedPrefsEvents",
		 * Context.MODE_PRIVATE); SharedPreferences sharedPreferencesStories =
		 * getSherlockActivity().getSharedPreferences("sharedPrefsStories",
		 * Context.MODE_PRIVATE); SharedPreferences sharedPreferencesAutobus =
		 * getSherlockActivity().getSharedPreferences("sharedPrefsAutobus",
		 * Context.MODE_PRIVATE); SharedPreferences sharedPreferencesParcheggi =
		 * getSherlockActivity().getSharedPreferences("sharedPrefsParcheggi",
		 * Context.MODE_PRIVATE); SharedPreferences sharedPreferencesTreni =
		 * getSherlockActivity().getSharedPreferences("sharedPrefsTreni",
		 * Context.MODE_PRIVATE);
		 */

		reChecked = new boolean[100];

		for (Integer i = 0; i < 100; i++) {
			reChecked[i] = sharedPreferences.getBoolean(i.toString(), false);
		}
		
		return reChecked;

	}
		
}


