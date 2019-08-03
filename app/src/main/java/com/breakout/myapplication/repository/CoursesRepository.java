package com.breakout.myapplication.repository;

import android.support.annotation.NonNull;


import com.breakout.myapplication.model.Content;

import java.util.List;

import io.reactivex.Observable;


public interface CoursesRepository {

//    @NonNull
//    Observable<List<Result>> getUdemyCourses();
//
//    @NonNull
//    Observable<List<Review>> getUdemyReviews(Integer courseId);


    @NonNull
    Observable<List<String>> getCities(String rayon);

    @NonNull
    Observable<List<String>> getStreets(String city);

    @NonNull
    Observable<List<String>> getHomes(String street);
}
