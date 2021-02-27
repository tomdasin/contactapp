package com.mycontactsapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class AddContactActivity extends AppCompatActivity {

    Contacts contacts;ContactsDataBase CDB;
    EditText firstName;EditText phoneNo;EditText addressNo;EditText emailAdd;
//    String fullName;String phoneNumber;String houseAdd;String email;
//    long phoneNum;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        CDB = new ContactsDataBase(getApplicationContext());

        firstName = findViewById(R.id.firstName);
        phoneNo = findViewById(R.id.mobileNo);
        addressNo = findViewById(R.id.address);
        emailAdd = findViewById(R.id.email);
        toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        CDB = new ContactsDataBase(getApplicationContext());

//        Bundle b = getIntent().getExtras();
//        firstName.setText(b.getString("EDITING-NAME"));
//        phoneNo.setText(b.getString("EDITING-NUMBER"));
//        emailAdd.setText(b.getString("EDITING-EMAIL"));
//        addressNo.setText(b.getString("EDITING-ADDRESS"));

    }
    public void discardContact(View v){
        Toast.makeText(this, "Discarding Contact...", Toast.LENGTH_SHORT).show();
        phoneNo.setText(null);
        firstName.setText(null);
        emailAdd.setText(null);
        addressNo.setText(null);
    }
    public void saveContact(View v){
        String fullName = firstName.getText().toString();
        String phoneNumber = phoneNo.getText().toString();
        String email = emailAdd.getText().toString();
        String houseAdd = addressNo.getText().toString();
//        int length = fullName.length();

        try{
            if(phoneNumber.length() == 0 && fullName.length() == 0)
                Toast.makeText(AddContactActivity.this, "Name And Number field must not be EMPTY.", Toast.LENGTH_SHORT).show();
            else if(fullName.length() > 0 && phoneNumber.length() == 0 )
                Toast.makeText(AddContactActivity.this, "Please fill in the Contact Number.", Toast.LENGTH_SHORT).show();
            else if(fullName.length() == 0 && phoneNumber.length() > 0 )
                Toast.makeText(AddContactActivity.this, "Please fill in the Contact Name.", Toast.LENGTH_SHORT).show();
            else{
                long phoneNum = Long.parseLong(phoneNumber);
                contacts = new Contacts(fullName, phoneNum, email, houseAdd);
                long resultFrom = CDB.addToContactBase(contacts);
                if(resultFrom != -1){
                    Toast.makeText(AddContactActivity.this, "Added Successfully!...", Toast.LENGTH_SHORT).show();
                    finish();
                }else{ Toast.makeText(getApplicationContext(), "Unsuccessful...Try Again.", Toast.LENGTH_SHORT).show(); }
            }
        }catch(Exception e){
            Toast.makeText(AddContactActivity.this,
                    e.getMessage() + "Failed to Add Contact Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.toolbar_default_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.help:
                Toast.makeText(getApplicationContext(),"Help...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.moreOptions:
                Toast.makeText(getApplicationContext(),"More Options Clicked...", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return true;
    }

    public void moreOptions(View v){
        Toast.makeText(this, "Options...", Toast.LENGTH_SHORT).show();
    }
    public void changeDisplayPicture(View v){
        Toast.makeText(this, "Change display picture...", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(AddContactActivity.this, DisplayContact.class);
//        startActivity(intent);
    }
    public void moveBack(View v){
        Toast.makeText(this, "Back...", Toast.LENGTH_SHORT).show();
    }
}
