package com.example.cep;

import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConsultaCEP extends AsyncTask<String, Void, String> //MainActivity
{
    TextView textCEP;


    public ConsultaCEP(TextView tt)
        {
            textCEP = tt;
        }

    @Override
    protected String doInBackground(String... string) {
        String stringURL = string[0];
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        StringBuffer stringBuffer =new StringBuffer();

        try {
            URL url = new URL(stringURL);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            inputStream = conexao.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String linha = "";
            while ((linha = reader.readLine()) != null)
                {
                stringBuffer.append(linha);
                }
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }//*/

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);


        String logradouro = null;
        String cep = null;
        String complemento = null;
        String bairro = null;
        String localidade = null;
        String uf = null;

        StringBuilder sb = new StringBuilder();
        //String objetoValor, valorMoeda, simbolo = null;
        try {
            JSONObject jsonObject = new JSONObject(s);

            logradouro = jsonObject.getString("logradouro");
            cep = jsonObject.getString("cep");
            complemento = jsonObject.getString("complemento");
            bairro = jsonObject.getString("bairro");
            localidade = jsonObject.getString("localidade");
            uf = jsonObject.getString("uf");
            sb.append(logradouro).append("\n").append(localidade).append("\n").append(cep)
                    .append("\n").append(complemento).
                    append("\n").append(bairro).append("\n").append(uf).append("\n");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        textCEP.setText(sb);
    }
}
