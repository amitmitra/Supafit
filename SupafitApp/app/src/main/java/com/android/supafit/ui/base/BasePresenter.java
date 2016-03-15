package com.android.supafit.ui.base;

/**
 * Created by harsh on 2/1/16.
 */
public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mvpView;

    @Override
    public void attachView(T view) {
        this.mvpView = view;
    }

    @Override
    public void detachView() {
        this.mvpView = null;
    }

    public T getMvpView() {
        return mvpView;
    }

    public boolean isViewAttached(){return mvpView!=null;}

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
