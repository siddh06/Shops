package com.example.fooddelivery.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.Activity.ProductDetailsActivity;
import com.example.fooddelivery.R;
import com.example.fooddelivery.databinding.RvSampleProductBinding;
import com.example.fooddelivery.modal.Product;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    Context context;
    ArrayList<Product> productArrayList;

    public ProductAdapter(Context context, ArrayList<Product> productArrayList){
        this.context = context;
        this.productArrayList = productArrayList;
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_sample_product,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productArrayList.get(position);
        //holder.binding.imgproduct.setImageURI();
        holder.binding.txtproduct.setText(product.getTitle());
        holder.binding.txtprice.setText("From ₹ "+ product.getPrice());
        Picasso.get().load(product.getImage()).into(holder.binding.imgproduct);

        holder.binding.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(context, ProductDetailsActivity.class);
                    intent.putExtra("productId", product.getId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }catch (Exception e){
                    Log.e("ProductDeailsActivity", "onClick: "+ e.getMessage());
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        RvSampleProductBinding binding;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RvSampleProductBinding.bind(itemView);
        }
    }
}
