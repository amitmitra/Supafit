package com.android.supafit.netoperations;

import android.content.Context;
import android.util.Log;

import com.android.supafit.SupafitApplication;
import com.android.supafit.netoperations.handler.NetworkHandler;
import com.android.supafit.netoperations.networkmodel.plan.MealPlans;
import com.android.supafit.netoperations.networkmodel.program.Program;
import com.android.supafit.netoperations.networkmodel.summary.ActivitySummary;
import com.android.supafit.netoperations.networkmodel.user.User;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by amitmitra on 15/03/16.
 */
public class VolleyRequest {

    private static final String TAG = VolleyRequest.class.getSimpleName();

    private static final int VOLLEY_REQUEST_TIMEOUT_DURATION = 5000;
    private static final DefaultRetryPolicy retryPolicy = new DefaultRetryPolicy(VOLLEY_REQUEST_TIMEOUT_DURATION,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);


    public static final void signinUser(final String username, final String password, final NetworkHandler handler) throws JSONException {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URLFactory.SIGNIN_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    User user = new Gson().fromJson(response.get("user").toString(), User.class);
                    handler.success(user);
                } catch (JSONException e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        JSONObject obj = new JSONObject(res);
                        if(obj.get("error") != null && ((String)obj.get("error")).contains("Invalid Credentials")){
                            handler.success(null);
                            return;
                        }
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
                handler.failure(error);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=UTF-8");
                headers.put("Accept", "application/json");
                headers.put("User_Credentials", "client_id:supafit-mobile-app,client_secret:SsUbJJio22nH3rgFf32eRFEF43dedc2wfc2ef_RF34wsdxSXQSCX34RDSdcsd,user_name:"
                        + username + ",password:" +password);
                return headers;
            }
        };

        SupafitApplication.getInstance().addToRequestQueue(request);
    }

    public static final void signupUser(String name, String email, String password,
                                        String phoneNumber, final NetworkHandler handler) throws JSONException{
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        obj.put("email", email);
        obj.put("password", password);
        if(phoneNumber != null && !phoneNumber.isEmpty()) {
            JSONObject phoneObject = new JSONObject();
            phoneObject.put("type", "primary");
            phoneObject.put("number", phoneNumber);
            JSONArray array = new JSONArray();
            array.put(phoneObject);
            obj.put("phoneNumbers", array);
        }
        Log.i(TAG, obj.toString());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URLFactory.SIGNUP_MANUAL_URL, obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                User user = new Gson().fromJson(response.toString(), User.class);
                handler.success(user);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        JSONObject obj = new JSONObject(res);
                        if(obj.get("error") != null && ((String)obj.get("error")).contains("Email already registered")){
                            handler.success(null);
                            return;
                        }
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
                handler.failure(error);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=UTF-8");
                headers.put("Accept", "application/json");
                headers.put("Client_Credentials", "client_id:supafit-mobile-app,client_secret:SsUbJJio22nH3rgFf32eRFEF43dedc2wfc2ef_RF34wsdxSXQSCX34RDSdcsd");
                return headers;
            }
        };
        request.setRetryPolicy(retryPolicy);
        SupafitApplication.getInstance().addToRequestQueue(request);
    }
    public static final void updateUserInformation(final Context c, final NetworkHandler handler, final User user) throws JSONException{


        final Gson gson = new Gson();
        String jsonObject = gson.toJson(user);
        JSONObject obj = new JSONObject(jsonObject);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, URLFactory.USERS,obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    final Gson gson = new Gson();
                    User user = gson.fromJson(jsonObject.getJSONObject("user").toString(), User.class);
                    handler.success(user);
                } catch (JSONException e){
                    Log.e(TAG, "JSONException");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                handler.failure(volleyError);
            }
        });
        jsonObjectRequest.setRetryPolicy(retryPolicy);
        SupafitApplication.getInstance().addToRequestQueue(jsonObjectRequest);
    }



    public static final void getPlansPackage(Context mContext, final NetworkHandler handler){

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URLFactory.GET_PACKAGE_URL,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Program> cases = new Gson().fromJson(response.toString(), new TypeToken<List<Program>>() {}.getType());
                handler.success(cases);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handler.failure(error);
            }
        });

        request.setRetryPolicy(retryPolicy);
        SupafitApplication.getInstance().addToRequestQueue(request);
    }

    public static final void getSummaryData(Context mContext,final NetworkHandler handler,String date){
    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URLFactory.GET_SUMMARY_URL + date , null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {

            ActivitySummary summary = new Gson().fromJson(response.toString(), ActivitySummary.class);
            handler.success(summary);
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            handler.failure(error);
        }
    });
        request.setRetryPolicy(retryPolicy);
        SupafitApplication.getInstance().addToRequestQueue(request);
    }

    public static final void getPlanDetailsforMeal(final NetworkHandler handler, long id, String startDate, String endDate)throws JSONException{
        JsonArrayRequest planRequest = new JsonArrayRequest(Request.Method.GET, String.format(URLFactory.GET_MEAL_PLAN_DETAILS,
                String.valueOf(id), startDate, endDate), null,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<MealPlans> meals = new ArrayList<MealPlans>();
                Gson gson = new Gson();
                for(int i=0; i<response.length(); i++){
                    try {
                        meals.add(gson.fromJson(response.get(i).toString(), MealPlans.class));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                handler.success(meals);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                handler.failure(error);
            }
        });
        planRequest.setRetryPolicy(retryPolicy);
        SupafitApplication.getInstance().addToRequestQueue(planRequest);
    }


    public static final void updateMealPlanStatus(final Context c, final NetworkHandler handler,MealPlans meal ) throws JSONException{

        final Gson gson = new Gson();
        //String jsonObject = gson.toJson(user);
        final JSONObject obj = new JSONObject(gson.toJson(meal));

        JsonObjectRequest jsonjReq = new JsonObjectRequest(Request.Method.PUT, URLFactory.PUT_MEAL_PLAN_STATUS, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                handler.success(obj);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    public static final void getUserById(final long id, final NetworkHandler handler){
        JsonObjectRequest request = new JsonObjectRequest(URLFactory.GET_USER_BY_ID + id, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                handler.success(new Gson().fromJson(response.toString(), User.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handler.failure(error);
            }
        });

        request.setRetryPolicy(retryPolicy);
        SupafitApplication.getInstance().addToRequestQueue(request);
    }
}
