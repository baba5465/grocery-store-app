package com.baba.grocerystore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.baba.grocerystore.Model.AccountListModel;
import com.baba.grocerystore.R;


import java.util.List;

public class AccountListAdapter extends ArrayAdapter<AccountListModel> {
    List<AccountListModel> listModels;
    int resource;
    Context context;
    public AccountListAdapter(Context context, int resource, List<AccountListModel> listModels) {
        super(context, resource, listModels);
        this.context = context;
        this.resource = resource;
        this.listModels = listModels;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resource,null,false);
        ImageView image = view.findViewById(R.id.acc_item_icon);
        TextView text = view.findViewById(R.id.acc_item_text);
        image.setImageResource(listModels.get(position).getImage());
        text.setText(listModels.get(position).getText());
        return view;
    }
}
