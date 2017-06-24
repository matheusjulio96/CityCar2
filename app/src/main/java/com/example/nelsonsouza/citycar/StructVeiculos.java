package com.example.nelsonsouza.citycar;

/**
 * Created by matheus on 22/06/17.
 */

public class StructVeiculos {
    public int[] rowid;
    public String[] modelo;
    public String[] ano;
    public int[] km;

    public StructVeiculos(int Tam) {
        this.rowid = new int[Tam];
        this.modelo = new String[Tam];
        this.ano = new String[Tam];
        this.km = new int[Tam];
    }
}
