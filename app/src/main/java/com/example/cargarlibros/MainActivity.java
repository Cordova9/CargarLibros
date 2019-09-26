package com.example.cargarlibros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edit_buscador;
    Button btn_buscar;
    JSONArray libros;
    RecyclerView rv_buscador;
    List<Item_Libro> buscador_data;
    BuscadorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_buscar = findViewById(R.id.btn_buscar);
        edit_buscador = findViewById(R.id.edit_buscador);
        rv_buscador = findViewById(R.id.rv_libros);
        buscador_data = new ArrayList<>();
        adapter = new BuscadorAdapter(this, buscador_data);
        rv_buscador.setAdapter(adapter);
        rv_buscador.setLayoutManager(new LinearLayoutManager(this));

        ConnectivityManager c = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);//ve si tenemos  coneccion a internet

        NetworkInfo info = c.getActiveNetworkInfo();//getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(info == null) {
            Toast.makeText(this, "Active su Wifi", Toast.LENGTH_LONG).show();
        }else if(!info.isConnected()) {
            Toast.makeText(this, "Error, no tiene internet", Toast.LENGTH_LONG).show();
        }
        final String BASE_URL ="https://www.googleapis.com/books/v1/volumes?";//concatenanto

        final String QUERY_PARAM = "q";

        final String MAX_RESULTS = "maxResults";

        final String PRINT_TYPE = "printType";
        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    final Uri builtURI = Uri.parse(BASE_URL).buildUpon()
                            .appendQueryParameter(QUERY_PARAM,edit_buscador.getText().toString().replace(" ", "+"))
                            .appendQueryParameter(MAX_RESULTS, "10")
                            .appendQueryParameter(PRINT_TYPE, "books")
                            .build();
                    Log.e("ErrorInternet",builtURI.toString());
                    URL requestURL = new URL(builtURI.toString());
                    TaskCargarLibros task = new TaskCargarLibros(MainActivity.this);//porque el constructor requeria el context
                    task.execute(requestURL);//ejecutar

                } catch (Exception e) {
                    Log.e("ErrorInternet", e.toString());
                    e.printStackTrace();
                }
            }
        });

    }

    public void  llenarlibros (JSONArray l){
        libros = l;
        try {
            buscador_data.clear();
            for (int i = 0; i < libros.length();i++) {
                Log.e("ErrorInternet", libros.getJSONObject(i).toString());
                buscador_data.add(new Item_Libro(l.getJSONObject(i).getJSONObject("volumeInfo").getString("title"),
                        libros.getJSONObject(i).getJSONObject("volumeInfo").getString("publishedDate")
                ));
            }
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
