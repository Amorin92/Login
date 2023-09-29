package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;

public class MainActivityConverte extends AppCompatActivity {

    private EditText brasil, eua, euro, guarani, japao;
    private EditText campoAtual;
    private boolean isUpdating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_converte);

        brasil = findViewById(R.id.brasil);
        eua = findViewById(R.id.eua);
        euro = findViewById(R.id.uniaoeuropeia);
        guarani = findViewById(R.id.paraguai);
        japao = findViewById(R.id.japao);

        // Adicione um TextWatcher a todos os campos de entrada
        brasil.addTextChangedListener(textWatcher);
        eua.addTextChangedListener(textWatcher);
        euro.addTextChangedListener(textWatcher);
        guarani.addTextChangedListener(textWatcher);
        japao.addTextChangedListener(textWatcher);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!isUpdating) {
                double valorEntrada = 0.0;
                if (!s.toString().isEmpty()) {
                    valorEntrada = Double.parseDouble(s.toString());
                }

                // Define os valores de todos os campos com base na moeda de origem
                campoAtual = (EditText) getCurrentFocus();
                atualizarCampos(valorEntrada);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private void atualizarCampos(double valorEntrada) {
        isUpdating = true;

        double taxaDolar = 0.20;
        double taxaEuro = 0.19;
        double taxaGuarani = 1444.59;
        double taxaYen = 29.68;

        if (campoAtual == brasil) {
            eua.setText(formatarValor(valorEntrada * taxaDolar));
            euro.setText(formatarValor(valorEntrada * taxaEuro));
            guarani.setText(formatarValor(valorEntrada * taxaGuarani));
            japao.setText(formatarValor(valorEntrada * taxaYen));
        } else if (campoAtual == eua) {
            brasil.setText(formatarValor(valorEntrada / taxaDolar));
            euro.setText(formatarValor(valorEntrada * (taxaEuro / taxaDolar)));
            guarani.setText(formatarValor(valorEntrada * (taxaGuarani / taxaDolar)));
            japao.setText(formatarValor(valorEntrada * (taxaYen / taxaDolar)));
        } else if (campoAtual == euro) {
            brasil.setText(formatarValor(valorEntrada / taxaEuro));
            eua.setText(formatarValor(valorEntrada / (taxaEuro / taxaDolar)));
            guarani.setText(formatarValor(valorEntrada * (taxaGuarani / taxaEuro)));
            japao.setText(formatarValor(valorEntrada * (taxaYen / taxaEuro)));
        } else if (campoAtual == guarani) {
            brasil.setText(formatarValor(valorEntrada / taxaGuarani));
            eua.setText(formatarValor(valorEntrada / (taxaGuarani / taxaDolar)));
            euro.setText(formatarValor(valorEntrada / (taxaGuarani / taxaEuro)));
            japao.setText(formatarValor(valorEntrada * (taxaYen / taxaGuarani)));
        } else if (campoAtual == japao) {
            brasil.setText(formatarValor(valorEntrada / taxaYen));
            eua.setText(formatarValor(valorEntrada / (taxaYen / taxaDolar)));
            euro.setText(formatarValor(valorEntrada / (taxaYen / taxaEuro)));
            guarani.setText(formatarValor(valorEntrada / (taxaYen / taxaGuarani)));
        }

        isUpdating = false;
    }


    private String formatarValor(double valor) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(valor);
    }
}
