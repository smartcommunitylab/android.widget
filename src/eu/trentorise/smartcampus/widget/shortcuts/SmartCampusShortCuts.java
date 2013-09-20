package eu.trentorise.smartcampus.widget.shortcuts;

import eu.trentorise.smartcampus.widget.R;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.widget.RemoteViews;
import android.widget.Toast;

public class SmartCampusShortCuts extends AppWidgetProvider {
	private static final String ACTION_CLICK = "ACTION_CLICK";
	private  WidgetHelper helper = null;
	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		Toast.makeText(context, "Set shortcuts", Toast.LENGTH_SHORT).show();
	}
	  @Override
	  public void onUpdate(Context context, AppWidgetManager appWidgetManager,
	      int[] appWidgetIds) {
			helper = new WidgetHelper(context);

			Toast.makeText(context, "Update shortcuts", Toast.LENGTH_SHORT).show();
	        final int N = appWidgetIds.length;
	        
	        // Perform this loop procedure for each App Widget that belongs to this provider
	        for (int i=0; i<N; i++) {
	            int appWidgetId = appWidgetIds[i];
	            // Create an Intent to launch ExampleActivity
//	            Intent intent = new Intent(context, DiscoverTrentoActivity.class);
	            Intent intent = new Intent();
	    		intent.setAction(context.getString(helper.DTBOOKMARKS[0].intent));
	            intent.putExtra(helper.DTBOOKMARKS[0].params.get(0).name, helper.DTBOOKMARKS[0].params.get(0).value);
	            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
	                    | Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            //dt non ne ha bisogno poiche' e' unica activity
	            PendingIntent dtpendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
	            intent = new Intent();
	    		intent.setAction(context.getString(helper.DTBOOKMARKS[1].intent));
	            intent.putExtra(helper.DTBOOKMARKS[1].params.get(0).name, helper.DTBOOKMARKS[1].params.get(0).value);
	            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
	                    | Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            //dt non ne ha bisogno poiche' e' unica activity
	            PendingIntent dt2pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
	            
	            
//	            intent = new Intent();
//	    		intent.setAction(context.getString(R.string.real_time_info_intent_action));
//
//	    		intent.putExtra(helper.JPBOOKMARKS[0].params.get(0).name, helper.JPBOOKMARKS[0].params.get(0).value);
//	    		intent.putExtra(helper.JPBOOKMARKS[0].params.get(1).name, helper.JPBOOKMARKS[0].params.get(1).value);
//	    		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
//	                    | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//	            PendingIntent jppendingIntent =
//	                    TaskStackBuilder.create(context)
//	                                    // add all of DetailsActivity's parents to the stack,
//	                                    // followed by DetailsActivity itself
//	                                    .addNextIntentWithParentStack(intent)
//	                                    .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

	            // Get the layout for the App Widget and attach an on-click listener
	            // to the button
				RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.shortcuts_widget);
	            views.setOnClickPendingIntent(R.id.button1, dtpendingIntent);
	            views.setOnClickPendingIntent(R.id.button2, dt2pendingIntent);
	            
//	            views.setOnClickPendingIntent(R.id.button2, jppendingIntent);

	            // Tell the AppWidgetManager to perform an update on the current app widget
	            appWidgetManager.updateAppWidget(appWidgetId, views);
	            

	  }
}
}
