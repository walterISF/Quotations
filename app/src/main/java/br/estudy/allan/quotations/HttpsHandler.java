package br.estudy.allan.quotations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Allan on 08/09/2016.
 */
public class HttpsHandler {

    private final int readTimeOut = 15000;
    private final int connectionTimeOut = 15000;
    private String response;

    public String doHttpsRequest(String URLRequest)throws Exception{

        int cont = 0;
        URL url;

        try{
            url = new URL(URLRequest);
            HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(readTimeOut);
            conn.setConnectTimeout(connectionTimeOut);
            conn.connect();

            if(conn.getResponseCode() == HttpsURLConnection.HTTP_OK){
                cont=0;
                InputStream is = conn.getInputStream();
                this.response = getJSONFromInputStream(is);
            }
            else{
                if(cont<3){
                    cont++;
                    doHttpsRequest(URLRequest);
                }
                else{
                    throw new Exception("Error, Impossible to connect with the URL");
                }
            }
            return response;
        }
        catch (IOException e) {
            if(cont<3){
                cont++;
                doHttpsRequest(URLRequest);
            }
            else{
                throw new Exception("Error, Impossible to establish connection");
            }
        }
        return response;
    }

    private String getJSONFromInputStream(InputStream is)throws Exception{
        int cont=0;
        try{
            StringBuilder response = new StringBuilder();
            String reader;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            while((reader = br.readLine())!=null){
                //System.out.println(reader);
                response.append(reader);
            }
            return response.toString();
        } catch (IOException e){
            if(cont<3){
                cont++;
                getJSONFromInputStream(is);
            }
            else{
                throw new Exception("Impossible to read from server");
            }
        }
        return null;
    }
}
