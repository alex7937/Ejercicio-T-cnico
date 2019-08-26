package com.example.ejerciciotecnico.Interfaces;

import android.content.Context;
import android.view.View;

public interface BasedeDatos {
    interface CRUD{
        void Registrar (View view, Context context, String ID, String Nombre, String Cancion, String Album, String Urlimagen, String Urlaudio);
        void CargarRegistros(Context context);
        void EliminarRegistro(View view, Context context, String ID);
    }

    interface Conectar{
        void EnviarRegistros(View view, Context context, String ID, String Nombre, String Cancion, String Album, String Urlimagen, String Urlaudio);
        void SolicitarRegistros(Context context);
        void SolicitarEliminar(View view, Context context, String ID);
    }
}
