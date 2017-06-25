package com.example.nelsonsouza.citycar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
public class Activity_user_ve_solicitacao extends AppCompatActivity {

    private AcessoDados banco;
    private Solicitacao solicitacao;

    CheckBox cboxDef;
    EditText txtVeic;
    EditText txtLocal;
    EditText txtHora;
    EditText txtMotivo;
    EditText txtModelo;
    EditText txtData;
    EditText txtObs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_ve_solicitacao);

        setTitle(this.getString(R.string.txt_minha_solic));

        Intent intent = getIntent();
        int rowid = (int) intent.getSerializableExtra("numClick");

        banco = new AcessoDados(this);

        solicitacao = banco.consultarSolicitacao(rowid);

        cboxDef = (CheckBox)findViewById(R.id.cboxDeferido);
        txtVeic = (EditText)findViewById(R.id.txtModelo);
        txtLocal = (EditText)findViewById(R.id.txtLocal);
        txtHora = (EditText)findViewById(R.id.txtHora);
        txtMotivo = (EditText)findViewById(R.id.txtMotivo);
        txtModelo = (EditText) findViewById(R.id.txtModeloVeSolicitacao);
        txtData = (EditText) findViewById(R.id.txtDataVeSolicitacao);
        txtObs = (EditText) findViewById(R.id.txtObsVeSolicitacao);

        cboxDef.setChecked(solicitacao.isDeferido());
        txtVeic.setText(solicitacao.getPlacaVeiculo()); //modelo
        txtLocal.setText(solicitacao.getLocalRetirada());
        txtHora.setText(solicitacao.getHoraRetirada());
        txtMotivo.setText(solicitacao.getMotivo());
        txtModelo.setText(solicitacao.getModeloVeiculo());
        txtData.setText((solicitacao.getDataDevolucao()));
        txtObs.setText(solicitacao.getObs());

    }
}