package com.example.nelsonsouza.citycar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ActivityGerRelUserSolic extends AppCompatActivity {

    private ListView solusers;

    private AcessoDados banco;
    private Usuario usuarioLogado;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ger_rel_user_solic);

        setTitle("Usuários mais Locadores");

        banco = new AcessoDados(this);

        Intent intent = getIntent();
        usuarioLogado = (Usuario) intent.getSerializableExtra("usuario");

        final StructSolicUsuarios ssolusers = banco.consultarSolicitacoesUsuarios();

        solusers = (ListView) findViewById(R.id.solusers);

        adapter = new AdapterSolicUsuarios(this,ssolusers);

        solusers.setAdapter((ListAdapter)adapter);

        solusers.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ActivityGerRelUserSolic.this, Activity_ger_cad_usuario.class); //intent para verificar se há recursos no aparelho
                        intent.putExtra("numClick", ssolusers.rowidUser[position]);
                        startActivity(intent);
                    }
                }
        );
    }
}
