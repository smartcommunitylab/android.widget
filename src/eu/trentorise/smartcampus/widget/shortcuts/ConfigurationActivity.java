package eu.trentorise.smartcampus.widget.shortcuts;

import java.util.Arrays;

import org.json.JSONArray;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;

import eu.trentorise.smartcampus.widget.R;





public class ConfigurationActivity extends SherlockFragmentActivity {
	
	int mAppWidgetId;

	private int widgetID;
    Button AddWidget;
	   @Override
	   public void onCreate(Bundle savedInstanceState)
	      {
		   super.onCreate(savedInstanceState);
	        // Set the result to CANCELED.  This will cause the widget host to cancel
	        // out of the widget placement if they press the back button.
	        setResult(RESULT_CANCELED);

	        // Set the view layout resource to use.
	        setContentView(R.layout.configuration_layout);

	        // Find the EditText
	        AddWidget = (Button)findViewById(R.id.vai);

	        // Bind the action for the save button.
	        findViewById(R.id.vai).setOnClickListener(mOnClickListener);

	        // Find the widget id from the intent. 
	        Intent intent = getIntent();
	        Bundle extras = intent.getExtras();
	        if (extras != null) {
	            mAppWidgetId = extras.getInt(
	                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
	        }

	        // If they gave us an intent without the widget id, just bail.
	        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
	            finish();
	        }

	    }

	    View.OnClickListener mOnClickListener = new View.OnClickListener() {
	        public void onClick(View v) {
	            final Context context = ConfigurationActivity.this;

	            // Push widget update to surface with newly set prefix
//	            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
//	            SmartCampusShortCuts.updateAppWidget(context, appWidgetManager,
//	                    mAppWidgetId, null);

	            // Make sure we pass back the original appWidgetId
	            WidgetHelper helper = new WidgetHelper(context);
	        	String[] DTPREFERENCES;
	        	String[] JPPREFERENCES;
	    		/* Load prefix values */
	    		DTPREFERENCES = new String[2];
	    		DTPREFERENCES[0] =JSONSharedPreferences.convertToJSON(helper.DTBOOKMARKS[0]);
	    		DTPREFERENCES[1] =JSONSharedPreferences.convertToJSON(helper.DTBOOKMARKS[1]);		
	    		JPPREFERENCES = new String[4];
	    		JPPREFERENCES[0] =JSONSharedPreferences.convertToJSON(helper.JPBOOKMARKS[0]);
	    		JPPREFERENCES[1] =JSONSharedPreferences.convertToJSON(helper.JPBOOKMARKS[10]);	
	    		JPPREFERENCES[2] =JSONSharedPreferences.convertToJSON(helper.JPBOOKMARKS[28]);
	    		JPPREFERENCES[3] =JSONSharedPreferences.convertToJSON(helper.JPBOOKMARKS[29]);	
	    		JSONSharedPreferences.saveJSONArray(context, "WIDGET", "DTPREFERENCES",new JSONArray(Arrays.asList(DTPREFERENCES)));
	    		JSONSharedPreferences.saveJSONArray(context, "WIDGET", "JPPREFERENCES",new JSONArray(Arrays.asList(JPPREFERENCES)));

	    		Intent resultValue = new Intent();
	            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
	            setResult(RESULT_OK, resultValue);
	            finish();
	        }
	    };
	    


}
