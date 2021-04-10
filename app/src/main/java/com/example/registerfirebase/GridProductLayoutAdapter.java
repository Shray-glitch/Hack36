package com.example.registerfirebase;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.List;

public class GridProductLayoutAdapter extends BaseAdapter {

    List<GridProductLayoutModel> gridProductLayoutModelList;

    public GridProductLayoutAdapter(List<GridProductLayoutModel> gridProductLayoutModelList) {
        this.gridProductLayoutModelList = gridProductLayoutModelList;
    }


    @Override
    public int getCount() {

        //// return number of items to show

        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_layout, null);
            view.setElevation(0);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent(parent.getContext(), ProductDetailsActivity.class);
                    parent.getContext().startActivity(productDetailsIntent);
                }
            });

            ImageView productImage = view.findViewById(R.id.product_grid_image);
            TextView productTitle = view.findViewById(R.id.product_grid_title);
            TextView productPrice = view.findViewById(R.id.product_grid_price);

            productImage.setImageResource(gridProductLayoutModelList.get(position).getProductImage());
            productTitle.setText(gridProductLayoutModelList.get(position).getProductTitle());
            productPrice.setText(gridProductLayoutModelList.get(position).getProductPrice());
        }
        else {
            view = convertView;
        }
        return view;
    }
}
