package com.example.anjana.binmaster.HomePage;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.anjana.binmaster.GridPage;
import com.example.anjana.binmaster.MySingleton;
import com.example.anjana.binmaster.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class DailyService extends Fragment {

    String url="http://192.168.8.100:8000/api/display";
    String uId=new Integer(1).toString();
    List<String> dates;
    ArrayAdapter<String> adapter;
    ListView requestList;


    public DailyService() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_daily_service, container, false);


        requestList = (ListView)rootView.findViewById(R.id.datesLV);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dates = new ArrayList<String>();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i = 0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        dates.add(jsonObject.getString("requestedDate"));
                    }
                    adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_expandable_list_item_1,dates);
                    requestList.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();

                params.put("uId",uId);
                return params;
            }
        };


        MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);


        FloatingActionButton fab = (FloatingActionButton)rootView.findViewById(R.id.btnFloating);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View rootView) {
                // Click action
                switch (rootView.getId()){
                    case R.id.btnFloating:
                        getActivity().startActivity(new Intent(getActivity(),GridPage.class));
                        break;
                }
            }
        });
        return  rootView;
    }

}




