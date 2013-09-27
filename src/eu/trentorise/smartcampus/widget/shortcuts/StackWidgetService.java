package eu.trentorise.smartcampus.widget.shortcuts;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import eu.trentorise.smartcampus.widget.R;
import eu.trentorise.smartcampus.widget.shortcuts.WidgetHelper.BookmarkDescriptor;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class StackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(getApplicationContext(), intent);
    }
}

class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private static final int mCount = 2;
    private List<WidgetItem> mWidgetItems = new ArrayList<WidgetItem>();
    private Context mContext;
    private int mAppWidgetId;
	BookmarkDescriptor[] DTPREFERENCES;
	BookmarkDescriptor[] JPPREFERENCES;
	
    public StackRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
  
    }

    public void onCreate() {
        // In onCreate() you setup any connections / cursors to your data source. Heavy lifting,
        // for example downloading or creating content etc, should be deferred to onDataSetChanged()
        // or getViewAt(). Taking more than 20 seconds in this call will result in an ANR.
 
        for (int i = 0; i < mCount; i++) {
            mWidgetItems.add(new WidgetItem(i + "!"));
        }
     	try {
			JSONArray DTjsonArray = JSONSharedPreferences.loadJSONArray(mContext, "WIDGET", "DTPREFERENCES");
			List<BookmarkDescriptor> list = new ArrayList<BookmarkDescriptor>();
			for (int k = 0; k < DTjsonArray.length(); k++) {
				list.add(JSONSharedPreferences.fromJson(DTjsonArray.get(k).toString()));
			}
			DTPREFERENCES = new BookmarkDescriptor[list.size()];
			list.toArray(DTPREFERENCES); 
			
			JSONArray JPjsonArray = JSONSharedPreferences.loadJSONArray(mContext, "WIDGET", "JPPREFERENCES");
			list = new ArrayList<BookmarkDescriptor>();
			for (int k = 0; k < JPjsonArray.length(); k++) {
				list.add(JSONSharedPreferences.fromJson(JPjsonArray.get(k).toString()));
			}
			JPPREFERENCES = new BookmarkDescriptor[list.size()];
			list.toArray(JPPREFERENCES); 			
			} catch (Exception e) {
			e.printStackTrace();
		}
        // We sleep for 3 seconds here to show how the empty view appears in the interim.
        // The empty view is set in the StackWidgetProvider and should be a sibling of the
        // collection view.
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public void onDestroy() {
        // In onDestroy() you should tear down anything that was setup for your data source,
        // eg. cursors, connections, etc.
        mWidgetItems.clear();
    }

    public int getCount() {
        return mCount;
    }

    public RemoteViews getViewAt(int position) {
        // position will always range from 0 to getCount() - 1.

        // We construct a remote views item based on our widget item xml file, and set the
        // text based on the position.
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
        rv.setTextViewText(R.id.widget_item, mWidgetItems.get(position).text);

//        // Next, we set a fill-intent which will be used to fill-in the pending intent template
//        // which is set on the collection view in StackWidgetProvider.
//        Bundle extras = new Bundle();
//        extras.putInt(SmartCampusShortCutsProvider.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.setAction(mContext.getString(DTPREFERENCES[0].intent));
        fillInIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
       		 | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        fillInIntent.putExtras(extras);
        rv.setOnClickFillInIntent(R.id.widget_item, fillInIntent);

        
       
        
        // You can do heaving lifting in here, synchronously. For example, if you need to
        // process an image, fetch something from the network, etc., it is ok to do it here,
        // synchronously. A loading view will show up in lieu of the actual contents in the
        // interim.
//        try {
//            System.out.println("Loading view " + position);
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // Return the remote views object.
        return rv;
    }

    public RemoteViews getLoadingView() {
        // You can create a custom loading view (for instance when getViewAt() is slow.) If you
        // return null here, you will get the default loading view.
        return null;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public long getItemId(int position) {
        return position;
    }

    public boolean hasStableIds() {
        return true;
    }

    public void onDataSetChanged() {
        // This is triggered when you call AppWidgetManager notifyAppWidgetViewDataChanged
        // on the collection view corresponding to this factory. You can do heaving lifting in
        // here, synchronously. For example, if you need to process an image, fetch something
        // from the network, etc., it is ok to do it here, synchronously. The widget will remain
        // in its current state while work is being done here, so you don't need to worry about
        // locking up the widget.
    }
    
    private  class WidgetItem {
        public String text;

        public WidgetItem(String text) {
            this.text = text;
        }
    }
}
