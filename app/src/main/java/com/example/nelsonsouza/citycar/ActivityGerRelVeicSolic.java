package com.example.nelsonsouza.citycar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ActivityGerRelVeicSolic extends AppCompatActivity {

    private ListView veiculos;

    private AcessoDados banco;
    //private Usuario usuarioLogado;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ger_rel_veic_solic);

        setTitle("Veículos mais Locados");

        banco = new AcessoDados(this);

        //Intent intent = getIntent();
        //usuarioLogado = (Usuario) intent.getSerializableExtra("usuario");

        final StructSolicVeiculos svs = banco.consultarQtdLocsVeiculos();

        veiculos = (ListView) findViewById(R.id.veiculos);

        adapter = new AdapterSolicVeiculos(this,svs);

        veiculos.setAdapter((ListAdapter)adapter);

        veiculos.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ActivityGerRelVeicSolic.this, Activity_ger_obt_info_veiculo.class); //intent para verificar se há recursos no aparelho
                        intent.putExtra("numClick", svs.rowidVeic[position]);
                        startActivity(intent);
                    }
                }
        );
    }
}
