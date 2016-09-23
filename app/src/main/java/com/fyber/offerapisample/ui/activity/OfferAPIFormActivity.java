package com.fyber.offerapisample.ui.activity;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fyber.offerapisample.R;
import com.fyber.offerapisample.ui.fragment.OfferAPIResultFragment;

/**
 * Created by wonmook on 2016. 9. 22..
 * Main Activity to get param from EditText and show Offer API result to {@link OfferAPIResultFragment}
 *
 * @see OfferAPIResultFragment
 */
@VisibleForTesting
public class OfferAPIFormActivity extends AppCompatActivity {

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
        apiRequestBtn = (Button)findViewById(R.id.offer_api_request_btn);

        uidEt.setText(getResources().getString(R.string.test_uid));
        uidEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uidEt.setError(null);
            }
        });
        apikeyEt.setText(getResources().getString(R.string.test_apikey));
        apikeyEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apikeyEt.setError(null);
            }
        });
        appidEt.setText(getResources().getString(R.string.test_appid));
        appidEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appidEt.setError(null);
            }
        });
        pub0Et.setText(getResources().getString(R.string.test_pub0));
        apiRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeOfferAPIGetRequest();
            }
        });
    }

    private void executeOfferAPIGetRequest(){

        if(!isUidValid(uidEt.getText().toString())){
            uidEt.setError(getResources().getString(R.string.error_invalid_uid));
            uidEt.requestFocus();
            return ;
        }

        if(!isApiKeyValid(apikeyEt.getText().toString())){
            apikeyEt.setError(getResources().getString(R.string.error_invalid_apikey));
            apikeyEt.requestFocus();
            return ;
        }

        if (!isAppIdValid(appidEt.getText().toString())){
            appidEt.setError(getResources().getString(R.string.error_invalid_appid));
            appidEt.requestFocus();
            return ;
        }

        OfferAPIResultFragment resultFragment = (OfferAPIResultFragment)getSupportFragmentManager().findFragmentById(R.id.offer_api_result_fragment);
        if(resultFragment !=null  && resultFragment.isInLayout())
        {
            resultFragment.showOfferAPIResult(uidEt.getText().toString(), apikeyEt.getText().toString(),appidEt.getText().toString(),pub0Et.getText().toString(),1);
        }

    }

    private boolean isUidValid(String uid) {
        return uid!=null && uid.length() > 0;
    }

    private boolean isApiKeyValid(String apikey){
        return apikey!=null && apikey.length() > 0;
    }

    private boolean isAppIdValid(String appid){
        return appid!=null && appid.length()>0;
    }

}
