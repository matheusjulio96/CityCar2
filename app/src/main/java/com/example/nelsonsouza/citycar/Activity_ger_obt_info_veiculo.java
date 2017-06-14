package com.example.nelsonsouza.citycar;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
        txtModelo = (EditText) findViewById(R.id.txtModelo);
        txtCombustivel = (EditText) findViewById(R.id.txtCombustivel);
        txtChassi = (EditText) findViewById(R.id.txtChassi);
        txtSituacaoAtual = (EditText) findViewById(R.id.txtSituacaoAtual);

    }

    EditText txtMarca;
    EditText txtAno;
    EditText txtKmRodado;
    EditText txtModelo;
    EditText txtCombustivel;
    EditText txtChassi;
    EditText txtSituacaoAtual;

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
