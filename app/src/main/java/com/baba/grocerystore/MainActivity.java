package com.baba.grocerystore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String firstName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName().split(" ")[0];
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Hi, "+ firstName);


        final HomeFragment homeFragment = new HomeFragment();
        final CategoryFragment categoryFragment = new CategoryFragment();
        final AccountFragment accountFragment = new AccountFragment();
        //final SearchFragment searchFragment = new SearchFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,homeFragment).commit();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,homeFragment).commit();
                        return true;
                    case R.id.category:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,categoryFragment).commit();
                        return true;
//                    case R.id.search:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,searchFragment).commit();
//                        return true;
                    case R.id.account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,accountFragment).commit();
                        return true;
                }

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cart:
                startActivity(new Intent(getApplicationContext(),CartActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}