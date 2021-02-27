package com.mycontactsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ContactsDataBase extends SQLiteOpenHelper {

    Contacts contacts;
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "contactsapp.db";
    public static final String CONTACT_TABLE = "Contacts";
    public static final String COLUMN_CONTACT_NAME = "COLUMN_CONTACT_NAME";
    public static final String COLUMN_PHONE_NUMBER = "COLUMN_PHONE_NUMBER";
    public static final String COLUMN_EMAIL = "COLUMN_EMAIL";
    public static final String COLUMN_HOUSEADDRESS = "COLUMN_HOUSEADDRESS";
    public static final String COLUMN_ID = "ID";

    public ContactsDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String Query = "CREATE TABLE "+ CONTACT_TABLE + "("  + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CONTACT_NAME + " TEXT, "
                + COLUMN_PHONE_NUMBER + " BIGINT, "
                + COLUMN_EMAIL + " TEXT, "
                + COLUMN_HOUSEADDRESS + " TEXT )";

        db.execSQL(Query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

    public long addToContactBase(Contacts c){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CONTACT_NAME,c.getContactName());
        cv.put(COLUMN_PHONE_NUMBER,c.getContactPhoneNumber());
        cv.put(COLUMN_EMAIL,c.getContactEmail());
        cv.put(COLUMN_HOUSEADDRESS,c.getContactAddress());

        long result = db.insert(CONTACT_TABLE, null, cv);

        db.close();
        return result;
    }

    public Cursor pullOutContactNames(){
        String query = " SELECT * FROM " + CONTACT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

//        db.close();
        return c;
    }
    public ArrayList<String> returningContactNames(){
        ArrayList<String> collection = new ArrayList<>();
        String query = " SELECT * FROM " + CONTACT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst()){
            do{
                String collections = c.getString(1);
                collection.add(collections);
            }
            while(c.moveToNext());
        }

        db.close();

        return collection;
    }

    public Integer deleteContacts(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(CONTACT_TABLE, " ID = ? ",new String[] {id} );
    }

    public Integer update(String contactID, Contacts contact){
        SQLiteDatabase db = this.getWritableDatabase();
//        String updateQuery = " UPDATE " + CONTACT_TABLE +
//                " SET " + COLUMN_CONTACT_NAME + " = " +
//                contact.getContactName()   +  " WHERE " + COLUMN_ID  + " = " +
//                contactID;
//        db.execSQL(updateQuery);
//        db.execSQL("UPDATE "+CONTACT_TABLE+" SET name = "+"'"+s+"' "+ "WHERE salary = "+"'"+s1+"'");
        ContentValues cv =  new ContentValues();
        cv.put(COLUMN_CONTACT_NAME,contact.getContactName());
        cv.put(COLUMN_PHONE_NUMBER,contact.getContactPhoneNumber());
        cv.put(COLUMN_EMAIL,contact.getContactEmail());
        cv.put(COLUMN_HOUSEADDRESS,contact.getContactAddress());
        //updating row
        return db.update(CONTACT_TABLE, cv,  COLUMN_ID + " =  ?", new String[] {contactID});


    };
//    public String display(){
//        String Q = " SELECT * FROM " + CONTACT_TABLE;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(Q,null);
//        StringBuilder SB = new StringBuilder();
//        Contacts con;
//
//        if(c.moveToFirst()){
//            do{
//                int contact_id = c.getInt(0);
//                String contact_name = c.getString(1);
//                long contact_number = c.getLong(2);
//
//                con = new Contacts(contact_name, contact_number);
//                SB.append(con.toString());
//            }while(c.moveToNext());
//        }else{
//
//        }
//        return SB.toString();
//    }
}
