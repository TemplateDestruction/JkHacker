package com.breakout.myapplication.api;



import com.breakout.myapplication.model.Content;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EducationService {

//    //endpoint = https://www.udemy.com/api-2.0/
//    @GET("courses/?page_size=20")
//    Observable<Example> getUdemyResponse(@Query("page") int pageNumber);



    @GET("city/")
    Observable<List<Content>> getCities(@Query("RAYON") String rayon);

    @GET("street/")
    Observable<List<Content>> getStreets(@Query("CITY") String city);

    @GET("home/")
    Observable<List<Content>> getHouses(@Query("STREET") String street);






}
