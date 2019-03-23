package com.ecommerce.shakil.e_commerce_buy;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ItemDetails extends AppCompatActivity {

    private TextView foodtitle,fooddescription,foodprice,quantity,loccationtxt,name,txt_price,quantity_txt,txt_location;
    private Button orderButton,increase,decrease;

    private int count=0;

    private String titleStr,descriptionStr,locationStr;
    private double priceDouble;

    private Spinner location;
    private List<String> locationList;

    private double price;

    private CheckBox deliveryCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        /*
            THIS METHOD WILL BE USED TO INITIALIZE ALL THE ATTRIBUTES
        */
        init();
        /*
            THIS METHOD WILL BE USED TO GET ALL THE INTENT DATA PASSING FROM THE ALL FOOD ITEM ACTIVITY
        */
        getIntentData();
        /*
            THIS METHOD WILL BE USED TO GET ALL THE INTENT DATA PASSING FROM THE ALL FOOD ITEM ACTIVITY
        */
        setData();
        /*
            THIS METHOD WILL BE USED TO ADD DATA INTO THE LOCATION SPINNER
        */
        setLocationData();
        /*
            THIS METHOD WILL BE USED TO CHECK WHETHER THE USER CHECK THE CHECKBOX OR NOT
        */
        checkbox_clicked();
        /*
            THIS METHOD WILL BE USED TO SET THE QUANTITY OF THAT FOOD ITEM
        */
        setQuantity();
        //The on click listener for button order in order to order something or food
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                    THIS METHOD WILL BE USED TO ORDER FOR ITEM
                */
                order();
            }
        });
    }

    /*
        THIS METHOD WILL BE USED TO INITIALIZE ALL THE ATTRIBUTES
    */

    public void init(){
        foodtitle=findViewById(R.id.foodTitle);
        fooddescription=findViewById(R.id.foodDescription);
        foodprice=findViewById(R.id.foodPrice);
        location=findViewById(R.id.locationSpinner);
        orderButton=findViewById(R.id.placeOrderButton);
        increase=findViewById(R.id.increaseOrder);
        decrease=findViewById(R.id.decreaseOrder);
        quantity=findViewById(R.id.quantity);
        deliveryCheckbox=findViewById(R.id.deliveryCheckbox);
        loccationtxt=findViewById(R.id.locationTextView);

        locationList=new ArrayList<>();
    }
    /*
        THIS METHOD WILL BE USED TO ORDER FOR ITEM
    */
    public void order(){
        if ((count>0) && (deliveryCheckbox.isChecked()) && (!loccationtxt.equals("")) && (!loccationtxt.equals("Please select your delivery location"))){
            price=count*priceDouble;
            //Toast.makeText(getApplicationContext(),"Quantity : "+count+"\nLocation : "+locationStr+"\nPrice : "+price,Toast.LENGTH_LONG).show();
            try{
                //here we are creating a custom dialog in order to show the invoice details
                Dialog dialog=new Dialog(ItemDetails.this);
                dialog.setContentView(R.layout.custom_layout);
                dialog.setTitle("Invoice");
                dialog.show();
                //initilizing the custom layouts components
                name=dialog.findViewById(R.id.tv_item_namee);
                txt_price=dialog.findViewById(R.id.tv_item_price);
                quantity_txt=dialog.findViewById(R.id.tv_quantity);
                txt_location=dialog.findViewById(R.id.tv_location);
                //setting the texts of custom layout items
                name.setText("Item name : "+titleStr);
                txt_price.setText("Total Price : "+price);
                quantity_txt.setText("Quantity : "+count);
                txt_location.setText("Location : "+locationStr);
            }catch(Exception e){
                Toast.makeText(getApplicationContext(),""+e.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("EXCEPTION",""+e.getMessage());
                Log.e("EXCEPTION",""+e.getMessage());
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"Please select each component properly.",Toast.LENGTH_LONG).show();
        }
    }
    /*
        THIS METHOD WILL BE USED TO SET THE QUANTITY OF THAT FOOD ITEM
    */
    public void setQuantity(){
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count>=0){
                    count++;
                    quantity.setText("Quantity : "+count);
                }
            }
        });

        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count>1){
                    count--;
                    quantity.setText("Quantity : "+count);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Quantity can not be less than 1",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /*
        THIS METHOD WILL BE USED TO CHECK WHETHER THE USER CHECK THE CHECKBOX OR NOT
    */
    public void checkbox_clicked() {
        deliveryCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(deliveryCheckbox.isChecked()) {
                    Toast.makeText(getApplicationContext(),"Delivery Method Selected.",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Delivery Method Not Selected.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    /*
        THIS METHOD WILL BE USED TO ADD DATA INTO THE LOCATION SPINNER
    */
    public void setLocationData(){
        locationList.add("Please select your delivery location");
        locationList.add("Kazipara");
        locationList.add("Shewrapara");
        locationList.add("Badhundhara Residential Area");
        locationList.add("Uttara");
        locationList.add("Banani");
        locationList.add("Mohammadpur");
        locationList.add("Shaymoli");
        locationList.add("Mirpur-1");
        locationList.add("Mirpur-10");
        locationList.add("Mohakhali DOHS");
        locationList.add("Banani DOHS");
        locationList.add("Mirpur DOHS");
        locationList.add("Dhaka Cantonment");
        locationList.add("Dhanmondi-08");
        locationList.add("Dhanmondi-15");
        locationList.add("Dhanmondi-27");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, locationList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        location.setAdapter(dataAdapter);

        //selecting or declaring the on select item listener of spinner
        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loccationtxt.setText("Location : "+locationList.get(i));
                locationStr=locationList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(),"Please select a location.",Toast.LENGTH_LONG).show();
            }
        });

    }

    /*
        THIS METHOD WILL BE USED TO GET ALL THE INTENT DATA PASSING FROM THE ALL FOOD ITEM ACTIVITY
    */
    public void getIntentData(){
        Intent intent=getIntent();
        titleStr=intent.getExtras().getString("title");
        descriptionStr=intent.getExtras().getString("description");
        priceDouble=intent.getExtras().getDouble("price");
    }

    /*
        THIS METHOD WILL BE USED TO SET THE DATA INTO TEXTVIEWS
    */
    public void setData(){
        foodtitle.setText("Item Name : "+titleStr);
        fooddescription.setText("Description : "+descriptionStr);
        foodprice.setText("Item Price : "+priceDouble);
    }
}
