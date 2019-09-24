package com.beksultan.qr_pay.ui.business.director.detail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.model.business.director.detail.Staff;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.StaffHolder> {

    private List<Staff> staffList;
    private Callback callback;

    public StaffAdapter(List<Staff> staffList) {
        this.staffList = staffList;
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
        holder.bind(staffList.get(position));
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public int getItemCount() {
        return staffList.size();
    }

    public class StaffHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_item_staff)
        LinearLayout ll_item_staff;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_balance)
        TextView tv_balance;
        Staff bindStaff;

        public StaffHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Staff staff) {
            bindStaff = staff;
            tv_name.setText(String.valueOf(staff.name + " " + staff.surname));
            tv_balance.setText(String.valueOf(staff.balance + " KZT"));
        }

        @OnClick(R.id.ll_item_staff)
        public void onClickItem() {
            callback.selectedItem(bindStaff);
        }

    }

    public interface Callback {
        void selectedItem(Staff staff);
    }
}
