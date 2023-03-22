package com.example.fooddelivery.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.fooddelivery.R;
import com.example.fooddelivery.databinding.SampleSliderImgBinding;
import com.example.fooddelivery.modal.SliderImageModel;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderViewHolder>{
    Context context;
    //int[] mydata;
    List<SliderImageModel> sliderImageModels = new ArrayList<>();

    public SliderAdapter(Context context, List<SliderImageModel> sliderImageModels) {
        this.context = context;
        this.sliderImageModels = sliderImageModels;
    }

    /*public void renewItems(List<SliderImageModel> sliderItems) {
        this.sliderImageModels = sliderItems;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.sliderImageModels.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(SliderImageModel sliderItem) {
        this.sliderImageModels.add(sliderItem);
        notifyDataSetChanged();
    }*/

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_slider_img, null);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder viewHolder, int position) {
        SliderImageModel model = sliderImageModels.get(position);
        //Log.e("SliderAdapter", "onBindViewHolder: "+ url);
        //Glide.with(viewHolder.itemView).load(model.getImage()).fitCenter().into(viewHolder.binding.imgSlide);
        //viewHolder.binding.imgSlide.setImageResource(mydata[position]);
        //viewHolder.img.setImageResource(mydata[position]);
        Picasso.get().load(model.getImage()).into(viewHolder.img);
        //viewHolder.txtDes.setText(model.getTitle());
    }

    @Override
    public int getCount() {
        return sliderImageModels.size();
    }

    public class SliderViewHolder extends SliderViewAdapter.ViewHolder {
        ImageView img;
        //TextView txtDes;
        View itemView;
        public SliderViewHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imgSlide);
            //txtDes = itemView.findViewById(R.id.txtSlideImg);
            this.itemView = itemView;
        }
    }
}
