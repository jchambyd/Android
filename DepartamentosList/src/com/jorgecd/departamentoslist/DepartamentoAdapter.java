package com.jorgecd.departamentoslist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
 
public class DepartamentoAdapter extends ArrayAdapter<Departamento> {
    private static final String tag = "DepartamentoArrayAdapter";
    private static final String ASSETS_DIR = "images/";
    private Context context;
    private ImageView dptoIcono;
    private TextView dptoName;
    private TextView dptoCapital;
    private List<Departamento> dptos = new ArrayList<Departamento>();
 
    public DepartamentoAdapter(Context context, int textViewResourceId, List<Departamento> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.dptos = objects;
    }
 
    public int getCount() {
        return this.dptos.size();
    }
 
    public Departamento getItem(int index) {
        return this.dptos.get(index);
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            // ROW INFLATION
            Log.d(tag, "Starting XML Row Inflation ... ");
            LayoutInflater inflater = (LayoutInflater) this.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.dpto_list_item, parent, false);
            Log.d(tag, "Successfully completed XML Row Inflation!");
        }
        /*row.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
            	Toast.makeText(context, "Website", Toast.LENGTH_LONG).show();  // What you want
            }
        });*/
        // Obteniendo item
        Departamento dpto = getItem(position);
        
        if(position%2 == 0) row.setBackgroundColor(Color.BLUE);
         
        // Obteniendo referencias 
        dptoIcono = (ImageView) row.findViewById(R.id.dpto_icono);
         
        dptoName = (TextView) row.findViewById(R.id.dpto_name);
         
        
        dptoCapital = (TextView) row.findViewById(R.id.dpto_capital);
 
        //Colocando el nombre del departamento
        dptoName.setText(dpto.nombre);
         
        // Formando la ruta de la imagen a colocar
        String imgFilePath = ASSETS_DIR + dpto.ruta;
        System.out.println(imgFilePath);
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(this.context.getResources().getAssets().open(imgFilePath));
            dptoIcono.setImageBitmap(bitmap);
            System.out.println("Cargo bien la Imagen");
        } catch (IOException e) {
            e.printStackTrace();
        }
         
        // Colocando la capital
        dptoCapital.setText((CharSequence)dpto.capital);
        return row;
    }
}
