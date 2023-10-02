package com.example.intent_order;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivityRegister extends AppCompatActivity {
    private EditText editTextName,editTextEmail,editTextPhone;
    private Button buttonCancel,buttonOK;
    private TextView textViewBMI;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);

        setTitle("Register");
        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        
        editTextName = (EditText) findViewById(R.id.editText_name);
        editTextEmail = (EditText) findViewById(R.id.editText_email);
        editTextPhone = (EditText) findViewById(R.id.editText_phone);
        
        editTextPassword = (EditText) findViewById(R.id.editText_password);

        buttonCancel = (Button) findViewById(R.id.button_cancel);
        buttonOK = (Button) findViewById(R.id.button_ok);
        textViewBMI = (TextView) findViewById(R.id.textView_bmi);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextName.setText("");
                editTextEmail.setText("");
                editTextPhone.setText("");
                editTextPassword.setText("");
                textViewBMI.setText("");

            }
        });

        buttonOK.setOnClickListener(new View.OnClickListener() {
            private String name,email,phone,password;

            @Override
            public void onClick(View v) {
                if(editTextName.length()==0 || editTextEmail.length()==0 || editTextPhone.length()==0 ||editTextPassword.length()==0){
                    Toast.makeText(MainActivityRegister.this,"Please input personal information.",Toast.LENGTH_SHORT).show();

                }else{
                    name = editTextName.getText().toString();
                    email = editTextEmail.getText().toString();
                    phone = editTextPhone.getText().toString();
                    password = editTextPassword.getText().toString();
                    textViewBMI.setText("Name : "+name+"\n"+"Email : "+email+"\n"+"Phone : "+phone+"\n"+"Password : "+password);

                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

