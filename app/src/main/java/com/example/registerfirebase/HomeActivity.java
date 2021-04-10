package com.example.registerfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    Button btnProduct,btnWishlist;
    private ImageButton Android,Web,Design,Ml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
     //   FirebaseAuth.getInstance().signOut();

        //NOT INCLUDING FloatingActionButton
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

//        setContentView(R.layout.content_main);
//
//        btnProduct = (Button)findViewById(R.id.product_details);
        btnWishlist = (Button)findViewById(R.id.wishlist);

//        btnProduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent productDetailsIntent = new Intent(btnProduct.getContext(), ProductDetailsActivity.class);
//                btnProduct.getContext().startActivity(productDetailsIntent);
//            }
//        });

//        Android = (ImageButton) findViewById(R.id.android);
//        Web = (ImageButton)findViewById(R.id.web);
//        Ml = (ImageButton)findViewById(R.id.ml);
//        Design = (ImageButton)findViewById(R.id.design);

//        Android.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent productDetailsIntent1 = new Intent(Android.getContext(), ViewAllActivity.class);
//                Android.getContext().startActivity(productDetailsIntent1);
//            }
//        });
//
//        Web.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent productDetailsIntent1 = new Intent(Web.getContext(), ViewAllActivity.class);
//                Web.getContext().startActivity(productDetailsIntent1);
//            }
//        });
//
//        Ml.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent productDetailsIntent1 = new Intent(Ml.getContext(), ViewAllActivity.class);
//                Ml.getContext().startActivity(productDetailsIntent1);
//            }
//        });
//
//        Design.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent productDetailsIntent1 = new Intent(Design.getContext(), ViewAllActivity.class);
//                Design.getContext().startActivity(productDetailsIntent1);
//            }
//        });


        btnWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent productDetailsIntent1 = new Intent(btnWishlist.getContext(), ViewAllActivity.class);
                btnWishlist.getContext().startActivity(productDetailsIntent1);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}