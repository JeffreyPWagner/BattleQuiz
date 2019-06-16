package com.example.battlequiz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import static com.example.battlequiz.HomeScreenActivity.currentUser;
import static com.example.battlequiz.HomeScreenActivity.mAuth;

public class SignUpActivity extends AppCompatActivity {

    Button signUpBut;
    EditText emailText;
    EditText passwordText;
    EditText verifyPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        emailText = findViewById(R.id.signup_email_et);
        passwordText = findViewById(R.id.signup_password_et);
        verifyPasswordText = findViewById(R.id.password_verify_et);

        signUpBut = findViewById(R.id.signup_but);
        signUpBut.setOnClickListener((View v)->{
            String email = emailText.getText().toString();
            String password = passwordText.getText().toString();
            String verifyPassword = verifyPasswordText.getText().toString();
            if(!password.equals(verifyPassword) ){
                passwordText.setText("");
                verifyPasswordText.setText("");
                Toast.makeText(SignUpActivity.this, "Passwords do not match.",
                        Toast.LENGTH_SHORT).show();
            }
            else {
                if (!(email.equals("") || password.equals("") || verifyPassword.equals(""))) {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("SIGNUP", "createUserWithEmail:success");
                                        HomeScreenActivity.currentUser = mAuth.getCurrentUser();
                                        HomeScreenActivity.userID = currentUser.getUid();
                                        //updateUI(user);
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("SIGNUP", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }

                                    // ...
                                }
                            });
                }
            }
        });
    }

}
