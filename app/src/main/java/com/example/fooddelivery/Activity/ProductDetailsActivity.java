package com.example.fooddelivery.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fooddelivery.R;
import com.example.fooddelivery.Retrofit.RetrofitClient;
import com.example.fooddelivery.Util.AppPreference;
import com.example.fooddelivery.modal.ProductDetailsModal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.HashSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity {
    int productId;
    private final String TAG = "ProductDetailsActivity";
    private ProgressDialog progressDialog;
    ProductDetailsModal model;
    ImageView imgProductImage;
    TextView txtProductTitle, txtProductPrice, txtProductDesc;
    Button btnAddCart;
    HashSet<Integer> myCartData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        imgProductImage = findViewById(R.id.imgSingleProduct);
        txtProductTitle = findViewById(R.id.txtProductTitle);
        txtProductPrice = findViewById(R.id.txtProductPrice);
        txtProductDesc = findViewById(R.id.txtProductDesc);
        btnAddCart = findViewById(R.id.btnAddCart);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        Intent intent = getIntent();
        productId = intent.getIntExtra("productId", 0);

        if (productId != 0){
            String id = String.valueOf(productId);
            Log.e(TAG, "Product Found: "+ productId );
            getProductDetais(id);
        }else {
            Toast.makeText(this, "Product Not Found: "+ productId, Toast.LENGTH_SHORT).show();
        }

        String mydata = AppPreference.getInstance(ProductDetailsActivity.this).getString("cartData");

        myCartData = new Gson().fromJson(mydata, new TypeToken<HashSet<Integer>>(){}.getType());

        if(myCartData.contains(productId)){
            btnAddCart.setText("Cart Added");
        }
    }

    private void getProductDetais(String productId) {
        try {
            Call<ProductDetailsModal> call = RetrofitClient.getInstance().getMyApi().getProductDetails(productId);
            call.enqueue(new Callback<ProductDetailsModal>() {
                @Override
                public void onResponse(Call<ProductDetailsModal> call, Response<ProductDetailsModal> response) {
                    if(response.isSuccessful()){
                        try {
                            model = response.body();
                            String title = model.getTitle();
                            String price = String.valueOf(model.getPrice());
                            String image = model.getImage();
                            String desc = model.getDescription();

                            //imgProductImage.setImageURI(Uri.parse(image));
                            Picasso.get().load(image).into(imgProductImage);
                            txtProductTitle.setText(title);
                            txtProductPrice.setText("₹ "+price);
                            txtProductDesc.setText(desc);
                            progressDialog.hide();
                        }catch (Exception e){
                            Log.e(TAG, "onResponse: "+ e.getMessage() );
                        }
                    }
                }

                @Override
                public void onFailure(Call<ProductDetailsModal> call, Throwable t) {
                    Toast.makeText(ProductDetailsActivity.this, "Fialed", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            Toast.makeText(this, "Error While Requesting", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "getProductDetais: "+ e.getMessage() );
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myCartData.contains(productId)){
                    btnAddCart.setText("Cart Added");
                    Toast.makeText(ProductDetailsActivity.this, "Product Already Added To Cart List", Toast.LENGTH_SHORT).show();
                }else {
                    myCartData.add(productId);
                    btnAddCart.setText("Cart Added");
                    String addedCart = new Gson().toJson(myCartData);

                    AppPreference.getInstance(ProductDetailsActivity.this).putString("cartData", addedCart);

                    Toast.makeText(ProductDetailsActivity.this, "Added To Cart", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}