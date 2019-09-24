package com.beksultan.qr_pay.ui.business.head.detail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.model.business.head.DepartmentStaff;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DepartmentStaffAdapter extends RecyclerView.Adapter<DepartmentStaffAdapter.StaffHolder> {

    List<DepartmentStaff> departmentStaffList;
    public CallBack callBack;

    public DepartmentStaffAdapter(List<DepartmentStaff> departmentStaffList) {
        this.departmentStaffList = departmentStaffList;
    }

    public void remove(DepartmentStaff departmentStaff){
        departmentStaffList.remove(departmentStaff);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StaffHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_staff_pay, parent, false);

        return new StaffHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffHolder holder, int position) {
        holder.bind(departmentStaffList.get(position));
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public int getItemCount() {
        return departmentStaffList.size();
    }

    public class StaffHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_item)
        LinearLayout ll_item;
        @BindView(R.id.tv_StaffName)
        TextView tv_staffName;
        @BindView(R.id.tv_StaffBalance)
        TextView tv_StaffBalance;
        @BindView(R.id.img_delete)
        ImageView img_delete;
        @BindView(R.id.img_pay)
        ImageView img_pay;
        DepartmentStaff binddepartmentStaff;

        public StaffHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(DepartmentStaff departmentStaff) {
            binddepartmentStaff = departmentStaff;
            tv_staffName.setText(String.valueOf(departmentStaff.name + " " + departmentStaff.surname));
            tv_StaffBalance.setText(String.valueOf(departmentStaff.balance + " KZT"));
        }

        @OnClick(R.id.img_delete)
        public void OnclickDelete() {
            callBack.delete(binddepartmentStaff);
        }

        @OnClick(R.id.img_pay)
        public void OnclickPay() {
            callBack.pay(binddepartmentStaff);
        }

        @OnClick(R.id.ll_item)
        public void OnclickItem() {
            callBack.selectedItem(binddepartmentStaff);
        }

    }

    public interface CallBack {

        void delete(DepartmentStaff departmentStaff);

        void pay(DepartmentStaff departmentStaff);

        void selectedItem(DepartmentStaff departmentStaff);

    }
}
