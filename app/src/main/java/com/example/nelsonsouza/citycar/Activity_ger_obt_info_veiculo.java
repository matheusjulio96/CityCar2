package com.example.nelsonsouza.citycar;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_ger_obt_info_veiculo extends AppCompatActivity {

    private AcessoDados banco;
    private Veiculo veiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ger_obt_info_veiculo);
        setTitle("CityCar - Informações do Veiculo");

        txtMarca = (EditText) findViewById(R.id.txtMarca);
        txtAno = (EditText) findViewById(R.id.txtAno);
        txtKmRodado = (EditText) findViewById(R.id.txtKmRodado);
        txtModelo = (EditText) findViewById(R.id.txtModeloRod);
        txtCombustivel = (EditText) findViewById(R.id.txtCombustivel);
        txtChassi = (EditText) findViewById(R.id.txtChassi);
        txtSituacaoAtual = (EditText) findViewById(R.id.txtSituacaoAtual);

        try{
            Intent intent = getIntent();
            int rowid = (int) intent.getSerializableExtra("numClick");

            banco = new AcessoDados(this);

            veiculo = banco.consultaVeiculo(rowid);

            if(veiculo==null){
                Toast.makeText(this, "Veículo não encontrado.", Toast.LENGTH_LONG).show();
            }else{
                txtAno.setText(veiculo.getAno());
                txtModelo.setText(veiculo.getModelo());
                txtCombustivel.setText(veiculo.getCombustivel());
                txtChassi.setText(veiculo.getChassi());
                txtMarca.setText(veiculo.getMarca());
                txtKmRodado.setText(String.valueOf(veiculo.getKmRodado()));
                txtSituacaoAtual.setText(veiculo.isLocado()?"Locado":"Livre");

                //visible false nos outros componentes
                lblInformePlaca = (TextView) findViewById(R.id.lblInformePlaca);
                lblInformePlaca.setVisibility(View.INVISIBLE);
                txtPlaca = (EditText) findViewById(R.id.txtPlaca);
                txtPlaca.setVisibility(View.INVISIBLE);
                consultar = (Button) findViewById(R.id.consultar);
                consultar.setVisibility(View.INVISIBLE);
            }
        }catch(Exception e){
            Log.d("t",e.getMessage());
        }
    }

    EditText txtMarca;
    EditText txtAno;
    EditText txtKmRodado;
    EditText txtModelo;
    EditText txtCombustivel;
    EditText txtChassi;
    EditText txtSituacaoAtual;

    TextView lblInformePlaca;
    EditText txtPlaca;
    Button consultar;

    public void consultarClicked(View v){
        try{
            banco = new AcessoDados(this);
            veiculo = banco.consultaVeiculo(((EditText) findViewById(R.id.txtPlaca)).getText().toString());

            if(veiculo==null){
                Toast.makeText(this, "Veículo não encontrado.", Toast.LENGTH_LONG).show();
            }else{
                txtAno.setText(veiculo.getAno());
                txtModelo.setText(veiculo.getModelo());
                txtCombustivel.setText(veiculo.getCombustivel());
                txtChassi.setText(veiculo.getChassi());
                txtMarca.setText(veiculo.getMarca());
                txtKmRodado.setText(String.valueOf(veiculo.getKmRodado()));
                txtSituacaoAtual.setText(veiculo.isLocado()?"Locado":"Livre");
            }

        }catch (Exception e){
            Toast mensagem = Toast.makeText(this,
                    e.getMessage(), Toast.LENGTH_LONG);
            mensagem.show();
        }
    }


}
