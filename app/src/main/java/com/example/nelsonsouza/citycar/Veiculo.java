package com.example.nelsonsouza.citycar;

/**
 * Created by Nelson Souza on 25/05/2017.
 */

public class Veiculo {
    private String marca;
    private String modelo;
    private String ano;
    private String combustivel;
    private String chassi;
    private String placa;
    private int id;
    private boolean locado;

    public int getKmRodado() {
        return kmRodado;
    }

    public void setKmRodado(int kmRodado) {
        this.kmRodado = kmRodado;
    }

    private int kmRodado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Veiculo(String marca, String modelo, String ano, String combustivel, String chassi, String placa) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;

        this.combustivel = combustivel;
        this.chassi = chassi;
        this.placa = placa;
    }

    public Veiculo() {
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public boolean isLocado() {
        return locado;
    }

    public void setLocado(boolean locado) {
        this.locado = locado;
    }
}
