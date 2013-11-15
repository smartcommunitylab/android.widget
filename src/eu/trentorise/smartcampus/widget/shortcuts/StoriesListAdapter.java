package eu.trentorise.smartcampus.widget.shortcuts;

import java.util.ArrayList;
import java.util.List;

import com.actionbarsherlock.app.SherlockFragment;

import eu.trentorise.smartcampus.dt.fragments.home.EventsAdapter.ViewHolder;
import eu.trentorise.smartcampus.widget.R;
import eu.trentorise.smartcampus.widget.shortcuts.WidgetHelper.BookmarkDescriptor;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StoriesListAdapter extends ArrayAdapter implements OnCheckedChangeListener{


	private int resource;
    private LayoutInflater inflater;
    private Context context;
    Editor editor;
    ViewHolder holder;
    SherlockFragment contextsherlock;
    //lista di bookmarks    
    //private ArrayList <BookmarkDescriptor> bklist;
    private List <BookmarkDescriptor> bklist;
    public boolean[] checkBoxStateStories = new boolean[5];

    
    public StoriesListAdapter ( Context ctx, int resourceId, List<BookmarkDescriptor> objects, boolean []check) {

        super(ctx, resourceId, objects);
        resource = resourceId;
        inflater = LayoutInflater.from(ctx);
        context=ctx;
        bklist=objects;
        checkBoxStateStories=check;
    }
    
   
	
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	

	

// METODO GETVIEW DOVE VADO A PRENDERE IL PARAMETRI DI BOOKMARKS
  
    @Override
    public View getView ( final int position, View convertView, ViewGroup parent ) {
    	
    	TextView name;
    	ImageView image;
    	CheckBox checkBox;
    	
    
    	if (convertView == null) {
    		convertView = (RelativeLayout) inflater.inflate( R.layout.row_element, parent, false );
    		holder= new ViewHolder();
    		holder.name = (TextView)convertView.findViewById(R.id.name);
    		holder.checkBox=(CheckBox)convertView.findViewById(R.id.checkBox);
    		convertView.setTag(holder);
    	}else{
    		holder=(ViewHolder)convertView.getTag();
    	}
    	
    
    	
    	holder.name.setText(bklist.get(position).getName().toString());
    	holder.checkBox.setChecked(checkBoxStateStories[position]);
    	
    	
    	holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    	      
    	      checkBoxStateStories[position] = isChecked;
               if(checkBoxStateStories[position])
               {
                   holder.checkBox.setChecked(true);
               }
               else
               {
                   holder.checkBox.setChecked(false);
               }
         	  save(checkBoxStateStories);
    	       }});
    
    	
    	//boolean item[]=load();
    	//holder.checkBox.setChecked(item[position]);
    	
    	/*
    	checkBox.setOnClickListener(new View.OnClickListener() {
    	     
    		   public void onClick(View v) {
    		    if(((CheckBox)v).isChecked())
    		     checkBoxStateStories[position]=true;
    		    else
    		     checkBoxStateStories[position]=false;   
    		    }
    		   });
    	*/
    	return convertView;
    	
    }

    
    
    static class ViewHolder {
        TextView name;
        CheckBox checkBox;
    }

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		
	}

	private void save(final boolean[] isChecked) {
		SharedPreferences sharedPreferences = context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
	    SharedPreferences.Editor editor = sharedPreferences.edit();
	    for(Integer i=0;i<isChecked.length;i++)
	     {
	         editor.putBoolean(i.toString(), isChecked[i]);
	     }
	    editor.commit();
	    }
	

}