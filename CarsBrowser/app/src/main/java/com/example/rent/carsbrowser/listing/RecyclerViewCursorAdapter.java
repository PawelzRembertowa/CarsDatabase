package com.example.rent.carsbrowser.listing;

import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rent.carsbrowser.CarsTableContract;
import com.example.rent.carsbrowser.R;

import butterknife.ButterKnife;

/**
 * Created by RENT on 2017-03-27.
 */

public class RecyclerViewCursorAdapter extends RecyclerView.Adapter<RecyclerViewCursorAdapter.ViewHolder> {

    private Cursor cursor;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //cursor wczytuje same malo okienko, by oszczedzac pamiec
        cursor.moveToPosition(position);
        String imageUrl = cursor.getString(cursor.getColumnIndex(CarsTableContract.COLUMN_IMAGE));
        String make = cursor.getString(cursor.getColumnIndex(CarsTableContract.COLUMN_MAKE));
        String model = cursor.getString(cursor.getColumnIndex(CarsTableContract.COLUMN_MODEL));
        String year = cursor.getString(cursor.getColumnIndex(CarsTableContract.COLUMN_YEAR));

        holder.year.setText("Rok produkcji: " + year);
        holder.makeAndModel.setText(make + " " + model);
        Glide.with(holder.imageView.getContext()).load(imageUrl).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return cursor != null? cursor.getCount(): 0;
    }

    public void setCursor(@Nullable Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        TextView makeAndModel;

        TextView year;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = ButterKnife.findById(itemView, R.id.image);
            makeAndModel = (TextView) itemView.findViewById(R.id.make_and_modelID);
            year = (TextView) itemView.findViewById(R.id.yearID2);

        }
    }
}
