package com.avinnovz.sss.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avinnovz.sss.R;
import com.avinnovz.sss.interfaces.OnListItemClickListener;
import com.avinnovz.sss.models.Directory;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jayan on 8/30/2016.
 */
public class DirectoryAdapter extends RecyclerView.Adapter<DirectoryAdapter.ViewHolder> {

    OnListItemClickListener onListItemClickListener;
    Activity mActivity;
    List<Directory> directories;

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rootView)
        LinearLayout rootView;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvPosition)
        TextView tvPosition;

        @BindColor(R.color.colorListViewRow1)
        int colorListViewRow1;
        @BindColor(R.color.colorListViewRow2)
        int colorListViewRow2;

        boolean isExpanded = false;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public DirectoryAdapter(Activity activity, List<Directory> directories, OnListItemClickListener onListItemClickListener) {
        this.mActivity = activity;
        this.directories = directories;
        this.onListItemClickListener = onListItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_directory, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (holder != null) {

            //SET BG
            if (position % 2 == 0) {
                holder.rootView.setBackgroundColor(holder.colorListViewRow1);
            } else {
                holder.rootView.setBackgroundColor(holder.colorListViewRow2);
            }

            //SET NAME
            holder.tvName.setText(directories.get(position).getName());

            //SET POSITION
            holder.tvPosition.setText(directories.get(position).getPosition());

            //VIEW DETAILS
            holder.rootView.setOnClickListener(view -> {
                if (onListItemClickListener != null) {
                    onListItemClickListener.onItemClick(directories.get(position));
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return directories.size();
    }
}
