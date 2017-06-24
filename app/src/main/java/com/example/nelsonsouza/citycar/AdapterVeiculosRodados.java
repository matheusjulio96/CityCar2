package com.example.nelsonsouza.citycar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * Created by matheus on 22/06/17.
 */

public class AdapterVeiculosRodados extends BaseAdapter {
    private Context context;
    private StructVeiculos veiculos;
    private int maiorkm;

    public AdapterVeiculosRodados(Context context, StructVeiculos sveiculos) {
        this.context = context;
        this.veiculos = sveiculos;

        maiorkm=0;
        for(int i : veiculos.km){
            if(i > maiorkm){
                maiorkm = i;
            }
        }
    }

    @Override
    public int getCount() {
        return veiculos.rowid.length;
    }

    @Override
    public Object getItem(int position) {
        return veiculos.getClass();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.modelo_lst_veiculos_rodados,null);
        }
        TextView modelo = (TextView) convertView.findViewById(R.id.txtModeloRod);
        TextView ano = (TextView) convertView.findViewById(R.id.txtAnoRod);
        TextView km = (TextView) convertView.findViewById(R.id.txtKmRod);
        ProgressBar pbkm = (ProgressBar)convertView.findViewById(R.id.progressBar);
        pbkm.setMax(maiorkm);
        modelo.setText(veiculos.modelo[position]);
        ano.setText(veiculos.ano[position]);
        km.setText(String.valueOf(veiculos.km[position]));
        pbkm.setProgress(veiculos.km[position]);
        return convertView;
    }
}
