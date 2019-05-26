package com.breakout.myapplication.standard;

import io.reactivex.disposables.Disposable;

public interface LoadingView {


    void hideLoadingIndicator();

    void showLoadingIndicator(Disposable disposable);
}
