package com.example.fooddelivery.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fooddelivery.Adapter.CartAdapter;
import com.example.fooddelivery.Adapter.ProductAdapter;
import com.example.fooddelivery.R;
import com.example.fooddelivery.Retrofit.RetrofitClient;
import com.example.fooddelivery.Util.AppPreference;
import com.example.fooddelivery.modal.CartModel;
import com.example.fooddelivery.modal.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PrimitiveIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserCartActivity extends AppCompatActivity {

    private Button deleteCartBtn;
    private ArrayList<Product> myCartList;
    private RecyclerView rvCartList;
    private CartAdapter cartAdapter;
    private ArrayList<CartModel> cartModelsList;
    private HashSet<Integer> addCart;
    private TextView txtEmtyCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cart);

        rvCartList = findViewById(R.id.cartRv);
        txtEmtyCart = findViewById(R.id.txtEmtyCart);

        String myString = AppPreference.getInstance(this).getString("cartData");
        Log.e("Mycart", "Mycart mystring: "+ myString );

        addCart = new Gson().fromJson(myString, new TypeToken<HashSet<Integer>>(){}.getType());

        getCartList();
    }

    private void getCartList() {
        myCartList = new ArrayList<>();
        cartModelsList = new ArrayList<>();

        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvCartList.setLayoutManager(manager);

        Call<List<Product>> myProductList = RetrofitClient.getInstance().getMyApi().getProducts();
        myProductList.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    myCartList = (ArrayList<Product>) response.body();
                    showAddedCartList();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(UserCartActivity.this, "Error While Requesting", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showAddedCartList() {
        try {
            for(int i = 0; i < myCartList.size(); i++){
                int id = myCartList.get(i).getId();
                if(!addCart.isEmpty() && addCart.contains(id)){
                    Product product = myCartList.get(i);
                    cartModelsList.add(new CartModel(product.getId(),product.getTitle(), product.getImage(), product.getPrice()));
                    Log.e("MyCart", "Cart List: "+ id );
                }
            }

            Log.e("MyCart", "cartModelsList size: "+ cartModelsList.size() );
            if(!cartModelsList.isEmpty()){
                txtEmtyCart.setVisibility(View.GONE);
                cartAdapter = new CartAdapter(getApplicationContext(), cartModelsList);
                rvCartList.setAdapter(cartAdapter);
            }else {
                Log.e("MyCart", "showAddedCartList: "+ cartModelsList.size());
                txtEmtyCart.setVisibility(View.VISIBLE);
            }

        }catch (Exception e){
            Log.e("UserCartACtivity", "onResponse: "+ e.getMessage() );
        }
    }
}