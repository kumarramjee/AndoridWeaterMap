package weathermap.mobimedia.com.andoridweatermap.util;

import android.app.DownloadManager;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import weathermap.mobimedia.com.andoridweatermap.bean.Weatherbean;
import weathermap.mobimedia.com.andoridweatermap.parser.ParserWeather;

/**
 * Created by sadhana on 10/6/15.
 */
public class Weather {

    String imageUrl;
    String lastUpdate;

    public static interface WeatherClientListener {
        public void onWeatherResponse(Weatherbean weather);
    }


    public static void getWeather(final String woeid, final String unit, final RequestQueue rq, final WeatherClientListener listener) {
       // String url2Call = makeWeatherURL(woeid, unit);
       // Log.d("SwA", "Weather URL [" + url2Call + "]");
        final Weatherbean result = new Weatherbean();
        final StringRequest req = new StringRequest(Request.Method.GET, "http://weather.yahooapis.com/forecastrss", new Response.Listener<String>() {


            //@Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("w", woeid);
                params.put("u", unit);
                return params;
            }
            @Override
            public void onResponse(String s) {
                ParserWeather.parseResponse(s, result);
                listener.onWeatherResponse(result);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }


        });

        rq.add(req);
    }




}



