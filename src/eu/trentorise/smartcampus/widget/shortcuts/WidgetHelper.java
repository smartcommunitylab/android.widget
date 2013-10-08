package eu.trentorise.smartcampus.widget.shortcuts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.actionbarsherlock.app.SherlockFragment;

import eu.trentorise.smartcampus.jp.custom.data.SmartLine;
import eu.trentorise.smartcampus.jp.helper.RoutesHelper;
import eu.trentorise.smartcampus.jp.model.RouteDescriptor;
import eu.trentorise.smartcampus.widget.R;

public class WidgetHelper {
	public static Context ctx;
	public static final String PARAM_TYPE = "TYPE";
	public static final String PARAM_COLOR = "COLOR";
	public static final String PARAM_RESOURCE = "RESOURCE";
	public static final String PARAM_AGENCY_ID = "AGENCY_ID";
	public static final String PARAM_ROUTE_ID = "ROUTE_ID";
	public static final String PARAM_CATEGORY = "CATEGORY";

	public static final String TYPE_DT = "TYPE_DT";
	public static final String TYPE_JP = "TYPE_JP";
	public static final String TYPE_JP_PARKINGS = "TYPE_JP_PARKINGS";

	public BookmarkDescriptor[] DTBOOKMARKS = null;
	public BookmarkDescriptor[] JPBOOKMARKSBUS = null;
	public BookmarkDescriptor[] JPBOOKMARKSTRAINS = null;
	public BookmarkDescriptor[] JPBOOKMARKSPARK = null;
	public BookmarkDescriptor[] JPBOOKMARKS = null;
	public BookmarkDescriptor[] ALLBOOKMARKS = null;

	public WidgetHelper(Context ctx) {
		this.ctx = ctx;
		initBookmarks();
	}

	private void initBookmarks() {
		initDTBookmarks();
		initJPBookmarks();
	}

