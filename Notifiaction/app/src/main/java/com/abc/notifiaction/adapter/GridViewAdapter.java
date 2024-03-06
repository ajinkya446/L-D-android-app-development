package com.abc.notifiaction.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.abc.notifiaction.model.CategoryModel;
import com.abc.notifiaction.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

public class GridViewAdapter extends ArrayAdapter<CategoryModel> {
    public Context ctx;

    public GridViewAdapter(@NonNull Context context, ArrayList<CategoryModel> courseModelArrayList) {
        super(context, 0, courseModelArrayList);
        ctx = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item_layout, parent, false);
        }

        CategoryModel categoryModel = getItem(position);
        TextView titleGridView = listitemView.findViewById(R.id.titleGrid);
        ImageView imgGridview = listitemView.findViewById(R.id.imgViewGrid);
        CardView cardView = listitemView.findViewById(R.id.cardView);
        ConstraintLayout constraintLayout = listitemView.findViewById(R.id.constraint);
        constraintLayout.setBackground(categoryModel.getDrawable());

        titleGridView.setText(categoryModel.titleName);
        Picasso.get().load(Objects.requireNonNull(categoryModel.getDrawableId().toString())).into(imgGridview);

//        imgGridview.setImageResource(categoryModel.getDrawableId());

        /*cardView.setOnClickListener(v -> {
            Toast.makeText(ctx, "Category " + categoryModel.titleName + " " + "selected", Toast.LENGTH_SHORT).show();
            Intent intent =new Intent(ctx, QuizList.class);

        });*/
        return listitemView;
    }
}


