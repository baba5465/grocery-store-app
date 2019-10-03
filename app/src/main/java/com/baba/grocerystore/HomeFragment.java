package com.baba.grocerystore;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {


    public HomeFragment() {
    }

    private TextView horizontalScrollLayoutTitle;
    private Button viewAll;
    private RecyclerView recyclerView;
    List<ProductModel> productModelList = new ArrayList<>();
    List<ProductModel> gridProductModelList = new ArrayList<>();
    DatabaseReference databaseReference;
    HorizontalScrollAdapter horizontalScrollAdapter;
    GridView gridView;
    String[] category = new String[]{"Fruits & Vegetables", "Foodgrains, Oil & Masala", "Bakery, Cakes & Dairy", "Beverages", "Snacks", "Beauty", "Household"};


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        /**
         * Horizontal Layout
         */
        gridView = view.findViewById(R.id.grid_pro_layout_grid_view);
        horizontalScrollLayoutTitle = view.findViewById(R.id.horizontal_scroll_layout_title);
        horizontalScrollLayoutTitle.setText("Fruits & Vegetables");
        viewAll = view.findViewById(R.id.horizontal_scroll_layout_button);
        recyclerView = view.findViewById(R.id.horizontal_scroll_layout_recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        //horizontalScrollAdapter.notifyDataSetChanged();

        /**
         * Grid Layout
         */
        getItemFromDatabase();
        getItemFromDatabaseForGrid();

        return view;
    }
    private void getItemFromDatabase(){
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Category/"+category[0]);
        productModelList.clear();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    ProductModel pm = data.getValue(ProductModel.class);
                    productModelList.add(new ProductModel(pm.getItemname(),pm.getItemprice(),pm.getImageurl()));
                }
                horizontalScrollAdapter = new HorizontalScrollAdapter(productModelList);
                recyclerView.setAdapter(horizontalScrollAdapter);
                horizontalScrollAdapter.notifyDataSetChanged();
                gridView.setBackgroundColor(Color.GRAY);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void getItemFromDatabaseForGrid(){
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Category/"+category[4]);
        gridProductModelList.clear();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    ProductModel pm = data.getValue(ProductModel.class);
                    gridProductModelList.add(new ProductModel(pm.getItemname(),pm.getItemprice(),pm.getImageurl()));
                }
                GridProductLayoutAdapter gridProductLayoutAdapter = new GridProductLayoutAdapter(gridProductModelList);
                gridView.setAdapter(gridProductLayoutAdapter);
                gridProductLayoutAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
