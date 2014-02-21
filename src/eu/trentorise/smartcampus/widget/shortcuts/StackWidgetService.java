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

import eu.trentorise.smartcampus.jp.helper.RoutesHelper;
import eu.trentorise.smartcampus.widget.R;
import eu.trentorise.smartcampus.widget.shortcuts.WidgetHelper.BookmarkDescriptor;
import eu.trentorise.smartcampus.widget.shortcuts.WidgetHelper.Param;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.util.TypedValue;
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
	private static int mCount;
	private List<WidgetItem> mWidgetItems = new ArrayList<WidgetItem>();
	private Context mContext;
	private int mAppWidgetId;
	//BookmarkDescriptor[] DTPREFERENCES;
	//BookmarkDescriptor[] JPPREFERENCES;
	BookmarkDescriptor[] ALLPREFERENCES;

	private WidgetHelper helper;

	public StackRemoteViewsFactory(Context context, Intent intent) {
		mContext = context;
		mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
	}

	@Override
	public void onCreate() {
		try {
			JSONArray ALLjsonArray = JSONSharedPreferences.loadJSONArray(mContext, "WIDGET", "ALLPREFERENCES");
			List<BookmarkDescriptor> list = new ArrayList<BookmarkDescriptor>();
			for (int k = 0; k < ALLjsonArray.length(); k++) {
				list.add(JSONSharedPreferences.fromJson(ALLjsonArray.get(k).toString()));
			}
			
			ALLPREFERENCES = new BookmarkDescriptor[list.size()];
			list.toArray(ALLPREFERENCES);
			
/*
			JSONArray JPjsonArray = JSONSharedPreferences.loadJSONArray(mContext, "WIDGET", "JPPREFERENCES");
			list = new ArrayList<BookmarkDescriptor>();
			for (int k = 0; k < JPjsonArray.length(); k++) {
				list.add(JSONSharedPreferences.fromJson(JPjsonArray.get(k).toString()));
			}
			JPPREFERENCES = new BookmarkDescriptor[list.size()];
			list.toArray(JPPREFERENCES);
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		helper = new WidgetHelper(mContext);
		
		// ALLPREFERENCES = WidgetHelper.concat(DTPREFERENCES, JPPREFERENCES);
		
		// In onCreate() you setup any connections / cursors to your data
		// source. Heavy lifting,
		// for example downloading or creating content etc, should be deferred
		// to onDataSetChanged()
		// or getViewAt(). Taking more than 20 seconds in this call will result
		// in an ANR.
		
		//Dimensiono gli spazi in base a quanti sono stati cliccati
		mCount=ALLPREFERENCES.length;
			
		for (int i = 0; i < mCount; i++) {						//mCount
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

	@Override
	public void onDestroy() {
		// In onDestroy() you should tear down anything that was setup for your
		// data source,
		// eg. cursors, connections, etc.
		mWidgetItems.clear();
	}

	@Override
	public int getCount() {
		return mCount;
	}

	@Override
	public RemoteViews getViewAt(int position) {
		// position will always range from 0 to getCount() - 1.

		// We construct a remote views item based on our widget item xml
		// file, and set the
		// text based on the position.
		RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
		ShapeDrawable sd1 = new ShapeDrawable(new RectShape());
		sd1.getPaint().setColor(0xFFFFFFFF);
		sd1.getPaint().setStyle(Style.STROKE);
		sd1.getPaint().setStrokeWidth(1);
		Canvas c = new Canvas();
		sd1.draw(c);
		Bitmap b =Bitmap.createBitmap(120, 120, Bitmap.Config.ARGB_8888);
				c.drawBitmap(b, 0, 0, sd1.getPaint());
		// Next, we set a fill-intent which will be used to fill-in the
		// pending intent template
		// which is set on the collection view in StackWidgetProvider.
				
		
			
				
		if (ALLPREFERENCES != null && ALLPREFERENCES[position] != null) {   //ALLPREFERENCES è dimensionato come le preferenze selezionate
			
			
			BookmarkDescriptor myDescriptor = ALLPREFERENCES[position];
	
			
			// set the widgetbutton
			if (WidgetHelper.PARAM_TYPE.equals(myDescriptor.params.get(0).name)) {	//controllare con gli input da config activity?
				String type = myDescriptor.params.get(0).value;
				if (WidgetHelper.TYPE_DT.equals(type)) {
					// dt -> put icons and hide text
					rv.setViewVisibility(R.id.text_widget_item, View.GONE);
					rv.setImageViewResource(R.id.image_widget_item, Integer.parseInt(myDescriptor.params.get(2).value));

					rv.setInt(R.id.image_widget_item, "setBackgroundResource", R.drawable.rounded_border_dt);

				} else if (WidgetHelper.TYPE_JP.equals(type)) {
					// jp -> put text and hide icon

					rv.setViewVisibility(R.id.image_widget_item, View.GONE);
					// rv.setImageViewBitmap(R.id.text_widget_item,
					// getBackground(Color.parseColor("#6EB046")));
					if (!( "0".equals(myDescriptor.params.get(2).value )))
					{
						//bus
						rv.setTextViewTextSize(R.id.text_widget_item, TypedValue.COMPLEX_UNIT_DIP, 80);

						rv.setTextViewText(R.id.text_widget_item, RoutesHelper.getShortNameByRouteIdAndAgencyID(
								myDescriptor.params.get(4).value, myDescriptor.params.get(3).value));	//4,3
						rv.setTextColor(R.id.text_widget_item,Integer.parseInt(myDescriptor.params.get(2).value));//rv.setInt(R.id.text_widget_item, "setBackgroundColor",
								//Integer.parseInt(myDescriptor.params.get(2).value));
						rv.setInt(R.id.text_widget_item, "setBackgroundResource", R.drawable.rounded_border_jp);

					}
					else
					{
						//train
//						rv.setTextViewTextSize(R.id.text_widget_item, TypedValue.COMPLEX_UNIT_DIP, 12);
						//controllo contesto getRouteDescriptor i vari parametri
						rv.setTextViewText(R.id.text_widget_item, mContext.getString(RoutesHelper.getRouteDescriptorByRouteId( myDescriptor.params.get(3).value,myDescriptor.params.get(4).value).getNameResource()));
						rv.setTextColor(R.id.text_widget_item,Color.BLACK);//Int(R.id.text_widget_item, "setBackgroundColor",Color.BLACK);
						rv.setInt(R.id.text_widget_item, "setBackgroundResource", R.drawable.rounded_border_jp);

					}

				} else if (WidgetHelper.TYPE_JP_PARKINGS.equals(type)) {
					// jp parcheggio -> put text and hide icon
					rv.setViewVisibility(R.id.text_widget_item, View.GONE);
					rv.setImageViewResource(R.id.image_widget_item, Integer.parseInt(myDescriptor.params.get(2).value));
					rv.setInt(R.id.image_widget_item, "setBackgroundResource", R.drawable.rounded_border_jp);

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
				rv.setOnClickFillInIntent(R.id.layout, fillInIntent);
			}

		}
		// Return the remote views object.
		// AppWidgetManager manager = AppWidgetManager.getInstance(mContext);
		// manager.updateAppWidget(thisWidget, rv);
		return rv;
	}

	@Override
	public RemoteViews getLoadingView() {
		// You can create a custom loading view (for instance when getViewAt()
		// is slow.) If you
		// return null here, you will get the default loading view.
		return null;
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
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

	// public static Bitmap getBackground(int bgColor, int width, int height,
	// Context context) {
	// try {
	// // convert to HSV to lighten and darken
	// int alpha = Color.alpha(bgColor);
	// float[] hsv = new float[3];
	// Color.colorToHSV(bgColor, hsv);
	// hsv[2] -= .1;
	// int darker = Color.HSVToColor(alpha, hsv);
	// hsv[2] += .3;
	// int lighter = Color.HSVToColor(alpha, hsv);
	//
	// // create gradient useng lighter and darker colors
	// GradientDrawable gd = new GradientDrawable(
	// GradientDrawable.Orientation.LEFT_RIGHT,new int[] { darker, lighter});
	// gd.setGradientType(GradientDrawable.RECTANGLE);
	// // set corner size
	// gd.setCornerRadii(new float[] {4,4,4,4,4,4,4,4});
	//
	// // get density to scale bitmap for device
	// float dp = context.getResources().getDisplayMetrics().density;
	//
	// // create bitmap based on width and height of widget
	// Bitmap bitmap = Bitmap.createBitmap(Math.round(width * dp),
	// Math.round(height * dp),
	// Bitmap.Config.ARGB_8888);
	// Canvas canvas = new Canvas(bitmap);
	// gd.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
	// gd.draw(canvas);
	// return bitmap;
	// } catch (Exception e) {
	// e.printStackTrace();
	// return null;
	// }
	// }

	public static Bitmap getBackground(int bgcolor) {
		try {
			Bitmap.Config config = Bitmap.Config.ARGB_8888; // Bitmap.Config.ARGB_8888
															// Bitmap.Config.ARGB_4444
															// to be used as
															// these two config
															// constant supports
															// transparency
			Bitmap bitmap = Bitmap.createBitmap(2, 2, config); // Create a
																// Bitmap

			Canvas canvas = new Canvas(bitmap); // Load the Bitmap to the Canvas
			canvas.drawColor(bgcolor); // Set the color

			return bitmap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