	private void initJPBookmarks() {
		/* put params for bus lines */

		List<SmartLine> busLines = null;
		List<SmartLine> trainLines = null;
		String[] agencyIds = new String[] { RoutesHelper.AGENCYID_BUS_ROVERETO, RoutesHelper.AGENCYID_BUS_TRENTO };

		for (int i = 0; i < agencyIds.length; i++) {
			busLines = RoutesHelper.getSmartLines(ctx, agencyIds[i]);
			JPBOOKMARKSBUS = initjpbookmarklines(ctx.getString(R.string.real_time_info_bus_intent_action),
					agencyIds[i], busLines);

		}

		agencyIds = new String[] { RoutesHelper.AGENCYID_TRAIN_BZVR, RoutesHelper.AGENCYID_TRAIN_TM,
				RoutesHelper.AGENCYID_TRAIN_TNBDG };
		for (int i = 0; i < agencyIds.length; i++) {
			List<RouteDescriptor> trainLinesDescriptors = RoutesHelper.getRouteDescriptorsList(agencyIds);
			trainLines = new ArrayList<SmartLine>();
			for (RouteDescriptor route : trainLinesDescriptors) {
				trainLines.add(new SmartLine(null, route.getAgencyId(), 0, null, null,
						Arrays.asList(route.getRouteId())));
			}
			// trainLines = RoutesHelper.getSmartLines(ctx, agencyIds[i]);

			JPBOOKMARKSTRAINS = initjpbookmarklines(ctx.getString(R.string.real_time_info_train_intent_action),
					agencyIds[i], trainLines);

		}
		JPBOOKMARKSPARK = new BookmarkDescriptor[1];
		JPBOOKMARKSPARK[0] = new BookmarkDescriptor(ctx.getString(R.string.real_time_info_parking_intent_action),
				ctx.getString(R.string.widget_poi_parking), new ArrayList<Param>() {
					{
						add(new Param(PARAM_TYPE, TYPE_JP_PARKINGS));
						add(new Param(PARAM_COLOR, Integer.toString(R.color.jpappcolor)));
						add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_p_parkings)));

					}
				});

		JPBOOKMARKS = concat(JPBOOKMARKSBUS, JPBOOKMARKSTRAINS);
		JPBOOKMARKS = concat(JPBOOKMARKS, JPBOOKMARKSPARK);
	}

	private BookmarkDescriptor[] initjpbookmarklines(String action, final String agencyId, List<SmartLine> busLines) {
		List<BookmarkDescriptor> lines = new ArrayList<WidgetHelper.BookmarkDescriptor>();
		for (final SmartLine line : busLines) {
			
			String name = "";
			if (line.getRoutesShorts().size() > 0)
				name = line.getRoutesShorts().get(0);
			lines.add(new BookmarkDescriptor(action, name, new ArrayList<Param>() {
				{
					add(new Param(PARAM_TYPE, TYPE_JP));
					add(new Param(PARAM_COLOR, Integer.toString(R.color.jpappcolor)));
					add(new Param(PARAM_RESOURCE, Integer.toString(line.getColor())));
					add(new Param(PARAM_AGENCY_ID, agencyId));
					add(new Param(PARAM_ROUTE_ID, line.getRouteID().get(0)));

				}
			}));
		}
		return lines.toArray(new BookmarkDescriptor[lines.size()]);
	}

	private void initDTBookmarks() {
		DTBOOKMARKS = new BookmarkDescriptor[] {
				new BookmarkDescriptor(ctx.getString(R.string.places_intent_action),
						ctx.getString(R.string.tab_places), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_p_other)));

							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.places_intent_action),
						ctx.getString(R.string.categories_poi_museum), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_p_museums)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.museum_category)));

							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.places_intent_action),
						ctx.getString(R.string.categories_poi_mobility), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_p_mobility)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.mobility_category)));

							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.places_intent_action),
						ctx.getString(R.string.categories_poi_parking), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_p_parkings)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.parking_category)));

							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.places_intent_action),
						ctx.getString(R.string.categories_poi_office), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_p_offices)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.service_category)));

							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.places_intent_action),
						ctx.getString(R.string.categories_poi_theater), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_p_theaters)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.theater_category)));

							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.places_intent_action),
						ctx.getString(R.string.categories_poi_university), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_p_university)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.university_category)));

							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.places_intent_action),
						ctx.getString(R.string.categories_poi_accommodation), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_p_accomodation)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.hotel_category)));

							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.places_intent_action),
						ctx.getString(R.string.categories_poi_library), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_p_libraries)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.library_category)));

							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.places_intent_action),
						ctx.getString(R.string.categories_poi_food), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_p_food)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.restaurant_category)));

							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.places_intent_action),
						ctx.getString(R.string.categories_poi_drink), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_p_drink)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.bar_category)));

							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.places_intent_action),
						ctx.getString(R.string.categories_poi_cinema), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_p_cinemas)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.cinema_category)));

							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.places_intent_action),
						ctx.getString(R.string.categories_poi_family), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_p_family)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.family_category)));

							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.places_intent_action), "Other POI",
						new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_p_other)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.other_poi_category)));

							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.stories_intent_action),
						ctx.getString(R.string.tab_stories), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_s_other)));

							}
						}),

				new BookmarkDescriptor(ctx.getString(R.string.stories_intent_action),
						ctx.getString(R.string.categories_story_leisure), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_s_leisure)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.leisure_category)));
							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.stories_intent_action),
						ctx.getString(R.string.categories_story_offices_and_services), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_s_organizations)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.office_category)));
							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.stories_intent_action),
						ctx.getString(R.string.categories_story_university), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_s_university)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.university_category)));
							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.stories_intent_action),
						ctx.getString(R.string.categories_story_culture), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_s_cultural)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.culture_category)));
							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.stories_intent_action), "Other Story",
						new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_s_other)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.other_story_category)));
							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.events_intent_action),
						ctx.getString(R.string.tab_places), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_e_other)));
							}
						}),

				new BookmarkDescriptor(ctx.getString(R.string.events_intent_action),
						ctx.getString(R.string.categories_event_concert), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_e_concerts)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.concert_category)));
							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.events_intent_action),
						ctx.getString(R.string.categories_event_happyhour), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_e_happyhours)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.happy_hour_category)));
							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.events_intent_action),
						ctx.getString(R.string.categories_event_movie), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_e_movies)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.movies_category)));
							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.events_intent_action),
						ctx.getString(R.string.categories_event_party), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_e_parties)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.parties_category)));
							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.events_intent_action),
						ctx.getString(R.string.categories_event_seminar), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_e_seminars)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.seminar_category)));
							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.events_intent_action),
						ctx.getString(R.string.categories_event_concert), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_e_movies)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.theaters_category)));
							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.events_intent_action),
						ctx.getString(R.string.categories_event_exhibition), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_e_performances)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.exhibitions_category)));
							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.events_intent_action),
						ctx.getString(R.string.categories_event_family), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_e_family)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.family_category)));
							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.events_intent_action), "Other events",
						new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_e_other)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.other_category)));
							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.events_intent_action),
						ctx.getString(R.string.categories_event_today), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_e_todaysevents)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.today_category)));
							}
						}),
				new BookmarkDescriptor(ctx.getString(R.string.map_intent_action),
						ctx.getString(R.string.categories_event_my), new ArrayList<Param>() {
							{
								add(new Param(PARAM_TYPE, TYPE_DT));
								add(new Param(PARAM_COLOR, Integer.toString(R.color.dtappcolor)));
								add(new Param(PARAM_RESOURCE, Integer.toString(R.drawable.ic_e_myevents)));
								add(new Param(PARAM_CATEGORY, ctx.getString(R.string.my_category)));
							}
						}) };
	}

	public static class BookmarkDescriptor implements Parcelable {
		public String intent;
		public String name;
		public List<Param> params;

		public BookmarkDescriptor() {
		}

		public BookmarkDescriptor(String intent, String name, List<Param> params) {
			super();
			this.intent = intent;
			this.name = name;
			this.params = params;
		}

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {

		}

		public String getIntent() {
			return intent;
		}

		public void setIntent(String intent) {
			this.intent = intent;
		}

		public List<Param> getParams() {
			return params;
		}

		public void setParams(List<Param> params) {
			this.params = params;
		}

	}

	public static class Param implements Parcelable {
		public String value;
		public String name;

		public Param() {
		}

		public Param(String name, String value) {
			this.name = name;
			this.value = value;
		}

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {

		}
	}

	public static BookmarkDescriptor[] concat(BookmarkDescriptor[] A, BookmarkDescriptor[] B) {
		int aLen = A.length;
		int bLen = B.length;
		BookmarkDescriptor[] C = new BookmarkDescriptor[aLen + bLen];
		System.arraycopy(A, 0, C, 0, aLen);
		System.arraycopy(B, 0, C, aLen, bLen);
		return C;
	}
}
