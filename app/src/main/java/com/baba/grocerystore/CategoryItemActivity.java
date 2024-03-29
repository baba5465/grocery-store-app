package com.baba.grocerystore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.baba.grocerystore.Adapter.CategoryAdapter;
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

public class CategoryItemActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CategoryAdapter categoryAdapter;
    List<ProductModel> productModelList = new ArrayList<>();;
    String[] category = new String[]{"Fruits & Vegetables", "Foodgrains, Oil & Masala", "Bakery, Cakes & Dairy", "Beverages", "Snacks", "Beauty", "Household"};

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_item);

        Intent intent = getIntent();
        int catNo = intent.getIntExtra("Number",-1);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Category/"+category[catNo]);

        recyclerViewConfig();
        getItemFromDatabase();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent1 = new Intent(getApplicationContext(), AddToCartActivity.class);
                        intent1.putExtra("Name" , productModelList.get(position).getItemname());
                        intent1.putExtra("Price" , productModelList.get(position).getItemprice());
                        intent1.putExtra("Image" , productModelList.get(position).getImageurl());
                        intent1.putExtra("Id" , productModelList.get(position).getItemid());
                        startActivity(intent1);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }
    private void recyclerViewConfig(){
        recyclerView = findViewById(R.id.cat_rec_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    public void getItemFromDatabase(){
        productModelList.clear();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    ProductModel pm = data.getValue(ProductModel.class);
                    productModelList.add(new ProductModel(pm.getItemname(),pm.getItemprice(),pm.getImageurl(), pm.getItemid()));
                }
                categoryAdapter = new CategoryAdapter(productModelList);
                recyclerView.setAdapter(categoryAdapter);
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
