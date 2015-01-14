package com.surfacetension.keentime;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by simonkenny on 14/01/15.
 */
public class EventCollectionAdapter extends RecyclerView.Adapter<EventCollectionAdapter.ViewHolder> {
    private String[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mView;
        public ViewHolder(View v) {
            super(v);
            mView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public EventCollectionAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EventCollectionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_collection_list_cell, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        ((TextView)holder.mView.findViewById(R.id.text)).setText(mDataset[position]);
        String selectedCollection = GlobalSettings.getInstance().getCurEventCollection();
        boolean isSelected = false;
        if( selectedCollection != null ) {
            if (selectedCollection.equals(mDataset[position])) { //debug
                ((ImageView) holder.mView.findViewById(R.id.icon)).setImageResource(R.drawable.ic_action_accept);
                isSelected = true;
            }
        }
        if( !isSelected ) {
            ((ImageView) holder.mView.findViewById(R.id.icon)).setImageDrawable(null);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}