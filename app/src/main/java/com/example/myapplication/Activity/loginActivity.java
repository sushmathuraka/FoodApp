package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class loginActivity extends BaseActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setVariable();

        }

    private void setVariable() {
        binding.loginbtn.setOnClickListener(v -> {
            String email=binding.userEdit.getText().toString();
            String password=binding.passEdit.getText().toString();
            if(!email.isEmpty() && !password.isEmpty()){
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(loginActivity.this, task ->  {

                    if(task.isSuccessful()){
                        startActivity(new Intent(loginActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(loginActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                    }

                });
            }else{
                Toast.makeText(loginActivity.this, "Please fill user name and password", Toast.LENGTH_SHORT).show();
            }

        });

    }

}