

package eu.trentorise.smartcampus.widget.shortcuts;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;



import com.actionbarsherlock.view.MenuItem;

import eu.trentorise.smartcampus.widget.R;

public class ConfigurationActivity extends SherlockFragmentActivity {
        int mAppWidgetId;
        private WidgetHelper helper ;
        private int widgetID;
        Button AddWidget;
    
        // Dichiaro le variabili
        DrawerLayout mDrawerLayout;
        ListView mDrawerList;
        ActionBarDrawerToggle mDrawerToggle;
        MenuListAdapter mMenuAdapter;
        String[] title;
        String[] subtitle;
        int[] icon;
        
        FragITR fragitr = new FragITR();
        FragResume fragresume = new FragResume();
        Fragment6 fragment6 = new Fragment6();
        FragmentGuida fragmentguida = new FragmentGuida();
        FragPoi fragpoi = new FragPoi();
        FragEvent fragevent = new FragEvent();
        FragStories fragstories = new FragStories();
                
        
        private CharSequence mDrawerTitle;
        private CharSequence mTitle;
    
        @Override
        protected void onStart() {
                super.onStart();
         //INIZIO PARTE MIA
              
                initSharedPreference();		//Inizializzo preferenze
                
                
         // Get the Title
                        mTitle = mDrawerTitle = getTitle();

                        // Generate title
                        title = new String[] { "Luoghi", "Eventi",
                                        "Storie", "Info in tempo reale", "Riassunto"};

                        // Generate subtitle
                        subtitle = new String[] { "I luoghi di Trento", "I migliori Eventi",
                                        "Le tue storie da vivere", "Le informazioni aggiornate ogni secondo", "Le tue scelte fino ad ora"};

                        // Generate icon
                        icon = new int[] { R.drawable.action_about, R.drawable.action_settings,
                                        R.drawable.collections_cloud, R.drawable.ic_position, R.drawable.ic_start};

                        // Locate DrawerLayout in drawer_main.xml
                        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

                        // Locate ListView in drawer_main.xml
                        mDrawerList = (ListView) findViewById(R.id.listview_drawer);

                        // Set a custom shadow that overlays the main content when the drawer
                        // opens
                        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                                        GravityCompat.START);

                        // Pass string arrays to MenuListAdapter
                        mMenuAdapter = new MenuListAdapter(ConfigurationActivity.this, title, subtitle,
                                        icon);

                        // Set the MenuListAdapter to the ListView
                        mDrawerList.setAdapter(mMenuAdapter);

                        // Capture listview menu item click
                        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

                        // Enable ActionBar app icon to behave as action to toggle nav drawer
                        getSupportActionBar().setHomeButtonEnabled(true);
                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                        // ActionBarDrawerToggle ties together the the proper interactions
                        // between the sliding drawer and the action bar app icon
                        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                                        R.drawable.ic_drawer, R.string.drawer_open,
                                        R.string.drawer_close) {

                                @Override
                                public void onDrawerClosed(View view) {
                                        // TODO Auto-generated method stub
                                        super.onDrawerClosed(view);
                                }

                                @Override
                                public void onDrawerOpened(View drawerView) {
                                        // TODO Auto-generated method stub
                                        // Set the title on the action when drawer open
                                        getSupportActionBar().setTitle(mDrawerTitle);
                                        super.onDrawerOpened(drawerView);
                                }
                        };
        
                        
                        
                        mDrawerLayout.setDrawerListener(mDrawerToggle);

