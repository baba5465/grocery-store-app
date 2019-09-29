package com.baba.grocerystore.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baba.grocerystore.Model.ProductModel;
import com.baba.grocerystore.R;

import java.util.List;

public class GridProductLayoutAdapter extends BaseAdapter {

    List<ProductModel> gridProductModelList;

    public GridProductLayoutAdapter(List<ProductModel> gridProductModelList) {
        this.gridProductModelList = gridProductModelList;
    }

    @Override
    public int getCount() {
        return 9;
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
            productImage.setImageResource(gridProductModelList.get(i).getProductImage());
            productName.setText(gridProductModelList.get(i).getProductName());
            productPrice.setText(gridProductModelList.get(i).getProductPrice());
        }else {
            view1 = view;
        }
        return view1;
    }
}
