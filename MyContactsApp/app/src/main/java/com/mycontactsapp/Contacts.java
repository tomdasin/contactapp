package com.mycontactsapp;

import android.widget.Toast;

public class Contacts {
    private int ID;
    private String contactName;
    private long contactPhoneNumber;
    private String contactEmail;
    private String contactAddress;

    public Contacts(String contactName, long contactPhoneNumber, String contactEmail, String contactAddress, int ID) {
        if(contactName.length() > 0){
            this.contactName = contactName;
        }
        this.contactPhoneNumber = contactPhoneNumber;
        this.contactEmail = contactEmail;
        this.contactAddress = contactAddress;
        this.ID = ID;
    }
    public Contacts(String contactName, long contactPhoneNumber, String contactEmail, String contactAddress) {
        try{
//            if(contactName.length() > 0){
                this.contactName = contactName;
//            }
            this.contactPhoneNumber = contactPhoneNumber;
            this.contactEmail = contactEmail;
            this.contactAddress = contactAddress;
        }catch(Exception e){
            e.getMessage();
        }
    }
    public String getContactName() {
        return contactName;
    }
    public long getContactPhoneNumber() {
        return contactPhoneNumber;
    }
    public String getContactEmail() { return contactEmail; }
    public String getContactAddress() {
        return contactAddress;
    }
    public int getID(){return ID;}
}
