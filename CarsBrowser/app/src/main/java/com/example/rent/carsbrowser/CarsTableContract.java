package com.example.rent.carsbrowser;

import android.provider.BaseColumns;

/**
 * Created by RENT on 2017-03-25.
 */

public class CarsTableContract implements BaseColumns {

    public static String TABLE_NAME= "CARS";
    public static String COLUMN_MAKE = "make";
    public static String COLUMN_MODEL = "model";
    public static String COLUMN_IMAGE = "image";
    public static String COLUMN_YEAR = "year";
}
