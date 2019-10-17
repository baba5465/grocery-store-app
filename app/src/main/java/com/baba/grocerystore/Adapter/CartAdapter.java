package com.baba.grocerystore.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.baba.grocerystore.CartActivity;
import com.baba.grocerystore.Model.CartItemModel;
import com.baba.grocerystore.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    List<CartItemModel> cartItemModels;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Cart/"+FirebaseAuth.getInstance().getCurrentUser().getUid());

    public CartAdapter(List<CartItemModel> cartItemModels) {
        this.cartItemModels = cartItemModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Picasso.get().load(cartItemModels.get(position).getItemImage()).into(holder.itemImage);
        holder.itemName.setText(cartItemModels.get(position).getItemName());
        holder.itemPrice.setText("Rs. "+cartItemModels.get(position).getItemPrice()+"/-");
        holder.itemQuantity.setText("Qty : "+cartItemModels.get(position).getItemQuantity());
        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cartItemModels.size()!=0) {
                    CartActivity.count = 0;
                    databaseReference.child(cartItemModels.get(position).getItemId()).removeValue();
                    //CartActivity.cartItemModels.remove(position);
                    CartActivity.cartAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItemModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView itemImage;
        private TextView itemName, itemPrice, itemQuantity;
        private ImageView deleteItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.cart_image);
            itemName = itemView.findViewById(R.id.cart_item_name);
            itemPrice = itemView.findViewById(R.id.cart_item_price);
            itemQuantity = itemView.findViewById(R.id.cart_item_quantity);
            deleteItem = itemView.findViewById(R.id.cart_delete_item);
        }
    }
}
