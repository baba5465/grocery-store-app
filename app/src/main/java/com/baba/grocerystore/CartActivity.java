package com.baba.grocerystore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baba.grocerystore.Adapter.CartAdapter;
import com.baba.grocerystore.Adapter.HorizontalScrollAdapter;
import com.baba.grocerystore.Model.CartItemModel;
import com.baba.grocerystore.Model.OrderDetails;
import com.baba.grocerystore.Model.ProductModel;
import com.baba.grocerystore.Model.UserDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    DatabaseReference databaseReference, dBRefUserDets, dBRefOrder;
    public static List<CartItemModel> cartItemModels = new ArrayList<>();
    public static CartAdapter cartAdapter;
    TextView totalAmount;
    Button checkout;
    public static int count = 0;
    UserDetails userDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Log.i("date", getCurrentDate());
        Log.i("time", getCurrentTime());

        if (cartItemModels.size() > 0) {
            cartItemModels.clear();
        }
        recyclerView = findViewById(R.id.cart_recycler);
        checkout = findViewById(R.id.cart_ceckout_btn);
        ImageView backButton = findViewById(R.id.cart_back_btn);
        totalAmount = findViewById(R.id.cart_total_price);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        cartAdapter = new CartAdapter(cartItemModels);
        recyclerView.setAdapter(cartAdapter);
        getItemFromDatabase();
        cartAdapter.notifyDataSetChanged();


        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserDetails();
                //orderSave();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finishAffinity();
                finish();
            }
        });

    }

    private void getItemFromDatabase() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Cart/" + FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cartItemModels.clear();
                count = 0;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    CartItemModel pm = data.getValue(CartItemModel.class);
                    cartItemModels.add(new CartItemModel(pm.getItemName(), pm.getItemPrice(), pm.getItemImage(), pm.getItemId(), pm.getItemQuantity(), pm.getItemTotalPrice()));
                    count += Integer.parseInt(pm.getItemTotalPrice());
                }
                totalAmount.setText("Rs. " + count + "/-");
                cartAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void orderSave() {
        dBRefOrder = FirebaseDatabase.getInstance().getReference().child("Orders");
        for (CartItemModel cm : cartItemModels) {
            OrderDetails orderDetails = new OrderDetails(userDetails.getName(), userDetails.getAddress(), userDetails.getNumber(), userDetails.getEmail(), FirebaseAuth.getInstance().getCurrentUser().getUid(),
                    cm.getItemName(), cm.getItemPrice(), "COD", cm.getItemQuantity(), cm.getItemTotalPrice(), getCurrentDate(), getCurrentTime());
            String key = dBRefOrder.push().getKey();
            dBRefOrder.child(key).setValue(orderDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(), "Order Saved", Toast.LENGTH_SHORT).show();
                    clearCart();
                }
            });
        }

    }

    private void clearCart() {
        databaseReference.removeValue();
        onBackPressed();
    }

    private void getUserDetails() {
        dBRefUserDets = FirebaseDatabase.getInstance().getReference().child("UserDetails").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        dBRefUserDets.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    userDetails = dataSnapshot.getValue(UserDetails.class);
                    orderSave();
                } else {
                    Toast.makeText(getApplicationContext(), "Enter the details", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), EditInfoActivity.class));


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM / yyyy ");
        String strDate = mdformat.format(calendar.getTime());
        //display(strDate);
        return strDate;

    }

    public String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strTime = mdformat.format(calendar.getTime());
        return strTime;
    }
}
