package eu.trentorise.smartcampus.widget.shortcuts;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;

import eu.trentorise.smartcampus.widget.R;

public class FragITR extends SherlockFragment 
{
	

	FragAutobus fragautobus = new FragAutobus();
	FragTreni fragtreni = new FragTreni();
	FragParcheggi fragparcheggi = new FragParcheggi();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragitr, container, false);
		return rootView;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		ListView listView = (ListView) getSherlockActivity().findViewById(
				R.id.fragment4);

		ArrayList<String> scelte = new ArrayList<String>();

		scelte.add("Orario Autobus");
		scelte.add("Orario Treni");
		scelte.add("Parcheggi");

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getSherlockActivity(), android.R.layout.simple_list_item_1,
				scelte);
		
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new DrawerItemClickListener());

	}
	

	
	
	private class DrawerItemClickListener implements 
	ListView.OnItemClickListener {
		@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
				selectItem(position);
	}
		
}
	
	
	private void selectItem(int position) {

		FragmentTransaction ft = getSherlockActivity().getSupportFragmentManager().beginTransaction();
		
		switch (position) {
		case 0:
			ft.replace(R.id.content_frame, fragautobus);
			break;
		case 1:
			ft.replace(R.id.content_frame, fragtreni);
			break;
		case 2:
			ft.replace(R.id.content_frame, fragparcheggi);
			break;
		}

		ft.commit();	
}
}