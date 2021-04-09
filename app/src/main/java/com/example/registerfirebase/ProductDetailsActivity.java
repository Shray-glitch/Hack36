package com.example.registerfirebase;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {


    private ViewPager productImagesViewPager;
    private TabLayout viewpagerIndicator;
    private FloatingActionButton addToWishlistButton;
    private static boolean ADDED_TO_WISHLIST=false;

    // for descriptions
    private ViewPager productDetailsViewpager;
    private TabLayout productDetailsTabLayout;

    //firebase cloud for title
    private TextView productTitle;

    // firebase cloud for images
    private FirebaseFirestore firebaseFirestore;

   @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productImagesViewPager = findViewById(R.id.product_images_viewpager);
        viewpagerIndicator = findViewById(R.id.viewpager_indicator);
        addToWishlistButton = findViewById(R.id.add_to_wishlist);

        //for descriptions
       productDetailsViewpager = findViewById(R.id.product_details_viewpager);
       productDetailsTabLayout = findViewById(R.id.product_details_tablayout);

        firebaseFirestore = FirebaseFirestore.getInstance();

        productTitle = findViewById(R.id.product_title);

       List<String> productImages = new ArrayList<>();


        firebaseFirestore.collection("PRODUCTS").document("D4phe54ZB7jIZQnz03cF")
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();

                    for(long x = 1; x < (long)documentSnapshot.get("no_of_product_images") + 1; x++ )
                    {
                        productImages.add(documentSnapshot.get("product_image_"+x).toString());
                    }
                    ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);
                    productImagesViewPager.setAdapter(productImagesAdapter);

                    productTitle.setText(documentSnapshot.get("product_title").toString());

                }
                else {
                    String error = task.getException().getMessage();
                    Toast.makeText(ProductDetailsActivity.this,error, Toast.LENGTH_SHORT).show();
                }
            }
        });


//        List<String> productImages = new ArrayList<>();
//        productImages.add(R.drawable.dos);
//        productImages.add(R.drawable.dos);
//        productImages.add(R.drawable.dos);
//        productImages.add(R.drawable.dos);

//        ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);
//        productImagesViewPager.setAdapter(productImagesAdapter);


        viewpagerIndicator.setupWithViewPager(productImagesViewPager, true);

        addToWishlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ADDED_TO_WISHLIST==true){
                    ADDED_TO_WISHLIST = false;
                    addToWishlistButton.setSupportBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#9e9e9e")));
                }
                else {
                    ADDED_TO_WISHLIST = true;
                    addToWishlistButton.setSupportBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
                }
            }
        });

        // for descriptions
       productDetailsViewpager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(), productDetailsTabLayout.getTabCount()));

       productDetailsViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailsTabLayout));

       productDetailsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

           @Override
           public void onTabSelected(TabLayout.Tab tab) {
               productDetailsViewpager.setCurrentItem(tab.getPosition());
           }

           @Override
           public void onTabUnselected(TabLayout.Tab tab) {

           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {

           }
       });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu; adds items to action bar if it is present
        getMenuInflater().inflate(R.menu.search_and_cart_icon,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handles action bar item clicks here
        int id = item.getItemId();
        if(id == android.R.id.home) {
            finish();
            return true;
        }
        if(id == R.id.main_search_icon) {
            return true;
        }
        if(id == R.id.main_cart) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    private void setSupportActionBar(Toolbar toolbar) {
//    }


}