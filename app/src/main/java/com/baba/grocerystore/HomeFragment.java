package com.baba.grocerystore;

import android.content.Intent;
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
import android.widget.AdapterView;
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

    private RecyclerView recyclerView;
    private List<ProductModel> productModelList = new ArrayList<>();
    private List<ProductModel> gridProductModelList = new ArrayList<>();
    private DatabaseReference databaseReference;
    private HorizontalScrollAdapter horizontalScrollAdapter;
    private GridView gridView;
    private String[] category = new String[]{"Fruits & Vegetables", "Foodgrains, Oil & Masala", "Bakery, Cakes & Dairy", "Beverages", "Snacks", "Beauty", "Household"};


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        gridView = view.findViewById(R.id.grid_pro_layout_grid_view);
        TextView horizontalScrollLayoutTitle = view.findViewById(R.id.horizontal_scroll_layout_title);
        horizontalScrollLayoutTitle.setText("Fruits & Vegetables");

        Button viewAllHor = view.findViewById(R.id.horizontal_scroll_layout_button);
        viewAllHor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CategoryItemActivity.class).putExtra("Number", 0));
            }
        });

        Button viewAllGrid = view.findViewById(R.id.grid_pro_layout_view_all);
        viewAllGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CategoryItemActivity.class).putExtra("Number", 4));
            }
        });

        recyclerView = view.findViewById(R.id.horizontal_scroll_layout_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        getItemFromDatabase();
        getItemFromDatabaseForGrid();
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getContext(), AddToCartActivity.class);
                        intent.putExtra("Name" , productModelList.get(position).getItemname());
                        intent.putExtra("Price" , productModelList.get(position).getItemprice());
                        intent.putExtra("Image" , productModelList.get(position).getImageurl());
                        intent.putExtra("Id" , productModelList.get(position).getItemid());
                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), AddToCartActivity.class);
                intent.putExtra("Name" , gridProductModelList.get(i).getItemname());
                intent.putExtra("Price" , gridProductModelList.get(i).getItemprice());
                intent.putExtra("Image" , gridProductModelList.get(i).getImageurl());
                intent.putExtra("Id" , gridProductModelList.get(i).getItemid());
                startActivity(intent);
            }
        });

        return view;
    }

    private void getItemFromDatabase() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Category/" + category[0]);
        productModelList.clear();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    ProductModel pm = data.getValue(ProductModel.class);
                    productModelList.add(new ProductModel(pm.getItemname(), pm.getItemprice(), pm.getImageurl(), pm.getItemid()));
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

    private void getItemFromDatabaseForGrid() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Category/" + category[4]);
        gridProductModelList.clear();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    ProductModel pm = data.getValue(ProductModel.class);
                    gridProductModelList.add(new ProductModel(pm.getItemname(), pm.getItemprice(), pm.getImageurl(), pm.getItemid()));
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
