package weathermap.mobimedia.com.andoridweatermap.parser;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

import weathermap.mobimedia.com.andoridweatermap.bean.Weatherbean;

/**
 * Created by sadhana on 10/6/15.
 */
public class ParserWeather {




    public static Weatherbean parseResponse (String resp, Weatherbean result) {
        Log.d("SwA", "Response [" + resp + "]");

        Weatherbean  mweatherbean=new Weatherbean();
        try {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(new StringReader(resp));

            String tagName = null;
            String currentTag = null;

            int event = parser.getEventType();
            boolean isFirstDayForecast = true;
            while (event != XmlPullParser.END_DOCUMENT) {
                tagName = parser.getName();

                if (event == XmlPullParser.START_TAG) {
                    if (tagName.equals("yweather:wind")) {


                       // ...
                    }
                    else if (tagName.equals("yweather:atmosphere")) {
                       // ...
                    }
                    else if (tagName.equals("yweather:forecast")) {

                        //
                    }
                    else if (tagName.equals("yweather:condition")) {

                        if (currentTag == null) {
                            String text = parser.getAttributeValue(null, "text");
                            mweatherbean.setText(text);
                            String temp=parser.getAttributeValue(null, "temp");
                            mweatherbean.setTemp(temp);
                            String date=parser.getAttributeValue(null, "date");
                            mweatherbean.setDate(date);



                        }
                    }
                    else if (tagName.equals("yweather:units")) {
                        //...
                    }
                    else if (tagName.equals("yweather:location")) {
                        //...
                    }
                    else if (tagName.equals("image"))
                        currentTag = "image";
                    else if (tagName.equals("url")) {
                        if (currentTag == null) {
                            String imageUrl = parser.getAttributeValue(null, "src");
                            mweatherbean.setimageUrl(imageUrl);
                            Log.i("Parser Weather","IMage url =="+ mweatherbean.getImageUrl());
                        }
                    }
                    else if (tagName.equals("lastBuildDate")) {
                        currentTag="update";
                    }
                    else if (tagName.equals("yweather:astronomy")) {
                        //...
                    }

                }
                else if (event == XmlPullParser.END_TAG) {
                    if ("image".equals(currentTag)) {
                        currentTag = null;
                    }
                }
                else if (event == XmlPullParser.TEXT) {
                    //if ("update".equals(currentTag))
                      //  result.lastUpdate = parser.getText();
                }
                event = parser.next();
            }

        }
        catch(Throwable t) {
            t.printStackTrace();
        }

        return result;
    }
}
