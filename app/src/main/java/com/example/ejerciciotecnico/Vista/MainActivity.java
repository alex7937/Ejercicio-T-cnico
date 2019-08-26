package com.example.ejerciciotecnico.Vista;

import android.content.Intent;
import android.os.Bundle;
import com.example.ejerciciotecnico.Presentador.Presentador;
import com.example.ejerciciotecnico.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    public static String Busqueda= "";
    public static Boolean estadoImagen = true;

    public static RecyclerView mRecyclerView;
    public static ImageView Imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.myRecyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Imagen = (ImageView) findViewById(R.id.ImagenInicio);

        if (estadoImagen==false)
            Imagen.setVisibility(View.GONE);

        //Toast.makeText(getApplicationContext(),"Busqueda " + Busqueda,Toast.LENGTH_SHORT).show();

        Presentador P = new Presentador();
        P.EnviarCadena(Busqueda, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem item = menu.findItem(R.id.buscar);
        final SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                finish();
                startActivity(getIntent());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Busqueda = s;
                estadoImagen = false;
                Favoritos.estado = false;
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Favoritos:
                Intent intent = new Intent(this,Favoritos.class);
                startActivity(intent);
                Favoritos.estado=true;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
