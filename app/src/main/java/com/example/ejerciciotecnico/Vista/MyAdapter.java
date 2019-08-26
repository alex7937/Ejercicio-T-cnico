package com.example.ejerciciotecnico.Vista;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ejerciciotecnico.Modelo.HeaderDatosBusqueda;
import com.example.ejerciciotecnico.Presentador.Presentador;
import com.example.ejerciciotecnico.R;

import java.io.IOException;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<HeaderDatosBusqueda> mDataset;


    public static Context context;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView Nombre;
        TextView Cancion;
        TextView Album;
        Button Audio;
        Button Favorito;
        //ImageButton
        ImageButton Botonlibro;
        //Context


        public MyViewHolder(View v) {
            super(v);
            context = v.getContext();

            Nombre = (TextView) v.findViewById(R.id.B_Nombre);
            Cancion = (TextView) v.findViewById(R.id.B_Cancion);
            Album = (TextView) v.findViewById(R.id.B_Album);
            Botonlibro = (ImageButton) v.findViewById(R.id.B_IB1);
            Audio = (Button) v.findViewById(R.id.B_Audio);
            Favorito = (Button) v.findViewById(R.id.B_Favorito);
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<HeaderDatosBusqueda> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_busqueda, parent, false);

        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.Nombre.setText(mDataset.get(position).getNombre());
        holder.Cancion.setText(mDataset.get(position).getCancion());
        holder.Album.setText(mDataset.get(position).getAlbum());

        //Carga de imagen
        String urlImagenNoDisponible = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/Imagen_no_disponible.svg/1024px-Imagen_no_disponible.svg.png";

        int i;
        for (i=0 ; i <mDataset.get(position).getUrl().length(); i++){
        }
        if (i==4 || i==0){
            Glide.with(holder.Botonlibro.getContext()).load(urlImagenNoDisponible).into(holder.Botonlibro);
        }else {
            Glide.with(holder.Botonlibro.getContext()).load(mDataset.get(position).getUrl()).into(holder.Botonlibro);
        }


        // Evento botón Audio
        boolean estado = true;

        final MediaPlayer mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mp.setDataSource(mDataset.get(position).getAudio());
            mp.prepare();

        } catch (IOException e) {
        }

        holder.Audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Hola soy un Toast", Toast.LENGTH_SHORT).show();
                if (mp.isPlaying()){
                    mp.pause();
                    holder.Audio.setText("Reproducir");
                    //Toast.makeText(context, "Musica pausada", Toast.LENGTH_SHORT).show();
                }else {
                    mp.start();
                    holder.Audio.setText("Pausar");
                    //Toast.makeText(context, "Reproduciendo", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Evento botón Favoritos
        if (Favoritos.estado==true){
            holder.Favorito.setText("Eliminar");
        }else {
            holder.Favorito.setText("Favoritos");
        }

        if(holder.Favorito.getText().equals("Favoritos")) {
            holder.Favorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(context, "Canción añadida a Favoritos", Toast.LENGTH_SHORT).show();
                    Presentador presentador = new Presentador();
                    presentador.EnviarRegistros(view, context,
                                                mDataset.get(position).getId(),
                                                mDataset.get(position).getNombre(),
                                                mDataset.get(position).getCancion(),
                                                mDataset.get(position).getAlbum(),
                                                mDataset.get(position).getUrl(),
                                                mDataset.get(position).getAudio());
                }
            });
        }else if (holder.Favorito.getText().equals("Eliminar")){
            holder.Favorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(context, "Eliminada", Toast.LENGTH_SHORT).show();
                    Presentador presentador = new Presentador();
                    presentador.SolicitarEliminar(view, context, mDataset.get(position).getId());
                }
            });
        }
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
