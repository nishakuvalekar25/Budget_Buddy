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
import com.example.expensetrackersystem.model.incomeModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class incomeAdapter extends RecyclerView.Adapter<incomeAdapter.viewholder> {
    private Context context;
    private List<incomeModel> incomeModelList = new ArrayList<>();

    public incomeAdapter(Context context, List<incomeModel> incomeModelList) {
        this.context = context;
        this.incomeModelList = incomeModelList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_income_item, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        incomeModel model = incomeModelList.get(position);
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

            tv_incomeAmount = itemView.findViewById(R.id.tv_incomeAmount);
            tv_incomeJob = itemView.findViewById(R.id.tv_incomeJob);
            tv_incomeDate = itemView.findViewById(R.id.tv_incomeDate);
        }
    }
}