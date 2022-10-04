package com.example.cep;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cep.BD.CEP;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private List<CEP> cepList = new ArrayList<CEP>();


    public Adapter(List<CEP> lista)
    {
        this.cepList = lista;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        StringBuilder mod = new StringBuilder();

        mod.append(this.cepList.get(position).getLocalidade())
                .append("\n")
                .append(this.cepList.get(position).getBairro())
                .append("\n")
                .append(this.cepList.get(position).getLocalidade());

        holder.cep.setText(this.cepList.get(position).getCep());

        holder.endereco.setText(mod.toString());
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_adapter_view, parent, false);
        return new MyViewHolder(itemLista);
    }


    @Override
    public int getItemCount()
    {
        return this.cepList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView cep, endereco;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            //cep = itemView.findViewById(R.id.textCEP);
            endereco = itemView.findViewById(R.id.textEndereco);
        }
    }
}

 //*/