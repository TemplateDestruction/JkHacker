package com.breakout.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.breakout.myapplication.model.Example;
import com.breakout.myapplication.repository.RepositoryProvider;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.io.IOException;
import java.util.List;

import static com.github.mikephil.charting.charts.Chart.LOG_TAG;

public class SearchActivityDatabase extends AppCompatActivity {



    DBHelper dbHelper;

//    SearchableSpinner borrowSpin;
    SearchableSpinner pointSpin;
    SearchableSpinner streetSpin;
    SearchableSpinner homeSpin;
    SearchableSpinner searchableSpinner;
    Button button;

    List<String> borrows;
    List<String> points;
    List<String> streets;
    List<String> homes;

    // вывод в лог данных из курсора
    void logCursor(Cursor c) {
        if (c != null) {
            if (c.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String cn : c.getColumnNames()) {
                        str = str.concat(cn + " = " + c.getString(c.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(LOG_TAG, str);
                } while (c.moveToNext());
            }
        } else
            Log.d(LOG_TAG, "Cursor is null");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_layout);
//        dbHelper = new DBHelper(this);
//        SQLiteDatabase database = dbHelper.getWritableDatabase();
////        dbHelper.onCreate(database);
////        System.out.println("AAAAAAAAAAAAAAAAAAAA");
////        System.out.println(this.getDatabasePath("mytable").getAbsolutePath());
//        Cursor cursor = database.query("house_param", new String[], "formalname_city");
//        Cursor cursor = database.rawQuery("SELECT DISTINCT formalname_city FROM comp_param", new String[40]);
//        logCursor(cursor);
//        borrows = new ArrayList<>();
//        borrows.add("gatciha");
//        borrows.add("gatc");
//        borrows.add("gatcia");
//        borrows.add("tciha");

        DataBaseHelper myDbHelper = new DataBaseHelper(this);
        myDbHelper.openDataBase();
        Cursor cursor = myDbHelper.getReadableDatabase().rawQuery("SELECT DISTINCT formalname_city FROM comp_param", new String[40]);

//        myDbHelper = new DataBaseHelper(this);
//
//        try {
//            System.out.println("DDDDDDDDDDDDDD");
//            myDbHelper.createDataBase();
//        } catch (IOException ioe) {
//            System.out.println("CCCCCCCCCCC");
//            throw new Error("Unable to create database");
//        }
//
//        try {
//            myDbHelper.openDataBase();
//        }catch(SQLException sqle){
//            throw sqle;
//        }

//        borrowSpin = findViewById(R.id.borrow_spinner);
        pointSpin = findViewById(R.id.point_spinner);
        streetSpin = findViewById(R.id.street_spinner);
        homeSpin = findViewById(R.id.home_spinner);
        button = findViewById(R.id.search_entery);


//        loadBorrows();
//        loadPoints();




    }

//    private void loadBorrows() {
//
//        //on Next
//        setBorrowsSpin(borrowSpin, borrows);
//
//    }

    private void setHomeSpin(Spinner homeSpin, List<String> homes) {
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, homes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        homeSpin.setAdapter(adapter);
        homeSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String bor = streets.get(position);
                Toast.makeText(SearchActivityDatabase.this, bor, Toast.LENGTH_SHORT).show();
                button.setVisibility(View.VISIBLE);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setStreetsSpin(Spinner streetSpin, List<String> streets) {
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, streets);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        streetSpin.setAdapter(adapter);
        streetSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadHomes(streets.get(position));
                String bor = streets.get(position);
                Toast.makeText(SearchActivityDatabase.this, bor, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadHomes(String street) {
        setHomeSpin(homeSpin, homes);
    }

    private void setPointSpin(Spinner pointSpin, List<String> points) {
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, points);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pointSpin.setAdapter(adapter);
        pointSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadStreets(points.get(position));
                String bor = points.get(position);
                Toast.makeText(SearchActivityDatabase.this, bor, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadStreets(String point) {
        //on Next
        setStreetsSpin(streetSpin, points);
    }

    private void setBorrowsSpin(Spinner borrowSpin, List<String> borrows) {
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, borrows);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        borrowSpin.setAdapter(adapter);
        borrowSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadPoints();
                String bor = borrows.get(position);
                Toast.makeText(SearchActivityDatabase.this, bor, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadPoints() {
        RepositoryProvider
                .getCoursesRepository()
                .getLivePoints()
                .subscribe(this::onComplete, this::onError);
        //on Next
    }

    private void onError(Throwable throwable) {
        System.out.println(throwable.getLocalizedMessage());
    }

    private void onComplete(List<Example> examples) {
        for (Example example : examples) {
            points.add(example.getName());
        }
        setPointSpin(pointSpin, points);

    }


    public void onSearchClick(View view) {
        //send location to server

        //on Next
        startActivity(new Intent(this, ResultAcitivity.class));
    }



    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "house", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- onCreate database ---");
            // создаем таблицу с полями
            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement,"
                    + "name text,"
                    + "email text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }
}
