package com.pat_041.android.uniconn;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.pat_041.android.uniconn.definitions.SuperObjects;

import java.util.ArrayList;
import java.util.Random;

public class SearchingActivityAdapter extends RecyclerView.Adapter<SearchingActivityAdapter.ItemViewHolder>{


    private static final String TAG = SearchingActivityAdapter.class.getSimpleName();

    final private ListItemClickListener mOnClickListener;

    private int mNumberItems;

    private ArrayList<? extends SuperObjects> list;

    /**
     * The interface that receives onClick messages.
     */
    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public SearchingActivityAdapter(int numberOfItems, ListItemClickListener listener, ArrayList<? extends SuperObjects> l)
    {
        list = l;
        mNumberItems = numberOfItems;
        mOnClickListener = listener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layout = R.layout.search_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately;
        shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layout, viewGroup, shouldAttachToParentImmediately);
        ItemViewHolder viewHolder = new ItemViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        //holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView mImageView;
        TextView heading;
        TextView extra;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.list_image_view);
            heading = (TextView) itemView.findViewById(R.id.list_heading);
            extra = (TextView) itemView.findViewById(R.id.list_extra);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // create an intent to go to the corresponding item page in listener in Searching Activity
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }

        public void bind(int position) {

            Random rand = new Random();
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            int randomColor = Color.rgb(r,g,b);

            SuperObjects object = list.get(position);
            TextDrawable drawable = TextDrawable.builder()
                    .buildRoundRect(object.getHeading().toUpperCase().charAt(0)+"", randomColor, 10); // radius in px
            mImageView.setImageDrawable(drawable);
            heading.setText(object.getHeading());
            extra.setText(object.getExtra());

        }
    }
}
