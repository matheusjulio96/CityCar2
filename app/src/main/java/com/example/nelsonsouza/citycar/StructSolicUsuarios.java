package com.example.nelsonsouza.citycar;

/**
 * Created by matheus on 23/06/17.
 */

public class StructSolicUsuarios {
    public int[] rowidUser;
    public String[] nome;
    public int[] numLocs;

    public StructSolicUsuarios(int Tam) {
        this.rowidUser = new int[Tam];
        this.nome = new String[Tam];
        this.numLocs = new int[Tam];
    }
}
