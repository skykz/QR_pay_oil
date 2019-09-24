package com.beksultan.qr_pay.adapter.base;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public abstract class BaseSuperRViewAdapter extends RecyclerView.Adapter<BaseSuperRViewAdapter.MainViewHolder> {

    protected OnItemClickListener onItemClickListener;
    protected boolean loading;
    protected List<Object> data;

    protected void setLoading(boolean loading) {
        if (this.loading && !loading) {
            this.loading = false;
            notifyItemRemoved(getItemCount());
        } else if (!this.loading && loading) {
            this.loading = true;
            notifyItemInserted(getItemCount() - 1);
        }
    }

    public boolean isLoading() {
        return loading;
    }

    public void addList(List<?> newItems) {
        int positionStart = data.size();
        int itemCount = newItems.size();
        data.addAll(newItems);
        notifyItemRangeInserted(positionStart, itemCount);
    }

    public void clearItems() {
        data.clear();
        notifyDataSetChanged();
    }

    @Nullable
    public Object getItem(int position) {
        if (data != null && data.size() > position) {
            return data.get(position);
        }
        return null;
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        public MainViewHolder(View v) {
            super(v);
        }
    }

    public class SimpleViewHolder extends MainViewHolder {
        public SimpleViewHolder(View v) {
            super(v);
        }
    }

    protected View inflate(ViewGroup parent, int resource) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        return mInflater.inflate(resource, parent, false);
    }

    protected IllegalStateException incorrectOnCreateViewHolder() {
        return new IllegalStateException("Incorrect ViewType found");
    }

    protected IllegalStateException incorrectGetItemViewType() {
        return new IllegalStateException("Incorrect object added");
    }

    public interface OnItemClickListener {
        void onItemClick(String id, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
