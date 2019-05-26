package com.breakout.myapplication.api;



import com.breakout.myapplication.model.Example;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EducationService {

//    //endpoint = https://www.udemy.com/api-2.0/
//    @GET("courses/?page_size=20")
//    Observable<Example> getUdemyResponse(@Query("page") int pageNumber);
//
    @GET("/{borrow_name}/")
    Observable<Example> getUdemyReviews(@Path("borrow_name") Integer courseId);

    @GET("region")
    Observable<List<Example>> getPoints();





}
