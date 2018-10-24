package test.seven_store.com.sevenstore;


import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ApiService {
    private static  final  String TAG = "ApiService";
    public static final String URL = "http://192.168.56.1/myshop/alldata.php";
    private Context context;
    private Gson gson = new Gson();

    public ApiService(Context context) {
        this.context = context;
    }

    public  void getProducts(final OnResultCallback<List<Product>> onResultCallback) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Type type = new TypeToken<List<Product>>(){}.getType();
                        List<Product>products = gson.fromJson(response.toString(),type);
                        //-----------------------------------------------------------------------

                        /*for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject productJsonObject = response.getJSONObject(i);
                                Product product = new Product();
                                product.setId(productJsonObject.getString("id"));
                                product.setTitle(productJsonObject.getString("name"));
                                product.setImageUrl(productJsonObject.getString("image"));
                                product.setCurrentPrice(productJsonObject.getString("price_current"));
                                product.setPreviusPrice(productJsonObject.getString("price_previous"));
                                product.setStatus(productJsonObject.getInt("status"));
                                products.add(product);

                            } catch (JSONException e) {
                                Log.e(TAG, "onResponse: "+ e.toString());

                            }
                        }*/
                        //--------------------------------------------------------------------------------------
                        onResultCallback.onReceived(products);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onResultCallback.onError();

            }
        });
        Volley.newRequestQueue(context).add(jsonArrayRequest);

    }
    public  interface OnResultCallback<T>{
        void onReceived(T t);
        void  onError();
    }
}
