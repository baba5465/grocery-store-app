package com.baba.grocerystore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.baba.grocerystore.Model.UserDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class EditInfoActivity extends AppCompatActivity {

    EditText userName, userEmail, userNo, userAdd, userCity;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference databaseReference1;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("UserDetails");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("UserDetails/" + user.getUid());
        retreiveDetails();
        ImageView userImage = findViewById(R.id.edit_user_image);
        Picasso.get().load(user.getPhotoUrl()).transform(new CircleTransform()).into(userImage);
        userName = findViewById(R.id.edit_user_name);
        userName.setText(user.getDisplayName());
        userEmail = findViewById(R.id.edit_user_email);
        userEmail.setText(user.getEmail());
        userNo = findViewById(R.id.edit_phone_number);
        userAdd = findViewById(R.id.edit_user_address);
        userCity = findViewById(R.id.edit_user_city);
        Button saveBtn = findViewById(R.id.save_details);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userName.getText().toString().isEmpty()) {
                    userName.setError("Required");
                } else if (userEmail.getText().toString().isEmpty()) {
                    userEmail.setError("Required");
                } else if (userNo.getText().toString().isEmpty()) {
                    userNo.setError("Required");
                } else if (userAdd.getText().toString().isEmpty()) {
                    userAdd.setError("Required");
                } else if (userCity.getText().toString().isEmpty()) {
                    userCity.setError("Required");
                } else {
                    saveDetails();
                }
            }
        });
        //retreiveDetails();
    }

    private void saveDetails() {
        UserDetails userDetails = new UserDetails(userName.getText().toString(), userEmail.getText().toString(), userNo.getText().toString(), userAdd.getText().toString(), userCity.getText().toString());
        databaseReference.child(user.getUid()).setValue(userDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void retreiveDetails() {

        if (databaseReference1 != null) {
            databaseReference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    UserDetails userDetails = dataSnapshot.getValue(UserDetails.class);
                    if (dataSnapshot.exists()) {
                        userName.setText(userDetails.getName());
                        userAdd.setText(userDetails.getAddress());
                        userCity.setText(userDetails.getCity());
                        userNo.setText(userDetails.getNumber());
                        userEmail.setText(userDetails.getEmail());
                    } else
                        return;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            return;
        }
    }
}
