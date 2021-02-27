package com.mycontactsapp;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditingContactPage extends AppCompatActivity {

    Contacts contacts;
    ContactsDataBase CDB;
    EditText firstName;EditText phoneNo;EditText addressNo;EditText emailAdd;
    int holderID;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_contact_page);
        CDB = new ContactsDataBase(getApplicationContext());

        firstName = findViewById(R.id.EfirstName);
        phoneNo = findViewById(R.id.EmobileNo);
        addressNo = findViewById(R.id.Eaddress);
        emailAdd = findViewById(R.id.Eemail);

        toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        toggle.syncState();

        Bundle b = getIntent().getExtras();

        firstName.setText(b.getString("EDITING-NAME"));
        phoneNo.setText(b.getString("EDITING-NUMBER"));
        addressNo.setText(b.getString("EDITING-ADDRESS"));
        emailAdd.setText(b.getString("EDITING-EMAIL"));
        holderID = Integer.parseInt(b.getString("EDITING-CONTACT-ID"));

//        Toast.makeText(this, "NAME = " + b.getString("EDITING-NAME"), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "NUMBER = " + b.getString("EDITING-NUMBER"), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "ADDRESS = " + b.getString("EDITING-ADDRESS"), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "EMAIL = " + b.getString("EDITING-EMAIL"), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "ID = " + b.getString("EDITING-CONTACT-ID"), Toast.LENGTH_SHORT).show();
    }

    public void discardContact(View v){
        Toast.makeText(this, "Discarding Contact...", Toast.LENGTH_SHORT).show();
        phoneNo.setText(null);
        firstName.setText(null);
        emailAdd.setText(null);
        addressNo.setText(null);
    }

    public void saveContact(View v){
        String HolderIDString = Integer.toString(holderID);
        contacts = new Contacts(firstName.getText().toString(),Long.parseLong(phoneNo.getText().toString()),
                addressNo.getText().toString(),emailAdd.getText().toString());
        int result = CDB.update(HolderIDString, contacts);
        try{
            if(result > 0){
                Toast.makeText(this, "UPDATED YOUR CONTACT SUCCUSSFULLY", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "COULDN'T UPDATE YOUR CONTACT SUCCUSSFULLY...TRY AGAIN", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Toast.makeText(this, e.getMessage() + "\n" + "Ooops Something went Wrong..." , Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}