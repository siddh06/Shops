package com.example.fooddelivery.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.fooddelivery.Adapter.CategoryProductAdapter;
import com.example.fooddelivery.R;
import com.example.fooddelivery.Retrofit.RetrofitClient;
import com.example.fooddelivery.modal.CategoryProductModal;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryProduct extends AppCompatActivity {
    private RecyclerView categoryProductRv;
    private CategoryProductModal categoryProductModal;
    private CategoryProductAdapter categoryProductAdapter;
    private ArrayList<CategoryProductModal> categoryProductList;
    private ProgressDialog progressDialog;
    private final String TAG = "CategoryProduct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_product);

        categoryProductRv = findViewById(R.id.categoryProductRv);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        String categoryName = "";
        Intent intent = getIntent();
        categoryName = intent.getStringExtra("categoryName");
        Log.e("categoryName", "onCreate: "+ categoryName);

        if(!categoryName.isEmpty()){
            getCategoryProduct(categoryName);
        }else {
            Toast.makeText(this, "Category Not Found", Toast.LENGTH_SHORT).show();
        }

    }

    private void getCategoryProduct(String categoryName) {
        categoryProductList = new ArrayList<>();

        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        categoryProductRv.setLayoutManager(manager);

        /*categoryProductList.add(new CategoryProductModal("This is sample Product Title", 200));
        categoryProductList.add(new CategoryProductModal("This is sample Product Title", 200));
        categoryProductList.add(new CategoryProductModal("This is sample Product Title", 200));
        categoryProductList.add(new CategoryProductModal("This is sample Product Title", 200));
        categoryProductList.add(new CategoryProductModal("This is sample Product Title", 200));*/

        try {
            Call<ArrayList<CategoryProductModal>> call = RetrofitClient.getInstance().getMyApi().getCategoryProduct(categoryName);
            call.enqueue(new Callback<ArrayList<CategoryProductModal>>() {
                @Override
                public void onResponse(Call<ArrayList<CategoryProductModal>> call, Response<ArrayList<CategoryProductModal>> response) {
                    if(response.isSuccessful()){
                        categoryProductList = response.body();
                        CategoryProductAdapter adapter = new CategoryProductAdapter(getApplicationContext(), categoryProductList);
                        categoryProductRv.setAdapter(adapter);
                        progressDialog.hide();
                        Toast.makeText(CategoryProduct.this, "Sucessfull", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<CategoryProductModal>> call, Throwable t) {
                    Toast.makeText(CategoryProduct.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            Log.e(TAG, "getCategoryProduct: "+ e.getMessage() );
            Toast.makeText(this, "Error while Requesting", Toast.LENGTH_SHORT).show();
        }
    }
}