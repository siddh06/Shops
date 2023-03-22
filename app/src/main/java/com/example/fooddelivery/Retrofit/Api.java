package com.example.fooddelivery.Retrofit;

import com.example.fooddelivery.modal.Category;
import com.example.fooddelivery.modal.CategoryProductModal;
import com.example.fooddelivery.modal.Product;
import com.example.fooddelivery.modal.ProductDetailsModal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
    String BASE_URL = "https://fakestoreapi.com/";

    @GET("products")
    Call<List<Product>> getProducts();

    @GET("products/{id}")
    Call<ProductDetailsModal> getProductDetails(@Path("id") String id);

    @GET("products/category/{name}")
    Call<ArrayList<CategoryProductModal>> getCategoryProduct(@Path("name") String name);
}
