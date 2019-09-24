package com.beksultan.qr_pay.ui.business.head.pay;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.model.business.head.DepartmentStaff;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayStaffAdapter extends RecyclerView.Adapter<PayStaffAdapter.PayStaffHolder> {

    private List<DepartmentStaff> departmentStaffList;
    private Callback callback;
    private Context context;

    public PayStaffAdapter(List<DepartmentStaff> departmentStaffList, Context context) {
        this.departmentStaffList = departmentStaffList;
        this.context = context;
    }

    @NonNull
    @Override
    public PayStaffHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_staff, parent, false);
        return new PayStaffHolder(view);
    }

    public void selectItem(List<DepartmentStaff> departmentStaffList){
        this.departmentStaffList = departmentStaffList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull PayStaffHolder holder, int position) {
        holder.bind(departmentStaffList.get(position));
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public int getItemCount() {
        return departmentStaffList.size();
    }

    public class PayStaffHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_item)
        LinearLayout ll_item;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_balance)
        TextView tv_balance;
        DepartmentStaff bindDepartmentStaff;

        private PayStaffHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(DepartmentStaff departmentStaff) {
            bindDepartmentStaff = departmentStaff;
            tv_name.setText(departmentStaff.name);
            tv_balance.setText(String.valueOf(departmentStaff.balance + " KZT"));

            if (departmentStaff.isSelected) {
                ll_item.setBackgroundColor(context.getResources().getColor(R.color.gray));
            } else {
                ll_item.setBackgroundColor(context.getResources().getColor(R.color.white));
            }
        }

        @OnClick(R.id.ll_item_staff)
        public void onClickItem() {
            callback.selectedItem(bindDepartmentStaff);
        }

    }

    public interface Callback {
        void selectedItem(DepartmentStaff departmentStaff);
    }
}
