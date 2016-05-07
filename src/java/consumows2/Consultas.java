/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumows2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import org.json.JSONObject;

/**
 *
 * @author dhursa
 */
public class Consultas {
    
    //JSONObject obj = new JSONObject();
    
    public Consultas() {
        
    }
    
    public boolean ConectarJson(String usuario, String pass) throws MalformedURLException, ProtocolException, IOException{
        
        String webService = "http://localhost/ws_sso/index_ws.php?funcion=login&nickname="+ usuario + "&clave="+ pass + "";
        URL url = new URL (webService);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("ACCEPT", "Application/json");
        String output = "";
        boolean respuesta = false;
        
        if(conn.getResponseCode()== 200){
            
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String line = "";
            
            while((line = br.readLine()) != null){
                    //System.out.println(output);
                    //salida = json.getString("OK");
                    output += line;
                    
            }
            
            
            //obj.getJSONObject(output);
            JSONObject obj = new JSONObject(output);
            
            String estado = obj.getString("estado");
            //System.out.println(output);
            br.close();
            
            if(estado.equals("1")){
                respuesta = true;
            }else{
                respuesta = false;    
            }
        }
        
        return respuesta;
        
    }
    
}
