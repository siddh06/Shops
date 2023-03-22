package com.example.fooddelivery.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.fooddelivery.Adapter.CategoryAdapter;
import com.example.fooddelivery.Adapter.ProductAdapter;
import com.example.fooddelivery.Adapter.SliderAdapter;
import com.example.fooddelivery.R;
import com.example.fooddelivery.Retrofit.RetrofitClient;
import com.example.fooddelivery.Util.AppPreference;
import com.example.fooddelivery.databinding.ActivityMainBinding;
import com.example.fooddelivery.modal.Category;
import com.example.fooddelivery.modal.Product;
import com.example.fooddelivery.modal.SliderImageModel;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    CategoryAdapter categoryAdapter;
    ProductAdapter productAdapter;
    ArrayList<Category> myCategoryList;
    ArrayList<Product> productArrayList;
    ArrayList<SliderImageModel> sliderImageModels;
    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;
    private HashSet<Integer> addCart;
    //private SharedPreferences sharedPreferences;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*addCart = new HashSet<>();
        addCart.add(2);
        addCart.add(3);
        addCart.add(4);

        String json = new Gson().toJson(addCart);*/

        /*sharedPreferences = getSharedPreferences("MyCartData", MODE_PRIVATE);
        SharedPreferences.Editor myCartEdit = sharedPreferences.edit();

        myCartEdit.putString("cartData",json);
        myCartEdit.commit();*/

        /*AppPreference.getInstance(this).putString("cartData", json);
        Log.e("Mycart", "addCart: "+ addCart.contains(2));*/

        getSliderImages();
        getCategoryList();
        getProducts();

    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.profile:
                        //Toast.makeText(MainActivity.this, "profile Selected", Toast.LENGTH_SHORT).show();
                        Intent profileActivityOpen = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(profileActivityOpen);
                        break;
                    case R.id.cart:
                        Toast.makeText(MainActivity.this, "cart Selected", Toast.LENGTH_SHORT).show();
                        Intent cartActivityOpen = new Intent(MainActivity.this, UserCartActivity.class);
                        startActivity(cartActivityOpen);
                        break;
                    case R.id.like:
                        Toast.makeText(MainActivity.this, "No Like Product Found", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.account:
                        Toast.makeText(MainActivity.this, "Account Selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.contact:
                        Toast.makeText(MainActivity.this, "Contact Selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.about:
                        Toast.makeText(MainActivity.this, "About Selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logout:
                        Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                }
                return false;
            }
        });
    }

    private void getSliderImages() {
        sliderImageModels = new ArrayList<>();
        sliderImageModels.add(new SliderImageModel("https://static.toiimg.com/photo/msid-96426364/96426364.jpg", "hiii siidhhesh"));
        //sliderImageModels.add(new SliderImageModel("https://www.shopickr.com/wp-content/uploads/2021/01/myntra-republic-day-sale-2021.png", "hiii Devendra"));
        //sliderImageModels.add(new SliderImageModel("https://www.jiomart.com/images/cms/aw_rbslider/slides/1655751757_Smart-casuals.jpg", "hiii Devendra"));
        sliderImageModels.add(new SliderImageModel("https://assets.indiadesire.com/images/myntra%20right%20to%20fashion%20sale%20jan%202023.jpg", "hiii Devendra"));
        sliderImageModels.add(new SliderImageModel("https://reviews.com.np/uploads/images/Article/Hyundai/18-10-20/NewFolder/TCL-new%3Dyear-offer-2078-body.jpeg", "hiii Kunal"));

        SliderAdapter adapter = new SliderAdapter(this, sliderImageModels);

        binding.imageSlider.setSliderAdapter(adapter);

        binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.SCALE_DOWN); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        binding.imageSlider.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        binding.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        binding.imageSlider.setIndicatorSelectedColor(Color.WHITE);
        binding.imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        binding.imageSlider.setScrollTimeInSec(4); //set scroll delay in seconds :
        binding.imageSlider.startAutoCycle();
    }

    public void getCategoryList(){
        myCategoryList = new ArrayList<>();

        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2,LinearLayoutManager.HORIZONTAL, false);
        binding.categoryList.setLayoutManager(gridLayoutManager);
        myCategoryList.add(new Category("electronics", R.drawable.electronics));
        myCategoryList.add(new Category("jewelery", R.drawable.jewelery));
        myCategoryList.add(new Category("men's clothing", R.drawable.men_clothes));
        myCategoryList.add(new Category("women's clothing", R.drawable.woman_clothes));
        myCategoryList.add(new Category("Grosarry", R.drawable.grossary));
        myCategoryList.add(new Category("kids Clothing", R.drawable.kid_clothes));
        myCategoryList.add(new Category("Demo", R.drawable.iphone));

        categoryAdapter = new CategoryAdapter(getApplicationContext(),myCategoryList);
        binding.categoryList.setAdapter(categoryAdapter);
        //categoryAdapter = new CategoryAdapter(this,categories);
    }

    public void getProducts(){
        productArrayList = new ArrayList<>();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        binding.rvProductsList.setLayoutManager(gridLayoutManager);
        /*productArrayList.add(new Product("Samsung",R.drawable.fruit2,5000));
        productArrayList.add(new Product("Samsung",R.drawable.fruit1,5000));
        productArrayList.add(new Product("Samsung",R.drawable.fruit3,5000));
        productArrayList.add(new Product("Samsung",R.drawable.fruits2,5000));
        productArrayList.add(new Product("Samsung",R.drawable.fruit1,5000));*/

        Call<List<Product>> myProductList = RetrofitClient.getInstance().getMyApi().getProducts();
        myProductList.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    productArrayList = (ArrayList<Product>) response.body();
                    productAdapter = new ProductAdapter(getApplicationContext(),productArrayList);
                    binding.rvProductsList.setAdapter(productAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error While Requesting", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}