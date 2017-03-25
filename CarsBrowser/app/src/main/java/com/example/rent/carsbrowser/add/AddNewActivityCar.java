package com.example.rent.carsbrowser.add;

import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rent.carsbrowser.Car;
import com.example.rent.carsbrowser.CarBuilder;
import com.example.rent.carsbrowser.MotoDatabaseOpenHelper;
import com.example.rent.carsbrowser.R;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewActivityCar extends AppCompatActivity {



    private String imageUrl;
    private MotoDatabaseOpenHelper databaseOpenHelper;

    @BindView(R.id.imageID)
    ImageView image;

    @BindView(R.id.yearID)
    EditText year;

    @BindView(R.id.modelID)
    EditText model;

    @BindView(R.id.makeID)
    EditText make;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_car);
        ButterKnife.bind(this);
        databaseOpenHelper = new MotoDatabaseOpenHelper(this);
    }

    @OnClick (R.id.add_car_buttonID)
    void onAddCarButtonClick() {

        Car car = new CarBuilder()
                .setMake(make.getText().toString())
                .setModel(model.getText().toString())
                .setYear(Integer.parseInt(year.getText().toString()))
                .setImage(imageUrl)
                .createCar();
        boolean added = databaseOpenHelper.insertCar(car);

        if (added) {
            Toast.makeText(this, "Dodano nowy samochód", Toast.LENGTH_LONG).show();
            make.setText(null);
            model.setText(null);
            imageUrl = null;
            image.setImageResource(R.drawable.noimageplaceholder);
            year.setText(null);
        }

    }
    @OnClick (R.id.add_image_buttonID)
    void onAddImageButtonClick(){
        View promptView = LayoutInflater.from(this).inflate(R.layout.dialog_prompt, null);

        EditText urlEditText = (EditText) findViewById(R.id.url_edit_textID);

        new AlertDialog.Builder(this)
                .setView(promptView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        imageUrl = urlEditText.getText().toString();
                        Glide.with(AddNewActivityCar.this)
                                .load(imageUrl)
                                .into(image);
                    }
                }).show();
    }
}
