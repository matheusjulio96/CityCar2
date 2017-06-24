package com.example.nelsonsouza.citycar;

/**
 * Created by matheus on 24/06/17.
 */

public class StructSolicVeiculos {
    public int[] rowidVeic;
    public String[] modelo;
    public String[] ano;
    public int[] numSol;

    public StructSolicVeiculos(int Tam) {
        this.rowidVeic = new int[Tam];
        this.modelo = new String[Tam];
        this.ano = new String[Tam];
        this.numSol = new int[Tam];
    }
}