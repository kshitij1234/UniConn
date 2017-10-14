package com.pat_041.android.uniconn.networkingutils;

import android.content.Context;
import android.content.res.AssetManager;

import com.pat_041.android.uniconn.MainActivity;
import com.pat_041.android.uniconn.definitions.College;
import com.pat_041.android.uniconn.definitions.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

public class CallAPIUtils {

    /* This class will have a bunch of overloaded methods to make use of ConnectionUtils to get json and return
    different objects based on the type of query - college, event etc.
     */

    public static ArrayList<College> getStandAloneObjects(String url) throws JSONException {
        ArrayList<College> arrayList = new ArrayList<College>();
        JSONObject jsonObject = ConnectionUtils.makeConnection(url);
        JSONArray jsonArray = null;
        jsonArray = jsonObject.getJSONArray("records");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = null;
            obj = jsonArray.getJSONObject(i);
            String name = obj.getString("name");
            int id = obj.getInt("id");
            String address = obj.getString("address_line1") + " " + obj.getString("address_line2");
            String city = obj.getString("city");
            String state = obj.getString("state");
            String district = obj.getString("district");
            String pin_code = obj.getString("pin_code");
            String website = obj.getString("website");
            String year_of_establishment = obj.getString("year_of_establishment");
            String longitude = obj.getString("longitude");
            String latitude = obj.getString("latitude");
            String area = obj.getString("area");
            College clg = new College();
            clg.setAddress(address);
            clg.setArea(area);
            clg.setId(id);
            clg.setCity(city);
            clg.setCollegeName(name);
            clg.setDistrict(district);
            clg.setEst_date(year_of_establishment);
            clg.setState(state);
            clg.setPincode(pin_code);
            clg.setWebsite(website);
            clg.setLatitute(latitude);
            clg.setLongitude(longitude);
            clg.setExtra(city,state);
            System.out.println(id);
            System.out.println(address);
            System.out.println(city);
            System.out.println(state);
            System.out.println(pin_code);
            System.out.println(website);
            System.out.println(year_of_establishment);
            System.out.println(area);
            System.out.println(latitude);
            System.out.println(longitude);
            System.out.println(district);
            arrayList.add(clg);
        }
        return arrayList;
    }

    public static ArrayList<College> getStandAloneObjects(String key, String value) throws JSONException {
        ArrayList<College> arrayList = new ArrayList<College>();
        String basicInformationOfCollegesUrl = ApiCalls.BASIC_INFORMATION_OF_STANDALONE_URL;
        basicInformationOfCollegesUrl += "&filters[" + key + "]=" + value;
        return getStandAloneObjects(basicInformationOfCollegesUrl);
    }

    public static ArrayList<College> getStandAloneObjects(int t,String value) throws JSONException {
        ArrayList<College> arrayList = new ArrayList<College>();
        HashSet<College> hashSet = new HashSet<>();
        ArrayList<String> keys = new ArrayList<>(Arrays.asList("name","city","state","district","pin_code","website","year_of_establishment","longitude","latitude"));
        for (String key: keys) {
            hashSet.addAll(getStandAloneObjects(key,value));
        }
        for (College c : hashSet){
            if (c != null)
                arrayList.add(c);
        }
        return arrayList;
    }

    public JSONObject loadJSONfromAsset(Context context) throws JSONException {
        String json = null;
        try {
            InputStream is = context.getAssets().open("yourfilename.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        JSONObject obj = new JSONObject(json);
        return obj;
    }

    public List<Event> getListOfEvents(Context context) throws JSONException {
        JSONArray jsonArray = null;
        jsonArray = loadJSONfromAsset(context).getJSONArray("records");
        ArrayList<Event> arrayList = new ArrayList<>();
        for (int i=0;i<jsonArray.length();i++){
            Event event = new Event();
            JSONObject obj = null;
            obj = jsonArray.getJSONObject(i);
            event.setName(obj.getString("name"));
            event.setId(obj.getInt("id"));
            event.setCity(obj.getString("city"));
            event.setState(obj.getString("state"));
            event.setSdate(obj.getString("sdate"));
            event.setEdate(obj.getString("edate"));
            event.setInfo(obj.getString("info"));
            event.setImg(obj.getString("img"));
            event.setLink(obj.getString("link"));
            arrayList.add(event);
        }
        return arrayList;
    }
}
