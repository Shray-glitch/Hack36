package com.example.registerfirebase.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.registerfirebase.GridProductLayoutAdapter;
import com.example.registerfirebase.GridProductLayoutModel;
import com.example.registerfirebase.R;

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

        List<GridProductLayoutModel> gridProductLayoutModelList = new ArrayList<>();
        gridProductLayoutModelList.add(new GridProductLayoutModel(R.drawable.dogs,"Cute Dogs", "Rs.9999/-"));
        gridProductLayoutModelList.add(new GridProductLayoutModel(R.drawable.dogs,"Cute Dogs", "Rs.9999/-"));
        gridProductLayoutModelList.add(new GridProductLayoutModel(R.drawable.dogs,"Cute Dogs", "Rs.9999/-"));
        gridProductLayoutModelList.add(new GridProductLayoutModel(R.drawable.dogs,"Cute Dogs", "Rs.9999/-"));
        gridProductLayoutModelList.add(new GridProductLayoutModel(R.drawable.dogs,"Cute Dogs", "Rs.9999/-"));
        gridProductLayoutModelList.add(new GridProductLayoutModel(R.drawable.dogs,"Cute Dogs", "Rs.9999/-"));


        /////// Grid Product
        TextView gridLayoutTitle = view.findViewById(R.id.grid_layout_title);
        GridView gridView = view.findViewById(R.id.grid_product_layout_gridview);

        gridView.setAdapter(new GridProductLayoutAdapter(gridProductLayoutModelList));

        return view;

    }
}