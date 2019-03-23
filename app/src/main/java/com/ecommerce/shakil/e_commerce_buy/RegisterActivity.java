package com.ecommerce.shakil.e_commerce_buy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

public class RegisterActivity extends AppCompatActivity {

    MaterialEditText username,email,password;
    Button signup;
    TextView signin;

    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //to initialize the all attributes
        init();
        initObjects();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postDataToSQLite();
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }

    /**
     * This method is to initialize all the components that will be used from xml
     */
    public void init(){
        username=findViewById(R.id.ediTextUsername);
        email=findViewById(R.id.ediTextEmail);
        password=findViewById(R.id.ediTextPassword);
        signin=findViewById(R.id.signintextview);
        signup=findViewById(R.id.signup);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHelper(getApplicationContext());
        user = new User();
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {
        if (username.getText().toString().isEmpty()) {
            username.setError("Username can not be empty.");
        }
        if (email.getText().toString().isEmpty()) {
            email.setError("Email field can not be empty.");
        }
        if (password.getText().toString().isEmpty()) {
            password.setError("Password can not be empty.");
        }

        if (!databaseHelper.checkUser(email.getText().toString().trim())) {

            user.setName(username.getText().toString().trim());
            user.setEmail(email.getText().toString().trim());
            user.setPassword(password.getText().toString().trim());

            databaseHelper.addUser(user);

            // Snack Bar to show success message that record saved successfully
            Toast.makeText(getApplicationContext(),"Data registered successfully.",Toast.LENGTH_LONG).show();
            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

        } else {
            // Snack Bar to show error message that record already exists
            Toast.makeText(getApplicationContext(),"Registration was unsuccessfull..",Toast.LENGTH_LONG).show();
        }


    }
}
