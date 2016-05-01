package com.android.supafit.ui.signup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.supafit.R;
import com.android.supafit.activity.MainActivity;
import com.android.supafit.database.DatabaseHandler;
import com.android.supafit.database.dbmodel.DBUser;
import com.android.supafit.netoperations.VolleyRequest;
import com.android.supafit.netoperations.handler.NetworkHandler;
import com.android.supafit.netoperations.networkmodel.user.User;
import com.android.supafit.utils.AppConstants;
import com.android.supafit.utils.AppPreferences;
import com.android.supafit.utils.AppUtility;

import org.json.JSONException;

import java.util.Calendar;

public class SignupActivity extends AppCompatActivity {


    private static final String TAG = SignupActivity.class.getSimpleName();

    String Name, Phone, Email, Password, Password2;
    private CheckBox termsConditioncheck;
    private Button getStarted;
    private EditText name, phone, email, password, password2;
    private ProgressDialog progressDialog;
    private Toolbar toolbar;
    private CoordinatorLayout mCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Signup");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        name = (EditText) findViewById(R.id.input_name);
        phone = (EditText) findViewById(R.id.input_phone);
        email = (EditText) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.create_password);
        password2 = (EditText) findViewById(R.id.create_password2);
        termsConditioncheck = (CheckBox) findViewById(R.id.terms_conditions);
        getStarted = (Button) findViewById(R.id.get_started);
        mCoordinatorLayout =(CoordinatorLayout)findViewById(R.id.coordinartor_layout);

        termsConditioncheck.setText(Html.fromHtml(" I accept the <u>Terms and Conditions<u>"));

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.get_started:
                        signup();
                        break;
                }
            }
        });
    }

    public void signup() {

        if (!validate()) {
            Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog = new ProgressDialog(this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onSignupSuccess();
                    }
                }, 500);
    }

    private void onSignupSuccess() {

        NetworkHandler handler = new NetworkHandler() {
            @Override
            public void success(Object response) {
                if(response == null){
                    Toast.makeText(SignupActivity.this, "Email Already Registered!", Toast.LENGTH_SHORT).show();
                } else {
                    User signupUser = (User) response;
                    DBUser dbUser = new DBUser();
                    dbUser.setName(signupUser.getName());
                    dbUser.setJoining_date(AppUtility.formatDate(Calendar.getInstance().getTime()));
                    if(signupUser.getPhoneNumbers() != null && !signupUser.getPhoneNumbers().isEmpty())
                    dbUser.setPhone_number(signupUser.getPhoneNumbers().get(0).getNumber());
                    new DatabaseHandler(SignupActivity.this).addNewUser(dbUser);
                    new AppPreferences(SignupActivity.this).setUserStatus(AppConstants.USER_SIGNED_IN);
                    new AppPreferences(SignupActivity.this).setLoginType(AppConstants.LOGIN_TYPE_MANUAL);
                    new AppPreferences(SignupActivity.this).setUserEmail(signupUser.getEmail());
                    new AppPreferences(SignupActivity.this).setUserPassword(Password);
                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_right_to_left, R.anim.slide_right_to_half_left);
                    progressDialog.dismiss();
                    finishAffinity();
                }
            }

            @Override
            public void failure(Exception e) {
                AppUtility.showLongSnackBar(mCoordinatorLayout, "Network Problem! Can not Register!");
                progressDialog.dismiss();
            }
        };

        try {
            VolleyRequest.signupUser(Name, Email, Password, Phone, handler);
        } catch (JSONException e) {
            Log.e(TAG, "Not able to Signup user because of " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean validate() {
        boolean valid = true;

        Name = name.getText().toString();
        Phone = phone.getText().toString();
        Email = email.getText().toString();
        Password = password.getText().toString();
        Password2 = password2.getText().toString();

        if (Name.isEmpty() || Name.length() < 5) {
            name.setError("at least 5 characters");
            valid = false;
        } else {
            name.setError(null);
        }

        if (Phone.isEmpty() || Phone.length() < 10) {
            phone.setError("valid phone number");
            valid = false;
        } else {
            phone.setError(null);
        }
        if (Email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            email.setError("enter a valid email address");
            valid = false;
        } else {
            email.setError(null);
        }

        if (Password.isEmpty() || Password.length() < 4 || password.length() > 10) {
            password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            password.setError(null);
        }

        if (Password2.isEmpty() || Password2.length() < 4 || password2.length() > 10) {
            password2.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            password2.setError(null);
        }

        if(!Password.isEmpty() && !Password2.isEmpty() && !Password.equals(Password2)){
            password2.setError("Passwrod and Confirm Password do not match");
            valid = false;
        } else {
            password2.setError(null);
        }

        if (!termsConditioncheck.isChecked()) {
            Toast.makeText(this, "Agree to terms and Conditions", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            termsConditioncheck.setError(null);
        }
        return valid;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_half_left_to_right, R.anim.slide_left_to_right);
    }
}
