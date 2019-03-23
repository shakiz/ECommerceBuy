package com.ecommerce.shakil.e_commerce_buy;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView email,password;
    String usernameStr,passwordStr;
    Typeface fontface;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerviewLayoutManager;
    RecyclerView.Adapter recyclerViewAdapter;
    //arraylists for recylcerview items
    private ArrayList<String> datasetFoodName;
    private ArrayList<String> datasetFoodDescription;
    private ArrayList<Double> datasetFoodPrice;
    private ArrayList<Integer> datasetFoodImage;
    //items for vertical food items in recyclerview
    private List<Food> foodlistforRecyclerviewVertical = new ArrayList<>();
    private RecyclerView recyclerViewVertical;
    private FoodAdapterVerticalRecyclerView mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //this method called here to initialize all the attributes
        init();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //this method is just called here to get the intent values
        //getIntentValues();

    }
    /*
    This method will be used to initialize all the xml attributes with java classes
    */
    public void init(){
        //email=findViewById(R.id.textViewUserEmail);
        //password=findViewById(R.id.textViewUserPassword);
        recyclerView=findViewById(R.id.foodRecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerViewVertical = (RecyclerView) findViewById(R.id.recyclerviewVertical);
        //for initializing the arraylists we will be used this method
        initTheArraylists();
        //in order to insert the data into those arraylists we will be used this method
        arraylistdataInsertion();
        //this will be used for the horizontal recyclerview food items
        recyclerviewLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(recyclerviewLayoutManager);
        recyclerViewAdapter=new FoodAdapter(this,datasetFoodName,datasetFoodDescription,datasetFoodPrice,datasetFoodImage);
        recyclerView.setAdapter(recyclerViewAdapter);

        //this will be used for the horizontal recyclerview food items
        mAdapter = new FoodAdapterVerticalRecyclerView(getApplicationContext(),foodlistforRecyclerviewVertical);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewVertical.setLayoutManager(mLayoutManager);
        recyclerViewVertical.setItemAnimator(new DefaultItemAnimator());
        recyclerViewVertical.setAdapter(mAdapter);


        //listViewadapter= new CustomListviewFoodItemAdapter(getApplicationContext(),dataModels);
        //listView.setAdapter(listViewadapter);
    }
    /*
    This method will be used to initialize all the arralylists that we will be used int the system
    */
    private void initTheArraylists() {
        datasetFoodDescription=new ArrayList<>();
        datasetFoodName=new ArrayList<>();
        datasetFoodPrice=new ArrayList<>();
        datasetFoodImage=new ArrayList<>();
        }

    /*
    This method will be used to insert all the data for food name, food description and food image
    */
    private void arraylistdataInsertion() {
        //adding the food name
        datasetFoodName.add("Pasta");
        datasetFoodName.add("Kabab");
        datasetFoodName.add("Halim");
        datasetFoodName.add("Chciken Tikka");
        datasetFoodName.add("Grill Chicken");
        datasetFoodName.add("Biriani");
        datasetFoodName.add("Tehari");
        //adding the food description
        datasetFoodDescription.add("A food made from a mixture of flour, water.");
        datasetFoodDescription.add("Reshmi Kabab Recipe With Marinated Chicken.");
        datasetFoodDescription.add("Haleem is made of wheat, barley, meat.");
        datasetFoodDescription.add("It is traditionally small pieces of boneless chicken baked.");
        datasetFoodDescription.add("Barbecue chicken consists of chicken parts.");
        datasetFoodDescription.add("A world-renowned Indian dish, biryani takes time.");
        datasetFoodDescription.add("Tehari is popular in other parts of Bangladeh.");
        //adding the food prices
        datasetFoodPrice.add(200.0);
        datasetFoodPrice.add(190.0);
        datasetFoodPrice.add(120.0);
        datasetFoodPrice.add(290.0);
        datasetFoodPrice.add(320.0);
        datasetFoodPrice.add(120.0);
        datasetFoodPrice.add(140.0);
        //adding the food image
        datasetFoodImage.add(R.drawable.pasta);
        datasetFoodImage.add(R.drawable.kabab);
        datasetFoodImage.add(R.drawable.halim);
        datasetFoodImage.add(R.drawable.tandorichicken);
        datasetFoodImage.add(R.drawable.grilledchicken);
        datasetFoodImage.add(R.drawable.biriani);
        datasetFoodImage.add(R.drawable.tehari);


        //adding data into vertical recyclerview food items
        foodlistforRecyclerviewVertical.add(new Food("Pizza",220.0,R.drawable.pizza));
        foodlistforRecyclerviewVertical.add(new Food("Pasta",320.0,R.drawable.pasta));
        foodlistforRecyclerviewVertical.add(new Food("Tandori Chicken",290.0,R.drawable.tandorichicken));
        foodlistforRecyclerviewVertical.add(new Food("Veggie Burger",340.0,R.drawable.veggieburger));
        foodlistforRecyclerviewVertical.add(new Food("Grilled Chicken",320.0,R.drawable.grilledchicken));
        foodlistforRecyclerviewVertical.add(new Food("Fried Chicken",300.0,R.drawable.friedchicken));
        foodlistforRecyclerviewVertical.add(new Food("French Fries",110.0,R.drawable.frenchfries));

    }

    /*
    This method will be used to get all the data passes from the previous activity
    */
    public void getIntentValues(){
        Intent intent=getIntent();
        usernameStr=intent.getExtras().getString("email");
        passwordStr=intent.getExtras().getString("password");

        fontface=Typeface.createFromAsset(getAssets(),"fonts/NABILA.TTF");

        email.setText("Email : "+usernameStr);
        email.setTypeface(fontface);
        password.setText("Password : "+passwordStr);
        password.setTypeface(fontface);
        Log.d("EMAIL : ",""+usernameStr);
        Log.d("PASSWORD : ",""+passwordStr);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_items_to_buy) {
            // Handle the camera action
        }
        else if (id == R.id.nav_edit_profile) {

        }
        else if (id == R.id.nav_share) {

        }
        else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
