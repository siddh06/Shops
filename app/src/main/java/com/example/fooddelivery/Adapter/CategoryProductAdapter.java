package com.example.fooddelivery.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.Activity.ProductDetailsActivity;
import com.example.fooddelivery.R;
import com.example.fooddelivery.databinding.CategoryProductItemBinding;
import com.example.fooddelivery.modal.CategoryProductModal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class CategoryProductAdapter extends RecyclerView.Adapter<CategoryProductAdapter.CategoryProductViewHolder>{
    Context context;
    ArrayList<CategoryProductModal> categoryProductModalsList;

    public CategoryProductAdapter(Context context, ArrayList<CategoryProductModal> categoryProductModalsList) {
        this.context = context;
        this.categoryProductModalsList = categoryProductModalsList;
    }

    @NonNull
    @Override
    public CategoryProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_product_item, parent,false);
        return new CategoryProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryProductViewHolder holder, int position) {
        CategoryProductModal modal = categoryProductModalsList.get(position);
        Picasso.get().load(modal.getImage()).into(holder.binding.imgProduct);
        holder.binding.txtTitle.setText(modal.getTitle());
        holder.binding.txtPrice.setText("₹"+String.valueOf(modal.getPrice()));

        holder.binding.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(context, ProductDetailsActivity.class);
                    intent.putExtra("productId", modal.getId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryProductModalsList.size();
    }

    public class CategoryProductViewHolder extends RecyclerView.ViewHolder {
        CategoryProductItemBinding binding;
        public CategoryProductViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CategoryProductItemBinding.bind(itemView);
        }
    }
}