//                        if (savedInstanceState == null) {
//                                selectItem(0);
//                        }
        }
         @Override
         public void onCreate(Bundle savedInstanceState)
         {
         super.onCreate(savedInstanceState);
         // Set the result to CANCELED. This will cause the widget host to cancel
         // out of the widget placement if they press the back button.
         setResult(RESULT_CANCELED);

         // Set the view layout resource to use.
         setContentView(R.layout.drawer_main);
        
         //Setto il fragment iniziale
         FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
         ft.replace(R.id.content_frame, fragmentguida);
         ft.commit();
        
         /*
         // Find the EditText
         AddWidget = (Button)findViewById(R.id.vai);

         // Bind the action for the save button.
         findViewById(R.id.vai).setOnClickListener(mOnClickListener);
         */
         
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
         
         helper = new WidgetHelper(this);
                }
        
        
         @Override
        public void onAttachFragment(android.app.Fragment fragment) {
                // TODO Auto-generated method stub
                super.onAttachFragment(fragment);
                
                setContentView(R.layout.fragmentguida);
        }

                @Override
                public boolean onOptionsItemSelected(MenuItem item) {

                        if (item.getItemId() == android.R.id.home) {

                                if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                                        mDrawerLayout.closeDrawer(mDrawerList);
                                } else {
                                        mDrawerLayout.openDrawer(mDrawerList);
                                }
                        }

                        return super.onOptionsItemSelected(item);
                }

                // ListView click listener in the navigation drawer
                private class DrawerItemClickListener implements
                                ListView.OnItemClickListener {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                                selectItem(position);
                        }
                }

                private void selectItem(int position) {

                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        // Locate Position
                        switch (position) {
                        case 0:
                                ft.replace(R.id.content_frame, fragpoi);
                                break;
                        case 1:
                                ft.replace(R.id.content_frame, fragevent);
                                break;
                        case 2:
                                ft.replace(R.id.content_frame, fragstories);
                                break;
                        case 3:
                                ft.replace(R.id.content_frame, fragitr);
                                break;
                        case 4:
                                ft.replace(R.id.content_frame, fragresume);
                                break;
                        default:
                                ft.replace(R.id.content_frame, fragmentguida);
                        }

                        
                        ft.commit();
                        mDrawerList.setItemChecked(position, true);

                        // Get the title followed by the position
                        setTitle(title[position]);
                        // Close drawer
                        mDrawerLayout.closeDrawer(mDrawerList);
                }

                @Override
                protected void onPostCreate(Bundle savedInstanceState) {
                        super.onPostCreate(savedInstanceState);
                        // Sync the toggle state after onRestoreInstanceState has occurred.
                        mDrawerToggle.syncState();
                }

                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                        super.onConfigurationChanged(newConfig);
                        // Pass any configuration change to the drawer toggles
                        mDrawerToggle.onConfigurationChanged(newConfig);
                }

                @Override
                public void setTitle(CharSequence title) {
                        mTitle = title;
                        getSupportActionBar().setTitle(mTitle);
                }
                
        		public void initSharedPreference(){
        			
        			SharedPreferences sharedPreferencesParcheggi = getSharedPreferences("sharedPrefsParcheggi", Context.MODE_PRIVATE);
        			SharedPreferences sharedPreferencesStories = getSharedPreferences("sharedPrefsStories", Context.MODE_PRIVATE);
        			SharedPreferences sharedPreferencesPOI = getSharedPreferences("sharedPrefsPOI", Context.MODE_PRIVATE);
        			SharedPreferences sharedPreferencesAutobus = getSharedPreferences("sharedPrefsAutobus", Context.MODE_PRIVATE);
        			SharedPreferences sharedPreferencesTreni = getSharedPreferences("sharedPrefsTreni", Context.MODE_PRIVATE);
        			SharedPreferences sharedPreferencesEventi = getSharedPreferences("sharedPrefsEvents", Context.MODE_PRIVATE);
        			//SharedPreferences sharedPreferencesTot = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        			
        			sharedPreferencesParcheggi.edit().clear().commit();
        			sharedPreferencesStories.edit().clear().commit();
        			sharedPreferencesPOI.edit().clear().commit();
        			sharedPreferencesAutobus.edit().clear().commit();
        			sharedPreferencesTreni.edit().clear().commit();
        			sharedPreferencesEventi.edit().clear().commit();
        			//sharedPreferencesTot.edit().clear().commit();
        		}
        		
        		public void prova() {
        			Toast.makeText(getApplicationContext(), "this is my Toast message!!! =)",
        					   Toast.LENGTH_LONG).show();
        		}
                
                // {
                        //FINE PARTE MIA
                        
                        

                        
           /*
                        
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
//         AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
//         SmartCampusShortCuts.updateAppWidget(context, appWidgetManager,
//         mAppWidgetId, null);

         // Make sure we pass back the original appWidgetId
         WidgetHelper helper = new WidgetHelper(context);
                 String[] DTPREFERENCES;
                 String[] JPPREFERENCES;
                 */
                         /* Load prefix values */
                        /*
                         DTPREFERENCES = new String[2];
                         DTPREFERENCES[0] =JSONSharedPreferences.convertToJSON(helper.DTBOOKMARKS[0]);
                         DTPREFERENCES[1] =JSONSharedPreferences.convertToJSON(helper.DTBOOKMARKS[1]);                
                         JPPREFERENCES = new String[4];
                         JPPREFERENCES[0] =JSONSharedPreferences.convertToJSON(helper.JPBOOKMARKS[0]);
                         JPPREFERENCES[1] =JSONSharedPreferences.convertToJSON(helper.JPBOOKMARKS[1]);        
                         JPPREFERENCES[2] =JSONSharedPreferences.convertToJSON(helper.JPBOOKMARKS[2]);
                         JPPREFERENCES[3] =JSONSharedPreferences.convertToJSON(helper.JPBOOKMARKS[3]);        
                         JSONSharedPreferences.saveJSONArray(context, "WIDGET", "DTPREFERENCES",new JSONArray(Arrays.asList(DTPREFERENCES)));
                         JSONSharedPreferences.saveJSONArray(context, "WIDGET", "JPPREFERENCES",new JSONArray(Arrays.asList(JPPREFERENCES)));

                         Intent resultValue = new Intent();
         resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
         setResult(RESULT_OK, resultValue);
         finish();
         }
         };
        
*/

}

