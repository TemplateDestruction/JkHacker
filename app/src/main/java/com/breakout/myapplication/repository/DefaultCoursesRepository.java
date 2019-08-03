package com.breakout.myapplication.repository;

import android.support.annotation.NonNull;


import com.breakout.myapplication.api.ApiFactory;
import com.breakout.myapplication.model.Content;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class DefaultCoursesRepository implements CoursesRepository {

    private int pageNumber = 1;

    @NonNull
    @Override
    public Observable<List<String>> getCities(String rayon) {
        return ApiFactory
                .getCityService()
                .getCities(rayon)
                .flatMap((Function<List<Content>, ObservableSource<List<String>>>) contents -> {
                    List<String> cities = new ArrayList<>();
                    for (Content content : contents) {
                        cities.add(content.getFields().getCity());
                    }
                    System.out.println("CITIES: " + cities);
                    return Observable.just(cities);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @NonNull
    @Override
    public Observable<List<String>> getStreets(String city) {
        return ApiFactory
                .getCityService()
                .getStreets(city)
                .flatMap((Function<List<Content>, ObservableSource<List<String>>>) contents -> {
                    List<String> cities = new ArrayList<>();
                    for (Content content : contents) {
                        cities.add(content.getFields().getStreet());

                    }
                    System.out.println("STREETS: "+ cities);
                    return Observable.just(cities);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @NonNull
    @Override
    public Observable<List<String>> getHomes(String street) {
        return ApiFactory
                .getCityService()
                .getHouses(street)
                .flatMap((Function<List<Content>, ObservableSource<List<String>>>) contents -> {
                    List<String> cities = new ArrayList<>();
                    for (Content content : contents) {
                        cities.add(content.getFields().getNumber());
                    }
                    System.out.println("HOMES: " + cities);
                    return Observable.just(cities);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

//    @NonNull
//    @Override
//    public Observable<List<Result>> getUdemyCourses() {
//        System.out.println("getUdemyCourses: BEGIN DOWNLOADING");
//
//        return ApiFactory.getCityService()
//                .getUdemyResponse(pageNumber++)
//                .flatMap(udemyResponse -> Observable.just(udemyResponse.getResults()))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//
//    }
//
//    @NonNull
//    @Override
//    public Observable<List<Review>> getUdemyReviews(Integer courseId) {
//        return ApiFactory.getCityService()
//                .getUdemyReviews(courseId)
//                .flatMap(reviewsResponse -> Observable.just(reviewsResponse.getReviews()))
//                .onErrorResumeNext(throwable -> {
//                    System.out.println("ERROR REVIEWS: " + throwable.getLocalizedMessage());
//                    return null;
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }




}
