package com.example.expensetrackersystem.fragments;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetrackersystem.DatabaseHandler;
import com.example.expensetrackersystem.DatabaseHandlerExpense;
import com.example.expensetrackersystem.R;
import com.example.expensetrackersystem.adapter.expenseAdapter;
import com.example.expensetrackersystem.adapter.incomeAdapter;
import com.example.expensetrackersystem.model.expenseModel;
import com.example.expensetrackersystem.model.incomeModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Dashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutUs extends Fragment {



    public AboutUs() {
        // Required empty public constructor
//        super(R.layout.activity_about_us);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_about_us, container, false);
    }




}