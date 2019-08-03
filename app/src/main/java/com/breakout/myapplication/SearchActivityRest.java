package com.breakout.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.breakout.myapplication.model.CalcModel;
import com.breakout.myapplication.model.UKmodel;
import com.breakout.myapplication.repository.RepositoryProvider;
import com.breakout.myapplication.standard.LoadingDialog;
import com.breakout.myapplication.standard.LoadingView;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchActivityRest extends AppCompatActivity {

    SearchableSpinner borrowSpin;
    SearchableSpinner pointSpin;
    SearchableSpinner streetSpin;
    SearchableSpinner homeSpin;
    SearchableSpinner searchableSpinner;
    Button button;
    private LoadingView dialog;

    String[] borrows;
    String[] cities;
    List<String> streets;
    List<String> homes;
    Intent intent;
    List<CalcModel> calcModels;
    List<UKmodel> uKmodels;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_layout);
        intent = new Intent(this, RadarChartActivity.class);
        Random random = new Random(100000);
        Random rnd = new Random(10000);
        calcModels = new ArrayList<>();
        uKmodels = new ArrayList<>();
        dialog = LoadingDialog.view(getSupportFragmentManager());
        for (int i = 0; i < 400; i++) {
            int calc1 = random.nextInt() + 25000;
            int calc2 = random.nextInt() + 50000;
            int calc3 = random.nextInt() + 75000;
            int calc4;
            int calc5;
            int calc6;
            int sum;
            int summa = calc1 + calc2 + calc3;
            calcModels.add(new CalcModel(calc1, calc2, calc3, summa));
            Random rd = new Random(2);
            if (rd.nextInt() == 1) {
                calc4 = calc1 + rnd.nextInt();
                calc5 = calc2 - rnd.nextInt();
                calc6 = calc3 + rnd.nextInt();
            } else {
                calc4 = calc1 - rnd.nextInt();
                calc5 = calc2 + rnd.nextInt();
                calc6 = calc3 - rnd.nextInt();
            }
            sum = calc4+calc5+calc6;
            uKmodels.add(new UKmodel(calc4, calc5, calc6, sum));
        }
//        borrows = new ArrayList<>();
//        borrows.add("gatciha");
//        borrows.add("gatc");
//        borrows.add("gatcia");
//        borrows.add("tciha");

        borrowSpin = findViewById(R.id.borrow_spinner);
        pointSpin = findViewById(R.id.point_spinner);
        streetSpin = findViewById(R.id.street_spinner);
        homeSpin = findViewById(R.id.home_spinner);
        button = findViewById(R.id.search_entery);

        borrowSpin.setTitle("Район");
        pointSpin.setTitle("Населенный пункт");
        streetSpin.setTitle("Улица");
        homeSpin.setTitle("Дом");

//        loadBorrows();
        loadCities();




    }

    private void loadBorrows() {
        borrows = getResources().getStringArray(R.array.borrows);
        //on Next
        setBorrowsSpin(borrowSpin, borrows);

    }

    private void setHomeSpin(Spinner homeSpin, List<String> homes) {
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, homes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        homeSpin.setAdapter(adapter);
        homeSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                button.setVisibility(View.VISIBLE);
                intent.putExtra("calc", calcModels.get(position));
                intent.putExtra("uk", uKmodels.get(position));
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @SuppressLint("CheckResult")
    private void loadHomes(String street) {
        RepositoryProvider
                .getLocationRepository()
                .getHomes(" " + street)
                .doOnSubscribe(disposable -> {dialog.showLoadingIndicator(disposable);})
                .doAfterTerminate(dialog::hideLoadingIndicator)
                .subscribe(this::onCompleteHomes, this::onError);

        //on Next
    }

    private void onCompleteHomes(List<String> strings) {
        homes = strings;
        setHomeSpin(homeSpin, homes);
    }

    private void setPointSpin(Spinner pointSpin, String[] cities) {
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pointSpin.setAdapter(adapter);
        pointSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadStreets(cities[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @SuppressLint("CheckResult")
    private void loadStreets(String city) {
        System.out.println("CITY: " + city);
        RepositoryProvider
                .getLocationRepository()
                .getStreets(" " + city)
                .doOnSubscribe(disposable -> {dialog.showLoadingIndicator(disposable);})
                .doAfterTerminate(dialog::hideLoadingIndicator)
                .subscribe(this::onCompleteStreets, this::onError);

    }

    private void onCompleteStreets(List<String> strings) {
        streets = strings;
        setStreetsSpin(streetSpin, streets);
    }



    private void setBorrowsSpin(Spinner borrowSpin, String[] borrows) {
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, borrows);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        borrowSpin.setAdapter(adapter);
        borrowSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                loadPointsRest(borrows[position]);
//                String bor = borrows.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @SuppressLint("CheckResult")
    private void loadPointsRest(String borrow) {
        RepositoryProvider
                .getLocationRepository()
                .getStreets(" " + borrow)
                .doOnSubscribe(disposable -> {dialog.showLoadingIndicator(disposable);})
                .doAfterTerminate(dialog::hideLoadingIndicator)
                .subscribe(this::onCompletePoints, this::onError);
    }

    private void onCompletePoints(List<String> strings) {
//        cities = strings;
//        setPointSpin(pointSpin, cities);
    }


    private void loadCities() {
        cities = getResources().getStringArray(R.array.points);
        setPointSpin(pointSpin, cities);
    }

    private void onError(Throwable throwable) {
        System.out.println(throwable.getLocalizedMessage());
    }



    public void onSearchClick(View view) {
        startActivity(intent);
    }
}
