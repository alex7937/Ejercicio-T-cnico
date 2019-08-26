package com.example.ejerciciotecnico.Modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.ejerciciotecnico.Interfaces.BasedeDatos;
import com.example.ejerciciotecnico.Interfaces.Busqueda;
import com.example.ejerciciotecnico.Vista.Favoritos;
import com.example.ejerciciotecnico.Vista.MainActivity;
import com.example.ejerciciotecnico.Vista.MyAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Interactor implements Busqueda.Buscar, BasedeDatos.CRUD {

    RequestQueue rq;
    JsonRequest jrq;

    @Override
    public void BuscarArtista(String cadena, final Context context) {
        //Toast.makeText(context, "Interactor "+ cadena, Toast.LENGTH_SHORT).show();

        rq = Volley.newRequestQueue(context);

        String servidor = "https://itunes.apple.com/search?term="+cadena;

        jrq = new JsonObjectRequest(Request.Method.GET, servidor, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Toast.makeText(getApplicationContext(),"Conexion exitosa "+ response,Toast.LENGTH_SHORT).show();
                MyAdapter mAdapter;
                ArrayList<HeaderDatosBusqueda> myDataset = new ArrayList<>();

                JSONArray jsonArray = response.optJSONArray("results");
                JSONObject jsonObject = null;

                if (response.optString("resultCount").equals("0")){
                    Toast.makeText(context,"No hay resultados ",Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        for (int j=0 ; j <jsonArray.length(); j++){
                            jsonObject = jsonArray.getJSONObject(j);
                            //Toast.makeText(getApplicationContext(),"Canción: "+ jsonObject.optString("trackName"),Toast.LENGTH_SHORT).show();

                            myDataset.add(new HeaderDatosBusqueda(
                                    "Artista: " + jsonObject.optString("artistName"),
                                    "Canción: " + jsonObject.optString("trackName"),
                                    "Album: " + jsonObject.optString("collectionName"),
                                    jsonObject.optString("artworkUrl100"),
                                    jsonObject.optString("previewUrl"),
                                    jsonObject.optString("trackId")));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mAdapter = new MyAdapter(myDataset);
                MainActivity.mRecyclerView.setAdapter(mAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Error ",Toast.LENGTH_SHORT).show();
            }
        });

        rq.add(jrq);

    }

    @Override
    public void Registrar(View view, Context context, String ID, String Nombre, String Cancion, String Album, String Urlimagen, String Urlaudio) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("id", ID);
        registro.put("nombre", Nombre);
        registro.put("cancion", Cancion);
        registro.put("album", Album);
        registro.put("urlimagen", Urlimagen);
        registro.put("urlaudio", Urlaudio);

        BaseDeDatos.insert("favoritos", null, registro);
        Toast.makeText(context, "Canción añadida a Favoritos" + Cancion, Toast.LENGTH_SHORT).show();
        BaseDeDatos.close();
    }

    @Override
    public void CargarRegistros(Context context) {
        //Toast.makeText(context, "Cargando registros", Toast.LENGTH_SHORT).show();
        MyAdapter mAdapter;
        ArrayList<HeaderDatosBusqueda> myDataset = new ArrayList<>();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Cursor cursor = BaseDeDatos.rawQuery("SELECT * FROM favoritos", null);

        if (cursor.moveToFirst()){
            do {
                myDataset.add(new HeaderDatosBusqueda(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(0)));
            }while (cursor.moveToNext());
        }else {
            Toast.makeText(context, "No hay canciones favoritas", Toast.LENGTH_SHORT).show();
        }

        mAdapter = new MyAdapter(myDataset);
        Favoritos.mRecyclerView.setAdapter(mAdapter);
        BaseDeDatos.close();
    }

    @Override
    public void EliminarRegistro(View view, Context context, String ID) {
        //Toast.makeText(context, "Canción eliminada ", Toast.LENGTH_SHORT).show();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        int cantidad = BaseDeDatos.delete("favoritos", "id=" + ID, null);
        BaseDeDatos.close();

        if (cantidad==1){
            Toast.makeText(context, "Canción eliminada", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Esta canción ya no existe ", Toast.LENGTH_SHORT).show();
        }
    }
}
