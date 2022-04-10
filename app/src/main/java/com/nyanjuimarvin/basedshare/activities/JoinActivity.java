package com.nyanjuimarvin.basedshare.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.nyanjuimarvin.basedshare.databinding.ActivityJoinBinding;
import com.nyanjuimarvin.basedshare.databinding.ActivityLoginBinding;
import com.nyanjuimarvin.basedshare.firebase.authentication.Authentication;

import java.util.Objects;

public class JoinActivity extends AppCompatActivity {

    private ActivityJoinBinding joinBinding;
    private ActivityLoginBinding loginBinding;
    private FirebaseAuth.AuthStateListener listener;
    private FirebaseAuth newUserAuth;
    private FirebaseUser newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        joinBinding = ActivityJoinBinding.inflate(getLayoutInflater());
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = joinBinding.getRoot();
        setContentView(view);
        createAuthStateListener();
        joinBinding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });

        loginBinding.signInText.setOnClickListener(view1 -> {
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        });
    }

    private boolean isValidEmail(String Email) {

        boolean validEmail = (Email != null && Email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"));
        if (!validEmail) {
            joinBinding.userEmail.getText().clear();
            joinBinding.userEmail.setError("Please enter a valid Email");
            return false;
        }
        return true;
    }

    private boolean isValidName(String name) {
        if (name.equals("")) {
            joinBinding.userName.getText().clear();
            joinBinding.userName.setError("Kindly enter your name ");
            return false;
        }
        return true;
    }

    private boolean isPasswordValid(String password, String confirmPassword) {
        if (password.length() < 6) {
            joinBinding.userPassword.getText().clear();
            Toast.makeText(getApplicationContext(), "Password too short", Toast.LENGTH_LONG).show();
            joinBinding.userPassword.setError("Passwords must have 6 characters or more");
            return false;
        } else if (!password.equals(confirmPassword)) {
            joinBinding.confirmPassword.getText().clear();
            Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_LONG).show();
            joinBinding.confirmPassword.setError("Passwords do not match");
            return false;
        }
        return true;
    }

    private boolean validInput(String name, String email, String password, String confirmPassword) {
        return name.matches("([a-zA-z]+|[a-zA-Z]+\\s[a-zA-Z]+)*")
                && email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
                && password.equals(confirmPassword);
    }

    private void createUser() {
        String userName = joinBinding.userName.getText().toString().trim();
        String userEmail = joinBinding.userEmail.getText().toString().trim();
        String userPassword = joinBinding.userPassword.getText().toString().trim();
        String confirmPassword = joinBinding.confirmPassword.getText().toString().trim();

        boolean validEmail = isValidEmail(userEmail);
        boolean validName = isValidName(userName);
        boolean validPassword = isPasswordValid(userPassword, confirmPassword);

        newUserAuth = Authentication.getAuth();

        if (!validEmail || !validName || !validPassword) {
            Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_LONG).show();
            return;
        }

        newUserAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        createFirebaseUser(Objects.requireNonNull(task.getResult().getUser()));
                        sendVerificationEmail();
                        Log.d("Message", "Account creation Successful");
                    } else {
                        Log.w("Warning", "Account Creation Failed", task.getException());
                        Toast.makeText(getApplicationContext(), "Account Creation Failed", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void createFirebaseUser(FirebaseUser user) {

        String userName = joinBinding.userName.getText().toString().trim();
        newUser = newUserAuth.getCurrentUser();

        UserProfileChangeRequest addName = new UserProfileChangeRequest.Builder()
                .setDisplayName(userName)
                .build();

        user.updateProfile(addName).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d("user", Objects.requireNonNull(user.getDisplayName()));
                Toast.makeText(getApplicationContext(), "Name set in firebase", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendVerificationEmail() {
        newUserAuth = Authentication.getAuth();
        FirebaseUser user = newUserAuth.getCurrentUser();

        assert user != null;
        user.sendEmailVerification().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Log.i("Email","Email Sent");
            }
        });
    }

    private void createAuthStateListener() {
        listener = firebaseAuth -> {
            final FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        newUserAuth = Authentication.getAuth();
        newUserAuth.addAuthStateListener(listener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (listener != null) {
            newUserAuth = Authentication.getAuth();
            newUserAuth.removeAuthStateListener(listener);
        }
    }
}
