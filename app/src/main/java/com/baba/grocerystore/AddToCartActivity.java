package com.baba.grocerystore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baba.grocerystore.Model.CartItemModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class AddToCartActivity extends AppCompatActivity {

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Cart/" + FirebaseAuth.getInstance().getCurrentUser().getUid());

        Intent intent = getIntent();
        final String name = intent.getStringExtra("Name");
        final String price = intent.getStringExtra("Price");
        final String image = intent.getStringExtra("Image");
        final String itemId = intent.getStringExtra("Id");

        ImageView itemImage = findViewById(R.id.add_to_cart_image);
        Picasso.get().load(image).into(itemImage);

        TextView itemName = findViewById(R.id.add_to_cart_name);
        itemName.setText(name);

        TextView itemPrice = findViewById(R.id.add_to_cart_price);
        itemPrice.setText("Rs." + price + "/-");

        final EditText quantity = findViewById(R.id.add_to_cart_quantity);

//        final TextView totalPrice = findViewById(R.id.add_to_cart_total);
//        totalPrice.setText("Total Payable Amount : "+Integer.parseInt(quantity.getText().toString())*Integer.parseInt(intent.getStringExtra("Price")));

        Button addToCartBtn = findViewById(R.id.add_to_cart_btn);
        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemId = databaseReference.push().getKey();
                String total = Integer.toString(Integer.parseInt(quantity.getText().toString()) * Integer.parseInt(price));

                CartItemModel cartItemModel = new CartItemModel(name, price, image, itemId, quantity.getText().toString(), total);
                databaseReference.child(itemId).setValue(cartItemModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isComplete()) {
                            Toast.makeText(getApplicationContext(), "Added To Cart", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
