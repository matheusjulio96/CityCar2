package com.example.nelsonsouza.citycar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ActivityGerRelMenu extends AppCompatActivity {

    private ListView opcoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ger_rel_menu);

        String[] itens = {
                "Veículos mais Rodados",
                "Usuários mais Locadores",
                "Veículos mais Locados"};

        opcoes = (ListView) findViewById(R.id.opcoes);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1, itens);
        opcoes.setAdapter(adaptador);

        final Class[] telas = {
                ActivityGerRelVeicRodado.class,
                ActivityGerRelUserSolic.class,
                ActivityGerRelVeicSolic.class};

        opcoes.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ActivityGerRelMenu.this, telas[position]); //intent para verificar se há recursos no aparelho

                        startActivity(intent);
                    }
                }
        );
    }
}
