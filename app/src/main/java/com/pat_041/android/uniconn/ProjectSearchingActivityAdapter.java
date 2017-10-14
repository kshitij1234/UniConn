package com.pat_041.android.uniconn;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.pat_041.android.uniconn.definitions.Project;
import com.pat_041.android.uniconn.definitions.SuperObjects;

import java.util.ArrayList;

public class ProjectSearchingActivityAdapter extends RecyclerView.Adapter<ProjectSearchingActivityAdapter.ItemViewHolder>{

    private static final String TAG = SearchingActivityAdapter.class.getSimpleName();

    final private ListItemClickListener mOnClickListener;

    private ArrayList<Project> list;


    /**
     * The interface that receives onClick messages.
     */
    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public ProjectSearchingActivityAdapter(ListItemClickListener listener, ArrayList<Project> l)
    {
        list = l;
        mOnClickListener = listener;
    }

    @Override
    public ProjectSearchingActivityAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ProjectSearchingActivityAdapter.ItemViewHolder holder, int position) {
        //Log.d(TAG, "#" + position);

        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(ArrayList<Project> l)
    {
        list = l;
        this.notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ItemViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }


        public void bind(int position) {

        }
    }

}
