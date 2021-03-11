package com.example.moneylover.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneylover.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

public class RegisterActivity extends AppCompatActivity {

    private static final int GOOGLE_SIGN_REQUEST =112 ;
    FirebaseAuth auth;
    private EditText email_et;
    private EditText pass_et;
    private Button register_btn;
    private TextView signIn_tv;

    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (Build.VERSION.SDK_INT >= 23) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        auth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        initializeGoogleLogin();
        emailAndPasswordLogin();

    }

    private void emailAndPasswordLogin() {
       email_et=findViewById(R.id.etEmail);
       pass_et=findViewById(R.id.etPassword);
       register_btn=findViewById(R.id.btnRegister);
        signIn_tv=findViewById(R.id.tvSignIn);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               register();
            }
        });
        signIn_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,SignInActivity.class));
            }
        });
    }

    private void register() {
        String email=email_et.getText().toString();
        String password=pass_et.getText().toString();
        if(TextUtils.isEmpty(email)){
            email_et.setError("Enter your email");
            return;
        }
        else if(TextUtils.isEmpty(password)){
            pass_et.setError("Enter your password");
            return;
        }


        else if(password.length()<4){
            pass_et.setError("Length should be > 4");
            return;
        }
        else if(!isValidEmail(email)){
            email_et.setError("invalid email");
            return;
        }
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,"Successfully registered",Toast.LENGTH_LONG).show();
                    FirebaseUser user=auth.getCurrentUser();
                    sendUserData(user);
                }
                else{
                    Toast.makeText(RegisterActivity.this,"Sign up fail!",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }
    private Boolean isValidEmail(CharSequence target){
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }


    private void initializeGoogleLogin() {
        Button google_login=findViewById(R.id.btnGoogleLogin);
        google_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doGoogleLogin();
            }
        });
    }

    private void doGoogleLogin() {
        // creating google sign in option object
        GoogleSignInOptions gOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("698603706809-ipnfgn7duf9aoq2camhm97lvlqkmn1l1.apps.googleusercontent.com")
                .requestEmail()
                .requestId()
                .requestProfile()
                .build();

        //creating google client object
        GoogleSignInClient googleSignInClient= GoogleSignIn.getClient(RegisterActivity.this,gOptions);

        //launching google login dialog intent
        Intent intent=googleSignInClient.getSignInIntent();
        startActivityForResult(intent,GOOGLE_SIGN_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //check result come from google
        if(requestCode==GOOGLE_SIGN_REQUEST){
            Task<GoogleSignInAccount> accountTask=GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account=accountTask.getResult(ApiException.class);
                processFirebaseLoginStep(account.getIdToken());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void processFirebaseLoginStep(String idToken) {
        AuthCredential authCredential= GoogleAuthProvider.getCredential(idToken,null);

        auth.signInWithCredential(authCredential)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user=auth.getCurrentUser();
                            sendUserData(user);
                        }

                    }
                });

    }

    private void sendUserData(FirebaseUser user) {
        if(user.getDisplayName()!=null) {
            Toast.makeText(this, "Login Success\n" + user.getDisplayName(), Toast.LENGTH_SHORT).show();
        }
        startActivity(new Intent(RegisterActivity.this,MainActivity.class));
    }
}