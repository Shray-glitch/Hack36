package com.example.registerfirebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductSpecificationAdapter extends RecyclerView.Adapter<ProductSpecificationAdapter.ViewHolder> {

    private List<ProductSpecificationModel> productSpecificationModelList;

    public ProductSpecificationAdapter(List<ProductSpecificationModel> productSpecificationModelList) {
        this.productSpecificationModelList = productSpecificationModelList;
    }

    @NonNull
    @Override
    public ProductSpecificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_specification_item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductSpecificationAdapter.ViewHolder viewHolder, int position) {
        String featureTitle = productSpecificationModelList.get(position).getFeatureName();
        String featureDetails = productSpecificationModelList.get(position).getFeatureValue();

        viewHolder.setFeatures(featureTitle, featureDetails);

    }

    @Override
    public int getItemCount() {
        return productSpecificationModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView featureName;
        private TextView featureValue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            featureName = (TextView) itemView.findViewById(R.id.feature_name);
            featureValue = (TextView) itemView.findViewById(R.id.feature_value);
        }
        private void setFeatures(String featureTitle, String featureDetail){
            featureName.setText(featureTitle);
            featureValue.setText(featureDetail);
        }
    }
}
