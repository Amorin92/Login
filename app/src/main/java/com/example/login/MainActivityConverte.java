package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivityConverte extends AppCompatActivity {

    private EditText valorDigitado;
    private TextView usa, euro, guarani;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_converte);

        valorDigitado = findViewById(R.id.ValorDigitado);
        usa = findViewById(R.id.usa);
        euro = findViewById(R.id.euro);
        guarani = findViewById(R.id.guarani);

        valorDigitado.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                double valorEntrada = 0.0;
                if (!s.toString().isEmpty()) {
                    valorEntrada = Double.parseDouble(s.toString());
                }

                double valorConvertidoEUA = converterMoeda(valorEntrada, "EUA");
                double valorConvertidoEuro = converterMoeda(valorEntrada, "Euro");
                double valorConvertidoGuarani = converterMoeda(valorEntrada, "Guarani");

                // Use String.format para mostrar apenas o valor sem texto
                usa.setText(String.format("%.2f", valorConvertidoEUA));
                euro.setText(String.format("%.2f", valorConvertidoEuro));
                guarani.setText(String.format("%.2f", valorConvertidoGuarani));
            }


            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    private double converterMoeda(double valorEntrada, String moeda) {

        double taxaDeCambio = 0.0;
        switch (moeda) {
            case "EUA":
                taxaDeCambio = 0.2;
                break;
            case "Euro":
                taxaDeCambio = 0.19;
                break;
            case "Guarani":
                taxaDeCambio = 1448.28;
                break;
        }
        return valorEntrada * taxaDeCambio;
    }
}
