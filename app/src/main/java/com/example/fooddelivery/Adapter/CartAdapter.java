package com.example.fooddelivery.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.Activity.UserCartActivity;
import com.example.fooddelivery.R;
import com.example.fooddelivery.Util.AppPreference;
import com.example.fooddelivery.databinding.RvSampleUserCartBinding;
import com.example.fooddelivery.modal.CartModel;
import com.example.fooddelivery.modal.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.zip.Inflater;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartAdapterViewHolder>{
    Context context;
    ArrayList<CartModel> cartList;

    public CartAdapter(Context context, ArrayList<CartModel> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_sample_user_cart, parent, false);
        return new CartAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapterViewHolder holder, int position) {
        CartModel model = cartList.get(position);
        holder.binding.txtCartTitle.setText(model.getTitle());
        holder.binding.txtCartPrice.setText(String.valueOf(model.getPrice()));
        Picasso.get().load(model.getImage()).into(holder.binding.imgCartProduct);

        holder.binding.deleteCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myData = AppPreference.getInstance(context).getString("cartData");

                HashSet<Integer> myCartData = new Gson().fromJson(myData, new TypeToken<HashSet<Integer>>(){}.getType());
                myCartData.remove(model.getId());

                String newCartData = new Gson().toJson(myCartData);
                AppPreference.getInstance(context).putString("cartData", newCartData);

                holder.binding.linearLayout.setVisibility(View.GONE);
                //holder.binding.deleteCartBtn.setText("Deleted");
                //holder.binding.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.gray));
                Toast.makeText(context, "Cart Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class CartAdapterViewHolder extends RecyclerView.ViewHolder{
        RvSampleUserCartBinding binding;
        public CartAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RvSampleUserCartBinding.bind(itemView);
        }
    }
}
