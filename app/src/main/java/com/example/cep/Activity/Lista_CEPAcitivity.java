package com.example.cep.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cep.Adapter;
import com.example.cep.BD.CEP;
import com.example.cep.BD.cepDAO;
import com.example.cep.R;

import java.util.ArrayList;
import java.util.List;

public class Lista_CEPAcitivity extends AppCompatActivity {

    Button btnVoltar;
    private RecyclerView rvlista;
    List<Lista_CEPAcitivity> lista_ceps = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cep);

        btnVoltar = findViewById(R.id.btnVolar);
        rvlista = findViewById(R.id.recyclerView);

       Endereco();

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volta = new Intent(getBaseContext(), MainActivity.class);
                Bundle bundle = new Bundle();
                startActivity(volta);
            }
        });
    }


    public void Endereco() {
        rvlista = findViewById(R.id.recyclerView);

        List<CEP> dbcep = new ArrayList<>();

        cepDAO cepdao = new cepDAO(getApplicationContext());

        Adapter adapter = new Adapter(cepdao.listar());

        rvlista.setHasFixedSize(true);
        rvlista.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        rvlista.setAdapter(adapter);
    }

}