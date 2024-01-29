package com.ajinkya.smsapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    List<ContactModel> items;
    public Context context;

    public ContactAdapter(List<ContactModel> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.contact_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        holder.textView.setText(items.get(position).name.toString());
        holder.imageViewMsg.setOnClickListener(v -> {
            Intent intent = new Intent(context, SendMessageScreen.class);
            intent.putExtra("title", items.get(position).name.toString());
            intent.putExtra("subTitle", items.get(position).phoneNumber.toString());
            context.startActivity(intent);
            // if permission is already granted then we are displaying a toast message as permission granted.
//                sendSMS(textViewNumber.getText().toString(),"Hello",view.getContext());

        });
        holder.imageViewCall.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + items.get(position).phoneNumber.toString()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageViewCall, imageViewMsg;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.name);
            imageViewCall = view.findViewById(R.id.callIcon);
            imageViewMsg = view.findViewById(R.id.msgIcon);


        }


        /*private void sendSMS(String mobileNumber,String txtMessage,Context context) {
            try {
                String phone = mobileNumber;
                String message = txtMessage.toString();

                if (!message.isEmpty()) {
                    SmsManager smsManager = SmsManager.getDefault();

                    ///Sending message here
                    smsManager.sendTextMessage(phone, null, message, null, null);
                    Toast.makeText(context, "Message Send successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Please write the message first", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                throw e;
            }
        }*/
    }
}

