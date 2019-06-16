package com.example.battlequiz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import static com.example.battlequiz.HomeScreenActivity.currentUser;
import static com.example.battlequiz.HomeScreenActivity.mAuth;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;

    TextView emailEditText;
    TextView passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        emailEditText = findViewById(R.id.email_et);
        passwordEditText = findViewById(R.id.password_et);

        loginButton = findViewById(R.id.login_but);
        loginButton.setOnClickListener((View v)->{
            //Authenication stuff
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("Sign", "signInWithEmail:success");
                                HomeScreenActivity.currentUser = mAuth.getCurrentUser();
                                HomeScreenActivity.userID = currentUser.getUid();
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("Sing", "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                emailEditText.setText("");
                                passwordEditText.setText("");
                                //updateUI(null);
                            }

                            // ...
                        }
                    });
        });
    }

}
