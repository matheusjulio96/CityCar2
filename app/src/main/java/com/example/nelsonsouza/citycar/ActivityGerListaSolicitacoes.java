package com.example.nelsonsouza.citycar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActivityGerListaSolicitacoes extends AppCompatActivity {
    private ListView locacoes;

    private AcessoDados banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_ve_locacoes);

        setTitle("Locações");

        banco = new AcessoDados(this);

        //String[] itens = {"Fusca Azul - 03/05/17","Fusca Preto - 04/05/17"};//solicitacoes
        final StructSolicitacoes ss = banco.consultarSolicitacoes(-1);//solicitacoes
        /*String[] locs = new String[dados.length/3];
        for(int i=0; i<locs.length; i++){
            locs[i] = dados[i*3] + " - " + dados[i*3+1] + " - " + dados[i*3+2];
        }*/

        locacoes = (ListView) findViewById(R.id.locacoes);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1, ss.descricao);
        locacoes.setAdapter(adaptador);

        locacoes.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ActivityGerListaSolicitacoes.this, Activity_user_ve_solicitacao.class); //intent para verificar se há recursos no aparelho
                        intent.putExtra("numClick", ss.rowid[position]);
                        startActivity(intent);
                    }
                }
        );
    }
}
