package eu.trentorise.smartcampus.widget.shortcuts;

import java.util.List;

import eu.trentorise.smartcampus.widget.R;
import eu.trentorise.smartcampus.widget.shortcuts.WidgetHelper.BookmarkDescriptor;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ObjectListAdapter extends ArrayAdapter{


	private int resource;
    private LayoutInflater inflater;
    private Context context;
    //lista di bookmarks    
    private List <BookmarkDescriptor> bklist;
    boolean[] checkBoxState;
    
    public ObjectListAdapter ( Context ctx, int resourceId, List<BookmarkDescriptor> objects, boolean[] check) {

        super(ctx, resourceId, objects);
        resource = resourceId;
        inflater = LayoutInflater.from(ctx);
        context=ctx;
        bklist=objects;
        checkBoxState= check;   
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
    	
    
    	
    	
    	convertView = inflater.inflate( R.layout.row_element, parent, false );
    	
    	name = (TextView)convertView.findViewById(R.id.name);
    	checkBox=(CheckBox) convertView.findViewById(R.id.checkBox);
    	
    	
    	name.setText(bklist.get(position).getName().toString());
    	
    	
    	checkBox.setChecked(checkBoxState[position]);
    	
    	checkBox.setOnClickListener(new View.OnClickListener() {
    	     
    		   @Override
			public void onClick(View v) {
    		    if(((CheckBox)v).isChecked())
    		     checkBoxState[position]=true;
    		    else
    		     checkBoxState[position]=false;   
    		    }
    		   });
    	
    	
        return convertView;
    }
	
}