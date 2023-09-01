package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView textEmail = findViewById(R.id.textEmail);
        TextView textSenha = findViewById(R.id.textSenha);

        Intent intent2 = getIntent();
        if (intent2 != null) {
            String email = intent2.getStringExtra("email_digitado");
            String senha = intent2.getStringExtra("senha_digitada");

            textEmail.setText("E-mail: " + email);
            textSenha.setText("Senha: " + senha);
        }

        Button btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(view -> finish());

    }
}