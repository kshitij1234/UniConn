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

import java.util.ArrayList;

public class SearchingActivityAdapter extends RecyclerView.Adapter<SearchingActivityAdapter.ItemViewHolder>{


    private static final String TAG = SearchingActivityAdapter.class.getSimpleName();

    final private ListItemClickListener mOnClickListener;

    private static int viewHolderCount;
    private int mNumberItems;

    private ArrayList<SuperObjects> list;

    /**
     * The interface that receives onClick messages.
     */
    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public SearchingActivityAdapter(int numberOfItems, ListItemClickListener listener, ArrayList<SuperObjects> l)
    {
        list = l;
        mNumberItems = numberOfItems;
        mOnClickListener = listener;
        viewHolderCount = 0;
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
        holder.bind(position);
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
            // create an intent to go to the corresponding item page
        }

        public void bind(int position) {

            TextDrawable drawable = TextDrawable.builder()
                    .buildRoundRect(object.getHeading(), Color.RED, 10); // radius in px

            mImageView.setImageDrawable(drawable);

            SuperObject object = list.get(position);
            heading.setText(object.getHeading());
            extra.setText(object.getExtra());

        }
    }
}
