package com.android.supafit.netoperations;

/**
 * Created by amitmitra on 15/03/16.
 */
public class URLFactory {

    public static final String TEST_URL = "http://54.187.141.143:8080/supafit-api/";

    public static final String BASE_URL = TEST_URL;

    public static final String SIGNUP_MANUAL_URL = BASE_URL + "signup/local";
    public static final String SIGNIN_URL = BASE_URL + "signin/login";
    public static final String USERS = BASE_URL + "users";
    public static final String USERS_FOOD_PREFERENCES = BASE_URL + "users/food/preferences";
    public static final String USERS_MEDICAL_CONDITION = BASE_URL + "users/medical/conditions";
    public static final String USERS_FITNESS_GOALS = BASE_URL + "users/fitness/goals";
    public static final String GET_PACKAGE_URL = BASE_URL + "programs";

}
