package com.example.expensetrackersystem.adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetrackersystem.DatabaseHandlerExpense;
import com.example.expensetrackersystem.R;
import com.example.expensetrackersystem.model.expenseModel;

import java.util.Calendar;
import java.util.List;

public class expenseAdapter2 extends RecyclerView.Adapter<expenseAdapter2.viewholder> {
    private Context context;
    private List<expenseModel> expenseModelList;
    private DatabaseHandlerExpense databaseHandler;

    public expenseAdapter2(Context context, List<expenseModel> expenseModelList, DatabaseHandlerExpense databaseHandler) {
        this.context = context;
        this.expenseModelList = expenseModelList;
        this.databaseHandler = databaseHandler;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_expense_item2, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        expenseModel model = expenseModelList.get(position);
        holder.tv_incomeAmount.setText("₹"+model.getAmount());
        holder.tv_incomeType.setText(model.getType());
        holder.tv_incomeNote.setText(model.getNote());

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(model.getDate()));
        String formattedDate = DateFormat.format("dd/MM/yyyy", calendar).toString();

        holder.tv_incomeDate.setText(formattedDate);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExpenseDialog(context, model);
            }
        });
    }

    public void showExpenseDialog(Context context, expenseModel model) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        final View customLayout = LayoutInflater.from(context).inflate(R.layout.expense_add_item, null);
        EditText et_income = customLayout.findViewById(R.id.et_incomeAmount);
        EditText et_type = customLayout.findViewById(R.id.et_incomeType);
        EditText et_note = customLayout.findViewById(R.id.et_incomeNote);

        et_income.setText(model.getAmount());
        et_type.setText(model.getType());
        et_note.setText(model.getNote());

        Button btn_save = customLayout.findViewById(R.id.btn_save);
        Button btn_cancel = customLayout.findViewById(R.id.btn_cancel);

        builder.setView(customLayout);
        AlertDialog alertDialog = builder.create();

        alertDialog.show();

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = model.getId();
                String amount = et_income.getText().toString();
                String type = et_type.getText().toString();
                String note = et_note.getText().toString();
                long date = System.currentTimeMillis();

                if (amount.isEmpty()) {
                    et_income.setError("Empty amount");
                    return;
                } else if (type.isEmpty()) {
                    et_type.setError("Empty Type");
                    return;
                } else if (note.isEmpty()) {
                    et_note.setError("Empty note");
                    return;
                } else {
                    databaseHandler.update(id, amount, type, note, String.valueOf(date));
                    alertDialog.dismiss();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return expenseModelList.size();
    }

    class viewholder extends RecyclerView.ViewHolder {
        private TextView tv_incomeDate, tv_incomeType, tv_incomeNote, tv_incomeAmount;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            tv_incomeDate = itemView.findViewById(R.id.tv_incomeDate);
            tv_incomeType = itemView.findViewById(R.id.tv_incomeType);
            tv_incomeNote = itemView.findViewById(R.id.tv_incomeNote);
            tv_incomeAmount = itemView.findViewById(R.id.tv_incomeAmount);

        }
    }
}
