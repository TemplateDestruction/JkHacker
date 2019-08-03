package com.breakout.myapplication.repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

public class RepositoryProvider {

    private static CoursesRepository repository;

    private RepositoryProvider() {

    }

    @NonNull
    public static CoursesRepository getLocationRepository() {
        if (repository == null) {
            repository = new DefaultCoursesRepository();
        }
        return repository;
    }

    public static void setRepository(CoursesRepository repository) {
        RepositoryProvider.repository = repository;
    }

    @MainThread
    public static void init() {
        repository = new DefaultCoursesRepository();
    }
}
