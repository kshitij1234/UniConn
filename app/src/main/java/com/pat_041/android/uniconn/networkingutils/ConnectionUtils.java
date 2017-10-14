package com.pat_041.android.uniconn.networkingutils;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class ConnectionUtils {

    private ConnectionUtils(){}

    private static URL makeURL(String s){
        URL url = null;
        try{
            url = new  URL(s);
        }catch (MalformedURLException e){
            Log.e("ConnectionUtils","Error forming url",e);
        }
        return url;
    }

    public static JSONObject makeConnection(String s){
        URL url = makeURL(s);
        String jsonResponse = null;
        if(url==null)
            return null;

        try{
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("ConnectionUtils","Unable to get data from url",e);
        }

        if(jsonResponse==null||jsonResponse.equals(""))
            return null;

        try {
            return new JSONObject(jsonResponse);
        } catch (JSONException e) {
            Log.e("ConnectionUtils","Bad json response");
        }
        return null;
    }

    private static String makeHttpRequest(URL url)throws IOException{

        String json="";
System.out.println("here");
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            System.out.println("gepirgeignoiwng");
            urlConnection.connect();
            System.out.println("why the hell \n"+urlConnection.getResponseCode());

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                json = readFromStream(inputStream);
                try {
                    System.out.println(json);
                }catch (Exception e){
                    System.out.println(e);
                }
            }else {
                Log.e("ConnectionUtils", "Error response code: " + urlConnection.getResponseCode());
            }

        }catch (IOException e){
            Log.e("ConnectionUtils","Error retrieving json response",e);
        }finally {
            if (urlConnection!=null)
                urlConnection.disconnect();
            if(inputStream!=null)
                inputStream.close();
        }
        return json;
    }

    private static String readFromStream(InputStream inputStream)throws IOException {

        StringBuilder output = new StringBuilder();

        if(inputStream!=null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

}
