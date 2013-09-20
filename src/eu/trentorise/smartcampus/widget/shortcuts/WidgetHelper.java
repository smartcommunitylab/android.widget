package eu.trentorise.smartcampus.widget.shortcuts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;

import com.actionbarsherlock.app.SherlockFragment;
import eu.trentorise.smartcampus.jp.SmartCheckBusFragment;
import eu.trentorise.smartcampus.widget.R;

public class WidgetHelper {
	public static  Context ctx;
	public   BookmarkDescriptor[] DTBOOKMARKS ;
	public  BookmarkDescriptor[] JPBOOKMARKS;
	public WidgetHelper(Context ctx) {
		this.ctx=ctx;
		initBookmarks();
	}
	
	private void initBookmarks() {
		initDTBookmarks();
		initJPBookmarks();
	}

	private void initJPBookmarks() {
		
		JPBOOKMARKS = new BookmarkDescriptor[] {
				new BookmarkDescriptor(R.string.real_time_info_intent_action, new ArrayList<Param>() {
					{
						add(new Param("AGENCY_ID", ctx.getResources().getStringArray(R.array.agency_id)[3]));
						add(new Param("ROUTE_ID", ctx.getResources().getStringArray(R.array.smart_check_12_numbers)[0]));

					}
				}),
					};
	}

	private void initDTBookmarks() {
		DTBOOKMARKS = 
				new BookmarkDescriptor[] {
				
				new BookmarkDescriptor(R.string.places_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.museum_category)));

					}
				}),
				new BookmarkDescriptor(R.string.places_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.mobility_category)));

					}
				}),
				new BookmarkDescriptor(R.string.places_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.parking_category)));

					}
				}),
				new BookmarkDescriptor(R.string.places_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.service_category)));

					}
				}),
				new BookmarkDescriptor(R.string.places_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.theater_category)));

					}
				}),
				new BookmarkDescriptor(R.string.places_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.university_category)));

					}
				}),
				new BookmarkDescriptor(R.string.places_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.hotel_category)));

					}
				}),
				new BookmarkDescriptor(R.string.places_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.library_category)));

					}
				}),
				new BookmarkDescriptor(R.string.places_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.restaurant_category)));

					}
				}),
				new BookmarkDescriptor(R.string.places_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.bar_category)));

					}
				}),
				new BookmarkDescriptor(R.string.places_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.cinema_category)));

					}
				}),
				new BookmarkDescriptor(R.string.places_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.family_category)));

					}
				}),
				new BookmarkDescriptor(R.string.places_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.other_poi_category)));

					}
				}),

				new BookmarkDescriptor(R.string.stories_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.leisure_category)));
					}
				}), 
				new BookmarkDescriptor(R.string.stories_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.office_category)));
					}
				}), 
				new BookmarkDescriptor(R.string.stories_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.university_category)));
					}
				}), 
				new BookmarkDescriptor(R.string.stories_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.culture_category)));
					}
				}), 
				new BookmarkDescriptor(R.string.stories_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.other_story_category)));
					}
				}), 
				
				new BookmarkDescriptor(R.string.events_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.concert_category)));
					}
				}),
				new BookmarkDescriptor(R.string.events_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.happy_hour_category)));
					}
				}),
				new BookmarkDescriptor(R.string.events_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.movies_category)));
					}
				}),
				new BookmarkDescriptor(R.string.events_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.parties_category)));
					}
				}),
				new BookmarkDescriptor(R.string.events_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.seminar_category)));
					}
				}),
				new BookmarkDescriptor(R.string.events_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.theaters_category)));
					}
				}),
				new BookmarkDescriptor(R.string.events_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.exhibitions_category)));
					}
				}),
				new BookmarkDescriptor(R.string.events_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.family_category)));
					}
				}),
				new BookmarkDescriptor(R.string.events_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.other_category)));
					}
				}),
				new BookmarkDescriptor(R.string.events_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.today_category)));
					}
				}),
				new BookmarkDescriptor(R.string.map_intent_action, new ArrayList<Param>() {
					{
						add(new Param("FRAGMENT", ctx.getString(R.string.my_category)));
					}
				}) };
	}



	public static class BookmarkDescriptor {
		public int intent;
		public List<Param> params;

		public BookmarkDescriptor(int intent, List<Param> params) {
			super();
			this.intent = intent;
			this.params = params;
		}
	}

	public static class Param {
		public String name;
		public String value;

		public Param(String name, String value) {
			this.name = name;
			this.value = value;
		}
	}
}
