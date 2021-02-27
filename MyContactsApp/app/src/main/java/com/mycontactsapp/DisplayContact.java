package com.mycontactsapp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DisplayContact extends AppCompatActivity {

    TextView contactName;
    TextView contactNumber;
    ImageView contactPicture;
    Toolbar toolbar2;
    ContactsDataBase CDB;
    String nameFromDatabase;
    long phoneNumberFromDatabase;
    String emailFromDatabase;
    String houseAddFromDatabase;
    String nameFD;
    int contactID;
    int contactId;
    Contacts contacts;
    String cName;
    String cNumber;
    String cEmail;
    String cAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);

        toolbar2 = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        contactName = findViewById(R.id.contactName);
        contactNumber = findViewById(R.id.contactNumberDisplayed);
        contactPicture = findViewById(R.id.contactPicture);

        Bundle b = getIntent().getExtras();

        nameFromDatabase = b.getString("FULLNAME");

        contactName.setText(nameFromDatabase);

        CDB = new ContactsDataBase(this);
        Cursor cursor = CDB.pullOutContactNames();
        try{
            if(cursor.moveToFirst()){
                do{
                    contactID = cursor.getInt(0);
                    nameFD = cursor.getString(1);
                    phoneNumberFromDatabase = cursor.getLong(2);
                    emailFromDatabase = cursor.getString(3);
                    houseAddFromDatabase = cursor.getString(4);
                    contacts = new Contacts(nameFD,phoneNumberFromDatabase,emailFromDatabase,houseAddFromDatabase,contactID);
                    String objContactName = contacts.getContactName();
                    if(objContactName.equals(nameFromDatabase)){
                        String longString = Long.toString(contacts.getContactPhoneNumber());
                        contactNumber.setText(longString);
                        contactId = contacts.getID();
                        cName = contacts.getContactName();
                        cNumber = Long.toString(contacts.getContactPhoneNumber());
                        cEmail = contacts.getContactEmail();
                        cAddress = contacts.getContactAddress();
                    }
                }
                while(cursor.moveToNext());
            }
        }catch(Exception e){
            Toast.makeText(this, "Error Message: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Not Working...", Toast.LENGTH_SHORT).show();
        }
    }
    public void editContact(View v){
        Toast.makeText(this, "Editing...", Toast.LENGTH_SHORT).show();
//        String cName = contacts.getContactName();
//        String cNumber = Long.toString(contacts.getContactPhoneNumber());
//        String cEmail = contacts.getContactEmail();
//        String cAddress = contacts.getContactAddress();
        Intent i = new Intent(DisplayContact.this, EditingContactPage.class);
        i.putExtra("EDITING-NAME",cName);
        i.putExtra("EDITING-NUMBER",cNumber);
        i.putExtra("EDITING-EMAIL",cEmail);
        i.putExtra("EDITING-ADDRESS",cAddress);
        i.putExtra("EDITING-CONTACT-ID", Integer.toString(contactId));
        startActivity(i);
    }
    public void shareContact(View v){
        Toast.makeText(this, " Now Sharing...", Toast.LENGTH_SHORT).show();
    }
    public void deleteContact(View v){
//        Toast.makeText(this, "Deleting...", Toast.LENGTH_SHORT).show();
        try{
//            String contactInfo = contactName.getText().toString();
            String id = Integer.toString(contactId);
            Integer deletedRowsofContact = CDB.deleteContacts(id);
            if(deletedRowsofContact.intValue() < 0){
                Toast.makeText(this, "Non Coresponing Values...Try Again!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception ex){
            Toast.makeText(this, "Could not Delete Contact...Try Again!", Toast.LENGTH_SHORT).show();
        }

        finish();
    }
    public void displayCont(){}
    public void moreOptions(View v){ Toast.makeText(this, "Options...", Toast.LENGTH_SHORT).show(); }
    public void moveBack(View v){
        Toast.makeText(this, "Back...", Toast.LENGTH_SHORT).show();
    }
    public void phoneContact(View v){
        Toast.makeText(this, "Phoning Contact...", Toast.LENGTH_SHORT).show();
        Uri calling = Uri.parse("tel:" + contactNumber.getText().toString());
        Intent makeCall = new Intent(Intent.ACTION_DIAL,calling);
        startActivity(makeCall);
    }
    public void messageContact(View v){
        Toast.makeText(this, "Messaging Contact...", Toast.LENGTH_SHORT).show();
        Uri messaging = Uri.parse("sms:" + contactNumber.getText().toString());
        Intent intent = new Intent(Intent.ACTION_SEND, messaging);
        String phoneNo =  contactNumber.getText().toString();
        startActivity(intent);
    }
    public void emailContact(View v){
        Toast.makeText(this, "Emailing Contact...", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, contacts.getContactEmail(), Toast.LENGTH_SHORT).show();
        Uri email = Uri.parse("mailto:");
        Intent sendEmail = new Intent(Intent.ACTION_SEND);
        sendEmail.setData(email);
        sendEmail.setType("text/plain");
        sendEmail.putExtra(Intent.EXTRA_EMAIL,contacts.getContactEmail());
        startActivity(sendEmail);
    }
    public void onBackPressed(View v){
        super.onBackPressed();
    }
}
