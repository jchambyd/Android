package com.jorgecd.departamentoslist;

public class Departamento
{
    public String nombre;
    public String capital;
    public String link;
    public String ruta;

    public Departamento()
        {
            // TODO Auto-generated constructor stub
        }

    public Departamento(String nombre, String capital, String link, String rutaImagen)
        {
            this.nombre = nombre;
            this.capital = capital;
            this.link = link;
            this.ruta = rutaImagen;
        }

    @Override
    public String toString()
        {
            return this.nombre;
        }
    
}