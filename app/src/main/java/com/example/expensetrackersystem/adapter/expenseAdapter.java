package com.example.expensetrackersystem.adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetrackersystem.R;
import com.example.expensetrackersystem.model.expenseModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class expenseAdapter extends RecyclerView.Adapter<expenseAdapter.viewholder> {
    private Context context;
    private List<expenseModel> incomeModelList = new ArrayList<>();

    public expenseAdapter(Context context, List<expenseModel> incomeModelList) {
        this.context = context;
        this.incomeModelList = incomeModelList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_expense_item, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        expenseModel model = incomeModelList.get(position);
        holder.tv_incomeAmount.setText("₹"+model.getAmount());

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(model.getDate()));
        String formattedDate = DateFormat.format("dd/MM/yyyy", calendar).toString();

        holder.tv_incomeDate.setText(formattedDate);
        holder.tv_incomeJob.setText(model.getType());
    }

    @Override
    public int getItemCount() {
        return incomeModelList.size();
    }

    class viewholder extends RecyclerView.ViewHolder {
        TextView tv_incomeJob, tv_incomeAmount, tv_incomeDate;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            tv_incomeAmount = itemView.findViewById(R.id.tv_expenseAmount);
            tv_incomeJob = itemView.findViewById(R.id.tv_expenseJob);
            tv_incomeDate = itemView.findViewById(R.id.tv_expenseDate);
        }
    }
}
