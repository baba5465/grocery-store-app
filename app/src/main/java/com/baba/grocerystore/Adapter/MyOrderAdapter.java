package com.baba.grocerystore.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.baba.grocerystore.Model.OrderDetails;
import com.baba.grocerystore.R;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

    List<OrderDetails> orderDetailsList;

    public MyOrderAdapter(List<OrderDetails> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemName.setText(orderDetailsList.get(position).getItemName());
        holder.itemPrice.setText(orderDetailsList.get(position).getItemPrice());
        holder.date.setText(orderDetailsList.get(position).getOrderDate());
        holder.payment.setText(orderDetailsList.get(position).getPaymentStatus());
        holder.quantity.setText("Qty : "+orderDetailsList.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        return orderDetailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemName, itemPrice, quantity, payment, date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.ord_name);
            itemPrice = itemView.findViewById(R.id.ord_price);
            quantity = itemView.findViewById(R.id.ord_quantity);
            payment = itemView.findViewById(R.id.ord_payment_method);
            date = itemView.findViewById(R.id.ord_date);
        }
    }
}
