package com.example.ejerciciotecnico.Modelo;

public class HeaderDatosBusqueda {

    private String Nombre;
    private String Cancion;
    private String Album;
    private String Url;
    private String Audio;
    private String Id;

    public HeaderDatosBusqueda(String nombre, String cancion, String album, String url, String audio, String id) {
        Nombre = nombre;
        Cancion = cancion;
        Album = album;
        Url = url;
        Audio = audio;
        Id = id;
    }



    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCancion() {
        return Cancion;
    }

    public void setCancion(String cancion) {
        Cancion = cancion;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getAudio() {
        return Audio;
    }

    public void setAudio(String audio) {
        Audio = audio;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }




}
