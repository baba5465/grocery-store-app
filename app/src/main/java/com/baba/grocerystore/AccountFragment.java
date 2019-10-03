package com.baba.grocerystore;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baba.grocerystore.Adapter.AccountListAdapter;
import com.baba.grocerystore.Model.AccountListModel;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class AccountFragment extends Fragment {

    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    List<AccountListModel> listModels = new ArrayList<>();


    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        TextView Username = view.findViewById(R.id.acc_username);
        TextView Email = view.findViewById(R.id.acc_user_email);
        ImageView UserImage = view.findViewById(R.id.acc_user_image);
        Username.setText(firebaseUser.getDisplayName());
        Email.setText(firebaseUser.getEmail());
        Picasso.get().load(firebaseUser.getPhotoUrl()).transform(new CircleTransform()).into(UserImage);
        addListItem();
        final ListView listView = view.findViewById(R.id.acc_list_view);

        AccountListAdapter adapter = new AccountListAdapter(getContext(),R.layout.acc_list_item,listModels);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    startActivity(new Intent(getContext(),MyOrdersActivity.class));

                }else if(i==1){
                    startActivity(new Intent(getContext(),EditInfoActivity.class));
                }else{
                    AuthUI.getInstance()
                            .signOut(getContext())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getContext(),"Logged out",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getContext(),RegisterActivity.class));
                                    getActivity().finish();
                                }
                            });
                }
            }
        });

        return view;
    }
    private void addListItem(){
        listModels.clear();
        listModels.add(new AccountListModel(R.drawable.order,"My Orders"));
        listModels.add(new AccountListModel(R.drawable.edit,"Edit Information"));
        listModels.add(new AccountListModel(R.drawable.logout,"Logout"));
    }

}
