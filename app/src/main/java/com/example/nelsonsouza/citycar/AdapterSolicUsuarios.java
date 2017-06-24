package com.example.nelsonsouza.citycar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by matheus on 23/06/17.
 */

public class AdapterSolicUsuarios extends BaseAdapter {
    private Context context;
    private StructSolicUsuarios solusers;
    private int maiornumsol;

    public AdapterSolicUsuarios(Context context, StructSolicUsuarios ssolusers) {
        this.context = context;
        this.solusers = ssolusers;

        maiornumsol=0;
        for(int i : solusers.numLocs){
            if(i > maiornumsol){
                maiornumsol = i;
            }
        }
    }

    @Override
    public int getCount() {
        return solusers.rowidUser.length;
    }

    @Override
    public Object getItem(int position) {
        return solusers.getClass();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.modelo_lst_solic_usuarios,null);
        }
        TextView nome = (TextView) convertView.findViewById(R.id.txtNome);
        TextView numsol = (TextView) convertView.findViewById(R.id.txtNumSol);
        ProgressBar pbnumsol = (ProgressBar)convertView.findViewById(R.id.progressBar);
        pbnumsol.setMax(maiornumsol);
        nome.setText(solusers.nome[position]);
        numsol.setText(String.valueOf(solusers.numLocs[position]));
        pbnumsol.setProgress(solusers.numLocs[position]);
        return convertView;
    }
}
