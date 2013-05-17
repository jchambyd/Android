package com.jorgecd.diccionario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	EditText clave, descrip;
	List<HashMap<String,String>> aList; 
	SimpleAdapter adapter;
	 String[] from;
	// Array of strings
    String[] claves = new String[] {
            "Clave Wifi",
            "Clave PC",
            "Cumpleanos Tio",
            "Numero T.Credito",
            "DNI",
            "Numero L.Militar",
            "Placa Auto",
            "Definicion Sistema",
            "Norma 568A",
            "Norma 568B"
    };
    
 // Array of integers points to images stored in /res/drawable-ldpi/
    int[] flags = new int[]{
                R.drawable.computer,
                R.drawable.computer,
                R.drawable.personas,
                R.drawable.trabajo,
                R.drawable.personal,
                R.drawable.personal,
                R.drawable.personal,
                R.drawable.universidad,
                R.drawable.universidad,
                R.drawable.universidad
    };

    // Array of strings to store currencies
    String[] informacion = new String[]{
        "IngSis123",
        "clave_secreta",
        "25 de Junio",
        "4567-3434-6911-2344",
        "47630984",
        "47630984",
        "V1C-365",
        "Conjunto de elementos organizados y relacionadas que interactuan entre sí para lograr un objetivo",
        "Blanco Verde - Verde - Blanco Naranja - Azul - Blanco Azul - Naranja - Blanco Marron - Marron",
        "Blanco Naranja - Naranja - Blanco Verde - Azul - Blanco Azul - Verde - Blanco Marron - Marron"
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Orgnizador de Ideas");
        clave = (EditText) findViewById(R.id.infor_clave);
		descrip = (EditText) findViewById(R.id.infor_desc);	
        // Each row in the list stores country name, currency and flag
        aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<10;i++){
                HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt", claves[i]);
            hm.put("flag", Integer.toString(flags[i]) );
            hm.put("cur", informacion[i]);
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = { "flag","txt"};

        // Ids of views in listview_layout
        int[] to = { R.id.flag,R.id.txt};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.autocomplete_layout, from, to);
        
        // Getting a reference to CustomAutoCompleteTextView of activity_main.xml layout file
        CustomAutoCompleteTextView autoComplete = ( CustomAutoCompleteTextView) findViewById(R.id.autocomplete);
        
        
        /** Defining an itemclick event listener for the autocompletetextview */
        OnItemClickListener itemClickListener = new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
        		/** Each item in the adapter is a HashMap object. 
        		 *  So this statement creates the currently clicked hashmap object
        		 * */
        		
        		HashMap<String, String> hm = (HashMap<String, String>) arg0.getAdapter().getItem(position);
        		
        		/** Getting a reference to the TextView of the layout file activity_main to set Currency */
        		TextView tvCurrency = (TextView) findViewById(R.id.tv_currency) ;
        		
        		/** Getting currency from the HashMap and setting it to the textview */
        		tvCurrency.setText("Informacion : " + hm.get("cur"));
        	}
		};
        
		/** Setting the itemclick event listener */
        autoComplete.setOnItemClickListener(itemClickListener);
                
        /** Setting the adapter to the listView */
        autoComplete.setAdapter(adapter);        
        
    }
    
    /** A callback method, which is executed when this activity is about to be killed
     * This is used to save the current state of the activity 
     * ( eg :  Configuration changes : portrait -> landscape )  
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	TextView tvCurrency = (TextView) findViewById(R.id.tv_currency) ;		
    	outState.putString("currency", tvCurrency.getText().toString());
    	super.onSaveInstanceState(outState);
    }
    
    /** A callback method, which is executed when the activity is recreated 
     * ( eg :  Configuration changes : portrait -> landscape )  
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
    	TextView tvCurrency = (TextView) findViewById(R.id.tv_currency) ;		
    	tvCurrency.setText(savedInstanceState.getString("currency"));
    	super.onRestoreInstanceState(savedInstanceState);
    }
    
    
    public void guardarNuevaEntrada(View b){
		String c ,d;
		c = clave.getText().toString();
		d = descrip.getText().toString();
		HashMap<String, String> hm = new HashMap<String,String>();
        hm.put("txt", c);
        hm.put("flag",Integer.toString(flags[1]));
        hm.put("cur", d);
        aList.add(hm);
        clave.setText("");
        descrip.setText("");               
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
