package com.example.ejerciciotecnico.Vista;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.ejerciciotecnico.Presentador.Presentador;
import com.example.ejerciciotecnico.R;


public class Favoritos extends AppCompatActivity {

    public static RecyclerView mRecyclerView;
    public static boolean estado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        mRecyclerView = findViewById(R.id.F_myRecyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Presentador presentador = new Presentador();
        presentador.SolicitarRegistros(this);

    }
}
