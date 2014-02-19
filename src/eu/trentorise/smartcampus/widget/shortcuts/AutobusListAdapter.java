package eu.trentorise.smartcampus.widget.shortcuts;

import java.util.List;

import eu.trentorise.smartcampus.widget.R;
import eu.trentorise.smartcampus.widget.shortcuts.WidgetHelper.BookmarkDescriptor;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AutobusListAdapter extends ArrayAdapter{


	private int resource;
    private LayoutInflater inflater;
    private Context context;
    //lista di bookmarks    
    //private ArrayList <BookmarkDescriptor> bklist;
    private List <BookmarkDescriptor> bklist;
    private List <String> bkdirectionlist;
    boolean[] checkBoxStateAutobus;
    FragResume resume = new FragResume();
    ViewHolder holder;
    
    
    public AutobusListAdapter ( Context ctx, int resourceId, List<BookmarkDescriptor> objects, List<String> direction, boolean[] check) {

        super(ctx, resourceId, objects);
        resource = resourceId;
        inflater = LayoutInflater.from(ctx);
        context = ctx;
        bklist = objects;
        bkdirectionlist = direction;
        checkBoxStateAutobus = check;  
        
    }
    

	
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	

// METODO GETVIEW DOVE VADO A PRENDERE IL PARAMETRI DI BOOKMARKS
  
    @Override
    public View getView ( final int position, View convertView, ViewGroup parent ) {

    	if (convertView == null) {
    		convertView = inflater.inflate( R.layout.row_element, parent, false );
    		holder= new ViewHolder();
    		holder.name = (TextView)convertView.findViewById(R.id.name);
    		holder.direction = (TextView)convertView.findViewById(R.id.direction);
    		holder.checkBox=(CheckBox) convertView.findViewById(R.id.checkBox);

    		holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            
    			@Override
    			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    				int getPosition = (Integer) buttonView.getTag();  // Here we get the position that we have set for the checkbox using setTag.
    				bklist.get(getPosition).setSelected(buttonView.isChecked()); // Set the value of checkbox to maintain its state.
    				checkBoxStateAutobus[getPosition] = isChecked;
    				save(checkBoxStateAutobus);
    			}
    		});
  		
    		
    		convertView.setTag(holder);
    		convertView.setTag(R.id.name, holder.name);
    		convertView.setTag(R.id.checkBox, holder.checkBox);
    		
        	} else {
        		
        		holder = (ViewHolder) convertView.getTag();
        	}
    	
    		holder.checkBox.setTag(position); // This line is important.

    		holder.name.setText(bklist.get(position).getName());
    		holder.direction.setText(bkdirectionlist.get(position));
    		holder.checkBox.setChecked(checkBoxStateAutobus[position]);
    	
    	
   
    	
    	return convertView;
    	
    }
    
    static class ViewHolder {
        protected TextView name;
        protected TextView direction;
        protected CheckBox checkBox;
    }
    
	private void save(final boolean[] isChecked) {
		SharedPreferences sharedPreferences = context.getSharedPreferences("sharedPrefsAutobus", Context.MODE_PRIVATE);
	    SharedPreferences.Editor editor = sharedPreferences.edit();
	    for(Integer i=0;i<isChecked.length;i++)
	    {
	         editor.putBoolean(i.toString(), isChecked[i]);
	    }
	    editor.commit();
	    }
    
}