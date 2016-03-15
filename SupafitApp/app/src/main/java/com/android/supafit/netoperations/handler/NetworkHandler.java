package com.android.supafit.netoperations.handler;

/**
 * Created by amitmitra on 15/03/16.
 */
public interface NetworkHandler {

    void success(Object response);
    void failure(Exception e);
}
