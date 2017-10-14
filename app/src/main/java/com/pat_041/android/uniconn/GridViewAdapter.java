package com.pat_041.android.uniconn;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Abhijit on 14-10-2017.
 */

public class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    public GridViewAdapter(Context c){
        mContext = c;
    }
    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        //get the item corresponding to your position
        LinearLayout gridLinearLayout = (LinearLayout) (convertView == null
                ? LayoutInflater.from(mContext).inflate(R.layout.linearlayout_grid, parent, false)
                : convertView);

        ((ImageView)gridLinearLayout.findViewById(R.id.image_grid)).setImageResource(mThumbIds[position]);
        ((TextView)gridLinearLayout.findViewById(R.id.grid_text)).setText(""+mTextIds[position]);
        return gridLinearLayout;
    }

    public Integer[] mTextIds = {
            R.drawable.pic_university, R.drawable.pic_institute,
            R.drawable.pic_events,R.drawable.pic_fellow
    };
    public Integer[] mThumbIds = {
            R.string.grid_univ_text,R.string.grid_inst_text,
            R.string.grid_event_text,R.string.grid_fellow_text
    };
}
