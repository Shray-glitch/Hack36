package com.example.registerfirebase.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.registerfirebase.GridProductLayoutAdapter;
import com.example.registerfirebase.GridProductLayoutModel;
import com.example.registerfirebase.ProductDetailsActivity;
import com.example.registerfirebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//        return root;

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        GridView gridView = view.findViewById(R.id.grid_product_layout_gridview);
        TextView gridLayoutTitle = view.findViewById(R.id.grid_layout_title);

        List<GridProductLayoutModel> gridProductLayoutModelList = new ArrayList<>();


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
                                    gridProductLayoutModelList.add(new GridProductLayoutModel(
                                            documentSnapshot.get("product_image_"+x).toString(),
                                            documentSnapshot.get("product_title_"+x).toString(),
                                            documentSnapshot.get("product_price_"+x).toString()
                                            )
                                    );
                                }

                                gridView.setAdapter(new GridProductLayoutAdapter(gridProductLayoutModelList));

                            //    return view;

                            }

                        }
                        else {
                            String error = task.getException().getMessage();
                            Toast.makeText(getContext(),error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });



//        List<GridProductLayoutModel> gridProductLayoutModelList = new ArrayList<>();
//        gridProductLayoutModelList.add(new GridProductLayoutModel(R.drawable.dogs,"Cute Dogs", "Rs.9999/-"));
//        gridProductLayoutModelList.add(new GridProductLayoutModel(R.drawable.dogs,"Cute Dogs", "Rs.9999/-"));
//        gridProductLayoutModelList.add(new GridProductLayoutModel(R.drawable.dogs,"Cute Dogs", "Rs.9999/-"));
//        gridProductLayoutModelList.add(new GridProductLayoutModel(R.drawable.dogs,"Cute Dogs", "Rs.9999/-"));
//        gridProductLayoutModelList.add(new GridProductLayoutModel(R.drawable.dogs,"Cute Dogs", "Rs.9999/-"));
//        gridProductLayoutModelList.add(new GridProductLayoutModel(R.drawable.dogs,"Cute Dogs", "Rs.9999/-"));


        /////// Grid Product
//        TextView gridLayoutTitle = view.findViewById(R.id.grid_layout_title);
     //   GridView gridView = view.findViewById(R.id.grid_product_layout_gridview);

      //  gridView.setAdapter(new GridProductLayoutAdapter(gridProductLayoutModelList));

        return view;

    }
}