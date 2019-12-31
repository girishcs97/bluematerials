package com.sial.bluematerials;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sial.bluematerials.MaterialFragment.OnListFragmentInteractionListener;

public class MyMaterialRecyclerViewAdapter extends RecyclerView.Adapter<MyMaterialRecyclerViewAdapter.ViewHolder> {

    private final OnListFragmentInteractionListener mListener;
    MaterialDataSource materialDS = MaterialDataSource.shared();

    public MyMaterialRecyclerViewAdapter(OnListFragmentInteractionListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_material, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = materialDS.getMaterials().get(position);
        holder.mIdView.setText(Integer.toString(materialDS.getMaterials().get(position).getItemID()));
        holder.mContentView.setText(materialDS.getMaterials().get(position).getItemNumber());
        holder.mDescenView.setText(Integer.toString(materialDS.getMaterials().get(position).getItemDescirption()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return materialDS.getMaterials().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mDescenView;
        public Material mItem;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
            mDescenView=(TextView) view.findViewById(R.id.description);
        }
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
