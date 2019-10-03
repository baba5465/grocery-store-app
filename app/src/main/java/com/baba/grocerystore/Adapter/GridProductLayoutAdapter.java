package com.baba.grocerystore.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baba.grocerystore.Model.ProductModel;
import com.baba.grocerystore.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GridProductLayoutAdapter extends BaseAdapter {

    List<ProductModel> gridProductModelList;

    public GridProductLayoutAdapter(List<ProductModel> gridProductModelList) {
        this.gridProductModelList = gridProductModelList;
    }

    @Override
    public int getCount() {
        return gridProductModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1;
        if (view==null){
            view1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_item_layout,null);
            ImageView productImage = view1.findViewById(R.id.grid_item_image);
            TextView productName = view1.findViewById(R.id.grid_item_name);
            TextView productPrice = view1.findViewById(R.id.grid_item_price);
            Picasso.get().load(gridProductModelList.get(i).getImageurl()).into(productImage);
            productName.setText(gridProductModelList.get(i).getItemname());
            productPrice.setText("Rs."+gridProductModelList.get(i).getItemprice()+"/-");
        }else {
            view1 = view;
        }
        return view1;
    }
}
