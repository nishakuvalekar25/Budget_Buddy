package com.example.expensetrackersystem.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetrackersystem.DatabaseHandlerExpense;
import com.example.expensetrackersystem.PieChart;
import com.example.expensetrackersystem.R;
import com.example.expensetrackersystem.adapter.expenseAdapter2;
import com.example.expensetrackersystem.model.expenseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Expense#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Expense extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Expense() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Expense.
     */
    // TODO: Rename and change types and number of parameters
    public static Expense newInstance(String param1, String param2) {
        Expense fragment = new Expense();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private TextView tvExpense;
    private RecyclerView rvExpense;
    private List<expenseModel> expenseModelList = new ArrayList<>();
    private DatabaseHandlerExpense databaseHandlerExpense;
    private String totalExpense;

    private ImageView iv_expensePie;

    private expenseAdapter2 expenseAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expense, container, false);

        init(view);

        iv_expensePie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), PieChart.class));
            }
        });

        fillExpense();

        return view;
    }

    private void fillExpense() {
        expenseModelList = databaseHandlerExpense.getAllIncome();
        int total = 0;
        for (expenseModel model : expenseModelList) {
            total += Integer.parseInt(model.getAmount());
        }
        totalExpense = String.valueOf(total);
        tvExpense.setText("₹" + totalExpense);

        expenseAdapter = new expenseAdapter2(getContext(), expenseModelList, databaseHandlerExpense);
        rvExpense.setLayoutManager(new LinearLayoutManager(getContext()));
        rvExpense.setHasFixedSize(true);

        rvExpense.setAdapter(expenseAdapter);
    }

    private void init(View view) {
        tvExpense = view.findViewById(R.id.tvExpense);
        rvExpense = view.findViewById(R.id.rvExpense);
        iv_expensePie = view.findViewById(R.id.iv_expensePie);
        databaseHandlerExpense = new DatabaseHandlerExpense(getContext());
    }


}