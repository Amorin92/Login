package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btLogin;
    EditText campoEmail;
    EditText campoSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btLogin = findViewById(R.id.button);
        campoEmail = findViewById(R.id.email);
        campoSenha = findViewById(R.id.senha);
        btLogin.setOnClickListener(this);
        campoEmail.setOnClickListener(this);
        campoSenha.setOnClickListener(this);

        // Configure o método de clique para o TextView "Esqueci a Senha"
        TextView esqueciSenhaTextView = findViewById(R.id.forgpass);
        esqueciSenhaTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Direcionar para a página desejada (MainActivity3 neste caso)
                Intent lost = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(lost);
            }
        });
    }

    @Override
    public void onClick(View view) {
        String login1 = campoEmail.getText().toString().trim();
        String senha1 = campoSenha.getText().toString().trim();

        if (login1.isEmpty() || senha1.isEmpty() || login1.contains(" ") || senha1.contains(" ")) {
            if (login1.isEmpty() && senha1.isEmpty()) {
                campoEmail.setError("Preencha!");
                campoSenha.setError("Preencha!");
            } else if (login1.isEmpty()) {
                campoEmail.setError("Digite o seu e-mail");
            } else {
                campoSenha.setError("Digite sua senha");
            }
        } else if (!Patterns.EMAIL_ADDRESS.matcher(login1).matches()) {
            campoEmail.setError("Digite um endereço de e-mail válido");
        } else {
            String valoresDigitados = "E-mail: " + login1 + ", Senha: " + senha1;
            Log.e("valores: ", valoresDigitados);

            Intent i = new Intent(MainActivity.this, MainActivity2.class);

            i.putExtra("email_digitado", login1);
            i.putExtra("senha_digitada", senha1);

            Toast.makeText(getApplicationContext(), "Usuário Logado", Toast.LENGTH_SHORT).show();

            startActivity(i);
        }
    }

    public void esqueciSenhaClicked(View view) {

    }
}
