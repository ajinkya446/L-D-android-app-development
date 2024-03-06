package com.example.api_task.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api_task.Model.Todos;
import com.example.api_task.R;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    public List<Todos> todoList;

    public TodoAdapter(List<Todos> todoList) {
        this.todoList = todoList;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
        TodoViewHolder TodoViewHolder = new TodoViewHolder(view);
        return TodoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        holder.textView.setText(todoList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public static class TodoViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.todoTitle);
        }
    }
}
