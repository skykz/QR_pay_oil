package com.beksultan.qr_pay.adapter.business.director;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.model.business.director.addDepartment.Head;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddDepartmentAdapter extends RecyclerView.Adapter<AddDepartmentAdapter.AddDepartmentHolder> {

    private List<Head> headList;
    private Callback callback;

    public AddDepartmentAdapter(List<Head> headList) {
        this.headList = headList;
    }

    @NonNull
    @Override
    public AddDepartmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_add_department, parent, false);

        return new AddDepartmentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddDepartmentHolder holder, int position) {
        holder.bind(headList.get(position));
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public int getItemCount() {
        return headList.size();
    }

    public class AddDepartmentHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_item)
        LinearLayout ll_item;
        @BindView(R.id.tv_workerName)
        TextView tv_workerName;
        @BindView(R.id.tv_userEmail)
        TextView tv_userEmail;
        Head bindHead;

        private AddDepartmentHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(Head head) {
            bindHead = head;
            tv_workerName.setText(String.valueOf(head.name + " " + head.surname));
            tv_userEmail.setText(head.email);
        }

        @OnClick(R.id.ll_item)
        public void onClickItem() {
            callback.selectedItem(bindHead);
        }
    }

    public interface Callback {
        void selectedItem(Head head);
    }
}
