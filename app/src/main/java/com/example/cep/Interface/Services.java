package com.example.cep.Interface;


import com.example.cep.BD.CEP;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Services {
    @GET("{cep}/json/")
    Call<CEP> recuperarCEP(@Path("cep") String cep);
}
