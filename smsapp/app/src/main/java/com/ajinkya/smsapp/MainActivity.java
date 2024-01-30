package com.ajinkya.smsapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //    EditText editTextMessage;
//    Button buttonSend, buttonReadContact;
    TextView textViewData;
    RecyclerView recyclerViewContact;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        editTextMessage = findViewById(R.id.msgText);
//        buttonSend = findViewById(R.id.sendButton);
//        buttonReadContact = findViewById(R.id.readButton);
        recyclerViewContact = findViewById(R.id.recyclerView);
        textViewData=findViewById(R.id.no_data);
        recyclerViewContact.setLayoutManager(new LinearLayoutManager(this));
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {
            // if permission is not granted then we are requesting for the permissions on below line.
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, 100);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_DENIED) {
            // if permission is not granted then we are requesting for the permissions on below line.
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 100);
        }else{
            readContacts();
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},100);
        }
//        buttonSend.setOnClickListener(v -> {
//            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {
//                // if permission is not granted then we are requesting for the permissions on below line.
//                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, 100);
//            } else {
//                // if permission is already granted then we are displaying a toast message as permission granted.
//                sendSMS();
//            }
//        });
//        buttonReadContact.setOnClickListener(v -> {
//            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_DENIED) {
//                // if permission is not granted then we are requesting for the permissions on below line.
//                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 100);
//            } else {
//                // if permission is already granted then we are displaying a toast message as permission granted.
//                readContacts();
//            }
//        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
//            sendSMS();
            readContacts();
        }
    }

    public void readContacts() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null);
            startManagingCursor(cursor);
            List<ContactModel> items = new ArrayList<ContactModel>();
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                @SuppressLint("Range") String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                if (!items.contains(number)) {
                    items.add(new ContactModel(name, number));
                }
            }
            if(items.isEmpty()){
                recyclerViewContact.setVisibility(View.INVISIBLE);
            }else{
                ContactAdapter contactAdapter = new ContactAdapter(items, this);
                recyclerViewContact.setAdapter(contactAdapter);
                textViewData.setVisibility(View.INVISIBLE);
            }

        } else {
            Toast.makeText(this, "Your device is incompatible reading contacts", Toast.LENGTH_SHORT).show();
        }
    }
}