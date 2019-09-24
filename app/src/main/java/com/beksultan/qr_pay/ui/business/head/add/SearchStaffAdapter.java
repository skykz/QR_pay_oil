package com.beksultan.qr_pay.ui.business.head.add;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.model.business.head.add.HeadStaff;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchStaffAdapter extends RecyclerView.Adapter<SearchStaffAdapter.StaffHolder> {

    List<HeadStaff> headStaffList;
    private Callback callback;

    public SearchStaffAdapter(List<HeadStaff> headStaffList) {
        this.headStaffList = headStaffList;
    }

    @NonNull
    @Override
    public StaffHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_staff, parent, false);

        return new StaffHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffHolder holder, int position) {
        holder.bind(headStaffList.get(position));
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public int getItemCount() {
        return headStaffList.size();
    }

    public class StaffHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_item_staff)
        LinearLayout ll_item_staff;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_balance)
        TextView tv_balance;
        HeadStaff bindHeadStaff;

        public StaffHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(HeadStaff headStaff){
            bindHeadStaff = headStaff;
            tv_name.setText(String.valueOf(headStaff.name + headStaff.surname));
            tv_balance.setText(headStaff.email);
        }

        @OnClick(R.id.ll_item_staff)
        public void onClickItem(){
            callback.selectedItem(bindHeadStaff);
        }
    }

    public interface Callback {
        void selectedItem(HeadStaff headStaff);
    }
}
