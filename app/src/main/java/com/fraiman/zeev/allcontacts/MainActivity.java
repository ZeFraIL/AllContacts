package com.fraiman.zeev.allcontacts;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText phoneNumber;
    Button takeContact, allContacts;
    ListView contactsListView;
    ArrayList<String> contacts;
    ArrayAdapter<String> adapter;
    Cursor cursor;
    String name, contactNumber, num="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]
                {
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.READ_PHONE_STATE
                }, 1);

        phoneNumber=findViewById(R.id.phoneNumber);
        takeContact=findViewById(R.id.takeContact);
        allContacts=findViewById(R.id.allContacts);
        contactsListView = findViewById(R.id.contactsListView);

        allContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAllContacts(v);
            }
        });

        takeContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callnow(v);
            }
        });

        contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String temp=contacts.get(i).toString();
                int start=temp.lastIndexOf(":");
                num=temp.substring(start+1);
                if (!TextUtils.isEmpty(num)) {
                    phoneNumber.setText(""+num);
                    Toast.makeText(MainActivity.this, "Number="+num, Toast.LENGTH_SHORT).show();
                    String dial = "tel:" + num;
                    startActivity(new Intent(Intent.ACTION_CALL,
                            Uri.parse(dial)));
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a valid telephone number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getContacts() {
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                name =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                contactNumber =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contacts.add(name + " " + ":" + " " + contactNumber);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    public void callnow(View view) {
        String phoneNum = phoneNumber.getText().toString();
        if (!TextUtils.isEmpty(phoneNum)) {
            String dial = "tel:" + phoneNum;
            startActivity(new Intent(Intent.ACTION_CALL,
                    Uri.parse(dial)));
        } else {
            Toast.makeText(MainActivity.this, "Please enter a valid telephone number", Toast.LENGTH_SHORT).show();
        }
    }

    public void viewAllContacts(View view) {
        contacts = new ArrayList<>();
        getContacts();
        adapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                contacts);
        contactsListView.setAdapter(adapter);
    }
}
