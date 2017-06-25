package com.example.nelsonsouza.citycar;

/**
 * Created by Nelson Souza on 25/05/2017.
 */

public class Solicitacao {
    private int cpfUsuario;
    private String motivo;
    private int periodo;
    private boolean dias;
    private boolean horas;
    private String horaIdeal;

    private boolean deferido = false;
    private String placaVeiculo;
    private String localRetirada;
    private String modeloVeiculo;
    private String horaRetirada;
    private String dataDevolucao;
    private String obs;


    public Solicitacao() {
    }

    public Solicitacao(int cpfUsuario, String motivo, int periodo, boolean dias, boolean horas, String horaIdeal) {
        this.cpfUsuario = cpfUsuario;
        this.motivo = motivo;
        this.periodo = periodo;
        this.dias = dias;
        this.horas = horas;
        this.horaIdeal = horaIdeal;
    }

    public int getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(int codUsuario) {
        this.cpfUsuario = codUsuario;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public boolean isDias() {
        return dias;
    }

    public void setDias(boolean dias) {
        this.dias = dias;
    }

    public boolean isHoras() {
        return horas;
    }

    public void setHoras(boolean horas) {
        this.horas = horas;
    }

    public String getHoraIdeal() {
        return horaIdeal;
    }

    public void setHoraIdeal(String horaIdeal) {
        this.horaIdeal = horaIdeal;
    }

    public boolean isDeferido() {
        return deferido;
    }

    public void setDeferido(boolean deferido) {
        this.deferido = deferido;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public String getLocalRetirada() {
        return localRetirada;
    }

    public void setLocalRetirada(String localRetirada) {
        this.localRetirada = localRetirada;
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public void setModeloVeiculo(String modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }

    public String getHoraRetirada() {
        return horaRetirada;
    }

    public void setHoraRetirada(String horaRetirada) {
        this.horaRetirada = horaRetirada;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
