package com.example.fooddelivery.Retrofit;

import com.example.fooddelivery.modal.Category;
import com.example.fooddelivery.modal.CategoryProductModal;
import com.example.fooddelivery.modal.Product;
import com.example.fooddelivery.modal.ProductDetailsModal;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    String BASE_URL = "https://fakestoreapi.com/";

    @POST("auth/login")
    Call<Void> login(@Body JsonObject object);

    @GET("products")
    Call<List<Product>> getProducts();

    @GET("products/{id}")
    Call<ProductDetailsModal> getProductDetails(@Path("id") String id);

    @GET("products/category/{name}")
    Call<ArrayList<CategoryProductModal>> getCategoryProduct(@Path("name") String name);
}
