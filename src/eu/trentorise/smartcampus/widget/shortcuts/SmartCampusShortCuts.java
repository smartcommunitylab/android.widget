package eu.trentorise.smartcampus.widget.shortcuts;


import eu.trentorise.smartcampus.widget.R;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class SmartCampusShortCuts extends AppWidgetProvider {
	private static final String ACTION_CLICK = "ACTION_CLICK";
	private  WidgetHelper helper = null;

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
				RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.shortcuts_widget);

				/*test luoghi*/
	            Intent intent1 = new Intent();
	            intent1.setAction(context.getString(helper.DTBOOKMARKS[0].intent));
	            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
	                    | Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            PendingIntent dt1pendingIntent = PendingIntent.getActivity(context, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);            
	            

				/*test musei*/

	            Intent intent2 = new Intent();
	            intent2.setAction(context.getString(helper.DTBOOKMARKS[1].intent));
	            intent2.putExtra(helper.DTBOOKMARKS[1].params.get(0).name, helper.DTBOOKMARKS[1].params.get(0).value);
	            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
	                    | Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            PendingIntent dt2pendingIntent = PendingIntent.getActivity(context, 1, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
	            

				/*test bus singolo trip (A)*/

	            Intent intent3 = new Intent();
	            intent3.setAction(context.getString(R.string.real_time_info_bus_intent_action));
	            intent3.putExtra(helper.JPBOOKMARKSBUS[0].params.get(0).name, helper.JPBOOKMARKSBUS[0].params.get(0).value);
	            intent3.putExtra(helper.JPBOOKMARKSBUS[0].params.get(1).name, helper.JPBOOKMARKSBUS[0].params.get(1).value);
	            intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
	                    | Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            PendingIntent jppendingIntent =
	                    TaskStackBuilder.create(context)
	                                    // add all of DetailsActivity's parents to the stack,
	                                    // followed by DetailsActivity itself
	                                    .addNextIntentWithParentStack(intent3)
	                                    .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

	            // Get the layout for the App Widget and attach an on-click listener
	            // to the button
	            views.setOnClickPendingIntent(R.id.button1, dt1pendingIntent);

	            views.setOnClickPendingIntent(R.id.button2, dt2pendingIntent);
	            
	            views.setOnClickPendingIntent(R.id.button3, jppendingIntent);
	            
	            views.setOnClickPendingIntent(R.id.button4, dt2pendingIntent);
	            views.setOnClickPendingIntent(R.id.button5, dt2pendingIntent);
	            views.setOnClickPendingIntent(R.id.button6, dt2pendingIntent);

	            
//	            views.setOnClickPendingIntent(R.id.button2, jppendingIntent);

	            // Tell the AppWidgetManager to perform an update on the current app widget
	            appWidgetManager.updateAppWidget(appWidgetId, views);
	            

	  }
	        

}
	  
      static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
              int appWidgetId, String titlePrefix) {
          Log.d("Widget", "updateAppWidget appWidgetId=" + appWidgetId + " titlePrefix=" + titlePrefix);

          RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.shortcuts_widget);
          // Tell the widget manager
          appWidgetManager.updateAppWidget(appWidgetId, views);
      }
}
