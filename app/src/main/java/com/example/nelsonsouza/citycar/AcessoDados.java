package com.example.nelsonsouza.citycar;

/**
 * Created by Nelson Souza on 25/05/2017.
 */



        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.SQLException;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;
        import android.widget.ListView;
        import android.widget.Toast;

        import java.lang.reflect.Array;
        import java.util.ArrayList;
        import java.util.List;

public class AcessoDados extends SQLiteOpenHelper {

    public AcessoDados(Context context) {
        super(context/**/, "citycarbd"/*nome banco*/, null, 1);
        //Apagar o banco quando for necessário alguma
        //alteração na estrutura
        //context.deleteDatabase("citycarbd");//delete banco de dados
    }

    @Override
    public void onCreate(SQLiteDatabase banco) {//todas as tabelas vão ser criadas através do método onCreate
        String
                DDL = "CREATE TABLE veiculo(placa TEXT PRIMARY KEY, marca TEXT, modelo TEXT," +
                "ano TEXT, combustivel TEXT,chassi TEXT, status BOOLEAN, manutencao BOOLEAN,kmrodado int)";

        banco.execSQL(DDL);

        DDL = "CREATE TABLE usuario(cpf INTEGER PRIMARY KEY, nome TEXT,"+
                "setor TEXT, telefone TEXT, senha TEXT)";
        banco.execSQL(DDL);

        //nao precisa de autoincrement pq sqlite cria rowid automaticamente
        DDL = "CREATE TABLE solicitacao(cpf_usuario INTEGER references usuario, motivo TEXT, periodo INTEGER," +
                "p_dias BOOLEAN, p_horas BOOLEAN, hora_ideal TEXT, deferido BOOLEAN, localRetirada TEXT,horaRetirada TEXT,"+
                "kmRetirada ,placa_veic TEXT references veiculo, status BOOLEAN, modelo TEXT, finalizada BOOLEAN)";
        // OBS FINALIZADA = qdo entrega o veiculo aqui passa para 1 quer dizer que encerrou....
        // status = default é zero e quando foi feito algo pelo gerente passa para 1.
        // deferido = qdo gerente aprova passa para 1 o default é 0
        banco.execSQL(DDL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase banco, int oldVersion, int newVersion) {//os drops,alter vão ser feitos através do metodo onUPgrade
        banco.execSQL("DROP TABLE IF EXISTS solicitacao");
    }

    /*
    * VEICULO
    * */

    public void insertVeiculo(Veiculo veiculo) {//método insert
        SQLiteDatabase banco = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("placa", veiculo.getPlaca());
        valores.put("marca", veiculo.getMarca());
        valores.put("modelo", veiculo.getModelo());
        valores.put("ano", veiculo.getAno());
        valores.put("combustivel", veiculo.getCombustivel());
        valores.put("chassi", veiculo.getChassi());
        valores.put("kmrodado", veiculo.getKmRodado());
        valores.put("status",0);
        banco.insert("veiculo", null, valores);
        banco.close();
    }

    public Veiculo consultaVeiculo(String placa) { //método consulta
        try{
            SQLiteDatabase banco = this.getReadableDatabase();

            Cursor campo = banco.query("veiculo", new String[]{
                    "marca", "modelo", "ano", "combustivel", "chassi", "kmrodado","status"
            }, "placa = '" + placa+"'", null, null, null, null, null);
            if (campo != null)
                campo.moveToFirst();
            Veiculo veiculo = new Veiculo();
            veiculo.setPlaca(placa);
            veiculo.setMarca(campo.getString(0));
            veiculo.setModelo(campo.getString(1));
            veiculo.setAno(campo.getString(2));
            veiculo.setCombustivel(campo.getString(3));
            veiculo.setChassi(campo.getString(4));
            veiculo.setKmRodado(campo.getInt(5));
            veiculo.setLocado(campo.getInt(6)==1?true:false);
            campo.close();
            return veiculo;
        }catch(Exception e){
            Veiculo veiculo = null;
            return veiculo;
        }
    }
    //update veiculo

    public int getNumeroRegistroVeiculo(){
        String consulta = "SELECT * FROM veiculo";
        SQLiteDatabase banco = this.getReadableDatabase();
        Cursor campo = banco.rawQuery(consulta, null);
        int numeroRegistros = campo.getCount();
        campo.close();
        return numeroRegistros;
    }
    /*
    * USUARIO
    * */

    public void inserirUsuario(Usuario usuario){
        SQLiteDatabase banco = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("cpf",usuario.getCpf());
        valores.put("nome",usuario.getNome());
        valores.put("setor",usuario.getSetor());
        valores.put("telefone",usuario.getTelefone());
        valores.put("senha",usuario.getSenha());
        banco.insert("usuario",null, valores);
        banco.close();
    }

    public Usuario consultarUsuario(int cpf){
        Usuario usuario = null;
        try{
            SQLiteDatabase banco = this.getReadableDatabase();
            Cursor campo = banco.query("usuario", new String[] {
                            "cpf", "nome", "setor", "Telefone", "Senha"}
                    , "cpf = " + cpf, null, null, null, null, null);

            if (campo != null) {
                usuario = new Usuario();
                campo.moveToFirst();
                usuario.setCpf(cpf);
                usuario.setNome(campo.getString(1));
                usuario.setSetor(campo.getString(2));
                usuario.setTelefone(campo.getString(3));
                usuario.setSenha(campo.getString(4));
                campo.close();
                return usuario;
            }
            return usuario;
        }catch (Exception e){
            return null;
        }
    }
    // altera o usuario selecionado...
    public void alteraUsuario(int cpf, Usuario usuario){
        SQLiteDatabase banco = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nome",usuario.getNome());
        valores.put("setor",usuario.getSetor());
        valores.put("telefone",usuario.getTelefone());
        valores.put("senha",usuario.getSenha());
        banco.update("usuario",valores,"cpf='"+ cpf +"'",null);
        banco.close();

    }
     /// metodo para exclusão de Usuarios ....
    public void excluiUsuario(int cpf, Usuario usuario){
        SQLiteDatabase banco = this.getWritableDatabase();
        banco.delete("usuario","cpf = '"+ cpf + "'",null);
        banco.close();
    }


    public int getNumeroRegistroUsuario(){
        String consulta = "SELECT * FROM usuario";
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor campo = db.rawQuery(consulta,null);// executa um comando sql
        int numeroRegistros = campo.getCount();
        campo.close();
        return numeroRegistros;
    }
    /*
    * SOLICITACAO
    * */

    public void insertSolicitacao(Solicitacao solicitacao) {//método insert
        SQLiteDatabase banco = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("cpf_usuario", solicitacao.getCpfUsuario());
        valores.put("motivo", solicitacao.getMotivo());
        valores.put("periodo", solicitacao.getPeriodo());
        valores.put("p_dias", solicitacao.isDias());
        valores.put("p_horas", solicitacao.isHoras());
        valores.put("hora_ideal", solicitacao.getHoraIdeal());
        valores.put("deferido", solicitacao.isDeferido());
        valores.put("placa_veic", solicitacao.getPlacaVeiculo());
        valores.put("status", 0);
        valores.put("finalizada",0); //ira mostrar que o veiculo ainda não foi devolvido

        banco.insert("solicitacao", null, valores);
    }

    public Solicitacao consultarSolicitacao(int solROWID){
        SQLiteDatabase banco = this.getReadableDatabase();
        Cursor campo = banco.query("solicitacao", new String[] {
                        "cpf_usuario", "motivo", "periodo", "p_dias", "p_horas", "horaRetirada", "deferido", "placa_veic", "localRetirada","modelo"}
                , "ROWID = " + solROWID, null, null, null, null, null);
        if (campo != null)
            campo.moveToFirst();

        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setCpfUsuario(campo.getInt(0));
        solicitacao.setMotivo(campo.getString(1));
        solicitacao.setPeriodo(campo.getInt(2));
        solicitacao.setDias(Boolean.getBoolean(campo.getString(3)));
        solicitacao.setHoras(Boolean.getBoolean(campo.getString(4)));
        solicitacao.setHoraRetirada(campo.getString(5));
        solicitacao.setDeferido(campo.getString(6).equals("1"));
        solicitacao.setPlacaVeiculo(campo.getString(7));
        solicitacao.setLocalRetirada(campo.getString(8));
        solicitacao.setModeloVeiculo(campo.getString(9));
        campo.close();
        return solicitacao;
    }

    public int getNumeroRegistroSolicitacao(){
        String consulta = "SELECT * FROM solicitacao";
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor campo = db.rawQuery(consulta,null);// executa um comando sql
        int numeroRegistros = campo.getCount();
        campo.close();
        return numeroRegistros;
    }

    public ArrayList<AprovaSolicitacao> listaSolicitacoes (ArrayList<AprovaSolicitacao> lista){
        Cursor campo;
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            campo = db.rawQuery("select cpf_usuario,motivo,nome, solicitacao.rowid, hora_ideal,setor, periodo,p_dias, p_horas from solicitacao "+
                    "inner join usuario on solicitacao.cpf_usuario = usuario.cpf and status = 0",null);
            if (campo !=null) {
                //campo.moveToFirst();
                while (true) {
                    if (campo.moveToNext()) {
                        AprovaSolicitacao usuario = new AprovaSolicitacao();
                        usuario.setCpf(campo.getString(0));
                        usuario.setMotivo(campo.getString(1));
                        usuario.setNome(campo.getString(2));
                        usuario.setIdRegistro(campo.getString(3));
                        usuario.setHoraIdeal(campo.getString(4));
                        usuario.setSetor(campo.getString(5));
                        boolean flag = (campo.getInt(campo.getColumnIndex("p_dias"))== 1);
                        boolean flag1 = (campo.getInt(campo.getColumnIndex("p_horas"))==1);
                        String vper="! ";
                        if (flag){
                            vper = campo.getString(6)+ " dias";
                        }
                        if (flag1){
                            vper = campo.getString(6)+ " horas";
                        }
                        usuario.setPeriodo(vper);
                        lista.add(usuario);
                    }else{
                        break;
                    }
                }
            }
            return lista;
        }catch (SQLException e){
            Toast.makeText(null,"Erro consulta ao BD !", Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public ArrayList<Veiculo> listaVeiculos (ArrayList<Veiculo> lista){
        Cursor campo;
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            //campo = db.rawQuery("select placa, modelo, combustivel, kmrodado from veiculo ",null);
            campo = db.rawQuery("select * from veiculo where status = 0",null); //status 0 = a disponivel
            if (campo !=null) {
                while (true) {
                    if (campo.moveToNext()) {

                            Veiculo veiculo = new Veiculo();
                            veiculo.setPlaca(campo.getString(campo.getColumnIndex("placa")));
                            veiculo.setModelo(campo.getString(campo.getColumnIndex("modelo")));
                            veiculo.setCombustivel(campo.getString(campo.getColumnIndex("combustivel")));
                            veiculo.setKmRodado(campo.getInt(campo.getColumnIndex("placa")));
                            //veiculo.setId(campo.getInt(campo.getColumnIndex("rowid")));
                            lista.add(veiculo);

                    }else{
                        break;
                    }
                }
            }

            return lista;
        }catch (SQLException e){
            Toast.makeText(null,"Erro consulta ao BD !", Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public void aprovarSolicitacao(AprovaSolicitacao solicitacao, int deferido){
        SQLiteDatabase banco = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        if (deferido == 1) {
            valores.put("hora_ideal", solicitacao.getHoraIdeal());
            valores.put("localRetirada", solicitacao.getLocalRetirada());
            valores.put("placa_veic", solicitacao.getPlaca());
            valores.put("deferido", deferido);
            valores.put("modelo",solicitacao.getModelo());
            valores.put("horaRetirada",solicitacao.getHoraRetirada());
        }
        valores.put("status", 1);
        banco.update("solicitacao",valores,"rowid = "+solicitacao.getIdRegistro(),null);

        // muda status do veiculo para em uso
        if (deferido == 1){
            ContentValues valveic = new ContentValues();
            valveic.put("status", 1);

            banco.update("veiculo",valveic,"placa ='"+solicitacao.getPlaca()+"'",null);
            banco.close();
        }
    }

    public StructSolicitacoes consultarSolicitacoes(/*rowid inicial, rowid final,*/int cpfUser){
        SQLiteDatabase banco = this.getReadableDatabase();
        Cursor campo = banco.query("solicitacao", new String[] {
                        "cpf_usuario","motivo", "hora_ideal", "deferido", "status", "rowid"}
                , "cpf_usuario = " + cpfUser, null, null, null, "rowid desc", null);
        if (campo != null)
            campo.moveToFirst();

        int qtd = campo.getCount();//qtd de registros que vieram

        String[] dados = new String[qtd];
        int[] rowids = new int[qtd];

        for(int i=0; i<qtd; i++){
            dados[i] = campo.getString(0) +" - "+ campo.getString(1) + " - "+ campo.getString(2) +" - ";
            if(campo.getString(4).equals("1")){
                dados[i] += campo.getString(3).equals("1")?"✔":"✖";//✔-✖
            }else{
                dados[i] += "-";
            }

            rowids[i] = campo.getInt(5);
            campo.moveToNext();//move para proxima linha
        }

        campo.close();

        StructSolicitacoes ss = new StructSolicitacoes(qtd);
        ss.descricao = dados;
        ss.rowid = rowids;

        return ss;
    }
    // usada para efetuar recebimento do veiculo

    public ArrayList<AprovaSolicitacao> listaVeiculosReceber (ArrayList<AprovaSolicitacao> lista){
        Cursor campo;
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            campo = db.rawQuery("select cpf_usuario,nome,solicitacao.rowid, placa_veic, modelo from solicitacao "+
                    "inner join usuario on solicitacao.cpf_usuario = usuario.cpf and solicitacao.finalizada = 0",null);
            if (campo !=null) {
                while (true) {
                    if (campo.moveToNext()) {
                        if (campo.getString(3)!=null){
                            AprovaSolicitacao usuario = new AprovaSolicitacao();
                            usuario.setCpf(campo.getString(0));
                            usuario.setNome(campo.getString(1));
                            usuario.setIdRegistro(campo.getString(2));
                            usuario.setPlaca(campo.getString(3));
                            usuario.setModelo(campo.getString(4));
                            lista.add(usuario);

                        }
                    }else{
                        break;
                    }
                }
            }
            return lista;
        }catch (SQLException e){
            return null;
        }
    }

    // Metodo para Liberar o Veiculo que estava emprestado.
    // Recebe o veiculo e jogo num outro banco de dados as informações sobre este veiculo.
    public void recebeVeiculo(AprovaSolicitacao solicitacao){
        SQLiteDatabase banco = this.getWritableDatabase();
        // finalizando a solicitação colocando como 1 no campo finalizada...
        ContentValues valores = new ContentValues();
        valores.put("finalizada",1);
        banco.update("solicitacao",valores,"placa_veic='"+solicitacao.getPlaca()+"' and cpf_usuario ='"+solicitacao.getCpf()+"'",null);
         /////// agora faz o lançamento na tabela de veiculos e libera o veiculo ////

        ContentValues valveic = new ContentValues();
        //valores.put("kmrodado",Integer.parseInt(solicitacao.)) // não foi incluido....... ficou de fora vai ficar ....
        valveic.put("status", 0);
        banco.update("veiculo",valveic,"placa ='"+solicitacao.getPlaca()+"'",null);

         /////// agora faz o lançamento numa tabela onde ficara a informação sobre todos as locações deste veiculo...
                 ///// deveria fazer , por conta de tempo habil não iremos fazer ..... mas
                 //// será necessário criar uma tabela a parte se armazene estes dados....
        banco.close();
    }

}