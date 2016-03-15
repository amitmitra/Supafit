package com.android.supafit.ui.base;

/**
 * Created by harsh on 2/1/16.
 */
public interface Presenter<V extends MvpView> {

    public void attachView(V view);
    public void detachView();
}
