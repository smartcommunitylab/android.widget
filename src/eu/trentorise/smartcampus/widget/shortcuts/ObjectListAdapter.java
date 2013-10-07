package eu.trentorise.smartcampus.widget.shortcuts;

import java.util.List;

import eu.trentorise.smartcampus.widget.R;
import eu.trentorise.smartcampus.widget.shortcuts.WidgetHelper.BookmarkDescriptor;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ObjectListAdapter extends ArrayAdapter{


	private int resource;
    private LayoutInflater inflater;
    private Context context;

    
    public ObjectListAdapter ( Context ctx, int resourceId, List objects) {

        super(ctx, resourceId, objects);
        resource = resourceId;
        inflater = LayoutInflater.from(ctx);
        context=ctx;
    }

// METODO GETVIEW DOVE VADO A PRENDERE IL PARAMETRI DI BOOKMARKS
  
    @Override
    public View getView ( int position, View convertView, ViewGroup parent ) {

    	TextView name;
    	ImageView image;
    	
    	convertView = ( RelativeLayout ) inflater.inflate( R.layout.row_element, parent, false );

    	
        
        return convertView;
    }
	
}