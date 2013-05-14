package com.jorgecd.departamentoslist;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import android.util.Log;
 
public class DepartamentoParser {
 
    private static final String tag = "DepartamentoParser";
    private static final String FILE_EXTENSION= ".png";
     
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private final List<Departamento> list;
 
    public DepartamentoParser() {
        this.list = new ArrayList<Departamento>();
    }
 
    private String getNodeValue(NamedNodeMap map, String key) {
        String nodeValue = null;
        Node node = map.getNamedItem(key);
        if (node != null) {
            nodeValue = node.getNodeValue();
        }
        return nodeValue;
    }
 
    public List<Departamento> getList() {
        return this.list;
    }
 
    /**
     * Parse XML file 
     * 
     * @param inStream
     */
    public void parse(InputStream inStream) {
    	System.out.println("Iniciando la parseada");
        try {
            // TODO: after we must do a cache of this XML!!!!
            this.factory = DocumentBuilderFactory.newInstance();
            this.builder = this.factory.newDocumentBuilder();
            this.builder.isValidating();
            Document doc = this.builder.parse(inStream, null);
            
            //doc.getDocumentElement().normalize();
 
            NodeList dptoList = doc.getElementsByTagName("departamento");
            final int length = dptoList.getLength();
            System.out.println("La lectura del xml " + length);
            for (int i = 0; i < length; i++) {
                final NamedNodeMap attr = dptoList.item(i).getAttributes();
                final String nombre = getNodeValue(attr, "nombre");
                final String capital = getNodeValue(attr, "capital");
                final String link = getNodeValue(attr, "link");
 
                System.out.println("Lee: " + nombre + " " + capital + " " + link );
                // Construct Country object
                Departamento country = new Departamento(nombre, capital, link, nombre + FILE_EXTENSION);
                 
                // Add to list
                this.list.add(country);
                 
                Log.d(tag, country.toString());
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}