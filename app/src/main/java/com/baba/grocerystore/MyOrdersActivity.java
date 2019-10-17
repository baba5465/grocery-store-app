package com.baba.grocerystore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.baba.grocerystore.Adapter.MyOrderAdapter;
import com.baba.grocerystore.Model.OrderDetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyOrderAdapter myOrderAdapter;
    List<OrderDetails> orderDetailsList = new ArrayList<>();
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        recyclerView = findViewById(R.id.my_ord_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        getData();

    }

    private void getData(){
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Orders");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                orderDetailsList.clear();
                for (DataSnapshot d : dataSnapshot.getChildren()){
                    OrderDetails od = d.getValue(OrderDetails.class);
                    //orderDetailsList.add(new OrderDetails(od.getItemName(), od.getItemPrice(), od.getPaymentStatus(), od.getQuantity(), od.getOrderDate()));
                    if (od.getUserId().contains(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                        orderDetailsList.add(new OrderDetails(od.getItemName(), od.getItemPrice(), od.getPaymentStatus(), od.getQuantity(), od.getOrderDate()));
                    }
                }
                myOrderAdapter = new MyOrderAdapter(orderDetailsList);
                recyclerView.setAdapter(myOrderAdapter);
                myOrderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
