package com.fyber.offerapisample.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fyber.offerapisample.R;
import com.fyber.offerapisample.ui.fragment.OfferAPIResultFragment;

/**
 * Created by wonmook on 2016. 9. 22..
 */
public class OfferAPIFormActivity extends AppCompatActivity {

    private final static String UID = "spiderman";
    private final static String APIKEY = "1c915e3b5d42d05136185030892fbb846c278927";
    private final static String APPID = "2070";
    private final static String PUB0 = "campaign2";
    private final static String LOCALE = "DE";
    private final static String IP  = "109.235.143.113";
    private final static String OFFER_TYPE = "112";

    private EditText uidEt;
    private EditText apikeyEt;
    private EditText appidEt;
    private EditText pub0Et;
    private Button   apiRequestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_api_form);
        initUI();
    }

    private void initUI(){
        uidEt = (EditText)findViewById(R.id.offer_api_uid);
        apikeyEt = (EditText)findViewById(R.id.offer_api_apikey);
        appidEt = (EditText)findViewById(R.id.offer_api_appid);
        pub0Et = (EditText)findViewById(R.id.offer_api_pup0);
        apiRequestBtn = (Button)findViewById(R.id.offer_api_request_button);

        uidEt.setText(UID);
        apikeyEt.setText(APIKEY);
        appidEt.setText(APPID);
        pub0Et.setText(PUB0);
        apiRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeOfferAPIGetRequest();
            }
        });
    }

    private void executeOfferAPIGetRequest(){

        OfferAPIResultFragment resultFragment = (OfferAPIResultFragment)getSupportFragmentManager().findFragmentById(R.id.offer_api_result_fragment);
        if(resultFragment !=null  && resultFragment.isInLayout())
        {
            resultFragment.showOfferAPIResult(uidEt.getText().toString(), apikeyEt.getText().toString(),appidEt.getText().toString(),pub0Et.getText().toString(),1);
        }

    }

}
