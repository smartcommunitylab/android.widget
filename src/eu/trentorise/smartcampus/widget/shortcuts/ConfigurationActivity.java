package eu.trentorise.smartcampus.widget.shortcuts;

import android.os.Bundle;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class ConfigurationActivity extends SherlockFragmentActivity{
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		Toast.makeText(this, "configurationactivity", Toast.LENGTH_LONG).show();
	}

}
