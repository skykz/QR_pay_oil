package com.beksultan.qr_pay.ui.business.director.pay;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.model.business.director.Department;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayAdapter extends RecyclerView.Adapter<PayAdapter.PayHolder> {

    private List<Department> departmentList;
    private Callback callback;
    private Context context;

    public PayAdapter(List<Department> departmentList, Context context) {
        this.departmentList = departmentList;
        this.context = context;
    }

    @NonNull
    @Override
    public PayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_department_pay, parent, false);
        return new PayHolder(view);
    }

    public void selectItem(List<Department> departmentList){
        this.departmentList = departmentList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull PayHolder holder, int position) {
        holder.bind(departmentList.get(position));
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public int getItemCount() {
        return departmentList.size();
    }

    public class PayHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_item)
        LinearLayout ll_item;
        @BindView(R.id.ll_row)
        LinearLayout ll_row;
        @BindView(R.id.tv_departmentName)
        TextView tv_departmentName;
        @BindView(R.id.tv_headName)
        TextView tv_headName;
        @BindView(R.id.tv_price)
        TextView tv_price;
        Department bindDepartment;

        private PayHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(Department department) {
            bindDepartment = department;
            tv_departmentName.setText(department.name);
            tv_headName.setText(String.valueOf(department.headName + " " + department.headSurname));
            tv_price.setText(String.valueOf(department.departmentBalance + " KZT"));

            if(department.isSelected){
                ll_item.setBackgroundColor(context.getResources().getColor(R.color.gray));
            }
            else{
                ll_item.setBackgroundColor(context.getResources().getColor(R.color.white));
            }
        }

        @OnClick(R.id.ll_item)
        public void onClickItem() {
            callback.selectedItem(bindDepartment);
        }
    }

    public interface Callback {
        void selectedItem(Department department);
    }
}
