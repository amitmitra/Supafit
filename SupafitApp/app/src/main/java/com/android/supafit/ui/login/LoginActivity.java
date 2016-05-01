package com.android.supafit.ui.login;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.supafit.R;
import com.android.supafit.activity.MainActivity;
import com.android.supafit.database.DatabaseHandler;
import com.android.supafit.database.dbmodel.DBUser;
import com.android.supafit.netoperations.VolleyRequest;
import com.android.supafit.netoperations.handler.NetworkHandler;
import com.android.supafit.netoperations.networkmodel.user.User;
import com.android.supafit.ui.planslist.PlanListActivity;
import com.android.supafit.ui.signup.SignupActivity;
import com.android.supafit.utils.AppConstants;
import com.android.supafit.utils.AppPreferences;
import com.android.supafit.utils.AppUtility;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LogInMvpView,GoogleApiClient.OnConnectionFailedListener {

    private static final int RC_SIGN_IN = 9001;
    private final String TAG = LoginActivity.class.getSimpleName();
    private GoogleApiClient mGoogleApiClient;
    private CallbackManager callbackManager;

    @Bind(R.id.login_button)
    LoginButton loginButton;
    @Bind(R.id.sign_in_button)
    SignInButton signInButton;
    @Bind(R.id.create_one_layout)
    TextView create_one_layout;
    @Bind(R.id.coordinartor_layout)
    CoordinatorLayout mCoordinatorLayout;
    @Bind(R.id.input_email)
    TextView input_email;
    @Bind(R.id.input_password)
    EditText input_password;

    String Email, Password;

    private int cahceHitCOunt = 0;
    private int nwHitCOunt = 0;

    private ProgressDialog progressDialog;

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        intitalizeGoogleSignIn();
        initializeFacebookSignIn();
        create_one_layout.setText(Html.fromHtml("No account yet? <u>Create one.<u>"));
    }

    private void initializeFacebookSignIn() {
        loginButton.setReadPermissions(Arrays.asList("public_profile", "user_friends", "email"));
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplicationContext(),"Facebook login success",Toast.LENGTH_SHORT).show();
                progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Accessing facebook...");
                progressDialog.show();
                String accessToken = loginResult.getAccessToken().getToken();
                Log.i("accessToken", accessToken);

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.i("LoginActivity", response.toString());
                        Bundle bFacebookData = getFacebookData(object);
                        final String name = bFacebookData.get("first_name") + " " + bFacebookData.get("last_name");
                        final String email = bFacebookData.getString("email");
                        final String password = bFacebookData.getString("email");

                        NetworkHandler signupHandler = new NetworkHandler() {
                            @Override
                            public void success(Object response) {
                                if(response == null){
                                    NetworkHandler signinHandler = new NetworkHandler() {
                                        @Override
                                        public void success(Object response) {
                                            if(response == null){
                                                Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                                                progressDialog.dismiss();
                                            } else {
                                                User signinUser = (User) response;

                                                DBUser dbUser = new DBUser();
                                                dbUser.setName(signinUser.getName());
                                                dbUser.setJoining_date(AppUtility.formatDate(Calendar.getInstance().getTime()));
                                                if(signinUser.getPhoneNumbers() != null && !signinUser.getPhoneNumbers().isEmpty())
                                                dbUser.setPhone_number(signinUser.getPhoneNumbers().get(0).getNumber());
                                                new DatabaseHandler(LoginActivity.this).addNewUser(dbUser);
                                                new AppPreferences(LoginActivity.this).setUserStatus(AppConstants.USER_SIGNED_IN);
                                                new AppPreferences(LoginActivity.this).setLoginType(AppConstants.LOGIN_TYPE_FACEBOOK);
                                                new AppPreferences(LoginActivity.this).setUserEmail(signinUser.getEmail());
                                                new AppPreferences(LoginActivity.this).setUserPassword(password);

                                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                startActivity(intent);
                                                overridePendingTransition(R.anim.slide_right_to_left, R.anim.slide_right_to_half_left);
                                                progressDialog.dismiss();
                                            }
                                        }

                                        @Override
                                        public void failure(Exception e) {
                                            Toast.makeText(LoginActivity.this, "Facebook Login Problem!", Toast.LENGTH_SHORT).show();
                                            progressDialog.dismiss();
                                        }
                                    };
                                    try {
                                        VolleyRequest.signinUser(email, password, signinHandler);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    User signupUser = (User) response;

                                    DBUser dbUser = new DBUser();
                                    dbUser.setName(signupUser.getName());
                                    dbUser.setJoining_date(AppUtility.formatDate(Calendar.getInstance().getTime()));
                                    if(signupUser.getPhoneNumbers() !=  null && !signupUser.getPhoneNumbers().isEmpty())
                                        dbUser.setPhone_number(signupUser.getPhoneNumbers().get(0).getNumber());
                                    new DatabaseHandler(LoginActivity.this).addNewUser(dbUser);
                                    new AppPreferences(LoginActivity.this).setUserStatus(AppConstants.USER_SIGNED_IN);
                                    new AppPreferences(LoginActivity.this).setLoginType(AppConstants.LOGIN_TYPE_FACEBOOK);
                                    new AppPreferences(LoginActivity.this).setUserEmail(signupUser.getEmail());
                                    new AppPreferences(LoginActivity.this).setUserPassword(password);
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_right_to_left, R.anim.slide_right_to_half_left);
                                    progressDialog.dismiss();
                                }
                            }

                            @Override
                            public void failure(Exception e) {
                                Toast.makeText(getApplicationContext(), "Facebook login error", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        };
                        try {
                            VolleyRequest.signupUser(name, email, password, null, signupHandler);
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
            request.setParameters(parameters);
            request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Facebook login cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getApplicationContext(), "Facebook login error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Bundle getFacebookData(JSONObject object) {

        try {
            Bundle bundle = new Bundle();
            String id = object.getString("id");

            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));

            return bundle;
        } catch (JSONException e){
            Log.e(TAG, "can not get facebook data");
            return null;
        }
    }

    private void intitalizeGoogleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(Scopes.PLUS_LOGIN))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setColorScheme(SignInButton.COLOR_LIGHT);
        signInButton.setScopes(gso.getScopeArray());
        //findViewById(R.id.sign_in_button).setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN && resultCode != RESULT_CANCELED) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            GoogleSignInAccount acct = result.getSignInAccount();
            final String name = acct.getDisplayName();
            final String email = acct.getEmail();
            final String password = acct.getEmail();
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setMessage("Accessing Google account...");
            progressDialog.show();

            NetworkHandler signupHandler = new NetworkHandler() {
                @Override
                public void success(Object response) {
                    if(response == null){
                        NetworkHandler signinHandler = new NetworkHandler() {
                            @Override
                            public void success(Object response) {
                                if(response == null){
                                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                } else {
                                    User signinUser = (User) response;

                                    DBUser dbUser = new DBUser();
                                    dbUser.setName(signinUser.getName());
                                    dbUser.setJoining_date(AppUtility.formatDate(Calendar.getInstance().getTime()));
                                    if(signinUser.getPhoneNumbers() != null && !signinUser.getPhoneNumbers().isEmpty())
                                    dbUser.setPhone_number(signinUser.getPhoneNumbers().get(0).getNumber());
                                    new DatabaseHandler(LoginActivity.this).addNewUser(dbUser);
                                    new AppPreferences(LoginActivity.this).setUserStatus(AppConstants.USER_SIGNED_IN);
                                    new AppPreferences(LoginActivity.this).setLoginType(AppConstants.LOGIN_TYPE_GOOGLE);
                                    new AppPreferences(LoginActivity.this).setUserEmail(signinUser.getEmail());
                                    new AppPreferences(LoginActivity.this).setUserPassword(password);
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_right_to_left, R.anim.slide_right_to_half_left);
                                    progressDialog.dismiss();
                                }
                            }

                            @Override
                            public void failure(Exception e) {
                                Toast.makeText(LoginActivity.this, "Google Login Problem!", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        };
                        try {
                            VolleyRequest.signinUser(email, password, signinHandler);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        User signupUser = (User) response;

                        DBUser dbUser = new DBUser();
                        dbUser.setName(signupUser.getName());
                        dbUser.setJoining_date(AppUtility.formatDate(Calendar.getInstance().getTime()));
                        if(signupUser.getPhoneNumbers() !=  null && !signupUser.getPhoneNumbers().isEmpty())
                            dbUser.setPhone_number(signupUser.getPhoneNumbers().get(0).getNumber());
                        new DatabaseHandler(LoginActivity.this).addNewUser(dbUser);
                        new AppPreferences(LoginActivity.this).setUserStatus(AppConstants.USER_SIGNED_IN);
                        new AppPreferences(LoginActivity.this).setLoginType(AppConstants.LOGIN_TYPE_GOOGLE);
                        new AppPreferences(LoginActivity.this).setUserEmail(signupUser.getEmail());
                        new AppPreferences(LoginActivity.this).setUserPassword(password);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_right_to_left, R.anim.slide_right_to_half_left);
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void failure(Exception e) {
                    Toast.makeText(getApplicationContext(), "Google login Problem!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            };
            try {
                VolleyRequest.signupUser(name, email, password, null, signupHandler);
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @OnClick(R.id.sign_in_button)
    public void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("LoginActivity.onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("LoginActivity.onPause");
        finish();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void stopProgressBar() {

    }

    @Override
    public void goToNextPage() {
        startActivity(PlanListActivity.getUserListActivityIntent(this));
    }

    @Override
    public void setSampleResponseText(String text) {

    }

    public void onLoginButtonClicked(View view) {

        userSignin();


    }

    public void userSignin() {
        if (!validate()) {
            Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog = new ProgressDialog(this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setMessage("Logging in . . . ");
        progressDialog.show();
        onSigninSuccess();
    }

    private void onSigninSuccess() {
        NetworkHandler signInHandler = new NetworkHandler() {
            @Override
            public void success(Object response) {
                if(response == null){
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                } else {
                    User signinUser = (User) response;
                    new AppPreferences(LoginActivity.this).setUserEmail(signinUser.getEmail());
                    new AppPreferences(LoginActivity.this).setUserPassword(Password);

                    DBUser dbUser = new DBUser();
                    dbUser.setName(signinUser.getName());
                    dbUser.setJoining_date(AppUtility.formatDate(Calendar.getInstance().getTime()));
                    if(signinUser.getPhoneNumbers() != null && !signinUser.getPhoneNumbers().isEmpty())
                        dbUser.setPhone_number(signinUser.getPhoneNumbers().get(0).getNumber());
                    new DatabaseHandler(LoginActivity.this).addNewUser(dbUser);

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_right_to_left, R.anim.slide_right_to_half_left);
                    new AppPreferences(LoginActivity.this).setUserStatus(AppConstants.USER_SIGNED_IN);
                    new AppPreferences(LoginActivity.this).setLoginType(AppConstants.LOGIN_TYPE_MANUAL);
                    progressDialog.dismiss();
                }
            }

            @Override
            public void failure(Exception e) {
                AppUtility.showLongSnackBar(mCoordinatorLayout, "Network Problem!");
                progressDialog.dismiss();
            }
        };

        try {
            VolleyRequest.signinUser(Email, Password, signInHandler);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean validate() {
        boolean valid = true;
        Email = input_email.getText().toString();
        Password = input_password.getText().toString();

        if (Email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            input_email.setError("enter a valid email address");
            valid = false;
        } else {
            input_email.setError(null);
        }

        if (Password.isEmpty() || Password.length() < 4 || Password.length() > 10) {
            input_password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            input_password.setError(null);
        }
        return valid;
    }


    public void signupButtonClicked(View view){
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_right_to_left, R.anim.slide_right_to_half_left);
    }
}
