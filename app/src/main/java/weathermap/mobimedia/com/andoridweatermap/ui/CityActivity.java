package weathermap.mobimedia.com.andoridweatermap.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

import weathermap.mobimedia.com.andoridweatermap.Adapter.CityAdapter;
import weathermap.mobimedia.com.andoridweatermap.R;
import weathermap.mobimedia.com.andoridweatermap.bean.CityResult;
import weathermap.mobimedia.com.andoridweatermap.parser.ParserCity;

public class CityActivity extends Activity {

    String cityname;
    int x;
    AutoCompleteTextView edt;
    Context ctx;
    ArrayList<CityResult> cityResultList;
    CityAdapter adpt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        cityResultList = new ArrayList<CityResult>();
         //cityname=edt.getText().toString().trim();





        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String woid = cityResultList.get(x).getWoeid().toString().trim();
                          Log.i("City Activity","selected country name"+woid);
                Intent intent_detail = new Intent(CityActivity.this, weatherDetail.class);
                intent_detail.putExtra("woidname",woid);
                startActivity(intent_detail);



            }
        });





        edt = (AutoCompleteTextView) findViewById(R.id.edt);


        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {



           cityname=edt.getText().toString().trim();
                new Thread(new Runnable() {
                    public void run() {
                        ParserCity parscity = new ParserCity();
                        parscity.setText(cityname);

                        try {
                            cityResultList = parscity.getCityList();

                            Log.i("cityActivity", "==" + cityResultList);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                adpt = new CityAdapter(getApplicationContext(), cityResultList);
                edt.setAdapter(adpt);
                adpt.notifyDataSetChanged();

            }
        });
        edt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                x = position;


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_city, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


