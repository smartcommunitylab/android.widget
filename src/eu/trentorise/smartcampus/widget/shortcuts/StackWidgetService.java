/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.trentorise.smartcampus.widget.shortcuts;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import eu.trentorise.smartcampus.widget.R;
import eu.trentorise.smartcampus.widget.shortcuts.WidgetHelper.BookmarkDescriptor;
import eu.trentorise.smartcampus.widget.shortcuts.WidgetHelper.Param;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class StackWidgetService extends RemoteViewsService {
	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		return new StackRemoteViewsFactory(this.getApplicationContext(), intent);
	}
}

class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
	private static final int mCount = 6;
	private List<WidgetItem> mWidgetItems = new ArrayList<WidgetItem>();
	private Context mContext;
	private int mAppWidgetId;
	BookmarkDescriptor[] DTPREFERENCES;
	BookmarkDescriptor[] JPPREFERENCES;
	BookmarkDescriptor[] ALLPREFERENCES;

	private WidgetHelper helper;

	public StackRemoteViewsFactory(Context context, Intent intent) {
		mContext = context;
		mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
	}

	public void onCreate() {
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
		helper = new WidgetHelper(mContext);
		ALLPREFERENCES = WidgetHelper.concat(DTPREFERENCES, JPPREFERENCES);
		// In onCreate() you setup any connections / cursors to your data
		// source. Heavy lifting,
		// for example downloading or creating content etc, should be deferred
		// to onDataSetChanged()
		// or getViewAt(). Taking more than 20 seconds in this call will result
		// in an ANR.
		for (int i = 0; i < mCount; i++) {
			mWidgetItems.add(new WidgetItem(i + "!"));
		}

		// // We sleep for 3 seconds here to show how the empty view appears in
		// the
		// // interim.
		// // The empty view is set in the StackWidgetProvider and should be a
		// // sibling of the
		// // collection view.
		// try {
		// Thread.sleep(3000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }

	}

	public void onDestroy() {
		// In onDestroy() you should tear down anything that was setup for your
		// data source,
		// eg. cursors, connections, etc.
		mWidgetItems.clear();
	}

	public int getCount() {
		return mCount;
	}

	public RemoteViews getViewAt(int position) {
		// position will always range from 0 to getCount() - 1.

		// We construct a remote views item based on our widget item xml
		// file, and set the
		// text based on the position.
		RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);

		// Next, we set a fill-intent which will be used to fill-in the
		// pending intent template
		// which is set on the collection view in StackWidgetProvider.
		if (ALLPREFERENCES != null && ALLPREFERENCES[position] != null) {
			BookmarkDescriptor myDescriptor = ALLPREFERENCES[position];

			// set the widgetbutton
			if (WidgetHelper.PARAM_TYPE.equals(myDescriptor.params.get(0).name)) {
				String type =myDescriptor.params.get(0).value;
				if (WidgetHelper.TYPE_DT.equals(type)){
					//dt -> put icons and hide text
//					int resource = 
//					rv.setImageViewResource(R.id.image_widget_item, );
					rv.setViewVisibility(R.id.text_widget_item, View.GONE);
//					android:src="@drawable/ab_solid_journeyplanner"
//					rv.setInt(R.id.image_widget_item, "setBackgroundResource", Integer.parseInt(myDescriptor.params.get(1).value));
					rv.setInt(R.id.layout, "setBackgroundResource", Integer.parseInt(myDescriptor.params.get(1).value));

				} else if (WidgetHelper.TYPE_JP.equals(type)){
					//jp -> put text and hide icon
					rv.setTextViewText(R.id.text_widget_item, myDescriptor.params.get(4).value);
					rv.setViewVisibility(R.id.image_widget_item, View.GONE);
					rv.setInt(R.id.text_widget_item, "setBackgroundResource", Integer.parseInt(myDescriptor.params.get(1).value));


				}
				BookmarkDescriptor bookmark = myDescriptor;
				Bundle extras = new Bundle();
				extras.putString(StackWidgetProvider.EXTRA_INTENT, bookmark.getIntent());
				if (bookmark.getParams() != null) {
					for (Param param : bookmark.getParams()) {
						extras.putString(param.name, param.value);

					}

				}
				Intent fillInIntent = new Intent(bookmark.getIntent());
				fillInIntent.putExtras(extras);
				rv.setOnClickFillInIntent(R.id.text_widget_item, fillInIntent);
			}
			// You can do heaving lifting in here, synchronously. For example,
			// if you need to
			// process an image, fetch something from the network, etc., it is
			// ok to do it here,
			// synchronously. A loading view will show up in lieu of the actual
			// contents in the
			// interim.
			// try {
			// System.out.println("Loading view " + position);
			// Thread.sleep(500);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
		}
		// Return the remote views object.
		return rv;
	}

	public RemoteViews getLoadingView() {
		// You can create a custom loading view (for instance when getViewAt()
		// is slow.) If you
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
		// This is triggered when you call AppWidgetManager
		// notifyAppWidgetViewDataChanged
		// on the collection view corresponding to this factory. You can do
		// heaving lifting in
		// here, synchronously. For example, if you need to process an image,
		// fetch something
		// from the network, etc., it is ok to do it here, synchronously. The
		// widget will remain
		// in its current state while work is being done here, so you don't need
		// to worry about
		// locking up the widget.
	}
}
