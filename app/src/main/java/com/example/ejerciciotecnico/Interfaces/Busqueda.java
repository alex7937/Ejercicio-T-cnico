package com.example.ejerciciotecnico.Interfaces;

import android.content.Context;

public interface Busqueda {
    interface IniciarBusqueda{
        void EnviarCadena(String cadena, Context context);
    }
    interface Buscar{
        void BuscarArtista(String cadena, Context context);
    }
}

