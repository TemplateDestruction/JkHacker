package com.breakout.myapplication.repository;

import android.support.annotation.NonNull;


import com.breakout.myapplication.model.Example;

import java.util.List;

import io.reactivex.Observable;


public interface CoursesRepository {

//    @NonNull
//    Observable<List<Result>> getUdemyCourses();
//
//    @NonNull
//    Observable<List<Review>> getUdemyReviews(Integer courseId);

    @NonNull
    Observable<List<Example>> getLivePoints();
}
