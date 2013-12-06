package eu.trentorise.smartcampus.widget.shortcuts;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.internal.cj;

import eu.trentorise.smartcampus.widget.R;
import eu.trentorise.smartcampus.widget.shortcuts.StoriesListAdapter.ViewHolder;
import eu.trentorise.smartcampus.widget.shortcuts.WidgetHelper.BookmarkDescriptor;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ResumeListAdapter extends ArrayAdapter{


	private int resource;
    private LayoutInflater inflater;
    private Context context;
    //lista di bookmarks    
    //private ArrayList <BookmarkDescriptor> bklist;
    private final List <BookmarkDescriptor> bklist;
    private final List <String> bkdirectionlist;
    //mi contiene tutti i cliccati o no, potrei passarla al widget
    public boolean[] checkBoxStateResume;
    ViewHolder holder;
    
    public ResumeListAdapter ( Context ctx, int resourceId, List<BookmarkDescriptor> objects, List<String> direction, boolean[] check) {

        super(ctx, resourceId, objects);
        resource = resourceId;
        inflater = LayoutInflater.from(ctx);
        context = ctx;
        bklist = objects;
        bkdirectionlist = direction;
        checkBoxStateResume = check;  
        
    }
    
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	

// METODO GETVIEW DOVE VADO A PRENDERE IL PARAMETRI DI BOOKMARKS
  
    @Override
    public View getView ( final int position, View convertView, ViewGroup parent ) {

  
    	if (convertView == null) {
    		convertView = (RelativeLayout) inflater.inflate( R.layout.row_element, parent, false );
    		holder= new ViewHolder();
    		holder.name = (TextView)convertView.findViewById(R.id.name);
    		holder.direction= (TextView)convertView.findViewById(R.id.direction);
    		holder.checkBox=(CheckBox) convertView.findViewById(R.id.checkBox);
    
    	
    		holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            
    			@Override
    			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    				int getPosition = (Integer) buttonView.getTag();  // Here we get the position that we have set for the checkbox using setTag.
    				bklist.get(getPosition).setSelected(buttonView.isChecked()); // Set the value of checkbox to maintain its state.
    				  
    				checkBoxStateResume[position] = isChecked;  
    				save(checkBoxStateResume);
    	               
    			}
    		});
    	
    	
    		convertView.setTag(holder);
    		convertView.setTag(R.id.name, holder.name);
    		convertView.setTag(R.id.direction, holder.direction);
    		convertView.setTag(R.id.checkBox, holder.checkBox);
    		
        	} else {
        		
        		holder = (ViewHolder) convertView.getTag();
        	}
    	
    		holder.checkBox.setTag(position); // This line is important.
    
    		holder.name.setText(bklist.get(position).getName());
    		holder.direction.setText(bkdirectionlist.get(position));
    		holder.checkBox.setChecked(bklist.get(position).isSelected());

    	
 
    	return convertView;
    }  
    
    
    static class ViewHolder {
        protected TextView name;
        protected TextView direction;
        protected CheckBox checkBox;
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