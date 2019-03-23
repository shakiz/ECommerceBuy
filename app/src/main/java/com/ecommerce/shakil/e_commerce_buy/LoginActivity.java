package com.ecommerce.shakil.e_commerce_buy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

public class LoginActivity extends AppCompatActivity {

    MaterialEditText email,password;
    Button signin;
    TextView signup;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //to initialize the all attributes
        init();
        initObjects();
        //signin click listener here, this method will handle the sign in button clcik action
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyFromSQLite();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }

    /**
     * This method is to initialize all the components that will be used from xml
     */
    public void init(){
        email=findViewById(R.id.ediTextEmailAddress);
        password=findViewById(R.id.ediTextPassword);
        signin=findViewById(R.id.signIn);
        signup=findViewById(R.id.signup);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHelper(getApplicationContext());
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {
        if (email.getText().toString().isEmpty()) {
            email.setError("Username can not be empty.");
        }
        if (password.getText().toString().isEmpty()) {
            password.setError("Password field can not be empty.");
        }
        if (databaseHelper.checkUser(email.getText().toString()
                , password.getText().toString())) {
            Intent accountsIntent = new Intent(getApplicationContext(), MainActivity.class);
            accountsIntent.putExtra("email", email.getText().toString().trim());
            accountsIntent.putExtra("password",password.getText().toString().trim());
            startActivity(accountsIntent);


        } else {
            // toast to show success message that record is wrong
            Toast.makeText(getApplicationContext(),"Username and password do not match.",Toast.LENGTH_LONG).show();
        }
    }


}
