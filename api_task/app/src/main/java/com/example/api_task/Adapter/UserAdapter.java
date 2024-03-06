package com.example.api_task.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api_task.AlbumList;
import com.example.api_task.Model.User;
import com.example.api_task.R;
import com.example.api_task.TodosList;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    public List<User> userList;
    public Context context;

    public UserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        UserViewHolder UserViewHolder = new UserViewHolder(view);
        return UserViewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.textViewName.setText(userList.get(position).getName());
        holder.textViewEmail.setText("Email: " + userList.get(position).getEmail());
        holder.textViewAddress.setText("Address: " + userList.get(position).getAddress().getStreet() + " " + userList.get(position).getAddress().getSuite() + " " + userList.get(position).getAddress().getCity());
        holder.textViewContact.setText("Phone: " + userList.get(position).getPhone());
        holder.buttonAlbum.setOnClickListener(v -> {
            Intent intent = new Intent(context, AlbumList.class);
            intent.putExtra("user_id", userList.get(position).getId());
            context.startActivity(intent);
        });
        holder.buttonTodo.setOnClickListener(v -> {
            Intent intent = new Intent(context, TodosList.class);
            intent.putExtra("user_id", userList.get(position).getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewEmail, textViewAddress, textViewContact;
        CardView cardView;
        Button buttonTodo, buttonAlbum;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.userName);
            textViewEmail = itemView.findViewById(R.id.userEmail);
            textViewAddress = itemView.findViewById(R.id.userAddress);
            textViewContact = itemView.findViewById(R.id.userContact);
            cardView = itemView.findViewById(R.id.userCard);
            buttonTodo = itemView.findViewById(R.id.todo);
            buttonAlbum = itemView.findViewById(R.id.album);
        }
    }
}
