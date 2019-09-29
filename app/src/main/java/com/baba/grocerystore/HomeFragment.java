package com.baba.grocerystore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.baba.grocerystore.Adapter.GridProductLayoutAdapter;
import com.baba.grocerystore.Adapter.HorizontalScrollAdapter;
import com.baba.grocerystore.Model.ProductModel;

import java.util.ArrayList;
import java.util.List;


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
        /**
         * Horizontal Layout
         */
        horizontalScrollLayoutTitle = view.findViewById(R.id.horizontal_scroll_layout_title);
        horizontalScrollLayoutTitle.setText("Fruits & Vegetables");
        viewAll = view.findViewById(R.id.horizontal_scroll_layout_button);
        recyclerView = view.findViewById(R.id.horizontal_scroll_layout_recycler_view);
        productModelList.clear();
        productModelList.add(new ProductModel(R.drawable.mango_, "Rs. 25/-", "Mango"));
        productModelList.add(new ProductModel(R.drawable.strawberry, "Rs. 25/-", "Strawberry"));
        productModelList.add(new ProductModel(R.drawable.banana, "Rs. 25/-", "Banana"));
        productModelList.add(new ProductModel(R.drawable.blueberry, "Rs. 25/-", "Blueberry"));
        productModelList.add(new ProductModel(R.drawable.pineapple, "Rs. 25/-", "Pineapple"));
        productModelList.add(new ProductModel(R.drawable.gauva, "Rs. 25/-", "Guava"));
        productModelList.add(new ProductModel(R.drawable.orange, "Rs. 25/-", "Orange"));
        productModelList.add(new ProductModel(R.drawable.watermelon, "Rs. 25/-", "Watermelon"));
        productModelList.add(new ProductModel(R.drawable.apple, "Rs. 25/-", "Apple"));
        HorizontalScrollAdapter horizontalScrollAdapter = new HorizontalScrollAdapter(productModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(horizontalScrollAdapter);
        horizontalScrollAdapter.notifyDataSetChanged();

        /**
         * Grid Layout
         */
        GridView gridView = view.findViewById(R.id.grid_pro_layout_grid_view);
        gridView.setAdapter(new GridProductLayoutAdapter(productModelList));

        return view;
    }

}
