package com.beksultan.qr_pay.adapter.business.director;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.model.business.director.Department;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.DepartmentHolder> {

    private List<Department> departmentList;
    private Callback callback;

    public DepartmentAdapter(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public void remove(Department department){
        departmentList.remove(department);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DepartmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_department, parent, false);
        return new DepartmentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentHolder holder, int position) {
        holder.bind(departmentList.get(position));
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public int getItemCount() {
        return departmentList.size();
    }

    public class DepartmentHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_item)
        LinearLayout ll_item;
        @BindView(R.id.tv_departmentName)
        TextView tv_departmentName;
        @BindView(R.id.tv_headName)
        TextView tv_headName;
        @BindView(R.id.tv_price)
        TextView tv_price;
        @BindView(R.id.img_edit)
        ImageView img_edit;
        @BindView(R.id.img_delete)
        ImageView img_delete;
        @BindView(R.id.img_money)
        ImageView img_money;
        Department bindDepartment;

        private DepartmentHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(Department department) {
            bindDepartment = department;
            tv_departmentName.setText(department.name);
            tv_headName.setText(String.valueOf(department.headName + " " + department.headSurname));
            tv_price.setText(String.valueOf(department.departmentBalance + " KZT"));
        }

        @OnClick(R.id.ll_item)
        public void onClickItem() {
            callback.selectedItem(bindDepartment);
        }

        @OnClick(R.id.img_edit)
        public void onClickEdit() {
            callback.edit(bindDepartment);
        }

        @OnClick(R.id.img_delete)
        public void onClickDelete() {
            callback.delete(bindDepartment);
        }

        @OnClick(R.id.img_money)
        public void onClickPay() {
            callback.pay(bindDepartment);
        }
    }

    public interface Callback {

        void edit(Department department);

        void delete(Department department);

        void pay(Department department);

        void selectedItem(Department department);
    }
}
