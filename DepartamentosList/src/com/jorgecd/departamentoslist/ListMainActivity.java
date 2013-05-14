package com.jorgecd.departamentoslist;

import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class ListMainActivity extends Activity {
	
	List<Departamento> dptoList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Departamento del Peru");
		setContentView(R.layout.activity_list_main);
		// Creando el parser para raw/countries.xml
        DepartamentoParser dptoParser = new DepartamentoParser();
        InputStream inputStream = getResources().openRawResource(R.raw.departamentos);
        // Parseamos
        dptoParser.parse(inputStream);
        System.out.println("PARSEO BIEN");
        // Obteniendo los departamentos y sus datos
        dptoList = dptoParser.getList();
         
        // Creando el ArrayAdapter
        DepartamentoAdapter adapter = new DepartamentoAdapter(getApplicationContext(), R.layout.dpto_list_item, dptoList);
        System.out.println("PUDO ADAPTAR"); 
        // Get reference to ListView holder
        ListView lv = (ListView) this.findViewById(R.id.dptosList);
         
        // Set the ListView adapter
        lv.setAdapter(adapter);
        System.out.println("ADPTER FINAL");
        
        
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) {                
                String item = dptoList.get(position).link;                
                //Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();
                Intent i=new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(item));
        		startActivity(i);
            }
        });        
	}	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_main, menu);
		return true;
	}
}
