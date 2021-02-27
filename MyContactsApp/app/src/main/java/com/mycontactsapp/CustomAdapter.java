package com.mycontactsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<String> {

    Context C;
    ArrayList<String> contacts;

    public CustomAdapter(@NonNull Context context, ArrayList<String> con) {
        super(context, R.layout.mycustomcontactlists, R.id.contactpersondetails, con);
        C = context;
        contacts = con;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View myView = convertView;
        LayoutInflater inflater = (LayoutInflater) C.getSystemService(C.LAYOUT_INFLATER_SERVICE);
        View myView = inflater.inflate(R.layout.mycustomcontactlists, parent, false);

        TextView nameInitials = myView.findViewById(R.id.nameInitials);
        TextView contactName = myView.findViewById(R.id.contactpersondetails);

        String name = contacts.get(position);
        char firstnameIni = name.charAt(0);
        char secondnameIni = name.charAt(1);
        String nameIni = Character.toString(firstnameIni) + Character.toString(secondnameIni);

        contactName.setText(contacts.get(position));
        nameInitials.setText(nameIni);

        this.notifyDataSetChanged();

        return myView;
    }
}
