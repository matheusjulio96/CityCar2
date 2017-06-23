package com.example.nelsonsouza.citycar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ActivityGerRelVeicRodado extends AppCompatActivity {

    private ListView veiculos;

    private AcessoDados banco;
    private Usuario usuarioLogado;

    //private AcessoDados conecta;
    //ArrayList<Veiculo> listaVeiculos = new ArrayList<>();
    private Adapter adapter;
    ListView lstVeiculos;
    AprovaSolicitacao solicitante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ger_rel_veic_rodado);

        setTitle("Veículos mais Rodados");

        banco = new AcessoDados(this);

        Intent intent = getIntent();
        usuarioLogado = (Usuario) intent.getSerializableExtra("usuario");

        final StructVeiculos sv = banco.consultarVeiculos();

        //sv KILOMETRAGEM

        veiculos = (ListView) findViewById(R.id.veiculos);

        adapter = new AdapterVeiculosRodados(this,sv);

        veiculos.setAdapter((ListAdapter)adapter);

        veiculos.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ActivityGerRelVeicRodado.this, Activity_ger_obt_info_veiculo.class); //intent para verificar se há recursos no aparelho
                        intent.putExtra("numClick", sv.rowid[position]);
                        startActivity(intent);
                    }
                }
        );
    }
}
