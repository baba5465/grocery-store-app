package com.baba.grocerystore;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.baba.grocerystore.Adapter.AccountListAdapter;
import com.baba.grocerystore.Model.AccountListModel;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {

    ListView listViewCategory;
    List<AccountListModel> listModelsCategory = new ArrayList<>();

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        listViewCategory = view.findViewById(R.id.listview_category);
        addItem();
        AccountListAdapter adapter = new AccountListAdapter(getContext(), R.layout.acc_list_item, listModelsCategory);
        listViewCategory.setAdapter(adapter);
        listViewCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getContext(), CategoryItemActivity.class).putExtra("Number",i));
            }
        });
        return view;
    }

    private void addItem() {
        listModelsCategory.clear();
        listModelsCategory.add(new AccountListModel(0, "Fruits & Vegetables"));
        listModelsCategory.add(new AccountListModel(0, "Foodgrains, Oil & Masala"));
        listModelsCategory.add(new AccountListModel(0, "Bakery, Cakes & Dairy"));
        listModelsCategory.add(new AccountListModel(0, "Beverages"));
        listModelsCategory.add(new AccountListModel(0, "Snacks"));
        listModelsCategory.add(new AccountListModel(0, "Beauty"));
        listModelsCategory.add(new AccountListModel(0, "Household"));
//        listModelsCategory.add(new AccountListModel(0,"Kitchen"));
//        listModelsCategory.add(new AccountListModel(0,"Baby Care"));
    }

}
