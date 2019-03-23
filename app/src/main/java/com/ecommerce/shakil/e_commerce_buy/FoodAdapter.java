package com.ecommerce.shakil.e_commerce_buy;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private ArrayList<String> dataset;
    private ArrayList<String> datasetdes;
    private ArrayList<Double> datasetprice;
    private ArrayList<Integer> datasetImage;

    private Context context;


    public FoodAdapter(Context context,ArrayList<String> dataset,ArrayList<String> datasetdes,ArrayList<Double> datasetprice,ArrayList<Integer> datasetImage){
        this.dataset=dataset;
        this.datasetdes=datasetdes;
        this.datasetprice=datasetprice;
        this.datasetImage=datasetImage;
        this.context=context;
    }

    public FoodAdapter(){

    }
    @NonNull
    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view,parent,false);
        ViewHolder viewholder=new ViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.food_title.setText(dataset.get(position));
        holder.food_description.setText(datasetdes.get(position));
        holder.food_price.setText("Price : "+datasetprice.get(position));
        holder.food_image.setImageResource(datasetImage.get(position));
        //setting the onclick listener on theimagview of foof item image
        //this will redirect to a new activity where the user can place order
        holder.food_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context,"Item name:"+dataset.get(position),Toast.LENGTH_LONG).show();
                Intent foodIntent=new Intent(context,ItemDetails.class);
                foodIntent.putExtra("title",dataset.get(position));
                foodIntent.putExtra("description",datasetdes.get(position));
                foodIntent.putExtra("price",datasetprice.get(position));
                context.startActivity(foodIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView food_title,food_description,food_price;
        ImageView food_image;
        public ViewHolder(View itemView){
            super(itemView);
            food_title=itemView.findViewById(R.id.title);
            food_description=itemView.findViewById(R.id.description);
            food_price=itemView.findViewById(R.id.price);
            food_image=itemView.findViewById(R.id.image);
        }
    }
}
