package com.example.nelsonsouza.citycar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_ger_cad_usuario extends AppCompatActivity {

    private int cpf;
    private EditText campo;
    private EditText campo1;
    private EditText campo2;
    private EditText campo3;
    private EditText campo4;
    private Button btnGravar;
    private Button btnExcluir;
    private Button btnAlterar;

    private Usuario usuario;
    private AcessoDados conecta;
    // flag para verificar se ja é cadastrado !!!!
    int flag = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ger_cad_usuario);
        setTitle("CityCar - Cadastro de Usuarios");
        conecta = new AcessoDados(this);
        campo = (EditText) findViewById(R.id.txtLCPF);
        campo1 = (EditText) findViewById(R.id.txtNome);
        campo2 = (EditText) findViewById(R.id.txtSetor);
        campo3 = (EditText) findViewById(R.id.txtTelefone);
        campo4 = (EditText) findViewById(R.id.txtSenha);
        btnGravar = (Button) findViewById(R.id.btnGravaUsuario);
        btnAlterar = (Button) findViewById(R.id.btnALTERAR);
        btnExcluir = (Button) findViewById(R.id.btnEXCLUIR);
        btnGravar.setEnabled(true);
        btnAlterar.setEnabled(false);
        btnExcluir.setEnabled(false);

        try{
            Intent intent = getIntent();
            int rowid = (int) intent.getSerializableExtra("numClick");

            usuario = conecta.consultarUsuario(rowid,2);
            if (usuario == null){
                Toast mensagem = Toast.makeText(this,
                        "Usuario não encontrado !", Toast.LENGTH_SHORT);
                mensagem.show();
                limpaCampos();
            }else{
                campo.setText(String.valueOf(usuario.getCpf()));
                campo1.setText(usuario.getNome());
                campo2.setText(usuario.getSetor());
                campo3.setText(usuario.getTelefone());
                campo4.setText(usuario.getSenha());

                btnAlterar.setVisibility(View.INVISIBLE);
                btnExcluir.setVisibility(View.INVISIBLE);
                btnGravar.setVisibility(View.INVISIBLE);
                Button btnPesquisar = (Button) findViewById(R.id.btnPesquisar);
                btnPesquisar.setVisibility(View.INVISIBLE);
                
                //android:editable="false"
                //android:inputType="none"

                EditText[] ae = new EditText[]{campo,campo1,campo2,campo3,campo4};

                for(EditText e : ae){
                    e.setClickable(false);
                    e.setInputType(InputType.TYPE_NULL);
                    //e.setFocusable(false);
                }
            }
        }catch(Exception e){
            Log.d("t",e.getMessage());
        }

    }

    public void btnPesquisar(View v){
        if (campo.getText().length()<=0){
            Toast mensagem = Toast.makeText(this,
                    "Necessário Digitar um CPF", Toast.LENGTH_SHORT);
            mensagem.show();

        }else{
            carregarUsuario();
        }

    }

    public void limpaCampos(){
        campo.setText("");
        campo1.setText("");
        campo2.setText("");
        campo3.setText("");
        campo4.setText("");
    }

    public void btnGravarUsuario(View v){
        if (campo.getText().length()<=0){
            Toast mensagem = Toast.makeText(this,
                    "Necessário Digitar um CPF", Toast.LENGTH_SHORT);
            mensagem.show();
        }else{
            usuario = new Usuario();
            cpf = Integer.parseInt(campo.getText().toString());
            usuario.setCpf(cpf);
            usuario.setNome(campo1.getText().toString());
            usuario.setSetor(campo2.getText().toString());
            usuario.setTelefone(campo3.getText().toString());
            usuario.setSenha(campo4.getText().toString());

            conecta.inserirUsuario(usuario);
            Toast mensagem = Toast.makeText(this,
                    "Cadastrado..."+conecta.getNumeroRegistroUsuario(), Toast.LENGTH_SHORT);
            mensagem.show();
            limpaCampos();
        }
    }

    public void btnAlterarUsuario(View view){
        usuario.setNome(campo1.getText().toString());
        usuario.setSetor(campo2.getText().toString());
        usuario.setTelefone(campo3.getText().toString());
        usuario.setSenha(campo4.getText().toString());
        cpf = Integer.parseInt(campo.getText().toString());
        conecta.alteraUsuario(cpf,usuario);

        Toast mensagem = Toast.makeText(this,"Alteração Efetuada!", Toast.LENGTH_LONG);
        mensagem.show();
        limpaCampos();
    }

    public void btnExcluirUsuario(View view){
        cpf = Integer.parseInt(campo.getText().toString());
        conecta.excluiUsuario(cpf,usuario);
        Toast mensagem = Toast.makeText(this,"EXCLUIDO !!", Toast.LENGTH_LONG);
        mensagem.show();
        limpaCampos();
    }

    public void carregarUsuario(){
        cpf = Integer.parseInt(campo.getText().toString());
        usuario = conecta.consultarUsuario(cpf);
        if (usuario!= null){
            campo1.setText(usuario.getNome());
            campo2.setText(usuario.getSetor());
            campo3.setText(usuario.getTelefone());
            campo4.setText(usuario.getSenha());
            btnAlterar.setEnabled(true);
            btnExcluir.setEnabled(true);
            btnGravar.setEnabled(false);
        }else{
            btnGravar.setEnabled(true);
            btnAlterar.setEnabled(false);
            btnExcluir.setEnabled(false);
            Toast mensagem = Toast.makeText(this,
                    "Usuario não encontrado !", Toast.LENGTH_SHORT);
            mensagem.show();
            limpaCampos();

        }
    }
}