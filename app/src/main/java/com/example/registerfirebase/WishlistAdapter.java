package com.example.registerfirebase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {

    private List<WishlistModel> wishlistModelList;
    private Boolean wishlist;
    private Context mcontext;

    public WishlistAdapter(List<WishlistModel> wishlistModelList, Boolean wishlist, Context mcontext) {
        this.wishlistModelList = wishlistModelList;
        this.wishlist = wishlist;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wishlist_item_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        String resource = wishlistModelList.get(position).getProductImage();
        String title = wishlistModelList.get(position).getProductTitle();
        String price = wishlistModelList.get(position).getProductPrice();
        String special = wishlistModelList.get(position).getSpeciality();
        String experience = wishlistModelList.get(position).getExperience();
        String mailBtn = wishlistModelList.get(position).getMailBtn();



        viewHolder.setData(resource,title,price,special,experience);

        viewHolder.mailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Intent.ACTION_SEND);
                String[] recipients={"onclicklisteners@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT,"Subject text here...");
                intent.putExtra(Intent.EXTRA_TEXT,"Your Name :\nRequire Service of:\nDetails:");
                intent.putExtra(Intent.EXTRA_CC,"mailcc@gmail.com");
                intent.setType("text/html");
                intent.setPackage("com.google.android.gm");
                mcontext.startActivity(Intent.createChooser(intent, "Send mail"));
            }

        });
    }


    @Override
    public int getItemCount() {
        return wishlistModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView productImage;
        private TextView productTitle;
        private TextView productPrice,Experience,speciality;
        private ImageButton mailBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title1);
            productPrice = itemView.findViewById(R.id.product_price1);
            speciality = itemView.findViewById(R.id.special);
            Experience = itemView.findViewById(R.id.experience);
            mailBtn = itemView.findViewById(R.id.mail_btn);
        }
        private void setData(String resource, String title, String price,String special, String experience){
          //  productImage.setImageResource(resource);

            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.mipmap.ic_launcher)).into(productImage);

            productTitle.setText(title);
            productPrice.setText(price);
            speciality.setText(special);
            Experience.setText(experience);
//            if(wishlist)
//            {
//                deleteBtn.setVisibility(View.VISIBLE);
//            }
//            else {
//                deleteBtn.setVisibility(View.GONE);
//            }
            mailBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(),"Delete",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
