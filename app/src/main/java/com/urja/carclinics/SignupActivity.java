package com.urja.carclinics;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.urja.carclinics.model.Customer;
import com.urja.carclinics.model.CustomerAddress;
import com.urja.carclinics.utils.AlertDialog;
import com.urja.carclinics.utils.CurrentLoggedInUser;
import com.urja.carclinics.utils.DatabaseConstants;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    FirebaseUser mCurrentUser;
    private EditText inputEmail, inputPassword, fullName, inputMobile;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private Customer mCustomer;
    private CustomerAddress mCustomerAddress;
    private String mCurrentUserId = null;
    //private ProgressBar progressBar;
    private FirebaseAuth auth;
    private DatabaseReference mdatabaseRootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference mCustomerRef = mdatabaseRootRef.child(DatabaseConstants.TABLE_CUSTOMER);// Add Name and Phone number to 'Customer' object
    private DatabaseReference mCustomerAddressRef = mdatabaseRootRef.child(DatabaseConstants.TABLE_CUSTOMER_ADDRESS);
    private ProgressDialog mProgressDialog;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        //UI Initialization
        //btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputMobile = (EditText) findViewById(R.id.mobileNum);
        inputPassword = (EditText) findViewById(R.id.password);
        inputPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.keyRegister || id == EditorInfo.IME_NULL) {
                    attemptRegister();
                    return true;
                }
                return false;
            }
        });

        //progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);
        fullName = (EditText) findViewById(R.id.fullName);

        //Event Listeners
        btnResetPassword.setOnClickListener(this);
        //btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_up_button:
                attemptRegister();
                break;
            /*case R.id.sign_in_button:
                finish();
                break;*/

            case R.id.btn_reset_password:
                startActivity(new Intent(SignupActivity.this, ResetPasswordActivity.class));
                break;

        }

    }

    private void attemptRegister() {
        mProgressDialog = new ProgressDialog(this);
        //mProgressDialog.setTitle(R.string.title_progress_dialog);
        mProgressDialog.setMessage("Creating Account...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();


        String email = inputEmail.getText().toString().trim();
        String mobile = inputMobile.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String name = fullName.getText().toString().toString();

        if (TextUtils.isEmpty(name)) {
            //Toast.makeText(getApplicationContext(), R.string.warning_name, Toast.LENGTH_SHORT).show();
            fullName.setError(getString(R.string.warning_name));
            mProgressDialog.dismiss();
            return;
        }

        if (TextUtils.isEmpty(mobile)) {
            //Toast.makeText(getApplicationContext(), R.string.warning_mobile, Toast.LENGTH_SHORT).show();
            inputMobile.setError(getString(R.string.warning_mobile));
            mProgressDialog.dismiss();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            //Toast.makeText(getApplicationContext(), R.string.warning_email, Toast.LENGTH_SHORT).show();
            inputEmail.setError(getString(R.string.warning_email));
            mProgressDialog.dismiss();
            return;
        }

        if (!TextUtils.isEmpty(email)){// Check if it is a valid email address
            String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);

            if (!matcher.matches()){
                inputEmail.setError(getString(R.string.warning_valid_email));
                mProgressDialog.dismiss();
                return;
            }
        }

        if (TextUtils.isEmpty(password)) {
            //Toast.makeText(getApplicationContext(), R.string.warning_password, Toast.LENGTH_SHORT).show();
            inputPassword.setError(getString(R.string.warning_password));
            mProgressDialog.dismiss();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), R.string.minimum_password, Toast.LENGTH_SHORT).show();
            mProgressDialog.dismiss();
            return;
        }

        //progressBar.setVisibility(View.VISIBLE);
        //create user
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            mProgressDialog.dismiss();
                            AlertDialog.Alert(SignupActivity.this, getString( R.string.info_signup_failed), task.getException().getMessage().toString());
                        } else {
                            //Update phone Number for that User
                            updateUserData();
                            mProgressDialog.dismiss();
                            //startActivity(new Intent(SignupActivity.this, MainActivity.class));
                            startActivity(new Intent(SignupActivity.this, WelcomeDashboardActivity.class));
                            finishAffinity();//Close other Activities, Issue : Login Screen is visible if we go back from WelcomeDashboardActivity
                        }
                    }
                });
    }

    private void updateUserData() {
        String fullName = this.fullName.getText().toString();
        String mobile = this.inputMobile.getText().toString();
        if (mCurrentUserId == null) {
            mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
            CurrentLoggedInUser.setCurrentFirebaseUser(mCurrentUser);
            mCurrentUserId = mCurrentUser.getUid();
        }


        mCustomer = new Customer();
        mCustomer.setName(fullName);
        mCustomer.setMobile(mobile);
        mCustomer.setRegToken(FirebaseInstanceId.getInstance().getToken());//Keep the token So that From Admin We can Send Message.

        mCustomerAddress = new CustomerAddress();
        mCustomerAddress.setAddress("");
        mCustomerAddress.setCountry("India");
        mCustomerAddress.setPincode("");

        /*mCustomerRef.child(mCurrentUserId).push().setValue(mCustomer);
        mCustomerAddressRef.child(mCurrentUserId).setValue(mCustomerAddress);*/

        mCustomerRef.child(mCurrentUserId).setValue(mCustomer);
        DatabaseReference child = mCustomerAddressRef.child(mCurrentUserId);
        child.setValue("date", new Date().toString());
        child.setValue(mCustomerAddress);
        child.push();

        CurrentLoggedInUser.setName(fullName);
        CurrentLoggedInUser.setMobile(mobile);
    }
}