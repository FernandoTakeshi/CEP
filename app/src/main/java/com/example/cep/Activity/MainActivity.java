package com.example.cep.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cep.BD.CEP;
import com.example.cep.R;
import com.example.cep.Interface.Services;
import com.example.cep.BD.cepDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{
    //Variaveis
    EditText editCEP;
    TextView textCEP;
    Button btnConsultaCEP, btnEnderecos, btnSalvar;
    String Resultado;
    CEP Resp;
    private com.example.cep.BD.CEP cep = null;
    private cepDAO cepdao = null;
    private Retrofit retrofit;
    double Latitude, Lontitude;
    double CEP;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //id
        editCEP = findViewById(R.id.editCEP);
        textCEP = findViewById(R.id.textCEP);
        btnConsultaCEP = findViewById(R.id.btnConsultaCEP);
        btnEnderecos = findViewById(R.id.btnEnderecos);
        btnSalvar = findViewById(R.id.btnSalvar);
        recyclerView = findViewById(R.id.recyclerView);
        //
        String urlCep = "https://viacep.com.br/ws/";
        retrofit =  new Retrofit.Builder()
                .baseUrl(urlCep)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //
        //Endereco();

        btnConsultaCEP.setOnClickListener(new View.OnClickListener()//Botão para consulta CEP
        {
            @Override
            public void onClick(View view)
            {
                if(editCEP.getText().length() == 8)
                {
                    Services services = retrofit.create(Services.class);
                    String cep = editCEP.getText().toString();
                    Call<CEP> call = services.recuperarCEP(cep);
                    call.enqueue(new Callback<CEP>()
                    {
                        @Override
                        public void onResponse(Call<CEP> call, Response<CEP> response)
                        {
                            if(response.isSuccessful())
                            {

                                CEP cep = response.body();
                                textCEP.setText(cep.getLogradouro()+", Bairro: "+cep.getBairro()+", Cidade: "+cep.getLocalidade()+", Estado: "+cep.getUf());

                                Resultado = "Cep: "+ cep.getCep()+", Estado: "+cep.getUf()+", Cidade: "+cep.getLocalidade()+", Bairro: "+cep.getBairro()+", "+cep.getLogradouro();

                                Resp = cep;

                                String searchString = Resultado;
                                Geocoder geocoder = new Geocoder(MainActivity.this);
                                List<Address> list = new ArrayList<>();
                                try
                                {


                                    list = geocoder.getFromLocationName(searchString, 1);
                                } catch (IOException e)
                                {
                                    e.printStackTrace();
                                }

                               // Log.i("vl","inicio");
                                if(list.size() > 0)
                                {
                                    Address address = list.get(0);

                                    Latitude = address.getLatitude();
                                    Lontitude = address.getLongitude();

                                    //Log.i("vl","fim");
                                }
                            }
                        }
                        @Override
                        public void onFailure(Call<CEP> call, Throwable t)
                        {

                        }
                    });
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Cep inválido ou inexistente.", Toast.LENGTH_SHORT).show();
                }
            }

        });

        btnSalvar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(Resp != null)
                {
                    cepdao = new cepDAO(getApplicationContext());
                    cepdao.salvar(Resp);
                    Toast.makeText(getApplicationContext(), "Endereço salvo!", Toast.LENGTH_SHORT).show();


                }
            }
        });

        btnEnderecos.setOnClickListener(new View.OnClickListener() //Botão para ver a lista de CEP salvas
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getBaseContext(), Lista_CEPAcitivity.class);
                Bundle params = new Bundle();
                startActivity(intent);
            }
        });
        
    }
}

