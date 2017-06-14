package com.example.nelsonsouza.citycar;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Activity_ReceberVeiculo extends AppCompatActivity {

    Cursor cursor;
    ListView lstDados;
    private Adapter adapter;
    AcessoDados conecta ;
    ArrayList<AprovaSolicitacao> listaSolicitacoes = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__receber_veiculo);

        setTitle("CityCar -Veiculos Locados");

        conecta = new AcessoDados(this);
        ArrayList<AprovaSolicitacao>listaSolicitacoes;
        carregaDados();

    }

    public void carregaDados() {
        conecta.listaVeiculosReceber(listaSolicitacoes);
        if (listaSolicitacoes != null) {
            adapter = new Adapter_ReceberVeiculos(this, listaSolicitacoes); // uso o modelo de adapter que criei...
            lstDados = (ListView) findViewById(R.id.lstVeiculosReceber);
            lstDados.setAdapter((ListAdapter) adapter);

            lstDados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    /*AprovaSolicitacao aprovar = (AprovaSolicitacao) adapter.getItem(position);
                    Toast.makeText(getBaseContext(),"Rsetor.  = "+ aprovar.getSetor(),Toast.LENGTH_LONG).show(); */

                    AprovaSolicitacao receber = (AprovaSolicitacao) adapter.getItem(position);
                    Intent intent = new Intent(Activity_ReceberVeiculo.this, Activity_DetalheReceberVeiculo.class);
                    intent.putExtra("usuario", receber);
                    startActivity(intent);
                    finish();

                }
            });

        }
    }

}
