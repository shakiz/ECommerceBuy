package com.ecommerce.shakil.e_commerce_buy;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class FoodAdapterVerticalRecyclerView extends RecyclerView.Adapter<FoodAdapterVerticalRecyclerView.MyViewHolder> {

    private List<Food> foodList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, price;
        ImageView images;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            price = (TextView) view.findViewById(R.id.price);
            images = (ImageView) view.findViewById(R.id.imagerecyclerView);
        }
    }


    public FoodAdapterVerticalRecyclerView(Context context,List<Food> moviesList) {
        this.foodList = moviesList;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_list_row_recyclerview_vertical, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {
        final Food food = foodList.get(position);
        holder.title.setText(food.getFoodtitle());
        holder.price.setText(""+food.getFoodprice());
        holder.images.setImageResource(food.getFoodimage());
        //setting the onclick listener on theimagview of foof item image
        //this will redirect to a new activity where the user can place order
        holder.images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent foodIntent=new Intent(context,ItemDetails.class);
                foodIntent.putExtra("title",food.getFoodtitle());
                foodIntent.putExtra("price",food.getFoodprice());
                context.startActivity(foodIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }
}