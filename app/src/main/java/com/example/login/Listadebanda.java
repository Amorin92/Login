package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
import java.util.ArrayList;

public class Listadebanda extends AppCompatActivity {

    ArrayList<String> bandasRelacionadas;
    ArrayList<String> bandasRelacionadasCopia;
    ArrayAdapter<String> arrayAdapter;
    SearchView searchView;
    ListView listView;

    Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listadebanda);

        searchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.listViewBandas);

        searchView.setIconified(false);

        // Recupere os estilos musicais selecionados do intent
        ArrayList<String> estilosSelecionados = getIntent().getStringArrayListExtra("estilosSelecionados");

        bandasRelacionadas = new ArrayList<>();
        bandasRelacionadasCopia = new ArrayList<>();

        for (String estilo : estilosSelecionados) {
            if (estilo.equals("Rock")) {
                bandasRelacionadas.add("The Beatles");
                bandasRelacionadas.add("Queen");
                bandasRelacionadas.add("Guns N’ Roses");
            } else if (estilo.equals("POP")) {
                bandasRelacionadas.add("Taylor Swift");
                bandasRelacionadas.add("Adele");
                bandasRelacionadas.add("The Weeknd");
            } else if (estilo.equals("Pagode")) {
                bandasRelacionadas.add("Jorge Aragão");
                bandasRelacionadas.add("Alexandre Pires");
                bandasRelacionadas.add("Péricles");
            } else if (estilo.equals("MPB")) {
                bandasRelacionadas.add("Elis Regina");
                bandasRelacionadas.add("Gilberto Gil");
                bandasRelacionadas.add("Milton Nascimento");
            } else if (estilo.equals("Blues")) {
                bandasRelacionadas.add("Howlin'Wolf");
                bandasRelacionadas.add("T-Bone Walker");
                bandasRelacionadas.add("Muddy Waters");
            } else if (estilo.equals("Sertanejo")) {
                bandasRelacionadas.add("Marília Mendonça");
                bandasRelacionadas.add("Jorge e Mateus");
                bandasRelacionadas.add("Henrique e Juliano");
            }
        }

        btnVoltar = findViewById(R.id.btnv);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Mantenha uma cópia original das bandas relacionadas
        bandasRelacionadasCopia.addAll(bandasRelacionadas);

        // Configure o adapter para a ListView com os dados iniciais
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bandasRelacionadas);
        listView.setAdapter(arrayAdapter);

        // Configure o ouvinte de consulta para o SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fazerBusca(newText);
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    private void fazerBusca(String s) {
        bandasRelacionadas.clear();

        s = s.toLowerCase();

        for (String item : bandasRelacionadasCopia) {
            if (item.toLowerCase().contains(s)) {
                bandasRelacionadas.add(item);
            }
        }
    }
}
