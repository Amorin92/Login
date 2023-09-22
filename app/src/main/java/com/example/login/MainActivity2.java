package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.login.Listadebanda;
import com.example.login.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    ChipGroup chipGroup;
    Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView textEmail = findViewById(R.id.textEmail);
        chipGroup = findViewById(R.id.ChipG1);

        Intent intent = getIntent();
        if (intent != null) {
            String email = intent.getStringExtra("email_digitado");
            textEmail.setText("Olá: " + email);
        }

        btnVoltar = findViewById(R.id.btnVoltar);

       btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btnPronto = findViewById(R.id.buttonVerificaChip);
        btnPronto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Integer> ids = chipGroup.getCheckedChipIds();
                List<String> estilosSelecionados = new ArrayList<>();

                for (Integer id : ids) {
                    Chip chip = chipGroup.findViewById(id);
                    estilosSelecionados.add(chip.getText().toString());
                }

                Intent intent = new Intent(MainActivity2.this, Listadebanda.class);
                intent.putStringArrayListExtra("estilosSelecionados", (ArrayList<String>) estilosSelecionados);
                startActivity(intent);
            }
        });
    }
}
