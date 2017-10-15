package com.pat_041.android.uniconn.networkingutils;

import com.pat_041.android.uniconn.definitions.College;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

public class CallAPIUtils {

    /* This class will have a bunch of overloaded methods to make use of ConnectionUtils to get json and return
    different objects based on the type of query - college, event etc.
     */

    public static ArrayList<College> getStandAloneObjects(String url) throws JSONException {
        ArrayList<College> arrayList = new ArrayList<College>();
        JSONObject jsonObject=ConnectionUtils.makeConnection(url);
        JSONArray jsonArray=null;
        jsonArray=jsonObject.getJSONArray("records");
        for(int i=0;i<jsonArray.length();i++)
        {
            JSONObject obj=null;
            obj = jsonArray.getJSONObject(i);
            String name = obj.getString("name");
            int id = obj.getInt("id");
            String address = obj.getString("address_line1")+" "+obj.getString("address_line2");
            String city = obj.getString("city");
            String state = obj.getString("state");
            String district = obj.getString("district");
            String pin_code = obj.getString("pin_code");
            String website = obj.getString ("website");
            String year_of_establishment = obj.getString ("year_of_establishment");
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
    public static ArrayList<College> getStandAloneObjectsByStateName(String stateName) throws JSONException {
        ArrayList<College> arrayList = new ArrayList<College>();
        String basicInformationOfCollegesUrl = ApiCalls.BASIC_INFORMATION_OF_STANDALONE_URL;
        basicInformationOfCollegesUrl+="&filters[state]="+stateName;
        return getStandAloneObjects(basicInformationOfCollegesUrl);
    }
    public static ArrayList<College> getStandAloneObjectsByCityName(String cityName) throws JSONException {
        ArrayList<College> arrayList = new ArrayList<College>();
        String basicInformationOfCollegesUrl = ApiCalls.BASIC_INFORMATION_OF_STANDALONE_URL;
        basicInformationOfCollegesUrl+="&filters[state]="+cityName;
        return getStandAloneObjects(basicInformationOfCollegesUrl);
    }

}
