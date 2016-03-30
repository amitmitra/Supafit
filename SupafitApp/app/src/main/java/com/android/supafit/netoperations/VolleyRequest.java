package com.android.supafit.netoperations;

import android.content.Context;
import android.util.Log;

import com.android.supafit.SupafitApplication;
import com.android.supafit.model.networkmodel.FitnessGoal;
import com.android.supafit.model.networkmodel.Food;
import com.android.supafit.model.networkmodel.MedicalCondition;
import com.android.supafit.model.networkmodel.PlanPackage;
import com.android.supafit.model.networkmodel.Token;
import com.android.supafit.model.networkmodel.User;
import com.android.supafit.netoperations.handler.NetworkHandler;
import com.android.supafit.utils.AppPreferences;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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


    public static final void signUpUser(final Context c, final NetworkHandler handler, User user) throws JSONException {

        final Gson gson = new Gson();
        String jsonObject = gson.toJson(user);
        JSONObject obj = new JSONObject(jsonObject);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URLFactory.SIGNUP_MANUAL_URL, obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                User user = gson.fromJson(jsonObject.toString(), User.class);
                handler.success(user);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                handler.failure(volleyError);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=UTF-8");
                headers.put("Client_id", new AppPreferences(c).getClientId());
                headers.put("Client_secret", new AppPreferences(c).getClientSecret());
                return headers;
            }
        };


        jsonObjectRequest.setRetryPolicy(retryPolicy);
        SupafitApplication.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    public static final void signInUser(final Context c, final NetworkHandler handler, final String username, final String password) throws JSONException{

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URLFactory.SIGNIN_URL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    final Gson gson = new Gson();
                    Token token = gson.fromJson(jsonObject.getJSONObject("token").toString(), Token.class);
                    new AppPreferences(c).setAccessToken(token.getAccess_token());
                    new AppPreferences(c).setRefreshToken(token.getRefresh_token());
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
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-type", "application/json; charset=UTF-8");
                headers.put("Client_id", new AppPreferences(c).getClientId());
                headers.put("Client_secret", new AppPreferences(c).getClientSecret());
                headers.put("user_name", username);
                headers.put("password", password);
                return headers;
            }
        };
        jsonObjectRequest.setRetryPolicy(retryPolicy);
        SupafitApplication.getInstance().addToRequestQueue(jsonObjectRequest);
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
    public static final void updateFoodInformation(final Context c, final NetworkHandler handler, final ArrayList<Food> food) throws JSONException{


        final Gson gson = new Gson();
        String jsonObject = gson.toJson(food);
        JSONObject obj = new JSONObject(jsonObject);
      String url = URLFactory.USERS_FOOD_PREFERENCES + "?user_id=" + (new AppPreferences(c).getUserId());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url ,obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    final Gson gson = new Gson();
                    //User user = gson.fromJson(jsonObject.getJSONObject("user").toString(), User.class);
                    handler.success(new Object());
                } catch (Exception e){
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

    public static final void updateMedicalConditionInformation(final Context c, final NetworkHandler handler, final ArrayList<MedicalCondition> food) throws JSONException{


        final Gson gson = new Gson();
        String jsonObject = gson.toJson(food);
        JSONObject obj = new JSONObject(jsonObject);
      String url = URLFactory.USERS_FOOD_PREFERENCES + "?user_id=" + (new AppPreferences(c).getUserId());
      JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url,obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    final Gson gson = new Gson();
                    //User user = gson.fromJson(jsonObject.getJSONObject("user").toString(), User.class);
                    handler.success(new Object());
                } catch (Exception e){
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

  public static final void updateMyGoals(final Context c, final NetworkHandler handler, final ArrayList<FitnessGoal> food) throws JSONException{


    final Gson gson = new Gson();
    String jsonObject = gson.toJson(food);
    JSONObject obj = new JSONObject(jsonObject);
    String url = URLFactory.USERS_FOOD_PREFERENCES + "?user_id=" + (new AppPreferences(c).getUserId());
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url,obj, new Response.Listener<JSONObject>() {
      @Override
      public void onResponse(JSONObject jsonObject) {
        try {
          final Gson gson = new Gson();
          //User user = gson.fromJson(jsonObject.getJSONObject("user").toString(), User.class);
          handler.success(new Object());
        } catch (Exception e){
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

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URLFactory.GET_PACKAGE_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<PlanPackage> cases = new Gson().fromJson(response.toString(), new TypeToken<List<PlanPackage>>() {}.getType());
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
}
