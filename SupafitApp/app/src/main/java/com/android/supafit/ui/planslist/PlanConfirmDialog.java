package com.android.supafit.ui.planslist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.supafit.R;
import com.android.supafit.SupafitApplication;
import com.android.supafit.netoperations.URLFactory;
import com.android.supafit.netoperations.networkmodel.program.Program;
import com.android.supafit.netoperations.networkmodel.user.User;
import com.android.supafit.ui.LightFontTextView;
import com.android.supafit.ui.RegularFontTextViewBold;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlanConfirmDialog extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.plan_name_selected)
    RegularFontTextViewBold planNameSelected;
    @Bind(R.id.plan_description_selected)
    LightFontTextView planDescriptionSelected;
    @Bind(R.id.plan_title_selected)
    LightFontTextView planTitleSelected;
    @Bind(R.id.plan_cost_selected)
    RegularFontTextViewBold planCostSelected;
    @Bind(R.id.confirm)
    AppCompatButton confirm;
    Program planPackage;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_confirm_dialog);
        ButterKnife.bind(this);

        planPackage = (Program) getIntent().getSerializableExtra("planPackage");
        planNameSelected.setText(planPackage.getName());
        planDescriptionSelected.setText(planPackage.getDescription());
        planTitleSelected.setText(planPackage.getTitle());
        planCostSelected.setText("Rs " + planPackage.getCost() + "/-");

        confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //
        Gson gson = new Gson();
        JSONObject obj = new JSONObject();
        try {
            JSONObject userObj = new JSONObject();
            userObj.put("id", 1);
            obj.put("User", userObj);
            obj.put("programId", planPackage.getId());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            String date = df.format(cal.getTime());
            obj.put("startDate", date);
            obj.put("cost", planPackage.getCost());
            obj.put("status", "ACTIVE");
            selectPlan(obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void selectPlan(JSONObject obj){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URLFactory.POST_PLAN_SUBSCRIBE,obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Posting data",response.toString());
                        Toast.makeText(PlanConfirmDialog.this,"Plan has been updated Successfully",Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(PlanConfirmDialog.this,"Some Problem occured",Toast.LENGTH_SHORT).show();
            }
        });
        SupafitApplication.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}
