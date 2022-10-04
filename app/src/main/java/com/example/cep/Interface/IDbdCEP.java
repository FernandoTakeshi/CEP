package com.example.cep.Interface;

import com.example.cep.BD.CEP;

import java.util.List;

    public  interface IDbdCEP
    {
        public boolean salvar(CEP dbCep);
        public boolean atualizar(CEP dbCep);
        public boolean deletar(CEP dbCep);

        public List<CEP> listar();
    }
