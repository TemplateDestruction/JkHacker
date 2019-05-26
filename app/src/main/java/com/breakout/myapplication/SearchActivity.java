package com.breakout.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.breakout.myapplication.model.Example;
import com.breakout.myapplication.repository.RepositoryProvider;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_layout);

//        borrows = new ArrayList<>();
//        borrows.add("gatciha");
        borrows.add("gatc");
        borrows.add("gatcia");
        borrows.add("tciha");

//        borrowSpin = findViewById(R.id.borrow_spinner);
        pointSpin = findViewById(R.id.point_spinner);
        streetSpin = findViewById(R.id.street_spinner);
        homeSpin = findViewById(R.id.home_spinner);
        button = findViewById(R.id.search_entery);


//        loadBorrows();
        loadPoints();




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
                Toast.makeText(SearchActivity.this, bor, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(SearchActivity.this, bor, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(SearchActivity.this, bor, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(SearchActivity.this, bor, Toast.LENGTH_SHORT).show();
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
}
