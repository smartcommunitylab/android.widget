package eu.trentorise.smartcampus.widget.shortcuts;

import java.util.ArrayList;
import java.util.List;

import eu.trentorise.smartcampus.widget.R;
import eu.trentorise.smartcampus.widget.shortcuts.WidgetHelper.BookmarkDescriptor;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TreniListAdapter extends ArrayAdapter{


	private int resource;
    private LayoutInflater inflater;
    private Context context;
    //lista di bookmarks    
    //private ArrayList <BookmarkDescriptor> bklist;
    private List <BookmarkDescriptor> bklist;
    boolean[] checkBoxStateTreni;
    
    public TreniListAdapter ( Context ctx, int resourceId, List<BookmarkDescriptor> objects, boolean[] check) {

        super(ctx, resourceId, objects);
        resource = resourceId;
        inflater = LayoutInflater.from(ctx);
        context=ctx;
        bklist=objects;
        checkBoxStateTreni= check; 
    
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
  
    
    	
    	
    	convertView = (RelativeLayout) inflater.inflate( R.layout.row_element, parent, false );
    	
    	name = (TextView)convertView.findViewById(R.id.name);
    	checkBox=(CheckBox)convertView.findViewById(R.id.checkBox);
    	
    	
    	name.setText(bklist.get(position).getName().toString());
    	
    	
    	checkBox.setChecked(checkBoxStateTreni[position]);
    	
    	checkBox.setOnClickListener(new View.OnClickListener() {
    	     
    		   public void onClick(View v) {
    		    if(((CheckBox)v).isChecked())
    		     checkBoxStateTreni[position]=true;
    		    else
    		     checkBoxStateTreni[position]=false;   
    		    }
    		   });
    	
    	return convertView;
    	
    }
}