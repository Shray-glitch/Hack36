package com.example.registerfirebase;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    Button btnProduct,btnWishlist;

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
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_user_account)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.nav_contact_us){
                    startActivity(new Intent(HomeActivity.this,contactUs.class));
                }else if(id == R.id.nav_user_account){
                    startActivity(new Intent(HomeActivity.this,myAccount.class));
                }
//                else if(id == R.id.nav_my_wishlist){
//                    startActivity(new Intent(HomeActivity.this,ViewAllActivity.class));
                else if(id == R.id.nav_home){
                 //   startActivity(new Intent(HomeActivity.this,HomeActivity.class));
                }else if(id == R.id.nav_open_shop){

                    //GMAIL FOR OPEN SHOP
                    Intent intent=new Intent(Intent.ACTION_SEND);
                    String[] recipients={"onclicklisteners@gmail.com"};
                    intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                    intent.putExtra(Intent.EXTRA_SUBJECT,"Subject text here...");
                    intent.putExtra(Intent.EXTRA_TEXT,"Name :\nSpecializations:\nDescription:\nDetails:\n(Attach Pictures of your work)");
                    intent.putExtra(Intent.EXTRA_CC,"mailcc@gmail.com");
                    intent.setType("text/html");
                    intent.setPackage("com.google.android.gm");
                    startActivity(Intent.createChooser(intent, "Send mail"));
                }
                return false;
            }
        });

        //setting onclick for drawer fragments
//        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
//            @Override
//            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
//                int menuId = destination.getId();
//                switch (menuId){
//                    case R.id.nav_my_wishlist:
//
//                        break;
//                }
//            }
//        });

//        setContentView(R.layout.content_main);
//
        btnProduct = (Button)findViewById(R.id.product_details);


        //PRODUCT BUTTON ON HOME
        btnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent productDetailsIntent = new Intent(btnProduct.getContext(), ProductDetailsActivity.class);
                btnProduct.getContext().startActivity(productDetailsIntent);
            }
        });

//        //WISHLIST BUTTON ON HOME
//        btnWishlist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent productDetailsIntent1 = new Intent(btnWishlist.getContext(), ViewAllActivity.class);
//                btnWishlist.getContext().startActivity(productDetailsIntent1);
//            }
//        });
//

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