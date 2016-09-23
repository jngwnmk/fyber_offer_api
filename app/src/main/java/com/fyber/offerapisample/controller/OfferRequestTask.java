package com.fyber.offerapisample.controller;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fyber.offerapisample.R;
import com.fyber.offerapisample.model.OfferAPIRequest;
import com.fyber.offerapisample.model.OfferAPIResponse;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;

/**
 * Created by wonmook on 2016. 9. 22..
 *
 * Fyber Mobile Offer API request task
 * It requests offer list with RESTful API composed with {@link OfferAPIBuilder}.
 * It returns {@link OfferAPIResponse}, which contains Offer information through {@link TaskCompleteListener}
 *
 * @see OfferAPIBuilder
 * @see OfferAPIResponse
 * @see TaskCompleteListener
 */
public class OfferRequestTask extends AsyncTask<Void, Void, OfferAPIResponse>{


    //For the purpose of test, using hard-coded BASE URL for Mobile Offer API
    private static String OFFER_URL = "http://api.fyber.com/feed/v1/offers.json";

    private static final String HASH_SIGNATURE = "X-Sponsorpay-Response-Signature";

    private OfferAPIRequest offerAPIRequest= null;
    private TaskCompleteListener taskCompleteListener = null;
    private Context context;

    private static String DEBUGTAG = OfferRequestTask.class.getSimpleName();

    public OfferRequestTask(Context context, OfferAPIRequest offerAPIRequest, TaskCompleteListener taskCompleteListener){
        this.context = context;
        this.offerAPIRequest = offerAPIRequest;
        this.taskCompleteListener = taskCompleteListener;
    }

    @Override
    protected OfferAPIResponse doInBackground(Void... params) {

        try{
            //Initialize RestTemlate object
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

            //Build complete URI by calculating hashkey
            URI offerAPIURI = getOfferAPIBuilderwithParam().buildURI();
            Log.d(DEBUGTAG,offerAPIURI.toString());

            //Make the request to the API passing the params and the authentication hash
            //Get the result from the response
            ResponseEntity<String> response = restTemplate.getForEntity(offerAPIURI, String.class);

            //Check the returned hash to check that it’s a real response (check signature)
            if(checkSignature(response, offerAPIRequest.getAPIKEY())){
                //Correct response
                ObjectMapper objectMapper = new ObjectMapper();
                OfferAPIResponse offerAPIValidResponse = objectMapper.readValue(response.getBody(), OfferAPIResponse.class);
                offerAPIValidResponse.setHttpCode(response.getStatusCode());
                return offerAPIValidResponse;
            } else {

                //Get response but it is not valid
                OfferAPIResponse offerAPINonValidResponse = new OfferAPIResponse();
                offerAPINonValidResponse.setCode(context.getResources().getString(R.string.offer_response_no_valid_txt));
                offerAPINonValidResponse.setMessage(context.getResources().getString(R.string.offer_response_no_valid_txt));
                offerAPINonValidResponse.setHttpCode(response.getStatusCode());
                return offerAPINonValidResponse;
            }

        }
        //Handle exception response 400, 401, ETC
        catch (HttpStatusCodeException e){
            OfferAPIResponse offerAPIBadResponse = new OfferAPIResponse();
            try {
                JSONObject errorResponse = new JSONObject(e.getResponseBodyAsString());
                offerAPIBadResponse.setCode(errorResponse.getString(context.getResources().getString(R.string.offer_api_response_code)));
                offerAPIBadResponse.setMessage(errorResponse.getString(context.getResources().getString(R.string.offer_api_response_msg)));
                offerAPIBadResponse.setHttpCode(e.getStatusCode());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            return offerAPIBadResponse;
        } catch (Exception e){
            Log.e(DEBUGTAG, e.getMessage(), e);
        }

        return null;
    }

    @Override
    protected void onPostExecute(OfferAPIResponse response){
        taskCompleteListener.onTaskCompleted(response);
    }

    private OfferAPIBuilder getOfferAPIBuilderwithParam(){
        //Setting parameter related to Google advertising ID
        try {
            offerAPIRequest.setGoogle_ad_id(AdvertisingIdClient.getAdvertisingIdInfo(context).getId());
            offerAPIRequest.setGoogle_ad_id_limited_tracking_enabled(AdvertisingIdClient.getAdvertisingIdInfo(context).isLimitAdTrackingEnabled());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        }

        //Initialize Fiber Mobile Offer API parameters
        OfferAPIBuilder offerAPIBuilder = OfferAPIBuilder.init(OFFER_URL, offerAPIRequest.getAPIKEY())
                .setParam(context.getResources().getString(R.string.offer_api_appid), String.valueOf(offerAPIRequest.getAppid()))
                .setParam(context.getResources().getString(R.string.offer_api_uid), offerAPIRequest.getUid())
                .setParam(context.getResources().getString(R.string.offer_api_ip), offerAPIRequest.getIp())
                .setParam(context.getResources().getString(R.string.offer_api_locale), offerAPIRequest.getLocale())
                .setParam(context.getResources().getString(R.string.offer_api_google_ad_id),offerAPIRequest.getGoogle_ad_id())
                .setParam(context.getResources().getString(R.string.offer_api_google_ad_id_limited_tracking_enabled), String.valueOf(offerAPIRequest.isGoogle_ad_id_limited_tracking_enabled()))
                .setParam(context.getResources().getString(R.string.offer_api_page), String.valueOf(offerAPIRequest.getPage()))
                .setParam(context.getResources().getString(R.string.offer_api_timestamp), String.valueOf(offerAPIRequest.getTimestamp()))
                .setParam(context.getResources().getString(R.string.offer_api_offer_types), offerAPIRequest.getOffer_type())
                .setParam(context.getResources().getString(R.string.offer_api_pub0), offerAPIRequest.getPub0())
                .setParam(context.getResources().getString(R.string.offer_api_os_version), offerAPIRequest.getOs_version());

        return offerAPIBuilder;
    }

    //Check the returned hash to check that it’s a real response (check signature)
    private boolean checkSignature(ResponseEntity<String> response, String apikey) {

        //1. Get the hashkey from response
        String responseHash = response.getHeaders().get(HASH_SIGNATURE).get(0);
        Log.d(DEBUGTAG, response.getBody());

        //2. Concatenate the full response body with your API key
        String responseBodyAndAPIKey = response.getBody() + apikey;

        //3. Hash the whole resulting string using SHA1
        String hashedValue = new String(Hex.encodeHex(DigestUtils.sha1(responseBodyAndAPIKey)));
        Log.d(DEBUGTAG, hashedValue);
        Log.d(DEBUGTAG, responseHash);

        return responseHash.equals(hashedValue);
    }

}
