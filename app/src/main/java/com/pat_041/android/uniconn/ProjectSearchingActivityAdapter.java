package com.pat_041.android.uniconn;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import de.hdodenhof.circleimageview.CircleImageView;

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
    public ProjectSearchingActivityAdapter.ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layout = R.layout.project_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately;
        shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layout, viewGroup, shouldAttachToParentImmediately);
        ItemViewHolder viewHolder = new ItemViewHolder(view);

        return viewHolder;
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


        CircleImageView image;
        TextView heading;
        TextView tag;

        public ItemViewHolder(View itemView) {
            super(itemView);
            image = (CircleImageView) itemView.findViewById(R.id.project_image);
            heading = (TextView) itemView.findViewById(R.id.project_heading);
            tag = (TextView) itemView.findViewById(R.id.project_tag);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }


        public void bind(int position) {

            Project p = list.get(position);
            heading.setText(p.getName());
            tag.setText(p.getTag());

            // need to use project_image later

        }
    }

}
