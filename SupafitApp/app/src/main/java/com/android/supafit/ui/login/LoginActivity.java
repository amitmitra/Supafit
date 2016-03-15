package com.android.supafit.ui.login;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.supafit.R;
import com.android.supafit.database.DatabaseHandler;
import com.android.supafit.gcm.GcmRegistrationIntentService;
import com.android.supafit.gcm.QuickstartPreferences;
import com.android.supafit.model.dbmodel.DBUser;
import com.android.supafit.model.networkmodel.User;
import com.android.supafit.netoperations.VolleyRequest;
import com.android.supafit.netoperations.handler.NetworkHandler;
import com.android.supafit.ui.planslist.PlanListActivity;
import com.android.supafit.ui.signup.SignupActivity;
import com.android.supafit.utils.AppPreferences;
import com.android.supafit.utils.AppUtility;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;

import org.json.JSONException;

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
        FacebookSdk.sdkInitialize(this);
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
                /*Timber.v("loginResult.getAccessToken().getToken() = " + loginResult.getAccessToken().getToken());
                Toast.makeText(getApplicationContext(),"Facebook login success",Toast.LENGTH_SHORT).show();
                loginPresenter.handleFbLogin(loginResult.getAccessToken().getToken());*/
                /*GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                System.out.println("object = [" + object + "], response = [" + response + "]");
                                loginPresenter.sendLoginParameter("facebook");
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,first_name,last_name");
                request.setParameters(parameters);
                request.executeAsync();*/
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

    private void intitalizeGoogleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(Scopes.PLUS_LOGIN))
                .requestIdToken(getString(R.string.google_oauth_client))
                .requestServerAuthCode(getString(R.string.google_oauth_client))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
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

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN && resultCode != RESULT_CANCELED) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            GoogleSignInAccount acct = result.getSignInAccount();
            String googleSererAuthCode = acct.getServerAuthCode();//acct.getIdToken()
            Log.i("AccessToken Gmail ", googleSererAuthCode);
            String idToken = acct.getIdToken();
            Toast.makeText(this, "Sign in success", Toast.LENGTH_SHORT).show();
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }



   /* @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            // ...
        }
    }*/

    @OnClick(R.id.sign_in_button)
    public void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("LoginActivity.onResume");
        //AppEventsLogger.activateApp(this);
     /*   rXsubscription = RxUtils.getNewCompositeSubIfUnsubscribed(rXsubscription);
        rXsubscription.add(rxBus.toObserverable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                if(o instanceof GitHubUser){
                    System.out.println("LoginActivity.call RxBusEvent");
                    GitHubUser gitHubUser = ((GitHubUser)o);
                    nwHitCOunt++;
                    sampleNwResponseText.setText(nwHitCOunt + " " + gitHubUser.getName() + " time " + gitHubUser.getTimestamp());
                }
            }
        }));*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("LoginActivity.onPause");
    }

    /*public ActivityComponent mActivityComponent;
    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(SupaFitApplication.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }*/

    @Override
    public void showProgressBar() {

    }

    @Override
    public void stopProgressBar() {

    }

    @Override
    public void goToNextPage() {
        sendGcmTokenToServer();
        startActivity(PlanListActivity.getUserListActivityIntent(this));
    }

    @Override
    public void setSampleResponseText(String text) {

    }


    public void sendGcmTokenToServer() {

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                //boolean sentToken = preferencesHelper.getGcmTokenSent();
                String sentToken = intent.getExtras().getString("gcm_token");
                if (sentToken != null && !sentToken.isEmpty()) {
                    //TODO : GCM Registration SUCCESS
                } else {
                    //TODO : GCM Registration FAILIURE
                }
            }
        };

        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(QuickstartPreferences.REGISTRATION_COMPLETE));
        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, GcmRegistrationIntentService.class);
            startService(intent);
        }
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported.");
                //finish();
            }
            return false;
        }
        return true;
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
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onSignupSuccess();
                    }
                }, 2000);
    }

    private void onSignupSuccess() {
        NetworkHandler signInHandler = new NetworkHandler() {
            @Override
            public void success(Object response) {
                User signinUser = (User) response;
                new AppPreferences(LoginActivity.this).setUserEmail(signinUser.getUserId());
                new AppPreferences(LoginActivity.this).setUserPassword(signinUser.getOtp());

                DBUser dbUser = new DBUser();
                dbUser.setName(signinUser.getName());
                dbUser.setJoining_date(AppUtility.formatDate(Calendar.getInstance().getTime()));
                dbUser.setPhone_number(signinUser.getPhoneNumbers().get(0).getNumber());
                new DatabaseHandler(LoginActivity.this).addNewUser(dbUser);

                Intent intent = new Intent(LoginActivity.this, PlanListActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right_to_left, R.anim.slide_right_to_half_left);
            }

            @Override
            public void failure(Exception e) {
                AppUtility.showLongSnackBar(mCoordinatorLayout, "Network Problem!");
            }
        };


        try {
            VolleyRequest.signInUser(LoginActivity.this,signInHandler,Email,Password);
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
