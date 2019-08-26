package com.example.ejerciciotecnico.Presentador;

import android.content.Context;
import android.view.View;
import android.widget.Toast;
import com.example.ejerciciotecnico.Interfaces.BasedeDatos;
import com.example.ejerciciotecnico.Interfaces.Busqueda;
import com.example.ejerciciotecnico.Modelo.Interactor;
import com.example.ejerciciotecnico.Vista.MainActivity;


public class Presentador implements Busqueda.IniciarBusqueda, BasedeDatos.Conectar {

    @Override
    public void EnviarCadena(String cadena, Context context) {
        Interactor I = new Interactor();
        I.BuscarArtista(cadena,context);
        //Toast.makeText(context, "Hola soy un Toast"+ cadena, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void EnviarRegistros(View view, Context context, String ID, String Nombre, String Cancion, String Album, String Urlimagen, String Urlaudio) {
        //Toast.makeText(context, "Canción añadida a Favoritos", Toast.LENGTH_SHORT).show();
        Interactor Interactor = new Interactor();
        Interactor.Registrar(view,context, ID, Nombre, Cancion, Album, Urlimagen, Urlaudio);
    }

    @Override
    public void SolicitarRegistros(Context context) {
        //Toast.makeText(context, "Solicitando registros", Toast.LENGTH_SHORT).show();
        Interactor interactor = new Interactor();
        interactor.CargarRegistros(context);
    }

    @Override
    public void SolicitarEliminar(View view, Context context, String ID) {
        //Toast.makeText(context, "Eliminada" + ID, Toast.LENGTH_SHORT).show();
        Interactor interactor = new Interactor();
        interactor.EliminarRegistro(view,context,ID);
    }


}
