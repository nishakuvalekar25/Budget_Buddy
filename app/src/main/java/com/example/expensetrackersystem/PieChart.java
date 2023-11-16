package com.example.expensetrackersystem;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.expensetrackersystem.model.expenseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class PieChart extends AppCompatActivity {

    private List<String> xData = new ArrayList<>();

    ArrayList pieEntries;

    private DatabaseHandlerExpense databaseHandlerExpense;

    HashMap<String, String> map;

    PieChartView pieChartView;
    List<SliceValue> pieData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        pieChartView = findViewById(R.id.chart);

        databaseHandlerExpense = new DatabaseHandlerExpense(PieChart.this);

        addData();
        getEntries();


        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        pieChartData.setHasCenterCircle(true).setCenterText1("Expenses").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
        pieChartView.setPieChartData(pieChartData);

    }

    private void addData() {
        List<expenseModel> expenseModelList = databaseHandlerExpense.getAllIncome();

        for (expenseModel model : expenseModelList) {
            xData.add(model.getType());
        }

        map = new HashMap<>();
        for (expenseModel model : expenseModelList) {
            int amount = Integer.parseInt(model.getAmount());
            if (map.containsKey(model.getType())) {
                int a = Integer.parseInt(map.get(model.getType()));
                map.put(model.getType(), String.valueOf(a + amount));
            } else {
                map.put(model.getType(), model.getAmount());
            }
        }
    }

    private void getEntries() {
        pieEntries = new ArrayList<>();
        int i = 0;

        pieData = new ArrayList<>();

        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.parseColor("#03045e"));
        colors.add(Color.parseColor("#023e8a"));
        colors.add(Color.parseColor("#0077b6"));
        colors.add(Color.parseColor("#0096c7"));
        colors.add(Color.parseColor("#00b4d8"));
        colors.add(Color.parseColor("#48cae4"));
        colors.add(Color.parseColor("#90e0ef"));
        colors.add(Color.parseColor("#ade8f4"));
        colors.add(Color.parseColor("#caf0f8"));


        for (String type : xData) {
            pieData.add(new SliceValue(Float.parseFloat(map.get(type)), colors.get(i % 5)).setLabel(type));
            i++;
        }
    }


}