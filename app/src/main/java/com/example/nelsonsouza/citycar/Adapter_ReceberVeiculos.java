package com.example.nelsonsouza.citycar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nelson Souza on 07/06/2017.
 */

public class Adapter_ReceberVeiculos extends BaseAdapter {
    private Context context;
    private ArrayList<AprovaSolicitacao> arrayList;

    public Adapter_ReceberVeiculos(Context context, ArrayList<AprovaSolicitacao> arrayList){
        this.context = context;
        this.arrayList = arrayList;

    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.model_recebe_veiculo,null);
        }
        TextView placa = (TextView) convertView.findViewById(R.id.idPlacaRecebeVeiculo);
        TextView modelo = (TextView)convertView.findViewById(R.id.idModeloRecebeVeiculo);
        placa.setText(arrayList.get(position).getPlaca());
        modelo.setText(arrayList.get(position).getModelo());

        return convertView;
    }


}
