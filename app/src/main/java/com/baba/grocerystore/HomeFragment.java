package com.baba.grocerystore;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
    }

    private TextView horizontalScrollLayoutTitle;
    private Button viewAll;
    private RecyclerView recyclerView;
    List<ProductModel> productModelList = new ArrayList<>();


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        horizontalScrollLayoutTitle = view.findViewById(R.id.horizontal_scroll_layout_title);
        horizontalScrollLayoutTitle.setText("Fruits & Vegetables");
        viewAll = view.findViewById(R.id.horizontal_scroll_layout_button);
        recyclerView = view.findViewById(R.id.horizontal_scroll_layout_recycler_view);
        productModelList.add(new ProductModel(R.drawable.apple, "Rs. 25/-", "Apple"));
        productModelList.add(new ProductModel(R.drawable.apple, "Rs. 25/-", "Apple"));
        productModelList.add(new ProductModel(R.drawable.apple, "Rs. 25/-", "Apple"));
        productModelList.add(new ProductModel(R.drawable.apple, "Rs. 25/-", "Apple"));
        productModelList.add(new ProductModel(R.drawable.apple, "Rs. 25/-", "Apple"));
        productModelList.add(new ProductModel(R.drawable.apple, "Rs. 25/-", "Apple"));
        productModelList.add(new ProductModel(R.drawable.apple, "Rs. 25/-", "Apple"));
        productModelList.add(new ProductModel(R.drawable.apple, "Rs. 25/-", "Apple"));
        productModelList.add(new ProductModel(R.drawable.apple, "Rs. 25/-", "Apple"));
        productModelList.add(new ProductModel(R.drawable.apple, "Rs. 25/-", "Apple"));
        HorizontalScrollAdapter horizontalScrollAdapter = new HorizontalScrollAdapter(productModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(horizontalScrollAdapter);
        horizontalScrollAdapter.notifyDataSetChanged();

        return view;
    }

}
