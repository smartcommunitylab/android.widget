package eu.trentorise.smartcampus.widget.shortcuts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;

import com.actionbarsherlock.app.SherlockFragment;

import eu.trentorise.smartcampus.jp.custom.data.SmartLine;
import eu.trentorise.smartcampus.jp.helper.RoutesHelper;
import eu.trentorise.smartcampus.widget.R;

public class WidgetHelper {
	public static Context ctx;
	public BookmarkDescriptor[] DTBOOKMARKS=null;
	public BookmarkDescriptor[] JPBOOKMARKSBUS=null;
	public BookmarkDescriptor[] JPBOOKMARKSTRAINS=null;
	public BookmarkDescriptor[] JPBOOKMARKSPARK=null;

	public WidgetHelper(Context ctx) {
		this.ctx = ctx;
		initBookmarks();
	}

	private void initBookmarks() {
		initDTBookmarks();
		initJPBookmarks();
	}

	private void initJPBookmarks() {
		/*put params for bus lines*/

		List<SmartLine> busLines=null;
		String[] agencyIds = new String[] { RoutesHelper.AGENCYID_BUS_ROVERETO, RoutesHelper.AGENCYID_BUS_TRENTO  };


		for (int i = 0; i<agencyIds.length;i++){
			busLines = RoutesHelper.getSmartLines(ctx, agencyIds[i]);
			JPBOOKMARKSBUS= initjpbookmarklines(R.string.real_time_info_bus_intent_action,agencyIds[i], busLines);

		}
		
		agencyIds = new String[] { RoutesHelper.AGENCYID_TRAIN_BZVR, RoutesHelper.AGENCYID_TRAIN_TM,
				RoutesHelper.AGENCYID_TRAIN_TNBDG };
		for (int i = 0; i<agencyIds.length;i++){
			busLines = RoutesHelper.getSmartLines(ctx, agencyIds[i]);
			JPBOOKMARKSTRAINS = initjpbookmarklines(R.string.real_time_info_train_intent_action,agencyIds[i], busLines);

		}

		/*put params for parking*/
		
	}

	private BookmarkDescriptor[] initjpbookmarklines(int action, final String agencyId, List<SmartLine> busLines) {
		List<BookmarkDescriptor> lines = new ArrayList<WidgetHelper.BookmarkDescriptor>();
		for (final SmartLine line: busLines)
		{
			lines.add(new BookmarkDescriptor(action,  new ArrayList<Param>() {
				{
				add(new Param("AGENCY_ID", agencyId));
				add(new Param("ROUTE_ID", line.getRouteID().get(0)));

			}}));
		}
		return lines.toArray(new BookmarkDescriptor[lines.size()]);
	}



	private void initDTBookmarks() {
		DTBOOKMARKS = new BookmarkDescriptor[] { new BookmarkDescriptor(R.string.places_intent_action, null),
				new BookmarkDescriptor(R.string.places_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.museum_category)));

					}
				}), new BookmarkDescriptor(R.string.places_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.mobility_category)));

					}
				}), new BookmarkDescriptor(R.string.places_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.parking_category)));

					}
				}), new BookmarkDescriptor(R.string.places_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.service_category)));

					}
				}), new BookmarkDescriptor(R.string.places_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.theater_category)));

					}
				}), new BookmarkDescriptor(R.string.places_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.university_category)));

					}
				}), new BookmarkDescriptor(R.string.places_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.hotel_category)));

					}
				}), new BookmarkDescriptor(R.string.places_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.library_category)));

					}
				}), new BookmarkDescriptor(R.string.places_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.restaurant_category)));

					}
				}), new BookmarkDescriptor(R.string.places_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.bar_category)));

					}
				}), new BookmarkDescriptor(R.string.places_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.cinema_category)));

					}
				}), new BookmarkDescriptor(R.string.places_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.family_category)));

					}
				}), new BookmarkDescriptor(R.string.places_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.other_poi_category)));

					}
				}), new BookmarkDescriptor(R.string.stories_intent_action, null),

				new BookmarkDescriptor(R.string.stories_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.leisure_category)));
					}
				}), new BookmarkDescriptor(R.string.stories_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.office_category)));
					}
				}), new BookmarkDescriptor(R.string.stories_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.university_category)));
					}
				}), new BookmarkDescriptor(R.string.stories_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.culture_category)));
					}
				}), new BookmarkDescriptor(R.string.stories_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.other_story_category)));
					}
				}), new BookmarkDescriptor(R.string.events_intent_action, null),

				new BookmarkDescriptor(R.string.events_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.concert_category)));
					}
				}), new BookmarkDescriptor(R.string.events_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.happy_hour_category)));
					}
				}), new BookmarkDescriptor(R.string.events_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.movies_category)));
					}
				}), new BookmarkDescriptor(R.string.events_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.parties_category)));
					}
				}), new BookmarkDescriptor(R.string.events_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.seminar_category)));
					}
				}), new BookmarkDescriptor(R.string.events_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.theaters_category)));
					}
				}), new BookmarkDescriptor(R.string.events_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.exhibitions_category)));
					}
				}), new BookmarkDescriptor(R.string.events_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.family_category)));
					}
				}), new BookmarkDescriptor(R.string.events_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.other_category)));
					}
				}), new BookmarkDescriptor(R.string.events_intent_action, new ArrayList<Param>() {
					{
						add(new Param("CATEGORY", ctx.getString(R.string.today_category)));
					}
				}), new BookmarkDescriptor(R.string.map_intent_action, new ArrayList<Param>() {
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
