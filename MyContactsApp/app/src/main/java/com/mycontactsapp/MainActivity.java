package com.mycontactsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab; Toolbar toolbar;
    ListView contactList; ArrayList<String> contactBook; StringBuilder SB; CustomAdapter CA;
    ContactsDataBase CDB;
    Cursor cursor;
    TextView noOfContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noOfContacts = findViewById(R.id.noOfContacts);
        fab = findViewById(R.id.floatingActionButton);
        contactList = findViewById(R.id.usersContacts);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        CDB = new ContactsDataBase(this);
        try{
            contactBook = CDB.returningContactNames();

            Toast.makeText(this, "Before Removing...Size = "+ contactBook.size(), Toast.LENGTH_SHORT).show();

            if(contactBook.size() == 0){
                Toast.makeText(this, "No Contacts to Display yet!...", Toast.LENGTH_SHORT).show();
            }else{
                CA = new CustomAdapter(getApplicationContext(),contactBook);
                contactList.setAdapter(CA);
                CA.notifyDataSetChanged();
                contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String nameOnList = (String) contactList.getItemAtPosition(position);
                        Intent i = new Intent(MainActivity.this, DisplayContact.class);
                        i.putExtra("FULLNAME", nameOnList);
                        startActivity(i);

                    }
                });
            }
        }catch(Exception e){
            Toast.makeText(this, "Something went Wrong...", Toast.LENGTH_SHORT).show();
        }

//        contactList.setOnItemClickListener();
        int NoofContacts = contactBook.size();
        noOfContacts.setText(NoofContacts + " Contacts");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView =(SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                MainActivity.this.CA.getFilter().filter(query);
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {

                MainActivity.this.CA.getFilter().filter(newText);

                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.selectAll:
                Toast.makeText(this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                contactList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
                break;
            case R.id.search:
                Toast.makeText(this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    public void change(View v){
        Toast.makeText(this, "Opening...",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainActivity.this, AddContactActivity.class);
        startActivity(i);
    }
    public void creatContact(View v){
        Toast.makeText(MainActivity.this, "Opening...",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainActivity.this, AddContactActivity.class);
        startActivity(i);
    }
    public void userPicture(View v){}

    @Override
    public void onBackPressed() {
        if(true){
            final Dialog exitDialog = new Dialog(this);
            exitDialog.setContentView(R.layout.exitdialogbox);
            Button btnYes = exitDialog.findViewById(R.id.yesConfirmed);
            Button btnNo = exitDialog.findViewById(R.id.noConfirmed);
            btnYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.super.onBackPressed();
                }
            });
            btnNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exitDialog.dismiss();
                }
            });

            exitDialog.show();
        }
    }
    public void multiChoiceDelete(View view){}
}
