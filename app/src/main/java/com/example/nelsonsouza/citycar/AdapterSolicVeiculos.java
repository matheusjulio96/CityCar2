package com.example.nelsonsouza.citycar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by matheus on 24/06/17.
 */

public class AdapterSolicVeiculos extends BaseAdapter {
    private Context context;
    private StructSolicVeiculos solveics;
    private int maiornumsol;

    public AdapterSolicVeiculos(Context context, StructSolicVeiculos ssolveics) {
        this.context = context;
        this.solveics = ssolveics;

        maiornumsol=0;
        for(int i : ssolveics.numSol){
            if(i > maiornumsol){
                maiornumsol = i;
            }
        }
    }

    @Override
    public int getCount() {
        return solveics.rowidVeic.length;
    }

    @Override
    public Object getItem(int position) {
        return solveics.getClass();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.modelo_lst_solic_veiculos,null);
        }
        TextView modelo = (TextView) convertView.findViewById(R.id.txtModeloSol);
        TextView ano = (TextView) convertView.findViewById(R.id.txtAnoSol);
        TextView qtdSol = (TextView) convertView.findViewById(R.id.txtQtdSolSol);
        ProgressBar pbQtdSol = (ProgressBar)convertView.findViewById(R.id.progressBar);
        pbQtdSol.setMax(maiornumsol);
        modelo.setText(solveics.modelo[position]);
        ano.setText(solveics.ano[position]);
        qtdSol.setText(String.valueOf(solveics.numSol[position]));
        pbQtdSol.setProgress(solveics.numSol[position]);
        return convertView;
    }
}
