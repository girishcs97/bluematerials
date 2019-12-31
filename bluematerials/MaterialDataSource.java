package com.sial.bluematerials;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MaterialDataSource {

    public interface IResponseListener
    {
        public void onNetworkResponse();
        public void onNetworkError(String errorMEssage);
    }

    //http://155.250.39.28:8080/blue/api/item/fetch

    private  static  MaterialDataSource sharedInstance;

    public  static synchronized MaterialDataSource shared(Context context)
    {
        if(sharedInstance == null) {
            sharedInstance = new MaterialDataSource();
        }
        sharedInstance.mContext = context;
        return  sharedInstance;
    }
    public  static synchronized MaterialDataSource shared()
    {
        return  sharedInstance;
    }


    private MaterialDataSource()
    {}

    private final String baseUrl = "http://155.250.39.28:8080/blue/api/item/fetch";
    private int pageNumber = 0;
    private final int NUM_RECORDS_PER_PAGE = 10;


    private Context mContext;
    private RequestQueue mRequestQueue;

    private  RequestQueue getRequestQueue()
    {
        if(mRequestQueue == null)
        {
            mRequestQueue = Volley.newRequestQueue(mContext);
        }
        return mRequestQueue;
    }

    private List<Material> fetchedRecords = new ArrayList<>();

    public List<Material> getMaterials()
    {
        return fetchedRecords;
    }

    public void fetch(final IResponseListener listener)
    {
        String url = baseUrl + "?size=" + Integer.toString(NUM_RECORDS_PER_PAGE) +
                "&page=" + Integer.toString(pageNumber);



        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,  new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        //Parse the json object

                        if(0==pageNumber)
                        {
                            fetchedRecords = new ArrayList<>();
                        }

                        try {
                            JSONArray jsonRecords = response.getJSONArray("entities");
                            for(int i=0; i< jsonRecords.length(); i++)
                            {
                                JSONObject jRecord = jsonRecords.getJSONObject(i);
                                Material material = Material.parseMaterial(jRecord);
                                if(null != material)
                                {
                                    fetchedRecords.add(material);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(null != listener)
                            listener.onNetworkResponse();

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if(null != listener)
                            listener.onNetworkError(error.getMessage());
                    }
                });
        getRequestQueue().add(request);
    }
}
