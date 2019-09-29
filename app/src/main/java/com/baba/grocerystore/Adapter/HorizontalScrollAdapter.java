package com.baba.grocerystore.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.baba.grocerystore.Model.ProductModel;
import com.baba.grocerystore.R;

import java.util.List;

public class HorizontalScrollAdapter extends RecyclerView.Adapter<HorizontalScrollAdapter.ViewHolder> {

    List<ProductModel> productModel;

    public HorizontalScrollAdapter(List<ProductModel> productModel) {
        this.productModel = productModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.productImage.setImageResource(productModel.get(position).getProductImage());
        holder.productName.setText(productModel.get(position).getProductName());
        holder.productPrice.setText(productModel.get(position).getProductPrice());
    }

    @Override
    public int getItemCount() {
        return productModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView productImage;
        private TextView productPrice;
        private TextView productName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.hor_scr_pro_image);
            productPrice = itemView.findViewById(R.id.hor_scr_pro_price);
            productName = itemView.findViewById(R.id.hor_scr_pro_name);

        }
    }
}
