package com.pat_041.android.uniconn;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class SearchingActivityAdapter extends RecyclerView.Adapter<SearchingActivityAdapter.ItemViewHolder>{


    private static final String TAG = SearchingActivityAdapter.class.getSimpleName();

    final private ListItemClickListener mOnClickListener;

    private static int viewHolderCount;
    private int mNumberItems;

    /**
     * The interface that receives onClick messages.
     */
    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public SearchingActivityAdapter(int numberOfItems, ListItemClickListener listener)
    {
        mNumberItems = numberOfItems;
        mOnClickListener = listener;
        viewHolderCount = 0;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
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
        public ItemViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
