package com.example.nelsonsouza.citycar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_DetalheReceberVeiculo extends AppCompatActivity {

    private AprovaSolicitacao solicitante; // vai para veiculo

    TextView campo1;
    TextView campo2;
    TextView campo3;
    TextView campo4;
    TextView campo5;
    TextView campo6;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__detalhe_receber_veiculo);
        setTitle("CityCar - RECEBIMENTO DE VEICULO");

        Intent intent = getIntent();
        solicitante = (AprovaSolicitacao) intent.getSerializableExtra("usuario");

        campo1 = (TextView) findViewById(R.id.txtPlacaReceberVeiculo);
        campo2 = (TextView) findViewById(R.id.txtModeloReceberVeiculo);
        campo3 = (TextView) findViewById(R.id.txtUsuarioReceberVeiculo);
        campo4 = (TextView) findViewById(R.id.txtCpfReceberVeiculo);
        //campo5 = (TextView) findViewById(R.id);
        campo6 = (TextView) findViewById(R.id.txtObsReceberVeiculo);

        campo1.setText(solicitante.getPlaca());
        campo2.setText(solicitante.getModelo());
        campo3.setText(solicitante.getNome());
        campo4.setText(solicitante.getCpf());
        campo1.setEnabled(false);
        campo2.setEnabled(false);
        campo3.setEnabled(false);
        campo4.setEnabled(false);

    }

    public void recebeVeiculo(View view){
        AcessoDados conecta = new AcessoDados(this);

        conecta.recebeVeiculo(solicitante);
        Toast.makeText(getBaseContext(),"VEICULO foi liberado!",Toast.LENGTH_LONG).show();
        finish();
    }




}
