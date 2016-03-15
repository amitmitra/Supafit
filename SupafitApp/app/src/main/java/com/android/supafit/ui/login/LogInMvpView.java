package com.android.supafit.ui.login;

import com.android.supafit.ui.base.MvpView;

/**
 * Created by harsh on 2/1/16.
 */
public interface LogInMvpView extends MvpView {

    public void showProgressBar();
    public void stopProgressBar();
    public void goToNextPage();
    public void setSampleResponseText(String text);

}
