package com.example.registerfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Cute Dogs");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        List<WishlistModel> wishlistModelList = new ArrayList<>();
//        wishlistModelList.add(new WishlistModel(R.drawable.dogs,"Cute Dogs!!","Rs.10000/-"));
//        wishlistModelList.add(new WishlistModel(R.drawable.dogs,"Cute Dogs!!","Rs.10000/-"));
//        wishlistModelList.add(new WishlistModel(R.drawable.dogs,"Cute Dogs!!","Rs.10000/-"));
//        wishlistModelList.add(new WishlistModel(R.drawable.dogs,"Cute Dogs!!","Rs.10000/-"));
//        wishlistModelList.add(new WishlistModel(R.drawable.dogs,"Cute Dogs!!","Rs.10000/-"));
//        wishlistModelList.add(new WishlistModel(R.drawable.dogs,"Cute Dogs!!","Rs.10000/-"));
//        wishlistModelList.add(new WishlistModel(R.drawable.dogs,"Cute Dogs!!","Rs.10000/-"));
//        wishlistModelList.add(new WishlistModel(R.drawable.dogs,"Cute Dogs!!","Rs.10000/-"));



        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseFirestore.collection("CATEGORIES")
                .document("ANDROID BACKEND")
                .collection("TOP_DEALS")
                .orderBy("index")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                //  List<GridProductLayoutModel> gridProductLayoutModelList = new ArrayList<>();

                                long no_of_products = (long)documentSnapshot.get("no_of_products");

                                for(long x = 1; x < no_of_products + 1; x++ )
                                {
                                   wishlistModelList.add(new WishlistModel(
                                                    documentSnapshot.get("image_"+x).toString(),
                                                    documentSnapshot.get("title_"+x).toString(),
                                                    documentSnapshot.get("price_"+x).toString(),
                                                    documentSnapshot.get("special_"+x).toString(),
                                           documentSnapshot.get("experience_"+x).toString()

                                            )
                                    );
                                }

                                WishlistAdapter adapter = new WishlistAdapter(wishlistModelList,false);
                                recyclerView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();


                            }

                        }
                        else {
                            String error = task.getException().getMessage();
                            Toast.makeText(ViewAllActivity.this,error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });



//        WishlistAdapter adapter = new WishlistAdapter(wishlistModelList,false);
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home ) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}